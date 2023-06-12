document.addEventListener("DOMContentLoaded", function() {
    var path = window.location.pathname;
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4 && xhr.status === 200) {
            var response = xhr.responseText;
            console.log("chiamata fatta");
        }
    };
    xhr.open("GET", "../ContaVisite?path="+ encodeURIComponent(path) , true);
    xhr.send();
});
