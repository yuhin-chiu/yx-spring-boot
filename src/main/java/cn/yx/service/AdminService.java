package cn.yx.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.yx.mapper.AdminMapper;
import cn.yx.model.Admin;

/**
 * @author yuxuanjiao
 * @date 2017年8月1日 下午7:57:11 
 * @version 1.0
 */

@Service
public class AdminService {

    @Resource
    private AdminMapper adminMapper;
    
    public Admin checkUser(Admin admin) {
        return adminMapper.checkUser(admin.getAccount());
    }

    public void addUser(String account, String password, String userName) {
        adminMapper.addUser(account, password, userName);
    }

    public void modifyUser(String account, String password) {
        adminMapper.modifyUser(account, password);
    }
}
