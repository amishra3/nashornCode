package org.com.helper;
import java.io.InputStream;
import javax.jcr.Node;
import javax.jcr.PathNotFoundException;
import javax.jcr.RepositoryException;
import javax.jcr.ValueFormatException;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;


public class FetchFile {
	public static InputStream getInput(String path) throws ValueFormatException, PathNotFoundException, RepositoryException {
		Resource resource ;
		ResourceResolver resourceResolver = null ;		
		resource = resourceResolver.getResource(path);
		Node jcnode = null;
		try {
			jcnode = resource.adaptTo(Node.class).getNode("jcr:content");
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		InputStream in = jcnode.getProperty("jcr:data").getBinary().getStream();			
		return in;
	}
}
