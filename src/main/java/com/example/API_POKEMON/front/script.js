const formulario = document.querySelector("form");
const botao = document.querySelector("button");
const jnome = document.querySelector(".nome");
const jidade = document.querySelector(".idade");
const jpeso = document.querySelector(".peso");
const jaltura = document.querySelector(".altura");
const jtreinador = document.querySelector(".treinadorId");
const jtipo = document.querySelector(".tipo");
const jfraqueza = document.querySelector(".fraquezas");
const jhabilidade = document.querySelector(".habilidades");

async function criarPokemon(){
    
    response = await fetch("http://localhost:8080/pokemon",
    {
        headers: {
            "Accept": "application/json",
            "Content=Type": "application/json"
        },
        metho: "POST",
        body: JSON.stringify({
            nome: jnome.value,
            idade: jidade.value,
            peso: jpeso.value,
            altura: jaltura.value,
            treinadorId: jtreinador.value,
            tipo: jtipo.value,
            fraquezas: jfraqueza.value,
            habilidades: jhabilidade.value
        })
    })
    .then(function (res) {console.log(res)})
    .catch(function (res) {console.log(res)})
}

function deletarPokemon(){
    jnome.value ="",
    jidade.value="",
    jpeso.value ="",
    jaltura.value ="",
    jtreinador.value ="",
    jtipo.value ="",
    jfraqueza.value ="",
    jhabilidade.value =""
}

formulario.addEventListener("submit", function(event){
    event.preventDefault();
    criarPokemon();
    deletarPokemon();
});