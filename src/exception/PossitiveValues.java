package exception;

public class PossitiveValues extends Exception{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PossitiveValues(){
        super("<html><center>Los valores ingresados <br> deben ser positivos <br> y mayores a cero</center><html>"); 
    }
}
