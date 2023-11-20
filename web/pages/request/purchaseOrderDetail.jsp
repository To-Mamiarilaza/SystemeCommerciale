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
    <div class="col-9 grid-margin mx-auto">
        <div class="card">
            <div class="card-body">
                <h4 class="card-title">BON DE COMMANDE</h4>
                <h4 class="card-description text-small mb-2">Numero : <span
                        class="text-black">BOC____</span></h4>
                <h4 class="card-description text-small"><%= proforma.getDate() %></h4>
                <div class="row mt-3">
                    <div class="col-md-7">
                        <dl class="row">
                            <dt class="col-sm-6 px-0">Nom du fournisseur</dt>
                            <dd class="col-sm-6 px-0">
                                Leader Price
                            </dd>

                            <dt class="col-sm-6 px-0">Contact du responsable</dt>
                            <dd class="col-sm-6 px-0">
                                +261 235 123 212
                            </dd>

                            <dt class="col-sm-6 px-0">Mail</dt>
                            <dd class="col-sm-6 px-0">
                                fournisseur@example.com
                            </dd>

                            <dt class="col-sm-6 px-0">Adresse</dt>
                            <dd class="col-sm-6 px-0">
                                Tanjombato
                            </dd>

                            <dt class="col-sm-6 px-0 mt-4">Date de livraison</dt>
                            <dd class="col-sm-6 px-0 mt-4">
                                <strong>10-10-2023</strong>
                            </dd>
                            <dt class="col-sm-6 px-0">Mode de paiement</dt>
                            <dd class="col-sm-6 px-0">
                                Cheque bancaire
                            </dd>
                        </dl>
                    </div>

                    <div class="col-md-5">
                        <p class="quantity-detail mt-0 text-info"><strong>Categories de produits
                                vendues</strong></p>
                        <ul>
                            <li>Produits premier necessite</li>
                            <li>Produits agricole</li>
                            <li>Produits hygienique</li>
                        </ul>
                    </div>

                    <div class="payment-condition mx-3 px-0 col-md-6">
                        <h4 class="mx-0 my-0 text-info">Condition de payement</h4>
                        <div class="mt-1">
                            <table class="table table-no-border">
                                <thead></thead>
                                <tbody>
                                    <tr class="py-1">
                                        <td><i class="mdi text-info mdi-arrow-right-bold"></i></td>
                                        <td class="px-0">30 %</td>
                                        <td class="px-0"><strong><span class="me-3">15-10-2023</span></strong></td>
                                    </tr>
                                    <tr class="py-1">
                                        <td><i class="mdi text-info mdi-arrow-right-bold"></i></td>
                                        <td class="px-0">70 %</td>
                                        <td class="px-0"><strong><span class="me-3">15-10-2023</span></strong></td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <table class="table mt-5">
                    <thead>
                        <tr class="table-info">
                            <th>Designation</th>
                            <th>Prix Unitaire</th>
                            <th>Quantite</th>
                            <th>TVA</th>
                            <th class="text-right px-4">Montant TVA</th>
                            <th class="text-right px-4">HT</th>
                            <th class="text-right px-4">TTC</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>Stylo</td>
                            <td>200 AR</td>
                            <td>30</td>
                            <td>20 %</td>
                            <td class="text-right">4200 AR</td>
                            <td class="text-right">4000 AR</td>
                            <td class="text-right">4200 AR</td>
                            <td class="text-right"><a href="" data-bs-toggle="modal"
                                    data-bs-target="#exampleModal"><i
                                        class="mdi mdi-settings action-icon"></i></a></td>
                        </tr>
                        <tr>
                            <td>Stylo</td>
                            <td>200 AR</td>
                            <td>30</td>
                            <td>20 %</td>
                            <td class="text-right">4200 AR</td>
                            <td class="text-right">4000 AR</td>
                            <td class="text-right">4200 AR</td>
                            <td class="text-right"><a href="" data-bs-toggle="modal"
                                    data-bs-target="#exampleModal"><i
                                        class="mdi mdi-settings action-icon"></i></a></td>
                        </tr>
                        <tr>
                            <td>Stylo</td>
                            <td>200 AR</td>
                            <td>30</td>
                            <td>20 %</td>
                            <td class="text-right">4200 AR</td>
                            <td class="text-right">4000 AR</td>
                            <td class="text-right">4200 AR</td>
                            <td class="text-right"><a href="" data-bs-toggle="modal"
                                    data-bs-target="#exampleModal"><i
                                        class="mdi mdi-settings action-icon"></i></a></td>
                        </tr>

                        <!-- TOTAL ROW -->

                        <tr>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td class="text-right">TOTAL</td>
                            <td class="text-right text-success">250 000 AR</td>
                            <td class="text-right text-success">4 000 000 AR</td>
                            <td class="text-right text-success">4 000 000 AR</td>
                            <td></td>
                        </tr>
                    </tbody>
                </table>
                <div class="mt-4">
                    <p><strong>Arrete le present proforma a la somme de :</strong> <br> <span>Trois
                            cent deux mille neux cent ARIARY</span></p>
                </div>
                <div class="mt-3">
                    <a href="../../proforma-detail" class="btn btn-light">Cancel</a>
                </div>
            </div>
        </div>
    </div>
</div>

