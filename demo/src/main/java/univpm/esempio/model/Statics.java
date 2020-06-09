package univpm.esempio.model;

import java.util.Vector;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.json.JSONObject;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

import univpm.esempio.model.FB_page_info.metric_values;

public class Statics {
	
	FB_page_info page = null;
	ALL_post posts = null;
	Vector<page_post> Posts_vector = null;
	private float Media_click =0;
	private float Media_negativa = 0;
	private float Media_virale = 0;
	private boolean login = false;
	private String Id = "";
	private String Acess_token = "";
	private String period = "";
	public Statics() {};
	
	public boolean Login(String Id, String Acess_token, String period) {
		
		try {
			this.Id = Id;
			this.Acess_token =Acess_token;
			this.period = period;
			page = new FB_page_info(Id,Acess_token,period);
			posts = new ALL_post(Id,Acess_token);
			Media_click = Average(page.getMetric_Object().page_consumptions,page.getMetric_Object().page_consumptions_unique);
			Media_negativa = Average(page.getMetric_Object().page_negative_feedback,page.getMetric_Object().page_negative_feedback_unique);
		    Media_virale = Average(page.getMetric_Object().page_impressions,page.getMetric_Object().page_impressions_unique);
			Posts_vector = posts.getPosts();
			login = true;			
		}catch (Exception e ) {			
			login = false;
		}
		
		return login;
	}
	
	public float Average(int a,int b)
	{
		return (float)a/(float)b;
	}
	
	public Vector<page_post> filter_method(request_body query) {
		
		Vector<page_post> Posts_vector_2 = new Vector<page_post>();
        for(page_post post : Posts_vector)
        {
          try {
            String valore = query.getValue();
            String op = query.getOperator();
            String impression_total =  ((Integer)post.getReactions_01().total_impression).toString();
            String condition = impression_total+op+valore;
            ScriptEngineManager sem = new ScriptEngineManager();         
            ScriptEngine se = sem.getEngineByName("JavaScript");
            
            if((boolean)se.eval(condition)) {
            	Posts_vector_2.add(post);
            }
            

        } catch (ScriptException e) {

            System.out.println("Invalid Expression");
            e.printStackTrace();

        }
        }
        return Posts_vector_2;
	}
	
	public Vector<page_post> get_all_Post() {
		return Posts_vector;
	}
	
	public boolean isLogin() {
			return login;
	}
	
	public FB_page_info get_page_all() {
		return page;
	}
	
	public void Update_period(String period) {
		this.period = period;
		page = new FB_page_info(Id,Acess_token,period);;
	}
	
}