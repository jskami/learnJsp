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
                            <h6 class="m-0 font-weight-bold text-primary"><a href="/shop/list">상품목록</a> |
                            &nbsp;${m_id}&nbsp;&nbsp;${m_name}</h6>
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
                                				<td>
                                					<form method="post" action="/shop/cartupdate">
                                					<input type="hidden" name="cs_code"	value="${cartsub.cs_code}">
                                					<select name="cs_cnt">
                                						<c:forEach var="count" begin="1" end="30" step="1"> <!-- 여기는 반드시 1개 이상은 담겨있다는 조건에 따른 식이다. -->
                                							<c:if test="${count == cartsub.cs_cnt}"> <!-- 반복문으로 계산한 수량과 카트서브에 담긴 수량이 같다면// -->
                                								<option value="${count}" selected>${count}</option> <!-- 1~30까지의 인터페이스만 나타나게 될거야. 이 반복이 끝나면 30을 나타낼거고 넘어온 값이 테이블의 값과 동일하면 마킹해준다. 즉, 플스5를 5개 담았으니 수량 '5'를 나타내고 싶어! 그래서 1~30까지의 수량을 반복문으로 돌리다가 카트서브(5개 담은곳)으로부터 수량 값이 넘어오고 그 값을 기준으로 반복하다가 selected를 통해서 마킹하여 멈춘다. 그러면 실제 화면에서 5라고 나타나겠지! ㅇㅋ?  -->
                                							</c:if>
                                							<c:if test="${count != cartsub.cs_cnt}"> <!-- 반복문으로 계산한 수량과 카트서브에 담긴 수량이 다르다면// -->
                                								<option value="${count}">${count}</option>
                                							</c:if>
                                						</c:forEach>
                                					</select>
                                					<input type="submit" value="수정">
                                					<a href="/shop/cartdelete?cs_code=${cartsub.cs_code}&cm_code=${cm_code}">삭제</a>
                                					</form>
                                				</td>
                                				<td>${cartsub.cs_money}</td>
                                				<td><fmt:formatDate pattern="yyyy-mm-dd" value="${cartsub.cs_rdate}"/>
                                				<td><fmt:formatDate pattern="yyyy-mm-dd" value="${cartsub.cs_udate}"/>
                                			</tr>
                                		</c:forEach> 
                                		<tr>
                                			<!-- <td colspan="9">총 금액 : ${carttotal.cm_total}원</td> -->
                                			<td colspan="9">
                                				<b>총 합계 :<fmt:formatNumber value="${carttotal.cm_total}" pattern="#,###" />원</b> <!-- #,###은 세 자리 마다 콤마로 끊어서 표현해라-의 의미  -->
                                				<c:if test="${not empty cm_code}"> <!-- cm_code가 비어있다면을 의미하는 것이 이렇게 empty를 쓴다. 비어있지 않다면? not empty이다. -->
                                				&nbsp;&nbsp;<a href="/shop/cartdeleteall?cm_code=${cm_code}">모두삭제</a>
                                				&nbsp;&nbsp;<a href="/shop/orderinfo?cm_code=${cm_code}">구매</a>
                                				</c:if>
                                			</td>
                                		</tr>
                                	</tbody>
                                </table>
                            </div>
                        </div>
                    </div>

                </div>
                <!-- /.container-fluid -->
                
<%@include file="../include/footer.jsp" %>         