package impl;

import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.NotFound;

import utils.JsonUtil;
import utils.RemoteUtil;
import ModuleAck.Acknowledger;
import ModuleAck.AcknowledgerHelper;
import ModuleV.VerifierPOA;
import common.ApplicationForm;
import common.Codes;
import common.Response;

public class VerifierImpl extends VerifierPOA {

	@Override
	public String verify(String jsonApp) {
		
		ApplicationForm form = JsonUtil.toApplicationForm(jsonApp);
		
		boolean isOK = isFormValid(form);
		
		Response r = null;
		
		if (isOK) {
			try {
				return passFormForward(jsonApp);
			} catch (Exception e) {
				r = new Response(Codes.WRONG, "Failed to pass to acknowledger");
			}
		} else {
			r = new Response(Codes.WRONG, "Invalid form");
		}		
		
		return JsonUtil.toJSON(r);
	}

	private boolean isFormValid(ApplicationForm form) {
		boolean ok = form.iin != null && form.iin.length() == 12 && !form.iin.startsWith("0");
		
		try {
			Long.parseLong(form.iin);
		} catch (NumberFormatException e) {
			return false;
		}
		
		return ok;
	}
	
	
	private String passFormForward(String jsonApp) throws InvalidName, NotFound, CannotProceed, org.omg.CosNaming.NamingContextPackage.InvalidName {
		NamingContextExt ncExt = RemoteUtil.createNamingContextExt("NameService", "127.0.0.1", "1050");
		Acknowledger ack = AcknowledgerHelper.narrow(ncExt.resolve_str("ACK"));
		return ack.ackn(jsonApp);
	}
	
}
