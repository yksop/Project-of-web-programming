var images = ["Immagini progetto/Private/11v.jpg", "Immagini progetto/Private/14v.jpg", "Immagini progetto/Private/16v.jpg"
    , "Immagini progetto/Private/17v.jpg", "Immagini progetto/Private/18v.jpg", "Immagini progetto/Private/19v.jpg"
    , "Immagini progetto/Private/20v.jpg", "Immagini progetto/Private/21v.jpg", "Immagini progetto/Private/23v.jpg", "Immagini progetto/Private/24v.jpg",
    "Immagini progetto/Private/26v.jpg", "Immagini progetto/Private/29v.jpg", "Immagini progetto/Private/30v.jpg", "Immagini progetto/Private/31v.jpg"];
var currentImage = 0;

function changeImage() {
    var sidebar = document.querySelector('.sidebar');
    currentImage = (currentImage + 1) % images.length;
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                sidebar.style.backgroundImage = 'url("' + images[currentImage] + '")';

            } else {
                console.log('Errore durante il caricamento dell\'immagine.');
            }
        }
    };
    xhr.open('GET', images[currentImage], true);
    xhr.send();
}

setInterval(changeImage, 5000);