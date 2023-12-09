<%@page import="java.util.List, model.movement.out.*, java.time.LocalDate" %>
<%
    List<OutgoingOrder> orders = (List<OutgoingOrder>) request.getAttribute("orders");
    LocalDate now = LocalDate.now();
%>
<div class="page-header">
    <h3 class="page-title">
        <span class="page-title-icon bg-gradient-primary text-white me-2">
            <i class="mdi mdi-home"></i>
        </span> Bon de sortie
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
                <h4 class="card-title">Listes des Bon de sortie</h4>
                <div class="mt-4 d-flex align-items-center justify-content-between">
                    <form action="./outgoing-order-list" method="GET">
                        <div class="input-groups d-flex align-items-center">
                            <div class="form-group me-4">
                                <label for="">Date</label>
                                <input type="date" value="<%= now %>" name="date" class="form-control form-control-sm px-5 mt-2">
                            </div>
                            <div class="form-group me-4">
                                <label for="">Etat</label>
                                <select name="status" id="" class="form-control form-control-sm px-5 mt-2">
                                    <option value="1">En attente</option>
                                    <option value="10">Validé</option>
                                    <option value="0">Refusé</option>
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
                                <th> Date </th>
                                <th> Reference </th>
                                <th> Responsable </th>
                                <th> Type </th>
                                <th> Departement </th>
                                <th> Bon de commande </th>
                                <th> Etat </th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <% for(OutgoingOrder order : orders) { %>
                            <tr>
                                <td><%= order.getDate() %></td>
                                <td><%= order.getReference() %></td>
                                <td><%= order.getResponsableName() %></td>
                                <td><%= order.getOrderType() %></td>
                                <td><%= order.getServiceDisplay() %></td>
                                <td><%= order.getPurchaseOrderDisplay() %></td>
                                <td>
                                    <label class="badge badge-<%= order.getStatusClass() %> label-width"><%= order.getStatusDisplay() %></label>
                                </td>
                                <td>
                                    <a href="./outgoing-order-detail?idOutgoingOrder=<%= order.getIdOutgoingOrder() %>"><i
                                            class="mdi mdi-clipboard-text action-icon"></i></a>
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