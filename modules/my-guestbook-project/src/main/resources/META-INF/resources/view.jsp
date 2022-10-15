<%@ include file="/init.jsp"%>

<p>
	<b><liferay-ui:message key="guestbook.caption" /></b>
</p>

<liferay-portlet:actionURL name="saveGuestBook" var="saveGuestBookVar">
</liferay-portlet:actionURL>

<liferay-portlet:renderURL var="viewGuestBookVar">
	<liferay-portlet:param name="guestBookId" value="221014" />
	<liferay-portlet:param name="mvcRenderCommandName"
		value="viewGuestBook" />
</liferay-portlet:renderURL>

<liferay-portlet:resourceURL id="deleteGuestBook"
	var="deleteGuestBookVar">
	<liferay-portlet:param name="guestBookId" value="221014" />
</liferay-portlet:resourceURL>

<liferay-portlet:resourceURL var="baseResourceUrl"></liferay-portlet:resourceURL>

<aui:form action="<%=saveGuestBookVar%>" cssClass="container-fluid-1280"
	method="post" name="fromGuestBook">
	<aui:select name="users"></aui:select>
	<aui:button primary="<%=true%>" type="submit" />
	<aui:button href="<%=viewGuestBookVar%>" value="Ver" />
	<aui:button href="<%=deleteGuestBookVar%>" value="Eliminar" />
</aui:form>

<aui:script>
AUI().use('aui-io-request', function(A) {
    A.io.request('${baseResourceUrl}', {
        method: 'post',
        sync: true,
        data: {
            <portlet:namespace />cmd: 'getUsers'
        },
        on: {
            success: function() {},
            end: function() {
                var userList = JSON.parse(this.get('responseData'));
                if (userList.length === 0) {
                    $("#error").empty();
                    $("#error").html("No Users found");
                    $("#error").addClass("alert");
                } else {
                    console.log(userList);
                    $.each(userList, function(index, value) {
                        $("#<portlet:namespace />users").append('<option value="' + value.id + '">' + value.name + '</option>');
                    });
                }
            }
        }
    });
});
</aui:script>
