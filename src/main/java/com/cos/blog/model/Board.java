package com.cos.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	//auto-incremnent
	private int id;
	
	@Column(nullable = false, length = 100)
	private String title;
	
	@Lob // 대용량 데이터 타입
	private String content; // 섬머노트 라이브러리 사용 -> html 태그가 저장됨
	
	private int count;
	
	@ManyToOne(fetch = FetchType.EAGER)	// Many = Board, User = One => 한명의 사용자가 여러개의 게시글을 쓸 수 있다.
	@JoinColumn(name = "userId")
	private User user;	// DB는 오브젝트를 저장할 수 없다. FK 사용, 자바는 오브젝트를 저장 할 수 있다.
	
	@CreationTimestamp
	private Timestamp createDate;  
	
	@OneToMany(mappedBy = "board", fetch = FetchType.EAGER)	// mappedBy 연관관계의 주인이 아니다! (난FK가 아님) DB에 컬럼을 만들지 마세요.	// 대상 클래스의 변수명을 mappedBy로 지정	// fetch = FetchType.EAGER 즉시로딩, fetch = FetchType.LAZY 레이지로딩
	private List<Reply> reply;
}
