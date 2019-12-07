package zrk.learning.dao;

import zrk.learning.domain.Seller;

public interface SellerDao {

    /**
     * 根据id查询
     * @param id
     * @return
     */
    public Seller findById(int id);
}
