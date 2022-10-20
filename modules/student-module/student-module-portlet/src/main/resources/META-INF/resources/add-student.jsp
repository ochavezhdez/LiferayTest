<%@ include file="init.jsp"%>

<portlet:actionURL name="addStudent" var="addStudentActionURL" />


<h2>Add Student Form here!</h2>

<aui:form action="<%=addStudentActionURL%>" name="studentForm"
	method="POST">
	<aui:fieldset-group markupView="lexicon">
		<aui:fieldset label="Personal Information">
			<aui:row>
				<aui:col width="50">
					<aui:input name="firstName">
						<aui:validator name="required" />
						<aui:validator name="alpha" />
					</aui:input>
				</aui:col>
				<aui:col width="50">
					<aui:input name="lastName">
						<aui:validator name="required" />
						<aui:validator name="alpha" />
					</aui:input>
				</aui:col>
				<aui:col width="50">
					<aui:input name="city">
						<aui:validator name="required" />
						<aui:validator name="alpha" />
					</aui:input>
				</aui:col>
			</aui:row>
		</aui:fieldset>
		<aui:fieldset label="Miscellaneous">
			<aui:row>
				<aui:col width="50">
					<aui:input name="enrollmentNo">
						<aui:validator name="required" />
						<aui:validator name="alphanum" />
					</aui:input>
				</aui:col>
				<aui:col width="50">
					<aui:input name="contactNo">
						<aui:validator name="required" />
						<aui:validator name="string" />
					</aui:input>
				</aui:col>
			</aui:row>
		</aui:fieldset>
		<aui:button-row>
			<aui:button type="submit" name="" value="submit"></aui:button>
		</aui:button-row>
	</aui:fieldset-group>
</aui:form>
