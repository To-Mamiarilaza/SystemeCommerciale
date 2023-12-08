<%@page import="java.util.List, model.movement.out.ServiceRequest, model.purchase.ArticleQuantity" %>
<%
    List<ServiceRequest> serviceRequests = (List<ServiceRequest>) request.getAttribute("serviceRequests");
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
                            <div class="col-md-6">
                                <a href="./outgoing-order-insertion" class="no-style-link">
                                    <div class="border p-4 border-1">
                                        <div class="d-flex justify-content-between">
                                            <h6>BOC0001</h6>
                                            <h6 class="m-0 text-black">10-12-2023</h6>
                                        </div>
                                        <ul class="text-black">
                                            <li>
                                                <div class="d-flex justify-content-between">
                                                    <span>Ordinateur</span><span>8</span>
                                                </div>
                                            </li>
                                            <li>
                                                <div class="d-flex justify-content-between">
                                                    <span>Cahier spirale</span><span>13</span>
                                                </div>
                                            </li>
                                        </ul>
                                    </div>
                                </a>
                            </div>

                            <div class="col-md-6">
                                <a href="./outgoing-order-insertion" class="no-style-link">
                                    <div class="border p-4 border-1">
                                        <div class="d-flex justify-content-between">
                                            <h6>BOC0001</h6>
                                            <h6 class="m-0 text-black">10-12-2023</h6>
                                        </div>
                                        <ul class="text-black">
                                            <li>
                                                <div class="d-flex justify-content-between">
                                                    <span>Ordinateur</span><span>8</span>
                                                </div>
                                            </li>
                                            <li>
                                                <div class="d-flex justify-content-between">
                                                    <span>Cahier spirale</span><span>13</span>
                                                </div>
                                            </li>
                                            <li>
                                                <div class="d-flex justify-content-between">
                                                    <span>Cahier spirale</span><span>13</span>
                                                </div>
                                            </li>
                                        </ul>
                                    </div>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>


            </div>
        </div>
    </div>
</div>