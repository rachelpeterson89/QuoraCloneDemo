package com.rpete.beltdemo.services;


import org.springframework.stereotype.Service;

import com.rpete.beltdemo.models.Question;
import com.rpete.beltdemo.repositories.QuestionRepository;

@Service
public class QuestionService {
	
	private final QuestionRepository questionRepo;
	
	public QuestionService(QuestionRepository questionRepo) {
		this.questionRepo = questionRepo;
	}
	
	public Question addQuestion(Question q) {
		return questionRepo.save(q);
	}
}
