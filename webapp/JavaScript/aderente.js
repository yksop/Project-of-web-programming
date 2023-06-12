var images = ["../Immagini progetto/Private/14v.jpg", "../Immagini progetto/Private/16v.jpg"
    , "../Immagini progetto/Private/17v.jpg", "../Immagini progetto/Private/18v.jpg", "../Immagini progetto/Private/19v.jpg"
    , "../Immagini progetto/Private/20v.jpg", "../Immagini progetto/Private/21v.jpg", "../Immagini progetto/Private/23v.jpg", "../Immagini progetto/Private/24v.jpg",
    "../Immagini progetto/Private/26v.jpg","../Immagini progetto/Private/11v.jpg",  "../Immagini progetto/Private/29v.jpg", "../Immagini progetto/Private/30v.jpg", "../Immagini progetto/Private/31v.jpg"];
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




const profileButton = document.querySelector('.profile-button');
const registrationButton = document.querySelector('.registration-button');
const donationButton = document.querySelector('.donation-button');
const cancelButton = document.querySelector('.cancel-button');
const profileData = document.getElementById('profile-data');
const activityData = document.getElementById('activity-data');
const cancelData = document.getElementById('cancel-data');
const donationData = document.getElementById('donation-data');



profileButton.addEventListener('click', function() {
    hideContentContainers();
    showInfo();
    profileData.style.display = 'block';
});

registrationButton.addEventListener('click', function() {
    hideContentContainers();
    activityData.style.display = 'block';
});

donationButton.addEventListener('click', function() {
    hideContentContainers();
    donationData.style.display="block";
});

cancelButton.addEventListener('click', function() {
    hideContentContainers();
    cancelData.style.display = 'block';
});


function logOut(){
    var xhr = new XMLHttpRequest();
    xhr.open('GET', '../LoginServlet?action=logout', true);
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4 && xhr.status === 200) {
            console.log("chiamata con successo");
            xhr.response;
            window.location.href="logIn.jsp?message=Logout%20eseguito%20con%20successo";
        }
    };
    xhr.send();
}

function showInfo(){
    var xhr = new XMLHttpRequest();
    xhr.open('POST', '../SingleElencoServlet', true);
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4 && xhr.status === 200) {
            document.getElementById("data").innerHTML = xhr.responseText;
        }
    };
    xhr.send();
}


function hideContentContainers() {
    const contentContainers = document.querySelectorAll('.content-container');
    contentContainers.forEach(function(container) {
        container.style.display = 'none';
    });
}

function cancellaIscrizione() {

    var xhr = new XMLHttpRequest();
    xhr.open('GET', '../RegServlet?action=delete', true);
    xhr.setRequestHeader('Accept', 'text/plain');
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4 && xhr.status === 200) {
            document.getElementById("cancel-data").style.display="none";
            var confirmationMessage = document.getElementById('confirmation-message');
            confirmationMessage.innerText = xhr.responseText;
            confirmationMessage.style.display = 'block';
            setTimeout(function() {
                window.location.href = 'signIn.jsp';
            }, 3000);
        }
    };

    xhr.send();
}


function sendDonation() {
    var donationAmount = document.getElementById('donation-input').value;
    var url = '../DonazioniServlet?action=aderente&donation=' + donationAmount;
    if (donationAmount !== '') {
        document.getElementById('donation-input').value = '';
        var xhr = new XMLHttpRequest();
        xhr.open('GET', url, true);
        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4 && xhr.status === 200) {
                // Chiamata AJAX completata con successo
                var messageDiv = document.getElementById('donation-message');
                messageDiv.innerText = 'Grazie per aver donato!';
                messageDiv.style.display = 'block';
                setTimeout(function() {
                    messageDiv.style.display = 'none';
                }, 3000);
            }
        };
        xhr.send();
    }
}

function goBack(button) {
    const cancelData = document.getElementById(button);
    cancelData.style.display = 'none';
}




document.addEventListener('DOMContentLoaded', function() {
    var xhr = new XMLHttpRequest();
    xhr.open('GET', '../RegServlet?action=welcome', true);
    xhr.setRequestHeader('Accept', 'text/plain');
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4 && xhr.status === 200) {
            var user_name = JSON.parse(xhr.responseText);
            var parts = user_name.split(":");
            var username = parts[0];
            var name = parts[1];
            var welcomeMessage = document.getElementById("welcome-message");
            if (getGender(name)) {
                welcomeMessage.innerText = 'Benvenuta ' + username + '!';
            } else {
                welcomeMessage.innerText = 'Benvenuto ' + username + '!';
            }

        }
    };
    xhr.send();
});

function getGender(name) {
    var lastCharacter = name.charAt(name.length - 1);
    var last=lastCharacter.toLowerCase();
    return last === 'a' || last === 'e' || last === 'y' || last === 'h'|| last === 'i';
}
