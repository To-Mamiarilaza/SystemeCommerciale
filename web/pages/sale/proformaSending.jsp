<div class="page-header">
    <h3 class="page-title">
        <span class="page-title-icon bg-gradient-primary text-white me-2">
            <i class="mdi mdi-home"></i>
        </span> Envoie de proforma
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
                <div class="row">
                    <form action="./proforma-sending-detail" class="col-md-7">
                        <h4 class="card-title">Envoye de proforma</h4>

                        <div class="form-group">
                            <label for="" class="form-label">Email</label>
                            <input type="email" class="form-control mt-3" name="email">
                        </div>
                        <h5>Les articles demandés</h5>
                        <div class="row mt-3">
                            <div class="col-md-4">
                                <select name="" class="form-select form-control-sm" id="">
                                    <option value="">Article</option>
                                </select>
                            </div>
                            <div class="col-md-4">
                                <input type="text" class="form-control form-control-sm">
                            </div>
                            <div class="col-md-2">
                                <button class="btn btn-gradient-primary">Ajouter</button>
                            </div>
                        </div>
                        <div class="detail mt-4 col-md-8">
                            <ul>
                                <li>
                                    <div class="d-flex justify-content-between align-items-center">
                                        <div class="d-flex w-50 justify-content-between">
                                            <span class="mx-3">Cache bouche</span>
                                            <span>18</span>
                                        </div>
                                        <span><a href="" class="text-danger"><i
                                                    class="action-icon mdi mdi-close"></i></a></span>
                                    </div>
                                </li>
                                <li>
                                    <div class="d-flex justify-content-between align-items-center">
                                        <div class="d-flex w-50 justify-content-between">
                                            <span class="mx-3">Savony</span>
                                            <span>300</span>
                                        </div>
                                        <span><a href="" class="text-danger"><i
                                                    class="action-icon mdi mdi-close"></i></a></span>
                                    </div>
                                </li>
                            </ul>
                        </div>
                        <input type="submit" class="btn btn-gradient-primary px-5 mt-4 w-25" value="Valider">
                    </form>
                    <div class="col-md-5">
                        <h4 class="card-title">Historique d'envoie</h4>
                        <table class="table">
                            <thead>
                                <tr>
                                    <th>Date</th>
                                    <th>Email</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>10-10-2023</td>
                                    <td>test@gmail.com</td>
                                </tr>
                                <tr>
                                    <td>10-10-2023</td>
                                    <td>test.example@gmail.com</td>
                                </tr>
                            </tbody>
                        </table>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>