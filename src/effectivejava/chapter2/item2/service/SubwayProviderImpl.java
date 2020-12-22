package effectivejava.chapter2.item2.service;

/**
 * 服务提供者实现类 
 * @author Administrator 
 * 
 */  
public class SubwayProviderImpl implements SubwayProviderInterface {  
  
    static {  
        ServiceManager.registerProvider("一卡通", new SubwayProviderImpl());  
    }  
  
    public SubWayInterface getService() {  
        return new SubWayImpl();  
    }  
  
} 
