package com.project.gelingeducation.dao.Impl;

import com.project.gelingeducation.dao.ILogDao;
import com.project.gelingeducation.domain.Log;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Repository
public class LogDaoImpl extends BaseDao implements ILogDao {


    @Override
    public Object queryAll(int currentPage, int pageSize) {
        return null;
    }

    @Override
    public void save(Log log) {
        getSession().save(log);
    }

    @Override
    public Object findByErrDetail(Long id) {
        return null;
    }

    @Override
    public void download(List<Log> logs, HttpServletResponse response) throws IOException {

    }

    @Override
    public void delAllByError() {

    }

    @Override
    public void delAllByInfo() {

    }
}
