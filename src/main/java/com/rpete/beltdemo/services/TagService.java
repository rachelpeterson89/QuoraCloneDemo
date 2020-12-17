package com.rpete.beltdemo.services;

import org.springframework.stereotype.Service;

import com.rpete.beltdemo.models.Tag;
import com.rpete.beltdemo.repositories.TagRepository;

@Service
public class TagService {
	
	private final TagRepository tagRepo;
	
	public TagService(TagRepository tagRepo) {
		this.tagRepo = tagRepo;
	}
	
	public Tag findTagBySubject(String subject) {
		return tagRepo.findBySubject(subject);
	}
	
	public Tag addTag(Tag t) {
		return tagRepo.save(t);
	}
}
