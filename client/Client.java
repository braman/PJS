import java.util.Scanner;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

import utils.JsonUtil;
import common.ApplicationForm;
import common.Response;
import ModuleV.Verifier;
import ModuleV.VerifierHelper;


public class Client {

	

	   /**
	    * @param args the command line arguments
	    */
	   public static void main(String[] args) {
	     try {
	    	 
	    	 String props[] = new String[] {
						"-ORBInitialPort",
						"1050",
						"-ORBInitialHost",
						"127.0.0.1"
				};
	    	 
		    ORB orb = ORB.init(props, null);
		    org.omg.CORBA.Object objRef =   orb.resolve_initial_references("NameService");
		    NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
		    Verifier verifier = (Verifier) VerifierHelper.narrow(ncRef.resolve_str("V"));
		    
		    Scanner in = new Scanner(System.in);
		    
		    String line = null;
		    
		    while (true) {
		    	System.out.print("Enter IIN(q for exit):");
		    	line = in.nextLine();
		    	
		    	if ("q".equalsIgnoreCase(line)) {
		    		break;
		    	}
		    	
		    	ApplicationForm appForm = new ApplicationForm();
		    	appForm.iin = line;
		    	
		    	String r= verifier.verify(JsonUtil.toJSON(appForm));
		    	
		    	Response response = JsonUtil.toClass(r, Response.class);
		    	
		    	if (response.code != 200) {
		    		System.err.println("Failed to save form, because of " + response.message + "\n");
		    	} else {
		    		System.out.println("Form saved successfuly!");
		    	}
		    }
		    
	      } catch (Exception e) {
	         System.out.println("Client exception: " + e);
		  e.printStackTrace();
	      }

	   }
	
}
