package org.springframework.samples.petclinic.owner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.springframework.samples.petclinic.utility.PetTimedCache;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class FindPetTest {


	private static Logger logger;
	private static PetTimedCache pets;
	private static OwnerRepository owners;

	@BeforeEach
	public void setup() {
		pets = mock(PetTimedCache.class);  // dummy object
		owners = mock(OwnerRepository.class);  // dummy object
		logger = mock(Logger.class);  // dummy object
	}

	@Test
	public void findPet_FindSuccessfully_PetExists_StateVerification() {
		Pet pet = new Pet();

		PetTimedCache pets = mock(PetTimedCache.class);
		when(pets.get(1)).thenReturn(pet);
		PetManager petManager = new PetManager(pets, owners, logger);

		assertEquals(petManager.findPet(1), pet);
	}

	@Test
	public void findPet_FailToFind_PetNotExists_StateVerification() {
		Pet pet = new Pet();

		PetTimedCache pets = mock(PetTimedCache.class);
		when(pets.get(1)).thenReturn(pet);
		PetManager petManager = new PetManager(pets, owners, logger);

		assertNull(petManager.findPet(2));
	}

	@Test
	public void findPet_FindSuccessfully_PetExists_BehaviorVerification() {
		Pet pet = mock(Pet.class);

		PetTimedCache pets = mock(PetTimedCache.class);
		when(pets.get(1)).thenReturn(pet);
		PetManager petManager = new PetManager(pets, owners, logger);

		assertEquals(petManager.findPet(1), pet);

		verify(pets, times(1)).get(1);
		verify(logger, times(1)).info(anyString(), anyInt());
	}

	@Test
	public void findPet_FailToFind_PetNotExists_BehaviorVerification() {
		Pet pet = mock(Pet.class);

		PetTimedCache pets = mock(PetTimedCache.class);
		when(pets.get(1)).thenReturn(pet);
		PetManager petManager = new PetManager(pets, owners, logger);


		assertNull(petManager.findPet(2));
		verify(pets, times(1)).get(2);
		verify(logger, times(1)).info(anyString(), anyInt());

	}


}
