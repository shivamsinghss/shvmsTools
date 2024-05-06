// Function to handle redirection based on cURL response
async function redirectWithCurl(longUrl) {
    
    const response = await fetch('https://shivamsinghss.github.io/shvmsTools/UI/shorten', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ originalUrl: longUrl })
    });
    const responseData = await response.text(); // Read the response as text
    if (responseData.trim() !== '') {
        const shortenedUrl = `https://shivamsinghss.github.io/shvmsTools/UI/redirect?shortUrl=${responseData.trim()}`;
        prompt('shortened URL:', shortenedUrl);
        window.location.href = shortenedUrl;
    } else {
        // If the response data is empty, show an error message
        alert('Error: Short URL not generated');
    }
}

// Event listener for form submission
document.getElementById('shortenForm').addEventListener('submit', async function(event) {
    event.preventDefault();
    const longUrl = document.getElementById('longUrl').value;

    // Call the function to handle redirection using cURL response
    redirectWithCurl(longUrl);
});

// document.getElementById('shortenForm').addEventListener('submit', async function() {
//     try {
//         const response = await fetch('http://localhost:8080/shorten', {
//             method: 'POST', // or 'POST' if you're sending data
//             headers: {
//                 'Content-Type': 'application/json',
//                 // Add any additional headers required by the API
//             },
//             // If you're sending data, include a body property with JSON.stringify(data)
//             // body: JSON.stringify({ key: 'value' })
//         });
//         const data = await response.json(); // Assuming the API returns JSON data
//         console.log('API Response:', data);
//         // Process the API response as needed
//     } catch (error) {
//         console.error('Error:', error);
//         // Handle errors, such as network issues or server errors
//         alert('Error: Failed to fetch data from API');
//     }
// });
