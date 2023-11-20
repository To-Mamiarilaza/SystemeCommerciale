/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

// save the purchase Order to the database
function savePurchaseOrder(idSupplier) {
    const deliveryDate = $('#deliveryDate').val();
    const paymentMethod = $('#paymentMethod').val();
    
    console.log("ID Supplier : " + idSupplier + " - deliveryDate : " + deliveryDate + " - paymentMethod : " + paymentMethod);
    
    $.ajax({
        type: 'POST',
        url: 'http://localhost:8080/SystemeCommerciale/purchase-order-insertion',
        data: {
            idSupplier: idSupplier,
            nbJourDelivery: deliveryDate,
            idPaymentMethod: paymentMethod
        },
        dataType: 'json',
        success: function (response) {
            if (response.error != null) {
            } else {
                window.location.href = "http://localhost:8080/SystemeCommerciale/purchase-order-list";
            }
        },
        error: function (jqXHR) {
            alert("Une erreur est survenue lors de l\'envoie : " + jqXHR.status);
        }
    });
}