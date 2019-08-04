package com.kaizensoftware.users;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class UsersApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsersApplication.class, args);
	}

//	@Data
//	@RequiredArgsConstructor
//	class Customer {
//		private final String name;
//		private final String cellPhone;
//		private final String address;
//	}

//	public void test1() {
//
//		test(new String[] {"Param", "Param", "Param", "Param"});
//
//		List<String> params = new ArrayList<>();
//		params.add("Param1");
//		params.add("Param2");
//		params.add("Param3");
//
//		test("asdasdasd", "asdasd", "asdasdasd");
//		//test(Arrays.asList("Param", "Param", "Param", "Param"));
//
//	}

//	public void test(String... param) {

//		String[] clone = param.clone();
//
//		String s = param[0];
//		String d = param[1];

//		Customer algo = Customer.builder()
//				.name("Algo")
//				.cellPhone("818915123")
//				.address("Calle 1")
//				.build();
//
//		Customer algo = new Customer("aasd", "81565", "Calle 1");
//	}
//

}
