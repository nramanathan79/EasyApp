<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form
	name="filterForm<c:out value='${columnDataCell.field.name}'></c:out>">
	<div class="row">
		<div class="col-sm-12 text-right">
			<a href=""
				ng-click="clearColumnFilter(<c:out value='${columnDataCell.field.name}'></c:out>)">Clear
				Filter</a>
		</div>
	</div>
	<div class="row bottom-5">
		<div class="col-sm-12">
			<div class="input-group input-group-sm">
				<label class="text-info small bottom-0">Contains</label> <input
					class="form-control" type="text"
					ng-model="filter<c:out value='${columnDataCell.field.name}'></c:out>Contains"></input>
			</div>
		</div>
	</div>
	<div class="row bottom-5">
		<div class="col-sm-12">
			<div class="input-group input-group-sm">
				<label class="text-info small bottom-0">Does Not Contain</label> <input
					class="form-control" type="text"
					ng-model="filter<c:out value='${columnDataCell.field.name}'></c:out>DoesNotContain"></input>
			</div>
		</div>
	</div>
	<div class="row bottom-5">
		<div class="col-sm-12">
			<div class="input-group input-group-sm">
				<label class="text-info small bottom-0">Starts With</label> <input
					class="form-control" type="text"
					ng-model="filter<c:out value='${columnDataCell.field.name}'></c:out>StartsWith"></input>
			</div>
		</div>
	</div>
	<div class="row bottom-5">
		<div class="col-sm-12">
			<div class="input-group input-group-sm">
				<label class="text-info small bottom-0">Ends With</label> <input
					class="form-control" type="text"
					ng-model="filter<c:out value='${columnDataCell.field.name}'></c:out>EndsWith"></input>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-6">
			<button class="btn btn-sm btn-primary" type="button"
				ng-click="applyColumnFilter(<c:out value='${columnDataCell.field.name}'></c:out>)">Apply</button>
		</div>
		<div class="col-sm-6 text-right">
			<button class="btn btn-sm btn-secondary" type="button">Cancel</button>
		</div>
	</div>
</form>