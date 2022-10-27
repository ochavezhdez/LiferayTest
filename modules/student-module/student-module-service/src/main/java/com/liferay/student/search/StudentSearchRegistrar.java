package com.liferay.student.search;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.search.spi.model.index.contributor.ModelIndexerWriterContributor;
import com.liferay.portal.search.spi.model.registrar.ModelSearchRegistrarHelper;
import com.liferay.portal.search.spi.model.result.contributor.ModelSummaryContributor;
import com.liferay.student.model.Student;

@Component(immediate = true)
public class StudentSearchRegistrar {

	@Reference(target = "(indexer.class.name=com.liferay.student.model.Student)")
	protected ModelIndexerWriterContributor<Student> modelIndexWriterContributor;

	@Reference
	protected ModelSearchRegistrarHelper modelSearchRegistrarHelper;

	@Reference(target = "(indexer.class.name=com.liferay.student.model.Student)")
	protected ModelSummaryContributor modelSummaryContributor;

	private ServiceRegistration<?> serviceRegistration;

	@Activate
	protected void activate(BundleContext bundleContext) {
		serviceRegistration = modelSearchRegistrarHelper.register(Student.class, bundleContext,
				modelSearchDefinition -> {
					modelSearchDefinition.setDefaultSelectedFieldNames(Field.COMPANY_ID, Field.ENTRY_CLASS_NAME,
							Field.ENTRY_CLASS_PK, Field.UID, Field.SCOPE_GROUP_ID, Field.GROUP_ID);
					modelSearchDefinition.setDefaultSelectedLocalizedFieldNames(Field.TITLE, Field.CONTENT);
					modelSearchDefinition.setModelIndexWriteContributor(modelIndexWriterContributor);
					modelSearchDefinition.setModelSummaryContributor(modelSummaryContributor);
					modelSearchDefinition.setSelectAllLocales(true);
				});
	}

	@Deactivate
	protected void deactivate() {
		serviceRegistration.unregister();
	}

}
