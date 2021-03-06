package univpm.OOP2020.App.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import univpm.OOP2020.Body.request_body;
import univpm.OOP2020.Model.Statics;

/**<p>
 * <b>Classe</b> tra cui l'utente interagisce con l'applicazione tramite i Mapping
 *</p>
 * @author Zhang Yihang
 * @see univpm.OOP2020.Model.Statics
 */
@RestController
public class simpleRestController {
	
	@Autowired(required = false)
	Statics Statics1 = new Statics();
	/**
	 * Mapping "/Login" che ci permette di effettuare login tramite i parametri <code> ID_Page </code> id della pagina <code>Access_Token</code> e <code>Period</code> periodo delle metriche
	 * @throws ResponseStatusException se login fallisce 
	 * @see univpm.OOP2020.Model.Statics#Login(String, String, String)
	 * @param param1 <code>String</code> ID_page con id della pagina
	 * @param param2 <code>String</code> Access_Token con access token di API Facebook
	 * @param param3 <code>String</code> Period con il periodo delle metriche
	 * @return <code>String</code> che indica Login successo
	 */
	@GetMapping("/login")
	public Object Method6(@RequestParam(name="ID_Page",defaultValue="null") String param1,
										   @RequestParam(name="Access_Token",defaultValue="null") String param2,
										   @RequestParam(name="Period",defaultValue="days_28") String param3){		
		if(Statics1.Login(param1, param2, param3))
		{System.out.println("login success");return new ResponseEntity<> ("Login effettuato con successo", HttpStatus.OK);}
		else {throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"login fallito");}	
	}
	/**
	 * il Mapping "/get_post_filtered" Ci restituisce il post basando sul filtro che inserisce nel <code>body</code>
	 * @param body e' l'oggetto del query da inserire
	 * @throws ResponseStatusException se non e' effettuato login
	 * @throws ResponseStatusException se espressione e' invalida
	 * @throws ResponseStatusException se metriche non esiste
	 * @return <code>Vector page_post </code> che contiene i post che soddisfano la condizione del filtro
	 * @see univpm.OOP2020.Model.Statics#filter_method(request_body)
	 * 
	 */
	@GetMapping("/get_post_filtered")
	public Object Method5(@RequestBody request_body body){
		if (Statics1.isLogin()){return Statics1.filter_method(body);}
		else {throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"login required");}
	}
	/**
	 * 
	 * Mapping "/get_all_post" Restituisce tutti i post della pagina
	 * @throws ResponseStatusException se non e' effettuato login
	 * @return  <code>Vector page_post </code> che contiene tutti i post della pagina
	 * @see univpm.OOP2020.Model.Statics#get_all_Post()
	 */
	@GetMapping("/get_all_post")
	public Object Method6(){
		if (Statics1.isLogin()){return Statics1.get_all_Post();}
		else{throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"login required");}
	}
	/**
	 * Mapping "/get_page" ci restituisce informazione della pagina
	 * @throws ResponseStatusException se non e' effettuato login
	 * @see univpm.OOP2020.Model.Statics#get_page_all()
	 * @return <code>FB_page_info</code> che contiene informazione della pagina
	 * 
	 */
	@GetMapping("/get_page")
	public Object Method7(){
		if (Statics1.isLogin()){return Statics1.get_page_all();}
		else{throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"login required");}
	}
	/**
	 * Mapping "/update_period" ci consente di cambiare il periodo delle metriche del Facebook
	 * @param param1 con il nome new_period indica il periodo che l'utente desidera di cambiare il campo valido sono <code>day</code>, <code> week</code> e <code>days_28</code>
	 * @return <code>String</code> che contiene messaggio della richiesta se e' andata a buon fine
	 * @throws ResponseStatusException se non e' effettuato login
	 * @see univpm.OOP2020.Model.Statics#Update_period(String)
	 */
	@GetMapping("/update_period")
	public Object Method8(@RequestParam(name="new_period",defaultValue="days_28") String param1){
		if (Statics1.isLogin()){Statics1.Update_period(param1);return ("period changed with success");}
		else {throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Login Required");}
	}
	/**
	 * Mapping "/consumption_per_post" ci restituisce informazione del consumo medio del post della pagina
	 * @throws ResponseStatusException se non e' effettuato login
	 * @return <code>String</code> che contiene il valore dalla richiesta
	 * @see univpm.OOP2020.Model.Statics#impression_per_post()
	 */
	@GetMapping("/impression_per_post")
	public String Method9(){
		if (Statics1.isLogin()){return Statics1.impression_per_post();}
		else {throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "login required");}
	}
	/**
	 * Mapping "/consumption_per_post" ci restituisce informazione della reazione media dei posts della pagina
	 * @throws ResponseStatusException se non e' effettuato login
	 * @return <code>String</code> che contiene messaggio della richiesta se e' andata a buon fine
	 */
	@GetMapping("/average_post_reaction")
	public String Method10(){
		if (Statics1.isLogin()){return Statics1.average_post_reaction();}
		else {throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "login required");}
	}

}
