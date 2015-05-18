package impl;

import java.util.UUID;

import org.omg.CosNaming.NamingContextExt;

import utils.JsonUtil;
import utils.RemoteUtil;
import ModuleAck.AcknowledgerPOA;
import ModuleHR.HumanResources;
import ModuleHR.HumanResourcesHelper;

import common.ApplicationForm;
import common.Response;

public class AcknoledgerImpl extends AcknowledgerPOA {

	
	@Override
	public String ackn(String jsonApp) {
		
		ApplicationForm appForm = JsonUtil.toApplicationForm(jsonApp);
		
		appForm.uniqueId = generateUniqueId();
		
		try {
			return passFormForward(JsonUtil.toJSON(appForm));
		} catch (Exception e) {
			return JsonUtil.toJSON(new Response(400, "Failed to pass hr"));
		}
	}

	
	private String generateUniqueId() {
		return UUID.randomUUID().toString();
	}
	
	
	private String passFormForward(String jsonApp) throws Exception {
		NamingContextExt ncExt = RemoteUtil.createNamingContextExt("NameService", "127.0.0.1", "1050");
		HumanResources hr = HumanResourcesHelper.narrow(ncExt.resolve_str("HR"));
		return hr.save(jsonApp);
	}


	
	
}
