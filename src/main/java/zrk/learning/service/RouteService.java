package zrk.learning.service;

import zrk.learning.domain.PageBean;
import zrk.learning.domain.Route;

public interface RouteService {

    /**
     * 根据类别进行分页查询
     * @param cid
     * @param currentPage
     * @param pageSize
     * @return
     */
    public PageBean<Route> pageQuery(int cid,int currentPage,int pageSize,String rname);

    Route findOne(String rid);
}
