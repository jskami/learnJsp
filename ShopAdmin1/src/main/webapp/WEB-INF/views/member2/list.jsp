<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@include file="../include/All_head.jsp" %>
<br>
					<!-- Page Heading -->
                    <h1 class="h3 mb-2 text-gray-800">세션 확인</h1>
                    <p> 관리자 ID : ${a_id}</p>
                    <p> 사용자 ID : ${m_id}</p>
                    
                <!-- Begin Page Content -->
                <div class="container-fluid">

                    <!-- Page Heading -->
                    <h1 class="h3 mb-2 text-gray-800">Member List</h1>
                    
                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            <h6 class="m-0 font-weight-bold text-primary"><a href="/member2/insert"><button>사용자 등록</button></a></h6>
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-bordered" width="100%" cellspacing="0">
                                    <thead>    
                                        <tr>
                                            <td>아이디</td>
                                            <td>비밀번호</td>
                                            <td>이름</td>
                                            <td>등록일</td>
                                        </tr>
                                	</thead>
                                	<tbody>
                                		<c:forEach items="${list}" var="member"> <%-- ${list}내의 이름이 model.addAttribute(이름, 메서드) 여기서의 이름과 같아야 한다. / 컨트롤해서 넘긴 모델을 이렇게 받아야 한다. 글이 5개 있다면 5번 반복하면서 보드에 저장한다는 의미 --%>
                                			<tr>
                                				<td>${member.m_id}</td>
                                				<td>${member.m_passwd}</td>
                                				<td><a href="/member2/view?m_id=${member.m_id}">${member.m_id}</a></td>
                                				<td><fmt:formatDate pattern="yyyy-mm-dd" value="${member.m_rdate}"/>
                                			</tr>
                                		</c:forEach>
                                	</tbody>
                                </table>
                            </div>
                        </div>
                    </div>

                </div>
                <!-- /.container-fluid -->
                
<%@include file="../include/All_foot.jsp" %>
            