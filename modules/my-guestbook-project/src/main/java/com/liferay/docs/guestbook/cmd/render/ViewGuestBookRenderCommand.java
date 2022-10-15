package com.liferay.docs.guestbook.cmd.render;

import com.liferay.docs.guestbook.constants.GuestbookPortletKeys;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import org.osgi.service.component.annotations.Component;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.util.Date;

@Component(
        property = {
                "javax.portlet.name=" + GuestbookPortletKeys.GUESTBOOK,
                "mvc.command.name=viewGuestBook"
        },
        service = MVCRenderCommand.class
)
public class ViewGuestBookRenderCommand implements MVCRenderCommand {

    public static final String VIEW_GUEST_BOOK = "/viewGuestBook.jsp";
    public static final String ERROR = "/error.jsp";

    @Override
    public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
        try {
            long guestBookId = ParamUtil.getLong(renderRequest, "guestBookId", -1L);
            System.out.println(new Date() + " => " + this.getClass().getSimpleName() + " => " + guestBookId);
        } catch (Exception e) {
            return ERROR;
        }

        return VIEW_GUEST_BOOK;
    }

}
