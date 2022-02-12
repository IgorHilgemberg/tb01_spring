# Trabalho 01 - Desenvolvimento Web (Engenharia de Computação - UEPG)

Uma api para visualizar e analisar pagamentos de jogadores de futebol mensalistas.

## Membros da equipe
* Igor Hilgemberg - RA: 17162426
* Eduardo Patrick - RA: 172

## Descrição do trabalho
Trabalho acadêmico de um sistema gerenciamento de pagamentos simplista de jogadores de futebol mensalistas realizado para a disciplina de Desenvolvimento Web da UEPG.

## Classes Models
* Jogador.java
* Pagamento.java
## Classes Controllers
* JogadorController.java
* PagamentoController.java
## Classes Repositories
* JogadorRepository.java
* PagamentoRepository.java

## Banco de dados
* Foi usado o MYSQL para gravar manter as informações.
* As tabelas do banco de dados são criadas automaticamente ao iniciar a aplicação.
* As configurações do banco de dados se encontram no arquivo **application_properties**. O comando **create-drop** foi usado para que as informações não fiquem gravadas no banco após o término da aplicação.

## Rotas da API

### Para Jogador:
| Método | URL | Ação |
|---|---|---|
|GET |/api/jogadores --> | lista todos os jogadores cadastrados |
|GET | /api/jogadores/id --> | lista as informações de determinado jogador |
|POST | /api/jogadores --> | cria um novo jogador |
|PUT |/api/jogadores/id --> | edita um determinado joagdor |
|DELETE |/api/jogadores/id --> | deleta um jogador determinado |
|DELETE |/api/jogadores --> | deleta todos os jogadores cadastrados |
### Para Pagamento:
| Método | URL | Ação |
|---|---|---|
|GET |/api/pagamentos --> | lista todos os pagamentos |
|GET |/api/pagamentos/id --> | lista um determinado pagamento |
|POST |/api/pagamentos --> | cria um novo pagamento |
|PUT |/api/pagamentos/id --> | edita um determinado pagamento |
|DELETE |/api/pagamentos/id --> | deleta um determinado pagamento |
|DELETE |/api/pagamentos --> | deleta todos os pagamentos cadastrados |
