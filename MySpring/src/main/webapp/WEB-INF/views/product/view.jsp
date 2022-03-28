<!-- // 여기에서 활용할 샘플은 insert.jsp이다.(형식이 비슷하니까) -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@include file="../include/header.jsp" %>
<script>
	function ImgWinOpen(p_code) {
		window.open("/product/imgupload?p_code="+p_code,"","width=500,height=200,toolbar=no,menubar=no,status=no,scrollbars=no"); // 자스 내부 함수로서 윈도우.open은 새창 띄우기이다. ("url위치(절대경로)/파일을 업로드하기 위한 위치/불러올 데이터 명칭="+동일한 데이터 명칭,"","속성값(참고용으로 아무거나 넣어보자)")
	}                         // 이것을 받아낼 수 있는 컨트롤러를 작성해야한다.
</script>

				<!-- Begin Page Content -->
                <div class="container-fluid">

                    <!-- Page Heading -->
                    <h1 class="h3 mb-2 text-gray-800">상품정보</h1>
                    <p class="mb-4"></p>

                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            <h6 class="m-0 font-weight-bold text-primary"><a href="/product/list">상품 목록으로 이동</a></h6>
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
						   
						   <div class="form-group row">
                               <div class="col-sm-6 mb-3 mb-sm-0">
                               	   <label>상품번호</label>
                                   <input type="text" class="form-control" value="${product.p_code}" readonly>
                               </div>
                           </div>
						   <div class="form-group row">
                               <div class="col-sm-6 mb-3 mb-sm-0">
                               	   <label>상품명</label>
                                   <input type="text" class="form-control" value="${product.p_name}" readonly>
                               </div>
                           </div>
                           <div class="form-group row">
                               <div class="col-sm-6 mb-3 mb-sm-0">
                               	   <label>단가</label>
                                   <input type="text" class="form-control" value="${product.p_price}" readonly>
                               </div>
                           </div>
                           <div class="form-group row">
                               <div class="col-sm-6 mb-3 mb-sm-0">
                               	   <label>이미지</label>
                                   <img src="/resources/product/${product.p_code}.jpg" height="200">
                               </div>
                           </div>
                           <div class="form-group row">
                               <div class="col-sm-6 mb-3 mb-sm-0">
                               	   <label>이미지 등록</label>
                                   <input type="button" value="이미지등록" class="form-control" onclick="ImgWinOpen('${product.p_code}')">
                               </div>
                           </div>
                           <div class="form-group row">
                               <div class="col-sm-6 mb-3 mb-sm-0">
                               	   <label>작성일</label>
                                   <input type="text" class="form-control" value="${product.p_rdate}" placeholder="작성일" readonly>
                               </div>
                           </div>
                           <div class="form-group row">
                               <div class="col-sm-6 mb-3 mb-sm-0">
                               	   <label>수정일</label>
                                   <input type="text" class="form-control" value="${product.p_udate}" placeholder="수정일" readonly>
                               </div>
                           </div>
                           <div class="form-group row">
                               <div class="col-sm-6 mb-3 mb-sm-0">
                                   <a href="/product/list"><button>수정</button></a>
                                   &nbsp;&nbsp;&nbsp;
                                   <a href="/product/delete?p_code=${product.p_code}"><button>삭제</button></a>
                               </div>
                           </div>
                       	
					</div>
				</div>
			</div>
		</div>			
<%@include file="../include/footer.jsp" %>
