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
		// xml方式
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

		// 注解方式
		// add(mapper);
		// delete(mapper);
		// get(mapper);
		// update(mapper);
		// listAll(mapper);
		// getProductList(mapper2);
		// listOrderzhujie(session);
		// 动态sql
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
		c.setName("注解方式，第三次修改");
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
		Category c = new Category("新增加的Category");
		mapper.add2(c);
		listAll2(mapper);
	}

	private static void listAll2(CategoryMapper mapper) {
		List<Category> cs = mapper.list2();
		for (Category category : cs) {
			System.out.println(category.getName());
		}
	}

	// 注解方式多对多
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

	// 注解方式多对一
	private static void getProductList(ProductMapper mapper) {
		List<Product> ps = mapper.list();
		for (Product product : ps) {
			System.out.println(product + "\t对应分类\t" + product.getCategory().getName());
		}
	}

	// 注解方式修改
	private static void update(CategoryMapper mapper) {
		Category c = mapper.get(2);
		c.setName("注解方式，第二次修改");
		mapper.update(c);
	}

	// 注解方式查询(修改)
	private static void get(CategoryMapper mapper) {
		Category c = mapper.get(2);
		System.out.println(c.getName());
	}

	// 注解方式删除
	private static void delete(CategoryMapper mapper) {
		mapper.delete(10);
	}

	// 注解方式添加
	private static void add(CategoryMapper mapper) {
		Category c = new Category("新增加的Category");
		mapper.add(c);
	}

	// 注解方式查询Category(修改-一对多)
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

	// bind标签
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

	// 修改Product
	private static void updateProductById(SqlSession session) {
		Product p = new Product(5, "product zz", 9.99f);
		session.update("updateProduct", p);
	}

	// 多条件查询
	private static void listProductByNameAndPrice(SqlSession session) {
		Map<String, Object> params = new HashMap<>();
		params.put("name", "b");// 这时params值为不为空都可以正常运行
		params.put("price", "2");
		List<Product> ps = session.selectList("listProductByName", params);
		for (Product product : ps) {
			System.out.println(product);
		}
	}

	// 查询Product使用模糊查询
	private static void listProductByName(SqlSession session) {
		Map<String, Object> params = new HashMap<>();
		params.put("name", "b");
		List<Product> ps = session.selectList("listProductByName", params);
		for (Product product : ps) {
			System.out.println(product);
		}

	}

	// 一对多关系
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

	// 多对一关系
	public static void listProduct(SqlSession session) {
		List<Product> ps = session.selectList("listProduct");
		for (Product product : ps) {
			System.out.println(product + "\t对应的分类是\t" + product.getCategory());
		}
	}

	// 多对多关系
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

	// 建立关系(插入数据)
	// 首先通过id分别获取Order和Product对象，接着就新建一个OrderItem对象，set order和product
	// 还有数量，最后用"addOrderItem"对应的sql语句插入.
	private static void addOrderItem(SqlSession session) {
		Order order = session.selectOne("getOrder", 1);
		Product product = session.selectOne("getProduct", 2);
		OrderItem orderItem = new OrderItem();
		orderItem.setProduct(product);
		orderItem.setOrder(order);
		orderItem.setNumber(200);
		session.insert("addOrderItem", orderItem);
	}

	// 删除关系(就是删掉OrderItem记录)
	// 同上通过对应的Order和Product的id进行删除
	private static void deleteOrderItem(SqlSession session) {
		Order order = session.selectOne("getOrder", 1);
		Product product = session.selectOne("getProduct", 2);
		OrderItem orderItem = new OrderItem();
		orderItem.setProduct(product);
		orderItem.setOrder(order);
		session.delete("deleteOrderItem", orderItem);

	}
	// 多对多不存在修改关系的做法，就是删除旧的，然后新增一条即达到修改的效果。
}
