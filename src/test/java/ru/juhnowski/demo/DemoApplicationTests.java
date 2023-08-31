package ru.juhnowski.demo;

import java.util.stream.Stream;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


// @RunWith(SpringRunner.class)
// @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
// @AutoConfigureMockMvc

//@ExtendWith(MockitoExtension.class)

@ExtendWith(SpringExtension.class)
@WebAppConfiguration()
@AutoConfigureMockMvc
class DemoApplicationTests {

    @Mock
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @BeforeEach // For Junit5
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup().build();

    }

	@Autowired
	private CatRepository catRepository;

	@BeforeEach
	public void before() throws Exception {
		Stream.of("Felix", "Garfield", "Whiskers").forEach(
			n -> catRepository.save(new Cat(n)));
	}

	@Test
	public void catsReflectedInRead() throws Exception {
		MediaType halJson = MediaType.parseMediaType("application/hal+json;charset=UTF-8");
		mockMvc
			.perform(get("/cats"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(halJson))
			.andExpect(
				mvcResult -> {
					String contentAsString = mvcResult.getResponse().getContentAsString();
					assertTrue(contentAsString.split("totalElements")[1].split(":")[1].trim().split(",")[0].equals("3"));
				}
			);
	}

}
