package org.springframework.samples.petclinic.owner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OwnerTest {

	private Owner john;
	private Pet garfield;
	private Pet odie;

	@BeforeEach
	void setup() {
		john = new Owner();

		garfield = new Pet();
		garfield.setName("garfield");

		odie = new Pet();
		odie.setName("odie");
		odie.isNew();
	}

	@Test
	void testGetSetAddress() {
		String address = "Amirabad, University of Tehran, ECE College";
		john.setAddress(address);
		assertEquals(john.getAddress(), address);
	}

	@Test
	void testGetSetCity() {
		String city = "Tehran";
		john.setCity(city);
		assertEquals(john.getCity(), city);
	}

	@Test
	void testGetSetTelephone() {
		String telephone = "02161114215";
		john.setTelephone(telephone);
		assertEquals(john.getTelephone(), telephone);
	}

	@Test
	void testAddGetRemovePet() {
		john.addPet(garfield);
		john.addPet(odie);
		assertEquals(john.getPet("garfield"), garfield);
		assertNull(john.getPet("garfield", true));

		john.removePet(garfield);
		assertNull(john.getPet("garfield"));
	}

	@Test
	void testGetPets() {
		john.addPet(garfield);
		john.addPet(odie);
		List<Pet> expected = Arrays.asList(odie, garfield);
		List<Pet> result = john.getPets();
		assertTrue(result.containsAll(expected) && expected.containsAll(result) && expected.size() == result.size());
	}

}
