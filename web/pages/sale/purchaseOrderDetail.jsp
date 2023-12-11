<%@page import="model.purchaseClient.*, model.sale.*" %>

<div class="page-header">
    <h3 class="page-title">
        <span class="page-title-icon bg-gradient-primary text-white me-2">
            <i class="mdi mdi-home"></i>
        </span> BON DE COMMANDE
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
<% if(request.getAttribute("purchaseOrderClient") != null) { 
PurchaseOrderClient purchaseOrderClient = (PurchaseOrderClient)request.getAttribute("purchaseOrderClient");
%>
<div class="row">
    <div class="col-10 grid-margin mx-auto">
        <div class="card">
            <div class="card-body">
                <h4 class="card-title">BON DE COMMANDE</h4>
                <h4 class="card-description text-small mb-2">Numero : <span
                        class="text-black"><%=purchaseOrderClient.getReference() %></span></h4>
                <div class="row mt-3">
                    <div class="col-md-7">
                        <dl class="row">
                            <dt class="col-sm-6 px-0">Nom du fournisseur</dt>
                            <dd class="col-sm-6 px-0">
                                HUILE DE BONGOLAVA
                            </dd>

                            <dt class="col-sm-6 px-0">Email</dt>
                            <dd class="col-sm-6 px-0">
                                huileBongolava@gmail.com
                            </dd>

                            <dt class="col-sm-6 px-0">Contact</dt>
                            <dd class="col-sm-6 px-0">
                                +261 34 12 356 27
                            </dd>

                            <dt class="col-sm-6 px-0">Adresse</dt>
                            <dd class="col-sm-6 px-0">
                                Tsiroanomandidy
                            </dd>

                            <dt class="col-sm-6 px-0 mt-4">Date de livraison</dt>
                            <dd class="col-sm-6 px-0 mt-4">
                                <%=purchaseOrderClient.getDeliveryDate() %>
                            </dd>
                            <dt class="col-sm-6 px-0">Mode de paiement</dt>
                            <dd class="col-sm-6 px-0">
                                <%=purchaseOrderClient.getPaymentMethod().getPaymentMethodName() %>
                            </dd>
                        </dl>
                    </div>

                    <div class="col-md-5">
                        <dl class="row">
                            <dt class="col-sm-6 px-0">Nom du client</dt>
                            <dd class="col-sm-6 px-0">
                                <%=purchaseOrderClient.getClientName() %>
                            </dd>

                            <dt class="col-sm-6 px-0">Addresse</dt>
                            <dd class="col-sm-6 px-0">
                                <%=purchaseOrderClient.getAdresse() %>
                            </dd>

                            <dt class="col-sm-6 px-0">Contact</dt>
                            <dd class="col-sm-6 px-0">
                                <%=purchaseOrderClient.getContactDelivery() %>
                            </dd>
                        </dl>
                    </div>

                </div>
                <table class="table mt-5">
                    <thead>
                        <tr class="table-info">
                            <th>Designation</th>
                            <th>Prix Unitaire</th>
                            <th>Quantite</th>
                            <th>TVA</th>
                            <th class="text-right px-4">Montant TVA</th>
                            <th class="text-right px-4">HT</th>
                            <th class="text-right px-4">TTC</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <% for(int i = 0; i < purchaseOrderClient.getArticleOrder().size(); i++) { %>
                        <tr>
                            <td><%=purchaseOrderClient.getArticleOrder().get(i).getArticle().getDesignation() %></td>
                            <td><%= purchaseOrderClient.getArticleOrder().get(i).getUnitPrice() %> AR</td>
                            <td><%=purchaseOrderClient.getArticleOrder().get(i).getQuantity() %></td>
                            <td><%= purchaseOrderClient.getArticleOrder().get(i).getArticle().getTva() %> %</td>
                            <td class="text-right"><%= purchaseOrderClient.getArticleOrder().get(i).getTvaAmount() %>AR</td>
                            <td class="text-right"><%= purchaseOrderClient.getArticleOrder().get(i).getHtAmount() %> AR</td>
                            <td class="text-right"><%= purchaseOrderClient.getArticleOrder().get(i).getTtcAmount() %> AR</td>
                        </tr>
                        <% } %>

                        <!-- TOTAL ROW -->
                        <tr>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td class="text-right">TOTAL</td>
                            <td class="text-right text-success"><%= purchaseOrderClient.getTvaTotal() %> AR</td>
                            <td class="text-right text-success"><%= purchaseOrderClient.getHtTotal() %> AR</td>
                            <td class="text-right text-success"><%= purchaseOrderClient.getTtcTotal() %> AR</td>
                            <td></td>
                        </tr>
                    </tbody>
                </table>
                <div class="mt-4">
                    <p><strong>Arrete le present proforma a la somme de :</strong> <br> <span> <%= purchaseOrderClient.getTtcTotalLetter() %> ARIARY</span></p>
                </div>
                <div class="mt-3 d-flex">
                    <% if(purchaseOrderClient.getStatus() == 5) { %>
                        <a href="./client-trait-purchase-order?idPurchaseOrderClient=<%=purchaseOrderClient.getIdPurchaseOrderClient() %>&status=5" class="btn btn-gradient-info mt-3 me-3">Demander magasin</a>
                        <a href="./client-purchase-order-list" class="btn btn-gradient-light mt-3">Cancel</a>
                    <% } else if(purchaseOrderClient.getStatus() == 10) { %>
                        <a href="./client-purchase-order-list" class="btn btn-gradient-light mt-3">En attente validation magasin</a>
                    <% } else if(purchaseOrderClient.getStatus() == 15) { %>
                        <a href="./client-trait-purchase-order?idPurchaseOrderClient=<%=purchaseOrderClient.getIdPurchaseOrderClient() %>&status=15&idHelp=1" class="btn btn-gradient-info mt-3 me-3">Facturer</a>
                        <a href="./client-purchase-order-list" class="btn btn-gradient-light mt-3">Cancel</a>
                    <% } else if(purchaseOrderClient.getStatus() == 20) { %>
                        <a href="./client-trait-purchase-order?idPurchaseOrderClient=<%=purchaseOrderClient.getIdPurchaseOrderClient() %>&status=20&idHelp=1" class="btn btn-gradient-info mt-3 me-3">Livrer</a>
                        <a href="./client-purchase-order-list" class="btn btn-gradient-light mt-3">Cancel</a>
                        <a href="./client-trait-purchase-order?idPurchaseOrderClient=<%=purchaseOrderClient.getIdPurchaseOrderClient() %>&status=15&idHelp=0" class="btn text-info mt-3 mx-5">Voir le facture</a>
                    <% } else if(purchaseOrderClient.getStatus() == 25) { %>
                        <a href="./client-trait-purchase-order?idPurchaseOrderClient=<%=purchaseOrderClient.getIdPurchaseOrderClient() %>&status=15&idHelp=0" class="btn text-info mt-3 mx-5">Voir le facture</a>
                        <a href="./client-trait-purchase-order?idPurchaseOrderClient=<%=purchaseOrderClient.getIdPurchaseOrderClient() %>&status=20&idHelp=0" class="btn text-info mt-3 ">Voir le bon de livraison</a>
                        <a href="./client-purchase-order-list" class="btn btn-gradient-light mt-3">Cancel</a>
                    <% } %>
                </div>
            </div>
        </div>
    </div>
</div>

<% } %>