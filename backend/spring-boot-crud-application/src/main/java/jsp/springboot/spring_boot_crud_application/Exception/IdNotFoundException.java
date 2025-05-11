package jsp.springboot.spring_boot_crud_application.Exception;

public class IdNotFoundException extends RuntimeException{
	@Override
	public String getMessage() {
		return "Id Not Found In DB";
	}
}
