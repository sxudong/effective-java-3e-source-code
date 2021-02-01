package effectivejava.chapter7.item47;

import java.util.stream.Stream;

public class Test {

    public static void main(String[] args) {
        Stream<ProcessHandle> allProcesses = ProcessHandle.allProcesses();

        // Hideous workaround to iterate over a stream 迭代流的解决方法
        for(ProcessHandle ph : ((Iterable<ProcessHandle>)
                                ProcessHandle.allProcesses()::iterator)) {
            // Process the process
        }

        for(ProcessHandle ph : Adapters.iterableOf(ProcessHandle.allProcesses())) {
            // Process the process
        }
    }
}
