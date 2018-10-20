package net.codetojoy

class App {
    def go() {
        def rows = new Rows().getRows()
        def builder = new Builder()
        def questions = builder.build(rows)
        questions.each { q ->
            println q.toString()
        }
    }

    static void main(def args) {
        App app = new App()
        app.go()
        System.out.println("TRACER Ready.")
    }
}
