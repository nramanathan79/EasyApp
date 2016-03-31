<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div ng-app="dataTable" ng-controller="dataTableController"
	ng-init="initializeResource('countries')">
	<form name="dataTableRowEdit" class="form-inline">
		<div class="container-fluid">
			<div class="row" ng-show="error">
				<div class="alert alert-danger col-md-12">{{error}}</div>
			</div>
			<c:if
				test="${dataTable.enableSearch || dataTable.enableMultiColumnSort || dataTable.enableColumnFilter || dataTable.enableRefresh}">
				<div class="row mtop-5">
					<div class="col-md-2">
						<c:if test="${dataTable.enableSearch}">
							<div class="row">
								<div class="col-md-12">
									<div class="input-group input-group-sm">
										<input class="form-control" type="text" name="searchText"
											placeholder="Search Table" ng-model="searchText"></input>
									</div>
								</div>
							</div>
						</c:if>
					</div>
					<div class="col-md-4">
						<c:if test="${dataTable.enableMultiColumnSort}">
							<div class="row">
								<div class="col-md-6">
									<label class="radio-inline"> <input type="radio"
										name="sortSelection" ng-init="sortSelection = 'Single'"
										ng-model="sortSelection" value="Single"
										ng-click="changeSortSelection()" checked="checked"></input>Single
										Column Sort
									</label> <label class="radio-inline"> <input type="radio"
										name="sortSelection" ng-model="sortSelection" value="Multiple"
										ng-click="changeSortSelection()"></input>Multi Column Sort
									</label>
								</div>
								<div class="col-md-6">
									<label><a href="" ng-click="clearSort()">Clear All
											Sort</a></label>
								</div>
							</div>
						</c:if>
					</div>
					<div class="col-md-4">
						<c:if test="${dataTable.enableColumnFilter}">
							<div class="row">
								<div class="col-md-12">
									<label><a href="" ng-click="clearFilters()">Clear
											All Filters</a></label> <label class="btn btm-sm btn-primary">Import<input
										style="display: none;" type="file" name="importCsv"
										id="importCsv" accept=".csv" import-csv="csvFileContent"></input>
									</label>
								</div>
							</div>
						</c:if>
					</div>
					<div class="col-md-2 text-right">
						<c:if test="${dataTable.enableRefresh}">
							<button class="btn btn-sm btn-primary" type="button"
								ng-disabled="records.editing" ng-click="listRecords()">Refresh</button>
						</c:if>
					</div>
				</div>
			</c:if>
			<%
				int numberOfColumns = 0;
			%>
			<table
				class="<c:out value="${dataTable.getStyleClassesString()}"></c:out>">
				<tr
					class="<c:out value="${dataTable.headerRow.get().getStyleClassesString()}"></c:out>">
					<c:if test="${dataTable.enableRowNum}">
						<%
							numberOfColumns++;
						%>
						<th>#</th>
					</c:if>
					<c:forEach items="${dataTable.columns}" var="column"
						varStatus="loop">
						<%
							numberOfColumns++;
						%>
						<th><c:choose>
								<c:when test="${dataTable.enableColumnSort}">
									<a href=""
										ng-click="addSort('<c:out value="${column.dataCell.field.name}"></c:out>')"><span
										<c:if test="${column.dataCell.field.required}">class="required-field"</c:if>><c:out
												value="${column.dataCell.field.label}"></c:out></span></a>
									<span class="glyphicon glyphicon-sort-by-attributes"
										ng-show="sortBy.indexOf('<c:out value="${column.dataCell.field.name}"></c:out>') >= 0"></span>
									<span class="glyphicon glyphicon-sort-by-attributes-alt"
										ng-show="sortBy.indexOf('-<c:out value="${column.dataCell.field.name}"></c:out>') >= 0"></span>
									<sup
										ng-show="showSortIndex('<c:out value="${column.dataCell.field.name}"></c:out>') > 0">{{showSortIndex('<c:out
											value="${column.dataCell.field.name}"></c:out>')}}
									</sup>
								</c:when>
								<c:otherwise>
									<span
										<c:if test="${column.dataCell.field.required}">class="required-field"</c:if>><c:out
											value="${column.dataCell.field.label}"></c:out></span>
								</c:otherwise>
							</c:choose> <c:if test="${dataTable.enableColumnFilter}">
								<div class="pull-right">
									<div class="dropdown layout-inline"
										ng-init="initializeColumnFilter('<c:out value="${column.dataCell.field.name}"></c:out>', '<c:out value="${column.dataCell.field.inputType}"></c:out>')">
										<a href=""
											id="<c:out value="${column.dataCell.field.name}"></c:out>Filter"
											class="dropdown-toggle" data-toggle="dropdown"
											aria-haspopup="true" aria-expanded="true"> <span
											class="glyphicon glyphicon-filter"
											ng-class="getFilterClass(<c:out value='${loop.index}'></c:out>)"></span>
										</a>
										<div class="dropdown-menu dropdown-menu-right padding-5"
											aria-labelledby="<c:out value="${column.dataCell.field.name}"></c:out>Filter">
											<c:set var="columnDataCell" value="${column.dataCell}"
												scope="request"></c:set>
											<c:import url="columnFilter.jsp">
												<c:param name="loopIndex" value="${loop.index}"></c:param>
											</c:import>
										</div>
									</div>
								</div>
							</c:if></th>
					</c:forEach>
					<c:if test="${dataTable.enableDataEdit}">
						<%
							numberOfColumns++;
						%>
						<th>Action</th>
					</c:if>
				</tr>
				<tr
					ng-repeat="record in records | filter:searchText | filter:columnFilterFunc | orderBy:sortBy">
					<c:if test="${dataTable.enableRowNum}">
						<td>{{$index+1}}</td>
					</c:if>
					<c:forEach items="${dataTable.columns}" var="column">
						<c:set var="inputCell" value="${column.dataCell}" scope="request"></c:set>
						<td>
							<div
								<c:if test="${!empty inputCell.id}">id="<c:out value='${inputCell.id}'></c:out>Display"</c:if>
								<c:if test="${!empty inputCell.getStyleClassesString()}">class="<c:out value='${inputCell.getStyleClassesString()}'></c:out>"</c:if>
								<c:if test="${!empty inputCell.customStyle}">style="<c:out value='${inputCell.customStyle}'></c:out>"</c:if>
								ng-hide="record.editing">
								{{record.
								<c:out value="${column.dataCell.field.name}"></c:out>
								}}
							</div>
							<div ng-show="record.editing">
								<c:import url="textInput.jsp">
									<c:param name="modelObject" value="record"></c:param>
								</c:import>
							</div>
						</td>
					</c:forEach>
					<c:if test="${dataTable.enableDataEdit}">
						<td>
							<div ng-hide="record.editing || records.editing">
								<button class="btn btn-sm btn-primary" type="button"
									ng-click="editRecord(record)">Edit</button>
								<button class="btn btn-sm btn-default" type="button"
									ng-click="deleteRecord(record)">Delete</button>
							</div>
							<div ng-show="record.editing">
								<button class="btn btn-sm btn-primary" type="submit"
									ng-hide="record.id" ng-click="createRecord(record)"
									ng-disabled="dataTableRowEdit.$invalid">Create</button>
								<button class="btn btn-sm btn-primary" type="submit"
									ng-show="record.id" ng-click="updateRecord(record)"
									ng-disabled="dataTableRowEdit.$invalid">Save</button>
								<button class="btn btn-sm btn-default" type="button"
									ng-click="cancelRecord(record)">Cancel</button>
							</div>
						</td>
					</c:if>
				</tr>
				<tr ng-show="records.length == 0">
					<td colspan="<%=numberOfColumns%>">No records found!</td>
				</tr>
			</table>
			<c:if test="${dataTable.enableDataEdit}">
				<button class="btn btn-sm btn-primary" type="button"
					ng-hide="records.editing" ng-click="addRecord()">Add</button>
			</c:if>
		</div>
	</form>
</div>
<script type="text/javascript" src="resources/js/dataTable.js"></script>
