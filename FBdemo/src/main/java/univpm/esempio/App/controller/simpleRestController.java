package univpm.esempio.App.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import univpm.esempio.model.FB_page_info;
import univpm.esempio.model.page_post;


@RestController
public class simpleRestController {
	
	@GetMapping("/getpost")
	public FB_page_info Method2(@RequestParam(name="param1",defaultValue="null") String param1) {
		return new FB_page_info("112228053835001","EAAj9pRhdt7cBADoFODHvJhLjqnMdEZCKaz8ORVjZCUmdL0uubCSjjykBSAc2ZANdVoOO5ilUcW8Hd03NlefWTvQlgPjDeSNcsizn5dvhGaTgPiwLZCXDGOVdkL1CORzryXatEQEc1i38mR9og5ohFSf4iy8dqysyMcRsk3tsDAZDZD","days_28");
	}
	
	@GetMapping("/getpost1")
	public page_post Method3(@RequestParam(name="param1",defaultValue="null") String param1) {
		return new page_post("112228053835001","EAAj9pRhdt7cBADoFODHvJhLjqnMdEZCKaz8ORVjZCUmdL0uubCSjjykBSAc2ZANdVoOO5ilUcW8Hd03NlefWTvQlgPjDeSNcsizn5dvhGaTgPiwLZCXDGOVdkL1CORzryXatEQEc1i38mR9og5ohFSf4iy8dqysyMcRsk3tsDAZDZD");
	}
}
