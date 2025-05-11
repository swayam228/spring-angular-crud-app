package jsp.springboot.spring_boot_crud_application.DTO;

import lombok.Data;

@Data
public class ResponseStructure<T> {
	private int statusCode;
	private String message;
	private T data;
}
