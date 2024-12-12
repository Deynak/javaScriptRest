const baseURL = 'http://localhost:8080/api/admin/products';
const productTable = document.getElementById('productTable');

// Функция для загрузки и отображения продуктов
function fetchProducts() {
    fetch(baseURL)
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! Status: ${response.status}`);
            }
            return response.json();
        })
        .then(data => {
            console.log("Fetched products:", data);
            productTable.innerHTML = ''; // Очистка таблицы перед добавлением новых данных
            data.forEach(product => {
                const row = document.createElement('tr');
                row.innerHTML = `
                    <td>${product.id}</td>
                    <td>${product.name}</td>
                    <td>${product.description}</td>
                    <td>${product.price}</td>
                    <td>${product.quantity}</td>
                    <td>
                        <button class="btn btn-info btn-sm" onclick="showEditModal(${product.id})">Edit</button>
                        <button class="btn btn-danger btn-sm" onclick="deleteProduct(${product.id})">Delete</button>
                    </td>
                `;
                productTable.appendChild(row);
            });
        })
        .catch(error => {
            console.error("Error fetching products:", error);
        });
}

// Вызов функции для начальной загрузки продуктов
fetchProducts();
// Add a new product
document.getElementById('addProductForm').onsubmit = function (e) {
    e.preventDefault();
    const product = {
        name: document.getElementById('addProductName').value,
        description: document.getElementById('addProductDescription').value,
        price: parseFloat(document.getElementById('addProductPrice').value),
        quantity: parseInt(document.getElementById('addProductQuantity').value),
    };

    fetch(baseURL, {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(product),
    })
        .then(() => {
            $('#addProductModal').modal('hide');
            fetchProducts();
        });
};

// Show edit modal
function showEditModal(id) {
    fetch(`${baseURL}/${id}`)
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! Status: ${response.status}`);
            }
            return response.json();
        })
        .then(product => {
            // Заполнение данных в модальном окне
            document.getElementById('editProductId').value = product.id;
            document.getElementById('editProductName').value = product.name;
            document.getElementById('editProductDescription').value = product.description;
            document.getElementById('editProductPrice').value = product.price;
            document.getElementById('editProductQuantity').value = product.quantity;
            $('#editProductModal').modal('show');
        })
        .catch(error => {
            console.error("Error fetching product by ID:", error);
        });
}


// Edit a product
document.getElementById('editProductForm').onsubmit = function (e) {
    e.preventDefault();
    const product = {
        id: parseInt(document.getElementById('editProductId').value),
        name: document.getElementById('editProductName').value,
        description: document.getElementById('editProductDescription').value,
        price: parseFloat(document.getElementById('editProductPrice').value),
        quantity: parseInt(document.getElementById('editProductQuantity').value),
    };

    fetch(baseURL, {
        method: 'PUT',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(product),
    })
        .then(() => {
            $('#editProductModal').modal('hide');
            fetchProducts();
        });
};

// Delete a product
function deleteProduct(id) {
    fetch(`${baseURL}/${id}`, {method: 'DELETE'})
        .then(() => fetchProducts());
}

// Initial fetch
fetchProducts();