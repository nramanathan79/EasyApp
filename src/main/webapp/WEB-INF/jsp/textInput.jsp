<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<span
	<c:if test="${!empty inputCell.id}">id="<c:out value='${inputCell.id}'></c:out>"</c:if>
	<c:if test="${!empty inputCell.getStyleClassesString()}">class="<c:out value='${inputCell.getStyleClassesString()}'></c:out>"</c:if>
	<c:if test="${!empty inputCell.customStyle}">style="<c:out value='${inputCell.customStyle}'></c:out>"</c:if>>
	<c:choose>
		<c:when test="${inputCell.field.inputType == 'Text'}">
			<input type="text"
				ng-model="<c:if test='${!empty param.modelObject}'><c:out value='${param.modelObject}'></c:out>.</c:if><c:out value='${inputCell.field.name}'></c:out>"
				<c:if test="${inputCell.field.required}">required="required"</c:if>
				<c:if test="${!empty inputCell.field.minLength}">minlength="<c:out value='${inputCell.field.minLength}'></c:out>"</c:if>
				<c:if test="${!empty inputCell.field.maxLength}">maxlength="<c:out value='${inputCell.field.maxLength}'></c:out>"</c:if>
				<c:if test="${!empty inputCell.field.inputPattern}">pattern="<c:out value='${inputCell.field.inputPattern}'></c:out>"</c:if>
				<c:if test="${!empty inputCell.field.patternMismatchMessage}">title="<c:out value='${inputCell.field.patternMismatchMessage}'></c:out>"</c:if>
				ng-trim="<c:out value='${inputCell.field.trim}'></c:out>"></input>
		</c:when>
		<c:when test="${inputCell.field.inputType == 'Password'}">
			<input type="password"
				ng-model="<c:if test='${!empty param.modelObject}'><c:out value='${param.modelObject}'></c:out>.</c:if><c:out value='${inputCell.field.name}'></c:out>"
				<c:if test="${inputCell.field.required}">required="required"</c:if>></input>
		</c:when>
		<c:when test="${inputCell.field.inputType == 'Number'}">
			<input class="text-right" type="number"
				ng-model="<c:if test='${!empty param.modelObject}'><c:out value='${param.modelObject}'></c:out>.</c:if><c:out value='${inputCell.field.name}'></c:out>"
				<c:if test="${inputCell.field.required}">required="required"</c:if>
				<c:if test="${!empty inputCell.field.minValue}">min="<c:out value='${inputCell.field.minValue}'></c:out>"</c:if>
				<c:if test="${!empty inputCell.field.maxValue}">max="<c:out value='${inputCell.field.maxValue}'></c:out>"</c:if>></input>
		</c:when>
	</c:choose>
</span>