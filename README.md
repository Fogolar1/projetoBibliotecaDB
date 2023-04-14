# Bibloteca DB - Enzo Fogolari

## Compilação e Execução do código

Para realizar a compilação do código basta realizar um clone do projeto e abrir a pasta dele na IDE preferida (IntelliJ é recomendado). Ao clonar, basta executar o método Main da classe Main e você será levado para o menu principal da aplicação.

![image](https://user-images.githubusercontent.com/70671981/232145907-d53cd090-33cf-4208-919f-058d207d2045.png)

## Conexão com o banco de dados

É necessário baixar o [backup do banco de dados](./bibliotecaDB.backup) e configurá-lo no PostGreSQL. Feito isso, deve-se entrar na classe Conexao e alterar as informações da conexão, como usuário, senha, url e driver. No meu exemplo, as configurei dessa forma : 

![image](https://user-images.githubusercontent.com/70671981/232148179-8508244f-a084-4aab-910a-370142b87809.png)

### Com tudo isso feito, o código já deve estar sendo rodado no terminal da sua máquina.
