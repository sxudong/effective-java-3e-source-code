package effectivejava.chapter11.item80;

import java.util.concurrent.*;

public class Test2 {

    // 高并发编程-ExecutorCompletionService深入解析
    // https://cloud.tencent.com/developer/article/1444259
    public static void test() throws InterruptedException, ExecutionException {
        Executor executor = Executors.newFixedThreadPool(3);
        CompletionService<String> service = new ExecutorCompletionService<>(executor);
        for (int i = 0 ; i < 5 ;i++) {
            int seqNo = i;
            service.submit(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    return "HelloWorld-" + seqNo + "-" + Thread.currentThread().getName();
                }
            });
        }
        for (int j = 0 ; j < 5; j++) {
            System.out.println(service.take().get());
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        test();
    }
}
/* Output:
HelloWorld-2-pool-1-thread-3
HelloWorld-0-pool-1-thread-1
HelloWorld-1-pool-1-thread-2
HelloWorld-3-pool-1-thread-3
HelloWorld-4-pool-1-thread-1
 */