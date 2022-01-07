package com.mercadolibre.desafiotesting;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
class DesafioTestingApplicationTests {

	@InjectMocks
	DesafioTestingApplication application;

	@Test
	void contextLoads(){
		application.main(new String[] {});
	}

}
