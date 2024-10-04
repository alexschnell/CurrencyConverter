package br.com.currencyconverter.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import br.com.currencyconverter.repository.ExchangeRateRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.Date

class ExchangeRateViewModel(private val repository: ExchangeRateRepository): ViewModel() {

//    Usando LiveData:
    private val _exchangeRates = MutableLiveData<Pair<List<Pair<String, Double>>, Date?>>()
    val exchangeRates: LiveData<Pair<List<Pair<String, Double>>, Date?>> get() = _exchangeRates

    //Usando Flow
//    private val _exchangeRates = MutableStateFlow<Pair<List<Pair<String, Double>>, Date>?>(null)
//    val exchangeRates: StateFlow<Pair<List<Pair<String, Double>>, Date>?> = _exchangeRates

    suspend fun fetchExchangeRates(): Pair<List<Pair<String, Double>>, Date?>? {
        return repository.getExchangeRates()
//        viewModelScope.launch {
//            val result = repository.getExchangeRates()
//            _exchangeRates.postValue(result)
//        }
    }

}