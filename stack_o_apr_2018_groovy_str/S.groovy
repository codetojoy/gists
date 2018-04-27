

class A {
    static def template = 'token = ${token}' 
}

A.metaClass.mySubstitution = { def val ->
    def engine = new groovy.text.SimpleTemplateEngine()
    engine.createTemplate(delegate.template).make('token':val).toString()
}

class B {
    static def tokenRef = 'B static field'

    def parseGString(def src) {
        src.mySubstitution(tokenRef)
    }

    def parseString(def src) {
        def tokenValue = 'B local var'
        src.mySubstitution(tokenValue)
    }
}

def a = new A()
def b = new B()
assert 'token = B static field' == b.parseGString(a)
assert 'token = B local var' == b.parseString(a)
