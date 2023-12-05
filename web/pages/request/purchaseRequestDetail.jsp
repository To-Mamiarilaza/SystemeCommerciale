<%@page import="java.util.List"%>
<%@page import="model.purchase.PurchaseRequest"%>
<%@page import="model.purchase.ArticleQuantity"%>
<%@page import="model.article.Article"%>
<%@page import="model.base.Utilisateur"%>

<div class="page-header">
    <h3 class="page-title">
        <span class="page-title-icon bg-gradient-primary text-white me-2">
            <i class="mdi mdi-home"></i>
        </span> Demandes d'achat
    </h3>
    <nav aria-label="breadcrumb">
        <ul class="breadcrumb">
            <li class="breadcrumb-item active" aria-current="page">
                <span></span>Overview <i
                    class="mdi mdi-alert-circle-outline icon-sm text-primary align-middle"></i>
            </li>
        </ul>
    </nav>
</div>

<div class="row">
    <div class="col-8 grid-margin mx-auto">
        <div class="card">
            <div class="card-body">
                <h4 class="card-title">Detail demande achat</h4>
                <% if(request.getAttribute("purchaseRequest") != null && request.getAttribute("user") != null) { 
                    PurchaseRequest purchaseRequest = (PurchaseRequest)request.getAttribute("purchaseRequest");
                    Utilisateur user = (Utilisateur)request.getAttribute("user");
                %>
                <h6>Demande n° : <%=purchaseRequest.getIdPurchaseRequest() %></h6>
                <div class="mt-4">
                    <form action="./PurchaseRequestEdit" method="POST">
                        <div class="form-group">
                            <label for="exampleInputUsername1">Titre du demande</label>
                            <input type="text" name="title" class="form-control" id="exampleInputUsername1"
                                    placeholder="Demande mensuel du departement" value="<%=purchaseRequest.getTitle() %>">
                        </div>
                        <div class="form-group">
                            <label for="exampleInputPassword1">Description</label>
                            <textarea name="description" class="form-control" id="" cols="30" rows="10"><%=purchaseRequest.getDescription() %></textarea>
                        </div>
                        <% if(purchaseRequest.getStatus() == 1) { %>
                        <div class="row align-items-end">
                            <div class="form-group col-md-5">
                                <label for="article">Article</label>
                                <% if(request.getAttribute("articles") != null) { 
                                List<Article> articles = (List<Article>)request.getAttribute("articles");
                                %>
                                <select name="article" class="form-control form-control-sm input-height mt-2" id="articleInput">
                                    <% for(int i = 0; i < articles.size(); i++) { %>
                                    <option value="<%=articles.get(i).getIdArticle() %>"><%=articles.get(i).getDesignation() %></option>
                                    <% } %>
                                </select>
                                <% } %>
                            </div>
                            <div class="form-group col-md-3">
                                <label for="article">Quantite</label>
                                <input type="number" class="form-control mt-2" value="10"  name="quantity" id="quantiteInput">
                            </div>
                            <div class="form-group col-md-3">
                                <label for=""></label>
                                <input type="button" onclick="addNewArticle()" class="btn btn-gradient-primary" value="Ajouter">
                            </div>
                        </div>
                        <% } %>
                        <div class="row mb-3">
                            <div class="col-md-8">
                                <table class="table" id="articleList">
                                    <tbody>
                                        <% if(request.getAttribute("articlesQuantity") != null) { 
                                        List<ArticleQuantity> articleQuantity = (List<ArticleQuantity>)request.getAttribute("articlesQuantity");
                                        for(int i = 0; i < articleQuantity.size(); i++) {
                                        %>
                                        <tr id="<%=articleQuantity.get(i).getArticle().getCode() %>" class="article">
                                            <td><%=articleQuantity.get(i).getArticle().getCode() %></td>
                                            <td><%=articleQuantity.get(i).getArticle().getDesignation() %></td>
                                            <td><%=articleQuantity.get(i).getQuantity() %></td>
                                            <td><%=articleQuantity.get(i).getArticle().getUnity().getName() %></td>
                                            <td>
                                                <button type="button" onclick="deleteRequest(this)"><i class="mdi mdi-delete"></i></button> 
                                            </td>
                                        </tr>
                                        <% } } %>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <% if(request.getAttribute("error") != null) { %>
                        <p class="text-error"><i class="mdi mdi-information-outline"></i><%=request.getAttribute("error") %></p>
                        <% } %>
                        <% if(purchaseRequest.getStatus() == 1) { %>
                            <div class="mt-3">
                                <button type="submit"
                                        class="btn btn-gradient-primary px-5 me-2">Modifier demande</button>
                            </div>
                        <% } %>
                        <div class="mt-3">
                            <% if(purchaseRequest.getStatus() == 1 && user.getIsAdmin() == true) { %>
                            <a href="./PurchaseRequestAction?idHelp=1" class="btn btn-gradient-danger px-5 me-2">Valide</a>
                            <a href="./PurchaseRequestAction?idHelp=0" class="btn btn-gradient-success px-5 me-2">Refuse</a>
                            <br>
                            <% } %>
                            <a  href="./purchase-request-list" class="btn btn-light mt-3">Cancel</a>
                        </div>
                    </form>
                </div>
                <% } %>
            </div>
        </div>
    </div>
</div>
