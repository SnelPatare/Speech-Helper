package com.speechhelper.databasemanager;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@IdClass(SpeechID.class)
@Table(name="Speech")
public class SpeechEntity implements Serializable{
	private static final long serialVersionUID = 1L;

	  @Id
	  @GeneratedValue(strategy=GenerationType.SEQUENCE)
	  @Column(name="speech_id")  
	  private Long speechId;
	  
	  @Column(name="user_id")
	  private Long userId;
	  
	  @Column(name="date_created")
	  private String dateCreated;
	  
//	  @Column(name="speechtoTextReport")
//	  private ReportEntity speechToTextReport;

	  public SpeechEntity() {}


	 /* @Override
	  public String toString() {
	    return String.format(
	       // "Speech[speechId=%d, userId='%d', transcribedSpeechText='%s', convertedSpeechText='%s']",
	       // speechId, userId, transcribedSpeechText, convertedSpeechText );
	  }*/
	  
	  public Long getSpeechId() {
		    return speechId;
		  }
		  
	  public void setSpeechId(Long speechId) {
		  	this.speechId = speechId;
	  }
	  
	  public Long getUserId() {
		    return userId;
		  }
		  
	  public void setUserId(Long userId) {
		  	this.userId = userId;
	  }


	public String getDateCreated() {
		return dateCreated;
	}


	public void setDateCreated(String string) {
		this.dateCreated = string;
	}

	  
	  
//	  public String getTranscribedSpeechText() {
//		  return transcribedSpeechText;
//	  }
//	  
//	  public void setTranscribedSpeechText(String transcribedSpeechText) {
//		  this.transcribedSpeechText = transcribedSpeechText;
//	  }
//	
//	  public String getConvertedSpeechText() {
//		  return convertedSpeechText;
//	  }
//	  
//	  public void setConvertedSpeechText(String convertedSpeechText) {
//		  this.convertedSpeechText = convertedSpeechText;
//	  }
	  
}
