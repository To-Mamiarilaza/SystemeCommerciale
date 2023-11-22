/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

function selectBestSupplier(checkbox, idSupplierArticlePrice, idArticle) {
    const fournisseurList = $(checkbox).closest(".fournisseurs-list");
    const nbFournisseur = fournisseurList.find(".fournisseur-line").length;
    
    const modal = $('#exampleModal');
    
    alert("ID Article : " + idArticle);
    
    if (checkbox.checked && nbFournisseur < 3) {
        $('#validationDestination').attr('href', "./supplier-choice?idSupplierArticlePrice="+ idSupplierArticlePrice + "&checked=1&idArticle=" + idArticle);
        modal.modal('show');
    } else if (checkbox.checked) {
        console.log("==> " + "./supplier-choice?idSupplierArticlePrice="+ idSupplierArticlePrice + "&checked=1&idArticle=" + idArticle);
        //window.location.href = "./supplier-choice?idSupplierArticlePrice="+ idSupplierArticlePrice + "&checked=1&idArticle=" + idArticle;
    } else {
        //window.location.href = "./supplier-choice?idSupplierArticlePrice="+ idSupplierArticlePrice + "&checked=0&idArticle=" + idArticle;
    }
}