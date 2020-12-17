package effectivejava.chapter10.item74;

/**
 * 第74条：每个方法抛出的所有异常都要建立文档
 */
public class IndexOutOfBoundsException extends RuntimeException {
    private final int lowerBound;
    private final int upperBound;
    private final int index;

    /**
     * Constructs an IndexOutOfBoundsException. 构造一个IndexOutOfBoundsException
     *
     * @param lowerBound the lowest legal index value 最低法律指数值
     * @param upperBound the highest legal index value plus one 最高法律指数值加1
     * @param index      the actual index value 实际指标值
     */
    public IndexOutOfBoundsException(int lowerBound, int upperBound, int index) {
        // Generate a detail message that captures the failure
        // 生成捕获失败的详细消息
        super(String.format(
                "Lower bound: %d, Upper bound: %d, Index: %d",
                lowerBound, upperBound, index));

        // Save failure information for programmatic access
        // 保存故障信息以进行编程访问
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.index = index;
    }
}
