package univpm.OOP2020.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import univpm.OOP2020.Model.*;
import univpm.OOP2020.Service.FB_page_info;
/**
 * <p>
 * <b>Class</b> che viene utilizzata per eseguire dei test sulla classe FB_page_info e Statics
 * </p>
 * @author  Simone Scalella
 * @see univpm.OOP2020.Service.FB_page_info
 * @see univpm.OOP2020.Model.Statics
 */
class OOP_2020_Test {
	/**
	 * attributo privato necessario per il test
	 */
	private String access_token = "EAAj9pRhdt7cBADoFODHvJhLjqnMdEZCKaz8ORVjZCUmdL0uubCSjjykBSAc2ZANdVoOO5ilUcW8Hd03NlefWTvQlgPjDeSNcsizn5dvhGaTgPiwLZCXDGOVdkL1CORzryXatEQEc1i38mR9og5ohFSf4iy8dqysyMcRsk3tsDAZDZD";
	/**
	 * attributo privato necessario per il test
	 */
	private String id = "112228053835001";
	/**
	 * attributo privato necessario per il test
	 */
	private String period = "days_28";
	/**
	 * dichiarazione di un oggetto di tipo FB_page_info
	 */
	private FB_page_info test_page ;
	/**
	 * dichiarazione di un oggetto di tipo Statics
	 */
	private Statics test_login;
     /**
      * andiamo a instanziare due oggetti delle classi viste in precedenza utilizzando i loro costruttori
      * @throws Exception
      */
	@BeforeEach
	void setUp() throws Exception {
	     test_page = new FB_page_info(id,access_token,period);
	     test_login = new Statics();
	}

     /**
      * Test 1) verifica che tutti gli attibuti della classe metric_values siano non nulli dopo aver instanziato l'oggetto, 
      * quindi non ci sono stati problemi con la chiamata all'API facebook
      */
	@Test
	void test1() {
		assertNotNull(test_page.getMetric_Object());
	}
	/**
	 * Test 2) verifica che il metodo login funzioni correttamente passandogli l'Id della pagina, l'access_token e il period corretti
	 */
	@Test
	void test2() {
		assertEquals(true,test_login.Login(id,access_token, period));
	}

}
