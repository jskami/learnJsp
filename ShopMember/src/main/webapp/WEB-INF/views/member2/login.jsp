<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@include file="../include/All_head.jsp" %>
<br>
				<!-- Begin Page Content -->
                <div class="container-fluid">

                    <!-- Page Heading -->
                    <h1 class="h3 mb-2 text-gray-800">로그인</h1>
                    <p class="mb-4"></p>

                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            <h6 class="m-0 font-weight-bold text-primary"><a href="/member2/list"><button>사용자 목록</button></a> | <a href="/member2/insert"><button>사용자 등록</button></a></h6>
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">

						<form method="post" action="/member2/login" class="user">
                           <div class="form-group row">
                               <div class="col-sm-6 mb-3 mb-sm-0">
                               	   <label>아이디</label>
                                   <input type="text" class="form-control" name="m_id" placeholder="ID">
                               </div>
                           </div>
                           <div class="form-group row">
                               <div class="col-sm-6 mb-3 mb-sm-0">
                               	   <label>비밀번호</label>
                                   <input type="password" class="form-control" name="m_passwd" placeholder="PASSWORD">
                               </div>
                           </div>
                           <div class="form-group row">
                               <div class="col-sm-6 mb-3 mb-sm-0">
                               	   <label>성함</label>
                                   <input type="text" class="form-control" name="m_name" placeholder="NAME">
                               </div>
                           </div>
                           <div class="form-group row">
                               <div class="col-sm-6 mb-3 mb-sm-0">
                               	   <label>전화번호</label>
                                   <input type="text" class="form-control" name="m_phone" placeholder="PHONE">
                               </div>
                           </div>
                           <div class="form-group row">
                               <div class="col-sm-6 mb-3 mb-sm-0">
                               	   <label>이메일</label>
                                   <input type="text" class="form-control" name="m_email" placeholder="e-mail">
                               </div>
                           </div>
                           <div class="form-group row">
                               <div class="col-sm-6 mb-3 mb-sm-0">
                                   <input type="submit" class="form-control" placeholder="제출">
                                   <p><a href="/shop/list">장바구니 상세정보</a></p>
                               </div>
                           </div>
                       	</form>
                       	
					</div>
				</div>
			</div>
		</div>			
<%@include file="../include/All_foot.jsp" %>
