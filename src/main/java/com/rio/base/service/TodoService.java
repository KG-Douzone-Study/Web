package com.rio.base.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.rio.base.dto.TodoDTO;

public interface TodoService {
	
	void register(TodoDTO todoDTO);
	
	List<TodoDTO> getAll();
	
	TodoDTO getOne(Long tno);
	
	void remove(Long tno);
	
	void modify(TodoDTO todoDTO);
	
	
	// Postman Test, React Test
	ResponseEntity<?> getAllJson();

}
