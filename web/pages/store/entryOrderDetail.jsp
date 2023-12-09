<%@page contentType="text/html; charset=UTF-8" %>
<%@page import="model.anomalie.*, model.entry.*, model.purchase.*, model.reception.*, model.article.*, model.base.*, model.supplier.Supplier, java.util.List" %>
<% 
    EntryOrder entry = (EntryOrder) request.getAttribute("entry");
    List<EntryOrderArticle> articleEntry = (List<EntryOrderArticle>) request.getAttribute("entryArticles");
        List<Anomalie> deliveryAnomalie = (List<Anomalie>) request.getAttribute("anomalyDelivery");
    List<DetailAnomalie> deliveryAnomalyDetails = (List<DetailAnomalie>) request.getAttribute("anomalyDeliveryDetails");
%>
<!-- MODAL FOR THE DELIVERY -->
<div class="modal fade" id="deliveryModal" tabindex="-1" aria-labelledby="deliveryModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="deliveryModalLabel">ANOMALIE DE BON D'ENTREE</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <form action="./reception-order-insertion">
                <div class="modal-body">
                    <% if(deliveryAnomalyDetails.size() > 0 && deliveryAnomalie.size() > 0) { %>
                    <p><strong>On a constate les anomalies suivants</strong></p>
                    <ul>
                        <% for(int i=0; i<deliveryAnomalyDetails.size();i++) { %>
                        <li> <%= deliveryAnomalyDetails.get(i).getDetail() %> </li>
                            <% } %>
                    </ul>
                    <h5>Explication</h5>
                    <hr>
                    <% for(int i=0; i<deliveryAnomalie.size();i++) { %>
                    <p> <strong> Explication : <%= deliveryAnomalie.get(i).getExplication() %> </strong> </p>
                    <% } %>
                    <% } else { out.print("<p><strong> Pas d'anomalies constatés </strong></p>"); } %>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                </div>
            </form>
        </div>
    </div>
</div>
<!-- MODAL FOR THE DELIVERY -->

<div class="page-header">
    <h3 class="page-title">
        <span class="page-title-icon bg-gradient-primary text-white me-2">
            <i class="mdi mdi-home"></i>
        </span> Bon d'entree
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
                            <h4 class="mb-4 text-primary">BON D' ENTREE</h4>
                            <div>
                                <a type="button" data-bs-toggle="modal" data-bs-target="#deliveryModal"
                                   class="text-danger action-icon">
                                    <i class="mdi mdi-information-outline"></i>
                                </a>
                            </div>
                        </div>
                        <dl class="row">
                            <dt class="col-sm-6 px-0 mb-3">Date</dt>
                            <dd class="col-sm-6 px-0 mb-3">
                                <%= entry.getDate() %>
                            </dd>

                            <dt class="col-sm-6 px-0 mb-3">Reference entree</dt>
                            <dd class="col-sm-6 px-0 mb-3">
                                BDE000<%= entry.getIdEntryOrder() %>
                            </dd>

                            <dt class="col-sm-6 px-0 mb-3">Reference reception</dt>
                            <dd class="col-sm-6 px-0 mb-3">
                                <%= entry.getReceptionOrder().getReference() %>
                            </dd>

                            <dt class="col-sm-6 px-0 mb-3">Nom du responsable</dt>
                            <dd class="col-sm-6 px-0 mb-3">
                                <%= entry.getResponsableName() %>
                            </dd>

                            <dt class="col-sm-6 px-0 mb-3">Contact du responsable</dt>
                            <dd class="col-sm-6 px-0 mb-3">
                                <%= entry.getResponsableContact() %>
                            </dd>
                        </dl>
                        <h6 class="text-primary">Detail du livraison</h6>
                        <hr class="text-primary">
                        <div class="col-md-5">
                            <ul>
                                <% for(int i=0; i<articleEntry.size();i++) { %>
                                <li>
                                    <div class="d-flex justify-content-between">
                                        <span><%= articleEntry.get(i).getArticle().getDesignation() %></span>
                                        <span><%= articleEntry.get(i).getQuantity() %> Unite</span>
                                    </div>
                                </li>
                                <% } %>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="mt-4 mx-2">
                <% if(entry.getStatus() ==1) { %>
                    <div class="div">
                        <a href="./entry-action?action=confirmer&idEntry=<%= entry.getIdEntryOrder() %>" class="btn btn-info me-4">Confirmer</a>
                        <a href="./entry-action?action=refuser&idEntry=<%= entry.getIdEntryOrder() %>" class="btn btn-danger me-4">Refuser</a>
                    </div>
                <% } %>
                    <a href="./entry-order-list" class="btn btn-light mt-3">Cancel</a>
                </div>
            </div>
        </div>
    </div>
</div>