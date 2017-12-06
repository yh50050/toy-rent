package com.annet.test;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.toy.model.Employee;

public class Main {

	public static void main(String[] args) throws JsonGenerationException, JsonMappingException, IOException {

		Employee em = new Employee();
		em.setEmployeeName("张三");
		em.setEmployeeEmail("zhangsan@163.com");
		MyObjectMapper om = new MyObjectMapper();

		em = om.readValue("{\"employee_id\":0,\"employee_name\":\"张三\",\"employee_sex\":\"\",\"employee_phone\":null,\"employee_email\":\"zhangsan@163.com\",\"employee_branch\":\"\",\"employee_code\":\"\",\"employee_address\":\"\",\"employee_on_jop\":\"\"}",Employee.class);
		System.out.println(em);
		System.out.println(em.getEmployeeId()+
				"\n"+em.getEmployeeName()+
				"\n"+em.getEmployeeAddress()+
				"\n birth:"+em.getEmployeeBirth()+
				"\n"+em.getEmployeeBranch()+
				"\n"+em.getEmployeeEmail()+
				"\n"+em.getEmployeePhone()+
				"\n"+em.getEmployeeSex());
	}

}

class MyObjectMapper extends ObjectMapper {

	private static final long serialVersionUID = 4402127997078513582L;

	public MyObjectMapper() {
		// 设置null值不参与序列化(字段不被显示)
		// this.setSerializationInclusion(Include.NON_NULL);
		// null替换为""
		this.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {
			@Override
			public void serialize(Object arg0, JsonGenerator arg1, SerializerProvider arg2)
					throws IOException, JsonProcessingException {
				arg1.writeString("");
			}
		});
		// 禁用空对象转换json校验
		this.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		this.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		// 驼峰命名法转换为小写加下划线
		this.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
	}
	

	
			@Override
			public SerializerProvider getSerializerProvider() {
				// TODO 自动生成的方法存根
				return super.getSerializerProvider();
			}
}
