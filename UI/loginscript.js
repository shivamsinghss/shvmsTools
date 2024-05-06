// script.js

document.getElementById('signInForm').addEventListener('Log In', function(event) {
    event.preventDefault();
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;
    
    if (username === 'admin' && password === 'admin') {
        window.location.href = 'shvmsTools.html'; // Redirect to welcome page
    } else {
        document.getElementById('errorMessage').textContent = 'Invalid username or password';
    }
});

