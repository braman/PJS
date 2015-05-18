package servers;
import impl.VerifierImpl;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

import ModuleV.Verifier;
import ModuleV.VerifierHelper;

public class ServerV {

	public static void main(String args[]) {
		try{
			// create and initialize the ORB //// get reference to rootpoa &amp; activate the POAManager
			
			String props[] = new String[] {
					"-ORBInitialPort",
					"1050",
					"-ORBInitialHost",
					"127.0.0.1"
			};
			
			ORB orb = ORB.init(props, null);      
			POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
			rootpoa.the_POAManager().activate();

			// create servant and register it with the ORB
			VerifierImpl impl = new VerifierImpl();


			// get object reference from the servant
			org.omg.CORBA.Object ref = rootpoa.servant_to_reference(impl);
			Verifier href = VerifierHelper.narrow(ref);

			org.omg.CORBA.Object objRef =  orb.resolve_initial_references("NameService");
			NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);


			NameComponent path[] = ncRef.to_name( "V" );
			ncRef.rebind(path, href);

			System.out.println("Server ready and waiting ...");

			// wait for invocations from clients
			for (;;){
				orb.run();
			}
		} catch (Exception e) {
			System.err.println("ERROR: " + e);
			e.printStackTrace(System.out);
		}

		System.out.println("Server Exiting ...");

	}
}