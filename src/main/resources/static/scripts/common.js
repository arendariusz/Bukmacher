const toggle = document.querySelector(".menu-toggle");
const menu = document.querySelector(".menu");
const userToggle = document.querySelector(".user-toggle");
const userMenu = document.querySelector(".user");

function toggleMenu() {
    if (menu.classList.contains("expanded")) {
        menu.classList.remove("expanded");
        toggle.querySelector('a').innerHTML = '<i id="toggle-icon" class="far fa-plus-square"></i>';
    } else {
        menu.classList.add("expanded");
        toggle.querySelector('a').innerHTML = '<i id="toggle-icon" class="far fa-minus-square"></i>';
    }
}

function userToggleMenu() {
    userMenu.classList.toggle("expanded");
}

toggle.addEventListener("click", toggleMenu, false);
userToggle.addEventListener("click", userToggleMenu, false);