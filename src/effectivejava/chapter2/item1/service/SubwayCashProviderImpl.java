package effectivejava.chapter2.item1.service;

/**
 * 服务提供者实现类 
 * @author Administrator 
 * 
 */  
public class SubwayCashProviderImpl implements SubwayProviderInterface {

    static {
        ServiceManager.registerProvider("现金", new SubwayCashProviderImpl());
    }

    public SubWayInterface getService() {
        return new SubWayCashImpl();
    }  
  
} 
