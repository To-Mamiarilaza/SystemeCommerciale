<%@page import="model.purchaseClient.*, model.article.*, model.sale.*, java.util.List, java.util.Arrays" %>
<%
    double recette = (double) request.getAttribute("montantTotal");
    int requestNumber = (int) request.getAttribute("nombreVente");
    String[] articleName = (String[]) request.getAttribute("articleName");
    String[] proportions = (String[]) request.getAttribute("proportions");
    String article = Arrays.toString(articleName);
    String propo = Arrays.toString(proportions);
%>
<div class="page-header">
    <h3 class="page-title">
        <span class="page-title-icon bg-gradient-primary text-white me-2">
            <i class="mdi mdi-home"></i>
        </span> Dashboard
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
    <div class="col-md-4 stretch-card grid-margin">
        <div class="card bg-gradient-danger card-img-holder text-white">
            <div class="card-body">
                <img src="assets/images/dashboard/circle.svg" class="card-img-absolute" alt="circle-image" />
                <h4 class="font-weight-normal mb-3">Recettes <i
                        class="mdi mdi-chart-line mdi-24px float-right"></i>
                </h4>
                <h2 class="mb-5"><%= recette %> AR</h2>
            </div>
        </div>
    </div>
    <div class="col-md-4 stretch-card grid-margin">
        <div class="card bg-gradient-info card-img-holder text-white">
            <div class="card-body">
                <img src="assets/images/dashboard/circle.svg" class="card-img-absolute" alt="circle-image" />
                <h4 class="font-weight-normal mb-3">Nombre total de ventes <i
                        class="mdi mdi-bookmark-outline mdi-24px float-right"></i>
                </h4>
                <h2 class="mb-5"><%= requestNumber %></h2>
            </div>
        </div>
    </div>
</div>

<!-- CHART FOR GLOBAL STATISTICS -->
<div class="row">
    <div class="col-md-5 grid-margin stretch-card p-3">
        <div class="custom-block bg-white">
            <h5 class="mb-4"> Vente par article </h5>

            <div id="pie-chart"></div>
        </div>
    </div>
</div>
<!-- CHART FOR GLOBAL STATISTICS -->
<script type="text/javascript">
    var options = {
        series: <%= propo %>,
        chart: {
            width: 380,
            type: 'pie',
        },
        labels: <%= article %>,
        responsive: [{
                breakpoint: 480,
                options: {
                    chart: {
                        width: 200
                    },
                    legend: {
                        position: 'bottom'
                    }
                }
            }]
    };

    var chart = new ApexCharts(document.querySelector("#pie-chart"), options);
    chart.render();
</script>