document.getElementById('upload-form').addEventListener('submit', async function(event) {
    event.preventDefault();

    const imageFile = document.getElementById('imageFile').files[0];
    if (!imageFile) {
        alert('Please select an image file.');
        return;
    }

    const formData = new FormData();
    formData.append('image', imageFile);

    try {
        const response = await fetch('https://shvmstools.onrender.com/convertPNG', {
            method: 'POST',
            body: formData
        });

        if (!response.ok) {
            throw new Error(await response.text());
        }

        const blob = await response.blob();
        const url = URL.createObjectURL(blob);

        document.getElementById('convertedImage').src = url;
        document.getElementById('downloadLink').href = url;
        document.getElementById('result').classList.remove('hidden');
    } catch (error) {
        alert('Error converting image: ' + error.message);
    }
});
