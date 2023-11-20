<%@ page contentType="text/html; charset=UTF-8" %>
<%@page import="model.purchase.*, model.article.*, model.base.*, model.supplier.Supplier, java.util.List" %>
<% 
    List<Proforma> proformas = (List<Proforma>) request.getAttribute("proformas");
%>

<div class="page-header">
    <h3 class="page-title">
        <span class="page-title-icon bg-gradient-primary text-white me-2">
            <i class="mdi mdi-home"></i>
        </span> BON DE COMMANDE
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
    <div class="col-12 grid-margin">
        <div class="card">
            <div class="card-body">
                <h4 class="card-title">Listes des bon de commandes</h4>
                <div class="mt-4 d-flex align-items-center justify-content-between">
                    <form action="" method="POST">
                        <div class="input-groups d-flex align-items-center">
                            <div class="form-group me-4">
                                <label for="">Nom fournisseur</label>
                                <input type="text" class="form-control form-control-sm mt-3" name="supplierName">
                            </div>
                            <div>
                                <input type="submit" class="mx-2 mt-3 btn btn-gradient-primary"
                                    value="Chercher">
                            </div>
                        </div>
                    </form>
                </div>
                <div class="row">
                    <div class="table-responsive mt-2 col-md-8">
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th> ID </th>
                                    <th> Date </th>
                                    <th> Fournisseur </th>
                                    <th> Contact </th>
                                    <th> Livraison </th>
                                    <th> Montant </th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>BOC0001</td>
                                    <td>01-01-2023</td>
                                    <td>Leader Price</td>
                                    <td>+261 34 12 532 12</td>
                                    <td>
                                        12-01-2023
                                    </td>
                                    <td>
                                        2 000 000 AR
                                    </td>
                                    <td>
                                        <a href="./purchase-order-detail"><i
                                                class="mdi mdi-clipboard-text action-icon"></i></a>
                                    </td>
                                </tr>
                                <tr>
                                    <td>BOC0001</td>
                                    <td>01-01-2023</td>
                                    <td>Leader Price</td>
                                    <td>+261 34 12 532 12</td>
                                    <td>
                                        12-01-2023
                                    </td>
                                    <td>
                                        2 000 000 AR
                                    </td>
                                    <td>
                                        <a href="./purchase-order-detail"><i
                                                class="mdi mdi-clipboard-text action-icon"></i></a>
                                    </td>
                                </tr>
                                <tr>
                                    <td>BOC0001</td>
                                    <td>01-01-2023</td>
                                    <td>Leader Price</td>
                                    <td>+261 34 12 532 12</td>
                                    <td>
                                        12-01-2023
                                    </td>
                                    <td>
                                        2 000 000 AR
                                    </td>
                                    <td>
                                        <a href="./purchase-order-detail"><i
                                                class="mdi mdi-clipboard-text action-icon"></i></a>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    <div class="mt-3 col-md-4 px-5">
                        <h4>Listes des proformas disponible</h4>
                        <hr>
                        <table class="table table-no-border">
                            <thead>

                            </thead>
                            <tbody>
                                <% for(Proforma proforma : proformas) { %>
                                <tr>
                                    <td><i class="mdi mdi-arrow-right-bold"></i></td>
                                    <td><%= proforma.getSupplier().getSupplierName() %></td>
                                    <td class="text-warning text-right"><%= proforma.getTotalTTCString() %></td>
                                    <td><a href="./proforma-detail?idSupplier=<%= proforma.getSupplier().getIdSupplier() %>" class="no-style-link">Voir plus</a></td>
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