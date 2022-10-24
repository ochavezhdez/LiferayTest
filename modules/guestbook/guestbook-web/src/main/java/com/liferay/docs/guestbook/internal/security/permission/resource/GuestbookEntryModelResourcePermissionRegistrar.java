package com.liferay.docs.guestbook.internal.security.permission.resource;

import java.util.Dictionary;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

import com.liferay.docs.guestbook.constants.GuestbookConstants;
import com.liferay.docs.guestbook.model.GuestbookEntry;
import com.liferay.docs.guestbook.portlet.constants.GuestbookPortletKeys;
import com.liferay.docs.guestbook.service.GuestbookEntryLocalService;
import com.liferay.exportimport.kernel.staging.permission.StagingPermission;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermissionFactory;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.StagedModelPermissionLogic;
import com.liferay.portal.kernel.security.permission.resource.WorkflowedModelPermissionLogic;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.util.HashMapDictionary;
import com.liferay.portal.kernel.workflow.permission.WorkflowPermission;

@Component(immediate = true)
public class GuestbookEntryModelResourcePermissionRegistrar {

	@Reference
	private GuestbookEntryLocalService guestbookEntryLocalService;

	@Reference(target = "(resource.name=" + GuestbookConstants.RESOURCE_NAME + ")")
	private PortletResourcePermission portletResourcePermission;

	private ServiceRegistration<ModelResourcePermission> serviceRegistration;

	@Reference
	private StagingPermission stagingPermission;

	@Reference
	private WorkflowPermission workflowPermission;

	@Reference
	private GroupLocalService groupLocalService;

	@Activate
	public void activate(BundleContext bundleContext) {
		Dictionary<String, Object> properties = new HashMapDictionary<>();

		properties.put("model.class.name", GuestbookEntry.class.getName());

		serviceRegistration = bundleContext.registerService(ModelResourcePermission.class,
				ModelResourcePermissionFactory.create(GuestbookEntry.class, GuestbookEntry::getEntryId,
						guestbookEntryLocalService::getGuestbookEntry, portletResourcePermission,
						(modelResourcePermission, consumer) -> {
							consumer.accept(new StagedModelPermissionLogic<>(stagingPermission,
									GuestbookPortletKeys.GUESTBOOK, GuestbookEntry::getEntryId));
							consumer.accept(new WorkflowedModelPermissionLogic<>(workflowPermission,
									modelResourcePermission, groupLocalService, GuestbookEntry::getEntryId));
						}),
				properties);
	}

	@Deactivate
	public void deactivate() {
		serviceRegistration.unregister();
	}

}
