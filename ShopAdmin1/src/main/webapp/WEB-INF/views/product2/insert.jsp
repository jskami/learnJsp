<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@include file="../include/product_head.jsp" %>
<br>
                    <!-- Page Heading -->
                    <h1 class="h3 mb-2 text-gray-800">Product Insert</h1>
                    <p class="mb-4"></p>

                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            <h6 class="m-0 font-weight-bold text-primary"><a href="/product2/list"><button>상품 리스트</button></a></h6>
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">

						<form method="post" action="/product2/insert" class="user">
                           <div class="form-group row">
                               <div class="col-sm-6 mb-3 mb-sm-0">
                               	   <label>상품명</label>
                                   <input type="text" class="form-control" name="p_name" placeholder="상품명">
                               </div>
                           </div>
                           <div class="form-group row">
                               <div class="col-sm-6 mb-3 mb-sm-0">
                               	   <label>단가</label>
                                   <input type="text" class="form-control" name="p_price" placeholder="단가">
                               </div>
                           </div>
                           <div class="form-group row">
                               <div class="col-sm-6 mb-3 mb-sm-0">
                                   <input type="submit" value="상품등록" class="form-control" placeholder="제출">
                               </div>
                           </div>
                       	</form>
					</div>
				</div>
			</div>
		</div>			
<%@include file="../include/product_foot.jsp" %>
