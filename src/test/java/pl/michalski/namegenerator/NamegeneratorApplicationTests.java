package pl.michalski.namegenerator;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NamegeneratorApplicationTests {

	private NameService nameService;

	@Autowired
	public NamegeneratorApplicationTests(NameService nameService) {
		this.nameService = nameService;
	}

	@Test
	void contextLoads() {
	}

	@Test
	void shouldReturn5(){
		Assert.assertTrue(nameService.getFiveRandomNames().getContent().size() == 5);
	}

	@Test
	void shouldNotBeEmpty(){
		Assert.assertFalse(nameService.getFiveRandomNames().getContent().isEmpty());
	}

}
