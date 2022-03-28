<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@include file="../include/All_head.jsp" %>
<br>
                <!-- Begin Page Content -->
                <div class="container-fluid">

                    <!-- Page Heading -->
                    <h1 class="h3 mb-2 text-gray-800">Update</h1>
                    <p class="mb-4"></p>

                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            <h6 class="m-0 font-weight-bold text-primary"><a href="/product2/list?pageNum=${page.pageNum}"><button>상품리스트</button></a></h6>
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                            <form method="post" action="/product2/update?pageNum=${page.pageNum}" class="user" enctype="multipart/form-data">
                            	<div class="form-group row">
                                    <div class="col-sm-6 mb-3 mb-sm-0">
                                    	<label>상품번호</label>
                                        <input type="text" class="form-control" name="p_code" value="${product.p_code}" readonly>
                                    </div>
                                </div>
								<div class="form-group row">
                                    <div class="col-sm-6 mb-3 mb-sm-0">
                                    	<label>상품명</label>
                                        <input type="text" class="form-control" name="b_name" value="${product.p_name}">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <div class="col-sm-6 mb-3 mb-sm-0">
                                    	<label>가격</label>
                                        <input type="text" class="form-control" name="p_price" value="${product.p_price}">
                                    </div>
                                </div>
                                <!-- <div class="form-group row">
                                    <div class="col-sm-6 mb-3 mb-sm-0">
                                    	<label>파일</label>
                                        <input type="file" class="form-control" name="b_file">
                                    </div>
                                </div> -->
                                <div class="form-group row">
                                    <div class="col-sm-6 mb-3 mb-sm-0">
                                    	<label>작성일</label>
                                        ${product.p_udate}
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <div class="col-sm-6 mb-3 mb-sm-0">
                                        <input type="submit" class="form-control" value="수정하기">
                                    </div>
                                </div>
                            </form>

                            </div>
                        </div>
                    </div>

                </div>
                <!-- /.container-fluid -->
<%@include file="../include/All_foot.jsp" %>