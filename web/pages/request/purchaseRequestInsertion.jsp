<%@page import="java.util.List"%>
<%@page import="model.article.Article"%>

<div class="page-header">
    <h3 class="page-title">
        <span class="page-title-icon bg-gradient-primary text-white me-2">
            <i class="mdi mdi-home"></i>
        </span> Demandes d'achat
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

<div class="row">
    <div class="col-8 grid-margin mx-auto">
        <div class="card">
            <div class="card-body">
                <h4 class="card-title">Envoyer demande achat</h4>
                <div class="mt-4">
                    <form action="./purchase-request-insertion" method="POST">
                        <div class="form-group">
                            <label for="exampleInputUsername1">Titre du demande</label>
                            <input type="text" name="title" class="form-control" id="exampleInputUsername1"
                                placeholder="Demande mensuel du departement">
                        </div>
                        <div class="form-group">
                            <label for="exampleInputPassword1">Description</label>
                            <textarea name="description" class="form-control" id="" cols="30" rows="10"></textarea>
                        </div>
                        <div class="row align-items-end">
                            <div class="form-group col-md-5">
                                <label for="article">Article</label>
                                <% if(request.getAttribute("articles") != null) { 
                                    List<Article> articles = (List<Article>)request.getAttribute("articles"); %>
                                <select name="article" class="form-control form-control-sm input-height mt-2" id="articleInput">
                                    <% for(int i = 0; i < articles.size(); i++) { %>
                                    <option value="<%=articles.get(i).getIdArticle() %>"><%=articles.get(i).getDesignation() %></option>
                                    <% } %>
                                </select>
                                <% } %>
                            </div>
                            <div class="form-group col-md-3">
                                <label for="article">Quantite</label>
                                <input type="number" class="form-control mt-2" value="10"  name="quantite" id="quantiteInput">
                            </div>
                            <div class="form-group col-md-3">
                                <label for=""></label>
                                <input type="button" onclick="addNewArticle()" class="btn btn-gradient-primary" value="Ajouter">
                            </div>
                        </div>
                        <div class="row mb-3">
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
                            <p class="text-error" id="addArticleError"></p>
                        <div class="mt-3">
                            <button type="submit"
                                class="btn btn-gradient-primary px-5 me-2">Valider</button>
                            <a  href="./purchase-request-list" class="btn btn-light">Cancel</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>