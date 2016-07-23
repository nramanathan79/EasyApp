<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Countries</title>
<link rel="stylesheet" type="text/css"
	href="webjars/bootstrap/3.3.6/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css"
	href="webjars/bootstrap/3.3.6/css/bootstrap-theme.min.css" />
<link rel="stylesheet" type="text/css"
	href="webjars/font-awesome/4.6.3/css/font-awesome.min.css" />
<link rel="stylesheet" type="text/css" href="resources/css/easyapp.css" />
<script type="text/javascript" src="webjars/jquery/2.2.4/jquery.min.js"></script>
<script type="text/javascript"
	src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="webjars/angularjs/1.5.7/angular.min.js"></script>
<script type="text/javascript"
	src="webjars/angularjs/1.5.7/angular-resource.min.js"></script>
<script type="text/javascript"
	src="webjars/angular-animate/1.5.7/angular-animate.min.js"></script>
<script type="text/javascript"
	src="webjars/angular-touch/1.5.7/angular-touch.min.js"></script>
<script type="text/javascript"
	src="webjars/angular-ui-bootstrap/1.3.3/ui-bootstrap.min.js"></script>
<script type="text/javascript" src="webjars/spin.js/2.3.2/spin.min.js"></script>
<script type="text/javascript"
	src="webjars/angular-spinner/0.8.1/angular-spinner.min.js"></script>
</head>
<body>
	<div class="page-layout">
		<article class="page-main-article">
			<c:import url="dataTable.jsp" />
		</article>
	</div>
</body>
</html>