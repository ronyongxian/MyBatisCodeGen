package util;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class FreemakerUtil {

	private static Map<String, Template> templateMap = new ConcurrentHashMap<>();
	
	private static Template getTemplate(String key,String content){
		Template t = templateMap.get(key);
		if(t==null){
			Configuration cfg = new Configuration(Configuration.VERSION_2_3_26);
			StringTemplateLoader stringLoader = new StringTemplateLoader();
			stringLoader.putTemplate(key, content);
			cfg.setTemplateLoader(stringLoader);
			try {
				t = cfg.getTemplate(key, "utf-8");
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			templateMap.put(key, t);
		}
		return t;
	}

	public static String render(String key,String content, Map data) {
		try {
			Template t = getTemplate(key, content);
			Writer out = new StringWriter(4096);
			t.process(data, out);
			out.flush();
			out.close();
			return out.toString();
		} catch (IOException|TemplateException e) {
			throw new RuntimeException(e);
		}
	}
}
