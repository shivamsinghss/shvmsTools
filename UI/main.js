
const container = document.getElementById('container');
const prevButton = document.getElementById('prevButton');
const nextButton = document.getElementById('nextButton');
let currentIndex = 0;
const divCount = container.children.length;

function goToNextDiv() {
    currentIndex = (currentIndex + 1) % divCount;
    updateDivPositions();
}

function goToPrevDiv() {
    currentIndex = (currentIndex - 1 + divCount) % divCount;
    updateDivPositions();
}

function updateDivPositions() {
    for (let i = 0; i < divCount; i++) {
        const offset = (i - currentIndex) * 100;
        container.children[i].style.transform = `translateX(${offset}%)`;
    }
}

function redirectToPageUrlShortner() {
    window.location.href = 'URLShortner.html';
}

function redirectToPageImageCompressor() {
    window.location.href = 'imageResizer.html';
}

nextButton.addEventListener('click', goToNextDiv);
prevButton.addEventListener('click', goToPrevDiv);

updateDivPositions();
