package com.example.demo.base.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * 
 * @ClassName: MyUsernamePasswordAuthenticationFilter
 * @Description: 登录用户密码验证
 * @date 2015年7月27日 下午5:34:17
 */
public class MyUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	public static final String PIC = "validateCode";

	private Boolean useCaptcha = true;

	/**
	 * 
	 * @Title: attemptAuthentication
	 * @Description: 登录用户密码验证
	 * @date 2015年7月27日 下午5:42:30
	 *
	 * @param request
	 * @param response
	 * @return
	 * @throws AuthenticationException
	 */
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		// 验证验证码
		if (useCaptcha && !checkPic(request)) {
			throw new AuthenticationServiceException("check Picture error: ");
		}

		return super.attemptAuthentication(request, response);
	}

	/**
	 * 
	 * @Title: checkPic
	 * @Description: 验证验证码
	 * @date 2015年7月27日 下午5:41:02
	 *
	 * @param request
	 * @return
	 */
	private boolean checkPic(HttpServletRequest request) {
		String pic = (String) request.getSession().getAttribute("piccode");
		Object obj = request.getParameter(PIC);
		String sessionPic = (null == obj ? "" : obj.toString());
		if (pic != null) {
			if (sessionPic.toLowerCase().equals(pic.toLowerCase())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public void setUseCaptcha(Boolean useable) {
		this.useCaptcha = useable;
	}

}
