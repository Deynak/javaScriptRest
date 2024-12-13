const baseURL = 'http://localhost:8080/api/admin/products';
const productTable = document.getElementById('productTable');
const historyTable = document.getElementById('historyTable');
let userBalanceElement = document.getElementById('userBalance');
let selectedProduct = null;


function fetchProducts() {
    fetch('/api/user/products')
        .then(response => response.json())
        .then(data => {
            productTable.innerHTML = '';
            data.forEach(product => {
                const row = document.createElement('tr');
                row.innerHTML = `
                    <td>${product.id}</td>
                    <td>${product.name}</td>
                    <td>${product.description}</td>
                    <td>${product.price}</td>
                    <td>${product.quantity}</td>
                    <td>
                        <button class="btn btn-primary btn-sm" onclick="purchaseProduct(${product.id}, 1)">Buy</button>
                    </td>
                `;
                productTable.appendChild(row);
            });
        })
        .catch(error => console.error("Error fetching products:", error));
}


function showPurchaseModal(productId) {
    fetch(`${baseURL}/${productId}`)
        .then(response => response.json())
        .then(product => {
            selectedProduct = product;
            document.getElementById('purchaseProductInfo').innerText = `You are purchasing "${product.name}" (Price: $${product.price}, Available: ${product.quantity})`;
            document.getElementById('purchaseQuantity').value = 1;
            $('#purchaseModal').modal('show');
        })
        .catch(error => console.error("Error fetching product details:", error));
}

document.getElementById('purchaseForm').onsubmit = function (e) {
    e.preventDefault();

    const purchaseQuantity = parseInt(document.getElementById('purchaseQuantity').value);

    if (purchaseQuantity > selectedProduct.quantity) {
        alert("The requested quantity exceeds the available stock!");
        return;
    }

    const updatedProduct = {
        ...selectedProduct,
        quantity: selectedProduct.quantity - purchaseQuantity,
    };

    fetch(baseURL, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(updatedProduct),
    })
        .then(() => {
            alert(`You purchased ${purchaseQuantity} unit(s) of "${selectedProduct.name}".`);
            addToPurchaseHistory(selectedProduct, purchaseQuantity);
            $('#purchaseModal').modal('hide');
            fetchProducts();
        })
        .catch(error => console.error("Error processing purchase:", error));
};

function fetchUserBalance() {
    fetch('/api/user/balance') // Используйте правильный URL
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! Status: ${response.status}`);
            }
            return response.json();
        })
        .then(data => {
            userBalanceElement.innerText = `Balance: $${data.balance.toFixed(2)}`;
        })
        .catch(error => console.error("Error fetching user balance:", error));
}

function purchaseProduct(productId, quantity) {
    fetch(`/api/user/purchase?productId=${productId}&quantity=${quantity}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Failed to purchase product');
            }
            return response.text();
        })
        .then(data => {
            alert('Purchase successful!');
            fetchProducts(); // Обновление товаров
            fetchUserBalance(); // Обновление баланса
        })
        .catch(error => {
            console.error('Error processing purchase:', error);
            alert(`Purchase failed: ${error.message}`);
        });
}

function addToPurchaseHistory(product, quantity) {
    const purchase = {
        product: { id: product.id },
        quantity: quantity,
    };

    fetch('/api/user/purchase-history', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(purchase),
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Failed to save purchase history');
            }
            console.log("Purchase history saved");
        })
        .catch(error => console.error("Error saving purchase history:", error));
}



document.getElementById('historyBtn').onclick = function () {
    fetch('/api/user/purchase-history')
        .then(response => response.json())
        .then(data => {
            historyTable.innerHTML = '';
            data.forEach(history => {
                const row = document.createElement('tr');
                row.innerHTML = `
                    <td>${history.product.id}</td>
                    <td>${history.product.name}</td>
                    <td>${history.quantity}</td>
                    <td>${new Date(history.purchaseDate).toLocaleString()}</td>
                `;
                historyTable.appendChild(row);
            });
            $('#historyModal').modal('show');
        })
        .catch(error => console.error("Error fetching purchase history:", error));
};



document.getElementById('profileBtn').onclick = function () {
    window.location.href = '/user'; // Измените URL на соответствующий
};


document.getElementById('logoutBtn').onclick = function () {
    fetch('/logout', { method: 'POST' }) // Выполнение запроса на выход
        .then(() => {
            alert("Logged out successfully!");
            window.location.href = '/login'; // Перенаправление на страницу входа
        })
        .catch(error => console.error("Error logging out:", error));
};

fetchUserBalance();

fetchProducts();
