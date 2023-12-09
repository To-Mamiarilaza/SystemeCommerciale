<%@page import="model.article.*,java.util.*,model.sale.*" %>

<div class="page-header">
    <h3 class="page-title">
        <span class="page-title-icon bg-gradient-primary text-white me-2">
            <i class="mdi mdi-home"></i>
        </span> Envoie de proforma
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
                <div class="row">
                    <form action="./proforma-sending-detail" class="col-md-7" method="POST">
                        <h4 class="card-title">Envoye de proforma</h4>

                        <div class="form-group">
                            <label for="" class="form-label">Email</label>
                            <input type="email" class="form-control mt-3" name="email">
                        </div>
                        <h5>Les articles demandés</h5>
                        <div class="row mt-3">
                            <div class="col-md-4">
                                <% if(request.getAttribute("articles") !=null) { 
                                List<Article> articles = (List<Article>)request.getAttribute("articles");
                                %>
                                <select name="article" class="form-select form-control-sm" id="articleInput">
                                    <% for(int i = 0; i < articles.size(); i++) { %>
                                    <option value="<%=articles.get(i).getIdArticle() %>"><%=articles.get(i).getDesignation() %></option>
                                    <% } %>
                                </select>
                                <% } %>
                            </div>
                            <div class="col-md-4">
                                <input type="number" step="0.01" class="form-control form-control-sm" name="quantity" id="quantityInput">
                            </div>
                            <div class="col-md-2">
                                <button type="button" onclick="addNewArticleQuantitySale()" class="btn btn-gradient-primary">Ajouter</button>
                            </div>
                            <div class="col-md-8">
                                <table class="table" id="articleList">
                                    <tbody>
                                       
                                    </tbody>
                                    <div class="loader">
                                        <div class="dot"></div>
                                        <div class="dot"></div>
                                        <div class="dot"></div>
                                    </div>
                                </table>
                            </div>
                        </div>
                            
                        <input type="submit" class="btn btn-gradient-primary px-5 mt-4 w-25" value="Valider">
                    </form>
                    <div class="col-md-5">
                        <% if(request.getAttribute("proformaSendings") != null) { 
                        List<ProformaSending> proformaSending = (List<ProformaSending>)request.getAttribute("proformaSendings");
                        %>
                        <h4 class="card-title">Historique d'envoie</h4>
                        <table class="table">
                            <thead>
                                <tr>
                                    <th>Date</th>
                                    <th>Email</th>
                                </tr>
                            </thead>
                            <tbody>
                                <% for(int i = 0; i < proformaSending.size(); i++) { %>
                                <tr>
                                    <td><%=proformaSending.get(i).getDateSending() %></td>
                                    <td><%=proformaSending.get(i).getEmail() %></td>
                                </tr>
                                <% } %>
                            </tbody>
                        </table>
                        <% } %>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>