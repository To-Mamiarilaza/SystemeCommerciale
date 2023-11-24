/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

function selectBestSupplier(checkbox, idSupplierArticlePrice) {
    const fournisseurList = $(checkbox).closest(".fournisseurs-list");
    const nbFournisseur = fournisseurList.find(".fournisseur-line").length;
    
    const modal = $('#exampleModal');
    
    if (checkbox.checked && nbFournisseur < 3) {
        $('#validationDestination').attr('href', "./supplier-choice?idSupplierArticlePrice="+ idSupplierArticlePrice + "&checked=1");
        modal.modal('show');
    } else if (checkbox.checked) {
        window.location.href = "./supplier-choice?idSupplierArticlePrice="+ idSupplierArticlePrice + "&checked=1";
    } else {
        window.location.href = "./supplier-choice?idSupplierArticlePrice="+ idSupplierArticlePrice + "&checked=0";
    }
}