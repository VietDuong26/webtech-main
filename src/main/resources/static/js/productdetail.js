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

const listSize = document.querySelectorAll(".select-size button");
const listColor = document.querySelectorAll(".select-color button");

for (let sizeItem of listSize) {
    sizeItem.addEventListener('click', () => {
        if (sizeItem.classList.contains('select-button')) {
            sizeItem.classList.remove('select-button');
            removeValueSelect(sizeItem);
        } else {
            removeSelect(listSize, 'select-button');
            sizeItem.classList.add('select-button');
            getValueSelect(sizeItem);
        }
    })
}

for (let colorItem of listColor) {
    colorItem.addEventListener('click', () => {
        if (colorItem.classList.contains('select-button')) {
            colorItem.classList.remove('select-button');
            removeValueSelect(colorItem);
        } else {
            removeSelect(listColor, 'select-button');
            colorItem.classList.add('select-button');
            getValueSelect(colorItem);
        }
    })
}

function removeSelect(list, nameClass) {
    for (let i = 0; i < list.length; i++) {
        list[i].classList.remove(nameClass)
    }
}

function getValueSelect(element) {
    element.parentElement.setAttribute("value", element.getAttribute("value"));
}


function removeValueSelect(element) {
    element.parentElement.setAttribute("value", "");
}

