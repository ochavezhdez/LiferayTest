package com.liferay.student.search;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.search.batch.BatchIndexingActionable;
import com.liferay.portal.search.indexer.IndexerDocumentBuilder;
import com.liferay.portal.search.indexer.IndexerWriter;
import com.liferay.student.model.Student;

@Component(
	immediate = true,
	service = StudentBatchReindexer.class
)
public class StudentBatchReindexerImpl implements StudentBatchReindexer {

	@Reference(target = "(indexer.class.name=com.liferay.student.model.Student)")
	protected IndexerDocumentBuilder indexerDocumentBuilder;

	@Reference(target = "(indexer.class.name=com.liferay.student.model.Student)")
	protected IndexerWriter<Student> indexerWriter;

	@Override
	public void reindex(long studentId, long companyId) {
		BatchIndexingActionable batchIndexingActionable = indexerWriter.getBatchIndexingActionable();
		batchIndexingActionable.setAddCriteriaMethod(dynamicQuery -> {
			Property guestbookIdPropery = PropertyFactoryUtil.forName("studentId");
			dynamicQuery.add(guestbookIdPropery.eq(studentId));
		});
		batchIndexingActionable.setCompanyId(companyId);
		batchIndexingActionable.setPerformActionMethod((Student student) -> {
			Document document = indexerDocumentBuilder.getDocument(student);
			batchIndexingActionable.addDocuments(document);
		});
		batchIndexingActionable.performActions();
	}

}
