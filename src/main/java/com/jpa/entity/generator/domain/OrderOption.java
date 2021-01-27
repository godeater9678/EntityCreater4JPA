package com.jpa.entity.generator.domain;


import lombok.Data;

import javax.persistence.*;

/**
 자동으로 생성된 Entity 파일입니다.
**/
@Data
@Table(name="order_option, schema = homework")
public class OrderOption {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private int id;

	@Column(name="order_id")
	private int orderId;

	@Column(name="option_id")
	private int optionId;

	@Column(name="option_name")
	private String optionName;

	@Column(name="unit_price")
	private java.math.BigDecimal unitPrice;

	@Column(name="qty")
	private int qty;

	@Column(name="amount")
	private java.math.BigDecimal amount;

	@Column(name="created_at")
	private java.sql.Date createdAt;


}
