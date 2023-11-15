criterios = ["Sin ordenar", "Ascendente por precio", "Descendente por precio"]

function creaListaCriterios() {
    let selectOrder = document.getElementById("criteriosOrdenacion");
    criterios.forEach(p => {
        let criteriosName = document.createElement("option");
        let text = document.createTextNode(p);
        criteriosName.appendChild(text);
        selectOrder.appendChild(criteriosName);
    });

    // Mueve el evento de escucha fuera del bucle forEach
    selectOrder.addEventListener("change", function () {
        let selectedValue = selectOrder.value;
        pintaArticulos(selectedValue);
    });
}


function pintaArticulos(orden) {
    console.log(orden);
    const container = document.getElementById('contenedor');

    if (orden === "Sin ordenar") {
        listaArticulos = [...listaArticulosOriginal];
    } else if (orden === "Ascendente por precio") {
        listaArticulos.sort((a, b) => a.precio - b.precio);
    } else if (orden === "Descendente por precio") {
        listaArticulos.sort((a, b) => b.precio - a.precio);
    }

    // Itera sobre cada artículo en listaArticulos
    container.innerHTML = '';
    listaArticulos.forEach(articulo => {
        const colDiv = document.createElement('div');
        colDiv.className = 'col';

        const productCard = document.createElement('div');
        productCard.className = 'card flex';

        const productImage = document.createElement('img');
        productImage.src = `assets/${articulo.codigo}.jpg`;
        productImage.className = 'card-img-top';
        productCard.appendChild(productImage);

        const cardBody = document.createElement('div');
        cardBody.className = 'card-body';

        const title = document.createElement('h5');
        title.className = 'card-title';
        title.textContent = articulo.nombre;
        cardBody.appendChild(title);

        const description = document.createElement('p');
        description.className = 'card-text';
        description.textContent = articulo.descripcion;
        cardBody.appendChild(description);

        const price = document.createElement('p');
        price.className = 'card-text text-center';
        price.textContent = `${articulo.precio}€`;
        cardBody.appendChild(price);

        productCard.appendChild(cardBody);

        const btn = document.createElement('button');
        btn.id = articulo.codigo;
        btn.className = 'btn btn-success';
        btn.textContent = 'comprar';
        btn.addEventListener('click', function () {
            ponArticuloEnCarrito(articulo);
        });
        productCard.appendChild(btn);

        colDiv.appendChild(productCard);
        container.appendChild(colDiv);
    });

}


function ponArticuloEnCarrito(articulo) {
    window.carrito.anyadeArticulo(articulo);
}


function verCarro() {
    if (carrito.articulos.length === 0) {
        alert("El carrito está vacío")
    } else {
        carrito.verCarrito()
    }
}

function efectuaPedido() {
    // Primero, validar el carrito para asegurarse de que no está vacío.
    if (window.carrito.articulos.length === 0) {
        alert("Tu carrito está vacío.");
        return; // No proceder si el carrito está vacío.
    }
    alert("¡Gracias por tu pedido!");

    // Luego, limpiar el carrito de compras.
    window.carrito.articulos = [];
    // Finalmente, cerrar el diálogo del carrito si está abierto y actualizar la UI.
    const dialogo = document.getElementById('miDialogo');
    dialogo.close();

}


window.addEventListener('load', () => {
    creaListaCriterios();
    pintaArticulos();
    const carrito = new Carrito();  // Crea una instancia de Carrito
    window.carrito = carrito;  // Opcionalmente, haz que la instancia sea global si necesitas acceder a ella desde otros lugares

    // Obtiene la imagen del carrito por su ID y vincula el evento click
    const imagenCarrito = document.getElementById('imagenCarrito');
    imagenCarrito.addEventListener('click', () => {
        verCarro();  // Ejecuta la función verCarrito cuando se haga clic en la imagen
    });

    const botonPedido = document.getElementById('botonEfectuarPedido');
    // Elimina cualquier manejador previo para asegurarte de que solo se añade uno.
    botonPedido.removeEventListener('click', efectuaPedido);
});

