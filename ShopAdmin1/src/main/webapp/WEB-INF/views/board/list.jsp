<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@include file="../include/header.jsp" %>
                <!-- Begin Page Content -->
                <div class="container-fluid">

                    <!-- Page Heading -->
                    <h1 class="h3 mb-2 text-gray-800">리스트</h1>
                    <p class="mb-4"></p>

                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            <h6 class="m-0 font-weight-bold text-primary"><a href="/board/insert">게시판글쓰기</a>&nbsp;${user}&nbsp;${age}</h6>
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-bordered" width="100%" cellspacing="0">
                                	<thead>
                                        <tr>
                                            <th>번호</th>
                                            <th>제목</th>
                                            <th>작성자</th>
                                            <th>작성일</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    	<c:forEach items="${list}" var="board">
                                    		<tr>
                                    			<td>${board.b_num}</td>
                                    			<td><a href="/board/view?b_num=${board.b_num}&pageNum=${pageview.page.pageNum}">${board.b_subject}</a></td>
                                    			<td>${board.b_name}</td>
                                    			<td><fmt:formatDate pattern="yyyy-MM-dd" value="${board.b_date}"/>
                                    		</tr>
                                    	</c:forEach>
                                    	<tr>
                                    		<td colspan="4">
                                    			<c:if test="${pageview.prev}">
                                    				<a href="/board/list?pageNum=1">[<<]</a>&nbsp;&nbsp;
                                    				<a href="/board/list?pageNum=${pageview.startPage-1}">[prev]</a>&nbsp;&nbsp;
                                    			</c:if>
                                    			<c:forEach var="num" begin="${pageview.startPage}" end="${pageview.endPage}">
                                    				<c:if test="${pageview.page.pageNum == num}">
                                    					<b><a href="/board/list?pageNum=${num}">[${num}]</a></b>&nbsp;&nbsp;
                                    				</c:if>
                                    				<c:if test="${pageview.page.pageNum != num}">
                                    					<a href="/board/list?pageNum=${num}">[${num}]</a>&nbsp;&nbsp;
                                    				</c:if>
                                    			</c:forEach>
                                    			<c:if test="${pageview.next}">
                                    				<a href="/board/list?pageNum=${pageview.endPage+1}">[next]</a>&nbsp;&nbsp;
                                    				<a href="/board/list?pageNum=${pageview.realend}">[>>]</a>&nbsp;&nbsp;
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
