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
	private Owner john;
	private Pet garfield;
	private Pet odie;

	@Before
	public void setup(){
		john = new Owner();

		garfield = new Pet();
		garfield.setName("garfield");

		odie = new Pet();
		odie.setName("odie");

	}

	public PetServiceTest(String name, Integer petId){
		Pet pet = new Pet();
		pet.setName(name);
		pet.setId(petId);
		pet.setName(name);
		Owner owner = new Owner();
		petService.savePet(pet, owner);
		expected = pet;
	}

	@Parameters
	public static Collection<Object[]> parameters(){return Arrays.asList(new Object[][]{{"garfield", 1}, {"odie", 2}});
	}
	@Test public void findPetTest(){
		assertEquals(expected, petService.findPet(expected.getId()));
	}

}
