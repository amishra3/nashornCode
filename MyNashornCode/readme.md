Nashorn Code

Here, I'm covering few of live examples of implmenting Nashorn. Couple of topics:

Implmenting Nashorn Example - the best practice in terms of performance (NSimpl.java)
Using Nashorn for rendering React JS (NSReact.java)
Nashorn Service where you can configure js files as per your project (NSConfig.java)
Multithreaded Nashorn prompts (This is under progress, NSThread.java)

Start in sequence, in code base you'll find ActivateClass.java which is used to execute above mentioned java classes, a sample script.js is used for that class. When you'll run ACtivateClass.java you'll find output:

#Welcome to CKJ World

Instead of script.js, you can use any other JS files and call their methods with params to get proper output.
