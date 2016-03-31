<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form
	name="filterForm<c:out value='${columnDataCell.field.name}'></c:out>"
	class="form form-horizontal">
	<div class="row mbottom-5">
		<div class="col-sm-12 text-right">
			<a href=""
				ng-click="clearColumnFilter(<c:out value='${param.loopIndex}'></c:out>)">Clear
				Filter</a>
		</div>
	</div>
	<div class="panel panel-default padding-3 mbottom-5">
		<div class="row"
			ng-repeat="uniqueValue in columnFilters[<c:out value='${param.loopIndex}'></c:out>].uniqueValues">
			<div class="col-sm-12">
				<label><input class="checkbox mright-5" type="checkbox"
					ng-model="uniqueValue.selected" value="false"></input>{{uniqueValue.value}}</label>
			</div>
		</div>
	</div>
	<c:choose>
		<c:when test="${columnDataCell.field.inputType == 'Text'}">
			<div class="row mbottom-5">
				<div class="col-sm-12">
					<div class="input-group input-group-sm">
						<label class="text-info small mbottom-0">Contains</label> <input
							class="form-control" type="text"
							ng-model="columnFilters[<c:out value='${param.loopIndex}'></c:out>].contains"></input>
					</div>
				</div>
			</div>
			<div class="row mbottom-5">
				<div class="col-sm-12">
					<div class="input-group input-group-sm">
						<label class="text-info small mbottom-0">Does Not Contain</label>
						<input class="form-control" type="text"
							ng-model="columnFilters[<c:out value='${param.loopIndex}'></c:out>].doesNotContain"></input>
					</div>
				</div>
			</div>
			<div class="row mbottom-5">
				<div class="col-sm-12">
					<div class="input-group input-group-sm">
						<label class="text-info small mbottom-0">Starts With</label> <input
							class="form-control" type="text"
							ng-model="columnFilters[<c:out value='${param.loopIndex}'></c:out>].startsWith"></input>
					</div>
				</div>
			</div>
			<div class="row mbottom-5">
				<div class="col-sm-12">
					<div class="input-group input-group-sm">
						<label class="text-info small mbottom-0">Ends With</label> <input
							class="form-control" type="text"
							ng-model="columnFilters[<c:out value='${param.loopIndex}'></c:out>].endsWith"></input>
					</div>
				</div>
			</div>
			<div class="row mbottom-5"
				ng-show="hasFilterFields(<c:out value='${param.loopIndex}'></c:out>)">
				<div class="col-sm-12">
					<div class="checkbox">
						<label><input class="checkbox mright-5" type="checkbox"
							ng-model="columnFilters[<c:out value='${param.loopIndex}'></c:out>].ignoreCase"
							value="false"></input>Ignore Case</label>
					</div>
				</div>
			</div>
		</c:when>
		<c:when test="${columnDataCell.field.inputType == 'Number'}">
			<div class="row mbottom-5">
				<div class="col-sm-12">
					<div class="input-group input-group-sm">
						<label class="text-info small mbottom-0">From</label> <input
							class="form-control text-right" type="number"
							ng-model="columnFilters[<c:out value='${param.loopIndex}'></c:out>].fromNumber"
							max="{{columnFilters[<c:out value='${param.loopIndex}'></c:out>].toNumber}}"></input>
					</div>
				</div>
			</div>
			<div class="row mbottom-5">
				<div class="col-sm-12">
					<div class="input-group input-group-sm">
						<label class="text-info small mbottom-0">To</label> <input
							class="form-control text-right" type="number"
							ng-model="columnFilters[<c:out value='${param.loopIndex}'></c:out>].toNumber"
							min="{{columnFilters[<c:out value='${param.loopIndex}'></c:out>].fromNumber}}"></input>
					</div>
				</div>
			</div>
		</c:when>
	</c:choose>
</form>
