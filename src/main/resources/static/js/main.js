//Scale Web
const elementChange = document.querySelector('body');

function setScale() {
    if (window.innerWidth < 1280) {
        let zoom = window.innerWidth / 1280;
        elementChange.style.zoom = zoom;
    }
        console.log(window.innerWidth, elementChange.clientWidth)
}
setScale();
document.addEventListener("DOMContentLoaded", setScale);
window.addEventListener("resize", setScale);
window.addEventListener("load", setScale);

