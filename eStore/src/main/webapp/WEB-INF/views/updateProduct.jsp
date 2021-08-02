<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>

<link href="<c:url value="/resources/css/main.css"/>" rel="stylesheet">

<div class="container-wrapper">
	<div class="container">
		<h1>Update Product</h1>
		<p class="lead">Fill the below information to add a product:</p>
		
		<!-- action : 만약 하단의 submit이 눌렸을 때 수행될 url이 넣어져 있음, method로는 post로 보내게 되어있음 -->
		<!-- modelAttribute(여기에서는 product)의 내용을 가져와 form에 채워 넣어 준다. 
		사용자가 작성 중 무언가를 잘못입력했을때 입력해두었던 내용들이 날아가면 안되기 때문.   -->
		<sf:form action="${pageContext.request.contextPath}/admin/productInventory/updateProduct"
			method="post" modelAttribute="product" enctype="multipart/form-data">
			
			<!-- web form에 보이지는 않게 id 값 전달 -->
			<sf:hidden path="id"/>
			
			<!-- 아래에서 sf: path 부분은 모두 product(modelAttribute)의 name, category...이다.
				prodcut의 name,categroy..들을 가져와서 기본적으로 내용을 채워준다.  -->
			<!-- name을 입력 받는 부분 -->
			<!-- 그룹핑하기 위해 form-group이라는 클래스를 줌  -->
			<div class="form-group">
				<label for="name">Name</label>
				<sf:input path="name" id="name" class="form-control"/>
				<sf:errors path="name" cssStyle="color:#ff0000;"/>
			</div>
			
			<!-- category를 입력 받는 부분 -->
			<div class="form-group">
				<label for="category">Category: </label>
				<sf:radiobutton path="category" id="category" value="컴퓨터"/>컴퓨터
				<sf:radiobutton path="category" id="category" value="가전"/>가전
				<sf:radiobutton path="category" id="category" value="잡화"/>잡화
			</div>
			
			<!-- 상품 설명을 입력 받는 부분 -->
			<div class="form-group">
				<label for="description">Description</label>
				<sf:textarea path="description" id="description" class="form-control"/>
			</div>
			
			<!-- 가격을 입력 받는 부분 -->
			<div class="form-group">
				<label for="price">Price</label>
				<sf:input path="price" id="price" class="form-control"/>
				<sf:errors path="price" cssStyle="color:#ff0000;"/>
			</div>
			
			<!-- 재고를 입력 받는 부분 -->
			<div class="form-group">
				<label for="unitInStock">UnitInStock</label>
				<sf:input path="unitInStock" id="unitInStock" class="form-control"/>
				<sf:errors path="unitInStock" cssStyle="color:#ff0000;"/>
			</div>
			
			<!-- 제조사를 입력 받는 부분 -->
			<div class="form-group">
				<label for="manufacturer">Manufacturer</label>
				<sf:input path="manufacturer" id="manufacturer" class="form-control"/>
				<sf:errors path="manufacturer" cssStyle="color:#ff0000;"/>
			</div>
			
			<!-- 파일을 업로드 하는 부분 -->
			<div class="form-group">
				<label for="productImage">Upload Picture</label>
				<sf:input path="productImage" id="productImage" type="file" class="form-control"/>
			</div>
			
			<!-- Submit 버튼 -->
			<button type="submit" class="btn btn-primary">Submit</button>
			<!-- Cancel 버튼 -->
			<a href="<c:url value="/admin/productInventory"/>" class="btn btn-dark">Cancel</a>
		</sf:form>

	</div>
</div>

