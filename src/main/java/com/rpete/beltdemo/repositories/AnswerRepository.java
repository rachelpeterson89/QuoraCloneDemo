package com.rpete.beltdemo.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.rpete.beltdemo.models.Answer;

public interface AnswerRepository extends CrudRepository<Answer, Long> {
	List<Answer> findAll();
}
