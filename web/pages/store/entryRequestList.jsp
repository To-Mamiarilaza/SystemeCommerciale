<%@page contentType="text/html; charset=UTF-8" %>
<%@page import="model.entry.*, model.purchase.*, model.reception.*, model.article.*, model.base.*, model.supplier.Supplier, java.util.List" %>
<% 
    List<ReceptionOrder> receptions = (List<ReceptionOrder>) request.getAttribute("receptions");
%>
<div class="page-header">
    <h3 class="page-title">
        <span class="page-title-icon bg-gradient-primary text-white me-2">
            <i class="mdi mdi-home"></i>
        </span> Demande entree en magasin
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
    <div class="col-12 grid-margin">
        <div class="card">
            <div class="card-body">
                <h4 class="card-title">Listes des demandes d'entrees</h4>
                <div class="mt-4 d-flex align-items-center justify-content-between">
                    <form action="./entry-request-list" method="GET">
                        <div class="input-groups d-flex align-items-center">
                            <div class="form-group me-4">
                                <label for="">Date</label>
                                <input type="date" name="date" class="form-control form-control-sm px-5 mt-2">
                            </div>
                            <div class="form-group me-4">
                                <label for="">Etat du demande</label>
                                <select name="status" id="" class="form-control form-control-sm px-5 mt-2">
                                    <option value="2">En attente</option>
                                    <option value="5">Confirmer</option>
                                    <option value="0">Refuser</option>
                                </select>
                            </div>
                            <div>
                                <input type="submit" class="mx-2 btn btn-gradient-primary" value="Chercher">
                            </div>
                        </div>
                    </form>
                </div>
                <div class="table-responsive mt-2">
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th> Date demande </th>
                                <th> Bon de reception </th>
                                <th> Etat </th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <% for(int i=0; i<receptions.size(); i++) { %>
                            <tr>
                                <td><%= receptions.get(i).getReceptionDate() %></td>
                                <td><%= receptions.get(i).getReference() %></td>
                                <td>
                                    <%= new EntryOrder().getStatusString(receptions.get(i).getStatus()) %>
                                </td>
                                <% if(receptions.get(i).getStatus() == 2) { %>
                                <td>
                                    <a href="./entry-order-insertion?idReception=<%= receptions.get(i).getIdReceptionOrder() %>"><i
                                            class="mdi mdi-clipboard-text action-icon"></i></a>
                                </td>
                                <% } %>
                            </tr>
                            <% } %>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>