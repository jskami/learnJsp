<!-- // 여기에서 활용할 샘플은 insert.jsp이다.(형식이 비슷하니까) -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@include file="../include/All_head.jsp" %>

				<!-- Begin Page Content -->
                <div class="container-fluid">

                    <!-- Page Heading -->
                    <h1 class="h3 mb-2 text-gray-800">Detail View</h1>
                    <p class="mb-4"></p>

                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            <h6 class="m-0 font-weight-bold text-primary"><a href="/notice2/list?pageNum=${page.pageNum}"><button>Notice List</button></a></h6>
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">

						   <div class="form-group row">
                               <div class="col-sm-6 mb-3 mb-sm-0">
                               	   <label>번호</label>
                                   <input type="text" class="form-control" value="${notice.n_num}" readonly> <!-- 보드안에 있는 여러 레코드 중에서 해당하는 번호를!-의 의미 -->
                               </div>
                           </div>
                           <div class="form-group row">
                               <div class="col-sm-6 mb-3 mb-sm-0">
                               	   <label>제목</label>
                                   <input type="text" class="form-control" value="${notice.n_subject}" readonly>
                               </div>
                           </div>
                           <div class="form-group row">
                               <div class="col-sm-6 mb-3 mb-sm-0">
                               	   <label>작성자</label>
                                   <input type="text" class="form-control" value="${notice.n_name}" readonly>
                               </div>
                           </div>
                           <div class="form-group row">
                           		<div class="col-sm-6 mb-3 mb-sm-0">
                           			<label>내용</label>
                               		<textarea rows="10" cols=150" class="form-control" readonly>${notice.n_contents}</textarea>
                           		</div>
                           </div>
                           <div class="form-group row">
                               <div class="col-sm-6 mb-3 mb-sm-0">
                               	   <label>첨부 파일</label>
                                   <input type="text" class="form-control" value="${notice.n_file}" readonly>
                               </div>
                           </div>
                           <div class="form-group row">
                               <div class="col-sm-6 mb-3 mb-sm-0">
                               	   <label>작성일</label>
                                   <input type="text" class="form-control" value="${notice.n_date}" placeholder="작성일">
                               </div>
                           </div>
                           <div class="form-group row">
                               <div class="col-sm-6 mb-3 mb-sm-0">
                                   <a href="/notice2/update?n_num=${notice.n_num}&pageNum=${page.pageNum}"><button>수정</button></a>
                                   &nbsp;&nbsp;&nbsp;
                                   <a href="/notice2/delete?n_num=${notice.n_num}"><button>삭제</button></a>
                               </div>
                           </div>
                       	
					</div>
				</div>
			</div>
		</div>			
<%@include file="../include/All_foot.jsp" %>
