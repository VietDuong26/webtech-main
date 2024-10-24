const displayProducts = document.querySelectorAll(".display-product")
displayProducts[0].classList.add('open-product')
const imgDisplayProduct = document.querySelector(".productdisplay-img > img")
console.log(displayProducts)
for (let displayProduct of displayProducts) {
    displayProduct.addEventListener('click', () => {
        imgDisplayProduct.src = displayProduct.children[0].children[0].src
        for (let i = 0; i < displayProducts.length; i++) {
            displayProducts[i].classList.remove('open-product')
        }
        displayProduct.classList.add('open-product')
    })
}