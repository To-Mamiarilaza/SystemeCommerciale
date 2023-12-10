<%@page import="java.util.List"%>
<%@page import="model.purchase.PurchaseRequest"%>
<%@page import="model.base.Service"%>
<%@page import="model.movement.out.*, model.purchase.*, model.reception.*, model.article.*, model.base.*, model.supplier.Supplier, java.util.List" %>
<%@page contentType="text/html; charset=UTF-8" %>
<%
    OutgoingOrder outOrder = (OutgoingOrder) request.getAttribute("outOrder");
    List<OutgoingOrderDetail> outOrderDetails = (List<OutgoingOrderDetail>) request.getAttribute("orderDetails");
    List<Article> articles = (List<Article>) request.getAttribute("articles");
%>
<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="exampleModalLabel">Une anomalie s'est produite</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <form action="./dept-reception-order-list">
                <div class="modal-body">
                    <p><strong>On a constaté les anomalies suivants</strong></p>
                    <ul>
                        <li>La quantite attendue pour le savon est 12, mais on reçoit 10</li>
                        <li>La quantite attendue pour le savon est 12, mais on reçoit 10</li>
                        <li>La quantite attendue pour le savon est 12, mais on reçoit 10</li>
                    </ul>
                    <h5>Explication</h5>
                    <hr>
                    <textarea name="explication" placeholder="Vous devez écrire l'éxplication ici" id=""
                              class="form-control" cols="30" rows="7"></textarea>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-primary">Valider</button>
                </div>
            </form>
        </div>
    </div>
</div>

<div class="page-header">
    <h3 class="page-title">
        <span class="page-title-icon bg-gradient-primary text-white me-2">
            <i class="mdi mdi-home"></i>
        </span> Accuse de reception
    </h3>
    <nav aria-label="breadcrumb">
        <ul class="breadcrumb">
            <li class="breadcrumb-item active" aria-current="page">
                <span></span>Overview <i class="mdi mdi-alert-circle-outline icon-sm text-primary align-middle"></i>
            </li>
        </ul>
    </nav>
</div>

<div class="row">
    <div class="col-md-12 mx-auto grid-margin stretch-card">
        <div class="card">
            <div class="card-body">
                <div class="row">
                    <div class="col-md-6">
                        <h4 class="card-title">Insertion d'une accusée de récéption</h4>
                        <form class="forms-sample" action="./dept-reception-order-insertion" method="post">
                            <div class="form-group">
                                <label for="exampleInputEmail1">Date</label>
                                <input type="date" value = "<%= outOrder.getDate() %>" name="date" class="form-control" id="exampleInputEmail1"
                                       placeholder="" readonly>
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
                            <input type="hidden" name="idOutOrder" value="<%= outOrder.getIdOutgoingOrder() %>">
                            <div class="form-group">
                                <label for="exampleInputPassword1">Bon de sortie</label>
                                <input type="text" value="<%= outOrder.getReference() %>" name="referenceSortie" class="form-control"
                                       id="exampleInputEmail1" readonly>
                            </div>
                            <% if(request.getAttribute("error") != null) { %>
                            <p class="text-error">Une erreur s'est produite</p>
                            <% } %>
                            <button type="submit"
                                    class="btn btn-gradient-primary me-2">Crée
                                l'accuse de reception</button>
                            <a href="./dept-request-reception-list" class="btn btn-light">Cancel</a>
                        </form>
                    </div>
                    <div class="col-md-6">
                        <h4 class="card-title">Détails des articles reçue</h4>
                        <table class="table align-middle">
                            <thead>
                                <tr class="table-primary">
                                    <td>Designation</td>
                                    <td>Quantite</td>
                                    <td></td>
                                </tr>
                            </thead>
                            <tbody>
                                <% for(int i=0;i<outOrderDetails.size();i++) { %>
                                <tr>
                                    <td><%= outOrderDetails.get(i).getArticle().getDesignation() %></td>
                                    <td><%= outOrderDetails.get(i).getQuantity() %></td>
                                    <td>
                                        <a href="" class="text-danger"><i class="mdi mdi-close action-icon"></i></a>
                                    </td>
                                </tr>
                                <% } %>

                                <!-- MODIFICATION ROW -->

                                <tr>
                                    <td>
                                        <select name="article" class="form-select form-control-sm">
                                            <% for(int i=0;i<articles.size();i++) { %>
                                            <option value="<%= articles.get(i).getIdArticle() %>"><%= articles.get(i).getDesignation() %></option>
                                            <% } %>
                                        </select>
                                    </td>
                                    <td>
                                        <input type="text" class="form-control" name="quantite">
                                    </td>
                                    <td>
                                        <button class="btn btn-info">Ajouter</button>
                                    </td>
                                </tr>

                                <!-- MODIFICATION ROW -->

                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>