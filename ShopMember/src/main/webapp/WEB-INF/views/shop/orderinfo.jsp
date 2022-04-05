<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@include file="../include/All_head.jsp" %>
<br>
					<!-- Page Heading -->
                    <h1 class="h3 mb-2 text-gray-800">세션 확인</h1>
                    <p> 관리자 ID : ${a_id}</p>

                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            <h6 class="m-0 font-weight-bold text-primary"><a href="/shop2/list">상품목록</a> |
                            &nbsp;&nbsp;&nbsp;</h6>
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
                                            <td>금액</td>
                                        </tr>
                                	</thead>
                                	<tbody>
                                		<c:forEach items="${list}" var="ordersub"> <%-- ${list}내의 이름이 model.addAttribute(이름, 메서드) 여기서의 이름과 같아야 한다. / 컨트롤해서 넘긴 모델을 이렇게 받아야 한다. 글이 5개 있다면 5번 반복하면서 보드에 저장한다는 의미 --%>
                                			<tr>
                                				<td>${ordersub.os_code}</td>
                                				<td>${ordersub.p_code}</td>
                                				<td>${ordersub.p_name}</td>
                                				<td><img src="/resources/product/${ordersub.p_code}.jpg" height="100"></td>
                                				<td>${ordersub.p_price}</td>
                                				<td>${ordersub.os_money}</td>
                                			</tr>
                                		</c:forEach> 
                                		<tr>
                                		<!-- <td colspan="9">총 금액 : ${carttotal.cm_total}원</td> -->
	                               			<td colspan="6">
	                               				<b>총 합계 :<fmt:formatNumber value="${ordertotal.om_total}" pattern="#,###" />원</b> <!-- #,###은 세 자리 마다 콤마로 끊어서 표현해라-의 의미  -->
	                               				<c:if test="${not empty om_code}"> <!-- cm_code가 비어있다면을 의미하는 것이 이렇게 empty를 쓴다. 비어있지 않다면? not empty이다. -->
	                               			
	                               				</c:if>
                                		</tr>
                                	</tbody>
                                </table>
                            </div>
                        </div>
                    </div>

                </div>
                <!-- /.container-fluid -->
                
<%@include file="../include/All_foot.jsp" %>         