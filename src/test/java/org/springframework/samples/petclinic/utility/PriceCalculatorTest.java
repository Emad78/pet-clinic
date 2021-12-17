package org.springframework.samples.petclinic.utility;

import org.junit.Before;
import org.junit.Test;
import org.springframework.samples.petclinic.owner.Pet;
import org.springframework.samples.petclinic.visit.Visit;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;
import static java.time.temporal.ChronoUnit.YEARS;
import static org.junit.jupiter.api.Assertions.*;


public class PriceCalculatorTest {

	private static ArrayList<Pet> pets;

	@Before
	public void setup() {
		pets = new ArrayList<>();
	}

	@Test
	public void calcPrice_test1() {
		Pet pet1 = new Pet();
		pet1.setBirthDate(LocalDate.now().minusYears(2));
		Visit visit1 = new Visit();
		visit1.setDate(LocalDate.now().minusDays(100));
		pet1.addVisit(visit1);
		for (int i = 0; i < 4; i++) {
			pets.add(pet1);
		}

		Pet pet2 = new Pet();
		pet2.setBirthDate(LocalDate.of(2010, 1, 1));
		Visit visit2 = new Visit();
		visit2.setDate(LocalDate.of(2010, 1, 1));
		pet2.addVisit(visit2);
		for (int i = 0; i < 3; i++) {
			pets.add(pet2);
		}

		double result = PriceCalculator.calcPrice(pets, 19.0, 23.0);
		assertEquals(391523.75999999995, result);
	}

	@Test
	public void calcPrice_test2() {
		Pet pet2 = new Pet();
		pet2.setBirthDate(LocalDate.of(2021, 1, 1));
		Visit visit2 = new Visit();
		visit2.setDate(LocalDate.now().minusDays(99));
		pet2.addVisit(visit2);

		Pet pet1 = new Pet();
		pet1.setBirthDate(LocalDate.now().minusYears(2));
		Visit visit1 = new Visit();
		visit1.setDate(LocalDate.now().minusDays(100));
		pet1.addVisit(visit1);

		for (int i = 0; i < 7; i++) {
			pets.add(pet1);
			pets.add(pet2);
		}

		double result = PriceCalculator.calcPrice(pets, 19.0, 23.0);
		assertEquals(230193.15999999997, result);
	}

}
