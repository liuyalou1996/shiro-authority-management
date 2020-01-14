package com.universe.config.shiro.realm;

import com.universe.common.emuneration.UserStatusEnum;
import com.universe.pojo.domain.UserDo;
import com.universe.service.ResourceService;
import com.universe.service.RoleService;
import com.universe.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
/**
 * @author 刘亚楼
 * @date 2020/1/14
 */
public class ShiroJdbcRealm extends AuthorizingRealm {

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private ResourceService resourceService;

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authToken) throws AuthenticationException {
		String loginUsername = ((UsernamePasswordToken) authToken).getUsername();
		UserDo user = userService.getUserByUsername(loginUsername);
		if (user == null) {
			throw new UnknownAccountException("账号不存在!");
		}

		String userStatus = user.getStatus();
		if (UserStatusEnum.LOCKED.getValue().equals(userStatus)) {
			throw new LockedAccountException("账号已被锁定!");
		}
		if (UserStatusEnum.FORBIDDEN.getValue().equals(userStatus)) {
			throw new DisabledAccountException("账号已被禁用!");
		}

		// 返回认证信息
		SimpleAuthenticationInfo authInfo = new SimpleAuthenticationInfo();
		authInfo.setPrincipals(new SimplePrincipalCollection(user.getUsername(), super.getName()));
		authInfo.setCredentials(user.getPassword());
		authInfo.setCredentialsSalt(ByteSource.Util.bytes(user.getSalt()));
		return authInfo;
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String username = (String) principals.getPrimaryPrincipal();
		List<String> roleList = roleService.getRolesByUsername(username);
		List<String> permissionList = resourceService.getResourceCodeByUsername(username);
		Set<String> roleSet = new HashSet<String>(roleList);
		Set<String> permissionSet = new HashSet<>(permissionList);

		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		authorizationInfo.setRoles(roleSet);
		authorizationInfo.setStringPermissions(permissionSet);
		return authorizationInfo;
	}

}
