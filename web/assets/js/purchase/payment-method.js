/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


function getPaymentMethodTemplate(part, nbJour) {
    return `
    <tr class="line">
        <td><i class="mdi mdi-arrow-right-bold"></i></td>
        <td class="px-0">` + part + ` %</td>
        <td class="px-0"><strong><span class="me-3">` + nbJour + `</span>
                jour</strong></td>
        <td class="px-0">
            <a type="button" onclick="removePaymentMethod(this, ` + part + ` , ` + nbJour + `)" class="text-danger">
                <i class="mdi mdi-close"></i>
            </a>
        </td>
    </tr>
    `;
}

function savePaymentMethod(paymentMethodTemplate, part, nbJour) {
    $.ajax({
        type: 'POST',
        url: 'http://localhost:8080/SystemeCommerciale/payment-condition',
        data: {
            part: part,
            nbJour: nbJour,
            action: "add"
        },
        dataType: 'json',
        success: function (response) {
            if (response.error != null) {
                paymentMethodTemplate.remove();
            } else {
                
            }
        },
        error: function (jqXHR) {
            paymentMethodTemplate.remove();
            alert("Une erreur est survenue lors de l\'envoie : " + jqXHR.status);
        }
    });
}

function addNewPaymentMethod() {
    const part = $('#part').val();
    const nbJour = $('#nbJour').val();

    const paymentMethodList = $('#paymentMethodList');
    const paymentMethodTemplate = $(getPaymentMethodTemplate(part, nbJour));

    paymentMethodList.append(paymentMethodTemplate);

    savePaymentMethod(paymentMethodTemplate, part, nbJour);
    
    $('#part').val("");
    $('#nbJour').val("");
}

function removePaymentMethod(button, part, nbJour) {
    const line = $(button).closest('.line');
    line.remove();

    $.ajax({
        type: 'POST',
        url: 'http://localhost:8080/SystemeCommerciale/payment-condition',
        data: {
            part: part,
            nbJour: nbJour,
            action: "remove"
        },
        dataType: 'json',
        success: function (response) {
        },
        error: function (jqXHR) {
            alert("Une erreur est survenue lors de l\'envoie : " + jqXHR.status);
        }
    });
}