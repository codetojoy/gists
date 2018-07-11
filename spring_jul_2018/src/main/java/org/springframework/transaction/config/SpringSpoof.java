package org.springframework.transaction.config;

public class SpringSpoof {

    // This won't work on Java 1.8 without changes to org.springframework.core.JdkVersion
    public void initialize() {
        System.out.println("TRACER Spring spoof start ...");
        Object obj = TxNamespaceUtils.getAnnotationTransactionAttributeSourceClass();
        System.out.println("TRACER Spring spoof OK ... obj: " + obj);
    }
}
