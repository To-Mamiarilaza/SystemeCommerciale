<%@page import="java.util.List, model.stock.ArticleMethodMapping, model.stock.GestionMethod, model.article.Article, model.base.Utilisateur" %>
<%
    Utilisateur utilisateur = (Utilisateur) request.getAttribute("utilisateur");
    List<GestionMethod> gestionMethods = (List<GestionMethod>) request.getAttribute("gestionMethods");
    List<ArticleMethodMapping> articleMethods = (List<ArticleMethodMapping>) request.getAttribute("articleMethods");
    List<Article> articles = (List<Article>) request.getAttribute("articleWithoutMethods");
%>
<div class="page-header">
    <h3 class="page-title">
        <span class="page-title-icon bg-gradient-primary text-white me-2">
            <i class="mdi mdi-home"></i>
        </span> ARTICLE MAPPING
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
                <h4 class="card-title">Article Mapping</h4>
                <div class="row mt-5">
                    <div class="col-md-8">
                        <h5 class="text-primary">Les articles qui n'ont pas encore de methode</h5>
                        <hr>
                        <table class="table align-middle">
                            <thead>
                                <tr>
                                    <td>Article</td>
                                    <td>Methode</td>
                                    <td></td>
                                </tr>
                            </thead>
                            <tbody>
                            
                            <% for(Article article : articles) { %>
                            <tr>
                            <form action="./article-method" method="POST">
                                <input type="hidden" name="idArticle" value="<%= article.getIdArticle() %>">
                                <td><%= article.getDesignation() %></td>
                                <td>
                                    <select class="form-select form-control-sm" name="idMethod" id="">
                                        <% for(GestionMethod method : gestionMethods) { %>
                                        <option value="<%= method.getIdGestionMethod() %>"><%= method.getGestionMethodName() %></option>
                                        <% } %>
                                    </select>
                                </td>
                                <td><button type="submit" class="btn btn-success btn-sm">Valider</button></td>
                            </form>
                            </tr>
                            <% } %>
                            
                            </tbody>
                        </table>
                    </div>
                    <div class="col-md-4">
                        <h5 class="text-primary">Article et leurs methodes</h5>
                        <hr>
                        <table class="table">
                            <thead>
                                <tr>
                                    <td>Article</td>
                                    <td>Methode</td>
                                    <% if(utilisateur.getIsAdmin()) { %>
                                    <td></td>
                                    <% } %>
                                </tr>
                            </thead>
                            <tbody>
                                <% for(ArticleMethodMapping mapping : articleMethods) { %>
                                <tr>
                                    <td><%= mapping.getArticle().getDesignation() %></td>
                                    <td><%= mapping.getGestionMethod().getGestionMethodName() %></td>
                                    <% if(utilisateur.getIsAdmin()) { %>
                                    <td><a href="./delete-article-mapping?idMapping=<%= mapping.getIdArticleMethodMapping() %>" class="text-danger"><i class="mdi mdi-close"></i></a></td>
                                    <% } %>
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