# CurrencyConverter | Aplicativo que realiza conversão de taxas de câmbio

Repositório para armazenar um aplicativo que realiza a conversão de taxas de câmbio.

## :books: Funcionamento do APP
Ao iniciar, o aplicativo busca na API "https://cdn.moeda.info" as moedas e taxas de câmbio atuais baseadas no USD (dólar americano).
Inicialmente, o aplicativo seleciona as moedas: <br/>
De: "USD" (dólar americano) <br/>
Para: "BRL" (real brasileiro) <br/>
Valor: 1 <br/>

O usuário pode alterar as moedas, valor desejado e clicar no botão "Conversão". <br/>
O aplicativo realizará a conversão apresentando o valor.

## :books: Funcionamento da API
A cada hora, é gerado pela API um arquivo JSON com dados atualizados da taxa de câmbio. <br/>
A moeda base é o dólar USD. A API está disponível na nuvem CDN. <br/>
Para maiores informações, consulte: <br/>
https://moeda.info/pages/api

## :books: Padrões, Frameworks e/ou Bibliotecas utilizadas

| Padrão / Framework / Biblioteca | Finalidade |
|-------|---------|
| [Arquitetura MVVM]() | A arquitetura MVVM (Model-View-ViewModel) foi usada no desenvolvimento do aplicativo para separar claramente as responsabilidades entre a interface do usuário (View), a lógica de negócios e a manipulação de dados (Model). Usada para garantir que o código esteja bem estruturado, com uma separação clara entre a lógica de visualização e o código de negócios. |
| [Retrofit2]() | Consumo da API |
| [OkHttp]() | Usado para interceptar os logs |
| [Layouts com XML]() | Construção do layout com XML que é a abordagem tradicional no desenvolvimento Android. <br/> Caso deseje visualizar um projeto com Jetpack Compose, acesse meu outro projeto em: https://github.com/alexschnell/imgurcats.git |

## :books: Este repositório
```
https://github.com/alexschnell/CurrencyConverter.git
````
