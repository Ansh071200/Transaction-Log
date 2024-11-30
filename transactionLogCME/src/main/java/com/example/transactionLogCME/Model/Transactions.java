package com.example.transactionLogCME.Model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Transactions")
public class Transactions {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int id;

   // model data {will be mapped as columns <-- hibernate}
   private String prodName;
   private int transactionValue;
   private String description;
   private String transactionType;
   private LocalDateTime timeStamp;

   // below methods are lombok generation sets of setters, getters and
   // constructors.
   public int getId() {
      return this.id;
   }

   public String getProdName() {
      return this.prodName;
   }

   public int getTransactionValue() {
      return this.transactionValue;
   }

   public String getDescription() {
      return this.description;
   }

   public String getTransactionType() {
      return this.transactionType;
   }

   public LocalDateTime getTimeStamp() {
      return this.timeStamp;
   }

   public void setId(final int id) {
      this.id = id;
   }

   public void setProdName(final String prodName) {
      this.prodName = prodName;
   }

   public void setTransactionValue(final int transactionValue) {
      this.transactionValue = transactionValue;
   }

   public void setDescription(final String description) {
      this.description = description;
   }

   public void setTransactionType(final String transactionType) {
      this.transactionType = transactionType;
   }

   public void setTimeStamp(final LocalDateTime timeStamp) {
      this.timeStamp = timeStamp;
   }

   @Override
   public String toString() {
      int var10000 = this.getId();
      return "Transactions(id=" + var10000 + ", prodName=" + this.getProdName() + ", transactionValue="
            + this.getTransactionValue() + ", description=" + this.getDescription() + ", transactionType="
            + this.getTransactionType() + ", timeStamp=" + String.valueOf(this.getTimeStamp()) + ")";
   }

   public Transactions(final int id, final String prodName, final int transactionValue, final String description,
         final String transactionType, final LocalDateTime timeStamp) {
      this.id = id;
      this.prodName = prodName;
      this.transactionValue = transactionValue;
      this.description = description;
      this.transactionType = transactionType;
      this.timeStamp = timeStamp;
   }

   public Transactions() {
   }
}
