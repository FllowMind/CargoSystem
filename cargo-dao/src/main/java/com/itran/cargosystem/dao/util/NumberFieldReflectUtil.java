package com.itran.cargosystem.dao.util;

import java.lang.reflect.Field;

import org.apache.ibatis.reflection.ReflectionException;
import org.springframework.util.ReflectionUtils;

/**
 * 数值字段处理
 * 
 * @author svili
 * @date 2017年2月18日
 *
 */
public class NumberFieldReflectUtil {

	/**
	 * 设置Date类型字段值
	 * 
	 * @param target
	 *            the target object from which to get the field
	 * @param field
	 *            the field to set
	 * @param number
	 *            java.lang.Number
	 * @throws Exception
	 *             IllegalArgumentException, IllegalAccess
	 */
	public static void setFieldNumberValue(Object target, Field field, Object value) throws Exception {

		if (!Number.class.isAssignableFrom(field.getType())) {
			throw new ReflectionException(target.getClass().getName() + "." + field.getName()
					+ " : field type is not Number, can not convertToNumber");
		}

		if (!field.isAccessible()) {
			ReflectionUtils.makeAccessible(field);
		}
		
		if(value == null){
			field.set(target, null);
			return;
		}
		
		if(!(value instanceof Number)){
			throw new ReflectionException(value + " : is not Number type value , can not convertToNumber to field "
					+ target.getClass().getName() + "." + field.getName());
		}
		
		Number number = (Number)value;
		
		if (field.getType().equals(Byte.class)) {
			field.set(target, NumberUtil.toByte(number));
			return;
		}
		if (field.getType().equals(Double.class)) {
			field.set(target, NumberUtil.toDouble(number));
			return;
		}
		if (field.getType().equals(Float.class)) {
			field.set(target, NumberUtil.toFloat(number));
			return;
		}
		if (field.getType().equals(Integer.class)) {
			field.set(target, NumberUtil.toInt(number));
			return;
		}
		if (field.getType().equals(Long.class)) {
			field.set(target, NumberUtil.toLong(number));
			return;
		}
		if (field.getType().equals(Short.class)) {
			field.set(target, NumberUtil.toShort(number));
			return;
		}
		if (field.getType().equals(java.math.BigDecimal.class)) {
			field.set(target, NumberUtil.toBigDecimal(number));
			return;
		}

	}
}
