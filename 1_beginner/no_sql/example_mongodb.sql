/* Initialy MongoDB in your Local Machine and execute the script down. Is necessary uncomment the section in this file */

/*
// Cria/Utiliza o Banco de Dados
use example_cities
*/

/*
// Realiza a Inserção de mais de um Registro
db.states.insertMany(
    [
        {
            uf: "SP", name: "São Paulo", habitants: 451320,
            cities: [
                {name: "Araraquara", habitants: 1220},
                {name: "Ribeirão Preto", habitants: 520},
                {name: "São Carlos", habitants: 65230}
            ]
        },
        {
            uf: "RJ", name: "Rio de Janeiro", habitants: 365623, cities: [

                {name: "Rio de Janeiro", habitants: 5252},
                {name: "Niteroi", habitants: 6412}
            ]
        },
    ]);
*/

// Obtem e Exibe os Resultados "Bonitinhos" (Em JSON Identado)
/*
db.states.find().pretty()
*/

// Exclui uma Collection
/*
db.getCollection("cities").drop()
*/

/*
// Obtem ou Oculta os dados confome os Parametros passados no Project
db.states.aggregate(
    {$project: {name: 1, _id: 0, "cities.name": 1}}
   )
*/

/*
// Obtem a Soma dos Habitantes das Cidades dentro dos Estados
db.states.aggregate(
    // Define quais campos serão obtidos inicialmente e Soma os Habitantes por Estados
    {$project: {_id: 0, uf: 1, habitantsState: {$sum: "$cities.habitants"}}},
    // Agrupa os Resultados Anteriores e Obtem o Numero Total de Habitantes
    {$group: {_id: null, totalHabitants: {$sum: "$habitantsState"}}},
    // Remove o _id (Ele aparece como "null")
    {$project: {_id: 0}}
    )
*/

/*
// Filtra ao Maximo um Item/Documento
db.states.aggregate([
    // Obtem o Item/Documento que possui a chave "cities.name" como "Araraquara"
    {$match: {"cities.name": "Araraquara"}},
    // Separa em Objetos as Cidades
    {$unwind: "$cities"},
    // Obtem o Item/Documento que possui a chave "cities.name" como "Araraquara"
    {$match: {"cities.name": "Araraquara"}},
    // Configura quais dados serão exibidos
    {$project: {_id: 0, name: 1, habitants: 1}}
]).pretty()
*/

/*
// Atualiza o Numero de Habitantes do Estado de SP
db.states.updateOne({uf:"SP"}, {$set: {habitants: 41634861}} )
db.states.find().pretty()
*/

/*
// Atualiza a Lista de Cidades de SP, inserindo "Ibate" nela
db.states.updateOne({uf:"SP"}, {$push: {cities: {name: "Ibate", habitants: 6522}}} )
db.states.find().pretty()
*/

/*
// Insere o Estado de Minas Gerias sem Cidades
db.states.insertOne({name: "Minas Gerais", uf:"MG", city:"Minas Gerais", habitants: 45125415 })
// Atualiza a Lista de Cidades de Minas Gerais, inserindo uma Cidade
// Somente usar o "$set" na atualização quando deseja substituir a Infromação/Object/Array
db.states.updateOne({uf:"MG"}, {$set: {cities: {name: "Ibate", habitants: 6522}}} )
db.states.find().pretty()
*/

/*
// Obtem os Estados que Possuem Habitantes
db.states.find({habitants: {$exists: true}}, {_id: 0, name: 1})
*/

/*
// Obtem as Cidades que Possuem Habitantes Informados
db.states.find({"cities.habitants": {$exists: true}}, {_id: 0, "cities.name": 1})
*/

/*
// Insere um Estado Ficticio para Exemplificar a Exclusão
db.states.insertOne({name: "Minas Gerais de Ouro", uf: "MGO", city: "Minas Gerais de Ouro", habitants: 545132})
// Remove um ou mais registros compativies com o Filtro
db.states.deleteMany({"uf": "MGO"})
db.states.find().pretty()
*/