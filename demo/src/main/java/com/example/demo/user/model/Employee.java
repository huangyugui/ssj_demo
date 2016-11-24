package com.example.demo.user.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import com.example.demo.base.model.CommonBean;


/**
 * KeysPubEmployee entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "keys_pub_employee")
public class Employee extends CommonBean implements UserDetails {

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -7181230449995705883L;

	private Integer baseCode; // 员工编号
	private String baseRealName; // 真名
	private String baseNickname; // 花名
	private String password; // 密码

	public Integer getBaseCode() {
		return baseCode;
	}

	public void setBaseCode(Integer baseCode) {
		this.baseCode = baseCode;
	}

	public String getBaseRealName() {
		return baseRealName;
	}

	public void setBaseRealName(String baseRealName) {
		this.baseRealName = baseRealName;
	}

	public String getBaseNickname() {
		return baseNickname;
	}

	public void setBaseNickname(String baseNickname) {
		this.baseNickname = baseNickname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	//////////// UserDetail ///////////////
	@Transient
	private Set<GrantedAuthority> authorities;

	@Override
	@Transient
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = Collections.unmodifiableSet(sortAuthorities(authorities));
	}

	@Override
	@Transient
	public String getUsername() {
		return getBaseNickname();
	}

	@Override
	@Transient
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	@Transient
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	@Transient
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	@Transient
	public boolean isEnabled() {
		return true;
	}

	// copied from org.springframework.security.core.userdetails.User
	private static SortedSet<GrantedAuthority> sortAuthorities(Collection<? extends GrantedAuthority> authorities) {
		Assert.notNull(authorities, "Cannot pass a null GrantedAuthority collection");
		// Ensure array iteration order is predictable (as per
		// UserDetails.getAuthorities() contract and SEC-717)
		SortedSet<GrantedAuthority> sortedAuthorities = new TreeSet<GrantedAuthority>(new AuthorityComparator());

		for (GrantedAuthority grantedAuthority : authorities) {
			Assert.notNull(grantedAuthority, "GrantedAuthority list cannot contain any null elements");
			sortedAuthorities.add(grantedAuthority);
		}
		return sortedAuthorities;
	}

	private static class AuthorityComparator implements Comparator<GrantedAuthority>, Serializable {
		private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

		public int compare(GrantedAuthority g1, GrantedAuthority g2) {
			if (g2.getAuthority() == null) {
				return -1;
			}
			if (g1.getAuthority() == null) {
				return 1;
			}
			return g1.getAuthority().compareTo(g2.getAuthority());
		}
	}

}