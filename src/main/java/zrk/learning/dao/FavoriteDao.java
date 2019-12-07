package zrk.learning.dao;

import zrk.learning.domain.Favorite;

public interface FavoriteDao {
    /**
     * 根据rid和uid查询收藏信息
     * @param rid
     * @param uid
     * @return
     */
    public Favorite findByRidAndUid(int rid, int uid);

    int findCountByRid(int rid);

    void add(int parseInt, int uid);
}
