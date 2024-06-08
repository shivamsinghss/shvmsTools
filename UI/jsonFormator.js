document.addEventListener('DOMContentLoaded', updateLineNumbers);

function updateLineNumbers() {
    const textarea = document.getElementById('jsonInput');
    const lineNumbers = document.getElementById('lineNumbers');
    const numberOfLines = textarea.value.split('\n').length;
    lineNumbers.innerHTML = Array(numberOfLines).fill().map((_, i) => `<span>${i + 1}</span>`).join('');
}

function syncScroll() {
    const textarea = document.getElementById('jsonInput');
    const lineNumbers = document.getElementById('lineNumbers');
    lineNumbers.scrollTop = textarea.scrollTop;
}

async function formatJson() {
    const jsonInput = document.getElementById('jsonInput').value;
    const outputContainer = document.getElementById('outputContainer');

    // Clear previous output
    outputContainer.textContent = '';

    try {
        const response = await fetch('https://shvmstools.onrender.com/formatJson', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: jsonInput
        });

        const result = await response.json();

        if (response.ok) {
            const jsonOutput = document.createElement('pre');
            jsonOutput.textContent = result.formattedJson;
            outputContainer.appendChild(jsonOutput);
            showCopyButton(jsonOutput);
        } else {
            const { error, message, location } = result;
            const errorOutput = document.createElement('pre');
            errorOutput.textContent = `${error}: ${message} (Line: ${location.lineNr}, Column: ${location.columnNr})`;
            outputContainer.appendChild(errorOutput);
            hideCopyButton();
        }
    } catch (error) {
        const errorOutput = document.createElement('pre');
        errorOutput.textContent = 'An error occurred: ' + error.message;
        outputContainer.appendChild(errorOutput);
        hideCopyButton();
    }
}

function showCopyButton(jsonOutput) {
    const copyButton = document.createElement('button');
    copyButton.textContent = 'Copy';
    copyButton.onclick = () => copyToClipboard(jsonOutput.textContent);
    outputContainer.appendChild(copyButton);
}

function hideCopyButton() {
    const copyButton = document.querySelector('#outputContainer button');
    if (copyButton) {
        copyButton.remove();
    }
}

function copyToClipboard(text) {
    navigator.clipboard.writeText(text).then(() => {
        alert('Copied to clipboard');
    }).catch(err => {
        alert('Failed to copy: ' + err);
    });
}
