package com.liferay.docs.guestbook.portlet;

import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;

import com.liferay.docs.guestbook.constants.UserPortletKeys;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

/**
 * @author osvel
 */
@Component(
		immediate = true, 
		property = { 
				"com.liferay.portlet.display-category=category.sample", 
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

}
