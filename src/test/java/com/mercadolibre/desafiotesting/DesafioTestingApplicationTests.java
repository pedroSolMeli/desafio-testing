package com.mercadolibre.desafiotesting;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DesafioTestingApplicationTests {

	@InjectMocks
	DesafioTestingApplication application;

	@Test
	void contextLoads(){
		application.main(new String[] {});
	}

}
