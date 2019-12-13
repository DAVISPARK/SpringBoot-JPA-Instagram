package com.luv.insta.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luv.insta.model.Tag;

public interface TagRepository extends JpaRepository<Tag, Integer> {

}
