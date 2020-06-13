package univpm.OOP2020.interf;
import org.json.JSONObject;
/**
 * 
 * <p>
 * <b>Interfaccia</b> che contiene il metodo condiviso tra post e page 
 * </p>
 * @see univpm.OOP2020.models.ALL_post
 * @see univpm.OOP2020.models.FB_page_info
 */
public interface Download {
	/**
	 * Restituisce informazione dal Facebook API
	 * @param <code>String</code> ID indica Id della pagina
	 * @param <code>String</code> Access_token indica access token della pagina
	 * @param <code>String</code> metrics indica metriche della richiesta
	 * @return <code>JSONObject<code> che contiene informazione della pagina dal Facebook API
	 */
	public JSONObject GetPage(String ID,String Access_token,String metrics);

}
