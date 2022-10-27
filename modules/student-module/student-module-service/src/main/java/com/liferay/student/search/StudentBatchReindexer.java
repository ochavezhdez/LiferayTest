package com.liferay.student.search;

public interface StudentBatchReindexer {

	void reindex(long studentId, long companyId);

}
