$(document).ready(function () {
   $("#form-login").submit(function (event) {
       event.preventDefault();
       autenticarUsuario();
   });
});