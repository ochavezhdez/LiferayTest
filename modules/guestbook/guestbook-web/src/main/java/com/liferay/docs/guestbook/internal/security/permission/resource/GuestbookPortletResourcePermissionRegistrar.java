package com.liferay.docs.guestbook.internal.security.permission.resource;

import java.util.Dictionary;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

import com.liferay.docs.guestbook.constants.GuestbookConstants;
import com.liferay.docs.guestbook.portlet.constants.GuestbookPortletKeys;
import com.liferay.exportimport.kernel.staging.permission.StagingPermission;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermissionFactory;
import com.liferay.portal.kernel.security.permission.resource.StagedPortletPermissionLogic;
import com.liferay.portal.kernel.util.HashMapDictionary;

@Component(immediate = true)
public class GuestbookPortletResourcePermissionRegistrar {

	private ServiceRegistration<PortletResourcePermission> serviceRegistration;

	@Reference
	private StagingPermission stagingPermission;

	@Activate
	public void activate(BundleContext bundleContext) {
		Dictionary<String, Object> properties = new HashMapDictionary<>();

		properties.put("resource.name", GuestbookConstants.RESOURCE_NAME);

		serviceRegistration = bundleContext.registerService(PortletResourcePermission.class,
				PortletResourcePermissionFactory.create(GuestbookConstants.RESOURCE_NAME,
						new StagedPortletPermissionLogic(stagingPermission, GuestbookPortletKeys.GUESTBOOK)),
				properties);
	}

	@Deactivate
	public void deactivate() {
		serviceRegistration.unregister();
	}

}
