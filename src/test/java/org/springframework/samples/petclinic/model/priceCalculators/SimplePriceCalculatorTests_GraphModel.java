package org.springframework.samples.petclinic.model.priceCalculators;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.model.UserType;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class SimplePriceCalculatorTests_GraphModel {

	private List<Pet> pets;
	private double baseCharge;
	private double basePricePerPet;

	@BeforeEach
	public void setup() {
		this.pets = new ArrayList<>();
		this.baseCharge = 200;
		this.basePricePerPet = 1000;
	}

	@Test
	public void calcPriceEdgeCover_path1() {
		double totalPrice;
		SimplePriceCalculator priceCalculator = new SimplePriceCalculator();
		totalPrice = priceCalculator.calcPrice(this.pets, this.baseCharge, this.basePricePerPet, UserType.GOLD);
		assertEquals(totalPrice, baseCharge);
	}

	@Test
	public void calcPriceEdgeCover_path2() {
		Pet pet1 = mock(Pet.class);
		Pet pet2 = mock(Pet.class);
		PetType petType1 = mock(PetType.class);
		PetType petType2 = mock(PetType.class);
		when(pet1.getType()).thenReturn(petType1);
		when(pet2.getType()).thenReturn(petType2);
		when(petType1.getRare()).thenReturn(true);
		when(petType2.getRare()).thenReturn(false);
		pets.add(pet1);
		pets.add(pet2);
		double totalPrice, expected;
		expected = (this.baseCharge + this.basePricePerPet * 1.2 + this.basePricePerPet) * UserType.NEW.discountRate;
		SimplePriceCalculator priceCalculator = new SimplePriceCalculator();
		totalPrice = priceCalculator.calcPrice(this.pets, this.baseCharge, this.basePricePerPet, UserType.NEW);
		assertEquals(expected, totalPrice);
	}

}

