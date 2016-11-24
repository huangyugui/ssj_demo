package com.example.demo.base.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.demo.user.model.Employee;

/**
 * 
 * @ClassName: MyUserDetailServiceImpl
 * @Description:
 * @date 2015年7月27日 下午9:57:21
 */
@Component
public class MyUserDetailServiceImpl implements UserDetailsService {

	/**
	 * 员工service
	 */
//	@Resource(name = "employeeService")
//	private EmployeeService employeeService;
//
//	@Autowired
//	private JobService jobService;

	/**
	 * 
	 * @Title: loadUserByUsername
	 * @Description: 真正验证用户密码是否正确
	 * @date 2015年7月27日 下午5:58:11
	 *
	 * @param username
	 * @return
	 * @throws UsernameNotFoundException
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		Employee employee = this.employeeService.findUniqueByProperty("baseNickname", username);
//
//		if (employee == null || !employee.getBaseNickname().equals(username)) {
//			throw new UsernameNotFoundException(username + "用户不存在");
//		}
//		if (EMPLOYEE_OFF.equals(employee.getBaseStatus())) {
//			throw new UsernameNotFoundException(username + "员工已经离职");
//		}
		Employee employee = new Employee();
		employee.setId("11111111111");
		employee.setBaseCode(1);
		employee.setBaseNickname("admin");
		employee.setBaseRealName("超级管理员");
		employee.setPassword("efaabba9e5d3471f00e3a6bce7cbdbff");
		Collection<GrantedAuthority> grantedAuths = obtionGrantedAuthorities(employee);
		
		employee.setAuthorities(grantedAuths);
		return employee;
	}

	/**
	 * 
	 * @Title: obtionGrantedAuthorities
	 * @Description: TODO
	 * @date 2015年7月27日 下午6:43:07
	 *
	 * @param employee
	 * @return
	 */
	private Set<GrantedAuthority> obtionGrantedAuthorities(Employee employee) {
		Set<GrantedAuthority> authSet = new HashSet<GrantedAuthority>();

//		List<Job> jobs = jobService.getEmployeeJobs(employee.getId());
//		for (Job employeeJob : jobs) {
//			if (!StringUtils.isEmpty(employeeJob.getJobKey())) {
//				authSet.add(new SimpleGrantedAuthority(employeeJob.getJobKey()));
//			}
//		}
		authSet.add(new SimpleGrantedAuthority("ROLE_MR"));
		authSet.add(new SimpleGrantedAuthority("ROLE_TECH"));

		return authSet;
	}

}
