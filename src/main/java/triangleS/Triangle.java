package triangleS;

public class Triangle{

    public static double countArea(int sideA, int sideB, int sideC){
        double p = ((double)sideA+(double)sideB+(double)sideC)/2;
        double area =  Math.sqrt(p*(p-sideA)*(p-sideB)*(p-sideC));
        return area;
    }
}
