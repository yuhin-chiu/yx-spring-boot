package cn.yx.service;

import org.springframework.stereotype.Service;

import cn.yx.model.Admin;
import cn.yx.util.MD5Util;

/**
 * @author yuxuanjiao
 * @date 2017年8月1日 下午7:57:11 
 * @version 1.0
 */

@Service
public class AdminService {

    public Admin checkUser(Admin admin) {
//        return fxbAdminMapper.checkUser(admin.getAccount());
        if("admin".equals(admin.getAccount())) {
            Admin ad = new Admin();
            ad.setAccount("admin");
            ad.setUserName("admin");
            ad.setPasswd(MD5Util.toMD5("888888"));
            return ad;
        }
        
        return null;
    }

    public void addUser(String account, String password, String userName) {
//        fxbAdminMapper.addUser(account, password, userName);
        
    }
}
