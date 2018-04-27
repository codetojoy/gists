class A {
    static def strRefPlaceHolder = 'token = ${token}'
}

class B {
    static def tokenRef = 'B static field'
    def engine = new groovy.text.SimpleTemplateEngine()

    def parseGString(def str) {
        engine.createTemplate(str).make([token:tokenRef]).toString()
    }

    def parseString(def str) {
        def tokenValue = 'B local var'
        engine.createTemplate(str).make([token:tokenValue]).toString()
    }
}

assert 'token = B static field' == new B().parseGString(A.strRefPlaceHolder)
assert 'token = B local var' == new B().parseString(A.strRefPlaceHolder)
