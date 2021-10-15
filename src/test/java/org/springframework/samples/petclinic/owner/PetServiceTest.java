package org.springframework.samples.petclinic.owner;

import org.hibernate.annotations.Parameter;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.springframework.samples.petclinic.utility.SimpleDI;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.junit.Assume.*;

import static org.junit.Assume.assumeFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(Parameterized.class)
public class PetServiceTest {

	private Pet expected;
	private final PetService petService;
	private static Pet garfield;
	private static Pet odie;
	private static Pet tom;
	private static Pet jeri;
	private int expectedId;

	public PetServiceTest(Pet pet, int id){
		petService = mock(PetService.class);
		expected = pet;
		expectedId = id;
	}

	@Parameters
	public static Collection<Object[]> parameters(){

		garfield = new Pet();
		garfield.setName("garfield");
		garfield.setId(1);

		odie = new Pet();
		odie.setName("odie");
		odie.setId(2);

		tom = new Pet();
		tom.setName("tom");
		tom.setId(3);

		jeri = new Pet();
		jeri.setName("jeri");
		jeri.setId(4);

		return Arrays.asList(new Object[][]{{garfield, 1}, {odie, 2}, {tom, 3}, {jeri, 4}});
	}
	@Test
	public void findPetTest(){
		when(petService.findPet(1)).thenReturn(garfield);
		when(petService.findPet(2)).thenReturn(odie);
		when(petService.findPet(3)).thenReturn(tom);
		when(petService.findPet(4)).thenReturn(jeri);
		assumeTrue(expected != null);
		assumeTrue(expectedId >= 0);
		assertEquals(expected, petService.findPet(expectedId));
	}

}
