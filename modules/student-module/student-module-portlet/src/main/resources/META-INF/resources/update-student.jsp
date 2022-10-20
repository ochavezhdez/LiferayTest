<%@ include file="init.jsp"%>

<portlet:actionURL name="updateStudent" var="updateStudentActionURL" />

<aui:form action="<%=updateStudentActionURL%>" name="studentForm"
	method="POST" />

<%
String studentId = renderRequest.getParameter("studentId");
String enrollmentNo = renderRequest.getParameter("enrollmentNo");
String firstName = renderRequest.getParameter("firstName");
String lastName = renderRequest.getParameter("lastName");
String contactNo = renderRequest.getParameter("contactNo");
String city = renderRequest.getParameter("city");
%>
<aui:form action="<%=updateStudentActionURL%>" method="post">
	<aui:input name="studentId" type="hidden"
		value="<%=Long.parseLong(studentId)%>" />
	<aui:fieldset-group markupView="lexicon">
		<aui:fieldset label="Personal Information">
			<aui:row>
				<aui:col width="50">
					<aui:input name="firstName" value="${student.firstName}">
						<aui:validator name="required" />
						<aui:validator name="alpha" />
					</aui:input>
				</aui:col>
				<aui:col width="50">
					<aui:input name="lastName" value="${student.lastName}">
						<aui:validator name="required" />
						<aui:validator name="alpha" />
					</aui:input>
				</aui:col>
				<aui:col width="50">
					<aui:input name="city" value="${student.city}">
						<aui:validator name="required" />
						<aui:validator name="alpha" />
					</aui:input>
				</aui:col>
			</aui:row>
		</aui:fieldset>
		<aui:fieldset label="Miscellaneous">
			<aui:row>
				<aui:col width="50">
					<aui:input name="enrollmentNo" value="${student.enrollmentNo}">
						<aui:validator name="required" />
						<aui:validator name="alphanum" />
					</aui:input>
				</aui:col>
				<aui:col width="50">
					<aui:input name="contactNo" value="${student.contactNo}">
						<aui:validator name="required" />
						<aui:validator name="string" />
					</aui:input>
				</aui:col>
			</aui:row>
		</aui:fieldset>
		<aui:button-row>
			<aui:button type="submit" name="" value="update"></aui:button>
		</aui:button-row>
	</aui:fieldset-group>
</aui:form>
