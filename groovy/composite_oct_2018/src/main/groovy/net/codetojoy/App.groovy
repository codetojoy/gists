package net.codetojoy

class App {
    def questionBuilder = new QuestionBuilder()
    def partitioner = new Partitioner()

    def go(def rows) {
        def questions = questionBuilder.build(rows)
        questions.each { q -> println q.toString() }
    }

    static void main(def args) {
        App app = new App()
        def rows = new Rows().getRows()
        app.go(rows)
        System.out.println("TRACER Ready.")
    }
}
