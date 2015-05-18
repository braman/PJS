package impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import utils.JsonUtil;
import common.ApplicationForm;
import common.Codes;
import common.Response;
import ModuleHR.HumanResourcesPOA;

public class HRImpl extends HumanResourcesPOA {

	@Override
	public String save(String jsonApp) {
		Response response = null;
		
		ApplicationForm appForm = JsonUtil.toApplicationForm(jsonApp);
		
		String fileName = "data" + File.separator + appForm.uniqueId + ".json";
		
		try {
			saveToFile(fileName, appForm);
			response = Response.OK;
			
		} catch (FileNotFoundException e) {
			response = new Response(Codes.WRONG, "Failed to write save application form");
		}
		
		return JsonUtil.toJSON(response);
	}

	private void saveToFile(String fileName, ApplicationForm appForm) throws FileNotFoundException {
		PrintWriter out = new PrintWriter(new File(fileName));
		String data = JsonUtil.toJSON(appForm);
		out.print(data);
		out.close();	
	}
	
}
