package exception;

public class RepeatedPartition extends Exception{

    /**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public RepeatedPartition(){
        super("La partición ya existe");
    }
}
