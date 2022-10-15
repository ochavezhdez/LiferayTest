package com.liferay.docs.guestbook.cmd.action;

import com.liferay.docs.guestbook.constants.GuestbookPortletKeys;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import java.util.Date;

@Component(
        property = {
                "javax.portlet.name=" + GuestbookPortletKeys.GUESTBOOK,
                "mvc.command.name=saveGuestBook"
        },
        service = MVCActionCommand.class
)
public class SaveGuestBookActionCommand extends BaseMVCActionCommand {

    @Override
    protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
        System.out.println(new Date() + " => " + this.getClass().getSimpleName());
    }

}
