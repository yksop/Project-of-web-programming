
var logoutButton = document.querySelector('.logout-button');
logoutButton.addEventListener('click', function() {
    logOut();
});

var profileButton = document.querySelector('.profile-button');
var dataModal = document.getElementById('dataModal');

profileButton.addEventListener('click', function() {
    hideContentContainers();
    showInfo();
    dataModal.style.display = 'block';
});

var subscriptionButton = document.querySelector('.subscription-button');
var activityModal = document.getElementById('activityModal');

subscriptionButton.addEventListener('click', function() {
    hideContentContainers()
    activityModal.style.display = 'block';
});

var cancelButton = document.getElementById('cancelButton');
var confirmationModal = document.getElementById('confirmationModal');


cancelButton.addEventListener('click', function() {
    hideContentContainers()
    confirmationModal.style.display = 'block';
});

var confirmButton = document.getElementById('confirmButton');
var cancelButtonModal = document.getElementById('cancelButtonModal');

confirmButton.addEventListener('click', function() {
    cancellaIscrizione();
});

cancelButtonModal.addEventListener('click', function() {
    confirmationModal.style.display = 'none';
});


var closeButton = document.getElementById('closeButton');

closeButton.addEventListener('click', function() {
    dataModal.style.display = 'none';

});

var closeButtonCheckBox= document.getElementById("closeButtonCheckBox");

closeButtonCheckBox.addEventListener('click', function (){

    activityModal.style.display = 'none';
})

function hideContentContainers() {
    const contentContainers = document.querySelectorAll('.confirmation-modal');
    contentContainers.forEach(function(container) {
        container.style.display = 'none';
    });
}

window.addEventListener('DOMContentLoaded', function() {
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


function cancellaIscrizione() {
    var xhr = new XMLHttpRequest();
    xhr.open('GET', '../RegServlet?action=delete', true);
    xhr.setRequestHeader('Accept', 'text/plain');
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4 && xhr.status === 200) {
            document.getElementById("confirmationModal").style.display="none";
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


function logOut(){
    console.log("entrato in logout");
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