const URL_API = "http://localhost:8888"
let cartCount = document.getElementById("cartTotal");
let productCounters = document.getElementsByClassName("product-amount");
// let productCols = document.getElementsByClassName("col");

const addToCart = async (productId, productPrice) => {
    const response = await fetch(URL_API + "/add-cart/?product=" + productId);

    let cPrice = parseFloat(cartCount.innerText.substring(0, cartCount.innerText.length - 4));
    let pPrice = parseFloat(productPrice.substring(0, productPrice.length - 4));
    let newCartTotal = cPrice + pPrice + ' USD';

    for(let i = 0; i<productCounters.length; i++){
        if(productId == productCounters[i].dataset.productId){
            let count = parseInt(productCounters[i].innerText) + 1;
            productCounters[i].innerText = count + '';
        }
    }

    cartCount.innerText = `${newCartTotal}`;
}

const removeProduct = async (productId, productPrice) => {
    const response = await fetch(URL_API + "/remove/?product=" + productId);

    let cPrice = parseFloat(cartCount.innerText.substring(0, cartCount.innerText.length - 4));
    let pPrice = parseFloat(productPrice.substring(0, productPrice.length - 4));
    let newCartTotal = cPrice - pPrice + ' USD';

    for(let i = 0; i<productCounters.length; i++){
        if(productId == productCounters[i].dataset.productId){
            let count = parseInt(productCounters[i].innerText) - 1;
            productCounters[i].innerText = count + '';
        }
    }

    cartCount.innerText = `${newCartTotal}`;
}

const removeAllProduct = async (productId, productPrice) => {
    const response = await fetch(URL_API + "/remove/all/?product=" + productId);

    let cPrice = parseFloat(cartCount.innerText.substring(0, cartCount.innerText.length - 4));
    let pPrice = parseFloat(productPrice.substring(0, productPrice.length - 4));
    let newCartTotal = 0;

    for(let i = 0; i<productCounters.length; i++){
        if(productId == productCounters[i].dataset.productId){
            let count = parseInt(productCounters[i].innerText);
            newCartTotal = cPrice - pPrice * count + ' USD';
        }
    }

    cartCount.innerText = `${newCartTotal}`;
}


const init = () => {
    const addToCartButtons = document.getElementsByClassName("add-to-cart-btn");
    const removeProductButtons = document.getElementsByClassName("remove-product");
    const removeAllProductButtons = document.getElementsByClassName("remove-all");

    for(let addBtn of addToCartButtons) {
        addBtn.addEventListener("click", () => {
            const productId = addBtn.dataset.productId;
            const productPrice = addBtn.dataset.productPrice;

            addToCart(productId, productPrice);
            console.log("1")
        });
    }

    for(let removeBtn of removeProductButtons){
        removeBtn.addEventListener("click", () => {
            const productId = removeBtn.dataset.productId;
            const productPrice = removeBtn.dataset.productPrice;

            removeProduct(productId, productPrice);
            console.log("2")
        })
    }

    for(let removeAllBtn of removeAllProductButtons){
        removeAllBtn.addEventListener("click", () => {
            const productId = removeAllBtn.dataset.productId;
            const productPrice = removeAllBtn.dataset.productPrice;

            removeAllProduct(productId, productPrice);
            console.log("3")
        })
    }
}

init();