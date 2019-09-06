
@Grab('com.xlson.groovycsv:groovycsv:1.0')
import static com.xlson.groovycsv.CsvParser.parseCsv

@Grab('org.jsoup:jsoup:1.9.1')
import org.jsoup.*
import org.jsoup.nodes.*
import org.jsoup.select.*

class WikiWeb {
    def winners = new HashSet()
    def SELECTOR = 'td:matches(def)'

    def WikiWeb(def url) {
        try {
             def delay = 500
             try { Thread.sleep(delay) } catch (Exception ex) {} 
             def doc = Jsoup.connect(url).get()
             def defNodes = doc.select(SELECTOR)
             defNodes.each { def defNode ->
                 def parent = defNode.parent()
                 def nodes = parent.select("td a")
                 def index = 0
                 nodes.each { def node ->
                     if (node.text().indexOf(/\[/) == -1) {
                         if (index % 2 == 0) {
                            winners << node.text()
                           index++
                         }
                     }
                 }
            }
        } catch (Exception ex) {
            System.err.println("cannot read: " + url + " " + ex.message)
        }
    }
}

    
// ------- MAIN 

def urls = [ 
"https://en.wikipedia.org/wiki/UFC_Fight_Night:_Overeem_vs._Arlovski",
]

def map = [:]

urls.each { def url ->
    def wikiWeb = new WikiWeb(url)
    map[url] = wikiWeb.winners
}

println map.inspect()
