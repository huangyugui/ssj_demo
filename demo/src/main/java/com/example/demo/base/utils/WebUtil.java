/**
 * @Title: WebUtil.java
 * @Package sbd.keys.base.util
 * @Description: 
 * @author 张华
 * @date 2015年7月28日 上午11:57:01
 */
package com.example.demo.base.utils;

import java.util.Collection;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import com.example.demo.user.model.Employee;

/**
 * @ClassName: WebUtil
 * @Description:
 * @author 张华
 * @date 2015年7月28日 上午11:57:01
 */
public final class WebUtil {

	/**
	 * 
	 * @Title: getCurrentLoginVo
	 * @Description: 返回当前登录用户信息
	 * @author 张华
	 * @date 2015年7月28日 下午12:02:16
	 *
	 * @param request
	 * @return
	 */
	public static Employee getCurrentLoginVo() {
		if (SecurityContextHolder.getContext().getAuthentication() != null) {
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (principal instanceof Employee) {
				return (Employee) principal;
			}
		}
		return null;
	}

	/**
	 * 
	 * @Title: getCurrentLoginId
	 * @Description: 返回当前登录用户ID
	 * @author 张华
	 * @date 2015年7月28日 下午2:16:04
	 *
	 * @param request
	 * @return
	 */
	public static String getCurrentLoginId() {
		Employee employee = getCurrentLoginVo();
		return (null != employee) ? employee.getId() : "";
	}

	/**
	 * 
	 * @Title: getCurrentLoginName
	 * @Description: 返回当前登录用户账号
	 * @author 张华
	 * @date 2015年7月28日 下午2:16:19
	 *
	 * @param request
	 * @return
	 */
	public static String getCurrentLoginName() {
		Employee employee = getCurrentLoginVo();
		return (null != employee) ? employee.getBaseNickname() : "";
	}
	
	/**
	 * 
	 * @Title: getCurrentLoginRole
	 * @Description: 返回当前登录用户角色
	 * @author 张华
	 * @date 2015年10月10日 下午5:36:36
	 *
	 * @return
	 */
	public static Collection<SimpleGrantedAuthority> getCurrentLoginRole() {
		if (SecurityContextHolder.getContext().getAuthentication() != null) {
			return (Collection<SimpleGrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		}

		return null;
	}
}
