package com.petclinic.owner;

import com.petclinic.owner.services.ConsumeNewPetMQService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
class OwnerApplicationTests {

	@Autowired
	ConsumeNewPetMQService consumeNewPetMQService;

	@Test
	void consumeNewPetMQ_TEST() {
		//act
		boolean result = consumeNewPetMQService.consumeNewPetMessage();
		//assert
		Assert.isTrue(result, "Success");
	}

}
