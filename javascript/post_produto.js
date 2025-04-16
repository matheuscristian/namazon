#!/bin/deno

fetch("http://localhost:8080/produto", {
    method: "POST",
    headers: {
        "Content-Type": "application/json"
    },
    body: JSON.stringify({
        categoriaID: Deno.args[0],
        nomeProduto: Deno.args[1],
        descricao: Deno.args[2],
        preco: Deno.args[3],
        dimensaoDaEmbalagem: Deno.args[4],
        certificacao: Deno.args[5],
        codigoDoProduto: Deno.args[6],
        paisDeOrigem: Deno.args[7]
    })
})
.then(response => {
    if (!response.ok) {
        return response.text().then(text => { throw new Error(text) });
    }
    return response.json();
})
.then(data => {
    console.log(data["produtoID"]);
})
.catch(error => {
    console.error(error);
});

