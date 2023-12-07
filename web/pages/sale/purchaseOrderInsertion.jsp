<%@page import="model.purchase.*, model.article.*, model.sale.*, java.util.List" %>
<div class="page-header">
    <h3 class="page-title">
        <span class="page-title-icon bg-gradient-primary text-white me-2">
            <i class="mdi mdi-home"></i>
        </span> Bon de commande client
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
                        <h4 class="card-title">Insertion de bon de commande client</h4>
                        <form class="forms-sample" action="./client-purchase-order-insertion" method="POST">
                            <div class="form-group">
                                <label for="exampleInputUsername1">Reference</label>
                                <input type="text" name="reference" class="form-control" id="exampleInputUsername1">
                            </div>
                            <div class="form-group">
                                <label for="exampleInputEmail1">Date</label>
                                <input type="date" name="date" class="form-control" id="exampleInputEmail1"
                                       placeholder="">
                            </div>
                            <div class="form-group">
                                <label for="exampleInputPassword1">Nom client</label>
                                <input type="text" value="" name="clientName" class="form-control"
                                       id="exampleInputEmail1" placeholder="">
                            </div>
                            <div class="form-group">
                                <label for="exampleInputUsername1">Addresse</label>
                                <input type="text" name="adress" class="form-control" id="exampleInputUsername1">
                            </div>
                            <div class="form-group">
                                <label for="exampleInputUsername1">Contact</label>
                                <input type="text" name="contactDelivery" class="form-control"
                                       id="exampleInputUsername1">
                            </div>
                            <div class="form-group">
                                <label for="exampleInputUsername1">Date livraison</label>
                                <input type="date" name="deliveryDate" class="form-control"
                                       id="exampleInputUsername1">
                            </div>
                            <div class="form-group">
                                <label for="exampleInputUsername1">Mode de payment</label>
                                <% if(request.getAttribute("paymentMethods") != null) {
                                    List<PaymentMethod> paymentMethod = (List<PaymentMethod>)request.getAttribute("paymentMethods");
                                %>
                                <select class="form-select form-control-sm" name="methodPayment">
                                    <% for(int i = 0; i < paymentMethod.size(); i++) { %>
                                    <option value="<%=paymentMethod.get(i).getIdPaymentMethod() %>"><%=paymentMethod.get(i).getPaymentMethodName() %></option>
                                    <% } %>
                                </select>
                                <% } %>
                            </div>
                            <% if(request.getAttribute("error") != null) { %>
                            <p class="text-error"><%=request.getAttribute("error") %></p>
                            <% } %>
                            <button type="submit" data-bs-toggle="modal" data-bs-target="#exampleModal"
                                    class="btn btn-gradient-primary me-2">Valider</button>
                            <a href="./client-purchase-order-list" class="btn btn-light">Cancel</a>
                        </form>
                    </div>
                    <div class="col-md-6">
                        <h4 class="card-title">Détails des articles commandé</h4>
                        <table class="table table-no-border align-middle" id="articleList">
                            <thead>
                                <tr class="table-primary">
                                    <td>Designation</td>
                                    <td>Quantite</td>
                                    <td></td>
                                </tr>
                            </thead>
                            <tbody>
                                
                            </tbody>
                               
                                <!-- MODIFICATION ROW -->

                                <tr>
                                    <td>
                                        <% if(request.getAttribute("articles") != null) { 
                                            List<Article> articles = (List<Article>)request.getAttribute("articles");
                                        %>
                                        <select class="form-select form-control-sm" id="articleInput">
                                            <% for(int i = 0; i < articles.size(); i++) { %>
                                            <option value="<%=articles.get(i).getIdArticle() %>"><%=articles.get(i).getDesignation() %></option>
                                            <% } %>
                                        </select>
                                        <% } %>
                                    </td>
                                    <td>
                                        <input type="number" class="form-control form-control-sm" name="quantite" id="quantityInput">
                                    </td>
                                    <td>
                                        <button type="button" onclick="addNewArticleQuantityOrder()" class="btn btn-info">Ajouter</button>
                                    </td>
                                </tr>

                                <!-- MODIFICATION ROW -->

                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>