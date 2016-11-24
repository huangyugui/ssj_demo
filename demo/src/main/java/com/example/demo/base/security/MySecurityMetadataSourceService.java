package com.example.demo.base.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.example.demo.base.utils.WebUtil;

/**
 * 
 * @ClassName: MySecurityMetadataSourceService
 * @Description: 查询数据库中所有的权限列表
 * @date 2015年7月27日 下午8:11:15
 */
public class MySecurityMetadataSourceService implements FilterInvocationSecurityMetadataSource {

	protected final Log logger = LogFactory.getLog(getClass());

	private static Map<RequestMatcher, Collection<ConfigAttribute>> requestMap = Collections.synchronizedMap(new LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>>());


	// ~ Methods
	// ========================================================================================================

	private void initRequestMap() {
//		List<Map<String, Object>> jobList = jobAuthService.getAllAuthorities();
//		for (Map<String, Object> map : jobList) {
//			if (!StringUtils.isEmpty(map.get("job_key")) && !StringUtils.isEmpty(map.get("authority_url")) && !"#".equals(((String) map.get("authority_url")).trim())) {
//				AntPathRequestMatcher matcher = new AntPathRequestMatcher((String) map.get("authority_url"));
//				Collection<ConfigAttribute> attrs = requestMap.get(matcher);
//				if (attrs == null) {
//					attrs = new ArrayList<>();
//					attrs.add(new SecurityConfig("ROLE_ADMIN"));
//					requestMap.put(matcher, attrs);
//				}
//				attrs.add(new SecurityConfig((String) map.get("job_key")));
//			}
//		}
		AntPathRequestMatcher matcher = new AntPathRequestMatcher("/user/list");
		Collection<ConfigAttribute> attrs = new ArrayList<ConfigAttribute>();
		attrs.add(new SecurityConfig("ROLE_ADMIN"));
		attrs.add(new SecurityConfig("ROLE_MR"));
		requestMap.put(matcher, attrs);
		
		AntPathRequestMatcher matcher1 = new AntPathRequestMatcher("/user/save/*");
		Collection<ConfigAttribute> attrs1 = new ArrayList<ConfigAttribute>();
		attrs1.add(new SecurityConfig("ROLE_ADMIN"));
		requestMap.put(matcher1, attrs1);
		
		AntPathRequestMatcher matcher2 = new AntPathRequestMatcher("/login/anonymous");
		Collection<ConfigAttribute> attrs2 = new ArrayList<ConfigAttribute>();
		attrs2.add(new SecurityConfig("ROLE_ANONYMOUS"));
		attrs2.add(new SecurityConfig("ROLE_MR"));
		
		requestMap.put(matcher2, attrs2);
		
	}

	public Collection<ConfigAttribute> getAllConfigAttributes() {
		if (requestMap.isEmpty()) {
			initRequestMap();
		}
		Set<ConfigAttribute> allAttributes = new HashSet<ConfigAttribute>();

		for (Map.Entry<RequestMatcher, Collection<ConfigAttribute>> entry : requestMap.entrySet()) {
			allAttributes.addAll(entry.getValue());
		}

		return allAttributes;
	}

	public Collection<ConfigAttribute> getAttributes(Object object) {
		if (requestMap.isEmpty()) {
			initRequestMap();
		}

		// 未登录
		Collection<ConfigAttribute> attrs = new ArrayList<>();
		if (null == WebUtil.getCurrentLoginRole()) {
			attrs.add(new SecurityConfig("ROLE_ADMIN"));
			return attrs;
		}

		final HttpServletRequest request = ((FilterInvocation) object).getRequest();
		for (Map.Entry<RequestMatcher, Collection<ConfigAttribute>> entry : requestMap.entrySet()) {
			if (!WebUtil.getCurrentLoginRole().contains("ROLE_ADMIN") && !entry.getKey().toString().equals("Ant [pattern='/**']") && entry.getKey().matches(request)) {
				return entry.getValue();
			}
		}

		// 没有配置权限，默认可以访问
		for (SimpleGrantedAuthority simpleGrantedAuthority : WebUtil.getCurrentLoginRole()) {
			attrs.add(new SecurityConfig(simpleGrantedAuthority.getAuthority()));
		}

		return attrs;

	}

	public boolean supports(Class<?> clazz) {
		return FilterInvocation.class.isAssignableFrom(clazz);
	}

	public void refreshMap() {
		requestMap.clear();
	}
}
