package univpm.OOP2020.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import univpm.OOP2020.model.*;

class Esempio_Test {
	private String Access_Token = "EAAj9pRhdt7cBADoFODHvJhLjqnMdEZCKaz8ORVjZCUmdL0uubCSjjykBSAc2ZANdVoOO5ilUcW8Hd03NlefWTvQlgPjDeSNcsizn5dvhGaTgPiwLZCXDGOVdkL1CORzryXatEQEc1i38mR9og5ohFSf4iy8dqysyMcRsk3tsDAZDZD";
	private String Id = "112228053835001";
	private String period = "days_28";
	private FB_page_info test_page ;
	private Statics test_login;

	@BeforeEach
	void setUp() throws Exception {
	     test_page = new FB_page_info(Id,Access_Token,period);
	     test_login = new Statics();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test1() {
		assertNotNull(test_page.getMetric_Object());
	}
	@Test
	void test2() {
		assertEquals(true,test_login.Login(Id,Access_Token, period));
	}

}