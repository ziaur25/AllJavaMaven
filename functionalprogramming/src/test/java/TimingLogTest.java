
import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

import static org.junit.Assert.*;

public class TimingLogTest {



    @Test
    public void testTimed() {
        final String description = "carrot";
        AtomicReference<String> output = new AtomicReference<>("");
        TimingLog.timed(description,output::set,
        () -> "carrot");
        assert(output.get().contains(description));
    }

}
