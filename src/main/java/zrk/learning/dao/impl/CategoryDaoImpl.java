package zrk.learning.dao.impl;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import zrk.learning.dao.CategoryDao;
import zrk.learning.domain.Category;
import zrk.learning.util.JDBCUtils;

import java.util.List;

public class CategoryDaoImpl implements CategoryDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<Category> findAll() {
        String sql = "select * from tab_category";
        return template.query(sql,new BeanPropertyRowMapper<Category>(Category.class));
    }
}
