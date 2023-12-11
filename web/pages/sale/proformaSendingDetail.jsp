<%@page import="model.article.*, model.sale.*" %>

<div class="page-header">
    <h3 class="page-title">
        <span class="page-title-icon bg-gradient-primary text-white me-2">
            <i class="mdi mdi-home"></i>
        </span> ENVOIE DE PROFORMA
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
<% if(request.getAttribute("proformaSending") != null) { 
ProformaSending proformaSending = (ProformaSending)request.getAttribute("proformaSending");
%>
<div class="row">
    <div class="col-10 grid-margin mx-auto">
        <div class="card">
            <div class="card-body">
                <h4 class="card-title">PROFORMA</h4>
                <div class="row mt-3">
                    <div class="col-md-7">
                        <dl class="row">
                            <dt class="col-sm-6 px-0">Societe</dt>
                            <dd class="col-sm-6 px-0">
                                HUILE DE BONGOLAVA
                            </dd>

                            <dt class="col-sm-6 px-0">Contact du responsable</dt>
                            <dd class="col-sm-6 px-0">
                                +261 32 124 32
                            </dd>

                            <dt class="col-sm-6 px-0">Email</dt>
                            <dd class="col-sm-6 px-0">
                                huileBongolava@gmail.com
                            </dd>

                            <dt class="col-sm-6 px-0">Adresse</dt>
                            <dd class="col-sm-6 px-0">
                                Tsiroanomandidy
                            </dd>
                        </dl>
                    </div>

                    <div class="col-md-5">
                        <dl class="row">
                            <dt class="col-sm-6 px-0">Email client</dt>
                            <dd class="col-sm-6 px-0">
                                <%=proformaSending.getEmail() %>
                            </dd>
                        </dl>
                    </div>
                    
                </div>
                <table class="table mt-5">
                    <thead>
                        <tr class="table-info">
                            <th>Designation</th>
                            <th>Prix Unitaire</th>
                            <th>Quantite</th>
                            <th>Disponible</th>
                            <th>TVA</th>
                            <th class="text-right px-4">Montant TVA</th>
                            <th class="text-right px-4">HT</th>
                            <th class="text-right px-4">TTC</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <% for(int i = 0; i < proformaSending.getArticles().size(); i++) { %>
                        <tr>
                            <td><%=proformaSending.getArticles().get(i).getArticle().getDesignation() %></td>
                            <td><%= proformaSending.getArticles().get(i).getUnitPrice()  %> AR</td>
                            <td><%=proformaSending.getArticles().get(i).getQuantity() %></td>
                            <td><%=proformaSending.getArticles().get(i).getAvailableQuantity() %></td>
                            <td><%=proformaSending.getArticles().get(i).getTva() %> %</td>
                            <td class="text-right"><%=proformaSending.getArticles().get(i).getTvaAmount() %> AR</td>
                            <td class="text-right"><%=proformaSending.getArticles().get(i).getHtAmount() %> AR</td>
                            <td class="text-right"><%=proformaSending.getArticles().get(i).getTtcAmount() %> AR</td>
                        </tr>
                        <% } %>
                        <!-- TOTAL ROW -->

                        <tr>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td class="text-right">TOTAL</td>
                            <td class="text-right text-success"><%= proformaSending.getTvaTotal() %> AR</td>
                            <td class="text-right text-success"><%= proformaSending.getHtTotal() %> AR</td>
                            <td class="text-right text-success"><%= proformaSending.getTtcTotal() %> AR</td>
                            <td></td>
                        </tr>
                    </tbody>
                </table>
                <div class="mt-4">
                    <p><strong>Arrete le present proforma a la somme de :</strong> <br> <span> SOIXANTE DOUZE MILLE ARIARY</span></p>
                </div>
                <div class="mt-3">
                    
                    <a href="./SaveProformaSending?status=1" class="btn btn-info mt-3">Envoyer le proforma</a>
                    <a href="./SaveProformaSending?status=0" class="btn btn-light mt-3">Cancel</a>
                </div>
            </div>
        </div>
    </div>
</div>
<% } %>
