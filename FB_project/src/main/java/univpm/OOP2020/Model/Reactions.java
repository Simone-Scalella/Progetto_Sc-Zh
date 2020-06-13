package univpm.OOP2020.Model;
/**
 * <p>
 * <b>Classe</b> che contiene le reazioni del post
 * </p>
 * @author Scallela Simone
 *
 */
public class Reactions{
	/**
	 * attributo per i like 
	 */
	private int like= 0;
	/**
	 * attributo per wow
	 */
	private int wow = 0;
	/**
	 * attributo per love 
	 */
	private int love = 0;
	/**
	 * attributo care 
	 */
	private int care = 0;
	/**
	 * attributo haha 
	 */
	private int haha = 0;
	/**
	 * attributo per il totale delle reazioni  
	 */
	private int total_impression = 0;
	/**
	 * costruttore vuoto di reactions
	 */
	public Reactions() {};
	/**
	 * metodo che assegna la somma delle reazioni all'attributo precedente
	 */
	public void update(){total_impression = like+wow+love+care+haha;}
	/**
	 * @return <code> int </code>  metodo getter per like
	 */
	public int getlike() {return like;}
	/**
	 * @return <code> int </code> metodo getter per wow
	 */
	public int getwow() {return wow;}
	/**
	 * @return <code> int </code> metodo getter per love
	 */
	public int getlove() {return love;}	
	/**
	 * @return <code> int </code> metodo getter per care
	 */
	public int getcare(){return care;}
	/**
	 * @return <code> int </code> metodo getter per haha
	 */
	public int gethaha() {return haha;}
	/**
	 * @return <code> int </code> metodo getter per total_impression
	 */
	public int gettotal_impression() {return total_impression;}
	
}