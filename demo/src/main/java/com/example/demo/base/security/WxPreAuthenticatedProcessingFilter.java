package com.example.demo.base.security;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;


/**
 * 
 * @author hubin
 *
 */
public class WxPreAuthenticatedProcessingFilter extends AbstractPreAuthenticatedProcessingFilter {
	
	private Logger logger = LoggerFactory.getLogger(WxPreAuthenticatedProcessingFilter.class);

//	@Autowired
//	WxUserRepository wxUserRepository;
//	@Autowired
//	EmployeeRepository employeeRepository;

	static final String WX_OPENID_COOKIE_NAME = "wxid";

	@Override
	protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
		return "";
	}

	@Override
	protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
		logger.info("WxPreAuthenticatedProcessingFilter getPreAuthenticatedPrincipal : " + request.getRequestURI());

		if (!request.getRequestURI().contains("/wx/")) {
			return null;
		}

//		Cookie[] cookies = request.getCookies();
//
//		if (cookies != null) {
//			for (Cookie cookie : cookies) {
//				if (WX_OPENID_COOKIE_NAME.equals(cookie.getName())) {
//					System.out.println(
//							"WxPreAuthenticatedProcessingFilter getPreAuthenticatedPrincipal : wxid cookie is exsit : "
//									+ cookie.getValue());
//					WxUser wxUser = wxUserRepository.findOneByOpenid(cookie.getValue());
//
//					if (wxUser != null && StringUtils.isNotBlank(wxUser.getUserId())) {
//						Employee employee = employeeRepository.findOneByUserId(wxUser.getUserId());
//						if (employee != null) {
//							return employee.getBaseNickname();
//						}
//					}
//				}
//			}
//		}
//
//		return "admin";
		return "baseNickName";
	}

}
