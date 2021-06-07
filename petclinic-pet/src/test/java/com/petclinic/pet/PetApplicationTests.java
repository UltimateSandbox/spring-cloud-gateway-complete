package com.petclinic.pet;

import com.petclinic.pet.models.Pet;
import com.petclinic.pet.models.PetType;
import com.petclinic.pet.models.PetWithOwner;
import com.petclinic.pet.service.PetAddedMQService;
import org.joda.time.DateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.Date;

@SpringBootTest
class PetApplicationTests {

	@Autowired
	PetAddedMQService petAddedMQService;

	@Test
	public void sendVisitReminder_Test(){
		//arrange
		Pet pet = Pet.builder().withName("Gadget").withOwnerId(1L).withBirthDate(new Date()).withPetType(PetType.DOG).build();
		//act
		boolean result = petAddedMQService.sendPetAddition(pet);
		//assert
		Assert.isTrue(result, "successful");
	}

}
