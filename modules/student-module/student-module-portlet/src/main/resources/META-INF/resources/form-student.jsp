<%@ include file="init.jsp"%>

<portlet:actionURL name="saveStudentAction" var="saveStudentActionURL" />

<aui:form action="<%=saveStudentActionURL%>" name="studentForm"
	method="post">
	<aui:input name="studentId" type="hidden" value="${student.studentId}" />
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
			<aui:button type="submit" name="save" value="save"></aui:button>
		</aui:button-row>
	</aui:fieldset-group>
</aui:form>
