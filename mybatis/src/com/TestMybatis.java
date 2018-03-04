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
		// ͨ�������ļ�"mybatis-config.xml"�õ�SQLSessionFactory(����SqlSession����Ĺ���)
		SqlSession session = sqlSessionFactory.openSession();
		// ͨ��SQLSessionFactory�õ�Session

		addCategory(session);

		session.commit();
		session.close();

	}

	// ����
	private static void addCategory(SqlSession session) {
		Category c = new Category();
		c.setName("addCategory");
		session.insert("addCategory", c);
		listAll(session);
	}
/*
	// ɾ��
	private static void deleteCategory(SqlSession session) {
		Category c = new Category();
		System.out.println("��������Ҫɾ���Ķ������:");
		int i = input.nextInt();
		c.setId(i);
		session.delete("deleteCategory", c);
	}

	// ��ѯ
	private static void getCategory(SqlSession session) {
		Category c = new Category();
		System.out.println("�������ѯ�������:");
		int i = input.nextInt();
		c = session.selectOne("getCategory", i);
		System.out.println(c.getName());
	}

	// ����
	private static void updateCategory(SqlSession session) {
		Category c = new Category();
		System.out.println("�������޸Ķ������:");
		int i = input.nextInt();
		c = session.selectOne("getCategory", i);
		c.setName("�޸�������");
		session.update("updateCategory", c);
	}

	// ��������ѯ
	private static void listCategoryByIdAndName(SqlSession session) {
		Map<String, Object> params = new HashMap();
		params.put("id", 0);
		params.put("name", "cat");
		List<Category> cs = session.selectList("listCategoryByIdAndName", params);
		for (Category category : cs) {
			System.out.println(category.getName());
		}
	}

	// ģ����ѯ
	private static void listCategoryByName(SqlSession session) {
		List<Category> cs = session.selectList("listCategoryByName", "cat");
		for (Category category : cs) {
			System.out.println(category.getName());
		}

	}
*/
	// ����
	private static void listAll(SqlSession session) {
		// TODO Auto-generated method stub
		List<Category> cs = session.selectList("listCategory");
		// ͨ��session��selectList����������sql���listCategory��
		for (Category c : cs) {
			System.out.println(c.getName());
		}

	}

}
