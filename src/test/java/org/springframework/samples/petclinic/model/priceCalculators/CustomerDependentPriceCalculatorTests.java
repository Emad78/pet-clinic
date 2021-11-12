package org.springframework.samples.petclinic.model.priceCalculators;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.model.UserType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CustomerDependentPriceCalculatorTests {

	private static List<Pet> pets;
	private static double baseCharge;
	private static double basePricePerPet;
	private static CustomerDependentPriceCalculator customerDependentPriceCalculator;

	@BeforeEach
	public void setup() {
		pets = new ArrayList<>();
		baseCharge = 200;
		basePricePerPet = 1000;
		customerDependentPriceCalculator = new CustomerDependentPriceCalculator();
	}

	@Test
	public void calcPrice_WithoutDiscount_UserGoldType() {
		Pet pet1 = mock(Pet.class);
		pets.add(pet1);
		PetType petType1 = mock(PetType.class);
		when(pet1.getType()).thenReturn(petType1);
		when(petType1.getRare()).thenReturn(false);

		double totalPrice = customerDependentPriceCalculator.calcPrice(
			pets, baseCharge, basePricePerPet, UserType.GOLD
		);
		assertEquals(1160.0, totalPrice);
	}

	@Test
	public void calcPrice_WithoutDiscount_UserNewType() {
		Pet pet1 = mock(Pet.class);
		pets.add(pet1);
		PetType petType1 = mock(PetType.class);
		when(pet1.getType()).thenReturn(petType1);
		when(petType1.getRare()).thenReturn(false);

		double totalPrice = customerDependentPriceCalculator.calcPrice(
			pets, baseCharge, basePricePerPet, UserType.NEW
		);
		assertEquals(1200.0, totalPrice);
	}

	@Test
	public void calcPrice_WithDiscount_UserNewType() throws ParseException {
		PetType rareType = mock(PetType.class), nonRareType = mock(PetType.class);
		when(rareType.getRare()).thenReturn(true);
		when(nonRareType.getRare()).thenReturn(false);

		Pet pet1 = mock(Pet.class), pet2 = mock(Pet.class), pet3 = mock(Pet.class), pet4 = mock(Pet.class);
		when(pet1.getType()).thenReturn(rareType);
		when(pet2.getType()).thenReturn(nonRareType);
		when(pet3.getType()).thenReturn(rareType);
		when(pet4.getType()).thenReturn(nonRareType);

		when(pet3.getBirthDate()).thenReturn(new SimpleDateFormat("yyyy").parse("2010"));
		when(pet4.getBirthDate()).thenReturn(new SimpleDateFormat("yyyy").parse("2010"));

		pets.add(pet1);
		pets.add(pet2);
		pets.add(pet3);
		pets.add(pet4);
		pets.add(pet1);
		pets.add(pet1);
		pets.add(pet1);

		double totalPrice = customerDependentPriceCalculator.calcPrice(
			pets, baseCharge, basePricePerPet, UserType.NEW
		);
		assertEquals(9814.0, totalPrice);
	}

	@Test
	public void calcPrice_WithDiscount_UserGoldType() throws ParseException {
		PetType rareType = mock(PetType.class), nonRareType = mock(PetType.class);
		when(rareType.getRare()).thenReturn(true);
		when(nonRareType.getRare()).thenReturn(false);

		Pet pet1 = mock(Pet.class), pet2 = mock(Pet.class), pet3 = mock(Pet.class), pet4 = mock(Pet.class);
		when(pet1.getType()).thenReturn(rareType);
		when(pet2.getType()).thenReturn(nonRareType);
		when(pet3.getType()).thenReturn(rareType);
		when(pet4.getType()).thenReturn(nonRareType);

		when(pet3.getBirthDate()).thenReturn(new SimpleDateFormat("yyyy").parse("2010"));
		when(pet4.getBirthDate()).thenReturn(new SimpleDateFormat("yyyy").parse("2010"));

		pets.add(pet1);
		pets.add(pet2);
		pets.add(pet3);
		pets.add(pet4);
		pets.add(pet1);
		pets.add(pet1);
		pets.add(pet1);

		double totalPrice = customerDependentPriceCalculator.calcPrice(
			pets, baseCharge, basePricePerPet, UserType.GOLD
		);
		assertEquals(8256.0, totalPrice);
	}

}
