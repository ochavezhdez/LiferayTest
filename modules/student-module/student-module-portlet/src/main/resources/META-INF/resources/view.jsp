<%@ include file="/init.jsp"%>

<portlet:renderURL var="addStudentRenderURL">
	<liferay-portlet:param name="mvcRenderCommandName"
		value="saveStudentRender" />
</portlet:renderURL>

<aui:button-row>
	<aui:button href="<%=addStudentRenderURL%>" primary="true"
		value="Add Student" />
</aui:button-row>

<liferay-ui:search-container emptyResultsMessage="Students don't found"
	total="${ studentLocalService.getStudentsCount() }">
	<liferay-ui:search-container-results
		results="${ studentLocalService.getStudents(searchContainer.getStart(), searchContainer.getEnd()) }" />
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
				<liferay-ui:icon message="edit" url="${ updateStudentRenderURL }" />
				<liferay-ui:icon message="delete" url="${ deleteStudentActionURL }" />
			</liferay-ui:icon-menu>
		</liferay-ui:search-container-column-text>
	</liferay-ui:search-container-row>
	<liferay-ui:search-iterator markupView="lexicon" />
</liferay-ui:search-container>
