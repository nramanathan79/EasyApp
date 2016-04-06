<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div ng-app="dataTable" ng-controller="dataTableController"
	ng-init="initializeResource('countries')">
	<div class="container-fluid">
		<div class="row" ng-if="error" ng-cloak>
			<div class="alert alert-danger col-md-12">{{error}}</div>
		</div>
		<div class="row mtop-5">
			<c:if test="${dataTable.enableSearch}">
				<div class="col-md-3">
					<form id="dataTableSearch" class="form form-inline">
						<input class="form-control" type="text" placeholder="Search Table"
							ng-model="searchText" ng-model-options="{updateOn: 'submit'}"></input>
						<button class="btn btn-sm btn-primary" type="submit">
							<span class="glyphicon glyphicon-search mright-3"></span>Search
						</button>
						<button class="btn btn-sm btn-default" type="button"
							ng-click="clearSearch()">Clear</button>
					</form>
				</div>
			</c:if>
			<c:if test="${dataTable.enableMultiColumnSort}">
				<div class="col-md-3">
					<div class="btn-group" ng-init="sortSelection = 'Single'">
						<button class="btn btn-sm"
							ng-class="sortSelection == 'Single' ? 'btn-success' : 'btn-default'"
							ng-click="changeSortSelection('Single')">Single Column
							Sort</button>
						<button class="btn btn-sm"
							ng-class="sortSelection == 'Single' ? 'btn-default' : 'btn-success'"
							ng-click="changeSortSelection('Multiple')">Multi Column
							Sort</button>
					</div>
					<button class="btn btn-sm btn-default" ng-click="clearSort()">Clear
						All Sort</button>
				</div>
			</c:if>
			<c:if test="${dataTable.enableColumnFilter}">
				<div class="col-md-1">
					<button class="btn btn-sm btn-default" ng-click="clearFilters()">Clear
						All Filters</button>
				</div>
			</c:if>
			<c:if test="${dataTable.enableImportCsv}">
				<div class="col-md-1">
					<button class="btn btn-sm btn-primary" data-toggle="modal"
						data-target="#importCsv">
						<span class="glyphicon glyphicon-import mright-3"></span>Import
						CSV
					</button>

					<div id="importCsv" class="modal fade" role="dialog">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal">&times;</button>
									<h4 class="modal-title">Import CSV File</h4>
								</div>
								<div class="modal-body">
									<input type="file" name="importCsv" id="importCsv"
										accept=".csv" import-csv></input>
								</div>
								<div class="modal-footer">
									<button type="button" class="pull-left btn btn-primary"
										ng-click="importCsvFile()" ng-disabled="!hasImportCsvFile()">Import</button>
									<button type="button" class="btn btn-default"
										data-dismiss="modal">Close</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</c:if>
			<div class="col-md-4 text-right">
				<c:if test="${dataTable.enableRefresh}">
					<div class="row">
						<div class="col-md-12">
							<button class="btn btn-sm btn-primary"
								ng-disabled="filteredRecords.editing" ng-click="listRecords()">
								<span class="glyphicon glyphicon-refresh mright-3"></span>Refresh
							</button>
						</div>
					</div>
				</c:if>
			</div>
		</div>
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
					<th class="text-right">#</th>
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
									ng-if="sortBy.indexOf('<c:out value="${column.dataCell.field.name}"></c:out>') >= 0"
									ng-cloak></span>
								<span class="glyphicon glyphicon-sort-by-attributes-alt"
									ng-if="sortBy.indexOf('-<c:out value="${column.dataCell.field.name}"></c:out>') >= 0"
									ng-cloak></span>
								<sup
									ng-if="showSortIndex('<c:out value="${column.dataCell.field.name}"></c:out>') > 0"
									ng-cloak>{{showSortIndex('<c:out
										value="${column.dataCell.field.name}"></c:out>')}}
								</sup>
							</c:when>
							<c:otherwise>
								<span
									<c:if test="${column.dataCell.field.required}">class="required-field"</c:if>><c:out
										value="${column.dataCell.field.label}"></c:out></span>
							</c:otherwise>
						</c:choose> <c:if test="${dataTable.enableColumnFilter}">
							<c:set var="columnDataCell" value="${column.dataCell}"
								scope="request"></c:set>
							<c:import url="columnFilter.jsp">
								<c:param name="loopIndex" value="${loop.index}"></c:param>
							</c:import>
						</c:if></th>
				</c:forEach>
				<c:if test="${dataTable.enableDataEdit}">
					<%
						numberOfColumns++;
					%>
					<th class="text-nowrap">Action</th>
				</c:if>
			</tr>
			<tr
				ng-repeat="record in filteredRecords | orderBy:sortBy track by record.id"
				repeat-complete="stopSpinner()">
				<c:if test="${dataTable.enableRowNum}">
					<td class="text-right" ng-cloak>{{$index+1}}</td>
				</c:if>
				<c:forEach items="${dataTable.columns}" var="column">
					<c:set var="inputCell" value="${column.dataCell}" scope="request"></c:set>
					<td>
						<div
							<c:if test="${!empty inputCell.id}">id="<c:out value='${inputCell.id}'></c:out>Display"</c:if>
							<c:if test="${!empty inputCell.getStyleClassesString()}">class="<c:out value='${inputCell.getStyleClassesString()}'></c:out>"</c:if>
							<c:if test="${!empty inputCell.customStyle}">style="<c:out value='${inputCell.customStyle}'></c:out>"</c:if>
							ng-if="!record.editing" ng-cloak>
							{{record.
							<c:out value="${column.dataCell.field.name}"></c:out>
							}}
						</div>
						<div ng-if="record.editing" ng-cloak>
							<c:import url="textInput.jsp">
								<c:param name="modelObject" value="record"></c:param>
							</c:import>
						</div>
					</td>
				</c:forEach>
				<c:if test="${dataTable.enableDataEdit}">
					<td class="text-nowrap">
						<div ng-if="!(record.editing || filteredRecords.editing)" ng-cloak>
							<label class="small mright-5"><a href=""
								ng-click="editRecord(record)"><span
									class="glyphicon glyphicon-pencil mright-3"></span>Edit</a></label> <label
								class="small"><a href="" ng-click="deleteRecord(record)"><span
									class="glyphicon glyphicon-trash mright-3"></span>Delete</a></label>
						</div>
						<div ng-if="record.editing" ng-cloak>
							<label class="small mright-5" ng-if="!record.id" ng-cloak><a
								href="" ng-click="createRecord(record, false)"><span
									class="glyphicon glyphicon-save mright-3"></span>Create</a></label> <label
								class="small mright-5" ng-if="record.id" ng-cloak><a
								href="" ng-click="updateRecord(record)"><span
									class="glyphicon glyphicon-floppy-disk mright-3"></span>Save</a></label> <label
								class="small"><a href="" ng-click="cancelRecord(record)"><span
									class="glyphicon glyphicon-remove mright-3"></span>Cancel</a></label>
						</div>
					</td>
				</c:if>
			</tr>
			<tr ng-if="filteredRecords.length == 0 && searchText === '' && columnFilters.length <= 0" ng-cloak>
				<td colspan="<%=numberOfColumns%>">No records found!</td>
			</tr>
		</table>
		<c:if test="${dataTable.enableDataEdit}">
			<div class="row mbottom-5">
				<div class="col-sm-12">
					<button class="btn btn-sm btn-primary" ng-if="!filteredRecords.editing"
						ng-cloak ng-click="addRecord()">
						<span class="glyphicon glyphicon-plus mright-3"></span>Add
					</button>
				</div>
			</div>
		</c:if>
	</div>
	<span us-spinner="{radius:30, width:8, length: 16}"
		spinner-on="showSpinner"></span>
</div>
<script type="text/javascript"
	src="webjars/papa-parse/4.1.0/papaparse.min.js"></script>
<script type="text/javascript" src="resources/js/dataTable.js"></script>
<script type="text/javascript" src="resources/js/columnFilter.js"></script>