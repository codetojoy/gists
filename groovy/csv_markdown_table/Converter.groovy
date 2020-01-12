
@Grab('com.xlson.groovycsv:groovycsv:1.3')

import static com.xlson.groovycsv.CsvParser.parseCsv

/*
| Tables        | Are           | Cool  |
| ------------- |:-------------:| -----:|
| col 3 is      | right-aligned | $1600 |
*/

def generateHeader = { def row, def rowMap, def numColumns -> 
    def buffer = new StringBuilder() 
    buffer.append("|")

    // e.g. | Tables        | Are           | Cool  |
    for (i in 0..numColumns-1) {
        def rowValue = row.getAt(i)
        def thisColumn = rowMap.find { k, v -> v == rowValue }.key
        buffer.append(" ${thisColumn} | ")  
    }

    buffer.append("\n")

    // e.g. | ------------- |:-------------:| -----:|
    buffer.append("|")
    for (i in 0..numColumns-1) {
        buffer.append(":------:|")
    }

    buffer.append("\n")

    return buffer.toString()
}

def generateRow = { def row, def numColumns -> 
    def buffer = new StringBuilder() 
    buffer.append("|")

    // e.g. | col 3 is      | right-aligned | $1600 |
    for (i in 0..numColumns-1) {
        def value = row.getAt(i)
        buffer.append(" ${value} |")
    }

    buffer.append("\n")

    return buffer.toString()
}

def parseText = { def text ->
    def data = parseCsv text
    def numColumns = 0
    def isHeaderGenerated = false

    data.each { def row ->
        def rowMap = row.toMap()
        numColumns = rowMap.keySet().size()

        if (! isHeaderGenerated) {
            def outputHeader = generateHeader(row, rowMap, numColumns)
            print outputHeader             
            isHeaderGenerated = true 
        }

        def outputRow = generateRow(row, numColumns)
        print outputRow 
    } 
}

// ---------- main

try {
    if (args.length < 1) {
        println "Check usage"
        System.exit(-1)
    }

    def inFile = new File(args[0])
    def inFileText = inFile.getText()
    parseText(inFileText)
} catch (Exception ex) {
    System.err.println "caught exception ex: " + ex.message
}
