#!/bin/deno

fetch("http://localhost:8080/estoque", {
    method: "POST",
    headers: {
        "Content-Type": "application/json"
    },
    body: JSON.stringify({
        produtoID: Deno.args[0],
        quantidade: Deno.args[1]
    })
})
.then(response => {
    if (!response.ok) {
        return response.text().then(text => { throw new Error(text) });
    }
    return response.json();
})
.then(data => {
    console.log(data);
})
.catch(error => {
    console.error(error);
});

