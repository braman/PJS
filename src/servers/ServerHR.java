package servers;
import impl.HRImpl;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

import ModuleHR.HumanResources;
import ModuleHR.HumanResourcesHelper;
import ModuleV.Verifier;
import ModuleV.VerifierHelper;

public class ServerHR {

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
			HRImpl impl = new HRImpl();

			// get object reference from the servant
			org.omg.CORBA.Object ref = rootpoa.servant_to_reference(impl);
			HumanResources href = HumanResourcesHelper.narrow(ref);

			org.omg.CORBA.Object objRef =  orb.resolve_initial_references("NameService");
			NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

			NameComponent path[] = ncRef.to_name( "HR" );
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