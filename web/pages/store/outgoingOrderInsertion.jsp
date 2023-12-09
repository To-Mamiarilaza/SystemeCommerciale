<%@page import="java.util.List, model.movement.out.*, model.article.Article, java.time.LocalDate" %>
<%
    OutgoingOrder outgoingOrder = (OutgoingOrder) request.getAttribute("outgoingOrder");
    List<Article> articleList = (List<Article>) request.getAttribute("articleList");
    LocalDate now = LocalDate.now();
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
    <div class="col-md-12 mx-auto grid-margin stretch-card">
        <div class="card">
            <div class="card-body">
                <div class="row">
                    <div class="col-md-6">
                        <h4 class="card-title">Insertion de bon de sortie</h4>
                        <form action="./outgoing-order-insertion" method="POST" class="forms-sample">
                            <div class="form-group">
                                <label for="exampleInputEmail1">Date</label>
                                <input type="date" name="date" value="<%= now %>" class="form-control" id="exampleInputEmail1"
                                    placeholder="" readonly>
                            </div>
                            <% if(outgoingOrder.getService() != null) { %>
                            <div class="form-group">
                                <label for="exampleInputPassword1">Departement</label>
                                <input type="text" class="form-control" id="" value="<%= outgoingOrder.getService().getService() %>" readonly>
                            </div>
                            <% } %>
                            <div class="form-group">
                                <label for="exampleInputUsername1">Nom du responsable</label>
                                <input type="text" name="responsableName" class="form-control" id="exampleInputUsername1" required="">
                            </div>
                            <div class="form-group">
                                <label for="exampleInputUsername1">Contact responsable</label>
                                <input type="text" name="responsableContact" class="form-control"
                                       id="exampleInputUsername1" required>
                            </div>
                            <div class="form-group">
                                <label for="exampleInputUsername1">Motif du sortie</label>
                                <input type="text" name="motif" class="form-control"
                                       id="exampleInputUsername1" required>
                            </div>
                            
                            <% if(outgoingOrder.getPurchaseOrder() != null) { %>
                            <div class="form-group">
                                <label for="exampleInputPassword1">Bon de commande</label>
                                <input type="text" value="BOR0001" name="date" class="form-control"
                                    id="exampleInputEmail1">
                            </div>
                            <% } %>
                            
                            
                            <p class="text-error">Une erreur s'est produite</p>
                            <button type="submit" class="btn btn-gradient-primary me-2">Crée le bon
                                de sortie</button>
                            <a href="./outgoing-request-list" class="btn btn-light">Cancel</a>
                        </form>
                    </div>
                    <div class="col-md-6">
                        <h4 class="card-title">Détails des articles demandé</h4>
                        <table class="table align-middle">
                            <thead>
                                <tr class="table-primary">
                                    <td>Designation</td>
                                    <td>Quantite</td>
                                    <td></td>
                                </tr>
                            </thead>
                            <tbody id="detailList">
                                <% for(OutgoingOrderDetail detail : outgoingOrder.getDetails()) { %>
                                    <tr>
                                        <td><%= detail.getArticle().getDesignation() %></td>
                                        <td><%= detail.getQuantity() %></td>
                                        <td>
                                            <a type="button" onclick="removeDetail(this, '<%= detail.getArticle().getDesignation() %>', <%= detail.getQuantity() %>)" class="text-danger"><i class="mdi mdi-close action-icon"></i></a>
                                        </td>
                                    </tr>
                                <% } %>
                                <!-- MODIFICATION ROW -->

                                <tr>
                                    <td>
                                        <select id="articleChoice" class="form-select form-control-sm">
                                            <% for(Article article : articleList) { %>
                                                <option value="<%= article.getIdArticle() %>"><%= article.getDesignation() %></option>
                                            <% } %>
                                        </select>
                                    </td>
                                    <td>
                                        <input id="quantity" type="text" class="form-control" name="quantite">
                                    </td>
                                    <td>
                                        <button onclick="addNewDetail()" class="btn btn-info">Ajouter</button>
                                    </td>
                                </tr>

                                <!-- MODIFICATION ROW -->

                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>