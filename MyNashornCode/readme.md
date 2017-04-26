Nashorn Code

Here, I'm covering few of live examples of implmenting Nashorn. Couple of topics:

Implmenting Nashorn Example - the best practice in terms of performance (NSimpl.java)
Using Nashorn for rendering React JS (NSReact.java)
Nashorn Service where you can configure js files as per your project (NSConfig.java)
Multithreaded Nashorn prompts (This is under progress, NSThread.java)
In examples, we're referring FetchFile.getInput method to read script file. Here is a code snippet for that method as well: InputStream getInput(String path) { InputStream in = getClass().getClassLoader().getResourceAsStream(path); return in; }

I don't prefer to write multiple classes & use them as object, I prefer stand alone examples which is easier to understand and use. You can break each of these java files into multiple classes as per your requirement to make them easily accessible as per required.

Refer following to know more about Nashorn's methods & variables: https://docs.oracle.com/javase/8/docs/jdk/api/nashorn/jdk/nashorn/api/scripting/NashornScriptEngineFactory.html https://docs.oracle.com/javase/8/docs/jdk/api/nashorn/jdk/nashorn/api/scripting/NashornScriptEngine.html#NASHORN_GLOBAL https://docs.oracle.com/javase/8/docs/jdk/api/nashorn/index.html?jdk/nashorn/api/scripting/package-summary.html https://www.tutorialspoint.com/java8/java8_nashorn_java_script.htm

Nashorn is a JavaScript engine developed in the Java programming language by Oracle. It is based on the Da Vinci Machine (JSR 292) and has been released with Java 8. It is one of the javascript engine available by default in JDK 1.8 & later used to run javascripts from JAVA code. By default we've rhino engine for execution of any kind of javascript methods, while rhino is not compatible with many new approaches like react & hence to rectify such issues we used nashorn which is almost 10 times faster than rhino as it directly compiles the code in memory and passes the bytecode to JVM.
