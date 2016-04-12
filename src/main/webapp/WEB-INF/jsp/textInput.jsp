<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<span
    <c:if test="${!empty inputCell.id}">id="<c:out value='${inputCell.id}'/>"
</c:if>
    <c:if
        test="${!empty inputCell.getStyleClassesString()}">class="<c:out value='${inputCell.getStyleClassesString()}'/>"
</c:if>
    <c:if test="${!empty inputCell.customStyle}">style="<c:out value='${inputCell.customStyle}'/>"
</c:if>>
	<c:choose>
    <c:when test="${inputCell.field.inputType == 'Text'}">
			<input type="text"
             <c:if
                 test="${!empty inputCell.getStyleClassesString()}">class="<c:out value='${inputCell.getStyleClassesString()}'/>"
      </c:if>
             <c:if test="${!empty inputCell.customStyle}">style="<c:out value='${inputCell.customStyle}'/>"
      </c:if>
             ng-model="<c:if test='${!empty param.modelObject}'>
				<c:out value='${param.modelObject}'/>.</c:if>
			<c:out value='${inputCell.field.name}'/>"
             <c:if test="${inputCell.field.required}">required="required"</c:if>
             <c:if test="${!empty inputCell.field.minLength}">minlength="<c:out
					value='${inputCell.field.minLength}'/>"
      </c:if>
             <c:if test="${!empty inputCell.field.maxLength}">maxlength="<c:out
					value='${inputCell.field.maxLength}'/>"
      </c:if>
             <c:if test="${!empty inputCell.field.inputPattern}">pattern="<c:out
					value='${inputCell.field.inputPattern}'/>"
      </c:if>
             <c:if test="${!empty inputCell.field.patternMismatchMessage}">title="<c:out
					value='${inputCell.field.patternMismatchMessage}'/>"
      </c:if>
             ng-trim="<c:out value='${inputCell.field.trim}'/>"/>
    </c:when>
    <c:when test="${inputCell.field.inputType == 'Password'}">
			<input type="password"
             <c:if
                 test="${!empty inputCell.getStyleClassesString()}">class="<c:out value='${inputCell.getStyleClassesString()}'/>"
      </c:if>
             <c:if test="${!empty inputCell.customStyle}">style="<c:out value='${inputCell.customStyle}'/>"
      </c:if>
             ng-model="<c:if test='${!empty param.modelObject}'>
				<c:out value='${param.modelObject}'/>.</c:if>
			<c:out value='${inputCell.field.name}'/>"
             <c:if test="${inputCell.field.required}">required="required"</c:if>/>
    </c:when>
    <c:when
        test="${inputCell.field.inputType == 'Integer' || inputCell.field.inputType == 'Decimal'}">
			<input type="number"
             class="text-right <c:if test="${!empty inputCell.getStyleClassesString()}"><c:out value='${inputCell.getStyleClassesString()}'/></c:if>"
             <c:if test="${!empty inputCell.customStyle}">style="<c:out value='${inputCell.customStyle}'/>"
      </c:if>
             ng-model="<c:if test='${!empty param.modelObject}'>
				<c:out value='${param.modelObject}'/>.</c:if>
			<c:out value='${inputCell.field.name}'/>"
             <c:if test="${inputCell.field.required}">required="required"</c:if>
             <c:if test="${!empty inputCell.field.minValue}">min="<c:out
					value='${inputCell.field.minValue}'/>"
      </c:if>
             <c:if test="${!empty inputCell.field.maxValue}">max="<c:out
					value='${inputCell.field.maxValue}'/>"
      </c:if>
             <c:if test="${!empty inputCell.field.stepInterval}">step="<c:out
					value='${inputCell.field.stepInterval}'/>"
      </c:if>/>
    </c:when>
  </c:choose>
</span>