<%@ page contentType="text/html; charset=UTF-8" %>
<%@page import="model.purchase.*, model.article.*, model.base.*, model.supplier.Supplier, java.util.List" %>
<% 
    Utilisateur utilisateur = (Utilisateur) request.getAttribute("utilisateur");
    PurchaseOrder purchaseOrder = (PurchaseOrder) request.getAttribute("purchaseOrder");
%>

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

<div class="row">
    <div class="col-9 grid-margin mx-auto">
        <div class="card">
            <div class="card-body">
                <h4 class="card-title">BON DE COMMANDE</h4>
                <h4 class="card-description text-small mb-2">Numero : <span
                        class="text-black"><%= purchaseOrder.getIdPurchaseOrder() %></span></h4>
                <h4 class="card-description text-small">Aujourd'hui</h4>
                <div class="row mt-3">
                    <div class="col-md-7">
                        <dl class="row">
                            <dt class="col-sm-6 px-0">Nom du fournisseur</dt>
                            <dd class="col-sm-6 px-0">
                                <%= purchaseOrder.getSupplier().getSupplierName() %>
                            </dd>

                            <dt class="col-sm-6 px-0">Contact du responsable</dt>
                            <dd class="col-sm-6 px-0">
                                <%= purchaseOrder.getSupplier().getResponsableContact() %>
                            </dd>

                            <dt class="col-sm-6 px-0">Mail</dt>
                            <dd class="col-sm-6 px-0">
                                <%= purchaseOrder.getSupplier().getMail() %>
                            </dd>

                            <dt class="col-sm-6 px-0">Adresse</dt>
                            <dd class="col-sm-6 px-0">
                                <%= purchaseOrder.getSupplier().getSupplierAddress() %>
                            </dd>

                            <dt class="col-sm-6 px-0 mt-4">Date de livraison</dt>
                            <dd class="col-sm-6 px-0 mt-4">
                                <strong><%= purchaseOrder.getDeliveryDate() %></strong>
                            </dd>
                            <dt class="col-sm-6 px-0">Mode de paiement</dt>
                            <dd class="col-sm-6 px-0">
                                <%= purchaseOrder.getPaymentMethod().getPaymentMethodName() %>
                            </dd>
                        </dl>
                    </div>

                    <div class="col-md-5">
                        <p class="quantity-detail mt-0 text-info"><strong>Categories de produits
                                vendues</strong></p>
                        <ul>
                            <% for(Category category : purchaseOrder.getSupplier().getOwnedCategoryList()) { %>
                            <li><%= category.getDesignation() %></li>
                                <% } %>
                        </ul>
                    </div>

                    <div class="payment-condition mx-3 px-0 col-md-6">
                        <h4 class="mx-0 my-0 text-info">Condition de payement</h4>
                        <div class="mt-1">
                            <table class="table table-no-border">
                                <thead></thead>
                                <tbody>
                                    <% for(PaymentCondition paymentCondition : purchaseOrder.getPaymentConditions()) { %>
                                    <tr class="py-1">
                                        <td><i class="mdi text-info mdi-arrow-right-bold"></i></td>
                                        <td class="px-0"><%= paymentCondition.getPercentage() %> %</td>
                                        <td class="px-0"><strong><span class="me-3"><%= paymentCondition.getPaymentDate() %></span></strong></td>
                                    </tr>
                                    <% } %>
                                </tbody>
                            </table>
                        </div>
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
                        <% for(InvoiceLineItem lineItem : purchaseOrder.getLineItems()) { %>
                        <tr>
                            <td><%= lineItem.getArticle().getDesignation() %></td>
                            <td><%= lineItem.getUnitPriceString() %></td>
                            <td><%= lineItem.getQuantity() %></td>
                            <td><%= lineItem.getTva() %> %</td>
                            <td class="text-right"><%= lineItem.getTVAAmountString() %></td>
                            <td class="text-right"><%= lineItem.getHTAmountString() %></td>
                            <td class="text-right"><%= lineItem.getTTCAmountString() %></td>
                        </tr>
                        <% } %>

                        <!-- TOTAL ROW -->

                        <tr>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td class="text-right">TOTAL</td>
                            <td class="text-right text-success"><%= purchaseOrder.getTotalTVAString() %></td>
                            <td class="text-right text-success"><%= purchaseOrder.getTotalHTString() %></td>
                            <td class="text-right text-success"><%= purchaseOrder.getTotalTTCString() %></td>
                            <td></td>
                        </tr>
                    </tbody>
                </table>
                <div class="mt-4">
                    <p><strong>Arrete le present proforma a la somme de :</strong> <br> <span><%= purchaseOrder.getTotalTTCLetter() %> ARIARY</span></p>
                </div>
                <div class="mt-3">
                    <% if(purchaseOrder.getStatus() == 1 && utilisateur.getIsAdmin() == true) { %>
                    <a href="./purchase-order-validation?idPurchaseOrder=<%= purchaseOrder.getIdPurchaseOrder() %>&status=2" class="btn btn-gradient-danger px-5 me-2">Valide</a>
                    <a href="./purchase-order-validation?idPurchaseOrder=<%= purchaseOrder.getIdPurchaseOrder() %>&status=0" class="btn btn-gradient-success px-5 me-2">Refuse</a>
                    <br>
                    <% } %>
                    <a href="./purchase-order-list" class="btn btn-light mt-3">Cancel</a>
                    <a href="./PdfPurchaseOrder?idPurchaseOrder=<%= purchaseOrder.getIdPurchaseOrder() %>" class="btn btn-info mt-3">Voir pdf</a>
                </div>
            </div>
        </div>
    </div>
</div>

