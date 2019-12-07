package zrk.learning.service.impl;

import zrk.learning.dao.UserDao;
import zrk.learning.dao.impl.UserDaoImpl;
import zrk.learning.domain.User;
import zrk.learning.service.UserService;
import zrk.learning.util.MailUtils;
import zrk.learning.util.UuidUtil;

public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    /**
     * 注册用户
     * @param user
     * @return
     */
    @Override
    public boolean regist(User user) {
        //根据用户名查询用户对象
        User u = userDao.findByUsername(user.getUsername());
        //判断u是否为null
        if (u != null){
            //用户名存在，注册失败
            return false;
        }
        //保存用户信息
        //设置激活码，唯一字符串
        user.setCode(UuidUtil.getUuid());
        //设置激活状态
        user.setStatus("N");
        userDao.save(user);

        //激活邮件发送，邮件正文
        String content = "<a href='http://localhost:80/travel/user/active?code="+user.getCode()+"'>点击激活【旅游网】</a>";
        MailUtils.sendMail(user.getEmail(),content,"激活邮件");
        return true;
    }

    /**
     * 激活用户
     * @param code
     * @return
     */
    @Override
    public boolean active(String code) {
        //根据激活码查询用户对象
        User user = userDao.findByCode(code);
        if (user != null) {
            //调用dao的修改激活状态的方法
            userDao.updateStatus(user);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public User login(User user) {
        return userDao.findByUsernameAndPassword(user.getUsername(),user.getPassword());
    }

}
