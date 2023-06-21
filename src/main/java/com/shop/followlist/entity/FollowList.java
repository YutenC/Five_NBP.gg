package com.shop.followlist.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "FOLLOW_LIST")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FollowList implements java.io.Serializable{
	
	private static final long serialVersionUID = -2062002220867267220L;

	@EmbeddedId
	private PKFollowList pkFollowList;
	
}
