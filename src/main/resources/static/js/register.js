const form = document.getElementById("register-form");

function registerUser(data) {
    const promise = api.registerUser(data);
    promise.then(response => response.text())
    .then(() => {
        alert("Usuario registrado exitosamente.");
        window.location.href = "index.html"
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
    registerUser(data)
    form.reset();
});

