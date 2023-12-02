<!-- MODAL FOR THE DELIVERY -->
<div class="modal fade" id="deliveryModal" tabindex="-1" aria-labelledby="deliveryModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="deliveryModalLabel">Anomalie bon de livraison</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <form action="./reception-order-insertion">
                <div class="modal-body">
                    <p><strong>On a constate les anomalies suivants</strong></p>
                    <ul>
                        <li>La quantite attendue pour le savon est 12, mais on re�oit 10</li>
                        <li>La quantite attendue pour le savon est 12, mais on re�oit 10</li>
                        <li>La quantite attendue pour le savon est 12, mais on re�oit 10</li>
                    </ul>
                    <h5>Explication</h5>
                    <hr>
                    <textarea name="explication" readonly placeholder="Vous devez écrire l'�xplication ici" id=""
                        class="form-control" cols="30" rows="7"></textarea>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                </div>
            </form>
        </div>
    </div>
</div>
<!-- MODAL FOR THE DELIVERY -->

<!-- MODAL FOR THE RECEPTION -->
<div class="modal fade" id="receptionModal" tabindex="-1" aria-labelledby="receptionModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="receptionModalLabel">Anomalie reception</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <form action="./reception-order-insertion">
                <div class="modal-body">
                    <p><strong>On a constate les anomalies suivants</strong></p>
                    <ul>
                        <li>La quantite attendue pour le savon est 12, mais on reçoit 10</li>
                        <li>La quantite attendue pour le savon est 12, mais on reçoit 10</li>
                        <li>La quantite attendue pour le savon est 12, mais on reçoit 10</li>
                    </ul>
                    <h5>Explication</h5>
                    <hr>
                    <textarea name="explication" readonly placeholder="Vous devez écrire l'�xplication ici" id=""
                        class="form-control" cols="30" rows="7"></textarea>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                </div>
            </form>
        </div>
    </div>
</div>
<!-- MODAL FOR THE RECEPTION -->

<div class="page-header">
    <h3 class="page-title">
        <span class="page-title-icon bg-gradient-primary text-white me-2">
            <i class="mdi mdi-home"></i>
        </span> Demandes d'achat
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
                <h4 class="card-title">LIVRASON ET RECEPTION</h4>
                <div class="row">
                    <div class="col-md-6 mt-3 px-5">
                        <div class="row border border-1 p-4 ">
                            <div class="col-md-12">
                                <div class="d-flex justify-content-between">
                                    <h4 class="mb-4 text-primary">BON DE LIVRAISON</h4>
                                    <div>
                                        <a type="button" data-bs-toggle="modal" data-bs-target="#deliveryModal"
                                            class="text-danger action-icon"> <i class="mdi mdi-information-outline"></i>
                                        </a>
                                    </div>
                                </div>
                                <dl class="row">
                                    <dt class="col-sm-6 px-0 mb-3">Date</dt>
                                    <dd class="col-sm-6 px-0 mb-3">
                                        12-11-2023
                                    </dd>

                                    <dt class="col-sm-6 px-0 mb-3">Reference livraison</dt>
                                    <dd class="col-sm-6 px-0 mb-3">
                                        BDL0001
                                    </dd>

                                    <dt class="col-sm-6 px-0 mb-3">Reference commande</dt>
                                    <dd class="col-sm-6 px-0 mb-3">
                                        BDC0001
                                    </dd>

                                    <dt class="col-sm-6 px-0 mb-3">Livreur</dt>
                                    <dd class="col-sm-6 px-0 mb-3">
                                        RAZAFIARISOA Chresis
                                    </dd>

                                    <dt class="col-sm-6 px-0 mb-3">Contact livreur</dt>
                                    <dd class="col-sm-6 px-0 mb-3">
                                        +261 34 21 532 69
                                    </dd>
                                </dl>
                                <h6 class="text-primary">Detail du livraison</h6>
                                <hr class="text-primary">
                                <div class="col-md-5">
                                    <ul>
                                        <li>
                                            <div class="d-flex justify-content-between">
                                                <span>Savon</span>
                                                <span>20 Unite</span>
                                            </div>
                                        </li>
                                        <li>
                                            <div class="d-flex justify-content-between">
                                                <span>Cache bouche</span>
                                                <span>30 Unite</span>
                                            </div>
                                        </li>
                                        <li>
                                            <div class="d-flex justify-content-between">
                                                <span>Cache bouche</span>
                                                <span>30 Unite</span>
                                            </div>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-6 mt-3 px-5">
                        <div class="row border border-1 p-4">
                            <div class="col-md-12">
                                <div class="d-flex justify-content-between">
                                    <h4 class="mb-4 text-primary">BON DE RECEPTION</h4>
                                    <div>
                                        <a type="button" data-bs-toggle="modal" data-bs-target="#receptionModal"
                                            class="text-danger action-icon"> <i class="mdi mdi-information-outline"></i>
                                        </a>
                                    </div>
                                </div>
                                <dl class="row">
                                    <dt class="col-sm-6 px-0 mb-3">Date</dt>
                                    <dd class="col-sm-6 px-0 mb-3">
                                        12-11-2023
                                    </dd>

                                    <dt class="col-sm-6 px-0 mb-3">Reference reception</dt>
                                    <dd class="col-sm-6 px-0 mb-3">
                                        BDR0001
                                    </dd>

                                    <dt class="col-sm-6 px-0 mb-3">Reference livraison</dt>
                                    <dd class="col-sm-6 px-0 mb-3">
                                        BDL0001
                                    </dd>

                                    <dt class="col-sm-6 px-0 mb-3">Responsable</dt>
                                    <dd class="col-sm-6 px-0 mb-3">
                                        MAMIARILAZA To
                                    </dd>

                                    <dt class="col-sm-6 px-0 mb-3">Contact Responsable</dt>
                                    <dd class="col-sm-6 px-0 mb-3">
                                        +261 34 14 517 43
                                    </dd>
                                </dl>
                                <h6 class="text-primary">Detail du reception</h6>
                                <hr class="text-primary">
                                <div class="col-md-5">
                                    <ul>
                                        <li>
                                            <div class="d-flex justify-content-between">
                                                <span>Savon</span>
                                                <span>20 Unite</span>
                                            </div>
                                        </li>
                                        <li>
                                            <div class="d-flex justify-content-between">
                                                <span>Cache bouche</span>
                                                <span>30 Unite</span>
                                            </div>
                                        </li>
                                        <li>
                                            <div class="d-flex justify-content-between">
                                                <span>Cache bouche</span>
                                                <span>30 Unite</span>
                                            </div>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="mt-4 mx-2">
                        <div class="div">
                            <a href="" class="btn btn-info me-4">Envoyer au magasin</a>
                            <a href="" class="btn btn-danger me-4">Annuler</a>
                        </div>
                        <a href="./reception-list" class="btn btn-light mt-3">Cancel</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>