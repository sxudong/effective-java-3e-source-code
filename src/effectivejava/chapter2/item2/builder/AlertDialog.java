package effectivejava.chapter2.item2.builder;

// 非Android中的AlertDialog，便于说明问题，举个例子
public class AlertDialog {
    private int width;
    private int height;
    private String title;
    private String confirmText;
    private String denyText;

    // private
    private AlertDialog(){}

    // Builder中使用
    protected AlertDialog(Builder b){
        width = b.width;
        height = b.height;
        // 参数校验
        if(width==0 || height==0)
            throw new RuntimeException("size must be set");
    }

    // 构造者（没有写Builder构造器来设置必需参数，在AlertDialog中进行了参数校验）
    public static class Builder {
        private int width;
        private int height;
        private String title;
        private String confirmText;
        private String denyText;

        // 注意：返回的Builder。
        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }
        // 其他set...

        public AlertDialog build(){
            return new AlertDialog(this);
        }
    }

    public static void main(String[] args) {
        new AlertDialog.Builder().setTitle("提示").build(); //会成功抛出异常
    }
} 