
println "Iterate a list using closures"
def closure = {[1,2,3].each ({ item -> print "${item} " })}
closure.call()
println ""

//Or you could do, for each element in the list, call the closure
// And you don't have to assign the closure to any variable. 
[1,2,3].each ({ item -> print "${item} " })
println ""

println "Iterate a map using closures"
["k1":"v1", "k2":"v2"].each {key, value -> println key + "=" + value}
