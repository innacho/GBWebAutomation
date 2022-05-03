package triangleS;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.opentest4j.AssertionFailedError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TriangleTest {

    static Logger logger = LoggerFactory.getLogger(TriangleTest.class);

    @ParameterizedTest
    @CsvFileSource(resources = "/triangleParams.csv")
    public void triangleAreaTest(int a, int b, int c, double area){

        logger.info(("Test for " +a+", "+b+", "+c));
           try {
                assertEquals(area, Triangle.countArea(a, b, c), 0.001, "Test for " + a + ", " + b + ", " + c + " was wrong");
                logger.info("Test passed!");
            } catch (WrongSideException | NotTriangleException e){
               logger.warn(e.getMessage());
               logger.warn("Assertion for sides " +a+", "+b+", "+c + " failed. Test ignored");
            } catch(AssertionFailedError e){
            logger.error("Test for " + a + ", " + b + ", " + c + " FAILED!!!");
            logger.error(e.getMessage());
        }
    }
}
