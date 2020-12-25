package effectivejava.chapter2.item1.service;

/**
 * 服务提供者实现类 
 * @author Administrator 
 * 
 */  
public class SubwayCardProviderImpl implements SubwayProviderInterface {
  
    static {  
        ServiceManager.registerProvider("一卡通", new SubwayCardProviderImpl());
    }

    // 委托，持有
    public SubWayInterface getService() {  
        return new SubWayCardImpl();
    }  
  
} 
