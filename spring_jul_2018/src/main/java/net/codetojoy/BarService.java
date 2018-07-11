package net.codetojoy;

import org.springframework.transaction.config.SpringSpoof;

public class BarService {
    private FooService fooService;

    public void setFooService(FooService fooService) {
        this.fooService = fooService;
    }

    public FooService getFooService() {
        return fooService;
    }

    public void initialize() {
        System.out.println("TRACER hello from BarService.initialize() begin");
        new SpringSpoof().initialize();
        System.out.println("TRACER hello from BarService.initialize() end");
    }
}
