document.addEventListener("DOMContentLoaded", function () {
    var addArticle = document.getElementById("addArticleDetail");
    addArticle.addEventListener("click", function (event) {
        event.preventDefault();
        var article = document.getElementById("article").value;
        var quantite = document.getElementById("quantite").value;

        // le conteneur ou on doit ajouter la liste obtenue
        var tbody = document.getElementById("tbody");
        tbody.textContent = "";
        var xhr = new XMLHttpRequest();
        xhr.open("POST", "/SystemeCommerciale/delivery-add-article", true);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        xhr.onload = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                var responseData = JSON.parse(xhr.responseText);
                for (var i = 0; i < responseData.length; i++) {
                    var article = responseData[i].article.designation;
                    var quantite = responseData[i].quantity;
                    tbody.innerHTML += "<tr>\n\
                    <td>" + article + "</td>\n\
                    <td>" + quantite + "</td>\n\
                    <td><i class='mdi mdi-close action-icon text-danger'></i></td>\n\
                    </tr>";
                }
            } else {
                alert(" erreur lors de l'insertion de l'article ");
            }
        };
        var data = "article=" + encodeURIComponent(article) + "&quantite=" + encodeURIComponent(quantite);
        xhr.send(data);
        return false;
    });


    var addAnomalieDelivery = document.getElementById("addAnomalieDelivery");
    addAnomalieDelivery.addEventListener("click", function (event) {
        event.preventDefault();

        var explication = document.getElementById("explication").value;
        console.log(explication);
        var ulElement = document.getElementById("detailsAnnomalie");

        var detailAnomalie = ulElement.getElementsByTagName("li");
        var details = [];
        for (var i = 0; i < detailAnomalie.length; i++) {
            var anomalyText = detailAnomalie[i].textContent || detailAnomalie[i].innerText;
            console.log(anomalyText);
            details.push(anomalyText);
        }

        var xhr = new XMLHttpRequest();
        xhr.open("POST", "/SystemeCommerciale/add-anomalie-delivery", true);

        xhr.onload = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                window.location.href = "/SystemeCommerciale/reception-order-insertion";
            }

        };

        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        var data = "explication=" + encodeURIComponent(explication) + "&detailAnomalie=" + encodeURIComponent(JSON.stringify(details));
        xhr.send(data);
        return false;
    });

});


