package utils;

import com.google.gson.Gson;
import common.ApplicationForm;

public class JsonUtil {
	
	public static ApplicationForm toApplicationForm(String json) {
		Gson g = new Gson();
		return g.fromJson(json, ApplicationForm.class);
	}
	
	public static<T> T toClass(String json, Class<T> clazz) {
		Gson g = new Gson();
		return g.fromJson(json, clazz);
	}
	
	public static String toJSON(Object obj) {
		Gson g = new Gson();
		return g.toJson(obj);
	}
		
}
