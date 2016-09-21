<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>Welcome to EasyApp!</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />

	<link rel="stylesheet" type="text/css"
		href="node_modules/bootstrap/dist/css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css"
		href="node_modules/bootstrap/dist/css/bootstrap-theme.min.css" />
	<link rel="stylesheet" type="text/css"
		href="webjars/font-awesome/4.6.3/css/font-awesome.min.css" />
	<link rel="stylesheet" type="text/css" href="css/easyapp.css" />

	<script type="text/javascript" src="webjars/jquery/2.2.4/jquery.min.js"></script>
	<script type="text/javascript"
		src="node_modules/bootstrap/dist/js/bootstrap.min.js"></script>
    
    <!-- 1. Load libraries -->
    <!-- Polyfill(s) for older browsers -->
    <script src="node_modules/core-js/client/shim.min.js"></script>
    <script src="node_modules/zone.js/dist/zone.min.js"></script>
    <script src="node_modules/reflect-metadata/Reflect.js"></script>
    <script src="node_modules/systemjs/dist/system.src.js"></script>
    
    <!-- 2. Configure SystemJS -->
    <script src="js/systemjs.config.js"></script>
    <script>
        System.import('app').catch(function(err){ console.error(err); });
    </script>
  </head>
  
  <!-- 3. Display the application -->
  <body>
    <my-app>Loading...</my-app>
  </body>
</html>
