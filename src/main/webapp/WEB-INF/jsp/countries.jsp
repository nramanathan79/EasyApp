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
<script type="text/javascript"
	src="webjars/jquery/2.2.1/jquery.min.js"></script>
<script type="text/javascript"
	src="webjars/bootstrap/3.3.6/bootstrap.min.js"></script>
<script type="text/javascript"
	src="webjars/angularjs/1.5.0/angular.min.js"></script>
<script type="text/javascript"
	src="webjars/angularjs/1.5.0/angular-resource.min.js"></script>
<script type="text/javascript" src="resources/js/dataTable.js"></script>
</head>
<body>
	<div class="page-layout">
		<article class="page-main-article">
			<div ng-app="dataTable" ng-controller="dataTableController"
				ng-init="initializeResource('countries')">
				<form name="dataTableRowEdit" class="form-inline">
					<div class="container-fluid">
						<div class="row" ng-show="error">
							<div class="alert alert-danger col-md-12">{{error}}</div>
						</div>
						<div class="row">
							<div class="col-md-4">
								<div class="row">
									<div class="col-md-3">
										<label>Search Table</label>
									</div>
									<div class="col-md-9">
										<input type="text" name="searchText" placeholder="search"
											ng-model="searchText"></input>
									</div>
								</div>
							</div>
							<div class="col-md-4">
								<div class="row">
									<div class="col-md-3">
										<label>Sort Selection</label>
									</div>
									<div class="col-md-5">
										<label class="radio-inline"> <input type="radio"
											name="sortSelection" ng-init="sortSelection='Single'"
											ng-model="sortSelection" value="Single"
											ng-change="changeSortSelection()" checked="checked"></input>Single
										</label> <label class="radio-inline"> <input type="radio"
											name="sortSelection" ng-model="sortSelection"
											value="Multiple" ng-change="changeSortSelection()"></input>Multiple
										</label>
									</div>
									<div class="col-md-4">
										<label><a href="#" ng-click="clearSort()">Clear
												All Sort</a></label>
									</div>
								</div>
							</div>
							<div class="col-md-4">
								<div class="row text-right">
									<div class="col-md-8">
										<label><a href="#" ng-click="clearFilters()">Clear
												All Filters</a></label>
									</div>
									<div class="col-md-4">
										<button class="btn btn-xs btn-primary" type="button"
											ng-disabled="records.editing" ng-click="listRecords()">Refresh</button>
									</div>
								</div>
							</div>
						</div>
						<table class="table table-striped table-bordered">
							<tr class="text-uppercase">
								<th><a href="#" ng-click="addSort('countryName')">Country<span
										class="required">&nbsp;*</span></a>
									<div class="pull-right">
										<span ng-show="showSortIndex('countryName') > 0">{{showSortIndex('countryName')}}</span><img
											src="images/sortascending.png"
											ng-show="sortBy.indexOf('countryName') >= 0"></img><img
											src="images/sortdescending.png"
											ng-show="sortBy.indexOf('-countryName') >= 0"></img>
									</div></th>
								<th><a href="#" ng-click="addSort('isoAlpha2Code')">ISO
										2 Code<span class="required">&nbsp;*</span>
								</a>
									<div class="pull-right">
										<span ng-show="showSortIndex('isoAlpha2Code') > 0">{{showSortIndex('isoAlpha2Code')}}</span><img
											src="images/sortascending.png"
											ng-show="sortBy.indexOf('isoAlpha2Code') >= 0"></img><img
											src="images/sortdescending.png"
											ng-show="sortBy.indexOf('-isoAlpha2Code') >= 0"></img>
										<div class="dropdown" style="display: inline;">
											<a href="#" data-toggle="dropdown" class="dropdown-toggle"><b
												class="caret"></b></a>
											<ul class="dropdown-menu">
												<li><a href="#">Clear All</a></li>
												<li><a href="#">Select All</a></li>
												<li class="separator"></li>
												<li><a href="#">Something</a></li>
											</ul>
										</div>
									</div></th>
								<th><a href="#" ng-click="addSort('isoAlpha3Code')">ISO
										3 Code<span class="required">&nbsp;*</span>
								</a><span class="pull-right"><img
										src="images/sortascending.png"
										ng-show="sortBy.indexOf('isoAlpha3Code') >= 0" /><img
										src="images/sortdescending.png"
										ng-show="sortBy.indexOf('-isoAlpha3Code') >= 0" /> </span></th>
								<th class="text-right"><a href="#"
									ng-click="addSort('isoNumericCode')">ISO Numeric Code<span
										class="required">&nbsp;*</span></a><span class="pull-right"><img
										src="images/sortascending.png"
										ng-show="sortBy.indexOf('isoNumericCode') >= 0" /><img
										src="images/sortdescending.png"
										ng-show="sortBy.indexOf('-isoNumericCode') >= 0" /></span></th>
								<th class="text-right"><a href="#"
									ng-click="addSort('callingCode')">Phone Code<span
										class="required">&nbsp;*</span><img
										src="images/sortascending.png"
										ng-show="sortBy.indexOf('callingCode') >= 0" /></a><span
									class="pull-right"><img src="images/sortdescending.png"
										ng-show="sortBy.indexOf('-callingCode') >= 0" /></span></th>
								<th><a href="#" ng-click="addSort('currencyCode')">Currency
										Code<span class="required">&nbsp;*</span>
								</a><span class="pull-right"><img
										src="images/sortascending.png"
										ng-show="sortBy.indexOf('currencyCode') >= 0" /><img
										src="images/sortdescending.png"
										ng-show="sortBy.indexOf('-currencyCode') >= 0" /> </span></th>
								<th><a href="#" ng-click="addSort('continent')">Continent<span
										class="required">&nbsp;*</span></a><span class="pull-right"><img
										src="images/sortascending.png"
										ng-show="sortBy.indexOf('continent') >= 0" /><img
										src="images/sortdescending.png"
										ng-show="sortBy.indexOf('-continent') >= 0" /></span></th>
								<th><a href="#" ng-click="addSort('capitalCity')">Capital</a><span
									class="pull-right"><img src="images/sortascending.png"
										ng-show="sortBy.indexOf('capitalCity') >= 0" /><img
										src="images/sortdescending.png"
										ng-show="sortBy.indexOf('-capitalCity') >= 0" /></span></th>
								<th><a href="#" ng-click="addSort('capitalCityTimeZone')">Capital
										Time Zone</a><span class="pull-right"><img
										src="images/sortascending.png"
										ng-show="sortBy.indexOf('capitalCityTimeZone') >= 0" /><img
										src="images/sortdescending.png"
										ng-show="sortBy.indexOf('-capitalCityTimeZone') >= 0" /> </span></th>
								<th>Action</th>
							</tr>
							<tr
								ng-repeat="record in records | filter:searchText | orderBy:sortBy">
								<td>
									<div ng-hide="record.editing">{{record.countryName}}</div>
									<div ng-show="record.editing">
										<input class="text-capitalize" type="text"
											ng-model="record.countryName" required="required"
											placeholder="Country"></input>
									</div>
								</td>
								<td>
									<div ng-hide="record.editing">{{record.isoAlpha2Code}}</div>
									<div ng-show="record.editing">
										<input class="text-uppercase" type="text"
											ng-model="record.isoAlpha2Code" required="required"
											minlength="2" maxlength="2" placeholder="ISO 2" size="2"></input>
									</div>
								</td>
								<td>
									<div ng-hide="record.editing">{{record.isoAlpha3Code}}</div>
									<div ng-show="record.editing">
										<input class="text-uppercase" type="text"
											ng-model="record.isoAlpha3Code" required="required"
											minlength="3" maxlength="3" placeholder="ISO 3" size="3"></input>
									</div>
								</td>
								<td class="text-right">
									<div ng-hide="record.editing">{{record.isoNumericCode}}</div>
									<div ng-show="record.editing">
										<input class="text-right" type="number"
											ng-model="record.isoNumericCode" required="required" min="1"
											max="999" placeholder="ISO Numeric" size="3"></input>
									</div>
								</td>
								<td class="text-right">
									<div ng-hide="record.editing">+{{record.callingCode}}</div>
									<div ng-show="record.editing">
										<input class="text-right" type="number"
											ng-model="record.callingCode" required="required" min="1"
											max="999" placeholder="Phone Code" size="3"></input>
									</div>
								</td>
								<td>
									<div ng-hide="record.editing">{{record.currencyCode}}</div>
									<div ng-show="record.editing">
										<input class="text-uppercase" type="text"
											ng-model="record.currencyCode" required="required"
											minlength="3" maxlength="3" placeholder="Currency Code"
											size="3" />
									</div>
								</td>
								<td>
									<div ng-hide="record.editing">{{record.continent}}</div>
									<div ng-show="record.editing">
										<input class="text-capitalize" type="text"
											ng-model="record.continent" required="required"
											placeholder="Continent" />
									</div>
								</td>
								<td>
									<div ng-hide="record.editing">{{record.capitalCity}}</div>
									<div ng-show="record.editing">
										<input class="text-capitalize" type="text"
											ng-model="record.capitalCity" placeholder="Capital"></input>
									</div>
								</td>
								<td>
									<div ng-hide="record.editing">{{record.capitalCityTimeZone}}</div>
									<div ng-show="record.editing">
										<input class="text-capitalize" type="text"
											ng-model="record.capitalCityTimeZone"
											placeholder="Capital Time Zone"></input>
									</div>
								</td>
								<td>
									<div ng-hide="record.editing || records.editing">
										<button class="btn btn-xs btn-primary" type="button"
											ng-click="editRecord(record)">Edit</button>
										<button class="btn btn-xs btn-default" type="button"
											ng-click="deleteRecord(record)">Delete</button>
									</div>
									<div ng-show="record.editing">
										<button class="btn btn-xs btn-primary" type="submit"
											ng-hide="record.id" ng-click="createRecord(record)"
											ng-disabled="dataTableRowEdit.$invalid">Create</button>
										<button class="btn btn-xs btn-primary" type="submit"
											ng-show="record.id" ng-click="updateRecord(record)"
											ng-disabled="dataTableRowEdit.$invalid">Save</button>
										<button class="btn btn-xs btn-default" type="button"
											ng-click="cancelRecord(record)">Cancel</button>
									</div>
								</td>
							</tr>
							<tr ng-show="records.length == 0">
								<td colspan="10">No records found!</td>
							</tr>
						</table>
						<button class="btn btn-xs btn-primary" type="button"
							ng-hide="records.editing" ng-click="addRecord()">Add</button>
					</div>
				</form>
			</div>
		</article>
	</div>
</body>
</html>