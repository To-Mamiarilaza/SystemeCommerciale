<%@page import="model.purchaseClient.*, model.article.*, model.sale.*, java.util.List" %>

<div class="page-header">
    <h3 class="page-title">
        <span class="page-title-icon bg-gradient-primary text-white me-2">
            <i class="mdi mdi-home"></i>
        </span> Bon de commande client
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
                <h4 class="card-title">Listes des bon de commandes</h4>
                <div class="mt-4 d-flex align-items-center justify-content-between">
                    <form action="./PurchaseRequestFilter" method="POST">
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
                                    <option value="2">Validé</option>
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
                        <a href="./client-purchase-order-insertion" class="btn btn-gradient-primary">Inserer bon de commande</a>
                    </div>
                </div>
                <div class="table-responsive mt-2">
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th> Date </th>
                                <th> Reference </th>
                                <th> Nom client </th>
                                <th> Addresse </th>
                                <th> Etat </th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <% if(request.getAttribute("purchaseOrderClients") != null) { 
                            List<PurchaseOrderClient> purchaseOrders = (List<PurchaseOrderClient>)request.getAttribute("purchaseOrderClients");
                            for(int i = 0; i < purchaseOrders.size(); i++) {
                            %>
                            <tr>
                                <td><%=purchaseOrders.get(i).getDateInsertion() %></td>
                                <td><%=purchaseOrders.get(i).getReference() %></td>
                                <td><%=purchaseOrders.get(i).getClientName() %></td>
                                <td><%=purchaseOrders.get(i).getAdresse() %></td>
                                <td>
                                    <label class="badge badge-<%=purchaseOrders.get(i).getBadgeStatus() %> label-width"><%=purchaseOrders.get(i).getStatusLetter() %></label>
                                </td>
                                <td>
                                    <a href="./client-purchase-order-detail?idPurchaseOrderClient=<%=purchaseOrders.get(i).getIdPurchaseOrderClient() %>"><i class="mdi mdi-clipboard-text action-icon"></i></a>
                                </td>
                            </tr>
                            <% } } %>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

