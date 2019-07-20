import java.util.Date;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.logging.Logger;

public class TimingLog {

    Logger logger = Logger.getLogger("TimingLog");

    public static <T> T timed(String desc,
                              Supplier<T> code){
        //Consumer<String> defaultOutput = System.out::println;
        Consumer<String> defaultOutput = s ->{};

        return timed(desc, defaultOutput, code);
    }

    public static <T> T timed(String desc,
                              Consumer<String> output,
                              Supplier<T> code){
        final Date before = new Date();
        T result = code.get();
        final Long duration = new Date().getTime() - before.getTime();
        output.accept(desc+" took "+duration+" millisec");
        return result;
    }
}
