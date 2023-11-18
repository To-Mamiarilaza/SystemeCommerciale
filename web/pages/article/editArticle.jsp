<%@page import="java.util.List"%>
<%@page import="model.article.Category"%>
<%@page import="model.article.Article"%>

<div class="page-header">
    <h3 class="page-title">
        <span class="page-title-icon bg-gradient-primary text-white me-2">
            <i class="mdi mdi-home"></i>
        </span> Gestion des articles
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
    <div class="col-6 grid-margin mx-auto">
        <div class="card">
            <div class="card-body">
                <h4 class="card-title">Modifier un article</h4>
                <div class="mt-4">
                    <% if(request.getAttribute("article") != null) { 
                        Article article = (Article)request.getAttribute("article");
                    %>
                    <form action="./EditArticle" method="POST">
                        <input type="hidden" name="idArticle" value="<%=article.getIdArticle() %>"/>
                        <div class="form-group">
                            <label for="exampleInputUsername1">Code</label>
                            <input type="text" class="form-control" id="exampleInputUsername1"
                                   value="<%=article.getCode() %>" name="code">
                        </div>
                        <div class="form-group">
                            <label for="exampleInputEmail1">Designation</label>
                            <input type="text" class="form-control" id="exampleInputEmail1"
                                   value="<%=article.getDesignation() %>" name="designation">
                        </div>
                        <div class="form-group">
                            <label for="exampleInputPassword1">Description</label>
                            <input type="text" class="form-control" id="exampleInputPassword1"
                                   value="<%=article.getDescription() %>" name="description">
                        </div>
                        <div class="form-group">
                            <label for="exampleInputPassword1">TVA</label>
                            <input type="number" class="form-control" id="exampleInputPassword1"
                                   value="<%=article.getTva() %>" name="tva">
                        </div>
                        <div class="form-group row align-items-end">
                            <div class="col-md-7">
                                <label for="">Categorie</label>
                                <% if(request.getAttribute("categorys") != null) { 
                                    List<Category> categorys = (List<Category>)request.getAttribute("categorys");
                                %>
                                <select name="category">
                                    <% for(int i = 0; i < categorys.size(); i++) { 
                                    if(categorys.get(i).getIdCategory() == article.getCategory().getIdCategory()) { %>
                                        <option cheked value="<%=categorys.get(i).getIdCategory() %>"><%=categorys.get(i).getDesignation() %></option>
                                    <% } 
                                    else {%>
                                    <option value="<%=categorys.get(i).getIdCategory() %>"><%=categorys.get(i).getDesignation() %></option>
                                    <% } } %>
                                </select>
                                <% } %>
                            </div>
                            <div class="col-md-5">
                                <a href="./add-categorie" class="btn btn-gradient-primary"><i class="mdi mdi-playlist-plus me-2 "></i> Ajouter</a>
                            </div>
                        </div>
                        <% if(request.getAttribute("error") != null) { %>
                            <p class="text-error"><i class="mdi mdi-information-outline"></i><%=request.getAttribute("error") %></p>
                        <% } %>
                        <button type="submit"
                            class="btn btn-gradient-primary px-5 me-2">Modifier</button>
                        <a  href="./all-article" class="btn btn-light">Cancel</a>
                    </form>
                    <% } %>
                </div>
            </div>
        </div>
    </div>
</div>