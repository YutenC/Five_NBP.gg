package com.followproduct.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "FOLLOW_LIST")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FollowList {
	
	@EmbeddedId
	private PKFollowList pkFollowList;
}
