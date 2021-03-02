import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import org.junit.platform.engine.support.descriptor.FileSource;

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
    @ParameterizedTest
    @CsvSource({
            "10,20,30",
            "20,30,50",
            "30,40,30"
    })
    void testCase5(int num1,int num2,int result){
        Assertions.assertEquals(result,Calculator.add(num1,num2));
    }

    @ParameterizedTest
    @CsvFileSource(resources="/sample-data.csv",numLinesToSkip = 1)
    void testCase6(int num1,int num2,int result){
        Assertions.assertEquals(result,Calculator.add(num1,num2));
    }
}
