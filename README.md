# Nashorn Code

Currently, we've multiple documents online on nashorn but I couldn't find any single document from where I could have got everything as per my requirement. Then one day a thought to bring varieties of nashorn related code came to my mind while I was working on AEM project which required Nashorn integration because we were using React and rhino is not compatible with React, while AEM always by default uses rhino as javascript engine. 
So, all my examples are related somewhere with AEM as well as standalone java too.
Please provide your feedback or any thought to make it better.

Couple of topics:
1. Implmenting Nashorn Example - the best practice in terms of performance (NSimpl.java)
2. Using Nashorn for rendering React JS (NSReact.java)
3. Nashorn Service where you can configure js files as per your project (NSConfig.java)
4. Multithreaded Nashorn prompts (This is under progress, NSThread.java)

In examples, we're referring FetchFile.getInput method to read script file. Here is a code snippet for that method as well:
```Java
InputStream getInput(String path) {
    InputStream in = getClass().getClassLoader().getResourceAsStream(path);
    return in;
}
```
I don't prefer to write multiple classes & use them as object, I prefer stand alone examples which is easier to understand and use. You can break each of these java files into multiple classes as per your requirement to make them easily accessible as per required. 

Nashorn is a JavaScript engine developed in the Java programming language by Oracle. 
It is one of the javascript engine available by default in JDK 1.8 & later used to run javascripts from JAVA code.
By default we've rhino engine for execution of any kind of javascript methods, while rhino is not compatible with many new approaches like react & hence to rectify such issues we used nashorn which is almost 10 times faster than rhino as it directly compiles the code in memory and passes the bytecode to JVM.

Nashorn's goal is to implement a lightweight high-performance JavaScript runtime in Java with a native JVM. This Project intends to enable Java developers embedding of JavaScript in Java applications via JSR-223 and to develop free standing JavaScript applications using the jrunscript command-line tool.


# jjs
For Nashorn engine, JAVA 8 introduces a new command line tool, jjs, to execute javascript codes at console.
sample.js
```js
print('Welcome to CKJ world');
```
It will produce the following output:
Welcome to CKJ World


# JavaScript from Java
Using ScriptEngineManager, JavaScript code can be called and interpreted in Java.

Example
```Java
public class test {
   public static void main(String args[]){
   
      ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
      ScriptEngine nashorn = scriptEngineManager.getEngineByName("js");		
      String name = "CKJ World";   
      try {
         nashorn.eval("print('" + name + "')");         
      }catch(ScriptException e){
         System.out.println("Error executing script: "+ e.getMessage());
      }      
      System.out.println(result.toString());
   }
  }
```  

It should produce the following result −
CKJ World


Refer following to know more about Nashorn's methods & variables:
https://docs.oracle.com/javase/8/docs/jdk/api/nashorn/jdk/nashorn/api/scripting/NashornScriptEngineFactory.html
https://docs.oracle.com/javase/8/docs/jdk/api/nashorn/jdk/nashorn/api/scripting/NashornScriptEngine.html#NASHORN_GLOBAL 
https://docs.oracle.com/javase/8/docs/jdk/api/nashorn/index.html?jdk/nashorn/api/scripting/package-summary.html
https://www.tutorialspoint.com/java8/java8_nashorn_java_script.htm
