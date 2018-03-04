package com;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bean.People;
import com.mapper.PeopleMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class TestMybatis3 {
	// Spring和Mybatis整合 Spring注解方式测试

	@Autowired
	public PeopleMapper mapper;

	@Test
	public void testAdd() {
		People people = new People();
		people.setName("new People");
		mapper.add(people);
	}

	@Test
	public void testList() {
		System.out.println(mapper);
		List<People> ps=mapper.list();
		for (People people : ps) {
			System.out.println(people.getName());
		}
	}

}
