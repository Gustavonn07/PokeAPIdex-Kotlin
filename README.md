# PROGRAMAÇÃO PARA DISPOSITIVOS MÓVEIS

**Professor:** Windson Viana de Carvalho

**Alunos:**
- Gustavo Nepomuceno Nogueira (554728)
- Taís Gomes Crisóstomo Sabóia (554499)

------------------------------------------------------------------------

## Projeto: API REST -- Pokédex

## Descrição do Projeto

Neste projeto foi desenvolvida uma aplicação Android do tipo
**Pokédex**, utilizando a linguagem **Kotlin** e o framework **Jetpack
Compose** para a construção da interface do usuário. A aplicação consome
dados da **PokéAPI**, realizando requisições HTTP para obter informações
reais sobre Pokémons, como nome, número de identificação, imagem e
tipos. Todo o fluxo de comunicação com a API foi implementado de forma
assíncrona, garantindo que a interface do usuário não seja bloqueada
durante o carregamento dos dados.

A lógica do sistema foi organizada em diferentes componentes, promovendo
uma separação clara de responsabilidades. A camada de acesso a dados
ficou encarregada de buscar e tratar as informações retornadas pela API,
convertendo o JSON recebido em objetos utilizáveis dentro da aplicação.
Esses objetos são então utilizados pelas telas para exibição e interação
com o usuário, tornando o código mais limpo, organizado e de fácil
manutenção.

Foram desenvolvidas três telas principais. A primeira permite ao usuário
pesquisar um Pokémon pelo nome ou ID e visualizar sua imagem juntamente
com seus detalhes básicos. A segunda tela exibe as informações
detalhadas do Pokémon selecionado e possibilita que ele seja salvo,
aplicando uma regra de negócio que limita o cadastro a no máximo seis
Pokémons. A terceira tela apresenta a lista de Pokémons salvos,
permitindo a remoção individual de cada item, o que possibilita ao
usuário gerenciar seu time de forma dinâmica.

------------------------------------------------------------------------

## Descrição da API

A **PokéAPI** é uma API pública e gratuita que disponibiliza dados
completos e estruturados sobre o universo Pokémon, sendo amplamente
utilizada para fins educacionais. Ela fornece informações detalhadas
sobre Pokémons, tipos, habilidades, movimentos, sprites, evoluções,
regiões, itens e diversos outros elementos da franquia, permitindo que
desenvolvedores acessem esses dados por meio de requisições HTTP no
formato REST.

Os dados retornados pela PokéAPI são fornecidos em formato **JSON**, o
que facilita sua manipulação em diferentes linguagens de programação e
plataformas. Cada Pokémon pode ser acessado por meio de um identificador
numérico ou pelo próprio nome, o que torna a API flexível para
implementações de busca.

**Link para a API:** https://pokeapi.co/

------------------------------------------------------------------------

## Vídeo de Demonstração e Repositório

-   **GitHub:** https://github.com/Gustavonn07/PokeAPIdex-Kotlin\
-   **Vídeo da aplicação:** https://www.youtube.com/watch?v=-84M1CYc5vM
