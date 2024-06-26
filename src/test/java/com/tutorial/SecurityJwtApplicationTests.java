package com.tutorial;

import java.util.Random;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class SecurityJwtApplicationTests {

	@Test
	void contextLoads() {
		String test = "";
		Random r = new Random();
		if(r.nextInt() < -1){
			test = null;
		}
		assertNotNull(test);  // JUnit assertion
		System.out.println("hello world");
	}
}
