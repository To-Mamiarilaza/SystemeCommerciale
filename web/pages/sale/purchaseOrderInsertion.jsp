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
                        <form class="forms-sample">
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
                                <input type="text" value="" name="name" class="form-control"
                                       id="exampleInputEmail1" placeholder="">
                            </div>
                            <div class="form-group">
                                <label for="exampleInputUsername1">Addresse</label>
                                <input type="text" name="addresse" class="form-control" id="exampleInputUsername1">
                            </div>
                            <div class="form-group">
                                <label for="exampleInputUsername1">Contact</label>
                                <input type="text" name="livreurContact" class="form-control"
                                       id="exampleInputUsername1">
                            </div>
                            <div class="form-group">
                                <label for="exampleInputUsername1">Date livraison</label>
                                <input type="date" name="deliveryDate" class="form-control"
                                       id="exampleInputUsername1">
                            </div>
                            <div class="form-group">
                                <label for="exampleInputUsername1">Mode de payment</label>
                                <select class="form-select form-control-sm">
                                    <option>Virement bancaire</option>
                                </select>
                            </div>
                            <p class="text-error">Une erreur s'est produite</p>
                            <button type="button" data-bs-toggle="modal" data-bs-target="#exampleModal"
                                    class="btn btn-gradient-primary me-2">Valider</button>
                            <a href="./client-purchase-order-list" class="btn btn-light">Cancel</a>
                        </form>
                    </div>
                    <div class="col-md-6">
                        <h4 class="card-title">Détails des articles commandé</h4>
                        <table class="table table-no-border align-middle">
                            <thead>
                                <tr class="table-primary">
                                    <td>Designation</td>
                                    <td>Quantite</td>
                                    <td></td>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>Savon</td>
                                    <td>40</td>
                                    <td>
                                        <a href="" class="text-danger"><i class="mdi mdi-close action-icon"></i></a>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Pomme</td>
                                    <td>8</td>
                                    <td>
                                        <a href="" class="text-danger"><i class="mdi mdi-close action-icon"></i></a>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Pomme</td>
                                    <td>8</td>
                                    <td>
                                        <a href="" class="text-danger"><i class="mdi mdi-close action-icon"></i></a>
                                    </td>
                                </tr>

                                <!-- MODIFICATION ROW -->

                                <tr>
                                    <td>
                                        <select class="form-select form-control-sm">
                                            <option value="1">Savon</option>
                                            <option value="2">Cache bouche</option>
                                        </select>
                                    </td>
                                    <td>
                                        <input type="number" class="form-control form-control-sm" name="quantite">
                                    </td>
                                    <td>
                                        <button class="btn btn-info">Ajouter</button>
                                    </td>
                                </tr>

                                <!-- MODIFICATION ROW -->

                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>