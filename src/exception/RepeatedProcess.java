package exception;

public class RepeatedProcess extends Exception{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RepeatedProcess(){
        super("El proceso ya existe");
    }
}
