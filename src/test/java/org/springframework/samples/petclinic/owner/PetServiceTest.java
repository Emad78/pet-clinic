package org.springframework.samples.petclinic.owner;

import org.hibernate.annotations.Parameter;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.springframework.samples.petclinic.utility.SimpleDI;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(Parameterized.class)
public class PetServiceTest {

	public Pet expected;
	public PetService petService;

	public PetServiceTest( int petId){
		String petName = "hi";
		Pet pet = new Pet();
		pet.setId(petId);
		pet.setName(petName);
		Owner owner = new Owner();
		petService.savePet(pet, owner);
		expected = pet;
	}
	@Parameterized.Parameters
	public static Collection<Object[]> parameters(){return Arrays.asList(new Object[][]{{1}, {2}});
	}
	@Test public void findPetTest(){
		assertEquals(expected, petService.findPet(expected.getId()));
	}

}
