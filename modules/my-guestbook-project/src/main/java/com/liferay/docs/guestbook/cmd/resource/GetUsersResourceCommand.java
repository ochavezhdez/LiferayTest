package com.liferay.docs.guestbook.cmd.resource;

import com.liferay.docs.guestbook.constants.UserPortletKeys;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import java.util.Date;
import java.util.List;

@Component(
		property = { 
				"javax.portlet.name=" + UserPortletKeys.USER, 
				"mvc.command.name=getUsers" 
		}, 
		service = MVCResourceCommand.class
)
public class GetUsersResourceCommand extends BaseMVCResourceCommand {

	@Override
	protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws Exception {
		System.out.println(new Date() + " => " + this.getClass().getSimpleName());
		List<User> users = UserLocalServiceUtil.getUsers(QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		JSONArray userListJson = JSONFactoryUtil.createJSONArray();
		for (User user : users) {
			JSONObject userJSON = JSONFactoryUtil.createJSONObject();
			userJSON.put("id", user.getUserId());
			userJSON.put("name", user.getFullName());
			userListJson.put(userJSON);
		}

		resourceResponse.getWriter().print(userListJson);
	}

}
