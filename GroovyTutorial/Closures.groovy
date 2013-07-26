// Note how $ is used to get the value of param inside a String
def closure = {param -> println("Hello ${param}!")}
closure.call("World")

//Closure with 2 parameters and multiple line body
def closure2 = {greeting, item -> print(greeting);
                                  print(" ")
                                  print(item);
                                  print("!")
                                  println();
               }
//It can be called without the call keyword
closure2("Hello","World")

//If no parameter is defined, there is a default one called "it"
def closure3 = {println "Hello " + it + "!"}
closure3.call("World")
