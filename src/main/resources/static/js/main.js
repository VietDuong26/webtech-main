//Scale Web
const elementChange = document.querySelector('body');

function setScale() {
    if (window.innerWidth < 1280) {
        let zoom = window.innerWidth / 1280;
        elementChange.style.zoom = zoom;
    }
}
setScale();
document.addEventListener("DOMContentLoaded", setScale);
window.addEventListener("resize", setScale);
window.addEventListener("load", setScale);

const swiper = new Swiper('.swiper-container', {
    direction: 'horizontal',
    loop: true,
    autoplay: {
        delay: 3000,
        speed: 1000,
    },
})