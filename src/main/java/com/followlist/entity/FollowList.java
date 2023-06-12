package com.followlist.entity;

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
public class FollowList {
	
	@EmbeddedId
	private PKFollowList pkFollowList;
	
}
