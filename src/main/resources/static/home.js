const API_URL = "/api/properties";

// Variables globales para paginación
let currentPage = 0;
const pageSize = 5;

// Obtener referencia a los elementos del DOM
const propertyForm = document.getElementById("propertyForm");
const propertyTable = document.getElementById("propertyTable").getElementsByTagName("tbody")[0];
const searchQueryInput = document.getElementById("searchQuery");
const maxPriceInput = document.getElementById("maxPrice");
const maxSizeInput = document.getElementById("maxSize");

// Función para mostrar mensajes de éxito o error
function showMessage(message, isError = false) {
    const messageDiv = document.getElementById("message");

    // Establecer el mensaje
    messageDiv.textContent = message;

    messageDiv.style.color = isError ? "red" : "green";
    messageDiv.style.display = "block";
    messageDiv.style.fontSize = "18px";
    messageDiv.style.fontWeight = "bold";
    messageDiv.style.padding = "10px 20px";
    messageDiv.style.borderRadius = "5px";
    messageDiv.style.backgroundColor = isError ? "#FFEBEE" : "#ffffff";
    messageDiv.style.position = "fixed";
    messageDiv.style.top = "20px"; // Distancia desde la parte superior
    messageDiv.style.left = "50%";
    messageDiv.style.transform = "translateX(-50%)"; // Ajuste para centrar
    messageDiv.style.zIndex = "1000"; // Asegurar que esté por encima de otros elementos
    messageDiv.style.boxShadow = "0 4px 6px rgba(0, 0, 0, 0.1)";

    setTimeout(() => {
        messageDiv.style.display = "none";
    }, 3000);
}

// Función para cargar propiedades con paginación
async function loadProperties(page = currentPage) {
    try {
        const response = await fetch(`${API_URL}?page=${page}&size=${pageSize}`);
        if (!response.ok) throw new Error("Error al cargar propiedades");
        const properties = await response.json();
        propertyTable.innerHTML = ""; // Limpiar tabla
        properties.content.forEach(property => {
            const row = propertyTable.insertRow();
            row.innerHTML = `
                <td>${property.id}</td>
                <td>${property.address}</td>
                <td>${property.price}</td>
                <td>${property.size}</td>
                <td>${property.description}</td>
                <td class="actions">
                    <button class="editar" onclick="editProperty(${property.id})">Editar</button>
                    <button class="eliminar" onclick="deleteProperty(${property.id})">Eliminar</button>
                </td>
            `;
        });

        // Agregar controles de paginación
        const paginationDiv = document.getElementById("pagination");
        paginationDiv.innerHTML = `
            <button onclick="previousPage()">Anterior</button>
            <span>Página ${page + 1}</span>
            <button onclick="nextPage()">Siguiente</button>
        `;
    } catch (error) {
        showMessage(error.message, true);
    }
}

// Función para avanzar a la siguiente página

function nextPage() {
    currentPage++;
    loadProperties(currentPage);
}

// Función para retroceder a la página anterior
function previousPage() {
    if (currentPage > 0) {
        currentPage--;
        loadProperties(currentPage);
    }
}

// Función para buscar propiedades
async function searchProperties() {
    // Reiniciar la página a la primera (0) antes de buscar
    currentPage = 0;

    const query = document.getElementById("searchQuery").value.trim();
    const maxPrice = document.getElementById("maxPrice").value.trim();
    const maxSize = document.getElementById("maxSize").value.trim();

    // Construir la URL de búsqueda de manera segura
    let url = `${API_URL}/search?page=${currentPage}&size=${pageSize}`;
    if (query) url += `&query=${encodeURIComponent(query)}`;
    if (maxPrice) url += `&maxPrice=${parseFloat(maxPrice)}`;
    if (maxSize) url += `&maxSize=${parseFloat(maxSize)}`;

    try {
        const response = await fetch(url);
        if (!response.ok) throw new Error("Error al buscar propiedades");
        const properties = await response.json();

        if (properties.content.length === 0) {
            showMessage("No se encontraron propiedades.", true);
        } else {
            showMessage(`Se encontraron ${properties.totalElements} propiedades.`);
        }

        propertyTable.innerHTML = ""; // Limpiar tabla
        properties.content.forEach(property => {
            const row = propertyTable.insertRow();
            row.innerHTML = `
                <td>${property.id}</td>
                <td>${property.address}</td>
                <td>${property.price}</td>
                <td>${property.size}</td>
                <td>${property.description}</td>
                <td class="actions">
                    <button class="editar" onclick="editProperty(${property.id})">Editar</button>
                    <button class="eliminar" onclick="deleteProperty(${property.id})">Eliminar</button>
                </td>
            `;
        });

        // Actualizar controles de paginación
        updatePagination(properties.totalPages);
    } catch (error) {
        showMessage(error.message, true);
    }
}

function clearFilters() {
    // Limpiar los campos de búsqueda
    document.getElementById("searchQuery").value = "";
    document.getElementById("maxPrice").value = "";
    document.getElementById("maxSize").value = "";

    // Cargar todas las propiedades sin filtros
    currentPage = 0; // Reiniciar la página a la primera
    loadProperties();
}

function updatePagination(totalPages) {
    const paginationDiv = document.getElementById("pagination");
    if (totalPages === 0) {
        paginationDiv.innerHTML = `<span>No hay propiedades para mostrar.</span>`;
    } else {
        paginationDiv.innerHTML = `
            <button onclick="previousPage()" ${currentPage === 0 ? "disabled" : ""}>Anterior</button>
            <span>Página ${currentPage + 1} de ${totalPages}</span>
            <button onclick="nextPage()" ${currentPage === totalPages - 1 ? "disabled" : ""}>Siguiente</button>
        `;
    }
}

// Función para agregar o actualizar una propiedad
propertyForm.addEventListener("submit", async (e) => {
    e.preventDefault();
    try {
        const id = document.getElementById("propertyId").value;
        const property = {
            address: document.getElementById("address").value,
            price: parseFloat(document.getElementById("price").value),
            size: parseFloat(document.getElementById("size").value),
            description: document.getElementById("description").value
        };

        const url = id ? `${API_URL}/${id}` : API_URL;
        const method = id ? "PUT" : "POST";

        const response = await fetch(url, {
            method: method,
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(property)
        });

        if (!response.ok) throw new Error(id ? "Error al actualizar la propiedad" : "Error al crear la propiedad");

        showMessage(id ? "Propiedad actualizada correctamente" : "Propiedad creada correctamente");
        propertyForm.reset();
        document.getElementById("propertyId").value = "";
        loadProperties(currentPage);
    } catch (error) {
        showMessage(error.message, true);
    }
});

// Función para editar una propiedad
async function editProperty(id) {
    try {
        const response = await fetch(`${API_URL}/${id}`);
        if (!response.ok) throw new Error("Error al cargar la propiedad");
        const property = await response.json();
        document.getElementById("propertyId").value = property.id;
        document.getElementById("address").value = property.address;
        document.getElementById("price").value = property.price;
        document.getElementById("size").value = property.size;
        document.getElementById("description").value = property.description;
    } catch (error) {
        showMessage(error.message, true);
    }
}

// Función para eliminar una propiedad
async function deleteProperty(id) {
    try {
        const response = await fetch(`${API_URL}/${id}`, { method: "DELETE" });
        if (!response.ok) throw new Error("Error al eliminar la propiedad");
        showMessage("Propiedad eliminada correctamente");
        loadProperties(currentPage);
    } catch (error) {
        showMessage(error.message, true);
    }
}

// Cargar propiedades al iniciar
loadProperties();