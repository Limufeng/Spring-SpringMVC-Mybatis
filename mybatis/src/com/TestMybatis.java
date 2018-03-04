package com;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.bean.Category;

public class TestMybatis {
	static Scanner input = new Scanner(System.in);

	public static void main(String[] args) throws IOException {
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		// 通过配置文件"mybatis-config.xml"得到SQLSessionFactory(创建SqlSession对象的工厂)
		SqlSession session = sqlSessionFactory.openSession();
		// 通过SQLSessionFactory得到Session

		addCategory(session);

		session.commit();
		session.close();

	}

	// 增加
	private static void addCategory(SqlSession session) {
		Category c = new Category();
		c.setName("addCategory");
		session.insert("addCategory", c);
		listAll(session);
	}
/*
	// 删除
	private static void deleteCategory(SqlSession session) {
		Category c = new Category();
		System.out.println("请输入你要删除的对象序号:");
		int i = input.nextInt();
		c.setId(i);
		session.delete("deleteCategory", c);
	}

	// 查询
	private static void getCategory(SqlSession session) {
		Category c = new Category();
		System.out.println("请输入查询对象序号:");
		int i = input.nextInt();
		c = session.selectOne("getCategory", i);
		System.out.println(c.getName());
	}

	// 更改
	private static void updateCategory(SqlSession session) {
		Category c = new Category();
		System.out.println("请输入修改对象序号:");
		int i = input.nextInt();
		c = session.selectOne("getCategory", i);
		c.setName("修改了姓名");
		session.update("updateCategory", c);
	}

	// 多条件查询
	private static void listCategoryByIdAndName(SqlSession session) {
		Map<String, Object> params = new HashMap();
		params.put("id", 0);
		params.put("name", "cat");
		List<Category> cs = session.selectList("listCategoryByIdAndName", params);
		for (Category category : cs) {
			System.out.println(category.getName());
		}
	}

	// 模糊查询
	private static void listCategoryByName(SqlSession session) {
		List<Category> cs = session.selectList("listCategoryByName", "cat");
		for (Category category : cs) {
			System.out.println(category.getName());
		}

	}
*/
	// 遍历
	private static void listAll(SqlSession session) {
		// TODO Auto-generated method stub
		List<Category> cs = session.selectList("listCategory");
		// 通过session的selectList方法，调用sql语句listCategory。
		for (Category c : cs) {
			System.out.println(c.getName());
		}

	}

}
