<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@include file="../include/header.jsp" %>
                <!-- Begin Page Content -->
                <div class="container-fluid">

                    <!-- Page Heading -->
                    <h1 class="h3 mb-2 text-gray-800">장바구니 정보</h1>
                    <p> 사용자 ID : ${m_id}</p>
					<p> 사용자 NAME : ${m_name}</p>
                    <!-- <a href="/member/login">로그인</a> -->
                    <p class="mb-4"></p>

                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            <h6 class="m-0 font-weight-bold text-primary"><a href="/shop/list">상품목록</a></h6>
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-bordered" width="50%" cellspacing="0">
                                	<thead>    
                                        <tr>
                                       		<td>상세코드</td>
                                            <td>상품코드</td>
                                            <td>상품명</td>
                                            <td>이미지</td>
                                            <td>단가</td>
                                            <td>수량</td>
                                            <td>금액</td>
                                            <td>작성일</td>
                                            <td>수정일</td>
                                        </tr>
                                	</thead>
                                	<tbody>
                                		<c:forEach items="${list}" var="cartsub"> <%-- ${list}내의 이름이 model.addAttribute(이름, 메서드) 여기서의 이름과 같아야 한다. / 컨트롤해서 넘긴 모델을 이렇게 받아야 한다. 글이 5개 있다면 5번 반복하면서 보드에 저장한다는 의미 --%>
                                			<tr>
                                				<td>${cartsub.cs_code}</td>
                                				<td>${cartsub.p_code}</td>
                                				<td>${cartsub.p_name}</td>
                                				<td><img src="/resources/product/${cartsub.p_code}.jpg" height="100"></td>
                                				<td>${cartsub.p_price}</td>
                                				<td>${cartsub.cs_cnt}</td>
                                				<td>${cartsub.cs_money}</td>
                                				<td><fmt:formatDate pattern="yyyy-mm-dd" value="${cartsub.cs_rdate}"/>
                                				<td><fmt:formatDate pattern="yyyy-mm-dd" value="${cartsub.cs_udate}"/>
                                			</tr>
                                		</c:forEach> 
                                		<tr>
                                			<td colspan="9">총 금액 : ${carttotal.cm_total}원</td>
                                			<%-- <td colspan="9">
                                				<b>총 합계 :<fmt:fomatNumber value="${carttotal.cm_total}" pattern="#,###" >원</fmt:fomatNumber></b>
                                				<!-- #,###은 세 자리 마다 콤마로 끊어서 표현해라-의 의미  -->
                                			</td> --%>
                                			
                                		</tr>
                                	</tbody>
                                </table>
                            </div>
                        </div>
                    </div>

                </div>
                <!-- /.container-fluid -->
                
<%@include file="../include/footer.jsp" %>         