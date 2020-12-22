package effectivejava.chapter2.item2.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 服务提供者注册类 
 *  
 * @author Administrator 
 *  
 */  
public class ServiceManager {  
  
    private ServiceManager() {  
  
    }  
  
    private static final Map<String, SubwayProviderInterface> providers = new ConcurrentHashMap<String, SubwayProviderInterface>();
  
    public static void registerProvider(String name, SubwayProviderInterface p) {  
        providers.put(name, p);  
    }  
  
    public static SubWayInterface getService(String name) {  
  
        SubwayProviderInterface p = providers.get(name);  
  
        if (p == null) {  
            throw new IllegalArgumentException(  
                    "No provider registered with name:" + name);  
        }  
  
        return p.getService();  
  
    }  
  
}  