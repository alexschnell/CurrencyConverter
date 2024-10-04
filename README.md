# CurrencyConverter | Aplicativo que realiza conversão de taxas de câmbio

Repositório para armazenar um aplicativo realiza conversão de taxas de câmbio.

## :books: Funcionamento do APP
Ao iniciar, o aplicativo busca na API "https://cdn.moeda.info" as moedas e taxas de cãmbio atuais baseadas no USD (dólar americano).
Inicialmente, o aplicativo seleciona as moedas:
De: "USD" (dólar americano)
Para: "BRL" (real brasileiro)
Valor: 1

O usuário pode alterar as moedas, valor desejado e clicar no botão "Conversão".
O aplicativo realizará a conversão apresentando o valor.

## :books: Funcionamento API
A cada hora, é gerado pela API um arquivo JSON com dados atualizados da taxa de câmbio.
A moeda base é o dólar USD. A API está disponível na nuvem CDN.
Para maiores informações, consulte:
https://moeda.info/pages/api


## :books: Padrões e bibliotecas utilizadas

| Padrão / Biblioteca | Finalidade |
|-------|---------|
| [Arquitetura MVVM]() | A arquitetura MVVM (Model-View-ViewModel) foi usada no desenvolvimento do aplicativo para separar claramente as responsabilidades entre a interface do usuário (View), a lógica de negócios e a manipulação de dados (Model). Usada para garantir que o código esteja bem estruturado, com uma separação clara entre a lógica de visualização e o código de negócios. |
| [Retrofit2]() | Consumo da API |
| [OkHttp]() | Usado para interceptar os logs |
| [Koin]() | Injeção de Dependências |
| [Coil]() | Carregar e exibir imagens a partir de uma URL na web |
| [Jetpack Compose]() | Kit de ferramentas recomendado pelo Android para criar IUs nativas. |

## :books: Este repositório
```
https://github.com/alexschnell/CurrencyConverter.git
````
