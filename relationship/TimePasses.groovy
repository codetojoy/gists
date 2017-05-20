
// "I'll never smile again", Ruth Lowe - https://en.wikipedia.org/wiki/Ruth_Lowe 

@Grab(group='joda-time', module='joda-time', version='2.9.9')

import org.joda.time.*

def start = new DateTime(2012, 2, 16, 19, 00)
def end = new DateTime(2014, 11, 24, 19, 00)

println start
println end

def numDays = Days.daysBetween(start.toLocalDate(), end.toLocalDate()).getDays()
println "duration in : " + numDays

def now = new DateTime()
numDays = Days.daysBetween(end.toLocalDate(), now.toLocalDate()).getDays()
println "duration since : " + numDays

