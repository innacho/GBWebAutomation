package triangleS;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

import org.junit.Test;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class TriangleTest {

    @ParameterizedTest
    @CsvFileSource(resources = "/triangleParams.csv")
    public void triangleAreaTest(int a, int b, int c, double area){
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        double areaRes = Triangle.countArea(a, b, c);
        System.out.println(areaRes);

        assumingThat(
                (a+b>c && a+c>b && b+c>a),
                () -> assertEquals(area, Triangle.countArea(a, b, c), 0.001)
        );
    }
}
