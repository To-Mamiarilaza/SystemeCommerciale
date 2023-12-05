<div class="page-header">
    <h3 class="page-title">
        <span class="page-title-icon bg-gradient-primary text-white me-2">
            <i class="mdi mdi-home"></i>
        </span> Bon de sortie
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
                <h4 class="card-title">Listes des Bon de sortie</h4>
                <div class="mt-4 d-flex align-items-center justify-content-between">
                    <form action="../../PurchaseRequestFilter" method="POST">
                        <div class="input-groups d-flex align-items-center">
                            <div class="form-group me-4">
                                <label for="">Date</label>
                                <input type="date" name="date" class="form-control form-control-sm px-5 mt-2">
                            </div>
                            <div class="form-group me-4">
                                <label for="">Etat</label>
                                <select name="status" id="" class="form-control form-control-sm px-5 mt-2">
                                    <option value="1">En attente</option>
                                    <option value="2">Valid�</option>
                                    <option value="0">Refus�</option>
                                </select>
                            </div>
                            <div>
                                <input type="submit" class="mx-2 btn btn-gradient-primary" value="Chercher">
                            </div>
                        </div>
                    </form>
                </div>
                <div class="table-responsive mt-2">
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th> Date </th>
                                <th> Reference </th>
                                <th> Responsable </th>
                                <th> Type </th>
                                <th> Departement </th>
                                <th> Bon de commande </th>
                                <th> Motif </th>
                                <th> Etat </th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>12-11-2023</td>
                                <td>BDS0001</td>
                                <td>RAKOTO Jaona</td>
                                <td>Besoin interne</td>
                                <td>Informatique</td>
                                <td></td>
                                <td>
                                    <label class="badge badge-warning label-width">En
                                        attente</label>
                                </td>
                                <td>
                                    <a href="./outgoing-order-detail"><i
                                            class="mdi mdi-clipboard-text action-icon"></i></a>
                                </td>
                            </tr>
                            <tr>
                                <td>12-11-2023</td>
                                <td>BDS0001</td>
                                <td>Naivosoa</td>
                                <td>VENTE</td>
                                <td></td>
                                <td>BDC0002</td>
                                <td>
                                    <label class="badge badge-warning label-width">En
                                        attente</label>
                                </td>
                                <td>
                                    <a href="./outgoing-order-detail"><i
                                            class="mdi mdi-clipboard-text action-icon"></i></a>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>