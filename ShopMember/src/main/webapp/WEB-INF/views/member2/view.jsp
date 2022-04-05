<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@include file="../include/All_head.jsp" %>
<br>

				<!-- Begin Page Content -->
                <div class="container-fluid">

                    <!-- Page Heading -->
                    <h1 class="h3 mb-2 text-gray-800">User Info</h1>
                    <p class="mb-4"></p>

                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            <h6 class="m-0 font-weight-bold text-primary"><a href="/member2/list"><button>사용자 목록</button></a></h6>
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">

						   <div class="form-group row">
                               <div class="col-sm-6 mb-3 mb-sm-0">
                               	   <label>아이디</label>
                                   <input type="text" class="form-control" value="${member.m_id}" readonly> <!-- 보드안에 있는 여러 레코드 중에서 해당하는 번호를!-의 의미 -->
                               </div>
                           </div>
                           <div class="form-group row">
                               <div class="col-sm-6 mb-3 mb-sm-0">
                               	   <label>비밀번호</label>
                                   <input type="text" class="form-control" value="${member.m_passwd}" readonly>
                               </div>
                           </div>
                           <div class="form-group row">
                               <div class="col-sm-6 mb-3 mb-sm-0">
                               	   <label>이름</label>
                                   <input type="text" class="form-control" value="${member.m_name}" readonly>
                               </div>
                           </div>
                           <div class="form-group row">
                               <div class="col-sm-6 mb-3 mb-sm-0">
                               	   <label>전화번호</label>
                                   <input type="text" class="form-control" value="${member.m_phone}" readonly>
                               </div>
                           </div>
                           <div class="form-group row">
                               <div class="col-sm-6 mb-3 mb-sm-0">
                               	   <label>이메일</label>
                                   <input type="text" class="form-control" value="${member.m_email}" readonly>
                               </div>
                           </div>
                           <div class="form-group row">
                               <div class="col-sm-6 mb-3 mb-sm-0">
                               	   <label>등록일</label>
                                   <input type="text" class="form-control" value="${member.m_rdate}" readonly>
                               </div>
                           </div>
                           <div class="form-group row">
                               <div class="col-sm-6 mb-3 mb-sm-0">
                                   <a href="/member2/update?m_id=${member.m_id}"><button>수정</button></a>
                                   &nbsp;&nbsp;&nbsp;
                                   <a href="/member2/delete?m_id=${member.m_id}"><button>삭제</button></a>
                               </div>
                           </div>
                       	
					</div>
				</div>
			</div>
		</div>			
<%@include file="../include/All_foot.jsp" %>
