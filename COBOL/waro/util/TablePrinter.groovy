
def d = [:]

new File("../src/out.log").eachLine { line ->
    def regex = /deck.*(\d\d).*=.(\d\d)/
    def matcher = line =~ regex
    if (matcher.matches()) {
        def key = matcher[0][1] as String
        def value = matcher[0][2] as String
        d[key] = value 
    }
}

println "KY"    + " " + "Ba"    + " " + "Ch"    + " " + "Mo"
println "----"
println d['01'] + " " + d['05'] + " " + d['09'] + " " + d['13'] 
println d['02'] + " " + d['06'] + " " + d['10'] + " " + d['14'] 
println d['03'] + " " + d['07'] + " " + d['11'] + " " + d['15'] 
println d['04'] + " " + d['08'] + " " + d['12'] + " " + d['16'] 
 
