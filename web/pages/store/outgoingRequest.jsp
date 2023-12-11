<%@page import="java.util.List, model.movement.out.ServiceRequest, model.purchase.ArticleQuantity, model.purchaseClient.*" %>
<%
    List<ServiceRequest> serviceRequests = (List<ServiceRequest>) request.getAttribute("serviceRequests");
    List<PurchaseOrderClient> purchases = (List<PurchaseOrderClient>) request.getAttribute("purchases");
%>
<div class="page-header">
    <h3 class="page-title">
        <span class="page-title-icon bg-gradient-primary text-white me-2">
            <i class="mdi mdi-home"></i>
        </span> Sortie de stock
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
                <h4 class="card-title">Demandes de sortie de stock</h4>
                <div class="row mt-5">
                    <div class="col-md-6">
                        <h5 class="text-primary">Besoins des départements</h5>
                        <hr>
                        <div class="row">
                            <% for(ServiceRequest serviceRequest : serviceRequests) { %>
                            <div class="col-md-6 mb-4 h-50">
                                <a href="./outgoing-order-insertion?idService=<%= serviceRequest.getService().getIdService() %>" class="no-style-link">
                                    <div class="border p-4 border-1">
                                        <h5><%= serviceRequest.getService().getService() %></h5>
                                        <ul class="text-black">
                                            <% for(ArticleQuantity articleQuantity : serviceRequest.getArticleQuantities()) { %>
                                            <li>
                                                <div class="d-flex justify-content-between">
                                                    <span><%= articleQuantity.getArticle().getDesignation() %></span><span><%= articleQuantity.getQuantity() %></span>
                                                </div>
                                            </li>
                                            <% } %>
                                        </ul>
                                    </div>
                                </a>
                            </div>
                            <% } %>

                        </div>
                    </div>
                    <div class="col-md-6">
                        <h5 class="text-primary">Besoins pour vente</h5>
                        <hr>
                        <div class="row">
                            <% for(PurchaseOrderClient purchase : purchases) { %>
                            <div class="col-md-6">
                                <a href="./outgoing-order-insertion?idPurchase=<%= purchase.getIdPurchaseOrderClient() %>" class="no-style-link">
                                    <div class="border p-4 border-1">
                                        <div class="d-flex justify-content-between">
                                            <h6><%= purchase.getIdString() %></h6>
                                            <h6 class="m-0 text-black"><%= purchase.getDateInsertion() %></h6>
                                        </div>
                                        <ul class="text-black">
                                            <% for(ArticleOrder order : purchase.getArticleOrder()) { %>
                                            <li>
                                                <div class="d-flex justify-content-between">
                                                    <span><%= order.getArticle().getDesignation() %></span><span><%= order.getQuantity() %></span>
                                                </div>
                                            </li>
                                            <% } %>
                                        </ul>
                                    </div>
                                </a>
                            </div>
                            <% } %>
                        </div>
                    </div>
                </div>


            </div>
        </div>
    </div>
</div>