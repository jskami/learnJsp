<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@include file="../include/header.jsp" %>

				<!-- Begin Page Content -->
                <div class="container-fluid">

                    <!-- Page Heading -->
                    <h1 class="h3 mb-2 text-gray-800">글작성</h1>
                    <p class="mb-4"></p>

                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            <h6 class="m-0 font-weight-bold text-primary"><a href="/notice/list">게시판 리스트</a></h6>
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">

						<form method="post" action="/notice/insert" class="user">
                           <div class="form-group row">
                               <div class="col-sm-6 mb-3 mb-sm-0">
                               	   <label>제목</label>
                                   <input type="text" class="form-control" name="n_subject" placeholder="제목">
                               </div>
                           </div>
                           <div class="form-group row">
                               <div class="col-sm-6 mb-3 mb-sm-0">
                               	   <label>작성자</label>
                                   <input type="text" class="form-control" name="n_name" placeholder="작성자">
                               </div>
                           </div>
                           <div class="form-group row">
                           		<div class="col-sm-6 mb-3 mb-sm-0">
                           			<label>내용</label>
                               		<textarea rows="10" cols=150" class="form-control" name="n_contents" placeholder="내용"></textarea>
                           		</div>
                           </div>
                           <div class="form-group row">
                               <div class="col-sm-6 mb-3 mb-sm-0">
                                   <input type="submit" class="form-control" placeholder="제출">
                               </div>
                           </div>
                       	</form>
					</div>
				</div>
			</div>
		</div>			
<%@include file="../include/footer.jsp" %>
