package com.common.auth.service;

import com.common.module.user.dao.UserDAO;
import com.common.module.user.dto.User;
import com.common.module.user.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AuthUserDetailService implements UserDetailsService {

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private UserRoleService userRoleService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDAO.getByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}
		List<String> rolePermissions = userRoleService.getAllRoleAndPermissions();
		return new AuthUser(user,rolePermissions);
	}

}
