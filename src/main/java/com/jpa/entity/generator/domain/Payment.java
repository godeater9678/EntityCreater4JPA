package com.jpa.entity.generator.domain;


import lombok.Data;

import javax.persistence.*;

/**
 자동으로 생성된 Entity 파일입니다.
**/
@Data
@Table(name="payment, schema = homework")
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private int id;

	@Column(name="order_option_id")
	private int orderOptionId;

	@Column(name="payment_method")
	private String paymentMethod;

	@Column(name="price")
	private int price;

	@Column(name="created_at")
	private java.sql.Date createdAt;


}
