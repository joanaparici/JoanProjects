class Carrito {
    constructor() {
        this.articulos = [];
    }

    anyadeArticulo(articulo) {
        const indice = this.articulos.findIndex(a => a.codigo === articulo.codigo);
        if (indice === -1) {
            // El artículo no existe, así que lo añadimos con unidades = 1
            articulo.unidades = 1;
            this.articulos.push(articulo);
        } else {
            // El artículo ya existe, incrementamos las unidades
            this.articulos[indice].unidades++;
        }
        this.actualizarVistaCarrito(); // Actualizamos la interfaz de usuario
    }

    borraArticulo(codigo) {
        this.articulos = this.articulos.filter(articulo => articulo.codigo !== codigo);
        this.actualizarVistaCarrito(); // Actualizamos la interfaz de usuario
    }

    modificaUnidades(codigo, unidadesAModificar) {
        const articulo = this.articulos.find(a => a.codigo === codigo);
        if (articulo) {
            articulo.unidades += unidadesAModificar;
            if (articulo.unidades <= 0) {
                // Si las unidades son 0 o negativas, se elimina el artículo
                this.borraArticulo(codigo);
            } else {
                this.actualizarVistaCarrito(); // Actualizamos la interfaz de usuario
            }
        }
    }

    actualizarVistaCarrito() {
        // Encuentra el elemento que muestra el precio total del carrito, si existe.
        const totalCarrito = document.getElementById('total');
        if (totalCarrito) {
            totalCarrito.textContent = this.articulos.reduce((sum, articulo) => sum + (articulo.precio * articulo.unidades), 0).toFixed(2) + '€';
        }

        // Encuentra el contenedor donde se mostrarán los artículos del carrito.
        const dialogContent = document.getElementById('dialogContent');
        if (dialogContent) {

            dialogContent.innerHTML = '';

            // Crea una tabla para los artículos del carrito si aún no existe. Aquí con innerHTML
            const tabla = document.createElement('table');
            tabla.innerHTML = `
                <tr>
                    <th>Imagen</th>
                    <th>Nombre</th>
                    <th>Descripción</th>
                    <th>Precio</th>
                    <th>Unidades</th>
                    <th>Total</th>
                    <th>Acciones</th>
                </tr>
            `;

            // Añade cada artículo al contenedor de la lista del carrito.
            this.articulos.forEach((articulo, index) => {
                const fila = tabla.insertRow();
                fila.innerHTML = `
                    <td><img src="assets/${articulo.codigo}.jpg" alt="${articulo.nombre}" style="width: 50px;"></td>
                    <td>${articulo.nombre}</td>
                    <td>${articulo.descripcion}</td>
                    <td>${articulo.precio}€</td>
                    <td>
                        ${articulo.unidades}
                    </td>
                    <td>${(articulo.precio * articulo.unidades).toFixed(2)}€</td>
                    <td>
                        <button id="añadir-${index}">+</button>
                        <button id="restar-${index}">-</button>
                        <button id="borrar-${index}">Borrar</button>
                    </td>
                `;
            });

            // Añade la tabla al contenedor del diálogo.
            dialogContent.appendChild(tabla);

            // Añade manejadores de eventos para los botones recién creados.
            this.articulos.forEach((articulo, index) => {
                document.getElementById(`añadir-${index}`).addEventListener('click', () => {
                    this.modificaUnidades(articulo.codigo, 1);
                    this.actualizarVistaCarrito();
                });

                document.getElementById(`restar-${index}`).addEventListener('click', () => {
                    this.modificaUnidades(articulo.codigo, -1);
                    this.actualizarVistaCarrito();
                });

                document.getElementById(`borrar-${index}`).addEventListener('click', () => {
                    this.borraArticulo(articulo.codigo);
                    this.actualizarVistaCarrito();
                });
            });
        }
    }


    verCarrito() {
        this.mostrarDialogoCarrito();
    }

    mostrarDialogoCarrito() {
        const dialogo = document.getElementById('miDialogo');
        const dialogContent = document.getElementById('dialogContent');
        const dialogFooter = document.getElementById('dialogFooter');
        let total = 0;

        dialogContent.innerHTML = ''; // Limpiamos el contenido existente
        const tabla = document.createElement('table');
        tabla.innerHTML = `
            <tr>
                <th>Imagen</th>
                <th>Nombre</th>
                <th>Descripción</th>
                <th>Precio</th>
                <th>Unidades</th>
                <th>Total</th>
                <th>Acciones</th>
            </tr>
        `;

        this.articulos.forEach((articulo, index) => {
            const fila = tabla.insertRow();
            fila.innerHTML = `
                <td><img src="assets/${articulo.codigo}.jpg" alt="${articulo.nombre}" style="width: 50px;"></td>
                <td>${articulo.nombre}</td>
                <td>${articulo.descripcion}</td>
                <td>${articulo.precio}€</td>
                <td>${articulo.unidades}</td>
                <td>${(articulo.precio * articulo.unidades).toFixed(2)}€</td>
                <td>
                    <button id="add-${index}">+</button>
                    <button id="subtract-${index}">-</button>
                    <button id="delete-${index}">Borrar</button>
                </td>
            `;
            total += articulo.precio * articulo.unidades;
        });

        dialogContent.appendChild(tabla);
        dialogFooter.querySelector('#total').textContent = `${total.toFixed(2)}€`;
        dialogo.showModal(); // Mostrar el diálogo como modal

        this.articulos.forEach((articulo, index) => {
            document.getElementById(`add-${index}`).addEventListener('click', () => {
                this.modificaUnidades(articulo.codigo, 1);
                this.actualizarVistaCarrito();
            });

            document.getElementById(`subtract-${index}`).addEventListener('click', () => {
                this.modificaUnidades(articulo.codigo, -1);
                this.actualizarVistaCarrito();
            });

            document.getElementById(`delete-${index}`).addEventListener('click', () => {
                this.borraArticulo(articulo.codigo);
                this.actualizarVistaCarrito();
            });
        });
    }
}

window.addEventListener('load', () => {
    // ... el resto de la inicialización ...

    // Añade los manejadores de eventos para cerrar el diálogo y efectuar el pedido
    const dialogo = document.getElementById('miDialogo');
    document.getElementById('btnCierraDialog').addEventListener('click', () => {
        dialogo.close();
    });

    // Asegúrate de que este ID 'btnEfectuaPedido' exista en tu botón de "Efectuar pedido"
    document.getElementById('btnEfectuaPedido').addEventListener('click', efectuaPedido);
});