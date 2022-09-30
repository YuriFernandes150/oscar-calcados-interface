# oscar-calcados-interface

Essa interface interaje com a API de calçados desenvolvida por mim, ela não funcionará sem um Endpoint válido para a API.
Você pode ver como baixar e configurar a API [aqui.](https://github.com/YuriFernandes150/oscar-calcados-test-api)

## Configuração
![image](https://user-images.githubusercontent.com/16628285/193137495-ffbc57b8-ef8b-4f6f-88d8-f2bc590d6c05.png)

Ao iniciar a interface, você vai se deparar com essa tela, você pode clicar no link para ser redirecionado para a config da API, ou pode continuar para configurar.

![image](https://user-images.githubusercontent.com/16628285/193137748-3e92e6a2-fd08-4aa7-adc3-51b2b2a4bb43.png)


Ao clicar em OK, poderemos iniciar a config, aqui foi especificado uma URL na máquina local.
A caixa de seleção ```API Local``` permite que apontemos onde o jar da API se encontra em nosso sistema, a interface então vai iniciar automaticamente a API durante a sua inicialização, e irá encerrar a API quando a interface for fechada.

![image](https://user-images.githubusercontent.com/16628285/193138240-404844a2-25b0-4a2f-a316-1dc3e9559975.png)


Nesse Exemplo, vamos utilizar essa função com nossa API local, então precisaremos especificar um caminho para o nosso jar.
Após a config concluída, clicamos em salvar e depois em OK.

![image](https://user-images.githubusercontent.com/16628285/193138639-995b2112-30bc-48f2-8f95-1e6a53bb5732.png)


A interface vai tentar se comunicar com a API.
![image](https://user-images.githubusercontent.com/16628285/193138765-fbeb4b41-d0de-433f-9a37-e778500c1657.png)

## Tela Inicial

Em caso de sucesso, veremos a tela inicial, onde iremos executar a maioria das operações.
![image](https://user-images.githubusercontent.com/16628285/193138935-09e9f518-c5a2-474a-9d92-9a7c6add92f4.png)

## Cadastrando
Aqui já temos alguns registros, mas podemos adicionar mais, clicando em ```Cadastrar Novo Calçado```

![image](https://user-images.githubusercontent.com/16628285/193139188-e171d380-5f4b-4df1-a17e-614a62e69c84.png)

Com isso, abrimos a tela de cadastro, onde podemos cadastrar e alterar calçados, marcas, tamanhos, cores e categorias.

Vamos adicionar um de cada, Cor, Categoria, Tamanho e Marca.

![image](https://user-images.githubusercontent.com/16628285/193139565-417a90df-8089-43b8-be82-920190381a17.png)

![image](https://user-images.githubusercontent.com/16628285/193139591-3fe232fa-dd87-4489-909a-72d1d99ab8d2.png)

![image](https://user-images.githubusercontent.com/16628285/193139739-4b62d7e7-4d52-4b4f-bdc0-13ba51592087.png)

![image](https://user-images.githubusercontent.com/16628285/193139757-35c62202-5a7b-43cd-823c-fe7a566ee74c.png)

Depois disso, podemos  cadastrar o calçado:

![image](https://user-images.githubusercontent.com/16628285/193140016-e5c1e419-a4cb-463e-90ef-4d60780d81c2.png)


Agora, podemos ver nosso calçado na tabela:

![image](https://user-images.githubusercontent.com/16628285/193140185-dbd36be5-2b6a-4de6-a421-4f26b0fbf28e.png)

## Alterando

Clicando 2x em qualquer coluna da tabela, abrimos novamente a tela de cadastro, mas dessa vez em modo de Edição:

![image](https://user-images.githubusercontent.com/16628285/193141601-f9722e1e-bd04-40c1-9b27-ccd634990ba4.png)

Vamos editar o código desse calçado para 22:

![image](https://user-images.githubusercontent.com/16628285/193141784-424ee4ad-92ca-4420-b0f5-b53825f2d5f1.png)

Agora ele está com o código correto sequencial:
![image](https://user-images.githubusercontent.com/16628285/193141904-c39ac72c-582a-48c9-964a-68b23de85fb0.png)

## Busca

Clicando no botão com sinal ```>``` , podemos ver os filtros:

![image](https://user-images.githubusercontent.com/16628285/193142207-59b6b254-2540-4431-8551-dae261a22dab.png)

Podemos usar qualquer combinação de filtros que quisermos, vamos tentar buscar todos os calçados da cor Azul, e que sejam da categoria Chinelo:

![image](https://user-images.githubusercontent.com/16628285/193142447-6b348d54-cfcf-4142-a848-4a0fc1c8a5bd.png)

E essas são as funções básicas desssa interface, por favor, não hesite em publicar issues caso encontre algum problema!
