package com.luv.insta.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.luv.insta.model.Image;
import com.luv.insta.model.User;
import com.luv.insta.repository.ImageRepository;
import com.luv.insta.repository.UserRepository;

@Controller
public class TestController {
	
	@Autowired
	private UserRepository mUserRepository;
	
	@Autowired
	private ImageRepository mImageRepository;
	
	//sort = id,desc
	//sort = caption, asc
	//size = 1
	//page = 3 (페이지는 시작 번호가 0)
	@GetMapping("/test/image/feed")
	public @ResponseBody List<Image> testImageFeed(@PageableDefault(size=2, sort="id", direction = Sort.Direction.DESC) Pageable pageable){
		int userId=1;
		Page<Image> images = mImageRepository.findImage(userId, pageable);
		
		return images.getContent();
	}
	
	@GetMapping("/test/user/{id}")
	public @ResponseBody User testUser(@PathVariable int id) {
		User user = mUserRepository.findById(id).get();
		return user;
	}

	@GetMapping("/test/home")
	public String testHome() {
		return "home";
	}
	
	@GetMapping("/test/login")
	public String testLogin() {
		return "auth/login";
	}
	
	@GetMapping("/test/join")
	public String testjoin() {
		return "auth/join";
	}
	
	@GetMapping("/test/profile")
	public String testProfile() {
		return "user/profile";
	}
	
	@GetMapping("/test/profileEdit")
	public String testProfileEdit() {
		return "user/profile_edit";
	}
	
	@GetMapping("/test/feed")
	public String testFeed() {
		return "image/feed";
	}
	
	@GetMapping("/test/imageUpload")
	public String imageUpload() {
		return "image/image_upload";
	}
	

	
}
