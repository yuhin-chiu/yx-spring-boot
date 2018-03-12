package cn.yx.service;

import cn.yx.mapper.AdminMapper;
import cn.yx.model.Admin;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author yuxuanjiao
 * @date 2017年8月1日 下午7:57:11 
 * @version 1.0
 */

@Service
public class AdminService {

    @Resource
    private AdminMapper adminMapper;

    @Value("${spring.profiles}")
    private String profiles;
    
    public Admin checkUser(Admin admin) {
        System.out.println(profiles);
        if("dev".equals(profiles)) {
            admin.setUserName("dev");
            return admin;
        }
        return adminMapper.checkUser(admin.getAccount());
    }

    public void addUser(String account, String password, String userName) {
        adminMapper.addUser(account, password, userName);
    }

    public void modifyUser(String account, String password) {
        adminMapper.modifyUser(account, password);
    }
}
