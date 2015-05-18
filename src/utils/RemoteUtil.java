package utils;

import org.omg.CORBA.ORB;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

public class RemoteUtil {
	
	public static NamingContextExt createNamingContextExt(String serviceName, String host, String port) throws InvalidName {

   	 	String props[] = new String[] {
					"-ORBInitialPort",
					port,
					"-ORBInitialHost",
					host
			};
   	 
	    ORB orb = ORB.init(props, null);
	    org.omg.CORBA.Object objRef = orb.resolve_initial_references(serviceName);
	    NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
	    
	    return ncRef;
	}
	
}
