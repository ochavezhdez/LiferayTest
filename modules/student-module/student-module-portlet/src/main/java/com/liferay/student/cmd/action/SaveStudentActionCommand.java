package com.liferay.student.cmd.action;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.student.constants.StudentPortletKeys;
import com.liferay.student.dto.StudentDTO;
import com.liferay.student.model.Student;
import com.liferay.student.service.StudentLocalService;

@Component(
	property = { 
		"javax.portlet.name=" + StudentPortletKeys.STUDENT,
		"mvc.command.name=saveStudentAction"
	},
	service = MVCActionCommand.class
)
public class SaveStudentActionCommand extends BaseMVCActionCommand {

	@Reference
	private StudentLocalService studentLocalService;

	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		ServiceContext serviceContext = ServiceContextFactory.getInstance(Student.class.getName(), actionRequest);

		StudentDTO studentDTO = new StudentDTO();
		studentDTO.setStudentId(ParamUtil.getLong(actionRequest, "studentId", GetterUtil.DEFAULT_LONG));
		studentDTO.setEnrollmentNo(ParamUtil.getString(actionRequest, "enrollmentNo", GetterUtil.DEFAULT_STRING));
		studentDTO.setFirstName(ParamUtil.getString(actionRequest, "firstName", GetterUtil.DEFAULT_STRING));
		studentDTO.setLastName(ParamUtil.getString(actionRequest, "lastName", GetterUtil.DEFAULT_STRING));
		studentDTO.setContactNo(ParamUtil.getString(actionRequest, "contactNo", GetterUtil.DEFAULT_STRING));
		studentDTO.setCity(ParamUtil.getString(actionRequest, "city", GetterUtil.DEFAULT_STRING));

		try {
			studentLocalService.saveStudent(serviceContext.getUserId(), studentDTO, serviceContext);
			SessionMessages.add(actionRequest, "studentSaved");
		} catch (Exception e) {
			SessionErrors.add(actionRequest, e.getClass().getName());
			throw e;
		}
	}

}
