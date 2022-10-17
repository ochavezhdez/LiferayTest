package com.liferay.docs.guestbook.cmd.resource;

import java.util.Date;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;

import com.liferay.docs.guestbook.constants.UserPortletKeys;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.util.ParamUtil;

@Component(
		property = { 
				"javax.portlet.name=" + UserPortletKeys.USER, 
				"mvc.command.name=deleteUser" 
		}, 
		service = MVCResourceCommand.class
)
public class DeleteUserResourceCommand extends BaseMVCResourceCommand {

	@Override
	protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws Exception {
		try {
			long guestBookId = ParamUtil.getLong(resourceRequest, "userId", -1L);
			System.out.println(new Date() + " => " + this.getClass().getSimpleName() + " => " + guestBookId);
		} catch (Exception e) {
			System.out.println(new Date() + " => " + this.getClass().getSimpleName() + " => Error");
		}
	}

}
