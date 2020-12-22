package effectivejava.chapter2.item2.service;

/**
 * 客户端测试类 
 * @author Administrator 
 * 
 */  
public class Test {  
  
    /** 
     * @param args 
     * @throws ClassNotFoundException  
     */  
    public static void main(String[] args) throws ClassNotFoundException {  
          
        Class.forName("cn.netjava.cgl.subway.SubwayProviderImpl");  
        SubWayInterface swi = ServiceManager.getService("一卡通");  
        swi.in();  
        swi.out();  
    }  
} 
