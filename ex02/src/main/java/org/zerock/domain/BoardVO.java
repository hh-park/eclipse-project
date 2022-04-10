package org.zerock.domain;

import java.util.Date;

import lombok.Data;

// Lombok을 이용해 생성자와 getter/setter, toString()을 만들어냄 
@Data
public class BoardVO {

	private Long bno;
	private String title;
	private String content;
	private String writer;
	private Date regdate;
	private Date updateDate;
}
