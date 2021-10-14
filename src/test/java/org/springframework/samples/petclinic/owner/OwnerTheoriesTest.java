package org.springframework.samples.petclinic.owner;

import org.junit.Before;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(Theories.class)
public class OwnerTheoriesTest {
	private Owner owner;
	private Pet pet;

	@Before
	public void setup(){
		this.owner = new Owner();
		this.pet = new Pet();
	}

	@DataPoints
	public static String[] names = {"John", "Alex"};

	@DataPoints
	public static String[] petNames = {"garfield", "odie"};

	@Theory public void getPetTest(String name, String petName){
		this.owner.setFirstName(name);
		this.pet.setName(petName);
		this.owner.addPet(this.pet);
		Pet expected = this.owner.getPet(petName);
		assertEquals(expected, this.pet);
	}

}
