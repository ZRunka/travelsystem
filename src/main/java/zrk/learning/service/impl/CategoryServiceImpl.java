package zrk.learning.service.impl;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;
import zrk.learning.dao.CategoryDao;
import zrk.learning.dao.impl.CategoryDaoImpl;
import zrk.learning.domain.Category;
import zrk.learning.service.CategoryService;
import zrk.learning.util.JedisUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CategoryServiceImpl implements CategoryService {

    private CategoryDao categoryDao = new CategoryDaoImpl();

    @Override
    public List<Category> findAll() {
        //1.从redis中查询
        //1.1获取Jedis客户端
        Jedis jedis = JedisUtil.getJedis();
        //1.2可使用sortedset排序查询
        //Set<Tuple> categorys = jedis.zrange("category",0,-1);
        //1.3查询sortedset中的分数（cid）和值（cname）
        Set<Tuple> categorys = jedis.zrangeWithScores("category",0,-1);

        List<Category> cs = null;
        //2.判断查询的集合是否为空
        if (categorys == null || categorys.size() == 0){

            //3.如果为空，需要从数据库查询，在将数据存入redis
            //3.1从数据库查询
            cs = categoryDao.findAll();
            //3.2将集合数据存储到rdis中的category的key中
            for (int i = 0;i < cs.size(); i++){
                jedis.zadd("category",cs.get(i).getCid(),cs.get(i).getCname());
            }
        }else {
            //4.如果不为空，将set的数据存入list
            cs = new ArrayList<Category>();
            for (Tuple tuple : categorys){
                Category category = new Category();
                category.setCname(tuple.getElement());
                category.setCid((int) tuple.getScore());
                cs.add(category);
            }
        }
        return cs;
    }
}
