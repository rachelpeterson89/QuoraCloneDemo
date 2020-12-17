package com.rpete.beltdemo.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.rpete.beltdemo.models.Question;

public interface QuestionRepository extends CrudRepository<Question, Long> {
	List<Question> findAll();
}
