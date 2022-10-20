<%@page import="com.liferay.student.service.StudentLocalServiceUtil"%>
<%@ include file="/init.jsp"%>

<portlet:renderURL var="addStudentRenderURL">
	<portlet:param name="mvcPath" value="/add-student.jsp" />
</portlet:renderURL>

<div class="mb-5">
	<a href="<%=addStudentRenderURL%>" class="btn  btn-primary btn-default">
		<i class="glyphicon glyphicon-plus"></i> Add Student
	</a>
</div>

<liferay-ui:search-container emptyResultsMessage="Students don't found"
	total="<%=StudentLocalServiceUtil.getStudentsCount()%>">
	<liferay-ui:search-container-results
		results="<%=StudentLocalServiceUtil.getStudents(searchContainer.getStart(), searchContainer.getEnd())%>" />
	<liferay-ui:search-container-row
		className="com.liferay.student.model.Student" escapedModel="true"
		keyProperty="studentId" modelVar="student">
		<liferay-ui:search-container-column-text name="Enrollment No"
			orderable="true" property="enrollmentNo" />
		<liferay-ui:search-container-column-text name="First Name"
			orderable="true" property="firstName" />
		<liferay-ui:search-container-column-text name="Last Name"
			orderable="true" property="lastName" />
		<liferay-ui:search-container-column-text name="Contact No"
			orderable="true" property="contactNo" />
		<liferay-ui:search-container-column-text name="City" orderable="true"
			property="city" />
		<liferay-ui:search-container-column-text name="actions">
			<liferay-ui:icon-menu direction="left-side" icon=""
				markupView="lexicon" message="actions" showWhenSingleIcon="true">
				<portlet:renderURL var="updateStudentRenderURL">
					<portlet:param name="mvcPath" value="/update-student.jsp" />
					<portlet:param name="enrollmentNo" value="${student.enrollmentNo}" />
					<portlet:param name="firstName" value="${student.firstName}" />
					<portlet:param name="lastName" value="${student.lastName}" />
					<portlet:param name="contactNo" value="${student.contactNo}" />
					<portlet:param name="city" value="${student.city}" />
					<portlet:param name="studentId" value="${student.studentId}" />
				</portlet:renderURL>

				<portlet:actionURL name="deleteStudent" var="deleteStudentActionURL">
					<portlet:param name="studentId" value="${student.getStudentId()}" />
				</portlet:actionURL>
				<liferay-ui:icon message="edit" url="${updateStudentRenderURL}" />
				<liferay-ui:icon message="delete" url="${deleteStudentActionURL}" />
			</liferay-ui:icon-menu>
		</liferay-ui:search-container-column-text>
	</liferay-ui:search-container-row>
	<liferay-ui:search-iterator markupView="lexicon" />
</liferay-ui:search-container>
