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
	<div class="panel panel-default pad-3 mbottom-5 div-scroll-200"
		ng-if="columnFilters[<c:out value='${param.loopIndex}'></c:out>].uniqueValues.length > 0">
		<div class="row"
			ng-repeat="uniqueValue in columnFilters[<c:out value='${param.loopIndex}'></c:out>].uniqueValues">
			<div class="col-sm-12">
				<label class="fw-normal"><input class="checkbox mright-5"
					type="checkbox" ng-model="uniqueValue.selected"
					ng-model-options="{updateOn: 'submit'}" value="false"></input>{{uniqueValue.value}}</label>
			</div>
		</div>
	</div>
	<div class="row mbottom-5">
		<div class="col-sm-6">
			<div class="checkbox">
				<label><input class="checkbox mright-5" type="checkbox"
					ng-disabled="columnFilters[<c:out value='${param.loopIndex}'></c:out>].notBlank"
					ng-model="columnFilters[<c:out value='${param.loopIndex}'></c:out>].blank"
					ng-model-options="{updateOn: 'submit'}" value="false"></input>Blank</label>
			</div>
		</div>
		<div class="col-sm-6">
			<div class="checkbox">
				<label><input class="checkbox mright-5" type="checkbox"
					ng-disabled="columnFilters[<c:out value='${param.loopIndex}'></c:out>].blank"
					ng-model="columnFilters[<c:out value='${param.loopIndex}'></c:out>].notBlank"
					ng-model-options="{updateOn: 'submit'}" value="false"></input>Not
					Blank</label>
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
							ng-model="columnFilters[<c:out value='${param.loopIndex}'></c:out>].contains"
							ng-model-options="{updateOn: 'submit'}"></input>
					</div>
				</div>
			</div>
			<div class="row mbottom-5">
				<div class="col-sm-12">
					<div class="input-group input-group-sm">
						<label class="text-info small mbottom-0">Does Not Contain</label>
						<input class="form-control" type="text"
							ng-model="columnFilters[<c:out value='${param.loopIndex}'></c:out>].doesNotContain"
							ng-model-options="{updateOn: 'submit'}"></input>
					</div>
				</div>
			</div>
			<div class="row mbottom-5">
				<div class="col-sm-12">
					<div class="input-group input-group-sm">
						<label class="text-info small mbottom-0">Starts With</label> <input
							class="form-control" type="text"
							ng-model="columnFilters[<c:out value='${param.loopIndex}'></c:out>].startsWith"
							ng-model-options="{updateOn: 'submit'}"></input>
					</div>
				</div>
			</div>
			<div class="row mbottom-5">
				<div class="col-sm-12">
					<div class="input-group input-group-sm">
						<label class="text-info small mbottom-0">Ends With</label> <input
							class="form-control" type="text"
							ng-model="columnFilters[<c:out value='${param.loopIndex}'></c:out>].endsWith"
							ng-model-options="{updateOn: 'submit'}"></input>
					</div>
				</div>
			</div>
			<div class="row mbottom-5"
				ng-show="hasFilterFields(<c:out value='${param.loopIndex}'></c:out>)">
				<div class="col-sm-12">
					<div class="checkbox">
						<label><input class="checkbox mright-5" type="checkbox"
							ng-model="columnFilters[<c:out value='${param.loopIndex}'></c:out>].ignoreCase"
							ng-model-options="{updateOn: 'submit'}" value="false"></input>Ignore
							Case</label>
					</div>
				</div>
			</div>
		</c:when>
		<c:when
			test="${columnDataCell.field.inputType == 'Integer' || columnDataCell.field.inputType == 'Decimal'}">
			<div class="row mbottom-5">
				<div class="col-sm-12">
					<div class="input-group input-group-sm">
						<label class="text-info small mbottom-0">From</label> <input
							class="form-control text-right" type="number"
							ng-model="columnFilters[<c:out value='${param.loopIndex}'></c:out>].fromNumber"
							ng-model-options="{updateOn: 'submit'}"
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
							ng-model-options="{updateOn: 'submit'}"
							min="{{columnFilters[<c:out value='${param.loopIndex}'></c:out>].fromNumber}}"></input>
					</div>
				</div>
			</div>
		</c:when>
	</c:choose>
	<div class="row mbottom-5">
		<div class="col-sm-6">
			<button class="btn btn-sm btn-primary" type="submit">
				<span class="glyphicon glyphicon-filter mright-5"></span>Apply
			</button>
		</div>
		<div class="col-sm-6">
			<button class="btn btn-sm btn-default pull-right" type="button">
				Cancel</button>
		</div>
	</div>
</form>
