#!/bin/deno

fetch("http://localhost:8080/produto", {
    method: "POST",
    headers: {
        "Content-Type": "application/json"
    },
    body: JSON.stringify({
        categoriaID: 3,
        nomeProduto: "Smartwatch Premium",
        descricao: "Smartwatch com GPS e monitor cardíaco",
        preco: 799.90,
        dimensaoDaEmbalagem: "12x12x5 cm",
        certificacao: "CE",
        codigoDoProduto: "SWP-2023",
        paisDeOrigem: "China"
    })
})
.then(response => {
    if (!response.ok) {
        return response.text().then(text => { throw new Error(text) });
    }
    return response.json();
})
.then(data => {
    console.log("Product created successfully:", data);
    // You can add redirect or UI update here
})
.catch(error => {
    console.error("Error creating product:", error);
    // Handle errors in UI
});

