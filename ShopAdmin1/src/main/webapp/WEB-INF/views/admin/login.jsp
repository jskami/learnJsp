<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@include file="../include/login_head.jsp" %>
<br>
          <!-- Begin Page Content -->
          <div class="container-fluid">

              <!-- Page Heading -->
              <h1 class="h3 mb-2 text-gray-800">관리자 로그인</h1>
              <p class="mb-4"></p>

              <!-- DataTales Example -->
              <div class="card shadow mb-4">
                  <div class="card-header py-3">
                      <h6 class="m-0 font-weight-bold text-primary"></h6>
                  </div>
                  <div class="card-body">
                      <div class="table-responsive">
                      <form method="post" action="/admin/login" class="user">
                          <div class="form-group row">
                              <div class="col-sm-6 mb-3 mb-sm-0">
                              	<label>아이디</label>
                                  <input type="text" class="form-control" name="a_id">
                              </div>
                          </div>
						<div class="form-group row">
                              <div class="col-sm-6 mb-3 mb-sm-0">
                              	<label>암호</label>
                                  <input type="password" class="form-control" name="a_passwd">
                              </div>
                          </div>
                          <br>
                          <div class="form-group row">
                              <div class="col-sm-6 mb-3 mb-sm-0">
                              	<input type="submit" class="form-control">
                              </div>
                          </div>
                      </form>

                      </div>
                  </div>
              </div>
          </div>
          <!-- /.container-fluid -->
<%@include file="../include/login_foot.jsp" %>