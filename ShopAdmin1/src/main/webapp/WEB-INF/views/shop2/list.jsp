<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@include file="../include/shop_head.jsp" %>
<br>
					<!-- Page Heading -->
                    <h1 class="h3 mb-2 text-gray-800">세션 확인</h1>
                    <p> 관리자 ID : ${a_id}</p>
                    <p> 사용자 ID : ${m_id}</p>
                <!-- Begin Page Content -->
                <div class="container-fluid">

                    <!-- Page Heading -->
                    <h1 class="h3 mb-2 text-gray-800">Shop List</h1>
                    
                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            <h6 class="m-0 font-weight-bold text-primary"><a href="/product2/insert">상품등록</a> |
                            <a href="/shop2/cartinfo">장바구니목록</a></h6>
                        </div>
                        
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-bordered" width="50%" cellspacing="0">
                                   
                                	<tbody>
                                		<c:set var="cnt" value="0" /> <!-- jstl에서 변수 만드는 키워드는 set이다. 변수 선언! / 현재 카운트가 몇개인지 모르는 상태 / foreach를 이용해 list를 카운트 하고 싶어서 새로 만든 변수이고 그래서 foreach전에 선언한다. 한 행에 n개씩 나타내고 싶어서 쓰는 것이고, 각 행마다 연산이 필요하며 자동 증가 값이 필요하기 때문에 cnt를 추가 선언 한것이다. 반드시 필요한 문법 -->                          		
                                		<c:forEach items="${list}" var="product"> <!-- 컨트롤러에서 넘어온 변수 / 현재 카운트가 몇개인지 아는 상태 / 메인은 item이고 이 안의 리스트가 100개라고 해도 아래의 폼에서 begin, end를 통해서 정해진 값 만큼 나타난다. -->
                                			<c:choose>
                                			
                                			<c:when test="${cnt % 3 == 0}"> <!-- cnt변수를 3으로 나눈 나머지가 0과 같다면 -->
                                				<tr>
                                					<td>
                                						<table>
                                							<tr>
                                								<td>${product.p_code}</td>               					
                                							</tr>
                                							<tr>	
                                								<td><img src="/resources/product/${product.p_code}.jpg" width="250" height="150"></td>
                                							</tr>	
                                							<tr>	
                                								<td>${product.p_name}</td>
                                							</tr>
                                							<tr>	
                                								<td>${product.p_price}</td>
                                							</tr>
                                							<tr>
                                								<td>
                                									<form method="post" action="/shop2/cart">
                                										<!-- 상품코드와 수량을 넘길거야 / -->
                                										<input type="hidden" name="p_code" value="${product.p_code}">
                                										<select name="cs_cnt">
                                											<c:forEach var="count" begin="1" end="30" step="1"><!-- step은 생략 가능 -->
                                												<option value="${count}">${count}개</option>
                                											</c:forEach>
                                										</select>&nbsp;&nbsp;
                                										<input type="submit" value="장바구니">
                                									</form>
                                								</td>
                                							</tr>				
                                						</table>
                                					<td>	          				
                                			</c:when>
                                			                                 			
                                			<c:when test="${cnt % 3 == 2}">	<!-- cnt변수를 3으로 나눈 나머지가 2dhk 같다면 -->
                                				
                                					<td>
                                						<table>
                                							<tr>
                                								<td>${product.p_code}</td>               					
                                							</tr>
                                							<tr>	
                                								<td><img src="/resources/product/${product.p_code}.jpg" width="250" height="150"></td>
                                							</tr>	
                                							<tr>	
                                								<td>${product.p_name}</td>
                                							</tr>
                                							<tr>	
                                								<td>${product.p_price}</td>
                                							</tr>
                                							<tr>
                                								<td>
                                									<form method="post" action="/shop2/cart">
                                										<!-- 상품코드와 수량을 넘길거야 / -->
                                										<input type="hidden" name="p_code" value="${product.p_code}">
                                										<select name="cs_cnt">
                                											<c:forEach var="count" begin="1" end="30" step="1"><!-- step은 생략 가능 -->
                                												<option value="${count}">${count}개</option>
                                											</c:forEach>
                                										</select>&nbsp;&nbsp;
                                										<input type="submit" value="장바구니">
                                									</form>
                                								</td>
                                							</tr>				
                                						</table>
                                					<td>
                                				</tr>	
                                			</c:when>
                                			<c:otherwise> <!-- cnt 변수를 나눈 나머지가 이것도 저것도 아닐 때 -->
                                					
                                					<td>
                                						<table>
                                							<tr>
                                								<td>${product.p_code}</td>               					
                                							</tr>
                                							<tr>	
                                								<td><img src="/resources/product/${product.p_code}.jpg" width="250" height="150"></td>
                                							</tr>	
                                							<tr>	
                                								<td>${product.p_name}</td>
                                							</tr>
                                							<tr>	
                                								<td>${product.p_price}</td>
                                							</tr>
                                							<tr>
                                								<td>
                                									<form method="post" action="/shop2/cart">
                                										<!-- 상품코드와 수량을 넘길거야 / -->
                                										<input type="hidden" name="p_code" value="${product.p_code}">
                                										<select name="cs_cnt">
                                											<c:forEach var="count" begin="1" end="30" step="1"><!-- step은 생략 가능 -->
                                												<option value="${count}">${count}개</option>
                                											</c:forEach>
                                										</select>&nbsp;&nbsp;
                                										<input type="submit" value="장바구니">
                                									</form>
                                								</td>
                                							</tr>				
                                						</table>
                                					<td>
                                			</c:otherwise>
                                			
                                			</c:choose>
                                			
                                			<c:set var="cnt" value="${cnt+1}" /> <!-- cnt = cnt + 1  -->
                                		</c:forEach>
                                	
                                		<!--
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
                                		-->
                                	</tbody>
                                </table>
                            </div>
                        </div>
                    </div>

                </div>
                <!-- /.container-fluid -->
                
<%@include file="../include/shop_foot.jsp" %>         