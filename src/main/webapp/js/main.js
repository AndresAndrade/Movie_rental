var username = new URL(location.href).searchParams.get("username");
var user;

$(document).ready(function () {
    $(function () {
        $('[data-bs-toggle = "tooltip"]').tooltip();
    });

    getUsuario().then(function () {
        //$("#mi-perfil-btn").attr("href", "profile.html?username=" + username);
        $("#user-saldo").html(user.saldo.toFixed(2) + "$");
        getPeliculas(false, "ASC");
        //$("#ordernar-genero").click(ordenarPelicula);
    });
});

//proceso asincronico, obligatorio usar los prefijos async y await
async function getUsuario() {
    await $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletUsuarioPedir",
        data: $.param({
            username: username
        }),
        success: function (result) {
            let parsedResult = JSON.parse(result);
            if (parsedResult !== false) {
                user = parsedResult;
            } else {
                console.log("Error, recuperando los datos del usuario.");
            }
        }
    });
}

function getPeliculas(ordenar, orden) {

    $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletPeliculaListar",
        data: $.param({
            ordenar: ordenar,
            orden: orden
        }),
        success: function (result) {
            let parsedResult = JSON.parse(result);

            if (parsedResult !== false) {
                mostrarPeliculas(parsedResult);
            } else {
                console.log("Error recuperando los datos de las peliculas");
            }
        }
    });
}