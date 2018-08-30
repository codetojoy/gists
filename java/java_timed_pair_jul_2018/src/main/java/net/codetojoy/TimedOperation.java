
package net.codetojoy;

public class TimedOperation {
    public TimedResult execute(Operation operation) {
        long beginTime = System.currentTimeMillis();
        Object result = operation.execute();
        long elapsedTime = System.currentTimeMillis() - beginTime; 
        TimedResult timedResult = new TimedResult(result, elapsedTime);
        return timedResult;
    }
}
