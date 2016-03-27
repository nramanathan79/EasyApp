<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Countries</title>
<link rel="stylesheet" type="text/css"
	href="webjars/bootstrap/3.3.6/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css"
	href="webjars/bootstrap/3.3.6/css/bootstrap-theme.min.css" />
<link rel="stylesheet" type="text/css" href="resources/css/easyapp.css" />
<script type="text/javascript" src="webjars/jquery/2.2.2/jquery.min.js"></script>
<script type="text/javascript"
	src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="webjars/angularjs/1.5.2/angular.min.js"></script>
<script type="text/javascript"
	src="webjars/angularjs/1.5.2/angular-resource.min.js"></script>
</head>
<body>
	<div class="page-layout">
		<article class="page-main-article">
			<c:import url="dataTable.jsp"></c:import>
		</article>
	</div>
</body>
</html>