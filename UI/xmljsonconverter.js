async function convertJsonToXml() {
    const inputText = document.getElementById('inputText').value;
    try {
        const response = await fetch('https://shvmstools.onrender.com/json2xml', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(JSON.parse(inputText))
        });
        if (response.ok) {
            const xml = await response.text();
            document.getElementById('outputText').value = xml;
        } else {
            const error = await response.text();
            document.getElementById('outputText').value = `Error: ${error}`;
        }
    } catch (error) {
        document.getElementById('outputText').value = `Error: ${error.message}`;
    }
}

async function convertXmlToJson() {
    const inputText = document.getElementById('inputText').value;
    try {
        const response = await fetch('https://shvmstools.onrender.com/xml2json', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/xml'
            },
            body: inputText
        });
        if (response.ok) {
            const json = await response.json();
            document.getElementById('outputText').value = JSON.stringify(json, null, 4);
        } else {
            const error = await response.text();
            document.getElementById('outputText').value = `Error: ${error}`;
        }
    } catch (error) {
        document.getElementById('outputText').value = `Error: ${error.message}`;
    }
}
async function copyOutput() {
    const outputText = document.getElementById('outputText');
    outputText.select();
    document.execCommand('copy');
    alert('Output copied to clipboard!');
}
