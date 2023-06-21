package com.manager.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.core.entity.Core;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table
public class Manager extends Core {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer manager_id; 	// int 			NN, PK, AI
	@Column
	private String account; 		// varchar(40) 	NN
	@Column
	private String password; 		// varchar(40) 	NN
	@Column
	private String name; 			// varchar(40) 	NN
	@Column
	private String email; 			// varchar(100)	NN
	@Column
	private String phone; 			// char(10) 	NN
	@Column
	private String address; 		// varchar(100)
	@Column 
	private Integer is_working; 	// tinyInt		NN
	
	
	
}
