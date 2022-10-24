package com.liferay.docs.guestbook.portlet;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import com.liferay.docs.guestbook.constants.UserPortletKeys;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;

/**
 * @author osvel
 */
@Component(
		immediate = true, 
		property = { 
				"com.liferay.portlet.display-category=category.news", 
				"com.liferay.portlet.header-portlet-css=/css/main.css", 
				"com.liferay.portlet.instanceable=true", 
				"javax.portlet.display-name=User", 
				"javax.portlet.init-param.template-path=/", 
				"javax.portlet.init-param.view-template=/view.jsp", 
				"javax.portlet.name=" + UserPortletKeys.USER, 
				"javax.portlet.resource-bundle=content.Language", 
				"javax.portlet.security-role-ref=power-user,user"
		}, 
		service = Portlet.class
)
public class UserPortlet extends MVCPortlet {

	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		System.out.println(new Date() + " => " + this.getClass().getSimpleName());
		List<User> userList = UserLocalServiceUtil.getUsers(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
		renderRequest.setAttribute("userList", userList);
		super.render(renderRequest, renderResponse);
	}

}
