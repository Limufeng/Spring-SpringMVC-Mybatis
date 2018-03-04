package com.mapper;

import java.util.List;

import com.bean.People;

public interface PeopleMapper {
	public int add(People people);
	
	public void delete(int id);
	
	public People get(int id);
	
	public int update(People people);
	
	public List<People> list();
	
	public int count();
}
