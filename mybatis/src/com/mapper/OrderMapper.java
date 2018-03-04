package com.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.bean.Order;

public interface OrderMapper {
	@Select("select * from order_")
	@Results({
			@Result(property = "orderItems", javaType = List.class, column = "id", many = @Many(select = "com.mapper.OrderItemMapper.listByOrder")) })
	public List<Order> list();
}
