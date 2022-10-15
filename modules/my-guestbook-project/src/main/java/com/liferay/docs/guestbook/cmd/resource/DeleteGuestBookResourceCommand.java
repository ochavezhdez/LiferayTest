package com.liferay.docs.guestbook.cmd.resource;

import com.liferay.docs.guestbook.constants.GuestbookPortletKeys;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import java.util.Date;

@Component(
        property = {
                "javax.portlet.name=" + GuestbookPortletKeys.GUESTBOOK,
                "mvc.command.name=deleteGuestBook"
        },
        service = MVCResourceCommand.class
)
public class DeleteGuestBookResourceCommand extends BaseMVCResourceCommand {

    @Override
    protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
        try {
            long guestBookId = ParamUtil.getLong(resourceRequest, "guestBookId", -1L);
            System.out.println(new Date() + " => " + this.getClass().getSimpleName() + " => " + guestBookId);
        } catch (Exception e) {
            System.out.println(new Date() + " => " + this.getClass().getSimpleName() + " => Error");
        }
    }

}
