package com.liferay.student.cmd.action;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.student.constants.StudentPortletKeys;
import com.liferay.student.service.StudentLocalService;

@Component(
	property = {
		"javax.portlet.name=" + StudentPortletKeys.STUDENT,
		"mvc.command.name=deleteStudentAction"
	},
	service = MVCActionCommand.class
)
public class DeleteStudentActionCommand extends BaseMVCActionCommand {

	@Reference
	private StudentLocalService studentLocalService;

	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		long studentId = ParamUtil.getLong(actionRequest, "studentId", GetterUtil.DEFAULT_LONG);

		try {
			studentLocalService.deleteStudent(studentId);
			SessionMessages.add(actionRequest, "studentDeleted");
		} catch (Exception e) {
			SessionErrors.add(actionRequest, e.getClass().getName());
			throw e;
		}
	}

}
