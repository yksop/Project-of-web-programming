
        var images = ["Immagini progetto/Private/12.jpg", "Immagini progetto/Private/15.jpg", "Immagini progetto/Private/22.jpg"
                        , "Immagini progetto/Private/27.jpg", "Immagini progetto/Private/28.jpg", "Immagini progetto/Private/32.jpg"
                        , "Immagini progetto/Private/33.jpg"];
        var currentImage = 0;

        function changeImage() {
            var header = document.querySelector('.header');
            currentImage = (currentImage + 1) % images.length;
            var xhr = new XMLHttpRequest();
            xhr.onreadystatechange = function() {
                if (xhr.readyState === 4) {
                    if (xhr.status === 200) {
                    header.style.backgroundImage = 'url("' + images[currentImage] + '")';
                    } else {
                        console.log('Errore durante il caricamento dell\'immagine.');
                    }
                }
            };
            xhr.open('GET', images[currentImage], true);
            xhr.send();
         }

        setInterval(changeImage, 5000);

