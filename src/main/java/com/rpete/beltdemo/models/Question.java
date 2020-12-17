package com.rpete.beltdemo.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;


@Entity
@Table(name="questions")
public class Question {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String question;
	
	@OneToMany(mappedBy="answer", fetch=FetchType.LAZY)
	private List<Answer> answers;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User user;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
		name="question_tags",
		joinColumns=@JoinColumn(name="question_id"),
		inverseJoinColumns=@JoinColumn(name="tag_id")
	)
	private List<Tag> tags;
	
	@Column(updatable=false)
	private Date createdAt;
	private Date updatedAt;
	
	public Question() {}
	
	public Question(String question, User user) {
		this.question = question;
		this.user = user;
		this.tags = new ArrayList();
	}
	
	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getQuestion() {
		return question;
	}



	public void setQuestion(String question) {
		this.question = question;
	}



	public List<Answer> getAnswers() {
		return answers;
	}



	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}



	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}



	public List<Tag> getTags() {
		return tags;
	}



	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}



	public Date getCreatedAt() {
		return createdAt;
	}



	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}



	public Date getUpdatedAt() {
		return updatedAt;
	}



	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}



	@PrePersist
    protected void onCreate() {
    	this.createdAt = new Date();
    }
    
    @PreUpdate
    protected void onUpdate() {
    	this.updatedAt = new Date();
    }
	
}
