<%@page import="java.util.List, java.time.LocalDate, model.article.Article, model.stock.*" %>
<%
    String startDate = (String) request.getAttribute("startDate");
    String endDate = (String) request.getAttribute("endDate");
    List<Article> articleList = (List<Article>) request.getAttribute("articleList");
    EtatStock etatStock = (EtatStock) request.getAttribute("etatStock");
%>
<div class="page-header">
    <h3 class="page-title">
        <span class="page-title-icon bg-gradient-primary text-white me-2">
            <i class="mdi mdi-home"></i>
        </span> ETAT DE STOCK
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
                <h4 class="card-title">Etat de stock</h4>
                <p class="card-description">On peut consulter ici les quantites restantes</p>
                <form action="">
                    <div class="row align-items-end">
                        <div class="form-group col-md-4">
                            <label for="">Date debut</label>
                            <input type="date" name="startDate" class="form-control form-control-sm" value="<%= startDate %>">
                        </div>
                        <div class="form-group col-md-4">
                            <label for="">Date fin</label>
                            <input type="date" name="endDate" class="form-control form-control-sm" value="<%= endDate %>">
                        </div>
                        <div class="form-group col-md-4">
                            <label for="">Article</label>
                            <select name="articleCode" class="form-select form-control-sm" id="">
                                <option value="">Toutes les articles</option>
                                <% for(Article article : articleList) { %>
                                    <option value="<%= article.getCode() %>"><%= article.getDesignation() %></option>
                                <% } %>
                            </select>
                        </div>
                        <div class="form-group col-md-2">
                            <button type="submit" class="btn btn-gradient-info">Consulter</button>
                        </div>
                    </div>
                </form>
                <table class="table">
                    <thead>
                        <tr class="table-primary">
                            <td>Article</td>
                            <td>Quantite Initiale</td>
                            <td>Entrant</td>
                            <td>Sortant</td>
                            <td>Reste</td>
                            <td>Prix Unitaire</td>
                            <td>Montant</td>
                        </tr>
                    </thead>
                    <tbody>
                        <% for(Stock stock : etatStock.getStockList()) { %>
                        <tr>
                            <td><%= stock.getArticle().getDesignation() %></td>
                            <td><%= stock.getInitialQuantity() %></td>
                            <td><%= stock.getIncoming() %></td>
                            <td><%= stock.getOutgoing() %></td>
                            <td><%= stock.getRemain() %></td>
                            <td><%= stock.getUnitPriceDisplay() %></td>
                            <td><%= stock.getAmountDisplay() %></td>
                        </tr>
                        <% } %>

                        <!-- TOTAL ROW -->
                        <tr class="table-primary">
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td>TOTAL</td>
                            <td><%= etatStock.getStockTotalValue() %> AR</td>
                        </tr>
                        <!-- TOTAL ROW -->

                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>