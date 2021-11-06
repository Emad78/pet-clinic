package org.springframework.samples.petclinic.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.samples.petclinic.model.priceCalculators.SimplePriceCalculator;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class calcPriceEdgeCoverageTest {

	private Pet pet1;
	private Pet pet2;
	private List<Pet> pets;
	private double baseCharge;
	private double basePricePerPet;

	@BeforeEach
	public void setup(){
		this.pets = new ArrayList<Pet>();
		this.baseCharge = 200;
		this.basePricePerPet = 1000;
	}

	@Test
	public void calcPriceEdgeCover_path1(){
		double totalPrice;
		SimplePriceCalculator priceCalculator = new SimplePriceCalculator();
		totalPrice = priceCalculator.calcPrice(this.pets, this.baseCharge, this.basePricePerPet, UserType.GOLD);
		assertEquals(totalPrice, baseCharge);
	}

	@Test
	public void calcPriceEdgeCover_path2(){
		pet1 = mock(Pet.class);
		pet2 = mock(Pet.class);
		PetType petType1 = mock(PetType.class);
		PetType petType2 = mock(PetType.class);
		when(pet1.getType()).thenReturn(petType1);
		when(pet2.getType()).thenReturn(petType2);
		when(petType1.getRare()).thenReturn(true);
		when(petType2.getRare()).thenReturn(false);
		pets.add(pet1);
		pets.add(pet2);
		double totalPrice, expected;
		expected = (this.baseCharge + this.basePricePerPet*1.2 + this.basePricePerPet) * UserType.NEW.discountRate;
		SimplePriceCalculator priceCalculator = new SimplePriceCalculator();
		totalPrice = priceCalculator.calcPrice(this.pets, this.baseCharge, this.basePricePerPet, UserType.NEW);
		assertEquals(expected, totalPrice);
	}
}
