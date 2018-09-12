
package net.codetojoy

import groovy.xml.*

class XmlGenerator { 

    // e.g. input (in file): hello(there: 5150) 
    // e.g. output         : <hello there="5150" />
    //
    def generate(def groovyFile) {
        def BUILDER = new File(groovyFile).getText()

        def writer = new StringWriter()
        def builder = new groovy.xml.MarkupBuilder(writer)

        def buffer = new StringBuilder()

        // buffer.append("import groovy.xml.*; \n") 
        // buffer.append("def writer = new StringWriter(); \n")
        // buffer.append("def builder = new MarkupBuilder(writer); \n")
        buffer.append("builder.${BUILDER}  ;\n")
        buffer.append("return writer.toString(); \n")
        // buffer.append("return XmlUtil.serialize(writer.toString()); \n")

        def map = ['builder' : builder, 'writer' : writer]
        def binding = new Binding(map)
        def shell = new GroovyShell(binding)
        def script = buffer.toString()

        def header = '<?xml version="1.0" encoding="UTF-8"?>\n'
        def resultStr = header + shell.evaluate(script)

        println "TRACER resultStr : " + resultStr

        return XmlUtil.serialize(resultStr)
    }
}

