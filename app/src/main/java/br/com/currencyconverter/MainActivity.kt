package br.com.currencyconverter

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import br.com.currencyconverter.api.Endpoint
import br.com.currencyconverter.databinding.ActivityMainBinding
import br.com.currencyconverter.repository.ExchangeRateRepositoryImpl
import br.com.currencyconverter.util.NetworkUtils
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import br.com.currencyconverter.util.formatDate
import br.com.currencyconverter.util.formatDateHour
import br.com.currencyconverter.viewmodel.ExchangeRateViewModel
import br.com.currencyconverter.viewmodel.ExchangeRateViewModelFactory
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {
//    private lateinit var spFrom: Spinner
//    private lateinit var spTo: Spinner
//    private lateinit var edtValueFrom: EditText
//    private lateinit var btConverter: Button
//    private lateinit var txtResult: TextView
//    private lateinit var txtDateConverter: TextView
//    private lateinit var progressBar: ProgressBar
    private lateinit var exchangeRateViewModel: ExchangeRateViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Forma antiga usando "findViewById"
//        setContentView(R.layout.activity_main)
//        spFrom = findViewById(R.id.spFrom)
//        spTo = findViewById(R.id.spTo)
//        edtValueFrom = findViewById(R.id.edtValueFrom)
//        btConverter = findViewById(R.id.btConverter)
//        txtResult = findViewById(R.id.txtResult)
//        txtDateConverter = findViewById(R.id.txtDateConverter)
//        progressBar = findViewById(R.id.progressBar)

        getCurrencies()

        with(binding) {
            btConverter.setOnClickListener {
                progressBar.visibility = View.VISIBLE
                convertMoney(spFrom.selectedItem.toString(), spTo.selectedItem.toString())
            }
        }

    }

    fun getCurrencies() {
        val api = NetworkUtils.getRetrofitInstance("https://cdn.moeda.info").create(Endpoint::class.java)
        val repository = ExchangeRateRepositoryImpl(api)

        exchangeRateViewModel = ViewModelProvider(this, ExchangeRateViewModelFactory(repository))
            .get(ExchangeRateViewModel::class.java)

        exchangeRateViewModel.viewModelScope.launch {
            // Chama a função que busca as taxas de câmbio
            val exchangeRatesData = exchangeRateViewModel.fetchExchangeRates()
            exchangeRatesData?.let { data ->
                val ratesList = data.first
                val lastUpdateDate = data.second

                val idxUSD = ratesList.indexOfFirst { it.first == "USD" }
                val idxBRL = ratesList.indexOfFirst { it.first == "BRL" }
                val adapter = ArrayAdapter(
                    baseContext,
                    android.R.layout.simple_spinner_dropdown_item,
                    ratesList.map { it.first }
                )
                with(binding){
                    progressBar.visibility = View.GONE
                    spFrom.adapter = adapter
                    spTo.adapter = adapter
                    spFrom.setSelection(idxUSD)
                    spTo.setSelection(idxBRL)
                    if (edtValueFrom.text.isEmpty() || edtValueFrom.text.toString().toDouble() == 0.0)
                        edtValueFrom.setText("1")
                    txtResult.text = String.format("%.2f",
                        if (edtValueFrom.text.isNotEmpty() && edtValueFrom.text.toString().toDouble() > 0)
                            edtValueFrom.text.toString().toDouble() * ratesList[idxBRL].second
                        else ratesList[idxBRL].second
                    )

                    if (lastUpdateDate != null)
                        txtDateConverter.text = "Data: ${formatDateHour(lastUpdateDate)}"
                    else
                        txtDateConverter.text = ""

                    // Imprima a quantidade de registros e os dados
                    println("Quantidade de registros: ${ratesList.count()}")
                    ratesList.forEach { pair ->
                        println("Rate: ${pair.first} -> Valor: ${pair.second}")
                    }
                    println("Data da ultima atualização: ${formatDate(lastUpdateDate)}")
                }
            }
        }

        // Inicia a busca dos dados
        exchangeRateViewModel.viewModelScope.launch {
            exchangeRateViewModel.fetchExchangeRates()
        }

    }

    fun convertMoney(rateFrom: String, rateTo: String) {
        val api = NetworkUtils.getRetrofitInstance("https://cdn.moeda.info").create(Endpoint::class.java)
        val repository = ExchangeRateRepositoryImpl(api)

        exchangeRateViewModel = ViewModelProvider(this, ExchangeRateViewModelFactory(repository))
            .get(ExchangeRateViewModel::class.java)

        exchangeRateViewModel.viewModelScope.launch {
            // Chama a função que busca as taxas de câmbio
            val exchangeRatesData = exchangeRateViewModel.fetchExchangeRates()
            exchangeRatesData?.let { data ->
                val ratesList = data.first
                val lastUpdateDate = data.second

                val idxFrom = ratesList.indexOfFirst { it.first == rateFrom }
                val idxTo = ratesList.indexOfFirst { it.first == rateTo }
                val adapter = ArrayAdapter(
                    baseContext,
                    android.R.layout.simple_spinner_dropdown_item,
                    ratesList.map { it.first }
                )
                with(binding){
                    progressBar.visibility = View.GONE
                    spFrom.adapter = adapter
                    spTo.adapter = adapter
                    spFrom.setSelection(idxFrom)
                    spTo.setSelection(idxTo)

                    if (edtValueFrom.text.isEmpty() || edtValueFrom.text.toString().toDouble() == 0.0)
                        edtValueFrom.setText("1")
                    if (spFrom.selectedItem == "USD") {
                        txtResult.text = String.format("%.2f",
                            if (edtValueFrom.text.isNotEmpty() && edtValueFrom.text.toString().toDouble() > 0)
                                edtValueFrom.text.toString().toDouble() * ratesList[idxTo].second
                            else ratesList[idxTo].second
                        )
                    } else {
                        if (spFrom.selectedItem.toString().isNotEmpty() &&
                            spTo.selectedItem.toString().isNotEmpty()
                        ) {
                            val rateA: Double = ratesList[idxFrom].second
                            val rateB: Double = ratesList[idxTo].second
                            val result: Double = rateB / rateA
                            txtResult.text = String.format("%.2f",
                                if (edtValueFrom.text.isNotEmpty() && edtValueFrom.text.toString().toDouble() > 0)
                                    edtValueFrom.text.toString().toDouble() * result
                                else result
                            )
                            println("RateA: ${spFrom.selectedItem} -> ${rateA}")
                            println("RateB: ${spTo.selectedItem} -> ${rateB}")
                            println("Result: ${result}")
                        }
                    }
                    if (lastUpdateDate != null)
                        txtDateConverter.text = "Data: ${formatDateHour(lastUpdateDate)}"
                    else
                        txtDateConverter.text = ""
                }
            }
        }

    }
}