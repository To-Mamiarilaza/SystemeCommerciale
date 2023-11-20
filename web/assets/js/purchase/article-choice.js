/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

function selectBestSupplier(checkbox, idSupplierArticlePrice) {
    if (checkbox.checked) {
        window.location.href = "./supplier-choice?idSupplierArticlePrice="+ idSupplierArticlePrice + "&checked=1";
    } else {
        window.location.href = "./supplier-choice?idSupplierArticlePrice="+ idSupplierArticlePrice + "&checked=0";
    }
}