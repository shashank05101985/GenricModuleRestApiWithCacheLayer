package com.common.module.user.controller;

import java.util.Collection;

import com.common.module.user.dto.User;
import com.common.module.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/getAll")
	public Collection<User> getAllUser() {
		return userService.getAll();
	}

}
