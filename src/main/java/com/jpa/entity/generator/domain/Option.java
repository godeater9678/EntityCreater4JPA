package com.jpa.entity.generator.domain;


import lombok.Data;

import javax.persistence.*;

/**
 자동으로 생성된 Entity 파일입니다.
**/
@Data
@Table(name="option, schema = homework")
public class Option {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private int id;

	@Column(name="option_name")
	private String optionName;


}
