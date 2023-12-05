<%@ page contentType="text/html; charset=UTF-8" %>
<%@page import="model.purchase.*, model.article.Article, model.supplier.Supplier, java.util.List" %>
<% 
    List<ArticleRequest> articleRequests = (List<ArticleRequest>) request.getAttribute("articleRequests");
    List<Proforma> proformas = (List<Proforma>) request.getAttribute("proformas");
    List<Article> articles = (List<Article>) request.getAttribute("articles");
%>

<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Validation choix fournisseur</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal"
                        aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <p>Voulez vous vraiment choisir cette fournisseur ? <br> <span class="text-small text-danger"> Vous devez au moins en ajouter trois ! </span> </p>
                <a href="./article-price-insertion" class="btn btn-secondary">Annuler</a>
                <a href="" id="validationDestination" class="btn btn-primary">Choisir</a>
            </div>
        </div>
    </div>
</div>


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
    <div class="col-12 grid-margin">
        <div class="card">
            <div class="card-body">
                <h4 class="card-title">Listes des demandes d'achat</h4>
                <div class="mt-4 d-flex align-items-center justify-content-between">
                    <form action="./article-price-insertion" method="POST">
                        <div class="input-groups d-flex align-items-center">
                            <div class="form-group me-4">
                                <label for="">Filtre par article</label>
                                <select name="idArticle" id=""
                                        class="form-control form-control-sm px-5 mt-2">
                                    <option value="">Toutes les articles</option>
                                    <% for(Article article : articles) { %>
                                    <option value="<%= article.getIdArticle() %>"><%= article.getDesignation() %></option>
                                    <% } %>
                                </select>
                            </div>
                            <div>
                                <input type="submit" class="mx-2 btn btn-gradient-primary"
                                       value="Filtrer">
                            </div>
                            <div>
                                <a href="./send-article-request" class="mx-2 btn btn-outline-primary"><i class="mdi mdi-send me-4"></i>Demande de proforma</a>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="row">
                    <!-- LISTES DES ARTICLES DEMANDES -->
                    <div class="mt-3 row col-md-8 article-list">
                        <% for(ArticleRequest articleRequest : articleRequests) { %>
                        <div class="col-md-6 article-detail px-2 mb-3">
                            <div class="inside-div">
                                <h4><%= articleRequest.getArticle().getDesignation() %></h4>
                                <h6 class="mt-4 second-color">Quantite : <span
                                        class="mx-3"><%= articleRequest.getQuantity() %></span>
                                </h6>
                                <p class="quantity-detail mt-3 mb-2">Detail a propos du quantite</p>
                                <table class="table td-little-padding">
                                    <tbody>
                                        <% for(ArticleQuantity articleQuantity : articleRequest.getArticleQuantityDetails()) { %>
                                        <tr>
                                            <td><%= articleQuantity.getPurchaseRequest().getSendingDate() %></td>
                                            <td><%= articleQuantity.getPurchaseRequest().getService().getService() %></td>
                                            <td><%= articleQuantity.getQuantity() %></td>
                                        </tr>
                                        <% } %>
                                    </tbody>
                                </table>
                                <h6 class="mt-4 second-color">Prix par fournisseurs</h6>
                                <div class="fournisseurs-list mt-3" action="">
                                    <% for(SupplierArticlePrice supplierPrice : articleRequest.getSupplierArticlePrices()) { %>
                                    <div class="fournisseur-line form-group mb-2">
                                        <div
                                            class="d-flex align-items-center justify-content-between">
                                            <div class="form-check my-0">
                                                <label class="form-check-label mb-0">
                                                    <input type="checkbox" onchange="selectBestSupplier(this, <%= supplierPrice.getIdSupplierArticlePrice() %>)" class="form-check-input" value="<%= supplierPrice.getSupplier().getIdSupplier() %>" <%= supplierPrice.getChosenCheckedStatus() %>>
                                                    <%= supplierPrice.getSupplier().getSupplierName() %>
                                                    <i class="input-helper"></i>
                                                </label>
                                            </div>
                                            <div class="my-0 montant text-right">
                                                <%= supplierPrice.getUnitPriceString() %>
                                            </div>
                                            <div class="text-danger my-0">
                                                <a href="./article-price-managing?idSupplierPrice=<%= supplierPrice.getIdSupplierArticlePrice() %>"><i class="mdi mdi-close"></i></a>
                                            </div>
                                        </div>
                                    </div>
                                    <% } %>
                                </div>
                                <form action="./article-price-managing" method="POST" class="form mt-4">
                                    <input type="hidden" name="idArticle" value="<%= articleRequest.getArticle().getIdArticle() %>">
                                    <div class="row">
                                        <div class="col-md-5">
                                            <select name="idSupplier" id=""
                                                    class="form-control form-control-sm">
                                                <% for(Supplier supplier : articleRequest.getConvenableSuppliers()) { %>
                                                <option value="<%= supplier.getIdSupplier() %>"><%= supplier.getSupplierName() %></option>
                                                <% } %>
                                            </select>
                                        </div>
                                        <div class="col-md-5 px-0">
                                            <input type="number" name="price"
                                                   class="form-control form-control-sm"
                                                   placeholder="30 000 AR" required>
                                        </div>
                                        <div class="col-md-2 px-3">
                                            <button type="submit" class="add-button"><i
                                                    class="mdi mdi-plus-box"></i></button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                        <% } %>
                    </div>
                    <!-- LISTES DES ARTICLES DEMANDES -->

                    <!-- LISTES DES PROFORMAS DISPONIBLE -->
                    <div class="mt-3 col-md-4 px-5">
                        <h4>Listes des proformas disponible</h4>
                        <hr>
                        <table class="table table-no-border">
                            <thead>

                            </thead>
                            <tbody>
                                <% for(Proforma proforma : proformas) { %>
                                <tr>
                                    <td><i class="mdi mdi-arrow-right-bold"></i></td>
                                    <td><%= proforma.getSupplier().getSupplierName() %></td>
                                    <td class="text-warning text-right"><%= proforma.getTotalTTCString() %></td>
                                    <td><a href="./proforma-detail?idSupplier=<%= proforma.getSupplier().getIdSupplier() %>" class="no-style-link">Voir plus</a></td>
                                </tr>
                                <% } %>
                            </tbody>
                        </table>
                    </div>
                    <!-- LISTES DES PROFORMAS DISPONIBLE -->

                </div>
            </div>
        </div>
    </div>
</div>