package effectivejava.chapter6.item36;

// Bit field enumeration constants – OBSOLETE!
// 位域枚举常量过时
public class Text1 {
    public static final int STYLE_BOLD          = 1 << 0;       //1
    public static final int STYLE_ITATIC        = 1 << 1;       //2
    public static final int STYLE_UNDERLINE     = 1 << 2;       //4
    public static final int STYLE_STRIKETHROUGH = 1 << 3;       //8
  
    //Parameter is bitwise OR of zero or more STYLE_ constants
    //参数是按位的，或者包含0个或多个样式常量
    public void applyStyles(int styles){
        // 根据styles值进行拆分，判断是哪些参数...
    };

    // Sample use
    public static void main(String[] args) {
        Text1 text = new Text1();
        text.applyStyles(STYLE_BOLD|STYLE_ITATIC);

        System.out.println(STYLE_BOLD|STYLE_ITATIC); // 3
    }   
}