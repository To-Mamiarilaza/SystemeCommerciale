/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

function getDetailTemplate(idArticle, designation, quantity) {
    return `
        <tr>
            <td>` + designation + `</td>
            <td>` + quantity + `</td>
            <td>
                <a type="button" onclick="removeDetail(this, '` + designation + `', ` + quantity + `)" class="text-danger"><i class="mdi mdi-close action-icon"></i></a>
            </td>
        </tr>
    `;
}

function addNewDetail() {
    const idArticle = $('#articleChoice').val();
    const designation = $('#articleChoice option:selected').text();
    const quantity = $('#quantity').val();
    
    if (quantity == "") return;
    
    const container = $('#detailList');
    const newRow = $(getDetailTemplate(idArticle, designation, quantity));
    container.prepend(newRow);
    
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/SystemeCommerciale/add-outgoing-order-detail',
        data: {
            idArticle: idArticle,
            designation: designation,
            quantity: quantity
        },
        dataType: 'json',
        success: function (response) {
            if (response.error != null) {
                newRow.remove();
            } else {
                
            }
        },
        error: function (jqXHR) {
            newRow.remove();
            alert("Une erreur est survenue lors de l\'envoie : " + jqXHR.status);
        }
    });
}

function removeDetail(button, designation, quantity) {
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/SystemeCommerciale/remove-outgoing-order-detail',
        data: {
            designation: designation,
            quantity: quantity
        },
        dataType: 'json',
        success: function (response) {
            if (response.error != null) {
                
            } else {
                $(button).closest('tr').remove();
            }
        },
        error: function (jqXHR) {
            alert("Une erreur est survenue lors de l\'envoie : " + jqXHR.status);
        }
    });
}
    