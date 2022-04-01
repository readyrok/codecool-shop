const initCart = {
    fetchData: function () {
        fetch('/api/cart')
            .then(response => response.json())
            .then(data => initCart.AddShoppingList(data));
    },
    AddShoppingList: function (data) {
        const cart = document.getElementById("shopping-list");
        const cartLength = document.getElementById("cart-length")
        let counter = 0
        cart.innerHTML = ""
        let totalPrice = 0;
        if (data !== null) {
            for (item of data.lineItems) {
                const card = document.createElement("div")
                const product = document.createElement("div")
                const quantity = document.createElement("div")
                const price = document.createElement("div")
                product.textContent = "Product name: " + item.productName
                quantity.textContent = "Quantity: x" + item.quantity
                price.textContent = "Price: " + item.price + " USD"
                // card.classList.add("card", "w-auto", "p-3")
                card.classList.add("dropdown-item", "w-auto", "p-3")
                card.append(product, quantity, price)
                cart.append(card)
                counter += item.quantity
                totalPrice += item.price
            }
            cartLength.textContent = counter
            const pr = document.createElement("div")
            pr.textContent = "Total price: " + totalPrice.toFixed(2) + " USD"
            cart.append(pr)
        }
    },
    postData: function () {
        const buttons = document.querySelectorAll('.buy-btn')
        buttons.forEach(function (currentBtn) {
            currentBtn.addEventListener('click', function () {
                fetch("/addtocart", {
                    method: "POST",
                    credentials: "same-origin",
                    body: this.dataset.id,
                    headers: {
                        'Content-Type': 'text/plain'
                    }
                }).then(initCart.fetchData)
            })
        })
    },

}
window.onload = function () {
    initCart.fetchData();
    initCart.postData();
}
function registrationButton() {
    window.location = '/registration';
}
function loginButton() {
    window.location = '/login';
}
function adminPanel(){
    window.location = '/admin';
}
function editProfile(){
    window.location = '/profile';
}
function logoutButton() {
    window.location = '/logout';
}