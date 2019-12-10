package com.luv.insta.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Data
@Entity
public class Image {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String location;	// 사진 찍은 위치
	private String caption;	// 사진 설명
	private String postImage;	// 포스팅 사진 경로 + 이름 
	
	//fk가 아니면 mappedby을 걸어준다
	@ManyToOne
	@JoinColumn(name="userId")
	@JsonIgnoreProperties({"password"})	//무시할 속성이나 속성 목록을 표시하는 데 사용됨
	@JsonBackReference	//!#->User (순환참조 안됨.)
	private User user;	
	
	//(1) Tag List
	@OneToMany(mappedBy = "image")
	@JsonManagedReference	//!@->Tag 
	private List<Tag> tags = new ArrayList<>();
	
	//(2) Like List	// 이미지에는 좋아요를 많이 남길 수 있으니 List에 담는다
	@OneToMany(mappedBy = "image")
	private List<Likes> likes = new ArrayList<>();
	
	@Transient	//DB에 영향을 미치지 않는다.
	private int likeCount;
	
	@CreationTimestamp
	private Timestamp createDate;
	@CreationTimestamp
	private Timestamp updateDate;
	
	
	
}
