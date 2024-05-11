// Function to handle redirection based on cURL response
async function redirectWithCurl(longUrl) {
    
    const response = await fetch('https://shivamsinghss.github.io/shorten', {
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

