<%@page import="model.purchase.*, generalisation.src.generalisation.utils.*, model.article.*, model.base.*, model.statistics.*, model.supplier.Supplier, java.util.List, java.util.Arrays, java.util.stream.Collectors" %>
<%
    double montant = (Double) request.getAttribute("sommeMontant");
    int nombreRequest = (Integer) request.getAttribute("nombreRequest");
        
        /* Traitement des donnees pour la pie-chart */
        List<ServiceDepense> depenseService = (List<ServiceDepense>) request.getAttribute("serviceDepense");
        List<Service> listeService = (List<Service>) request.getAttribute("listeService");
        double[] tableau = new double[depenseService.size()];
        for (int i = 0; i < depenseService.size(); i++) {
            tableau[i] = depenseService.get(i).getMontant();
        }
        String[] serviceName = new String [listeService.size()];
        for (int a = 0; a < listeService.size(); a++) {
            serviceName[a] = "'"+listeService.get(a).getService()+"'";
        }
        String depenseString = Arrays.toString(tableau);
        String serviceString = Arrays.toString(serviceName);
        
        /* Traitement des donnees pour le charts */
        
        /* Traitement de la valeur du depense chaque mois */
        List<DepenseMensuel> depenseMensuel = (List<DepenseMensuel>) request.getAttribute("depenseMensuel");
        GenericUtil.detailList(depenseMensuel);
        
        /* Extraction des colonnes de mois et les valeurs pour chaque mois */
            String[] month = depenseMensuel.stream().map(depense -> depense.getMoisString()).toArray(String[]::new);
            String[] montantValues = depenseMensuel.stream().map(depense -> String.valueOf(depense.getValue_depense())).toArray(String[]::new);
         
            out.print(montantValues[0]);
            
        /* Transformation des tableaux en String */    
            String monthString = Arrays.toString(month);
            String montantString = Arrays.toString(montantValues);
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
                <h4 class="font-weight-normal mb-3">Depense mensuel <i
                        class="mdi mdi-chart-line mdi-24px float-right"></i>
                </h4>
                <h2 class="mb-5"><%= montant %> AR</h2>
            </div>
        </div>
    </div>
    <div class="col-md-4 stretch-card grid-margin">
        <div class="card bg-gradient-info card-img-holder text-white">
            <div class="card-body">
                <img src="assets/images/dashboard/circle.svg" class="card-img-absolute" alt="circle-image" />
                <h4 class="font-weight-normal mb-3">Demande d'article en attente <i
                        class="mdi mdi-bookmark-outline mdi-24px float-right"></i>
                </h4>
                <h2 class="mb-5"><%= nombreRequest %></h2>
            </div>
        </div>
    </div>
</div>

<!-- CHART FOR GLOBAL STATISTICS -->
<div class="row">
    <div class="col-md-7">
        <div class="custom-block bg-white">
            <h5 class="mb-4"> Depense mensuel </h5>

            <div id="chart"></div>
        </div>
    </div>
    <div class="col-md-5 grid-margin stretch-card">
        <div class="custom-block bg-white">
            <h5 class="mb-4"> Depense par service </h5>

            <div id="pie-chart"></div>
        </div>
    </div>
</div>
<!-- CHART FOR GLOBAL STATISTICS -->
<script type="text/javascript">
    var options = {
        series: <%= depenseString %>,
        chart: {
            width: 380,
            type: 'pie',
        },
        labels: <%= serviceString %>,
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
<script type="text/javascript">
    var month = <%= monthString %>;
    var montantValues = <%= montantString %>;
    console.log(montantValues);

    var options = {
        series: month.map(function (month, index) {
            return {
                name: month,
                data: [parseInt(montantValues[index])]
            };
        }),
        chart: {
            type: 'bar',
            height: 350
        },
        plotOptions: {
            bar: {
                horizontal: false,
                columnWidth: '25%',
                endingShape: 'rounded'
            },
        },
        dataLabels: {
            enabled: true
        },
        stroke: {
            show: true,
            width: 2,
            colors: ['transparent']
        },
        xaxis: {
            categories: month,
            labels: {
                show: true
            }
        },
        yaxis: {
            title: {
                text: '$ (percent)'
            }
        },
        fill: {
            opacity: 1
        },
        tooltip: {
            y: {
                formatter: function (val) {
                    return "$ " + val + " percent";
                }
            }
        }
    };

    var chart = new ApexCharts(document.querySelector("#chart"), options);
    chart.render();
</script>

