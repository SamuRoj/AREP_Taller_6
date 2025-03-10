const form = document.getElementById("property-form");
const tableBody = document.getElementById("property-table");
let isBeingEdited = false;
let idToEdit = null;

function getAllProperties() {
    const promise = api.getAllProperties();
    promise.then(
        response => {
            return response.json()
        }
    )
    .then(data => {
        renderTable(data);
        console.log("Respuesta del servidor:", data);
    })
    .catch(error => console.error("Error:", error));
}

function createProperty(data){
    const promise = api.createProperty(data);
    promise.then(response => response.text())
    .then(data => console.log("Respuesta del servidor:", data))
    .then(() => getAllProperties())
    .catch(error => console.error("Error:", error));
}

function updateProperty(id, data){
    const promise = api.updateProperty(id, data);
    promise.then(() => {
        getAllProperties()
    })
    .then(data => console.log("Respuesta del servidor:", data))
    .then(() => getAllProperties())
}

form.addEventListener("submit", function(event) {
    event.preventDefault();
    const data = {
        address: document.getElementById("address").value,
        price: parseFloat(document.getElementById("price").value),
        size: parseFloat(document.getElementById("size").value),
        description: document.getElementById("description").value
    };
    console.log("data: " + data)
    if(!isBeingEdited){
        createProperty(data);
    }
    else{
        updateProperty(idToEdit, data)
        isBeingEdited = false
        idToEdit = null
    }
    form.reset();
});

function renderTable(properties) {
    tableBody.innerHTML = "";
    properties.map((property) => {
        const row = `<tr data-id=${property.id}>
            <td>${property.address}</td>
            <td>${property.price}</td>
            <td>${property.size}</td>
            <td>${property.description}</td>
            <td>
                <button class="edit-button" onclick="editProperty(${property.id})">Editar</button>
                <button class="delete-button" onclick="deleteProperty(${property.id})">Eliminar</button>
            </td>
        </tr>`;
        tableBody.innerHTML += row;
    });
}

function editProperty(id) {
    const promise = api.getPropertyById(id);
    promise.then(response => response.json())
        .then(data => {
            document.getElementById("address").value = data.address;
            document.getElementById("price").value = data.price;
            document.getElementById("size").value = data.size;
            document.getElementById("description").value = data.description;

            idToEdit = id;
            isBeingEdited = true;
        })
        .catch(error => console.error("Error al obtener la propiedad:", error));
}

function deleteProperty(id){
    const promise = api.deleteProperty(id);
    promise.then(
        () => {
            const row = document.querySelector(`tr[data-id="${id}"]`);
            row.remove();
            console.log("Propiedad eliminada")
        }
    )
}

function filterProperties() {
    const type = document.getElementById("filter-type").value;
    const value = document.getElementById("filter-value").value;
    let promise;

    if(type === "address"){
        promise = api.filterByAddress(value);
    }
    else if(type === "price"){
        promise = api.filterByPrice(value);
    }
    else{
        promise = api.filterBySize(value);
    }

    promise.then(
        response => {
            return response.json()
        }
    )
    .then(data => {
        renderTable(data);
    })
    .catch(error => console.error("Error:", error));
}
