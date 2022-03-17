const URL_API = "http://localhost:8888"

const updateCart = async (productId) => {
    const response = await fetch(URL_API + "/add-cart/?product=" + productId);
    let cartCount = document.getElementById("cartCount");
    let newCartCount = parseInt(cartCount.innerText) + 1;

    cartCount.innerText = `${newCartCount}`;
}


const init = () => {
    const addToCartButtons = document.getElementsByClassName("add-to-cart-btn");

    for(let btn of addToCartButtons) {
        btn.addEventListener("click", () => {
            const productId = btn.dataset.productId;
            updateCart(productId);
        });
    }
}

init();