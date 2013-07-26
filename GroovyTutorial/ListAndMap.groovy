println "Working with lists"
def list= [1, 2, 'Hello', "Hello", new java.util.Date()]
println list
assert list.size() == 5
assert list.get(2) == "Hello"
assert list[2] == 'Hello'

println "Working with maps"
def map = ['name':'James', "location":"London"]
println map
assert map.size() == 2
assert map.get("name") == 'James'
assert map['location'] == "London"
