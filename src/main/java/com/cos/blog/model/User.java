package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity	// User 클래스가 MySql에 자동으로 테이블이 생성 된다.
//@DynamicInsert	// 값이 null인 컬럼은 제외하고 insert
public class User {
	
	@Id // Primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)	// 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
	private int id;	// 시퀀스, auto-increment
	
	@Column(nullable = false, length = 30, unique = true)
	private String username; // 아이디
	
	@Column(nullable = false, length = 100)	// 해쉬 (비밀번호암호화)
	private String password;
	
	@Column(nullable = false, length = 50)
	private String email;
	 
//	@ColumnDefault("USER")
	@Enumerated(EnumType.STRING)	// DB는 RoleType이라는게 없다.
	private RoleType role;	// Enum을 쓰는게 좋다.	
	
	@CreationTimestamp	// 시간 자동 입력 (NOW(), GETDATE())
	private Timestamp createDate;
	
	
}
