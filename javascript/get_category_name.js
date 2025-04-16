fetch(`http://localhost:8080/categoria`).then((res) => {
    return res.json();
}).then((data) => {
    console.log(data.find((v) => v["categoriaID"] == Deno.args[0])["nome"]);
}).catch(() => {
    console.error(null);
});