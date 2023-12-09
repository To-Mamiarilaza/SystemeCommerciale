<%@page import="java.util.List, model.stock.Incoming, model.stock.Outgoing" %>
<%
    List<Incoming> incomingList = (List<Incoming>) request.getAttribute("incomingList");
    List<Outgoing> outgoingList = (List<Outgoing>) request.getAttribute("outgoingList");

%>
<div class="page-header">
    <h3 class="page-title">
        <span class="page-title-icon bg-gradient-primary text-white me-2">
            <i class="mdi mdi-home"></i>
        </span> Historiques des mouvements
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
                <h4 class="card-title">Historiques des mouvements</h4>
                <div class="row mt-5">
                    <div class="col-md-6">
                        <h5 class="text-primary">Mouvements d'entrees</h5>
                        <hr>
                        <table class="table">
                            <thead>
                                <tr>
                                    <td>ID entree</td>
                                    <td>Date</td>
                                    <td>BDE</td>
                                    <td>Article</td>
                                    <td>Quantite</td>
                                </tr>
                            </thead>
                            <tbody>
                                <% for(Incoming incoming : incomingList) { %>
                                <tr>
                                    <td>#<%= incoming.getIdIncoming() %></td>
                                    <td><%= incoming.getDate() %></td>
                                    <td><%= incoming.getIdBDE() %></td>
                                    <td><%= incoming.getArticle().getDesignation() %></td>
                                    <td><%= incoming.getQuantity() %></td>
                                </tr>
                                <% } %>
                            </tbody>
                        </table>
                    </div>
                    <div class="col-md-6">
                        <h5 class="text-primary">Mouvements de sortie</h5>
                        <hr>
                        <table class="table">
                            <thead>
                                <tr>
                                    <td>ID sortie</td>
                                    <td>Date</td>
                                    <td>BDS</td>
                                    <td>Article</td>
                                    <td>Quantite</td>
                                </tr>
                                <% for(Outgoing outgoing : outgoingList) { %>
                                <tr>
                                    <td>#<%= outgoing.getIdOutgoing() %></td>
                                    <td><%= outgoing.getDate() %></td>
                                    <td><%= outgoing.getIdBDS() %></td>
                                    <td><%= outgoing.getIncoming().getArticle().getDesignation() %></td>
                                    <td><%= outgoing.getQuantity() %></td>
                                </tr>
                                <% } %>
                            </thead>
                        </table>
                    </div>
                </div>


            </div>
        </div>
    </div>
</div>