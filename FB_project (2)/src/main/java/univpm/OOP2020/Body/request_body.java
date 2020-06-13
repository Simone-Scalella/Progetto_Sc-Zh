package univpm.OOP2020.Body;
/**
 * <p>
 * <b>Classe</b> che contiene componenti di una semplice espressione logica del filtro
 * </p>
 * @author Zhang Yihang e Scalella Simone
 * @see univpm.OOP2020.Model.Statics#filter_method(request_body)
 */
public class request_body {
	/**
	 * <code>String</code> che contiene operatore logica (==,<=,>=,<,> ecc) dell'espressione
	 */
	private String operator = null;
	/**
	 * <code>String</code> che contiene valore in int dell'espressione
	 */
	private String value = null;
	/**
	 * <code>String</code> che contiene attributo dell'espressione
	 */
	private String attribute = null;
	
	public request_body() {};
	/**
	 * Restituisce operatore
	 * @return <code>String</code> che contiene operatore logico
	 */
	public String getOperator() {return operator;}
	/**
	 * Restituisce valore dell'espressione
	 * @return <code>String</code> che contiene il valore
	 */
	public String getValue() {return value;}
	/**
	 * Restituisce attributo dell'espressione
	 * @return <code>String</code> che contiene attributo dell'espressione
	 */
	public String getAttribute() {return attribute;}	
}
