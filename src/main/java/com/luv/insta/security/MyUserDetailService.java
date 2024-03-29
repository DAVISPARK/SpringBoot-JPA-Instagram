package com.luv.insta.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.luv.insta.model.User;
import com.luv.insta.repository.UserRepository;

@Service
public class MyUserDetailService implements UserDetailsService {
	
	@Autowired
	private UserRepository mRepo;
	
	// loginForm에서 action="user/loginProcess" 되면
	// 스프링 필터 체인이 낚어채서 loadUserByUsername 함수를 호출한다.
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = mRepo.findByUsername(username);
		MyUserDetails userDetails = null;
		if(user != null) {
			userDetails = new MyUserDetails();
			userDetails.setUser(user);
		}else {
			throw new UsernameNotFoundException("유저 네임이 없엉 : "+ username);
		}
		return userDetails;
	}
}
