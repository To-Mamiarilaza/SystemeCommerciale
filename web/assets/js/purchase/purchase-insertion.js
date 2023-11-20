function getArticleTemplate(code, name, quantity, unity) {
    return `
        <tr class="article">
            <td>`+code+`</td>
            <td>`+name+`</td>
            <td>`+quantity+`</td>
            <td>`+unity+`</td>
            <td>
                <button type="button" onclick="deleteRequest(this)"><i class="mdi mdi-delete"></i></button>
            <td>
        </tr>
    `;
}


// fonction pour ajouter une nouvelle article
/*function addNewArticle() {
    var articleValue = $('#articleInput').val();
    var quantiteValue = $('#quantiteInput').val();
    console.log("article : "+articleValue);
    console.log("Quantity : "+quantiteValue);

    // envoyer une ajax vers le controller
    $.ajax({
        type: 'POST',
        url: 'http://localhost:8080/SystemeCommerciale/AddNewRequest',
        data: {
            article: articleValue,
            quantity: quantiteValue
        },
        dataType: 'json',
        success: function (response) {
            if (response.error != null) {
                quizTemplate.remove();
                $('#addArticleError').text(response.error);
                console.log("error");
            } else {
                $('#addArticleError').text("");
                console.log("Mandeha");
                console.log(response);
                /*var code = response.article.code;
                var article = response.articleQuantity.designation;
                var quantity = response.quantity;
                var unity = response.article.unity.name;
                console.log("Tafa");
                var quizTemplate = $(getQuestionTemplate(questionValue, noteValue));
                
                // add template to the quiz container
                var articleList = $('#articleList');
                 var articleTemplate = $(getArticleTemplate(code, article, quantity, unity));
                articleList.append(articleTemplate);
            }
        },
        error: function (response) {
            console.log(response);
        }
    });
}
*/

// fonction pour ajouter une nouvelle question
function addNewArticle() {
    var articleValue = $('#articleInput').val();
    var quantiteValue = $('#quantiteInput').val();
    
    // envoyer une ajax vers le controller
    $.ajax({
        type: 'POST',
        url: 'http://localhost:8080/SystemeCommerciale/AddNewRequest',
        data: {
            article: articleValue,
            quantity: quantiteValue
        },
        dataType: 'text',
        success: function (response) {
              var responseJson = JSON.parse(response);
              if(responseJson.error != null) {
                  console.log(responseJson.error);
              } else {
                  console.log(responseJson);
                  if(responseJson.exist == true) {
                      updateTableListArticle(responseJson.code, responseJson.quantity);
                  } else {
                      
                        // add template to the quiz container
                        var articleList = $('#articleList');
                        var articleTemplate = $(getArticleTemplate(responseJson.code, responseJson.article, responseJson.quantity, responseJson.unity));
                        articleList.append(articleTemplate);
                        articleTemplate.attr('id', responseJson.code);        // Redonner l'ID aux nouvelles éléments
                  }
              }
        },
        error: function (response) {
            console.log("ERREUR , voici la reponse");
            console.log("TEXT : " + response);
            var jsonResponse = JSON.parse(response);
            console.log("JSON : " + jsonResponse);
        }
    });
}

//Fonction pour changer les valeurs du table article ajoutes
function updateTableListArticle(code, quantity) {
    // Obtenez la référence de l'élément table par son ID
    var articleList = document.getElementById('articleList');

    // Vérifiez si l'élément avec l'ID spécifié existe
    if (articleList) {
        // Obtenez toutes les lignes de la table (éléments tr)
        var rows = articleList.getElementsByTagName('tr');

        // Parcourez toutes les lignes de la table
        for (var i = 0; i < rows.length; i++) {
            // Obtenez toutes les cellules de la ligne actuelle (éléments td)
            var cells = rows[i].getElementsByTagName('td');

            if(cells[0].textContent == code) {
                cells[2].textContent = quantity;
            }
        }
    } else {
        console.log("L'élément avec l'ID 'articleList' n'a pas été trouvé.");
    }
}


// fonction pour supprimé un article ajoute
function deleteRequest(bouton) {
    var article = bouton.closest('.article');
    var id = article.id;
    console.log(id);
    // Ensuite supprimé du session
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/SystemeCommerciale/DeleteRequest',
        data: {
            code: id
        },
        success: function (reponse) {
            article.remove();
        },
        error: function () {
            alert("Une erreur est survenue lors du suppression !");
        }
    });
}