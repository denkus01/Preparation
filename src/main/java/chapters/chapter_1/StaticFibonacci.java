package chapters.chapter_1;

import java.math.BigDecimal;

public class StaticFibonacci {
    static StaticFibonacci staticFibonacci = new StaticFibonacci();

    BigDecimal a = new BigDecimal("1");
    BigDecimal b = new BigDecimal("1");
    BigDecimal c = new BigDecimal("0");

    int i = 0;

    public void getFibonacci() {
        if (i < 100) {
            ++i;
            a = b;
            b = c;
            c = a.add(b);
            System.out.println("" + c);
            staticFibonacci.getFibonacci();
        }
    }

}
