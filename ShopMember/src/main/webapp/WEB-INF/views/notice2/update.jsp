<!-- // 여기에서 활용할 샘플은 insert.jsp이다.(형식이 비슷하니까) -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@include file="../include/All_head.jsp" %>

				<!-- Begin Page Content -->
                <div class="container-fluid">

                    <!-- Page Heading -->
                    <h1 class="h3 mb-2 text-gray-800">Notice Update</h1>
                    <p class="mb-4"></p>

                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            <h6 class="m-0 font-weight-bold text-primary"><a href="/notice2/list"><button>Notice List</button></a></h6>
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">

						<form method="post" action="/notice2/update" class="user" enctype="multipart/form-data">
                           <div class="form-group row">
                               <div class="col-sm-6 mb-3 mb-sm-0">
                               	   <label>번호</label>
                                   <input type="text" class="form-control" name="n_num" value="${notice.n_num}" readonly >
                               </div>
                           </div>
                           <div class="form-group row">
                               <div class="col-sm-6 mb-3 mb-sm-0">
                               	   <label>제목</label>
                                   <input type="text" class="form-control" name="n_subject" value="${notice.n_subject}" >
                               </div>
                           </div>
                           <div class="form-group row">
                               <div class="col-sm-6 mb-3 mb-sm-0">
                               	   <label>작성자</label>
                                   <input type="text" class="form-control" name="n_name" value="${notice.n_name}" >
                               </div>
                           </div>
                           <div class="form-group row">
                           		<div class="col-sm-6 mb-3 mb-sm-0">
                           			<label>내용</label>
                               		<textarea rows="10" cols=150" class="form-control" name="n_contents" >${notice.n_contents}</textarea>
                           		</div>
                           </div>
                           <div class="form-group row">
                           		<div class="col-sm-6 mb-3 mb-sm-0">
                           			<label>파일</label>
                               		<input type="text" class="form-control" name="n_file" value="${notice.n_file}" readonly >
                           		</div>
                           </div>
                           <div class="form-group row">
                               <div class="col-sm-6 mb-3 mb-sm-0">
                               	   <label>작성일</label>
                                   ${notice.n_date} <!-- 인풋 태그를 쓰지 않고 값만 입력해준다. 인풋태그를 쓰면 폼에 의해 값을 넘겨버리기 때문 -->
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
<%@include file="../include/All_foot.jsp" %>
