package com.liferay.docs.guestbook.cmd.resource;

import java.util.Date;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;

import com.liferay.docs.guestbook.constants.UserPortletKeys;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.ParamUtil;

@Component(
		property = { 
				"javax.portlet.name=" + UserPortletKeys.USER, 
				"mvc.command.name=getUser" 
		}, 
		service = MVCResourceCommand.class
)
public class GetUserResourceCommand extends BaseMVCResourceCommand {

	@Override
	protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws Exception {
		try {
			long userId = ParamUtil.getLong(resourceRequest, "userId", -1L);
			System.out.println(new Date() + " => " + this.getClass().getSimpleName() + " => " + userId);

			User user = UserLocalServiceUtil.getUser(userId);
			JSONObject userJSON = JSONFactoryUtil.createJSONObject();
			userJSON.put("id", user.getUserId());
			userJSON.put("name", user.getFullName());
			userJSON.put("uuid", user.getUserUuid());
			userJSON.put("email", user.getEmailAddress());
			resourceResponse.getWriter().print(userJSON);
		} catch (Exception e) {
			System.out.println(new Date() + " => " + this.getClass().getSimpleName() + " => Error");
		}
	}

}
