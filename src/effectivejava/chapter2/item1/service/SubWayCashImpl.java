package effectivejava.chapter2.item1.service;

/**
 * 现金地铁进出服务实现
 * @author Administrator 
 * 
 */  
public class SubWayCashImpl implements SubWayInterface {
  
    public boolean in() {  
        System.out.println("通过现金进入地铁");
        /** 
         * 进行一些处理，然后返回是否放行 
         */  
        return false;  
    }  
  
    public boolean out() {  
        System.out.println("通过现金出地铁");
        /** 
         * 进行一些处理，然后返回是否放行 
         */  
        return false;  
    }  
  
}  
