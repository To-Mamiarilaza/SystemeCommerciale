<%@ page contentType="text/html; charset=UTF-8" %>
<%@page import="model.reception.*, model.article.*, model.base.*, model.supplier.Supplier, java.util.List" %>
<%
   SupplierDeliveryOrder delivery = (SupplierDeliveryOrder) request.getAttribute("delivery");
   ReceptionOrder reception = (ReceptionOrder) request.getAttribute("reception");
   List<ArticleDetails> articles = reception.getListeArticles();
   List<String> anomalies = (List<String>) request.getAttribute("anomalies");
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
                        <h4 class="card-title">Insertion du bon de reception</h4>
                        <form action="./reception-order-insertion" class="forms-sample" method="post">
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
                                <tr id='arti_<%= i %>'>
                                    <td><%= articles.get(i).getArticle().getDesignation() %></td>
                                    <td> <%= articles.get(i).getQuantity() %> </td>
                                    <td>
                                        <i class="mdi mdi-close action-icon text-danger" onclick='deleteArticles(<%= i %>)'></i>
                                    </td>
                                </tr>
                                <% } %>                              

                            </tbody>
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
                                <button type="button" class="btn btn-primary" id="addAnomalieReception">Valider</button>
                            </div>
                        </div>
                        <% } %>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>