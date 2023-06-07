<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
</head>
<body>
                    <div class="card shadow">
                        HI
                    </div>
                    <div class="card shadow">
                        <div class="card-header py-3">
                            <p class="text-primary m-0 fw-bold">管理員清單</p>
                        </div>
                        <div class="card-body">
                            <div class="col-md-6 custom-col-newmanager">
                                <a class="btn btn-primary btn-sm d-none d-sm-inline-block custom-col-newmanager-btn"
                                    role="button" href="#">
                                    新增管理員
                                </a>
                            </div>
                            <div class="row">
                                <div class="col-md-6 text-nowrap">
                                    <div id="dataTable_length" class="dataTables_length" aria-controls="dataTable">
                                        <label class="form-label">Show&nbsp;
                                            <select class="d-inline-block form-select form-select-sm">
                                                <option value="10" selected="">10</option>
                                                <option value="25">25</option>
                                                <option value="50">50</option>
                                                <option value="100">100</option>
                                            </select>&nbsp;
                                        </label>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="text-md-end dataTables_filter" id="dataTable_filter">
                                        <label class="form-label">
                                            <input type="search" class="form-control form-control-sm"
                                                aria-controls="dataTable" placeholder="Search">
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <div class="table-responsive table mt-2" id="manager_list_div" role="grid"
                                aria-describedby="dataTable_info">
                                <table class="table my-0" id="manager_list">
                                    <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>帳號</th>
                                            <th>密碼</th>
                                            <th>姓名</th>
                                            <th>信箱</th>
                                            <th>電話</th>
                                            <th>在職狀態</th>
                                            <th>設定</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td>%%%管理員ID</td>
                                            <td>%%%管理員帳號</td>
                                            <td>%%%管理員密碼</td>
                                            <td>Ason</td>
                                            <td>%%%管理員信箱</td>
                                            <td>%%%管理員電話</td>
                                            <td>%%%管理員在職狀態</td>
                                            <td>
                                                <a class="btn btn-primary btn-sm d-none d-sm-inline-block custom-manager-button"
                                                    role="button" href="#">
                                                    詳細資料
                                                </a>
                                                <a class="btn btn-primary btn-sm d-none d-sm-inline-block custom-manager-button"
                                                    role="button" href="#">
                                                    修改資料
                                                </a>
                                                <a class="btn btn-primary btn-sm d-none d-sm-inline-block custom-manager-button"
                                                    role="button" href="#">
                                                    調整在職狀態
                                                </a>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>%%%管理員ID2</td>
                                            <td>%%%管理員帳號2</td>
                                            <td>%%%管理員密碼2</td>
                                            <td>%%%管理員姓名2</td>
                                            <td>%%%管理員信箱2</td>
                                            <td>%%%管理員電話2</td>
                                            <td>%%%管理員在職狀態2</td>
                                            <td>
                                                <a class="btn btn-primary btn-sm d-none d-sm-inline-block custom-manager-button"
                                                    role="button" href="#">
                                                    詳細資料
                                                </a>
                                                <a class="btn btn-primary btn-sm d-none d-sm-inline-block custom-manager-button"
                                                    role="button" href="#">
                                                    修改資料
                                                </a>
                                                <a class="btn btn-primary btn-sm d-none d-sm-inline-block custom-manager-button"
                                                    role="button" href="#">
                                                    調整在職狀態
                                                </a>
                                            </td>
                                        </tr>

                                    </tbody>
                                    <tfoot>
                                        <tr>
                                            <td><strong>ID</strong></td>
                                            <td><strong>帳號</strong></td>
                                            <td><strong>密碼</strong></td>
                                            <td><strong>姓名</strong></td>
                                            <td><strong>信箱</strong></td>
                                            <td><strong>電話</strong></td>
                                            <td><strong>在職狀態</strong></td>
                                            <td><strong>設定</strong></td>
                                        </tr>
                                    </tfoot>
                                </table>
                            </div>
                            <div class="row">
                                <div class="col-md-6 align-self-center">
                                    <p id="dataTable_info" class="dataTables_info" role="status" aria-live="polite">
                                        Showing 1 to 10 of %%%總筆數/篩選筆數</p>
                                </div>
                                <div class="col-md-6">
                                    <nav
                                        class="d-lg-flex justify-content-lg-end dataTables_paginate paging_simple_numbers">
                                        <ul class="pagination">
                                            <li class="page-item disabled">
                                                <a class="page-link" aria-label="Previous" href="#">
                                                    <span aria-hidden="true">«</span>
                                                </a>
                                            </li>
                                            <li class="page-item active">
                                                <a class="page-link" href="#">1</a>
                                            </li>
                                            <li class="page-item">
                                                <a class="page-link" href="#">2</a>
                                            </li>
                                            <li class="page-item">
                                                <a class="page-link" href="#">3</a>
                                            </li>
                                            <li class="page-item">
                                                <a class="page-link" aria-label="Next" href="#">
                                                    <span aria-hidden="true">»</span>
                                                </a>
                                            </li>
                                        </ul>
                                    </nav>
                                </div>
                            </div>
                            <div class="col-md-6 custom-col-newmanager">
                                <a class="btn btn-primary btn-sm d-none d-sm-inline-block custom-col-newmanager-btn"
                                    role="button" href="#">
                                    新增管理員
                                </a>
                            </div>
                        </div>
                    </div>
</body>
</html>