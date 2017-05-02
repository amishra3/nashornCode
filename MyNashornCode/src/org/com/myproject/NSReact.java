package org.com.myproject;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Service;
/**
 * @CKJ : Contact ckj0369@gmail.com for any more information on this or to add
 *      anything in this which can improve this codebase.
 *
 */
@Component(configurationFactory = true, immediate = true, metatype = true, label = "Nashorn Project 1", description = "Fastest Execution Example")
@Service(value = NSReact.class)
public class NSReact {
	private static ScriptEngine nashorn;
	private static Invocable invEngine;

	@Activate
	protected void activate() {
		nashorn = new ScriptEngineManager().getEngineByName("JavaScript");
		scriptReader();
	}

	/*
	 * Read your js script
	 */
	private void scriptReader() {
		String file = "E:/script-react.js";
		try {
			if (null != file) {
				FileInputStream inputStream = new FileInputStream(file);
				Reader reader = new InputStreamReader(inputStream, "UTF-8");
				nashorn.eval(reader);
				invEngine = (Invocable) nashorn;
				reader.close();
				reader = null;
			}
		} catch (UnsupportedEncodingException exception) {
			System.out.println(exception.getMessage());
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
		}
	}

	/**
	 * Execute your stored js script Generally in case of react we've component
	 * name along with it's json to be passed to js method Assuming here my
	 * method name is renderMethod and component name is componentName with json
	 * as componentJson
	 */
	public synchronized String evaluateComponent(String componentName, String componentJson) {		
		String parse = null;
		try {
			if (null != componentJson) {
				Object reactObj = invEngine.invokeFunction("renderMethod", componentName, componentJson);
				parse = reactObj.toString();
			}
		} catch (Exception e) {
			System.out.println("Execution failed due to : "+ e.getMessage());
		}
		return parse;
	}
}
