document.getElementById("login-form").addEventListener("submit", async function(event) {
    event.preventDefault(); // Evitar que el formulario se envíe de forma tradicional

    // Obtener los valores del formulario
    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;

    try {
        // Enviar la solicitud de login al backend
        const response = await fetch("/auth/login", {
            method: "POST",
            headers: {
                "Content-Type": "application/x-www-form-urlencoded",
            },
            body: `username=${encodeURIComponent(username)}&password=${encodeURIComponent(password)}`
        });

        // Verificar si la respuesta es exitosa
        if (response.ok) {
            const data = await response.json();
            alert("Login exitoso: " + data.message); // Mostrar mensaje de éxito
            window.location.href = "/home.html"; // Redirigir al usuario
        } else {
            // Mostrar un mensaje de error si las credenciales son incorrectas
            const error = await response.text();
            alert("Error durante el login: " + error);
        }
    } catch (error) {
        // Manejar errores de red o del servidor
        console.error("Error durante el login:", error);
        alert("Error durante el login");
    }
});