package univpm.OOP2020.Service;
import org.json.JSONObject;
/**
 * 
 * <p>
 * <b>Interfaccia</b> che contiene il metodo condiviso tra post e page 
 * </p>
 * @author Scalella Simone
 * @see univpm.OOP2020.Service.ALL_post
 * @see univpm.OOP2020.Service.FB_page_info
 */
public interface Download {
	/**
	 * Restituisce informazione dal Facebook API
	 * @param ID <code>String</code>  indica Id della pagina
	 * @param Access_token <code>String</code> Access_token indica access token della pagina
	 * @param metrics <code>String</code> metrics indica metriche della richiesta
	 * @return <code>JSONObject</code> che contiene informazione della pagina dal Facebook API
	 */
	public JSONObject GetPage(String ID,String Access_token,String metrics);

}
