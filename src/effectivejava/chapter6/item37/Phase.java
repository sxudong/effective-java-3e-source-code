package effectivejava.chapter6.item37;
import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

// Using a nested EnumMap to associate data with enum pairs - (Pages 174-5)
public enum Phase {
    // 固体，液体，气体
    SOLID, LIQUID, GAS;
    public enum Transition {
        MELT(SOLID, LIQUID), FREEZE(LIQUID, SOLID), // 熔化（固体，液体）  冻结（液体，固体）
        BOIL(LIQUID, GAS), CONDENSE(GAS, LIQUID),
        SUBLIME(SOLID, GAS), DEPOSIT(GAS, SOLID);

//        // Adding a new phase (Page 175)
//        SOLID, LIQUID, GAS, PLASMA;
//        public enum Transition {
//            MELT(SOLID, LIQUID), FREEZE(LIQUID, SOLID),
//            BOIL(LIQUID, GAS), CONDENSE(GAS, LIQUID),
//            SUBLIME(SOLID, GAS), DEPOSIT(GAS, SOLID),
//            IONIZE(GAS, PLASMA), DEIONIZE(PLASMA, GAS);

        private final Phase from; // 起始
        private final Phase to;   // 目标
        Transition(Phase from, Phase to) {
            this.from = from;
            this.to = to;
        }

        // Initialize the phase transition map
        // 第2版本，显式迭代来初始化阶段过渡映射，代码更加繁琐，但是易于理解。
        private static final Map<Phase, Map<Phase, Transition>> m =
                new EnumMap<Phase, Map<Phase, Transition>>(Phase.class);
        static {
            for (Phase p : Phase.values())
                m.put(p, new EnumMap<Phase, Transition>(Phase.class));
            for (Transition trans : Transition.values())
                m.get(trans.from).put(trans.to, trans); // 往EnumMap中添加元素
        }
        // 第3版本
//        private static final Map<Phase, Map<Phase, Transition>> m = Stream.of(values()).collect(groupingBy(
//                t -> t.from,                      // 起始
//                () -> new EnumMap<>(Phase.class),
//                toMap(t -> t.to,                  // 目标
//                        t -> t,                   // Transition
//                        (x, y) -> y,
//                        () -> new EnumMap<>(Phase.class))));
//
        public static Transition from(Phase from, Phase to) {
            return m.get(from).get(to);
        }
    }

    // Simple demo program - prints a sloppy table
    public static void main(String[] args) {
        System.out.println(Transition.m);
        for (Phase src : Phase.values()) {
            for (Phase dst : Phase.values()) {
                Transition transition = Transition.from(src, dst);
                if (transition != null)
                    System.out.printf("%s to %s : %s %n", src, dst, transition);
            }
        }
    }
}
/* Output:
{SOLID={LIQUID=MELT, GAS=SUBLIME}, LIQUID={SOLID=FREEZE, GAS=BOIL}, GAS={SOLID=DEPOSIT, LIQUID=CONDENSE}}
SOLID to LIQUID : MELT
SOLID to GAS : SUBLIME
LIQUID to SOLID : FREEZE
LIQUID to GAS : BOIL
GAS to SOLID : DEPOSIT
GAS to LIQUID : CONDENSE
 */