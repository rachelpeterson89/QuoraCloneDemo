package com.rpete.beltdemo.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.rpete.beltdemo.models.Tag;

public interface TagRepository extends CrudRepository<Tag, Long> {
	
	List<Tag> findAll();
	
	Tag findBySubject(String subject);
}
