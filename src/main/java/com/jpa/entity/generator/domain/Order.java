package com.jpa.entity.generator.domain;


import lombok.Data;

import javax.persistence.*;

/**
 자동으로 생성된 Entity 파일입니다.
**/
@Data
@Table(name="order, schema = homework")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private int id;

	@Column(name="member_id")
	private int memberId;

	@Column(name="title")
	private String title;

	@Column(name="created_at")
	private java.sql.Date createdAt;


}
