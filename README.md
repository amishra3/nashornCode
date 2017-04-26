# Nashorn Code
Here, I'm covering few of live examples of implmenting Nashorn.
Couple of topics:
1. Implmenting Nashorn Example - the best practice in terms of performance
2. Using Nashorn for rendering React JS
3. Multithreaded Nashorn prompts (This is under progress)

In examples, we're referring FetchFile.getInput method to read script file. Here is a code snippet for that method as well:
InputStream getInput(String path) {
    InputStream in = getClass().getClassLoader().getResourceAsStream(path);
    return in;
}

Refer following to know more about Nashorn's methods & variables:
https://docs.oracle.com/javase/8/docs/jdk/api/nashorn/jdk/nashorn/api/scripting/NashornScriptEngineFactory.html
https://docs.oracle.com/javase/8/docs/jdk/api/nashorn/jdk/nashorn/api/scripting/NashornScriptEngine.html#NASHORN_GLOBAL
