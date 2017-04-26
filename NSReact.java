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

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Service;
import org.osgi.service.component.ComponentContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @CKJ : Contact ckj0369@gmail.com for any more information on this or to add
 *      anything in this which can improve this codebase.
 *
 */
@Component(configurationFactory = true, immediate = true, metatype = true, label = "Nashorn Project 1", description = "Fastest Execution Example")
@Service(value = NSImpl.class)
public class NSImpl {

	private static ScriptEngine nashorn;
	private static final Logger LOGGER = LoggerFactory.getLogger(NSImpl.class);
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
		InputStream file = null;
		try {
			file = FetchFile.getInput("e://script.js"); // FetchFile class has
														// method getInput to
														// convert a file from
														// physical path to an
														// InputStream file
			if (null != file) {
				Reader reader = new InputStreamReader(file, "UTF-8");
				if (nashorn instanceof Compilable) {
					script = ((Compilable) nashorn).compile(reader);
					script.eval();
				} else {
					nashorn.eval(reader);
				}
				reader.close();
				reader = null;
			}
		} catch (UnsupportedEncodingException exception) {
			LOGGER.error("Not able to read script" + exception.getMessage());
		} catch (Exception exception) {
			LOGGER.error("Unexpected error occured while processing the JS file" + exception.getMessage());
		}
	}

	/**
	 * Execute your stored js script Generally in case of react we've component
	 * name along with it's json to be passed to js method Assuming here my
	 * method name is renderMethod and component name is componentName with json
	 * as componentJson
	 * 
	 */
	public synchronized String evaluateComponent(String componentName, String componentJson) {
		invEngine = (Invocable) nashorn;
		String parse = null;
		try {
			if (null != componentJson) {
				componentJson = componentName.replaceAll("(?m)(?<crlf>\r?\n)", ""); // Replace
																					// all
																					// lines,tabs
																					// etc
				Object reactObj = invEngine.invokeFunction("renderMethod", componentName, componentJson);
				parse = formHTML(reactObj.toString(), componentName, componentJson);
			}
		} catch (Exception e) {
			LOGGER.info("Nashorn Exception  : Not able to execute - Due to following error: ", e.getMessage());
		}
		return parse;
	}

	/*
	 * This function takes your div tags and contents and form a markup code to
	 * provide a proper preview, Please note this might change as per your react
	 * requirements
	 * 
	 */

	private static String formHTML(String reactObj, String componentName, String componentJson) {
		String returnValue = String.format("<div data-component=\"%s\" data-props=\"%s\">%s</div></div>", componentName,
				componentJson, StringEscapeUtils.unescapeHtml4(reactObj));
		return returnValue;
	}

}
