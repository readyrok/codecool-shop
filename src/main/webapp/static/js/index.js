const URL_API = "http://localhost:8888"
const updateCart = async (productId) => {
    console.log(productId);
    const response = await fetch(URL_API + "/add-cart/?product=" + productId);
    console.log(response);
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