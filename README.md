# Nashorn Code
Here, I'm covering few of live examples of implmenting Nashorn.
Couple of topics:
1. Implmenting Nashorn Example - the best practice in terms of performance (NSimpl.java)
2. Using Nashorn for rendering React JS (NSReact.java)
3. Nashorn Service where you can configure js files as per your project (NSConfig.java)
4. Multithreaded Nashorn prompts (This is under progress, NSThread.java)

In examples, we're referring FetchFile.getInput method to read script file. Here is a code snippet for that method as well:
InputStream getInput(String path) {
    InputStream in = getClass().getClassLoader().getResourceAsStream(path);
    return in;
}

I don't prefer to write multiple classes & use them as object, I prefer stand alone examples which is easier to understand and use. You can break each of these java files into multiple classes as per your requirement to make them easily accessible as per required.

Refer following to know more about Nashorn's methods & variables:
https://docs.oracle.com/javase/8/docs/jdk/api/nashorn/jdk/nashorn/api/scripting/NashornScriptEngineFactory.html
https://docs.oracle.com/javase/8/docs/jdk/api/nashorn/jdk/nashorn/api/scripting/NashornScriptEngine.html#NASHORN_GLOBAL 
https://docs.oracle.com/javase/8/docs/jdk/api/nashorn/index.html?jdk/nashorn/api/scripting/package-summary.html
https://www.tutorialspoint.com/java8/java8_nashorn_java_script.htm


Nashorn is a JavaScript engine developed in the Java programming language by Oracle. It is based on the Da Vinci Machine (JSR 292) and has been released with Java 8.
It is one of the javascript engine available by default in JDK 1.8 & later used to run javascripts from JAVA code.
By default we've rhino engine for execution of any kind of javascript methods, while rhino is not compatible with many new approaches like react & hence to rectify such issues we used nashorn which is almost 10 times faster than rhino as it directly compiles the code in memory and passes the bytecode to JVM.


Nashorn's goal is to implement a lightweight high-performance JavaScript runtime in Java with a native JVM. This Project intends to enable Java developers embedding of JavaScript in Java applications via JSR-223 and to develop free standing JavaScript applications using the jrunscript command-line tool.


jjs
For Nashorn engine, JAVA 8 introduces a new command line tool, jjs, to execute javascript codes at console.

Interpreting js File
Create and save the file sample.js in c:\> JAVA folder.

sample.js
print('Hello World!');
Open console and use the following command.

$jjs sample.js
It will produce the following output:

Hello World!
jjs in Interactive Mode
Open the console and use the following command.

$jjs
jjs> print("Hello, World!")
Hello, World!
jjs> quit()
>>
Pass Arguments
Open the console and use the following command.

$jjs -- a b c
jjs> print('letters: ' +arguments.join(", "))
letters: a, b, c
jjs>



Calling JavaScript from Java
Using ScriptEngineManager, JavaScript code can be called and interpreted in Java.

Example
Create the following Java program using any editor of your choice in, say, C:\> JAVA.

Java8Tester.java
import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import javax.script.ScriptException;

public class Java8Tester {
   public static void main(String args[]){
   
      ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
      ScriptEngine nashorn = scriptEngineManager.getEngineByName("nashorn");
		
      String name = "Mahesh";
      Integer result = null;
      
      try {
         nashorn.eval("print('" + name + "')");
         result = (Integer) nashorn.eval("10 + 2");
         
      }catch(ScriptException e){
         System.out.println("Error executing script: "+ e.getMessage());
      }
      
      System.out.println(result.toString());
   }
}
Verify the Result
Compile the class using javac compiler as follows −

$javac Java8Tester.java
Now run the Java8Tester as follows −

$java Java8Tester
It should produce the following result −

Mahesh
12
Calling Java from JavaScript
The following example explains how to import and use Java classes in java script −

sample.js
var BigDecimal = Java.type('java.math.BigDecimal');

function calculate(amount, percentage) {

   var result = new BigDecimal(amount).multiply(
   new BigDecimal(percentage)).divide(new BigDecimal("100"), 2, BigDecimal.ROUND_HALF_EVEN);
   
   return result.toPlainString();
}

var result = calculate(568000000000000000023,13.9);
print(result);
Open the console and use the following command.

$jjs sample.js
It should produce the following output −

78952000000000000003.20
