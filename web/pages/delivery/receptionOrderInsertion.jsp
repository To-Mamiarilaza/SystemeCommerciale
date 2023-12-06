<%@ page contentType="text/html; charset=UTF-8" %>
<%@page import="model.reception.*, model.article.*, model.base.*, model.supplier.Supplier, java.util.List" %>
<%
   SupplierDeliveryOrder delivery = (SupplierDeliveryOrder) request.getAttribute("delivery");
   List<ArticleDetails> articles = delivery.getListeArticles();
   // activer le modal : data-bs-toggle="modal" data-bs-target="#exampleModal"
   
%>

<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="exampleModalLabel"> Modification </h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <form action="./reception-order-insertion" method="post">
                <div class="modal-body">
                    <input type="text" name="article" value="">
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-primary">Valider</button>
                </div>
            </form>
        </div>
    </div>
</div>

<div class="row">
    <div class="col-md-12 mx-auto grid-margin stretch-card">
        <div class="card">
            <div class="card-body">
                <div class="row">
                    <div class="col-md-6">
                        <h4 class="card-title">Insertion du bon de reception</h4>
                        <form action="./reception-order-insertion" class="forms-sample" method="post">
                            <div class="form-group">
                                <label for="exampleInputUsername1">Reference bon de livraison</label>
                                <input type="text" name="referenceLivraison" class="form-control" id="exampleInputUsername1" value="<%= delivery.getReference() %>"  readonly>
                            </div>
                            <div class="form-group">
                                <label for="exampleInputUsername1">Reference bon de reception</label>
                                <input type="text" name="reference" class="form-control" id="exampleInputUsername1">
                            </div>
                            <div class="form-group">
                                <label for="exampleInputEmail1">Date</label>
                                <input type="date" name="date" class="form-control" id="exampleInputEmail1" value="<%= delivery.getDelivery_date() %>"
                                       placeholder="" readonly="">
                            </div>
                            <div class="form-group">
                                <label for="exampleInputUsername1">Nom du responsable</label>
                                <input type="text" name="responsable" class="form-control" id="exampleInputUsername1">
                            </div>
                            <div class="form-group">
                                <label for="exampleInputUsername1">Contact responsable</label>
                                <input type="text" name="responsableContact" class="form-control"
                                       id="exampleInputUsername1">
                            </div>
                            <% if((String) request.getAttribute("error") != null) { %>
                            <p class="text-error"> <%= (String) request.getAttribute("error") %> </p>
                            <% } %>           
                            <button type="submit"
                                    class="btn btn-gradient-primary me-2">Valider</button>
                            <a href="./delivery-order-insertion" class="btn btn-light">Cancel</a>
                        </form>
                    </div>
                    <div class="col-md-6">
                        <h4 class="card-title">Détails des articles reçue</h4>
                        <table class="table table-no-border align-middle">
                            <thead>
                                <tr class="table-primary">
                                    <td>Designation</td>
                                    <td>Quantite</td>
                                    <td></td>
                                </tr>
                            </thead>
                            <tbody>
                                <% for(int i=0; i<articles.size();i++) { %>
                                <tr>
                                    <td><%= articles.get(i).getArticle().getDesignation() %></td>
                                    <td> <%= articles.get(i).getQuantity() %> </td>
                                    <td><a href="./reception-action?action=1&idArticle=<%= i+1 %>" class="text-warning"><i
                                                class="mdi mdi-settings action-icon me-5"></i></a>
                                        <a href="./reception-action?action=2&idArticle=<%= i+1 %>" class="text-danger"><i class="mdi mdi-close action-icon"></i></a>
                                    </td>
                                </tr>
                                <% } %>                              

                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>