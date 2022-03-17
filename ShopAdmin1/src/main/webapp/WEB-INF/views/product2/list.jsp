<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@include file="../include/header.jsp" %>
                <!-- Begin Page Content -->
                <div class="container-fluid">

                    <!-- Page Heading -->
                    <h1 class="h3 mb-2 text-gray-800">상품 LIST</h1>
                    <p> 사용자 ID : ${m_id}</p>
					<p> 사용자 NAME : ${m_name}</p>
                    <!-- <a href="/member/login">로그인</a> -->
                    <p class="mb-4"></p>

                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            <h6 class="m-0 font-weight-bold text-primary"><a href="/product/insert">상품등록</a></h6>
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-bordered" width="100%" cellspacing="0">
                                    <thead>    
                                        <tr>
                                            <td>상품코드</td>
                                            <td>상품명</td>
                                            <td>단가</td>
                                            <td>이미지</td>
                                            <td>작성일</td>
                                            <td>수정일</td>
                                        </tr>
                                	</thead>
                                	<tbody>
                                		<c:forEach items="${list}" var="product"> <%-- ${list}내의 이름이 model.addAttribute(이름, 메서드) 여기서의 이름과 같아야 한다. / 컨트롤해서 넘긴 모델을 이렇게 받아야 한다. 글이 5개 있다면 5번 반복하면서 보드에 저장한다는 의미 --%>
                                			<tr>
                                				<td>${product.p_code}</td>
                                				<td><a href="/product/view?p_code=${product.p_code}">${product.p_name}</a></td>
                                				<td>${product.p_price}</td>
                                				<td><img src="/resources/product/${product.p_code}.jpg" height="50"></td>
                                				<td><fmt:formatDate pattern="yyyy-mm-dd" value="${product.p_rdate}"/>
                                				<td><fmt:formatDate pattern="yyyy-mm-dd" value="${product.p_udate}"/>
                                			</tr>
                                		</c:forEach>
                                	</tbody>
                                </table>
                            </div>
                        </div>
                    </div>

                </div>
                <!-- /.container-fluid -->
                
<%@include file="../include/footer.jsp" %>
            