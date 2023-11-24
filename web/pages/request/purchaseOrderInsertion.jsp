<%@ page contentType="text/html; charset=UTF-8" %>
<%@page import="model.purchase.*, model.article.*, model.base.*, model.supplier.Supplier, java.util.List" %>
<% 
    Utilisateur utilisateur = (Utilisateur) request.getAttribute("utilisateur");
    Proforma proforma = (Proforma) request.getAttribute("proforma");
    
    List<PaymentMethod> paymentMethods = (List<PaymentMethod>) request.getAttribute("paymentMethods");
%>

<!-- MODAL FOR MANAGING ARTICLE QUANTITY -->

<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel"
    aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Gestion des quantites par articles</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal"
                    aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <dl class="row mx-2">
                    <dt class="col-sm-4 px-0">Article</dt>
                    <dd class="col-sm-6 px-0">
                        Cache Bouche
                    </dd>

                    <dt class="col-sm-4 px-0 mt-3">Quantity</dt>
                    <dd class="col-sm-6 px-0 mt-3">
                        20 Litre
                    </dd>
                </dl>
                <div class="px-3">
                    <h6 class="">Listes des services sources</h6>
                    <div class="form-group mb-2 px-3 mt-3">
                        <ul>
                            <li>
                                <div class="d-flex align-items-center justify-content-between mx-5">
                                    <div>
                                        Informatique
                                    </div>
                                    <div>
                                        30 Litre
                                    </div>
                                    <div class="">
                                        <a href="" class="text-danger">
                                            <i class="mdi mdi-close"></i>
                                        </a>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="d-flex align-items-center justify-content-between mx-5">
                                    <div>
                                        Informatique
                                    </div>
                                    <div>
                                        30 Litre
                                    </div>
                                    <div class="">
                                        <a href="" class="text-danger">
                                            <i class="mdi mdi-close"></i>
                                        </a>
                                    </div>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary"
                    data-bs-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary">Save changes</button>
            </div>
        </div>
    </div>
</div>

<!-- MODAL FOR MANAGING ARTICLE QUANTITY -->

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
                <h4 class="card-description text-small mb-2">Numero : </h4>
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
                            <div class="row mt-5">
                                <div class="col-sm-6 px-0">
                                    <div class="form-group">
                                        <label for="">Date de livraison</label>
                                        <input type="number" id="deliveryDate"
                                            class="mt-1 form-control form-control-sm"
                                            placeholder="jour apres">
                                    </div>
                                </div>
                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <label for="">Mode de payement</label>
                                        <select name="" id="paymentMethod" class="mt-1 form-control form-control-sm"
                                            id="">
                                            <% for(PaymentMethod paymentMethod : paymentMethods) { %>
                                            <option value="<%= paymentMethod.getIdPaymentMethod() %>"><%= paymentMethod.getPaymentMethodName() %></option>
                                            <% } %>
                                        </select>
                                    </div>
                                </div>
                            </div>
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

                    <div class="payment-condition mx-3 px-0 col-md-6">
                        <h4 class="mx-0">Condition de payement</h4>
                        <div class="px-4 mt-4">
                            <div class="row align-items-end">
                                <div class="col-sm-4 px-0">
                                    <div class="form-group mb-0">
                                        <label for="">Part en %</label>
                                        <input type="number" id="part"
                                            class="mt-1 form-control form-control-sm"
                                            placeholder="30" min="0">
                                    </div>
                                </div>
                                <div class="offset-sm-1 col-sm-4 px-0">
                                    <div class="form-group mb-0">
                                        <label for="">Jour apres</label>
                                        <input type="number" id="nbJour"
                                            class="mt-1 form-control form-control-sm"
                                            placeholder="10" min="0">
                                    </div>
                                </div>
                                <div class="col-sm-3">
                                    <input type="button" onclick="addNewPaymentMethod()" class="btn btn-gradient-danger"
                                        value="Ajouter">
                                </div>
                            </div>
                        </div>
                        <div class="mt-3">
                            <table class="table table-no-border">
                                <thead></thead>
                                <tbody id="paymentMethodList">
                                    
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <table class="table mt-5">
                    <thead>
                        <tr class="table-primary">
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
                            <td class="text-right text-success"><%= proforma.getTotalTVAString() %></td>
                            <td class="text-right text-success"><%= proforma.getTotalHTString() %></td>
                            <td class="text-right text-success"><%= proforma.getTotalTTCString() %></td>
                        </tr>
                    </tbody>
                </table>
                <div class="mt-4">
                    <p><strong>Arrete le present bon de commande a la somme de :</strong> <br> <span><%= proforma.getTotalTTCToLetter() %> ARIARY</span></p>
                </div>
                <div class="mt-3">
                    <a type="button" onclick="savePurchaseOrder(<%= proforma.getSupplier().getIdSupplier() %>)"
                        class="btn btn-gradient-primary me-5">Valider</a>
                    <a href="./proforma-detail" class="btn btn-light">Cancel</a>
                </div>
            </div>
        </div>
    </div>
</div>