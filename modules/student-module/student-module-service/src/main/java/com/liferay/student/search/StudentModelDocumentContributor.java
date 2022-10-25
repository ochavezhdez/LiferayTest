package com.liferay.student.search;

import java.util.Locale;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.search.spi.model.index.contributor.ModelDocumentContributor;
import com.liferay.student.model.Student;

@Component(
	immediate = true,
	property = "indexer.class.name=com.liferay.student.model.Student",
	service = ModelDocumentContributor.class
)
public class StudentModelDocumentContributor implements ModelDocumentContributor<Student> {

	private static final Log log = LogFactoryUtil.getLog(StudentModelDocumentContributor.class);

	@Override
	public void contribute(Document document, Student baseModel) {
		try {
			document.addDate(Field.MODIFIED_DATE, baseModel.getModifiedDate());

			Locale defaultLocale = PortalUtil.getSiteDefaultLocale(baseModel.getGroupId());

			String localizedFirstName = LocalizationUtil.getLocalizedName("firstName", defaultLocale.toString());
			String localizedLastName = LocalizationUtil.getLocalizedName("lastName", defaultLocale.toString());

			document.addText(localizedFirstName, baseModel.getFirstName());
			document.addText(localizedLastName, baseModel.getLastName());
		} catch (PortalException pe) {
			if (log.isWarnEnabled()) {
				log.warn("Unable to index guestbook " + baseModel.getStudentId(), pe);
			}
		}
	}

}
