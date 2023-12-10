<%@page import="java.util.List"%>
<%@page import="model.purchase.PurchaseRequest"%>
<%@page import="model.base.Service"%>
<%@page import="model.movement.out.*, model.dept.*, model.reception.*, model.article.*, model.base.*, model.supplier.Supplier, java.util.List" %>
<%@page contentType="text/html; charset=UTF-8" %>
<%
    List<DeptReceptionArticle> deptReceptions = (List<DeptReceptionArticle>) request.getAttribute("deptArticles");
%>
<!-- MODAL FOR THE DELIVERY -->
<div class="modal fade" id="deliveryModal" tabindex="-1" aria-labelledby="deliveryModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="deliveryModalLabel">ANOMALIE ACCUSE DE RECEPTION</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <form action="./reception-order-insertion">
                <div class="modal-body">
                    <p><strong>On a constate les anomalies suivants</strong></p>
                    <ul>
                        <li>La quantite attendue pour le savon est 12, mais on reçoit 10</li>
                        <li>La quantite attendue pour le savon est 12, mais on reçoit 10</li>
                        <li>La quantite attendue pour le savon est 12, mais on reçoit 10</li>
                    </ul>
                    <h5>Explication</h5>
                    <hr>
                    <textarea name="explication" readonly placeholder="Vous devez écrire l'explication ici" id=""
                        class="form-control" cols="30" rows="7"></textarea>
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
        </span> Accusé de reception
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
                            <h4 class="mb-4 text-primary">ACCUSE DE RECEPTION</h4>
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
                                <%= deptReceptions.get(0).getDeptReception().getDate() %>
                            </dd>

                            <dt class="col-sm-6 px-0 mb-3">Reference sortie</dt>
                            <dd class="col-sm-6 px-0 mb-3">
                                <%= deptReceptions.get(0).getDeptReception().getOutgoingOrder().getReference() %>
                            </dd>
                            
                            <dt class="col-sm-6 px-0 mb-3">Nom du responsable</dt>
                            <dd class="col-sm-6 px-0 mb-3">
                                <%= deptReceptions.get(0).getDeptReception().getResponsableName() %>
                            </dd>

                            <dt class="col-sm-6 px-0 mb-3">Contact du responsable</dt>
                            <dd class="col-sm-6 px-0 mb-3">
                                <%= deptReceptions.get(0).getDeptReception().getResponsableContact() %>
                            </dd>
                        </dl>
                        <h6 class="text-primary">Detail du reception</h6>
                        <hr class="text-primary">
                        <div class="col-md-5">
                            <ul>
                                <% for(int i=0; i< deptReceptions.size();i++) { %>
                                <li>
                                    <div class="d-flex justify-content-between">
                                        <span><%= deptReceptions.get(i).getArticle().getDesignation() %></span>
                                        <span><%= deptReceptions.get(i).getQuantity() %> Unite</span>
                                    </div>
                                </li>
                                <% } %>
                            </ul>
                        </div>
                    </div>
                </div>

                <div class="mt-4 mx-2">
                    <% if(deptReceptions.get(0).getDeptReception().getStatus() == 1) { %>
                    <div class="div">
                        <a href="./dept-reception-servlet?action=1&idDeptReception=<%= deptReceptions.get(0).getDeptReception().getIdDeptReception() %>" class="btn btn-info me-4">Confirmer</a>
                        <a href="./dept-reception-servlet?action=0&idDeptReception=<%= deptReceptions.get(0).getDeptReception().getIdDeptReception() %>" class="btn btn-danger me-4">Refuser</a>
                    </div>
                    <% } %>
                    <a href="./dept-reception-order-list" class="btn btn-light mt-3">Cancel</a>
                </div>
            </div>
        </div>
    </div>
</div>