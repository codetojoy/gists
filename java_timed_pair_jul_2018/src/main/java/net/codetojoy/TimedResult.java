
package net.codetojoy;

public class TimedResult {
    private final Object result;
    private final long elapsedTime; 

    public TimedResult(Object result, long elapsedTime) {
        this.result = result;
        this.elapsedTime = elapsedTime;
    } 

    public Object getResult() { return result; }
    public long getElapsedTime()  { return elapsedTime; }
}
