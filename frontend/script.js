function showTab(tabId) {
    const tabs = document.querySelectorAll('.tab-content');
    tabs.forEach(tab => {
        tab.style.display = 'none'; 
    });

    document.getElementById(tabId).style.display = 'block'; 
}

showTab('login');

document.getElementById('switch-to-login').addEventListener('click', function() {
    showTab('login');
});


document.getElementById('switch-to-signup').addEventListener('click', function() {
    showTab('signup');
});

document.getElementById('search-form').addEventListener('submit', function(e) {
    e.preventDefault();

    const destination = document.getElementById('destination').value;
    const departureDate = document.getElementById('departure-date').value;
    const returnDate = document.getElementById('return-date').value;

    const searchResults = [
        { type: 'Flight', price: '$300', rating: '4.5', details: 'Flight details here' },
        { type: 'Hotel', price: '$150', rating: '4.0', details: 'Hotel details here' }
    ];

    displaySearchResults(searchResults);
});

function displaySearchResults(results) {
    const resultList = document.getElementById('result-list');
    resultList.innerHTML = '';

    results.forEach((result, index) => {
        const listItem = document.createElement('li');
        listItem.style.position = 'relative';
        listItem.style.border = '1px solid #ccc';
        listItem.style.padding = '10px';
        listItem.style.marginBottom = '10px';
        listItem.style.borderRadius = '8px';
        listItem.style.backgroundColor = '#f9f9f9';

        listItem.innerHTML = `
            <strong>${result.type}</strong>
            <p>Price: ${result.price}</p>
            <p>Rating: ${result.rating}</p>
            <p>Details: ${result.details}</p>
            <p><strong>Departure:</strong> ${result.departure}</p>
        `;

        // Add delete icon only for hotels
        if (result.type === 'Hotel') {
            const deleteIcon = document.createElement('span');
            deleteIcon.innerHTML =  '🗑️'; 
            deleteIcon.title = 'Delete';
            deleteIcon.className = 'delete-icon'; 
            deleteIcon.style.position = 'absolute';
            deleteIcon.style.top = '10px';
            deleteIcon.style.right = '10px';
            deleteIcon.style.color = '#e74c3c';
            deleteIcon.style.cursor = 'pointer';
            deleteIcon.style.fontSize = '18px';

            deleteIcon.addEventListener('click', () => {
                results.splice(index, 1);
                displaySearchResults(results);
            });

            listItem.appendChild(deleteIcon);
        }

        resultList.appendChild(listItem);
    });

}

document.getElementById('payment-form').addEventListener('submit', function(e) {
    e.preventDefault();
    alert('Booking confirmed! You will receive a confirmation email.');
});

document.getElementById('manage-users').addEventListener('click', function() {
    alert('Managing Users...');
});
