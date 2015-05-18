import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

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
		    
		    String response = verifier.verify("{\"iin\":\"123123123\"}");
		    
		    System.out.println(response);
		    
	      }
	      catch (Exception e) {
	         System.out.println("Hello Client exception: " + e);
		  e.printStackTrace();
	      }

	   }
	
}
