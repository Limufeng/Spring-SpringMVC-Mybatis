package com.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;

import com.bean.Category;
import com.provider.CategoryDynaSqlProvider;

public interface CategoryMapper {

	@Insert("insert into category_ (name) value (#{name})")
	public int add(Category category);

	@Delete("delete from category_ where id=#{id}")
	public void delete(int id);

	// *********************多对一********************

	@Select("select * from category_ where id=#{id}")
	public Category get(int id);

	@Update("update category_ set name=#{name} where id=#{id}")
	public int update(Category category);

	/**
	 * **********************一对多***************************
	 * 先使用@Select注解获取Category类本身
	 * 在使用@Results通过@Result和@Many中调用ProductMapper.listByCategory()方法,获取一对多关系
	 * 
	 * @return
	 */

	@Select("select * from category_")
	@Results({ @Result(property = "id", column = "id"),
			@Result(property = "products", javaType = List.class, column = "id", many = @Many(select = "com.mapper.ProductMapper.listByCategory")) })
	public List<Category> list();

	// 动态sql
	@InsertProvider(type = CategoryDynaSqlProvider.class, method = "add")
	public int add2(Category category);

	@DeleteProvider(type = CategoryDynaSqlProvider.class, method = "delete")
	public void delete2(int id);

	@SelectProvider(type = CategoryDynaSqlProvider.class, method = "get")
	public Category get2(int id);

	@UpdateProvider(type = CategoryDynaSqlProvider.class, method = "update")
	public int update2(Category category);

	@SelectProvider(type = CategoryDynaSqlProvider.class, method = "list")
	public List<Category> list2();

}
