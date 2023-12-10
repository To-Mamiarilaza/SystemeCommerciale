<%@page contentType="text/html; charset=UTF-8" %>
<%@page import="model.purchase.*, model.reception.*, model.article.*, model.base.*, model.supplier.Supplier, java.util.List" %>
<% 
    List<ReceptionOrder> receptions = (List<ReceptionOrder>) request.getAttribute("receptions");
%>
<div class="page-header">
    <h3 class="page-title">
        <span class="page-title-icon bg-gradient-primary text-white me-2">
            <i class="mdi mdi-home"></i>
        </span> Reception
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
                <h4 class="card-title">Bon de livraison et leur receptions</h4>
                <div class="mt-4 d-flex align-items-center justify-content-between">
                    <form action="./reception-list" method="GET">
                        <div class="input-groups d-flex align-items-center">
                            <div class="form-group me-4">
                                <label for="">Date</label>
                                <input type="date" name="date" class="form-control form-control-sm px-5 mt-2">
                            </div>
                            <div class="form-group me-4">
                                <label for="">Etat du demande</label>
                                <select name="status" id=""
                                        class="form-control form-control-sm px-5 mt-2">
                                    <option value="1">En attente</option>
                                    <option value="2">Confirmé</option>
                                    <option value="0">Refusé</option>
                                </select>
                            </div>
                            <div>
                                <input type="submit" class="mx-2 btn btn-gradient-primary"
                                       value="Chercher">
                            </div>
                        </div>
                    </form>
                    <div>
                        <a href="./delivery-order-insertion" class="btn btn-gradient-primary">Inserer bon de livraison</a>
                    </div>
                </div>
                <div class="table-responsive mt-2">
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th> Date </th>
                                <th> BDL </th>
                                <th> Livreur </th>
                                <th> BDR </th>
                                <th> Responsable </th>
                                <th> Etat </th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <% for(int i = 0; i < receptions.size(); i++) { %>
                            <tr>
                                <td><%= receptions.get(i).getReceptionDate() %></td>
                                <td><%= receptions.get(i).getDeliveryOrder().getReference() %></td>
                                <td><%= receptions.get(i).getDeliveryOrder().getDeliversName() %></td>
                                <td><%= receptions.get(i).getReference() %></td>
                                <td><%= receptions.get(i).getResponsableName() %></td>
                                <td>
                                    <%= receptions.get(i).getStatusString(receptions.get(i).getStatus()) %>
                                </td>
                                <td>
                                    <a href="./reception-detail?idReception=<%= receptions.get(i).getIdReceptionOrder() %>"><i class="mdi mdi-clipboard-text action-icon"></i></a>
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

