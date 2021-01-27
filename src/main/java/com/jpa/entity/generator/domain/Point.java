package com.jpa.entity.generator.domain;


import lombok.Data;

import javax.persistence.*;

/**
 자동으로 생성된 Entity 파일입니다.
**/
@Data
@Table(name="point, schema = homework")
public class Point {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private int id;

	@Column(name="member_id")
	private int memberId;

	@Column(name="point")
	private int point;

	@Column(name="reason")
	private String reason;

	@Column(name="used_point")
	private String usedPoint;

	@Column(name="expired_at")
	private java.sql.Date expiredAt;

	@Column(name="created_at")
	private java.sql.Date createdAt;


}
