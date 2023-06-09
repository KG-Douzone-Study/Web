package com.rio.base.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.rio.base.domain.TodoVO;
import com.rio.base.dto.TodoDTO;
import com.rio.base.mapper.TodoMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService{

	// TodoService 인터페이스를 구현하는 TodoServiceImpl 에는 의존성 주입을 이용해서
	// 데이터베이스 처리를 하는 TodoMapper 와 DTO, VO 의 변환을 처리하는 ModelMapper 를 주입한다.
	
	private final TodoMapper todoMapper;
	
	private final ModelMapper modelMapper;

	@Override
	public void register(TodoDTO todoDTO) {
		// TODO Auto-generated method stub
		
		log.info(modelMapper);
		TodoVO todoVO = modelMapper.map(todoDTO, TodoVO.class);
		log.info(todoVO);
		todoMapper.insert(todoVO);
		
		// TodoServiceImpl 의존성 주입이 사용되는 방식을 눈여겨 볼 필요가 있다.
		// 의존성 주입이 필요한 객체의 타입을 final로 고정하고 @ReqiredArgsConstructor를 이용해서 생성자를 생성하는 방식을 사용한다.
		// register()에서는 주입된 ModelMapper 를 이용해서 TodoDTO를 TodoVO로 변환하고 이를 TodoMapper를 통해서 insert 처리하게 된다.
		// service 패키지는 root-context.xml 에서 component-scan 패키지로 추가해준다.
		
	}

	@Override
	public List<TodoDTO> getAll() {
		// TODO Auto-generated method stub
		List<TodoDTO> dtoList = todoMapper.selectAll().stream()
				.map(vo -> modelMapper.map(vo, TodoDTO.class))
				.collect(Collectors.toList());
		
		return dtoList;
	}

	@Override
	public TodoDTO getOne(Long tno) {
		// TODO Auto-generated method stub
		TodoVO todoVO = todoMapper.selectOne(tno);
		
		TodoDTO todoDTO = modelMapper.map(todoVO, TodoDTO.class);
		
		return todoDTO;
	}

	@Override
	public void remove(Long tno) {
		// TODO Auto-generated method stub
		
		todoMapper.delete(tno);
		
	}

	@Override
	public void modify(TodoDTO todoDTO) {
		// TODO Auto-generated method stub
		
		TodoVO todoVO = modelMapper.map(todoDTO, TodoVO.class);
		
		todoMapper.update(todoVO);
		
	}
	
	
}
