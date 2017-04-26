# Nashorn Code
Here, I'm covering few of live examples of implmenting Nashorn.
Couple of topics:
1. Implmenting Nashorn Example - the best practice in terms of performance (NSimpl.java)
2. Using Nashorn for rendering React JS (NSReact.java)
3. Multithreaded Nashorn prompts (This is under progress, NSThread.java)

In examples, we're referring FetchFile.getInput method to read script file. Here is a code snippet for that method as well:
InputStream getInput(String path) {
    InputStream in = getClass().getClassLoader().getResourceAsStream(path);
    return in;
}

I dont' prefer to write multiple classes & use them as object, I prefer stand alone examples which is easier to understand and use. You can break each of these java files into multiple classes as per your requirement to make them easily accessible as per required.

Refer following to know more about Nashorn's methods & variables:
https://docs.oracle.com/javase/8/docs/jdk/api/nashorn/jdk/nashorn/api/scripting/NashornScriptEngineFactory.html
https://docs.oracle.com/javase/8/docs/jdk/api/nashorn/jdk/nashorn/api/scripting/NashornScriptEngine.html#NASHORN_GLOBAL
