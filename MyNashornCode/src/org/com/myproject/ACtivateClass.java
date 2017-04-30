package org.com.myproject;

import org.com.myproject.NSImpl;

//This is the Executor class to run all Nashorn files, just change the import types and pass correct params

public class ActivateClass {
	public static NSImpl ns = new NSImpl();
	public static String params;
	
	public static void main(String[] args) throws Exception {
	params = "CKJ World";
	ns.activate();
	System.out.println(ns.evaluateComponent(params).toString());
	}
}
