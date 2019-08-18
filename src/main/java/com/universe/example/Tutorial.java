package com.universe.example;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

@SuppressWarnings("deprecation")
public class Tutorial {

  public static void main(String[] args) {
    Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
    SecurityManager securityManager = factory.getInstance();
    SecurityUtils.setSecurityManager(securityManager);

    // 获取当前用户
    Subject currentUser = SecurityUtils.getSubject();

    // 如果是web应用，那么session就是HttpSession，如在非web环境中默认会使用企业级会话管理
    Session session = currentUser.getSession();
    session.setAttribute("somekey", "someValue");

    // 如果未认证过则进行认证
    if (!currentUser.isAuthenticated()) {
      UsernamePasswordToken token = new UsernamePasswordToken("root", "root");
      token.setRememberMe(true);
      try {
        currentUser.login(token);
      } catch (UnknownAccountException uae) {
        System.err.println("There is no user with username of " + token.getPrincipal());
      } catch (IncorrectCredentialsException ice) {
        System.err.println("Password for account " + token.getPrincipal() + " was incorrect!");
      } catch (LockedAccountException lae) {
        System.err.println("The account for username " + token.getPrincipal() + " is locked.  "
            + "Please contact your administrator to unlock it.");
      }
    }

    if (!currentUser.isPermitted("winnebago:drive:eagle5")) {
      System.err.println("You don't have this permission!");
    } else {
      System.err.println("You are permitted to drive the eagle5");
    }

    currentUser.logout();
  }
}
