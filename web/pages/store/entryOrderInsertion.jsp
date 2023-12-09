<%@page contentType="text/html; charset=UTF-8" %>
<%@page import="model.purchase.*, model.reception.*, model.article.*, model.base.*, model.supplier.Supplier, java.util.List" %>
<% 
    List<ReceptionArticleDetails> receptions = (List<ReceptionArticleDetails>) request.getAttribute("receptionArticles");
%>
<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="exampleModalLabel">Une anomalie s'est produite</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <form action="./entry-order-list">
                <div class="modal-body">
                    <p><strong>On a constaté les anomalies suivants</strong></p>
                    <ul>
                        <li>La quantite attendue pour le savon est 12, mais on reçoit 10</li>
                        <li>La quantite attendue pour le savon est 12, mais on reçoit 10</li>
                        <li>La quantite attendue pour le savon est 12, mais on reçoit 10</li>
                    </ul>
                    <h5>Explication</h5>
                    <hr>
                    <textarea name="explication" placeholder="Vous devez écrire l'éxplication ici" id=""
                              class="form-control" cols="30" rows="7"></textarea>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-primary">Valider</button>
                </div>
            </form>
        </div>
    </div>
</div>

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
    <div class="col-md-12 mx-auto grid-margin stretch-card">
        <div class="card">
            <div class="card-body">
                <div class="row">
                    <div class="col-md-6">
                        <h4 class="card-title">Insertion d'un nouveau bon d'entrée</h4>
                        <form class="forms-sample" method="post" action="./entry-order-insertion">
                            <div class="form-group">
                                <label for="exampleInputEmail1">Date</label>
                                <input type="date" name="date" class="form-control" id="exampleInputEmail1">
                            </div>
                            <div class="form-group">
                                <label for="exampleInputPassword1">Bon de reception</label>
                                <input type="text" value="<%= receptions.get(0).getReceptionOrder().getReference() %>" name="date" class="form-control"
                                       id="exampleInputEmail1" readonly>
                            </div>
                            <input type="hidden" name="idReception" value="<%= receptions.get(0).getReceptionOrder().getIdReceptionOrder() %>">
                            <div class="form-group">
                                <label for="exampleInputUsername1">Nom du responsable</label>
                                <input type="text" name="responsableName" class="form-control" id="exampleInputUsername1">
                            </div>
                            <div class="form-group">
                                <label for="exampleInputUsername1">Contact responsable</label>
                                <input type="text" name="responsableContact" class="form-control"
                                       id="exampleInputUsername1">
                            </div>
                            <% if(request.getParameter("error") != null) { %>
                            <p class="text-error"><%= request.getParameter("error") %></p>
                            <% } %>
                            <input type="submit"
                                    class="btn btn-gradient-primary me-2" value="Crée le bon d'entree">
                            <a href="./entry-request-list" class="btn btn-light">Cancel</a>
                        </form>
                    </div>
                    <div class="col-md-6">
                        <h4 class="card-title">Détails des articles recue</h4>
                        <table class="table align-middle">
                            <thead>
                                <tr class="table-primary">
                                    <td>Designation</td>
                                    <td>Quantite</td>
                                    <td></td>
                                </tr>
                            </thead>
                            <tbody>
                                <% for(int i=0;i<receptions.size();i++) { %>
                                <tr>
                                    <td><%= receptions.get(i).getArticle().getDesignation() %></td>
                                    <td><%= receptions.get(i).getQuantity() %></td>
                                    <td>
                                        <a href="./deleted-entry-article?id=<%=i%>&idReception=<%= receptions.get(i).getReceptionOrder().getIdReceptionOrder() %>" class="text-danger"><i class="mdi mdi-close action-icon"></i></a>
                                    </td>
                                </tr>
                                <% } %>                            
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>