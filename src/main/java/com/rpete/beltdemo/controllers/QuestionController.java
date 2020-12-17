package com.rpete.beltdemo.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rpete.beltdemo.models.Question;
import com.rpete.beltdemo.models.Tag;
import com.rpete.beltdemo.models.User;
import com.rpete.beltdemo.services.QuestionService;
import com.rpete.beltdemo.services.TagService;
import com.rpete.beltdemo.services.UserService;

@Controller
@RequestMapping("/questions")
public class QuestionController {
	
	private UserService userService;
	private QuestionService questionService;
	private TagService tagService;
	
	public QuestionController(UserService userService, QuestionService questionService, TagService tagService) {
		this.userService = userService;
		this.questionService = questionService;
		this.tagService = tagService;
	}
	
	@RequestMapping("/new")
	public String newQuestion() {
		return "new.jsp";
	}
	
	@PostMapping("")
	public String addQuestion(@RequestParam("question") String question, @RequestParam("tags") String tags, HttpSession session) {
		// get user object, form the id in session
		Long user_id = (Long)session.getAttribute("user_id");
		User logged_in_user = userService.findUserById(user_id);
		Question newQuestion = new Question(question, logged_in_user);
		questionService.addQuestion(newQuestion);
		
		// handle the tag creation
		// need to separate the tags by commas
		
		// get tags from the question
		List<Tag> questionTags = newQuestion.getTags();
		
		String[] arrOfTags = tags.split(",");
		for (String tag:arrOfTags) {
			System.out.println(tag.trim());
			// get the tag object
//			Tag trimmedTag = tag.trim();
			Tag tagInDb = tagService.findTagBySubject(tag);
			// create a new tag, and add to question tag list
			if (tagInDb == null) {
				// add to DB, then add tag to question
				Tag newTag = new Tag(tag);
				Tag savedTag = tagService.addTag(newTag);
				// add the tag to the question!
				// add saved tag into questionTags
				questionTags.add(savedTag);
			} else {
				// add tag to question that was in db
				questionTags.add(tagInDb);
			}
			newQuestion.setTags(questionTags);
		}
		// save the question and the many to many relationship to DB, after all tags were checked, outside of loop
		questionService.addQuestion(newQuestion);
		return "dashboard.jsp";
	}
}
