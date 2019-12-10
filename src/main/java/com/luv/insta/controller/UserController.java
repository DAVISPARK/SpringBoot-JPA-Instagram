package com.luv.insta.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.luv.insta.model.User;
import com.luv.insta.repository.UserRepository;
import com.luv.insta.util.Script;

@Controller
public class UserController {
	
	@Autowired
	private UserRepository mRepo;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@PostMapping("/auth/joinProc")
	public String AuthJoinProc(User user) {
		String rawPassword = user.getPassword();
		String encPassword = passwordEncoder.encode(rawPassword);
		user.setPassword(encPassword);
		mRepo.save(user);
		return "auth/login";
	}
	
	
	@GetMapping("/auth/login")
	public String testLogin() {
		return "auth/login";
	}
	
	@GetMapping("/auth/join")
	public String testjoin() {
		return "auth/join";
	}
	
	@GetMapping("/image/feed")
	public String testFeed() {
		return "image/feed";
	}
	
	}

