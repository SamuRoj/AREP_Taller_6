// let url = "http://localhost:8080"
let url = "https://srbackserver.duckdns.org:8080"

api = (
    function() {
        const getAllProperties = () => {
            return fetch(url + "/properties", {
                method: "GET",
                headers: {
                    "Content-Type": "application/json"
                }
            })
        }
    
        const getPropertyById = (id) => {
            return fetch(url + "/properties/" + id, {
                method: "GET",
                headers: {
                    "Content-Type": "application/json"
                }
            })
        }
    
        const filterByAddress = (address) => {
            return fetch(url + "/properties/address?address=" + address, {
                method: "GET",
                headers: {
                    "Content-Type": "application/json" 
                }
            })
        }
    
        const filterByPrice = (price) => {
            return fetch(url + "/properties/price?price=" + price, {
                method: "GET",
                headers: {
                    "Content-Type": "application/json"
                }
            })
        }
    
        const filterBySize = (size) => {
            return fetch(url + "/properties/size?size=" + size, {
                method: "GET",
                headers: {
                    "Content-Type": "application/json"
                }
            })
        }
    
        const createProperty = (data) =>  {
            return fetch(url + "/properties", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(data)
            })
        }
    
        const updateProperty = (id, data) => {
            return fetch(url + "/properties/" + id, {
                method: "PUT",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(data)
            })
        }
    
        const deleteProperty = (id) => {
            return fetch(url + "/properties/" + id, {
                method: "DELETE",
                headers: {
                    "Content-Type": "application/json"
                }
            })
        }

        const authenticateUser = (data) => {
            return fetch(url + "/users/auth", {
                method: "POST",
                headers: {  
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(data)
            })
        }

        const registerUser = (data) => {
            return fetch(url + "/users", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(data)
            })
        }

        return {
            getAllProperties,
            getPropertyById, 
            filterByAddress,
            filterByPrice,
            filterBySize,
            createProperty,
            updateProperty,
            deleteProperty,
            authenticateUser,
            registerUser
        }
    }
)();

