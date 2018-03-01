
@Grab(group='joda-time', module='joda-time', version='2.9.9')

import org.joda.time.*
import org.joda.time.format.*

def convert = { def inDate ->
    def inFormat = DateTimeFormat.forPattern('dd-MMM-yyyy')
    def outFormat = DateTimeFormat.forPattern('yyyy-MM-dd')
    def dateTime = inFormat.parseDateTime(inDate)
    def result = outFormat.print(dateTime)
    return result 
}

// ------------- main 

def d = '01-OCT-2015'
println convert(d) 
