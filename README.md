# JBBank
Aplicativo de Banco Digital

----------------------------------------------------------------------------------------------------

# Tecnologias Usadas

#### - Kotlin Language
#### - Clean Architecture
#### - Modulus App | Core
#### - Detekt
#### - Firebase

----------------------------------------------------------------------------------------------------

## Clean Architecture:

    - Arquitetura que visa a separação de conceitos dividindo o software em camadas que são
    dependentes, onde cada camada tem sua responsabilidade muito bem definida.

    - A Clean Architecture não é uma arquitetura exclusiva para desenvolvimento mobile. Em outras
    palavras, a Clean Architecture é uma arquitetura que visa a organização de um projeto, em 
    camadas, de forma a alcançar manutenibilidade, escalabilidade e testabilidade.

----------------------------------------------------------------------------------------------------

#### :app => Contém componentes de fremework do Android (view, database, context, navigation, framework)

    - Presentation: Activity, Fragment, Adapters, View Components
    - Framework: ViewModel, Navigation, Room Database, Firebase, Retrofit, Injeção de Dependência

#### :core => Contém regras de negócio, casos de uso e repositórios (nada relacionado com o framework do Android)

    - Use cases: Interfaces e implementações de todos os casos de uso da aplicação
    - Repository: Repositórios de acesso aos dados e implementações de fontes de dados diversos
    - Domain: Classes de domínio da aplicação

----------------------------------------------------------------------------------------------------

#### Detekt:

    - Ferramenta de análise estática de código servem para automatizar o processo de verificação
    de código (code review automático) antes mesmo do  programa ser executado, 
    de forma rápida e eficiente.

    - Auxilia os desenvolvedores no aprimoramento da qualidade de código gerado, sinaliza error de
    programação e indica violação de princípios fundamentais de design da linguagem.

        - Códigos duplicados
        - Nome de variáveis
        - Classes e métodos grandes
        - etc.

----------------------------------------------------------------------------------------------------

#### Firebase:

    - Com o Firebase, você desenvolve apps de alta qualidade, expande sua base de usuários e gera 
    mais receita. Cada recurso funciona de maneira independente.

    - O Firebase Realtime Database é um banco de dados NoSQL hospedado na nuvem. Com ele, você 
    armazena e sincroniza dados entre os seus usuários em tempo real.

----------------------------------------------------------------------------------------------------

