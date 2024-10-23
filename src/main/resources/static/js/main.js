//Scale Web
const elementChange = document.querySelector('body');
const offerLayout = document.querySelector('.offers-layout')

function setScale() {
    if (window.innerWidth < 1280) {
        let zoom = window.innerWidth / 1280;
        elementChange.style.zoom = zoom;
    }
    if (window.innerWidth < offerLayout.clientWidth) {
        let zoom = window.innerWidth / 1360;
        offerLayout.style.zoom = zoom;
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