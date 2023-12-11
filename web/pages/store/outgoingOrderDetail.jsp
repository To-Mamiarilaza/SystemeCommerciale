<%@page import="java.util.List, model.movement.out.*" %>
<%
    OutgoingOrder outgoingOrder = (OutgoingOrder) request.getAttribute("outgoingOrder");
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
    <div class="col-6 mx-auto grid-margin">
        <div class="card">
            <div class="card-body">
                <div class="row border border-1 p-4 ">
                    <div class="col-md-12">
                        <div class="d-flex justify-content-between">
                            <h4 class="mb-4 text-primary">BON DE SORTIE</h4>
                        </div>
                        <dl class="row">
                            <dt class="col-sm-6 px-0 mb-3">Date</dt>
                            <dd class="col-sm-6 px-0 mb-3">
                                <%= outgoingOrder.getDate() %>
                            </dd>

                            <dt class="col-sm-6 px-0 mb-3">Reference sortie</dt>
                            <dd class="col-sm-6 px-0 mb-3">
                                <%= outgoingOrder.getReference() %>
                            </dd>

                            <% if(outgoingOrder.getPurchaseOrderClient() != null) { %>
                            <dt class="col-sm-6 px-0 mb-3">Reference commande</dt>
                            <dd class="col-sm-6 px-0 mb-3">
                                <%= outgoingOrder.getPurchaseOrderDisplay() %>
                            </dd>
                            <% } %>
                            
                            <% if(outgoingOrder.getService() != null) { %>
                            <dt class="col-sm-6 px-0 mb-3">Département</dt>
                            <dd class="col-sm-6 px-0 mb-3">
                                <%= outgoingOrder.getService().getService() %>
                            </dd>
                            <% } %>

                            <dt class="col-sm-6 px-0 mb-3">Nom du responsable</dt>
                            <dd class="col-sm-6 px-0 mb-3">
                                <%= outgoingOrder.getResponsableName() %>
                            </dd>

                            <dt class="col-sm-6 px-0 mb-3">Contact du responsable</dt>
                            <dd class="col-sm-6 px-0 mb-3">
                                <%= outgoingOrder.getResponsableContact() %>
                            </dd>
                        </dl>
                            <h6 class="">Motif</h6>
                            <p><%= outgoingOrder.getMotif() %></p>
                        <h6 class="text-primary">Detail du sortie</h6>
                        <hr class="text-primary">
                        <div class="col-md-7">
                            <ul>
                                <% for(OutgoingOrderDetail detail : outgoingOrder.getDetails()) { %>
                                    <li>
                                        <div class="d-flex justify-content-between">
                                            <span><%= detail.getArticle().getDesignation() %></span>
                                            <span><%= detail.getQuantity() %> <%= detail.getArticle().getUnity().getName() %></span>
                                        </div>
                                    </li>
                                <% } %>
                            </ul>
                        </div>
                    </div>
                </div>

                <div class="mt-4 mx-2">
                    <% if(outgoingOrder.getStatus() == 1) { %>
                    <div class="div">
                        <a href="./accept-outgoing-order?idOutgoingOrder=<%= outgoingOrder.getIdOutgoingOrder() %>" class="btn btn-info me-4">Confirmer</a>
                        <a href="./refuse-outgoing-order?idOutgoingOrder=<%= outgoingOrder.getIdOutgoingOrder() %>" class="btn btn-danger me-4">Refuser</a>
                    </div>
                    <% } %>
                    <a href="./outgoing-order-list" class="btn btn-light mt-3">Cancel</a>
                </div>
            </div>
        </div>
    </div>
</div>