const URL_API = "http://localhost:8888"
const updateCart = async (productId) => {
    const response = await fetch(URL_API + "/add-cart/?product=" + productId);
}


const init = () => {
    console.log("test");
    const addToCartButtons = document.getElementsByClassName("add-to-cart-btn");

    for(let btn of addToCartButtons) {
        console.log(btn);
        btn.addEventListener("click", () => {
            const productId = btn.dataset.productId;
            updateCart(productId);
        });
    }
}

init();