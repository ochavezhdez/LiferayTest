<%@ page import="com.liferay.portal.kernel.model.User"%>
<%@ page import="java.util.List"%>
<%@ include file="/init.jsp"%>

<%
List<User> userList = (List<User>) request.getAttribute("userList");
%>

<liferay-portlet:actionURL name="saveUser" var="saveUserVar">
</liferay-portlet:actionURL>

<liferay-portlet:renderURL var="viewUserVar">
	<liferay-portlet:param name="userId" value="221014" />
	<liferay-portlet:param name="mvcRenderCommandName" value="viewUser" />
</liferay-portlet:renderURL>

<liferay-portlet:resourceURL id="deleteUser" var="deleteUserVar">
	<liferay-portlet:param name="userId" value="221014" />
</liferay-portlet:resourceURL>

<liferay-portlet:resourceURL id="getUsers" var="getUsersVar"></liferay-portlet:resourceURL>
<liferay-portlet:resourceURL id="getUser" var="getUserVar"></liferay-portlet:resourceURL>

<aui:form action="<%=saveUserVar%>" cssClass="container-fluid-1280"
	method="post" name="fromGuestBook">
	<aui:fieldset-group markupView="lexicon">
		<aui:fieldset label="Personal Information">
			<aui:row>
				<aui:col width="50">
					<aui:input label="Id" name="id" type="text" />
				</aui:col>
				<aui:col width="50">
					<aui:input label="Name" name="name" type="text" />
				</aui:col>
			</aui:row>
			<aui:row>
				<aui:col width="50">
					<aui:input label="uuid" name="uuid" type="text" />
				</aui:col>
				<aui:col width="50">
					<aui:input label="Email" name="email" type="email" />
				</aui:col>
			</aui:row>
		</aui:fieldset>
	</aui:fieldset-group>
	<aui:fieldset-group markupView="lexicon">
		<aui:fieldset label="Miscellaneous">
			<aui:select name="users">
				<c:forEach items="${userList}" var="user">
					<aui:option value="${user.userId}">${user.fullName}</aui:option>
				</c:forEach>
			</aui:select>
		</aui:fieldset>
	</aui:fieldset-group>
	<aui:button-row>
		<aui:button primary="<%=true%>" type="submit" />
		<aui:button href="<%=viewUserVar%>" value="Show" />
		<aui:button href="<%=deleteUserVar%>" value="Delete" />
	</aui:button-row>
</aui:form>

<aui:script>
$("#<portlet:namespace />users").change(function() {
	AUI().use('aui-io-request', function(getUser) {
		getUser.io.request('${getUserVar}', {
			method: 'get',
			sync: true,
			data: {
				<portlet:namespace />userId: $("#<portlet:namespace />users").val(),
			},
			dataType: 'json',
			on: {
				success: function() {},
				end: function() {
					var result = this.get('responseData');
					$("#<portlet:namespace />id").val(result.id);
					$("#<portlet:namespace />name").val(result.name);
					$("#<portlet:namespace />uuid").val(result.uuid);
					$("#<portlet:namespace />email").val(result.email);
				}
			}
		});
	});
});
</aui:script>
