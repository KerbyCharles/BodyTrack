
const historicoPeso = [
    { data: "10/11/2026", peso: 75 },
    { data: "11/11/2026", peso: 77 },
    { data: "12/11/2026", peso: 78 },
    { data: "13/11/2026", peso: 70 },
    { data: "14/11/2026", peso: 84.1 }
];


const pesoAtual = document.getElementById("pesoAtual")
const botaoMenu = document.getElementById("menu-mobile");
const sidebar = document.getElementById("sidebar-mobile");


const tabelaHistorico = document.querySelector("#minhaMedicao tbody");

const formulario = document.getElementById("formMedicao");
const campoPeso = document.getElementById("peso")
const campoData = document.getElementById("data")

const datas = historicoPeso.map(registro => registro.data);
const pesos = historicoPeso.map(registro => registro.peso);

const contexto = document.getElementById("graficoPeso");


const graficoPeso = new Chart(contexto, {
    type: "line",

    data: {
        labels: datas,
        datasets: [{
            label: "Peso (kg)",
            data: pesos,
            borderColor: "#0E7490",
            backgroundColor: "rgba(6, 182, 212, 0.12)",
            borderWidth: 3,
            fill: true,
            tension: 0.4
        }]
    },

    options: {
        responsive: true,
        maintainAspectRatio: false,

        plugins: {
            legend: {
                display: true
            }
        },

        scales: {
            y: {
                beginAtZero: false
            }
        }
    }
});
formulario.addEventListener("submit", (evento) =>{
    evento.preventDefault();

    const peso = Number(campoPeso.value);
    const data = campoData.value;
    const dataFormatada = data.split("-").reverse().join("/");

    if (peso <= 0 || !Number.isFinite(peso)){
        alert("Informe um peso válido!");
        return;

    }
    if (data === ""){
        alert("Informe a data da medição");
        return;
    
    }
    const hoje = new Date().toISOString().split("t")[0];

    if (data > hoje) {
         alert("A data da medição não pode ser futura");
         return;
    }
    const dataExiste = historicoPeso.some(
        registro => registro.data === dataFormatada
    );
    if (dataExiste) {
        alert("Já existe uma medição registrada nesta data");
        return;
    }

    historicoPeso.push({
        data:dataFormatada,
        peso: peso
    });


    localStorage.setItem(
        "historicoPeso",
        JSON.stringify(historicoPeso)
    );

    pesoAtual.textContent = `${peso} kg`;


    graficoPeso.data.labels = historicoPeso.map(
        registro => registro.data
    );
    graficoPeso.data.datasets[0].data = historicoPeso.map(
        registro => registro.peso
    );
    graficoPeso.update();

    atualizarTabela();

    formulario.reset();

    console.log(historicoPeso);
});
function atualizarTabela(){
    tabelaHistorico.innerHTML = "";

    historicoPeso.forEach(registro => {

        const linha = document.createElement("tr");
        linha.className = "border-b border-slate-100 hover:bg-clinical-500/10";

        linha.innerHTML = `
        <td class= "p-3">${registro.data}</td>
        <td class= "p-3">${registro.peso.toFixed(2)} kg</td>
        <td class= "p-3">-</td>`;

        tabelaHistorico.appendChild(linha);
    })
}
atualizarTabela();



if (botaoMenu && sidebar) {
    botaoMenu.addEventListener("click", () => {
        sidebar.classList.toggle("hidden");

        const aberto = ! sidebar.classList.contains("hidden");

        botaoMenu.setAttribute("aria-expanded", aberto);

        botaoMenu.setAttribute(
        "aria-label",
        aberto? "Fechar menu" : "Abrir menu"
        );
        botaoMenu.textContent = aberto ? "☰" : "☰";
    });
}