document.getElementById('upload-form').addEventListener('submit', async function(event) {
    event.preventDefault();
    console.log('Form submitted');

    const fileInput = document.getElementById('file');
    const percentageInput = document.getElementById('percentage');
    const file = fileInput.files[0];
    const percentage = percentageInput.value;

    if (!file || !percentage) {
        alert('Please select a file and enter a compression percentage.');
        return;
    }

    const formData = new FormData();
    formData.append('file', file);
    formData.append('percentage', percentage);

    try {
        const response = await fetch('http://localhost:8080/compress', {
            method: 'POST',
            body: formData
        });

        if (!response.ok) {
            throw new Error('Failed to compress image');
        }

        const blob = await response.blob();
        const imageUrl = URL.createObjectURL(blob);
        const imgElement = document.getElementById('compressed-image');
        const downloadButton = document.getElementById('download-button');

        imgElement.src = imageUrl;
        downloadButton.href = imageUrl;

        document.getElementById('output').style.display = 'block';
        console.log('Image compressed and displayed');
    } catch (error) {
        console.error('Error:', error);
        alert('An error occurred while compressing the image.');
    }
});
