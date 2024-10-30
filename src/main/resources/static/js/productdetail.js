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

// for (let sizeItem of listSize) {
//     sizeItem.addEventListener('click', () => {
//         if (sizeItem.classList.contains('select-button')) {
//             sizeItem.classList.remove('select-button');
//             removeValueSelect(sizeItem);
//         } else {
//             removeSelect(listSize, 'select-button');
//             sizeItem.classList.add('select-button');
//             getValueSelect(sizeItem);
//         }
//     })
// }

// for (let colorItem of listColor) {
//     colorItem.addEventListener('click', () => {
//         if (colorItem.classList.contains('select-button')) {
//             colorItem.classList.remove('select-button');
//             removeValueSelect(colorItem);
//         } else {
//             removeSelect(listColor, 'select-button');
//             colorItem.classList.add('select-button');
//             getValueSelect(colorItem);
//         }
//     })
// }

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
function getColor(product_id,size_id){
    let url = "http://localhost:8080/getColorByProductAndSize?product_id=" + product_id + "&size_id=" + size_id;
    $.ajax({
        type: "GET",
        dataType:'json',
        url: url,
        success: function (data) {
            console.log(data);
            loadColor(data);
        },
        error: function (error) {
            console.log(error)
        }
    });
}
function loadColor(data){
    let content=``;
    for (let i = 0; i < data.length; i++) {
        content+=`<button type="button" class="button-product relative" onclick="getQuantity(`+data[i].id+`)"><img src="/static/image/product_10.png"
                                alt="">`+data[i].color+`</button>\n`;
    }
    document.querySelector('#color-buttons').innerHTML=content;
}
let stock;
function getQuantity(stock_id){
    let url = "http://localhost:8080/getColorByProductAndSizeAndColor?stock_id=" + stock_id;
    $.ajax({
        type: "GET",
        dataType:'json',
        url: url,
        success: function (data) {
            console.log(data);
            stock=data;
            loadQuantity(data);
        },
        error: function (error) {
            console.log(error)
        }
    });
}
function loadQuantity(data){
    document.querySelector("#product_quantity").innerHTML=data.quantity +' sản phẩm có sẵn';
}
function add(){
    if(parseInt(document.getElementById('quantity-product').innerHTML)<parseInt(document.getElementById('product_quantity').innerHTML)){
        document.getElementById('quantity-product').innerHTML=parseInt(document.getElementById('quantity-product').innerHTML)+1;
    }
}
function minus(){
    if(parseInt(document.getElementById('quantity-product').innerHTML)>1){
        document.getElementById('quantity-product').innerHTML=parseInt(document.getElementById('quantity-product').innerHTML)-1;
    }
}
function getInfor(){
    let url = "http://localhost:8080/getInfor?stock_id="+stock.id;
    $.ajax({
        type: "GET",
        dataType:'json',
        url: url,
        success: function (data) {
            console.log(data);
            addToCart(data.product_id,data.size_id,data.color_id);
        },
        error: function (error) {
            console.log(error)
        }
    });
}
function addToCart(product_id,size_id,color_id){
    window.location='/addToCart?product_id='+product_id+"&quantity="+parseInt(document.getElementById('quantity-product').innerHTML)+"&size_id="+size_id+"&color_id="+color_id;
}
