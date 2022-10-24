package com.liferay.docs.guestbook.web.internal.security.permission.resource;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.docs.guestbook.constants.GuestbookConstants;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;

@Component(immediate = true)
public class GuestbookPermission {

	private static PortletResourcePermission portletResourcePermission;

	public static boolean contains(PermissionChecker permissionChecker, long groupId, String actionId) {
		return portletResourcePermission.contains(permissionChecker, groupId, actionId);
	}

	@Reference(target = "(resource.name=" + GuestbookConstants.RESOURCE_NAME + ")", unbind = "-")
	protected void setPortletResourcePermission(PortletResourcePermission portletResourcePermission) {
		this.portletResourcePermission = portletResourcePermission;
	}

}
