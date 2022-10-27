package com.liferay.student.search;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.search.batch.BatchIndexingActionable;
import com.liferay.portal.search.batch.DynamicQueryBatchIndexingActionableFactory;
import com.liferay.portal.search.spi.model.index.contributor.ModelIndexerWriterContributor;
import com.liferay.portal.search.spi.model.index.contributor.helper.ModelIndexerWriterDocumentHelper;
import com.liferay.student.model.Student;
import com.liferay.student.service.StudentLocalService;

@Component(
	immediate = true,
	property = "indexer.class.name=com.liferay.student.model.Student",
	service = ModelIndexerWriterContributor.class
)
public class StudentModelIndexerWriterContributor implements ModelIndexerWriterContributor<Student> {

	@Reference
	protected DynamicQueryBatchIndexingActionableFactory dynamicQueryBatchIndexingActionableFactory;

	@Reference
	protected StudentLocalService studentLocalService;

	@Override
	public void customize(BatchIndexingActionable batchIndexingActionable,
			ModelIndexerWriterDocumentHelper modelIndexerWriterDocumentHelper) {
		batchIndexingActionable.setPerformActionMethod((Student student) -> {
			Document document = modelIndexerWriterDocumentHelper.getDocument(student);
			batchIndexingActionable.addDocuments(document);
		});
	}

	@Override
	public BatchIndexingActionable getBatchIndexingActionable() {
		return dynamicQueryBatchIndexingActionableFactory
				.getBatchIndexingActionable(studentLocalService.getIndexableActionableDynamicQuery());
	}

	@Override
	public long getCompanyId(Student baseModel) {
		return baseModel.getCompanyId();
	}

}
