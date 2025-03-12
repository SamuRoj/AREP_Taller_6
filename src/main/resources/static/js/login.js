const form = document.getElementById("login-form");

function authenticateUser(data) {
    const promise = api.authenticateUser(data);
    promise.then(response => response.text())
    .then(() => {
        alert("Inicio de sesión correcto.");
        window.location.href = "properties.html"
    })
    .catch(error => console.error("Error:", error));
}

form.addEventListener("submit", function(event) {
    event.preventDefault();
    const data = {
        email: document.getElementById("email").value,
        password: document.getElementById("password").value
    };
    console.log("data: " + data)
    authenticateUser(data);
    form.reset();
});
