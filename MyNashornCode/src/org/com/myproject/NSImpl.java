package org.com.myproject;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import javax.script.Compilable;
import javax.script.CompiledScript;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Service;
import org.com.helper.FetchFile;
import org.osgi.service.component.ComponentContext;

/**
 * @CKJ : Contact ckj0369@gmail.com for any more information on this or to add anything in this which can improve this codebase.
 *
 */
@Component(configurationFactory = true, immediate = true, metatype = true, label = "Nashorn Project 1", description = "Fastest Execution Example")
@Service(value = NSImpl.class)
public class NSImpl{

	private static ScriptEngine nashorn;
	private static Invocable invEngine;
	private static CompiledScript script;

	@Activate
	protected void activate(ComponentContext context) {
		nashorn = new ScriptEngineManager().getEngineByName("JavaScript");
		scriptReader();
	}

	/**
	 * Read your js script
	 * 
	 */
	private void scriptReader() {
		String file = "E:/script.js";
		
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
			System.out.println("Not able to read script" + exception.getMessage());
		} catch (Exception exception) {
			System.out.println("Unexpected error occured while processing the JS file" + exception.getMessage());
		}
	}

	/**
	 * Execute your stored js script
	 * 
	 */
	public synchronized String evaluateComponent(String params) {
		String parse = null;
		try {
			if (null != params) {
				Object reactObj = invEngine.invokeFunction("renderMethod", params);
				parse = reactObj.toString();
			}
		} catch (Exception e) {
			System.out.println("Nashorn Exception  : Not able to execute - Due to following error: "+ e.getMessage());
		}	
		return parse;
	}

}
