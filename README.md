# spring-challenge ---Desafio de testes
## Solução de desafio grupo03
### INTEGRANTES:
* Emanuelle Besckow Figueiredo
* Pedro Sol Barbosa Montes 
* Ana Gabriela Siqueira Franco 
* Douglas Santos Rodrigues 
* Renan Santana Sousa
* Willy de Jesus Passos

--

Projeto do desafio Java + Spring +testes
## DESAFIO: REQUISITOS E APRESENTAÇÃO:
O Seguinte desafio foi proposto para aplicar os conhecimentos até então alcançados no BootcampIt - Meli

* A. Cenário

A empresa "Seu Imóvel" precisa ser capaz de calcular a metragem e o custo dos
diferentes imóveis que possui em sua carteira de clientes.
Para isso, solicita de cada imóvel: um nome do imóvel, um bairro (nome do bairro e
valor do metro quadrado no bairro) e a quantidade de cômodos que cada imóvel tem.
Ao mesmo tempo, cada cômodo contém um nome, uma largura e um comprimento.
Para isso, é necessário a criação de uma API Rest que permita:

● US-0001: Calcular a área total de uma propriedade. 

● US-0002: Indicar o preço dessa mesma propriedade com base na área total.
Lembre-se que os preços por metro quadrado são determinados em função do
bairro. 

● US-0003: Determinar qual é o maior cômodo da propriedade. 

● US-0004: Determinar a área de cada cômodo. 

"Seu Imóvel" tem padrões de qualidade muito elevados no que diz respeito aos
produtos de software que utiliza, uma vez que as transações que realiza diariamente são por quantias muito altas de dinheiro. É por isso que um consultor de informática que trabalha com eles estabeleceu uma série de validações que considera necessárias para levar em consideração na hora de incorporar dados, bem como diferentes testes de unidade que garantem os cálculos corretos.

##  Para acessar as funcionalidades da  API:

* Observações: 

** Sobre requisitos da API: para cadastrar uma Casa, é necessário que já esteja cadastrada uma localidade. 

** Para cadastrar os nomes, é necessário iniciar com letra maiúscula, e também não é aceito letras maiúsculas em outra localização da palavra que não ni início.


### LOCALIDADE:
* lista totas as localidades cadastradas:
* GET: /localidade
* retorna localidade em busca por id:
* GET: localidade/{id} 
* cadastra uma localidade:
* POST: /localidade
* PAYLOAD: 
{
	"nome": "Araras",
	"precoM2": 200
}
* PUT: localidade/{id}
* PAYLOAD: 
{
	"nome": "Araras",
	"precoM2": 200
}

* deleta localidade por id:
* DELETE: localidade/{id}

### CASA:
* lista totas as casas cadastradas:
* GET: /casa
* retorna casa em busca por id:
* GET: casa/{id} 
* cadastra uma casa:
* POST: /casa
* PAYLOAD: 
{
	"nome":"Casinha",
	"localidadeId": 1,
	"comodos": [
		{
				"nome":"Quarto",
				"largura": 2,
				"comprimento":5
		 },
		{
				"nome":"Escritorio",
				"largura": 5,
				"comprimento":5
		 },
		{
				"nome":"Banheiro",
				"largura": 2,
				"comprimento":2
		 },
		{
				"nome":"Cinema",
				"largura": 10,
				"comprimento":10
		 }
	 ]
} 

* deleta casa por id:
* DELETE: casa/{id} 

### SOBRE AS SOLUÇÕES PARA OS REQUISITOS: 

● US-0001: Calcular a área total de uma propriedade. 
* Buscando a casa por id, será retornada sua área total 

● US-0002: Indicar o preço dessa mesma propriedade com base na área total.
Lembre-se que os preços por metro quadrado são determinados em função do
bairro. 
* Buscando a casa por id, será retornada seu preço, levando em consideração a metragem e a localização 

● US-0003: Determinar qual é o maior cômodo da propriedade. 
* Buscando a casa por id, serão retornados todos os cômodos, bem como um boolean(flag) que assinala como verdadeiro o maior cômodo

● US-0004: Determinar a área de cada cômodo. 
* Buscando a casa por id, serão retornados todos os cômodos, bem como suas áreas

