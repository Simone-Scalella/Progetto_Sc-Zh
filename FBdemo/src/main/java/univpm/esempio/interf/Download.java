package univpm.esempio.interf;
import org.json.JSONObject;

public interface Download {
	
	public JSONObject GetPage(String ID,String Access_token,String metrics);
	
	public JSONObject GetPageUpdate( String ID,String Access_token);
	
	public String GetPageString( String ID,String Access_token);


}
