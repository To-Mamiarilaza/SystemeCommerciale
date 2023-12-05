<div class="page-header">
    <h3 class="page-title">
        <span class="page-title-icon bg-gradient-primary text-white me-2">
            <i class="mdi mdi-home"></i>
        </span> ETAT DE STOCK
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
                <h4 class="card-title">Etat de stock</h4>
                <p class="card-description">On peut consulter ici les quantites restantes</p>
                <form action="">
                    <div class="row align-items-end">
                        <div class="form-group col-md-4">
                            <label for="">Date debut</label>
                            <input type="date" class="form-control form-control-sm">
                        </div>
                        <div class="form-group col-md-4">
                            <label for="">Date fin</label>
                            <input type="date" class="form-control form-control-sm">
                        </div>
                        <div class="form-group col-md-4">
                            <label for="">Article</label>
                            <select name="" class="form-select form-control-sm" id="">
                                <option value="">Savon</option>
                                <option value="">Cache bouche</option>
                            </select>
                        </div>
                        <div class="form-group col-md-2">
                            <button type="submit" class="btn btn-gradient-info">Consulter</button>
                        </div>
                    </div>
                </form>
                <table class="table">
                    <thead>
                        <tr class="table-primary">
                            <td>Article</td>
                            <td>Sortant</td>
                            <td>Entrant</td>
                            <td>Quantite avant</td>
                            <td>Quantite restant</td>
                            <td>Prix Achat</td>
                            <td>Montant</td>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>Savon</td>
                            <td>100 Kg</td>
                            <td>500 Kg</td>
                            <td>200 Kg</td>
                            <td>400 Kg</td>
                            <td>20 000 AR</td>
                            <td>1 000 000 AR</td>
                        </tr>
                        <tr>
                            <td>Savon</td>
                            <td>100 Kg</td>
                            <td>500 Kg</td>
                            <td>200 Kg</td>
                            <td>400 Kg</td>
                            <td>20 000 AR</td>
                            <td>1 000 000 AR</td>
                        </tr>

                        <!-- TOTAL ROW -->
                        <tr class="table-primary">
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td>TOTAL</td>
                            <td>12 000 000 AR</td>
                        </tr>
                        <!-- TOTAL ROW -->

                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>