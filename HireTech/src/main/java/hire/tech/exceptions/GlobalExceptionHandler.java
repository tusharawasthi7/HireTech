package hire.tech.exceptions;
 
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;
import java.io.IOException;
import java.sql.SQLException;
 
import javax.sql.rowset.serial.SerialException;
 
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.ui.Model;
 
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.multipart.MultipartException;
 
@ControllerAdvice
public class GlobalExceptionHandler {
 
//	SerialException
	@ExceptionHandler(exception = SerialException.class)
	public String handleSerialException(SerialException exception) {
		exception.printStackTrace();
		return "errorPage";
	}
 
//	IOException
	@ExceptionHandler(exception = IOException.class)
	public String handleIOException(IOException exception) {
		exception.printStackTrace();
		return "errorPage";
	}
 
//	SQLException
	@ExceptionHandler(exception = SQLException.class)
	public String handleSQLException(SQLException exception) {
		exception.printStackTrace();
		return "errorPage";
	}
 
//	NullPointerException
	@ExceptionHandler(exception = NullPointerException.class)
	public String handleNullPointerException(NullPointerException exception) {
		exception.printStackTrace();
		return "errorPage";
	}
 
//	IllegalArgumentException
	@ExceptionHandler(exception = IllegalArgumentException.class)
	public String handleIllegalArgumentException(IllegalArgumentException exception) {
		exception.printStackTrace();
		return "errorPage";
	}
 
//	MultipartException (related to file upload issues)
	@ExceptionHandler(exception = MultipartException.class)
	public String handleMultipartException(MultipartException exception) {
		exception.printStackTrace();
		return "errorPage";
	}
 
//	HttpClientErrorException (related to REST template errors)
	@ExceptionHandler(exception = HttpClientErrorException.class)
	public String handleHttpClientErrorException(HttpClientErrorException exception) {
		exception.printStackTrace();
		return "errorPage";
	}
 
//	HttpServerErrorException (related to REST template errors)
	@ExceptionHandler(exception = HttpServerErrorException.class)
	public String handleHttpServerErrorException(HttpServerErrorException exception) {
		exception.printStackTrace();
		return "errorPage";
	}
 
//	ResourceAccessException (related to REST template errors)
	@ExceptionHandler(exception = ResourceAccessException.class)
	public String handleResourceAccessException(ResourceAccessException exception) {
		exception.printStackTrace();
		return "errorPage";
	}
 
	@ExceptionHandler(exception = EmptyResultDataAccessException.class)
	public String handleMultipartException(EmptyResultDataAccessException exception) {
		exception.printStackTrace();
		return "errorPage";
	}
 
	@ExceptionHandler(exception = DataAccessException.class)
	public String handleDataAccessException(DataAccessException exception, Model model) {
		exception.printStackTrace();
		return "errorPage";
	}
 
	@ExceptionHandler(exception = Exception.class)
	public String handleException(Exception exception) {
		exception.printStackTrace();
		return "errorPage";
	}
 
	@ExceptionHandler(exception = NoResourceFoundException.class)
	public String handleNoResourceFoundException(NoResourceFoundException exception) {
		exception.printStackTrace();
		return "errorPage";
	}
 
}
 