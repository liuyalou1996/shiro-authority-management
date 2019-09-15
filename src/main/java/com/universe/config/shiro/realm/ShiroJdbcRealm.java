package com.universe.config.shiro.realm;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.universe.common.entity.po.Permission;
import com.universe.common.entity.po.Role;
import com.universe.common.entity.po.User;
import com.universe.service.PermissionService;
import com.universe.service.RoleService;
import com.universe.service.UserService;

public class ShiroJdbcRealm extends AuthorizingRealm {

  @Autowired
  private UserService userService;

  @Autowired
  private RoleService roleService;

  @Autowired
  private PermissionService permissionService;

  @Override
  protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authToken) throws AuthenticationException {
    UsernamePasswordToken token = (UsernamePasswordToken) authToken;
    String username = token.getUsername();
    User user = userService.getUserByUsername(username);

    if (Objects.isNull(user)) {
      throw new UnknownAccountException("用户名不存在!");
    }

    String password = new String(token.getPassword());
    if (!user.getPassword().equals(password)) {
      throw new IncorrectCredentialsException("密码错误!");
    }

    return new SimpleAuthenticationInfo(token.getPrincipal(), token.getCredentials(), super.getName());
  }

  @Override
  protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
    String username = (String) principals.getPrimaryPrincipal();
    List<Role> roleList = roleService.getRolesByUsername(username);
    List<Permission> permissionList = permissionService.getPermissionsByUsername(username);

    Set<String> roleSet = new HashSet<String>();
    roleList.forEach(role -> roleSet.add(role.getRoleName()));

    Set<String> permissionSet = new HashSet<String>();
    permissionList.forEach(permission -> permissionSet.add(permission.getPermName()));

    SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
    info.setRoles(roleSet);
    info.setStringPermissions(permissionSet);

    return info;
  }

}
