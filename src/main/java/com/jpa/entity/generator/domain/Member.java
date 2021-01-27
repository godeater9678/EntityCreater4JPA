package com.jpa.entity.generator.domain;


import lombok.Data;

import javax.persistence.*;

/**
 자동으로 생성된 Entity 파일입니다.
**/
@Data
@Table(name="member, schema = homework")
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private int id;

	@Column(name="email")
	private String email;

	@Column(name="name")
	private String name;

	@Column(name="created_at")
	private java.sql.Date createdAt;


}
