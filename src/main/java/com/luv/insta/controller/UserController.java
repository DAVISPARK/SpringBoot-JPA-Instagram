package com.luv.insta.controller;



import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.luv.insta.model.User;
import com.luv.insta.repository.FollowRepository;
import com.luv.insta.repository.UserRepository;
import com.luv.insta.security.MyUserDetails;


@Controller
public class UserController {
	
	
	private static final Logger log = LoggerFactory.getLogger(UserController.class);

	
	@Autowired
	private UserRepository mUserRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private FollowRepository mFollowRepository;

	@PostMapping("/auth/joinProc")
	public String AuthJoinProc(User user) {
		String rawPassword = user.getPassword();
		String encPassword = passwordEncoder.encode(rawPassword);
		user.setPassword(encPassword);
		mUserRepository.save(user);
		return "redirect:/";
	}
	
	@GetMapping("/user/{id}")
	public String profile(
			@PathVariable int id, 
			@AuthenticationPrincipal MyUserDetails userDetails,
			Model model) {
		
		/**
		 *   1. imageCount
		 *   2. followerCount
		 *   3. followingCount
		 *   4. User 오브젝트 (Image (likeCount) 컬렉션)
		 *   5. followCheck 팔로우 유무 (1 팔로우, 1이 아니면 언팔로우)
		 */
		
		// 4번 임시(수정해야함)
		Optional<User> oUser = mUserRepository.findById(id);
		User user = oUser.get();
		model.addAttribute("user", user);
		
		// 5번
		User principal = userDetails.getUser();
		
		int followCheck = mFollowRepository.countByFromUserIdAndToUserId(principal.getId(), id);
		log.info("followCheck : "+followCheck);
		model.addAttribute("followCheck", followCheck);
		
		return "user/profile";
	}
	
	@GetMapping("/user/edit/{id}")
	public String userEdit(@PathVariable int id) {
		
		// 해당 id로 Select 하기
		// findByUserInfo() 사용 (만들어야 함)
		
		return "user/profile_edit";
	}
	
	@GetMapping("/auth/login")
	public String AuthLogin() {
		return "auth/login";
	}
	
	@GetMapping("/auth/join")
	public String Authjoin() {
		return "auth/join";
	}
	
	@GetMapping("/image/feed")
	public String ImgFeed() {
		return "image/feed";
	}
	
	@GetMapping("/image/explore")
	public String ImgExplore() {
		return "image/explore";
	}
	
	@GetMapping("/user/profile")
	public String UserProfile() {
		return "user/profile";
	}
	@GetMapping("/user/profile_edit")
	public String UserProfileEdit() {
		return "user/profile_edit";
	}
	}

