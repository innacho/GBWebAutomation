package triangleS;

public class Triangle{
    public static double countArea (int sideA, int sideB, int sideC) throws WrongSideException, NotTriangleException{
        if(sideA <=0 || sideB <=0 || sideC <=0) throw new WrongSideException("One of the sides has not positive length");
        if(sideA+sideB <= sideC || sideA+sideC <= sideB || sideB+sideC <= sideA) throw new NotTriangleException("It is not a triangle");
        double p = ((double)sideA+(double)sideB+(double)sideC)/2;
        double area =  Math.sqrt(p*(p-sideA)*(p-sideB)*(p-sideC));
        return area;
    }
}
