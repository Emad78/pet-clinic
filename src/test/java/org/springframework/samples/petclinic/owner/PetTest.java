package org.springframework.samples.petclinic.owner;

import org.junit.Before;
import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.samples.petclinic.visit.Visit;
import static org.junit.Assume.*;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.*;

@RunWith(Theories.class)
public class PetTest {

	private Pet pet;

	@DataPoints
	public static Visit[] firstVisits(){
		Visit first = new Visit();
		Visit second = new Visit();
		first.setDate(LocalDate.of(2000, 8,12));
		first.setDescription("First visit for first list");
		first.setId(1);
		second.setDate(LocalDate.of(2020, 8,22));
		second.setDescription("Second visit for first list");
		second.setId(2);
		return new Visit[] {first, second};
	}
	@DataPoints
	public static Visit[] secondVisits(){
		Visit first = new Visit();
		Visit second = new Visit();
		first.setDate(LocalDate.of(2010, 6,12));
		first.setDescription("First visit for second list");
		first.setId(3);
		second.setDate(LocalDate.of(2021, 8,22));
		second.setDescription("Second visit for second list");
		second.setId(4);
		return new Visit[] {first, second};
	}

	@Before
	public void setup(){
		pet = new Pet();
	}

	@Theory public void getVisitsTest(Visit first, Visit second){
		List<Visit> visits = new ArrayList<Visit>();
		visits.add(first);
		visits.add(second);
		assumeFalse(visits.contains(null));
		assumeFalse(first.getId() == second.getId());
		pet.setVisitsInternal(visits);
		List<Visit> result = pet.getVisits();
		for(Visit visit:visits)
			assertTrue(result.contains(visit));
	}
}
