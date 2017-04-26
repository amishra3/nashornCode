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
import org.apache.felix.scr.annotations.Service;
import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.PropertyUnbounded;
import org.apache.felix.scr.annotations.Reference;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.commons.json.JSONObject;
import org.osgi.framework.BundleContext;
import org.osgi.service.component.ComponentContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
	
	
	private static ResourceResolverFactory resourceResolverFactory;
	private static BundleContext bundleContext;

	@Reference
	private ResourceResolverFactory resourceResolverFactoryInit;

	private static final String PROJECT = "project";
	private static final String JS = "ssrjs";

	@Property(label = "Project Name", value = "My Project", description = "Project Name ")
	public static String SITE_ROOT_PATH = "project";

	@Property(unbounded = PropertyUnbounded.ARRAY, label = "Server side Javascript", value = {"script.js", "init.js"}, description = "Provide CRX path of server side javascript file")
	public static String JS_FILE = "ssrjs";

	private String [] multiString; 
	
	public String[] getMultiString()
    {
        return this.multiString;
    }

	protected String getSiteRootPath() {
		return SITE_ROOT_PATH;
	}


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
				for (int i = 0; i < this.multiString.length; i++) {
					file =FetchFile.getInput(this.multiString[i]); 
					if (null != file) {
						Reader reader = new InputStreamReader(file, "UTF-8");
						this.nashorn.eval(reader);					
					}
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
