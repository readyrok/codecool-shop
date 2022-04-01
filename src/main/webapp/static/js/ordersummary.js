const initOrder = {
    fetchData: function () {
        fetch('/api/cart')
            .then(response => response.json())
            .then(data => initOrder.ShoppingSummary(data));

    },
    ShoppingSummary: function (data) {
        let items = data.lineItems;
        let tbody = document.querySelector('.orderBody');
        tbody.innerHTML = "";
        let index = 1;
        for (item of items) {
            let outerHTML =
                `<tr>
                    <th>${index}</th>
                    <td>${item.productName}</td>
                    <td>${item.supplier}</td>
                    <td>${item.productPrice}</td>
                    <td>${item.quantity}</td>
                    <td class="text-start"><i class="fas fa-minus-circle me-2 productDecrease" data-productId="${item.productId}"></i><i class="fas fa-plus-circle productIncrease" data-productId="${item.productId}"></i></td>
                    <td class="text-end pe-3">${item.linePrice}</td>
                    <td><i class="fas fa-trash lineRemover" data-productId="${item.productId}"></i></td>
                </tr>`
            tbody.insertAdjacentHTML("beforeend", outerHTML);
            index++;
        }
        let outerHTML2 =
            `<tr>
                    <th></th>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td class="text-start">SubTotal:</td>
                    <td class="text-end pe-3">${data.subTotal + " " + data.currency}</td>
                    <td></td>
                </tr>`
        tbody.insertAdjacentHTML("beforeend", outerHTML2);

        initOrder.addDecreaseListener()
        initOrder.addIncreaseListener()
        initOrder.addRemoveListener();
    },
    addRemoveListener: function () {
        let removers = document.querySelectorAll(".lineRemover");
        for (let remover of removers) {
            remover.addEventListener("click", function (event) {
                let productId = event.target.getAttribute('data-productId');
                fetch("/deletelineitem", {
                    method: "POST",
                    credentials: "same-origin",
                    body: productId,
                    headers: {
                        'Content-Type': 'text/plain'
                    }
                }).then(initOrder.fetchData)
            });
        }

    },
    addDecreaseListener: function () {
        let decreaser = document.querySelectorAll(".productDecrease");
        for (let decrease of decreaser) {
            decrease.addEventListener("click", function (event) {
                let productId = event.target.getAttribute('data-productId');
                fetch("/decreaseproduct", {
                    method: "POST",
                    credentials: "same-origin",
                    body: productId,
                    headers: {
                        'Content-Type': 'text/plain'
                    }
                }).then(initOrder.fetchData)
            });
        }
    },
    addIncreaseListener: function () {
        let increaser = document.querySelectorAll(".productIncrease");
        for (let increase of increaser) {
            increase.addEventListener("click", function (event) {
                let productId = event.target.getAttribute('data-productId');
                fetch("/increaseproduct", {
                    method: "POST",
                    credentials: "same-origin",
                    body: productId,
                    headers: {
                        'Content-Type': 'text/plain'
                    }
                }).then(initOrder.fetchData)
            });
        }
    }
}
window.onload = function () {
    initOrder.fetchData();
}
function backToMainButton() {
    window.location = '/';
}
function registrationButton() {
    window.location = '/registration';
}
function loginButton() {
    window.location = '/login';
}
function editProfile(){
    window.location = '/profile';
}
function logoutButton() {
    window.location = '/logout';
}