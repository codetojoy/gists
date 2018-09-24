
package net.codetojoy

class App {
    static void main(String[] args) {
        def addressDao = new AddressDao();
        def states = addressDao.getStates();

        states.each { println it } 

        println "Ready."
    }
}
