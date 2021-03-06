<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="pull-right" ng-controller="columnFilterController">
	<div class="dropdown layout-inline"
		ng-init="initializeColumnFilter('<c:out value="${columnDataCell.field.name}"/>', '<c:out value="${columnDataCell.field.inputType}"/>')">
		<a href="" id="<c:out value='${columnDataCell.field.name}'/>Filter"
			class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true"
			aria-expanded="true" ng-click="backupColumnFilter()"> <span
			class="glyphicon glyphicon-filter" ng-class="getFilterClass()"></span>
		</a>
		<div class="dropdown-menu pad-5"
			aria-labelledby="<c:out value='${columnDataCell.field.name}'/>Filter">
			<form name="filterForm<c:out value='${columnDataCell.field.name}'/>"
				class="form form-inline">
				<div class="row mbottom-5" ng-if="columnFilter.filterPresent"
					ng-cloak>
					<div class="col-sm-12 text-right">
						<a href="" ng-click="clearColumnFilter()">Clear Filter</a>
					</div>
				</div>
				<div class="panel panel-default pad-3 mbottom-5 div-scroll-200"
					ng-if="columnFilter.uniqueValues.length > 0">
					<div class="row"
						ng-repeat="uniqueValue in columnFilter.uniqueValues track by uniqueValue.value">
						<div class="col-sm-12">
							<label class="fw-normal"><input class="checkbox mright-5"
								type="checkbox" ng-model="uniqueValue.selected"
								ng-model-options="{updateOn: 'blur'}" value="false" />{{uniqueValue.value}}</label>
						</div>
					</div>
				</div>
				<div class="row mbottom-5">
					<div class="col-sm-6">
						<div class="checkbox">
							<label><input class="checkbox mright-5" type="checkbox"
								ng-model="columnFilter.blank"
								ng-model-options="{updateOn: 'blur'}" value="false" />Blank</label>
						</div>
					</div>
					<div class="col-sm-6">
						<div class="checkbox">
							<label><input class="checkbox mright-5" type="checkbox"
								ng-model="columnFilter.notBlank"
								ng-model-options="{updateOn: 'blur'}" value="false" />Not Blank</label>
						</div>
					</div>
				</div>
				<c:choose>
					<c:when test="${columnDataCell.field.inputType == 'Text'}">
						<div class="row mbottom-5">
							<div class="col-sm-12">
								<div class="input-group input-group-sm">
									<label class="text-info small mbottom-0"
										for="<c:out value='${columnDataCell.field.name}'/>Contains">Contains</label>
									<input
										id="<c:out value='${columnDataCell.field.name}'/>Contains"
										class="form-control" type="text"
										ng-model="columnFilter.contains"
										ng-model-options="{updateOn: 'blur'}" />
								</div>
							</div>
						</div>
						<div class="row mbottom-5">
							<div class="col-sm-12">
								<div class="input-group input-group-sm">
									<label class="text-info small mbottom-0"
										for="<c:out value='${columnDataCell.field.name}'/>DoesNotContain">Does
										Not Contain</label> <input
										id="<c:out value='${columnDataCell.field.name}'/>DoesNotContain"
										class="form-control" type="text"
										ng-model="columnFilter.doesNotContain"
										ng-model-options="{updateOn: 'blur'}" />
								</div>
							</div>
						</div>
						<div class="row mbottom-5">
							<div class="col-sm-12">
								<div class="input-group input-group-sm">
									<label class="text-info small mbottom-0"
										for="<c:out value='${columnDataCell.field.name}'/>StartsWith">Starts
										With</label> <input
										id="<c:out value='${columnDataCell.field.name}'/>StartsWith"
										class="form-control" type="text"
										ng-model="columnFilter.startsWith"
										ng-model-options="{updateOn: 'blur'}" />
								</div>
							</div>
						</div>
						<div class="row mbottom-5">
							<div class="col-sm-12">
								<div class="input-group input-group-sm">
									<label class="text-info small mbottom-0"
										for="<c:out value='${columnDataCell.field.name}'/>EndsWith">Ends
										With</label> <input
										id="<c:out value='${columnDataCell.field.name}'/>EndsWith"
										class="form-control" type="text"
										ng-model="columnFilter.endsWith"
										ng-model-options="{updateOn: 'blur'}" />
								</div>
							</div>
						</div>
						<div class="row mbottom-5">
							<div class="col-sm-12">
								<div class="checkbox">
									<label><input class="checkbox mright-5" type="checkbox"
										ng-model="columnFilter.ignoreCase"
										ng-model-options="{updateOn: 'blur'}" value="false" />Ignore
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
									<label class="text-info small mbottom-0"
										for="<c:out value='${columnDataCell.field.name}'/>FromNumber">From</label>
									<input
										id="<c:out value='${columnDataCell.field.name}'/>FromNumber"
										class="form-control text-right" type="number"
										ng-model="columnFilter.fromNumber"
										ng-model-options="{updateOn: 'blur'}"
										max="{{columnFilter.toNumber}}" />
								</div>
							</div>
						</div>
						<div class="row mbottom-5">
							<div class="col-sm-12">
								<div class="input-group input-group-sm">
									<label class="text-info small mbottom-0"
										for="<c:out value='${columnDataCell.field.name}'/>ToNumber">To</label>
									<input
										id="<c:out value='${columnDataCell.field.name}'/>ToNumber"
										class="form-control text-right" type="number"
										ng-model="columnFilter.toNumber"
										ng-model-options="{updateOn: 'blur'}"
										min="{{columnFilter.fromNumber}}" />
								</div>
							</div>
						</div>
					</c:when>
				</c:choose>
				<div class="row mtop-5">
					<div class="col-sm-6">
						<button class="btn btn-sm btn-primary" type="submit"
							ng-click="applyColumnFilter()">
							<span class="glyphicon glyphicon-filter mright-5"></span>Apply
						</button>
					</div>
					<div class="col-sm-6">
						<button class="btn btn-sm btn-default pull-right" type="button"
							ng-click="restoreColumnFilter()">Cancel</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
