package common;

public class Response {
	
	public static final Response OK = new Response(Codes.OK, "OK");
	
	public int 	  code;
	public String message;
	
	public Response(int code, String message) {
		super();
		this.code = code;
		this.message = message;
	}
	
	
	
	
}
