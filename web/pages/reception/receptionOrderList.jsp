<%@page import="java.util.List"%>
<%@page import="model.purchase.PurchaseRequest"%>
<%@page import="model.base.Service"%>
<%@page import="model.movement.out.*, model.dept.*, model.reception.*, model.article.*, model.base.*, model.supplier.Supplier, java.util.List" %>
<%@page contentType="text/html; charset=UTF-8" %>
<%
    List<DeptReception> deptReceptions = (List<DeptReception>) request.getAttribute("deptReceptions");
%>
<div class="page-header">
    <h3 class="page-title">
        <span class="page-title-icon bg-gradient-primary text-white me-2">
            <i class="mdi mdi-home"></i>
        </span> Accusés de récéption
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
                <h4 class="card-title">Listes accusés récéptions</h4>
                <div class="mt-4 d-flex align-items-center justify-content-between">
                    <form action="./dept-reception-order-list" method="GET">
                        <div class="input-groups d-flex align-items-center">
                            <div class="form-group me-4">
                                <label for="">Date</label>
                                <input type="date" name="date" class="form-control form-control-sm px-5 mt-2">
                            </div>
                            <div class="form-group me-4">
                                <label for="">Etat du demande</label>
                                <select name="status" id=""
                                        class="form-control form-control-sm px-5 mt-2">
                                    <option value="1">Non traite</option>
                                    <option value="5">Confirmer</option>
                                    <option value="0">Refuse</option>
                                </select>
                            </div>
                            <div>
                                <input type="submit" class="mx-2 btn btn-gradient-primary"
                                       value="Chercher">
                            </div>
                        </div>
                    </form>
                </div>
                <div class="table-responsive mt-2">
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th> Date </th>
                                <th> ACR Reference </th>
                                <th> Responsable </th>
                                <th> Etat </th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <% for(int i=0;i<deptReceptions.size();i++) { %>
                            <tr>
                                <td><%= deptReceptions.get(i).getDate() %></td>
                                <td><%= deptReceptions.get(i).getReference() %></td>
                                <td><%= deptReceptions.get(i).getResponsableContact() %></td>
                                <td>
                                    <%= deptReceptions.get(i).getStatusString(deptReceptions.get(i).getStatus()) %>
                                </td>
                                <td>
                                    <a href="./dept-reception-order-detail?idDeptReception=<%= deptReceptions.get(i).getIdDeptReception() %>"><i class="mdi mdi-clipboard-text action-icon"></i></a>
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

