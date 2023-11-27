const paises = document.getElementById("paises");
const provincias = document.getElementById("provincias");
const ciudades = document.getElementById("ciudades");

// Llena el select de países
datos.paises.forEach((pais, index) => {
    const option = document.createElement("option");
    option.text = pais.nombre;
    option.value = index;
    paises.appendChild(option);
});

// Función para llenar el select de provincias
const llenarProvincias = () => {
    provincias.innerHTML = "";
    ciudades.innerHTML = "";
    const paisIndex = paises.value;

    datos.paises[paisIndex].provincias.forEach((provincia, index) => {
        const option = document.createElement("option");
        option.text = provincia.nombre;
        option.value = index;
        provincias.appendChild(option);
    });
};

// Función para llenar el select de ciudades
const llenarCiudades = () => {
    ciudades.innerHTML = "";
    const paisIndex = paises.value;
    const provinciaIndex = provincias.value;

    datos.paises[paisIndex].provincias[provinciaIndex].ciudades.forEach((ciudad, index) => {
        const option = document.createElement("option");
        option.text = ciudad;
        option.value = index;
        ciudades.appendChild(option);
    });
};

// Escuchar eventos de cambio en los select
paises.addEventListener("change", llenarProvincias);
provincias.addEventListener("change", llenarCiudades);
ciudades.addEventListener("change", () => {
    const paisIndex = paises.value;
    const provinciaIndex = provincias.value;
    const ciudadIndex = ciudades.value;
    alert(datos.paises[paisIndex].provincias[provinciaIndex].ciudades[ciudadIndex] + " es una ciudad en " + datos.paises[paisIndex].nombre);
});

// Llenar el select de provincias cuando se carga la página
llenarProvincias();

