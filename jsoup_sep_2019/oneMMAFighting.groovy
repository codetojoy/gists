
@Grab('com.xlson.groovycsv:groovycsv:1.0')
import static com.xlson.groovycsv.CsvParser.parseCsv

@Grab('org.jsoup:jsoup:1.9.1')
import org.jsoup.*
import org.jsoup.nodes.*
import org.jsoup.select.*

class Web {
    def picks = new HashSet()
    def SELECTOR = 'b:containsOwn(Pick)'

    def Web(def url) {
        try {
             def delay = 500
             try { Thread.sleep(delay) } catch (Exception ex) {} 
             def doc = Jsoup.connect(url).get()
             def nodes = doc.select(SELECTOR)
             nodes.each { def node ->
                 def pick = node.parent().text()
                 def matcher = (pick =~ /^Pick: (\w*)?.*$/)
                 if (matcher.matches()) {
                     picks << matcher[0][1]
                 }
             }
        } catch (Exception ex) {
            System.err.println("cannot read: " + url + " " + ex.message)
        }
    }
}

// ------- MAIN 

def urls = [ 
"http://www.mmafighting.com/2016/5/7/11616998/ufc-fight-night-87-predictions",
]

def map = [:]

urls.each { def url ->
    def web = new Web(url)
    map[url] = web.picks
}

println map.inspect()
