package bdd;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.owner.*;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class PetServiceSteps {

	@Autowired private PetService petService;
	@Autowired PetTypeRepository petTypeRepository;
	@Autowired PetRepository petRepository;
	@Autowired OwnerRepository ownerRepository;

	private PetType petType;
	private final HashMap<String, Owner> owners = new HashMap<>();
	private final HashMap<String, Pet> pets = new HashMap<>();

	private Owner actualOwnerFindResult;
	private Pet actualPetFindResult;

	@Given("There is an owner with first name {string}")
	public void thereIsAnOwner(String firstName) {
		Owner owner = new Owner();
		owner.setFirstName(firstName);
		owner.setLastName("-");
		owner.setAddress("-");
		owner.setCity("-");
		owner.setTelephone("0000000000");
		ownerRepository.save(owner);
		owners.put(firstName, owner);
	}

	@Given("There is pet type {string}")
	public void thereIsPetType(String petTypeName) {
		petType = new PetType();
		petType.setName(petTypeName);
		petTypeRepository.save(petType);
	}

	@Given("There is a pet called {string} with owner {string}")
	public void ownerHasAPet(String name, String ownerFirstName) {
		Owner owner = owners.get(ownerFirstName);
		Pet pet = petService.newPet(owner);
		pet.setType(petType);
		pet.setName(name);
		petService.savePet(pet, owner);
		pets.put(name, pet);
	}

	@When("Using find pet service to find {string}")
	public void performFindPet(String name) {
		actualPetFindResult = petService.findPet(pets.get(name).getId());
	}

	@When("Using find owner service to find {string}")
	public void usingFindOwnerServiceToFind(String firstName) {
		actualOwnerFindResult = petService.findOwner(owners.get(firstName).getId());
	}

	@Then("Founded pet name is {string}")
	public void findPetResultShouldBe(String expectedFindResultName) {
		assertEquals(expectedFindResultName, actualPetFindResult.getName());
	}

	@Then("Founded pet name is not {string}")
	public void findPetResultShouldNotBe(String expectedFindResultName) {
		assertNotEquals(expectedFindResultName, actualPetFindResult.getName());
	}

	@Then("Founded owner first name is {string}")
	public void findOwnerResultShouldBe(String expectedFindResultFirstName) {
		assertEquals(expectedFindResultFirstName, actualOwnerFindResult.getFirstName());
	}

	@Then("Founded owner first name is not {string}")
	public void findOwnerResultShouldNotBe(String expectedFindResultFirstName) {
		assertNotEquals(expectedFindResultFirstName, actualOwnerFindResult.getFirstName());
	}

}
