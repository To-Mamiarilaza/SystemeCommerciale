<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="exampleModalLabel">Une anomalie s'est produite</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <form action="./reception-order-insertion">
                <div class="modal-body">
                    <p><strong>On a constaté les anomalies suivants</strong></p>
                    <ul>
                        <li>La quantite attendue pour le savon est 12, mais on reçoit 10</li>
                        <li>La quantite attendue pour le savon est 12, mais on reçoit 10</li>
                        <li>La quantite attendue pour le savon est 12, mais on reçoit 10</li>
                    </ul>
                    <h5>Explication</h5>
                    <hr>
                    <textarea name="explication" placeholder="Vous devez Ã©crire l'éxplication ici" id=""
                        class="form-control" cols="30" rows="7"></textarea>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-primary">Valider</button>
                </div>
            </form>
        </div>
    </div>
</div>

<div class="row">
    <div class="col-md-12 mx-auto grid-margin stretch-card">
        <div class="card">
            <div class="card-body">
                <div class="row">
                    <div class="col-md-6">
                        <h4 class="card-title">Insertion du bon de reception</h4>
                        <form action="./reception-list" class="forms-sample">
                            <div class="form-group">
                                <label for="exampleInputUsername1">Reference bon de livraison</label>
                                <input type="text" name="reference" class="form-control" id="exampleInputUsername1" readonly>
                            </div>
                            <div class="form-group">
                                <label for="exampleInputEmail1">Date</label>
                                <input type="date" value="2023-11-12" name="date" class="form-control" id="exampleInputEmail1"
                                       placeholder="" readonly="">
                            </div>
                            <div class="form-group">
                                <label for="exampleInputUsername1">Nom du responsable</label>
                                <input type="text" name="responsable" class="form-control" id="exampleInputUsername1">
                            </div>
                            <div class="form-group">
                                <label for="exampleInputUsername1">Contact responsable</label>
                                <input type="text" name="responsableContact" class="form-control"
                                    id="exampleInputUsername1">
                            </div>
                            <p class="text-error">Une erreur s'est produite</p>
                            <button type="submit" data-bs-toggle="modal" data-bs-target="#exampleModal"
                                class="btn btn-gradient-primary me-2">Valider</button>
                                <a href="./delivery-order-insertion" class="btn btn-light">Cancel</a>
                        </form>
                    </div>
                    <div class="col-md-6">
                        <h4 class="card-title">Détails des articles reçue</h4>
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
                                    <td><a href="" class="text-warning"><i
                                                class="mdi mdi-settings action-icon me-5"></i></a>
                                        <a href="" class="text-danger"><i class="mdi mdi-close action-icon"></i></a>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Pomme</td>
                                    <td>8</td>
                                    <td><a href="" class="text-warning"><i
                                                class="mdi mdi-settings action-icon me-5"></i></a>
                                        <a href="" class="text-danger"><i class="mdi mdi-close action-icon"></i></a>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Pomme</td>
                                    <td>8</td>
                                    <td><a href="" class="text-warning"><i
                                                class="mdi mdi-settings action-icon me-5"></i></a>
                                        <a href="" class="text-danger"><i class="mdi mdi-close action-icon"></i></a>
                                    </td>
                                </tr>

                                <!-- MODIFICATION ROW -->

                                <tr>
                                    <td>
                                        <input type="text" class="form-control" value="Savon" readonly>
                                    </td>
                                    <td>
                                        <input type="text" class="form-control" name="quantite">
                                    </td>
                                    <td>
                                        <button class="btn btn-info">Modifier</button>
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