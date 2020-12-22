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
        Class.forName("effectivejava.chapter2.item2.service.SubwayProviderImpl");
        SubWayInterface swi = ServiceManager.getService("一卡通");  
        swi.in();  
        swi.out();  
    }  
} 
/* Output:
通过一卡通进入地铁
通过一卡通出地铁
 */