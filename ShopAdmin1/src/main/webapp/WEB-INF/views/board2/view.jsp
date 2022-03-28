<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@include file="../include/All_head.jsp" %>
<br>
                <!-- Begin Page Content -->
                <div class="container-fluid">

                    <!-- Page Heading -->
                    <h1 class="h3 mb-2 text-gray-800">DetailView</h1>
                    <p class="mb-4"></p>

                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            <h6 class="m-0 font-weight-bold text-primary"><a href="/board2/list?pageNum=${page.pageNum}"><button>게시판리스트</button></a></h6>
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                            	<div class="form-group row">
                                    <div class="col-sm-6 mb-3 mb-sm-0">
                                    	<label>번호</label>
                                        <input type="text" class="form-control" value="${board.b_num}" readonly>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <div class="col-sm-6 mb-3 mb-sm-0">
                                    	<label>제목</label>
                                        <input type="text" class="form-control" value="${board.b_subject }" readonly>
                                    </div>
                                </div>
								<div class="form-group row">
                                    <div class="col-sm-6 mb-3 mb-sm-0">
                                    	<label>작성자</label>
                                        <input type="text" class="form-control" value="${board.b_name }" readonly>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <div class="col-sm-6 mb-3 mb-sm-0">
                                    	<label>내용</label>
                                        <textarea rows="10" class="form-control" readonly>${board.b_contents }</textarea>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <div class="col-sm-6 mb-3 mb-sm-0">
                                    	<label>작성일</label>
                                        <input type="text" class="form-control" value="${board.b_date }" readonly>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <div class="col-sm-6 mb-3 mb-sm-0">
                                        <a href="/board2/update?b_num=${board.b_num}&pageNum=${page.pageNum}">[수정]</a>
                                        &nbsp;&nbsp;&nbsp;
                                        <a href="/board2/delete?b_num=${board.b_num}">[삭제]</a>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>

                </div>
                <!-- /.container-fluid -->
<%@include file="../include/All_foot.jsp" %>