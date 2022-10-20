package com.liferay.student.cmd.render;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.student.constants.StudentPortletKeys;
import com.liferay.student.model.Student;
import com.liferay.student.service.StudentLocalService;

@Component(
		property = {
				"javax.portlet.name=" + StudentPortletKeys.STUDENT,
				"mvc.command.name=saveStudentRender"
		},
		service = MVCRenderCommand.class
)
public class SaveStudentRender implements MVCRenderCommand {

	public static final String FORM_STUDENT = "/form-student.jsp";
	@Reference
	private StudentLocalService studentLocalService;

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		try {
			long studentId = ParamUtil.getLong(renderRequest, "studentId", GetterUtil.DEFAULT_LONG);

			Student student = null;
			if (studentId == GetterUtil.DEFAULT_LONG) {
				student = studentLocalService.createStudent(studentId);
			} else {
				student = studentLocalService.getStudent(studentId);
			}

			renderRequest.setAttribute("student", student);

		} catch (Exception e) {
			System.err.println(e);
		}

		return FORM_STUDENT;
	}

}
