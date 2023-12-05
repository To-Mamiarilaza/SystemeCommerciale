<%@ page contentType="text/html; charset=UTF-8" %>
<%@page import="model.purchase.*, model.article.*, model.base.*, model.supplier.Supplier, java.util.List" %>
<% 
    Utilisateur utilisateur = (Utilisateur) request.getAttribute("utilisateur");
    Proforma proforma = (Proforma) request.getAttribute("proforma");
%>
<div class="page-header">
    <h3 class="page-title">
        <span class="page-title-icon bg-gradient-primary text-white me-2">
            <i class="mdi mdi-home"></i>
        </span> PROFORMA
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
                <h4 class="card-title">Detail du proforma</h4>
                <h4 class="card-description text-small"><%= proforma.getDate() %></h4>
                <div class="row mt-3">
                    <div class="col-md-7">
                        <dl class="row">
                            <dt class="col-sm-6 px-0">Nom du fournisseur</dt>
                            <dd class="col-sm-6 px-0">
                                <%= proforma.getSupplier().getSupplierName() %>
                            </dd>

                            <dt class="col-sm-6 px-0">Contact du responsable</dt>
                            <dd class="col-sm-6 px-0">
                                <%= proforma.getSupplier().getResponsableContact() %>
                            </dd>

                            <dt class="col-sm-6 px-0">Mail</dt>
                            <dd class="col-sm-6 px-0">
                                <%= proforma.getSupplier().getMail() %>
                            </dd>

                            <dt class="col-sm-6 px-0">Adresse</dt>
                            <dd class="col-sm-6 px-0">
                                <%= proforma.getSupplier().getSupplierAddress() %>
                            </dd>
                        </dl>
                    </div>
                    <div class="col-md-5">
                        <p class="quantity-detail mt-0"><strong>Categories de produits
                                vendues</strong></p>
                        <ul>
                            <% for(Category category : proforma.getSupplier().getOwnedCategoryList()) { %>
                            <li><%= category.getDesignation() %></li>
                            <% } %>
                        </ul>
                    </div>
                </div>
                <table class="table mt-3">
                    <thead>
                        <tr class="table-primary">
                            <th>Designation</th>
                            <th>Prix Unitaire</th>
                            <th>Quantite</th>
                            <th>TVA</th>
                            <th class="text-right px-4">Montant TVA</th>
                            <th class="text-right px-4">HT</th>
                            <th class="text-right px-4">TTC</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% for(InvoiceLineItem lineItem : proforma.getInvoiceLineItems()) { %>
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
                            <td class="text-right text-success"><%= proforma.getMontantTVATotalString() %></td>
                            <td class="text-right text-success"><%= proforma.getMontantHTTotalString() %></td>
                            <td class="text-right text-success"><%= proforma.getMontantTTCTotalString() %></td>
                        </tr>
                    </tbody>
                </table>
                <div class="mt-4">
                    <p><strong>Arrete le present proforma a la somme de :</strong> <br> <span><%= proforma.getTotalTTCToLetter() %> ARIARY</span></p>
                </div>
                <div class="mt-3">
                    <% if(utilisateur.inFinanceService()) { %>
                    <a href="./purchase-order-insertion?idSupplier=<%= proforma.getSupplier().getIdSupplier() %>"
                        class="btn btn-gradient-primary me-5">Crée le bon de commande</a>
                    <% } %>
                    <a  href="./article-price-insertion" class="btn btn-light">Cancel</a>
                </div>
            </div>
        </div>
    </div>
</div>
