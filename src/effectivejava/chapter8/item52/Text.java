package effectivejava.chapter8.item52;

/**
 * JAVA的LIST接口的REMOVE重载方法调用原理
 * https://www.cnblogs.com/cartooon/p/11645033.html
 */
public class Text {

//    public void remove(int index){
//        System.out.println("调用传参为int的remove方法");
//    }
//
//    public void remove(Integer object){
//        System.out.println("调用传参为Integer的remove方法");
//    }

    public void remove(Object object){
        System.out.println("调用传参为Object的remove方法");
    }

    public static void main(String[] args) {
        Text text = new Text();
        // 如果注释掉Integer的重载方法，就会调用Object参数的方法
        text.remove((Integer) 1);
    }
}