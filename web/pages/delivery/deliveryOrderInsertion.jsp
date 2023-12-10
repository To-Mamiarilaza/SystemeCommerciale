<%@page contentType="text/html; charset=UTF-8" %>
<%@page import="model.purchase.*, model.reception.*, model.article.*, model.base.*, model.supplier.Supplier, java.util.List" %>
<%
   List<Article> articles = (List<Article>) request.getAttribute("articles");
   List<PurchaseOrder> commandes = (List<PurchaseOrder>) request.getAttribute("commandes");
   List<String> anomalies = (List<String>) request.getAttribute("annomalies");
   boolean hasAnomalies = true;
   if(anomalies == null)
   {
        hasAnomalies = false;
   } 
%>

<div class="row">
    <div class="col-md-12 mx-auto grid-margin stretch-card">
        <div class="card">
            <div class="card-body">
                <div class="row">
                    <div class="col-md-6">
                        <h4 class="card-title">Insertion de bon de livraison</h4>
                        <form class="forms-sample" action="./delivery-order-insertion" method="post">
                            <div class="form-group">
                                <label for="exampleInputEmail1">Date</label>
                                <input type="date" name="date" class="form-control" id="exampleInputEmail1"
                                       placeholder="">
                            </div>
                            <div class="form-group">
                                <label for="article"> Bon de commande </label>
                                <select class="form-select form-control-sm" name="boc">
                                    <% for (int i = 0; i < commandes.size(); i++) { %>
                                    <option value="<%= commandes.get(i).getIdPurchaseOrder() %>"><%= commandes.get(i).getReference() %> </option>
                                    <% } %>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="exampleInputUsername1">Nom livreur</label>
                                <input type="text" name="livreur" class="form-control" id="exampleInputUsername1">
                            </div>
                            <div class="form-group">
                                <label for="exampleInputUsername1">Contact livreur</label>
                                <input type="text" name="livreurContact" class="form-control"
                                       id="exampleInputUsername1">
                            </div>
                            <% if((String) request.getAttribute("error") != null) { %>
                            <p class="text-error"> <%= (String) request.getAttribute("error") %> </p>
                            <% } %>
                            <% if(anomalies == null) { %> 
                            <input type="submit"
                                   class="btn btn-gradient-primary me-2" value="Crée le bon de récéption"> 
                            <a href="./reception-list" class="btn btn-light" data-bs-toggle="modal" data-bs-target="#exampleModal">Cancel</a>
                            <% } %>
                        </form>
                    </div>
                    <div class="col-md-6">
                        <h4 class="card-title">Détails des articles livré</h4>
                        <table class="table table-no-border align-middle">
                            <thead>
                                <tr class="table-primary">
                                    <td>Designation</td>
                                    <td>Quantite</td>
                                    <td></td>
                                </tr>
                            </thead>
                            <tbody id="tbody">
                            </tbody>
                            <tr>
                                <td>
                                    <select class="form-select form-control-sm" name="article" id="article">
                                        <% for (int i = 0; i < articles.size(); i++) { %>
                                        <option value="<%= articles.get(i).getIdArticle() %>"> <%= articles.get(i).getDesignation() %> </option>
                                        <% } %>
                                    </select>
                                </td>
                                <td>
                                    <input type="number" class="form-control form-control-sm" name="quantite" id="quantite">
                                </td>
                                <td>
                                    <button class="btn btn-info" id="addArticleDetail">Ajouter</button>
                                </td>
                            </tr>
                        </table>
                        <% if(hasAnomalies && anomalies.size() > 0) { %>            
                        <div>
                            <div class="modal-body">
                                <% if (hasAnomalies) { %>
                                <p><strong>On a constaté les anomalies suivants</strong></p>
                                <ul id="detailsAnnomalie">
                                    <% for (String anomaly : anomalies) { %>
                                    <li><%= anomaly %></li>
                                    <% } %>
                                </ul>
                                <% } %>
                                <h5>Explication</h5>
                                <hr>
                                <textarea name="explication" placeholder="Vous devez décrire une explication ici" id="explication"
                                          class="form-control" cols="30" rows="7"></textarea>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-primary" id="addAnomalieDelivery">Valider</button>
                            </div>
                        </div>
                        <% } %>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>