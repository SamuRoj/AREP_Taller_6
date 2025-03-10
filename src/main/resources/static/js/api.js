api = (
    function() {
        const getAllProperties = () => {
            return fetch("/properties", {
                method: "GET",
                headers: {
                    "Content-Type": "application/json"
                }
            })
        }
    
        const getPropertyById = (id) => {
            return fetch("/properties/" + id, {
                method: "GET",
                headers: {
                    "Content-Type": "application/json"
                }
            })
        }
    
        const filterByAddress = (address) => {
            return fetch("/properties/address?address=" + address, {
                method: "GET",
                headers: {
                    "Content-Type": "application/json" 
                }
            })
        }
    
        const filterByPrice = (price) => {
            return fetch("/properties/price?price=" + price, {
                method: "GET",
                headers: {
                    "Content-Type": "application/json"
                }
            })
        }
    
        const filterBySize = (size) => {
            return fetch("/properties/size?size=" + size, {
                method: "GET",
                headers: {
                    "Content-Type": "application/json"
                }
            })
        }
    
        const createProperty = (data) =>  {
            return fetch("/properties", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(data)
            })
        }
    
        const updateProperty = (id, data) => {
            return fetch("/properties/" + id, {
                method: "PUT",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(data)
            })
        }
    
        const deleteProperty = (id) => {
            return fetch("/properties/" + id, {
                method: "DELETE",
                headers: {
                    "Content-Type": "application/json"
                }
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
            deleteProperty
        }
    }
)();

