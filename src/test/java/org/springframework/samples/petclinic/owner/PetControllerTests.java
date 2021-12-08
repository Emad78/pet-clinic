package org.springframework.samples.petclinic.owner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.utility.PetTimedCache;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.assertj.core.util.Lists;

@WebMvcTest(value = PetController.class,
includeFilters = {
	@ComponentScan.Filter(value = LoggerConfig.class, type = FilterType.ASSIGNABLE_TYPE),
	@ComponentScan.Filter(value = PetService.class, type = FilterType.ASSIGNABLE_TYPE),
	@ComponentScan.Filter(value = PetTimedCache.class, type = FilterType.ASSIGNABLE_TYPE),
	@ComponentScan.Filter(value = PetTypeFormatter.class, type = FilterType.ASSIGNABLE_TYPE)
})
class PetControllerTests {
	private static final String CREATE_OR_UPDATE_FORM = "pets/createOrUpdatePetForm";
	private static final String REDIRECT_TO_OWNER = "redirect:/owners/{ownerId}";

	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private PetRepository petRepository;
	@MockBean
	private OwnerRepository ownerRepository;

	@BeforeEach
	void setup() {
		PetType dog = new PetType();
		dog.setName("cat");
		dog.setId(1);

		Pet garfield = new Pet();
		garfield.setId(1);
		garfield.setName("garfield");
		given(ownerRepository.findById(63)).willReturn(new Owner());
		when(petRepository.findPetTypes()).thenReturn(Lists.newArrayList(dog));
		given(petRepository.findById(1)).willReturn(garfield);
	}

	@Test
	public void getCreationFormTest() throws Exception {
		mockMvc.perform(get("/owners/63/pets/new"))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("pet"))
			.andExpect(view().name("pets/createOrUpdatePetForm"));
	}
	@Test
	public void getUpdateFormTest() throws Exception {
		mockMvc.perform(get("/owners/63/pets/1/edit"))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("pet"))
			.andExpect(view().name("pets/createOrUpdatePetForm"));
	}


	@Test
	public void postCreationFormTest() throws Exception {
		mockMvc.perform(post("/owners/63/pets/new")
				.param("name", "tom")
				.param("type", "cat")
				.param("birthDate", "2000-01-12"))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/owners/{ownerId}"));
	}
	@Test
	public void postUpdateFormTest() throws Exception {
		mockMvc.perform(post("/owners/63/pets/2/edit")
				.param("name", "tom")
				.param("type", "cat")
				.param("birthDate", "2010-06-28"))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/owners/{ownerId}"));
	}


	@Test
	public void postCreationFormErrorTest() throws Exception {
		mockMvc.perform(post("/owners/63/pets/new"))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("pet"))
			.andExpect(view().name("pets/createOrUpdatePetForm"));
	}
	@Test
	public void postUpdateFormErrorTest() throws Exception {
		mockMvc.perform(post("/owners/63/pets/1/edit"))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("pet"))
			.andExpect(view().name("pets/createOrUpdatePetForm"));
	}

}
