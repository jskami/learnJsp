<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@include file="../include/header.jsp" %>
                <!-- Begin Page Content -->
                <div class="container-fluid">

                    <!-- Page Heading -->
                    <h1 class="h3 mb-2 text-gray-800">LIST</h1>
                    <p class="mb-4"></p>

                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            <h6 class="m-0 font-weight-bold text-primary"><a href="/notice/insert">게시판 글작성</a></h6>
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-bordered" width="100%" cellspacing="0">
                                    <thead>    
                                        <tr>
                                            <td>번호</td>
                                            <td>제목</td>
                                            <td>작성자</td>
                                            <td>작성일</td>
                                        </tr>
                                	</thead>
                                	<tbody>
                                		<c:forEach items="${list}" var="notice"> <%-- ${list}내의 이름이 model.addAttribute(이름, 메서드) 여기서의 이름과 같아야 한다. / 컨트롤해서 넘긴 모델을 이렇게 받아야 한다. 글이 5개 있다면 5번 반복하면서 보드에 저장한다는 의미 --%>
                                			<tr>
                                				<td>${notice.n_num}</td>
                                				<td><a href="/notice/view?b_num=${notice.n_num}&pageNum=${pageview.page.pageNum}">${notice.n_subject}</a></td> <!-- <- 링크를 넣어보자, 절대로 중간에 공백이 존재해서는 안된다. 제목에 글번호를 불러와 링크를 거는중이야. -->
                                				<td>${notice.n_name}</td>
                                				<td><fmt:formatDate pattern="yyyy-mm-dd" value="${notice.n_date}"/>
                                			</tr>
                                		</c:forEach>
                                		<tr>
                                			<td colspan="4">
                                			<c:if test="${pageview.prev}"><!-- 이전 페이지, 현재 페이지에서 -1하면 돼 -->
                                				<a href="/notic/list?pageNum=1">[<<]</a>&nbsp;&nbsp;
                                				<a href="/notic/list?pageNum=${pageview.startPage-1}">[prev]</a>&nbsp;&nbsp;
                                			</c:if>
                               				<c:forEach var="num" begin="${pageview.startPage}" end="${pageview.endPage}">
                               					<c:if test="${pageview.page.pageNum == num}">
                               						<b><a href="/notic/list?pageNum=${num}">[${num}]</a></b>&nbsp;&nbsp;
                               					</c:if>
                               					<c:if test="${pageview.page.pageNum != num}">
                               						<a href="/notic/list?pageNum=${num}">[${num}]</a>&nbsp;&nbsp;
                               					</c:if>
                               				</c:forEach>
                                			<c:if test="${pageview.next}"><!-- 다음 페이지, 끝 페이지에서 +1하면 돼 -->
                                				<a href="/notic/list?pageNum=${pageview.endPage+1}">[next]</a>&nbsp;&nbsp;
	                                			<a href="/notic/list?pageNum=${pageview.realend}">[>>]</a>&nbsp;&nbsp;	
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
            