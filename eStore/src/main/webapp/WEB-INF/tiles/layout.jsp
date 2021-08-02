<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Jekyll v3.8.6">
    <!-- tiles.xml의 title이라는 Attribute가 런타임 시에 넣어진다. -->
    <title><tiles:insertAttribute name="title"/></title>

    <link rel="canonical" href="https://getbootstrap.com/docs/4.4/examples/carousel/">

<!-- Bootstrap core CSS -->
<!-- resources라는 로컬파일을 접근하려면 url "/resources/"를 넣어줘야 맵핑이 됨. -->
<!-- 클라이언트가 아래의 url을 사용해서 서버에 요청하면 서버가 가져온다  -->
<link href="<c:url value="/resources/css/bootstrap.min.css"/>"
	rel="stylesheet"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">

<!-- Favicons -->
<!-- resources라는 로컬파일을 접근하려면 url "/resources/"를 넣어줘야 맵핑이 됨. -->
<!-- 클라이언트가 아래의 url을 사용해서 서버에 요청하면 서버가 가져온다  -->
<link rel="icon" href="<c:url value="/resources/favicon.ico"/>">

<!-- FontAwesome -->
<link href="<c:url value="/resources/fontAwesome/css/all.css"/>" rel="stylesheet"> <!--load all styles -->

<!-- AgularJS 쓰기 위한 것 -->
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.7.9/angular.min.js"></script>

    <style>
      .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        -ms-user-select: none;
        user-select: none;
      }

      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }
    </style>
    <!-- Custom styles for this template -->
    <link href="carousel.css" rel="stylesheet">
  </head>
  
  <body>
  <!-- tiles.xml에서의 menu, body, footer라는 Attribute를 넣어준다. 런타임 시에 넣어진다. -->
	<tiles:insertAttribute name="menu"/>
	<tiles:insertAttribute name="body"/>
	<tiles:insertAttribute name="footer"/>


</main>
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script>window.jQuery || document.write('<script src="/docs/4.4/assets/js/vendor/jquery.slim.min.js"><\/script>')</script>
<script src="/docs/4.4/dist/js/bootstrap.bundle.min.js" integrity="sha384-6khuMg9gaYr5AxOqhkVIODVIvm9ynTT5J4V1cfthmT+emCG6yVmEZsRHdxlotUnm" crossorigin="anonymous"></script>

</body>
</html>

