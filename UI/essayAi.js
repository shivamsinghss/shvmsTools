document.getElementById('essayForm').addEventListener('submit', function(e) {
    e.preventDefault();

    const topic = document.getElementById('topic').value;
    const type = document.getElementById('type').value;
    const wordCount = document.getElementById('wordCount').value;

    const data = {
        topic: topic,
        type: type,
        word_count: wordCount
    };

    // Show loader
    const loadingElement = document.querySelector('.banter-loader');
    loadingElement.style.display = 'block';

    fetch('https://shvmstools.onrender.com/gemini-api', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
        .then(response => response.blob())
        .then(blob => {
            const url = window.URL.createObjectURL(blob);
            const a = document.createElement('a');
            a.style.display = 'none';
            a.href = url;
            a.download = 'essay.docx';
            document.body.appendChild(a);
            a.click();
            window.URL.revokeObjectURL(url);

            // Hide loader
            loadingElement.style.display = 'none';

            alert('Essay generated and downloaded successfully!');
        })
        .catch((error) => {
            // Hide loader
            loadingElement.style.display = 'none';

            console.error('Error:', error);
            alert('Error generating essay.');
        });
});
