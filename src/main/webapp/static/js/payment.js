const initPayment = {
    AddPaymentListener: function () {
        const paypalRadio = document.querySelector("#paypal");
        const creditRadio = document.querySelector("#credit");
        const paypal = document.querySelector(".paypal")
        const credit = document.querySelector(".credit-card")
        paypalRadio.addEventListener("change", function ( event){
            if (paypalRadio.checked === true){
                paypal.style.display="block";
                credit.style.display="none";
            }
        })
        creditRadio.addEventListener("change", function ( event){
            if (creditRadio.checked === true){
                paypal.style.display="none";
                credit.style.display="block";
            }
        })

    }
}
window.onload = function () {
    initPayment.AddPaymentListener();
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