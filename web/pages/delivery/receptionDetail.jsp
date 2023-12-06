<%@page contentType="text/html; charset=UTF-8" %>
<%@page import="model.purchase.*, model.reception.*, model.article.*, model.base.*, model.supplier.Supplier, java.util.List" %>
<% 
    ReceptionOrder reception = (ReceptionOrder) request.getAttribute("reception");
    List<ReceptionArticleDetails> receptionArticles = (List<ReceptionArticleDetails>) request.getAttribute("receptionArticle");
    List<DeliveryArticleDetails> deliveryArticles = (List<DeliveryArticleDetails>) request.getAttribute("deliveryArticle");
%>
<div class="page-header">
    <h3 class="page-title">
        <span class="page-title-icon bg-gradient-primary text-white me-2">
            <i class="mdi mdi-home"></i>
        </span> Demandes d'achat
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
                <h4 class="card-title">LIVRASON ET RECEPTION</h4>
                <div class="row">
                    <div class="col-md-6 mt-3 px-5">
                        <div class="row border border-1 p-4 ">
                            <div class="col-md-12">
                                <div class="d-flex justify-content-between">
                                    <h4 class="mb-4 text-primary">BON DE LIVRAISON</h4>
                                    <div>
                                        <a type="button" data-bs-toggle="modal" data-bs-target="#deliveryModal"
                                           class="text-danger action-icon"> <i class="mdi mdi-information-outline"></i>
                                        </a>
                                    </div>
                                </div>
                                <dl class="row">
                                    <dt class="col-sm-6 px-0 mb-3">Date</dt>
                                    <dd class="col-sm-6 px-0 mb-3">
                                        <%= reception.getDeliveryOrder().getDelivery_date() %>
                                    </dd>

                                    <dt class="col-sm-6 px-0 mb-3">Reference livraison</dt>
                                    <dd class="col-sm-6 px-0 mb-3">
                                        <%= reception.getDeliveryOrder().getReference() %>
                                    </dd>

                                    <dt class="col-sm-6 px-0 mb-3">Reference commande</dt>
                                    <dd class="col-sm-6 px-0 mb-3">
                                        BOC000<%= reception.getDeliveryOrder().getPurchaseOrder().getIdPurchaseOrder() %>
                                    </dd>

                                    <dt class="col-sm-6 px-0 mb-3">Livreur</dt>
                                    <dd class="col-sm-6 px-0 mb-3">
                                        <%= reception.getDeliveryOrder().getDeliversName() %>
                                    </dd>

                                    <dt class="col-sm-6 px-0 mb-3">Contact livreur</dt>
                                    <dd class="col-sm-6 px-0 mb-3">
                                        <%= reception.getDeliveryOrder().getDeliversContact() %>
                                    </dd>
                                </dl>
                                <h6 class="text-primary">Detail du livraison</h6>
                                <hr class="text-primary">
                                <div class="col-md-5">
                                    <ul>
                                        <% for(int i=0; i<receptionArticles.size(); i++) { %>
                                        <li>
                                            <div class="d-flex justify-content-between">
                                                <span><%= receptionArticles.get(i).getArticle().getDesignation() %></span>
                                                <span><%= receptionArticles.get(i).getQuantity() %> Unite</span>
                                            </div>
                                        </li>
                                        <% } %>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-6 mt-3 px-5">
                        <div class="row border border-1 p-4">
                            <div class="col-md-12">
                                <div class="d-flex justify-content-between">
                                    <h4 class="mb-4 text-primary">BON DE RECEPTION</h4>
                                    <div>
                                        <a type="button" data-bs-toggle="modal" data-bs-target="#receptionModal"
                                           class="text-danger action-icon"> <i class="mdi mdi-information-outline"></i>
                                        </a>
                                    </div>
                                </div>
                                <dl class="row">
                                    <dt class="col-sm-6 px-0 mb-3">Date</dt>
                                    <dd class="col-sm-6 px-0 mb-3">
                                        <%= reception.getReceptionDate() %>
                                    </dd>

                                    <dt class="col-sm-6 px-0 mb-3">Reference reception</dt>
                                    <dd class="col-sm-6 px-0 mb-3">
                                        <%= reception.getReference() %>
                                    </dd>

                                    <dt class="col-sm-6 px-0 mb-3">Responsable</dt>
                                    <dd class="col-sm-6 px-0 mb-3">
                                        <%= reception.getResponsableName() %>
                                    </dd>

                                    <dt class="col-sm-6 px-0 mb-3">Contact Responsable</dt>
                                    <dd class="col-sm-6 px-0 mb-3">
                                        <%= reception.getResponsableContact() %>
                                    </dd>
                                </dl>
                                <h6 class="text-primary">Detail du reception</h6>
                                <hr class="text-primary">
                                <div class="col-md-5">
                                    <ul>
                                        <% for(int i=0; i<deliveryArticles.size(); i++) { %>
                                        <li>
                                            <div class="d-flex justify-content-between">
                                                <span><%= deliveryArticles.get(i).getArticle().getDesignation() %></span>
                                                <span><%= deliveryArticles.get(i).getQuantity() %> Unite</span>
                                            </div>
                                        </li>
                                        <% } %>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="mt-4 mx-2">
                        <div class="div">
                            <a href="./get-into-store?idReception=<%= reception.getIdReceptionOrder() %>&idDelivery=<%= reception.getDeliveryOrder().getIdSupplierDeliveryOrder() %>" class="btn btn-info me-4">Envoyer au magasin</a>
                            <a href="./reception-list" class="btn btn-danger me-4">Annuler</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>