package com;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.bean.Category;
import com.bean.Order;
import com.bean.OrderItem;
import com.bean.Product;
import com.mapper.CategoryMapper;
import com.mapper.OrderMapper;
import com.mapper.ProductMapper;

public class TestMybatis2 {
	static String resource;
	static InputStream inputStream;
	static SqlSessionFactory sessionFactory;
	static SqlSession session;
	static CategoryMapper mapper;
	static ProductMapper mapper2;

	private static void init() throws IOException {
		resource = "mybatis-config.xml";
		inputStream = Resources.getResourceAsStream(resource);
		sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		session = sessionFactory.openSession();
		mapper = session.getMapper(CategoryMapper.class);
		mapper2 = session.getMapper(ProductMapper.class);

	}

	public static void main(String[] args) throws IOException {
		init();
		// xml��ʽ
		// listCategory(session);
		// listProduct(session);
		// addOrderItem(session);
		// deleteOrderItem(session);
		// listOrder(session);

		// listProductByName(session);
		// updateProductById(session);
		// listProductByNameAndPrice(session);
		// productList(session);
		// listProductForeach(session);
		// listProductBind(session);

		// ע�ⷽʽ
		// add(mapper);
		// delete(mapper);
		// get(mapper);
		// update(mapper);
		// listAll(mapper);
		// getProductList(mapper2);
		// listOrderzhujie(session);
		// ��̬sql
		// listAll2(mapper);
		// add2(mapper);
		// delete2(mapper);
		// get2(mapper);
		// update2(mapper);

		session.commit();
		session.close();

	}

	private static void update2(CategoryMapper mapper) {
		Category c = mapper.get2(2);
		c.setName("ע�ⷽʽ���������޸�");
		mapper.update2(c);
		get2(mapper);
	}

	private static void get2(CategoryMapper mapper) {
		Category c = mapper.get2(2);
		System.out.println(c.getName());
	}

	private static void delete2(CategoryMapper mapper) {
		mapper.delete2(15);
		listAll2(mapper);
	}

	private static void add2(CategoryMapper mapper) {
		Category c = new Category("�����ӵ�Category");
		mapper.add2(c);
		listAll2(mapper);
	}

	private static void listAll2(CategoryMapper mapper) {
		List<Category> cs = mapper.list2();
		for (Category category : cs) {
			System.out.println(category.getName());
		}
	}

	// ע�ⷽʽ��Զ�
	private static void listOrderzhujie(SqlSession session) {
		OrderMapper mapper = session.getMapper(OrderMapper.class);
		List<Order> os = mapper.list();
		for (Order order : os) {
			System.out.println(order.getCode());
			List<OrderItem> ois = order.getOrderItems();
			if (null != ois) {
				for (OrderItem orderItem : ois) {
					System.out.format("\t%s\t%f\t%d%n", orderItem.getProduct().getName(),
							orderItem.getProduct().getPrice(), orderItem.getNumber());
				}
			}
		}
	}

	// ע�ⷽʽ���һ
	private static void getProductList(ProductMapper mapper) {
		List<Product> ps = mapper.list();
		for (Product product : ps) {
			System.out.println(product + "\t��Ӧ����\t" + product.getCategory().getName());
		}
	}

	// ע�ⷽʽ�޸�
	private static void update(CategoryMapper mapper) {
		Category c = mapper.get(2);
		c.setName("ע�ⷽʽ���ڶ����޸�");
		mapper.update(c);
	}

	// ע�ⷽʽ��ѯ(�޸�)
	private static void get(CategoryMapper mapper) {
		Category c = mapper.get(2);
		System.out.println(c.getName());
	}

	// ע�ⷽʽɾ��
	private static void delete(CategoryMapper mapper) {
		mapper.delete(10);
	}

	// ע�ⷽʽ���
	private static void add(CategoryMapper mapper) {
		Category c = new Category("�����ӵ�Category");
		mapper.add(c);
	}

	// ע�ⷽʽ��ѯCategory(�޸�-һ�Զ�)
	private static void listAll(CategoryMapper mapper) {
		List<Category> cs = mapper.list();
		for (Category category : cs) {
			System.out.println(category.getName());
			List<Product> ps = category.getProducts();
			for (Product product : ps) {
				System.out.println("\t" + product.getName());
			}
		}
	}

	// bind��ǩ
	private static void listProductBind(SqlSession session) {
		Map<String, String> params = new HashMap<>();
		params.put("name", "product");
		List<Product> ps = session.selectList("listProductBind", params);
		for (Product product : ps) {
			System.out.println(product);
		}
	}

	private static void listProductForeach(SqlSession session) {
		List<Integer> ids = new ArrayList<>();
		ids.add(1);
		ids.add(2);
		ids.add(3);
		List<Product> ps = session.selectList("listProductForeach", ids);
		for (Product product : ps) {
			System.out.println(product);
		}
	}

	private static void productList(SqlSession session) {
		Map<String, Object> params = new HashMap<>();
		List<Product> ps = session.selectList("productList", params);
		for (Product product : ps) {
			System.out.println(product);
		}
	}

	// �޸�Product
	private static void updateProductById(SqlSession session) {
		Product p = new Product(5, "product zz", 9.99f);
		session.update("updateProduct", p);
	}

	// ��������ѯ
	private static void listProductByNameAndPrice(SqlSession session) {
		Map<String, Object> params = new HashMap<>();
		params.put("name", "b");// ��ʱparamsֵΪ��Ϊ�ն�������������
		params.put("price", "2");
		List<Product> ps = session.selectList("listProductByName", params);
		for (Product product : ps) {
			System.out.println(product);
		}
	}

	// ��ѯProductʹ��ģ����ѯ
	private static void listProductByName(SqlSession session) {
		Map<String, Object> params = new HashMap<>();
		params.put("name", "b");
		List<Product> ps = session.selectList("listProductByName", params);
		for (Product product : ps) {
			System.out.println(product);
		}

	}

	// һ�Զ��ϵ
	public static void listCategory(SqlSession session) {
		List<Category> cs = session.selectList("listCategory");
		for (Category category : cs) {
			System.out.println(category);
			List<Product> ps = category.getProducts();
			for (Product product : ps) {
				System.out.println("/t" + product);
			}
		}

	}

	// ���һ��ϵ
	public static void listProduct(SqlSession session) {
		List<Product> ps = session.selectList("listProduct");
		for (Product product : ps) {
			System.out.println(product + "\t��Ӧ�ķ�����\t" + product.getCategory());
		}
	}

	// ��Զ��ϵ
	private static void listOrder(SqlSession session) {
		List<Order> orders = session.selectList("listOrder");
		for (Order order : orders) {
			System.out.println(order.getCode());
			List<OrderItem> oItems = order.getOrderItems();
			for (OrderItem orderItem : oItems) {
				System.out.format("\t%s\t%f\t%d%n", orderItem.getProduct().getName(), orderItem.getProduct().getPrice(),
						orderItem.getNumber());
			}
		}
	}

	// ������ϵ(��������)
	// ����ͨ��id�ֱ��ȡOrder��Product���󣬽��ž��½�һ��OrderItem����set order��product
	// ���������������"addOrderItem"��Ӧ��sql������.
	private static void addOrderItem(SqlSession session) {
		Order order = session.selectOne("getOrder", 1);
		Product product = session.selectOne("getProduct", 2);
		OrderItem orderItem = new OrderItem();
		orderItem.setProduct(product);
		orderItem.setOrder(order);
		orderItem.setNumber(200);
		session.insert("addOrderItem", orderItem);
	}

	// ɾ����ϵ(����ɾ��OrderItem��¼)
	// ͬ��ͨ����Ӧ��Order��Product��id����ɾ��
	private static void deleteOrderItem(SqlSession session) {
		Order order = session.selectOne("getOrder", 1);
		Product product = session.selectOne("getProduct", 2);
		OrderItem orderItem = new OrderItem();
		orderItem.setProduct(product);
		orderItem.setOrder(order);
		session.delete("deleteOrderItem", orderItem);

	}
	// ��Զ಻�����޸Ĺ�ϵ������������ɾ���ɵģ�Ȼ������һ�����ﵽ�޸ĵ�Ч����
}
