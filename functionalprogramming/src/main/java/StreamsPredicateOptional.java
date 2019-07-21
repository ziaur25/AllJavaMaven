import com.google.common.collect.Iterables;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class StreamsPredicateOptional {

    final static String[] foods = {"bright oranges", "juicy plums", "super brocolli", ""};

    public static final Predicate<String> NON_EMPTY = s -> !s.isEmpty();


    public static String summarize(final String[] descriptions){

        return Arrays.asList(descriptions).stream()
                // .peek(s-> System.out.println("filter:"+s))   //debug
                .filter(NON_EMPTY)
                //.peek(s-> System.out.println("map:"+s))       //debug
                .map(lastword)
                //.peek(s-> System.out.println("reduce:"+s))    //debug
                .reduce(joinOn(" & "))
                .orElse("");

    }

    private static BinaryOperator<String> joinOn(String connector) {
        return (allsofar, nextElement) -> allsofar + connector + nextElement;
    }

    private static BinaryOperator<String> chooseLast = (all, next) -> next;

    private static Function<String, String> lastword =
            phrase -> Arrays.asList(phrase.split(" ")).stream()
            .reduce(chooseLast).orElse("");



    public static void main(String[] args) {
        final String summary = summarize(foods);
        System.out.println(summary);
    }

}
