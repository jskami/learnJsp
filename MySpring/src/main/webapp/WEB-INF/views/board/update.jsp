<!-- // 여기에서 활용할 샘플은 insert.jsp이다.(형식이 비슷하니까) -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@include file="../include/header.jsp" %>

				<!-- Begin Page Content -->
                <div class="container-fluid">

                    <!-- Page Heading -->
                    <h1 class="h3 mb-2 text-gray-800">수정하기</h1>
                    <p class="mb-4"></p>

                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            <h6 class="m-0 font-weight-bold text-primary"><a href="/board/list?pageNum=${page.pageNum}">게시판 리스트</a></h6>
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">

						<form method="post" action="/board/update?pageNum=${page.pageNum}" class="user">
                           <div class="form-group row">
                               <div class="col-sm-6 mb-3 mb-sm-0">
                               	   <label>번호</label>
                                   <input type="text" class="form-control" name="b_num" value="${board.b_num}" readonly >
                               </div>
                           </div>
                           <div class="form-group row">
                               <div class="col-sm-6 mb-3 mb-sm-0">
                               	   <label>제목</label>
                                   <input type="text" class="form-control" name="b_subject" value="${board.b_subject}" >
                               </div>
                           </div>
                           <div class="form-group row">
                               <div class="col-sm-6 mb-3 mb-sm-0">
                               	   <label>작성자</label>
                                   <input type="text" class="form-control" name="b_name" value="${board.b_name}" >
                               </div>
                           </div>
                           <div class="form-group row">
                           		<div class="col-sm-6 mb-3 mb-sm-0">
                           			<label>내용</label>
                               		<textarea rows="10" cols=150" class="form-control" name="b_contents" >${board.b_contents}</textarea>
                           		</div>
                           </div>
                           <div class="form-group row">
                               <div class="col-sm-6 mb-3 mb-sm-0">
                               	   <label>작성일</label>
                                   ${board.b_date} <!-- 인풋 태그를 쓰지 않고 값만 입력해준다. 인풋태그를 쓰면 폼에 의해 값을 넘겨버리기 때문 -->
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
