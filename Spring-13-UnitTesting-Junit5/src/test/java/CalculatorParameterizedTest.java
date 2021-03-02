import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

public class CalculatorParameterizedTest {

    @ParameterizedTest
    @ValueSource(strings = {"Java","JS","TS"})
    void testCase1(String arg){
        Assertions.assertTrue(!arg.isEmpty());
    }

    @ParameterizedTest
    @ValueSource(ints = {3,6,9})
    void testCase2(int number){
        Assertions.assertEquals(0,number%3);
    }

    @ParameterizedTest
    @ValueSource(strings = {"Java","JS","TS"})
    //@EmptySource //executes the test with empty sources
    //@NullSource
    @NullAndEmptySource
    void testCase3(String arg){
        Assertions.assertTrue(!arg.isEmpty());
    }

    @ParameterizedTest
    @MethodSource("stringProvider")
    void testCase4(String arg){
        Assertions.assertNotNull(arg);
    }

    static String[] stringProvider(){
        String arr[]={"Java","JS","TS"};
        return arr;
    }
}
