package org.springframework.samples.petclinic.owner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.springframework.samples.petclinic.utility.PetTimedCache;
import org.springframework.samples.petclinic.visit.Visit;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
class PetManagerTest {

	private static Logger logger;
	private static PetTimedCache pets;
	private static OwnerRepository owners;

	@BeforeEach
	public void setup() {
		pets = mock(PetTimedCache.class);  // dummy object
		owners = mock(OwnerRepository.class);  // dummy object
		logger = mock(Logger.class);  // dummy object
	}

	// mockisty, state verification
	@Test
	public void findOwner_FindSuccessfully_OwnerExistsInRepository() {
		Owner owner = mock(Owner.class);  // dummy object
		OwnerRepository owners = mock(OwnerRepository.class);  // test stub
		when(owners.findById(1)).thenReturn(owner);
		PetManager petManager = new PetManager(pets, owners, logger);  // SUT

		assertEquals(petManager.findOwner(1), owner);
	}

	// mockisty, state verification
	@Test
	public void findOwner_FailToFind_OwnerNotExistsInRepository() {
		Owner owner = mock(Owner.class);  // dummy object
		OwnerRepository owners = mock(OwnerRepository.class);  // test stub
		when(owners.findById(1)).thenReturn(owner);
		PetTimedCache pets = mock(PetTimedCache.class);  // dummy object
		PetManager petManager = new PetManager(pets, owners, logger);  // SUT

		assertNull(petManager.findOwner(2));
	}

	// mockisty, behavior verification
	@Test
	public void newPet_Success_GivenOwner() {
		Owner owner = spy(Owner.class);  // test spy
		PetManager petManager = new PetManager(pets, owners, logger);  // SUT

		petManager.newPet(owner);

		verify(owner, times(1)).addPet(any(Pet.class));
	}

	// mockisty, state verification
	@Test
	public void findPet_FindSuccessfully_PetExists() {
		Pet pet = mock(Pet.class);  // dummy object
		PetTimedCache pets = mock(PetTimedCache.class);  // test stub
		when(pets.get(1)).thenReturn(pet);
		PetManager petManager = new PetManager(pets, owners, logger);  // SUT

		assertEquals(petManager.findPet(1), pet);
	}

	// mockisty, state verification
	@Test
	public void findPet_FailToFind_PetNotExists() {
		Pet pet = mock(Pet.class);  // dummy object
		PetTimedCache pets = mock(PetTimedCache.class);  // test stub
		when(pets.get(1)).thenReturn(pet);
		PetManager petManager = new PetManager(pets, owners, logger);  // SUT

		assertNull(petManager.findPet(2));
	}

	// mockisty, behavior verification
	@Test
	public void savePet_Success_GivenPetAndOwner() {
		Pet pet = mock(Pet.class);  // dummy object
		Owner owner = spy(Owner.class);  // test spy
		PetTimedCache pets = mock(PetTimedCache.class);  // test spy
		PetManager petManager = new PetManager(pets, owners, logger);  // SUT

		petManager.savePet(pet, owner);

		verify(owner, times(1)).addPet(pet);
		verify(pets, times(1)).save(pet);
	}

	// mockisty, state verification
	@Test
	public void getOwnerPets_Success_OwnerHasPets() {
		List<Pet> ownerPets = Arrays.asList(mock(Pet.class), mock(Pet.class));  // dummy objects
		Owner owner = mock(Owner.class);  // test stub
		when(owner.getPets()).thenReturn(ownerPets);
		OwnerRepository owners = mock(OwnerRepository.class);  // test stub
		when(owners.findById(1)).thenReturn(owner);
		PetManager petManager = new PetManager(pets, owners, logger);  // SUT

		assertEquals(petManager.getOwnerPets(1), ownerPets);
	}

	// mockisty, state verification
	@Test
	public void getOwnerPets_Fail_OwnerNotExists() {
		List<Pet> ownerPets = Arrays.asList(mock(Pet.class), mock(Pet.class));  // dummy objects
		Owner owner = mock(Owner.class);  // test stub
		when(owner.getPets()).thenReturn(ownerPets);
		OwnerRepository owners = mock(OwnerRepository.class);  // test stub
		when(owners.findById(1)).thenReturn(owner);
		PetManager petManager = new PetManager(pets, owners, logger);  // SUT

		assertThrows(NullPointerException.class, () -> petManager.getOwnerPets(2));
	}

	// mockisty, state verification
	@Test
	public void getOwnerPetTypes_Success_OwnerHasPets() {
		PetType petType1 = mock(PetType.class);  // dummy object
		PetType petType2 = mock(PetType.class);  // dummy object
		Pet pet1 = mock(Pet.class);  // test stub
		when(pet1.getType()).thenReturn(petType1);
		Pet pet2 = mock(Pet.class);  // test stub
		when(pet2.getType()).thenReturn(petType2);

		List<Pet> ownerPets = Arrays.asList(pet1, pet2);
		Owner owner = mock(Owner.class);  // test stub
		when(owner.getPets()).thenReturn(ownerPets);
		OwnerRepository owners = mock(OwnerRepository.class);  // test stub
		when(owners.findById(1)).thenReturn(owner);
		PetManager petManager = new PetManager(pets, owners, logger);  // SUT

		assertEquals(petManager.getOwnerPetTypes(1), new HashSet<>(Arrays.asList(petType2, petType1)));
	}

	// mockisty, state verification
	@Test
	public void getVisitsBetween_Success_PetHasVisits() {
		LocalDate startDate = LocalDate.of(2021, 10, 23);
		LocalDate endDate = LocalDate.of(2021, 10, 24);
		List<Visit> visits = Arrays.asList(mock(Visit.class), mock(Visit.class));  // dummy objects
		Pet pet = mock(Pet.class);  // test stub
		when(pet.getVisitsBetween(any(LocalDate.class), any(LocalDate.class))).thenReturn(visits);
		PetTimedCache pets = mock(PetTimedCache.class);  // test stub
		when(pets.get(1)).thenReturn(pet);
		PetManager petManager = new PetManager(pets, owners, logger);  // SUT

		assertEquals(petManager.getVisitsBetween(1, startDate, endDate), visits);
	}

}
