
class Transformer {
    static def engine = new groovy.text.SimpleTemplateEngine()
    static String myReplace(String str, String b, String c) {
        println "TRACER str: '${str}' b: '${b}' c: '${c}'"
        engine.createTemplate(str).make((b):c).toString()
    }
}

class A {
    static def template = 'token = ${token}' 
}

class B {
    static def tokenRef = 'B static field'

    def parseGString(def template) {
        template.myReplace('token', tokenRef)
    }

    def parseString(def template) {
        def tokenValue = 'B local var'
        template.myReplace('token', tokenValue)
    }
}

use (Transformer) {
    assert 'token = B static field' == new B().parseGString(A.template)
    assert 'token = B local var' == new B().parseString(A.template)
}
