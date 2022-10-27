<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@ include file="/init.jsp"%>

<portlet:renderURL var="addStudentRenderURL">
	<liferay-portlet:param name="mvcRenderCommandName"
		value="saveStudentRender" />
</portlet:renderURL>

<liferay-ui:success key="studentSaved" message="student-saved" />
<liferay-ui:success key="studentDeleted" message="student-deleted" />

<portlet:renderURL var="searchURL">
	<portlet:param name="mvcPath" value="/view.jsp" />
</portlet:renderURL>
<aui:form action="${searchURL}" name="fm">
	<div class="row">
		<div class="col-md-8">
			<aui:input inlineLabel="left" label="" name="keywords"
				placeholder="search-entries" size="256" />
		</div>
		<div class="col-md-4">
			<aui:button type="submit" value="search" />
		</div>
	</div>
</aui:form>

<aui:button-row>
	<aui:button href="<%=addStudentRenderURL%>" primary="true"
		value="Add Student" />
</aui:button-row>

<liferay-ui:search-container emptyResultsMessage="Students don't found"
	total="${ studentSearch.getStudentsCount() }">
	<liferay-ui:search-container-results
		results="${ studentSearch.getStudents(searchContainer.getStart(), searchContainer.getEnd()) }" />
	<liferay-ui:search-container-row
		className="com.liferay.student.model.Student" escapedModel="true"
		keyProperty="studentId" modelVar="student">
		<liferay-ui:search-container-column-text name="Enrollment No"
			property="enrollmentNo" />
		<liferay-ui:search-container-column-text name="First Name"
			property="firstName" />
		<liferay-ui:search-container-column-text name="Last Name"
			property="lastName" />
		<liferay-ui:search-container-column-text name="Contact No"
			property="contactNo" />
		<liferay-ui:search-container-column-text name="City" property="city" />
		<liferay-ui:search-container-column-text name="actions">
			<liferay-ui:icon-menu direction="left-side" icon=""
				markupView="lexicon" message="actions" showWhenSingleIcon="true">
				<portlet:renderURL var="updateStudentRenderURL">
					<liferay-portlet:param name="mvcRenderCommandName"
						value="saveStudentRender" />
					<portlet:param name="studentId" value="${ student.studentId }" />
				</portlet:renderURL>

				<portlet:actionURL name="deleteStudentAction"
					var="deleteStudentActionURL">
					<portlet:param name="studentId" value="${ student.getStudentId() }" />
				</portlet:actionURL>
				<liferay-ui:icon image="edit" message="Edit"
					url="${ updateStudentRenderURL }" />
				<liferay-ui:icon-delete image="delete"
					url="${ deleteStudentActionURL }" />
			</liferay-ui:icon-menu>
		</liferay-ui:search-container-column-text>
	</liferay-ui:search-container-row>
	<liferay-ui:search-iterator markupView="lexicon" />
</liferay-ui:search-container>
