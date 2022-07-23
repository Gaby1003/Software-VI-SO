package exception;

public class EmptyTextFieldException extends Exception{

	private static final long serialVersionUID = 1L;

	public EmptyTextFieldException(){
        super("Campo de texto vac\u00edo");
    }
}
