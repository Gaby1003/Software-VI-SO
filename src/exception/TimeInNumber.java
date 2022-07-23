package exception;

public class TimeInNumber extends Exception{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TimeInNumber(){
        super("<html><center>S\u00f3lo se admiten <br> valores num\u00e9ricos <br> en el tiempo</center></html>");
    }
}
