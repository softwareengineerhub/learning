package com.app.dao.pagination;

public interface PaginationDao {

    public void findData(int limit, int pageSize);

    public void findDataCriteria(int limit);

}
