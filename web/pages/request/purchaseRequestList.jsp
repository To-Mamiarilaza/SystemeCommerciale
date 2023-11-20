<%@page import="java.util.List"%>
<%@page import="model.purchase.PurchaseRequest"%>
<%@page import="model.base.Service"%>

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
                    <form action="./PurchaseRequestFilter" method="POST">
                        <div class="input-groups d-flex align-items-center">
                            <div class="form-group me-4">
                                <label for="">Service</label>
                                <select name="service" id=""
                                    class="form-control form-control-sm px-5 mt-2">
                                    <% if(request.getAttribute("services") != null) { 
                                    List<Service> services = (List<Service>)request.getAttribute("services");
                                    for(int  i = 0; i < services.size(); i++) {
                                    %>
                                    <option value="<%=services.get(i).getIdService() %>"><%=services.get(i).getService() %></option>
                                    <% } } %>
                                </select>
                            </div>
                            <div class="form-group me-4">
                                <label for="">Etat du demande</label>
                                <select name="status" id=""
                                    class="form-control form-control-sm px-5 mt-2">
                                    <option value="1">Non traite</option>
                                    <option value="2">Valide</option>
                                    <option value="0">Refuse</option>
                                </select>
                            </div>
                            <div>
                                <input type="submit" class="mx-2 btn btn-gradient-primary"
                                    value="Chercher">
                            </div>
                        </div>
                    </form>
                    <div>
                        <a href="./purchase-request-insertion" class="btn btn-gradient-primary">Envoyer
                            demande</a>
                    </div>
                </div>
                <div class="table-responsive mt-2">
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th> ID </th>
                                <th> Date </th>
                                <th> Service </th>
                                <th> Titre du demande </th>
                                <th> Etat </th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <% if(request.getAttribute("purchaseRequests") != null) { 
                            List<PurchaseRequest> purchaseRequests = (List<PurchaseRequest>)request.getAttribute("purchaseRequests");
                            for(int i = 0; i < purchaseRequests.size(); i++) {
                            %>
                            <tr>
                                <td><%=purchaseRequests.get(i).getIdPurchaseRequest() %></td>
                                <td><%=purchaseRequests.get(i).getSendingDate() %></td>
                                <td><%=purchaseRequests.get(i).getService().getService() %></td>
                                <td><%=purchaseRequests.get(i).getTitle() %></td>
                                <td>
                                    <label class="badge <%=purchaseRequests.get(i).getBadgeStatus() %> label-width"><%=purchaseRequests.get(i).getStatusString() %></label>
                                </td>
                                <td>
                                    <a href="./purchase-request-detail?idPurchaseRequest=<%=purchaseRequests.get(i).getIdPurchaseRequest() %>"><i class="mdi mdi-clipboard-text action-icon"></i></a>
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

