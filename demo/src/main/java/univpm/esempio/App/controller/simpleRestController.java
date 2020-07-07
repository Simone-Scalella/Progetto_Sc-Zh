package univpm.esempio.App.controller;

import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import univpm.esempio.model.FB_page_info;
import univpm.esempio.model.Statics;
import univpm.esempio.model.modello;
import univpm.esempio.model.page_post;
import univpm.esempio.model.request_body;
import univpm.esempio.model.ALL_post;


@RestController
public class simpleRestController {
	
	@Autowired(required = false)
	Statics Statics1 = new Statics();

	@GetMapping("/login")
	public ResponseEntity<Object>  Method6(@RequestParam(name="ID_Page",defaultValue="null") String param1,@RequestParam(name="Access_Token",defaultValue="null") String param2,@RequestParam(name="Period",defaultValue="days_28") String param3) {
		if(Statics1.Login(param1, param2, param3)) {
			return new ResponseEntity<> ("Login effettuato con successo", HttpStatus.OK); 
		}
		else {
			return new ResponseEntity<> ("Login fallito", HttpStatus.UNAUTHORIZED); 
		}
	
	}
	
	@GetMapping("/get_post_filetered")
	public Object Method5(@RequestBody request_body body){
		if (Statics1.isLogin()) {
			System.out.println("login success");
			return Statics1.filter_method(body);
		}
		else {
			
			String frase = "not logged";
			return (Object)frase;
		}
		
	}
	
	@GetMapping("/get_all_post")
	public Object Method6(){
		if (Statics1.isLogin()) {
			System.out.println("login success");
			return Statics1.get_all_Post();
		}
		else {			
			String frase = "not logged";
			return (Object)frase;
		}
		
	}
	
	@GetMapping("/get_page")
	public Object Method7(){
		if (Statics1.isLogin()) {
			System.out.println("login success");
			return Statics1.get_page_all();
		}
		else {			
			String frase = "not logged";
			return (Object)frase;
		}
		
	}
	
	@GetMapping("/update_period")
	public Object Method8(@RequestParam(name="new_period",defaultValue="days_28") String param1){
		if (Statics1.isLogin()) {
			System.out.println("login success");
			Statics1.Update_period(param1);
			return ("period changed with success");
		}
		else {			
			String frase = "not logged";
			return (Object)frase;
		}
		
	}

}
