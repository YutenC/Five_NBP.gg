package com.core.act.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Act_list")
public class ActList {
   @Id
   @Column(name = "Act_id")
   private int actId;

   @Column(name = "Member_id")
   private int memberId;

   @Column(name = "GiveBack_value")
   private String giveBackValue;

   public ActList() {
   }

   public ActList(int actId, int memberId, String giveBackValue) {
      this.actId = actId;
      this.memberId = memberId;
      this.giveBackValue = giveBackValue;
   }

   public int getActId() {
      return actId;
   }

   public void setActId(int actId) {
      this.actId = actId;
   }

   public int getMemberId() {
      return memberId;
   }

   public void setMemberId(int memberId) {
      this.memberId = memberId;
   }

   public String getGiveBackValue() {
      return giveBackValue;
   }

   public void setGiveBackValue(String giveBackValue) {
      this.giveBackValue = giveBackValue;
   }
}
