<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@include file="../include/product_head.jsp" %>
<br>
					<!-- Page Heading -->
                    <h1 class="h3 mb-2 text-gray-800">세션 확인</h1>
                    <p> 관리자 ID : ${a_id}</p>
                    
                <!-- Begin Page Content -->
                <div class="container-fluid">

                    <!-- Page Heading -->
                    <h1 class="h3 mb-2 text-gray-800">Product List</h1>

                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            <h6 class="m-0 font-weight-bold text-primary"><a href="/product2/insert"><button>상품등록</button></a></h6>
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-bordered" width="100%" cellspacing="0">
                                    <thead align="center" valign="center">    
                                        <tr>
                                            <td>상품코드</td>
                                            <td>상품명</td>
                                            <td>단가</td>
                                            <td>이미지</td>
                                            <td>작성일</td>
                                            <td>수정일</td>
                                        </tr>
                                	</thead>
                                	<tbody align="center" valign="center">
                                		<c:forEach items="${list}" var="product"> <%-- ${list}내의 이름이 model.addAttribute(이름, 메서드) 여기서의 이름과 같아야 한다. / 컨트롤해서 넘긴 모델을 이렇게 받아야 한다. 글이 5개 있다면 5번 반복하면서 보드에 저장한다는 의미 --%>
                                			<tr>
                                				<td>${product.p_code}</td>
                                				<td><a href="/product2/view?p_code=${product.p_code}">${product.p_name}</a></td>
                                				<td>${product.p_price}</td>
                                				<td><img src="/resources/product/${product.p_code}.jpg" height="50"></td>
                                				<td><fmt:formatDate pattern="yyyy-mm-dd" value="${product.p_rdate}"/>
                                				<td><fmt:formatDate pattern="yyyy-mm-dd" value="${product.p_udate}"/>
                                			</tr>
                                		</c:forEach>
                                		<tr>
                                    		<td colspan="6">
                                    			<c:if test="${pageview.prev}">
                                    				<a href="/product2/list?pageNum=1">[<<]</a>&nbsp;&nbsp;
                                    				<a href="/product2/list?pageNum=${pageview.startPage-1}">[prev]</a>&nbsp;&nbsp;
                                    			</c:if>
                                    			<c:forEach var="num" begin="${pageview.startPage}" end="${pageview.endPage}">
                                    				<c:if test="${pageview.page.pageNum == num}">
                                    					<b><a href="/product2/list?pageNum=${num}">[${num}]</a></b>&nbsp;&nbsp;
                                    				</c:if>
                                    				<c:if test="${pageview.page.pageNum != num}">
                                    					<a href="/product2/list?pageNum=${num}">[${num}]</a>&nbsp;&nbsp;
                                    				</c:if>
                                    			</c:forEach>
                                    			<c:if test="${pageview.next}">
                                    				<a href="/product2/list?pageNum=${pageview.endPage+1}">[next]</a>&nbsp;&nbsp;
                                    				<a href="/product2/list?pageNum=${pageview.realend}">[>>]</a>&nbsp;&nbsp;
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
                
<%@include file="../include/product_foot.jsp" %>
            