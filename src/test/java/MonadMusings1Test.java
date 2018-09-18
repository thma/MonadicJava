import org.junit.Test;

import java.util.Optional;

public class MonadMusings1Test {


    public Optional<Integer> optionalAdd(Optional<Integer> val1, Optional<Integer> val2) {
        return
                val1.flatMap(first  ->
                val2.flatMap(second ->
                        Optional.of(first + second)));
    }

    @Test
    public void testOptional() {
        Optional<Integer> value = optionalAdd(Optional.of(11), Optional.of(7));

        System.out.println(value);
    }

    @Test
    public void testWrap() {
        Wrap<Integer> a = Wrap.of(1);           // Wrap(value=1)
        Wrap<Integer> b = a.map(i -> i + 9);    // Wrap(value=10)
        Wrap<Integer> c = b.map(i -> i * 11);   // Wrap(value=110)
        a.map(i -> i * 10).map(i -> i + 11);    // Wrap(value=21)

        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
    }
}
