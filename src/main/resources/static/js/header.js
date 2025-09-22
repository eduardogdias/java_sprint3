document.addEventListener("DOMContentLoaded", function () {
    const btnOpcoes = document.getElementById("btnOpcoes");
    const sidebar = document.querySelector(".sidebar");

    if (btnOpcoes && sidebar) {
        btnOpcoes.addEventListener("click", function () {
            sidebar.classList.toggle("hidden");
        });
    }
});
