package com.luv.insta.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luv.insta.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	User findByUsername(String username);
}
