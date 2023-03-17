/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package setnumbercomputingtest;

import SetNumberCompute.Decide;
import SetNumberCompute.Divide;
import SetNumberCompute.LogicCalcul;
import SetNumberCompute.Minus;
import SetNumberCompute.Mul;
import SetNumberCompute.Plus;
import SetNumberCompute.Square;
import SetNumberData.CauseSetElements;
import SetNumberData.CauseSetNumber;

/**
 *
 * @author chogeon
 */
public class SetNumberComputingTest {

    /**
     * @param args the command line arguments
     */
    static int limit = 1;

    public static void main(String[] args) {
        test2();
        test3();
        test4();
        nSigmaFormulaVerification();
        //goldenbachConjecture();
        //fermatLastTheorem();
        //collatzConjecture();
    }

    public static void fermatLastTheorem() {
        for (int a = 2; a < 100; a++) {
            CauseSetNumber num = new CauseSetNumber(a, false);
            if (Decide.persistence(fermatProc(num, a), a)) {
                System.out.println("if it is established until a = " + a + " then fermatLastTheorem is true");
                return;
            }
        }
        System.out.println("if it is not established in 2<=a<100 then fermatLastTheorem is false");

    }

    public static CauseSetElements fermatProc(CauseSetNumber num, int total) {
        CauseSetNumber a = num.clone();
        CauseSetNumber b = num.clone();
        CauseSetNumber c = num.clone();
        CauseSetNumber n = num.clone();
        n = Plus.plus(n, new CauseSetNumber(1, true), new CauseSetElements(total, true), limit);
        CauseSetElements[] recordedCause = new CauseSetElements[]{new CauseSetElements()};
        fermatProc(a, b, c, n, total, new CauseSetElements(total, true), recordedCause);
        return recordedCause[0];
    }

    public static void fermatProc(CauseSetNumber a, CauseSetNumber b, CauseSetNumber c,
            CauseSetNumber n, int total, CauseSetElements cause, CauseSetElements[] recordedCause) {
//        a=Minus.minus(a, new CauseSetNumber(2, true), cause, total, limit);
//        b=Minus.minus(b, new CauseSetNumber(2, true), cause, total, limit);
//        c=Minus.minus(c, new CauseSetNumber(2, true), cause, total, limit);

        CauseSetNumber a1 = Square.spaure(a, n, cause, total, limit);
        CauseSetNumber b1 = Square.spaure(b, n, cause, total, limit);
        CauseSetNumber c1 = Square.spaure(c, n, cause, total, limit);
        CauseSetNumber temp = Plus.plus(a1, b1, cause, limit);
        CauseSetNumber difference;
        if (a1.possiblePositiveSetNumbers.get(0).causeSetElements.size() + b1.possiblePositiveSetNumbers.get(0).causeSetElements.size()
                < c1.possiblePositiveSetNumbers.get(0).causeSetElements.size()) {
            difference = Minus.minus(c1, temp, cause, total, limit);
        } else {
            difference = Minus.minus(temp, c1, cause, total, limit);
        }

        CauseSetElements cause1 = LogicCalcul.getCause(difference);
        recordedCause[0] = LogicCalcul.and(cause1, recordedCause[0]);
        if (a.possiblePositiveSetNumbers.get(0).causeSetElements.size() < total && b.possiblePositiveSetNumbers.get(0).causeSetElements.size() < total
                && c.possiblePositiveSetNumbers.get(0).causeSetElements.size() < total && n.possiblePositiveSetNumbers.get(0).causeSetElements.size() < total + 1) {
            return;
        }
//        cause1 = LogicCalcul.and(cause1, cause);
        if (a.possiblePositiveSetNumbers.get(0).causeSetElements.size() >= total) {
//            CauseSetElements cause2 = LogicCalcul.getCause(Minus.minus(a, new CauseSetNumber(1, true), cause1, total, limit));
//            cause2 = LogicCalcul.and(cause1, cause2);
            fermatProc(Minus.minus(a, new CauseSetNumber(1, true), new CauseSetElements(total, true), total, limit), b, c, n, total, new CauseSetElements(total, true), recordedCause);
        }
        if (b.possiblePositiveSetNumbers.get(0).causeSetElements.size() >= total) {
//            CauseSetElements cause2 = LogicCalcul.getCause(Minus.minus(b, new CauseSetNumber(1, true), cause1, total, limit));
//            cause2 = LogicCalcul.and(cause1, cause2);
            fermatProc(a, Minus.minus(b, new CauseSetNumber(1, true), new CauseSetElements(total, true), total, limit), c, n, total, new CauseSetElements(total, true), recordedCause);
        }
        if (c.possiblePositiveSetNumbers.get(0).causeSetElements.size() >= total) {
//            CauseSetElements cause2 = LogicCalcul.getCause(Minus.minus(c, new CauseSetNumber(1, true), cause1, total, limit));
//            cause2 = LogicCalcul.and(cause1, cause2);
            fermatProc(a, b, Minus.minus(c, new CauseSetNumber(1, true), new CauseSetElements(total, true), total, limit), n, total, new CauseSetElements(total, true), recordedCause);
        }
        if (n.possiblePositiveSetNumbers.get(0).causeSetElements.size() >= total + 1) {
//            CauseSetElements cause2 = LogicCalcul.getCause(Minus.minus(n, new CauseSetNumber(3, true), cause1, total, limit));
//            cause2 = LogicCalcul.and(cause1, cause2);
            fermatProc(a, b, c, Minus.minus(n, new CauseSetNumber(1, true), new CauseSetElements(total, true), total, limit), total, new CauseSetElements(total, true), recordedCause);
        }
    }

    public static void goldenbachConjecture() {
        for (int a = 3; a < 100; a++) {
            CauseSetNumber evenNum = new CauseSetNumber(a, false);
            evenNum = Mul.mul(evenNum, new CauseSetNumber(2, true), new CauseSetElements(a, true), a, limit);
            CauseSetNumber leftNum = new CauseSetNumber(3, true);
            CauseSetNumber rightNum = Minus.minus(evenNum, leftNum, new CauseSetElements(a, true), a, limit);
            CauseSetNumber recordedCause = new CauseSetNumber();
            goldenbachProc(evenNum, leftNum, rightNum, a, recordedCause);
            if (Decide.persistence(recordedCause, a)) {
                System.out.println("if it is established until a = " + a + " then goldenbachConjecture is true");
                return;
            } 
        }
        System.out.println("if it is not established in 3<=a<100 then goldenbachConjecture is false");

    }

    public static void goldenbachProc(CauseSetNumber evenNum, CauseSetNumber leftNum, CauseSetNumber rightNum, int total, CauseSetNumber recordedCause) {
        if (rightNum.possiblePositiveSetNumbers.get(0).causeSetElements.size() == 2) {
            return;
        }
        CauseSetNumber divNum = Minus.minus(leftNum, new CauseSetNumber(1, true), new CauseSetElements(total, true), total, limit);
        Object[] res = thisIsPrimeNum(leftNum, divNum, total, new CauseSetElements(total, true));
        if ((boolean) res[1]) {
            divNum = Minus.minus(rightNum, new CauseSetNumber(1, true), new CauseSetElements(total, true), total, limit);
            res = thisIsPrimeNum(rightNum, divNum, total, new CauseSetElements(total, true));
            if ((boolean) res[1]) {
                recordedCause.possiblePositiveSetNumbers.add((CauseSetElements) res[0]);

                leftNum = Plus.plus(leftNum, new CauseSetNumber(1, true), new CauseSetElements(total, true), limit);
                rightNum = Minus.minus(rightNum, new CauseSetNumber(1, true), new CauseSetElements(total, true), total, limit);

                goldenbachProc(evenNum, leftNum, rightNum, total, recordedCause);
            } else {
                leftNum = Plus.plus(leftNum, new CauseSetNumber(1, true), new CauseSetElements(total, true), limit);
                rightNum = Minus.minus(rightNum, new CauseSetNumber(1, true), new CauseSetElements(total, true), total, limit);

                goldenbachProc(evenNum, leftNum, rightNum, total, recordedCause);
            }
        } else {
            leftNum = Plus.plus(leftNum, new CauseSetNumber(1, true), new CauseSetElements(total, true), limit);
            rightNum = Minus.minus(rightNum, new CauseSetNumber(1, true), new CauseSetElements(total, true), total, limit);

            goldenbachProc(evenNum, leftNum, rightNum, total, recordedCause);

        }
    }

    public static Object[] thisIsPrimeNum(CauseSetNumber num, CauseSetNumber divNum, int total, CauseSetElements cause) {
        if (divNum.possiblePositiveSetNumbers.get(0).causeSetElements.size() == 1) {
            CauseSetNumber cause2 = Minus.minus(new CauseSetNumber(2, true), divNum, new CauseSetElements(total, true), total, limit);
            cause = LogicCalcul.and(cause, LogicCalcul.getCause(cause2), limit);
            return new Object[]{cause, true};
        }

        Object[] res = Divide.div(num, divNum, new CauseSetElements(total, true), total, limit);
        if (((CauseSetNumber) res[1]).possiblePositiveSetNumbers.get(0).causeSetElements.size() != 1) {
            CauseSetNumber cause4 = Minus.minus(((CauseSetNumber) res[1]), new CauseSetNumber(1, true), new CauseSetElements(total, true), total, limit);
            CauseSetNumber num1 = Minus.minus((CauseSetNumber) res[1], cause4, new CauseSetElements(total, true), total, limit);
            if (divNum.possiblePositiveSetNumbers.get(0).causeSetElements.size() == 1) {
                CauseSetNumber cause2 = Minus.minus(new CauseSetNumber(2, true), divNum, new CauseSetElements(total, true), total, limit);
                cause = LogicCalcul.and(cause, LogicCalcul.getCause(cause2), limit);
                return new Object[]{cause, true};
            } else {
                divNum = Minus.minus(divNum, new CauseSetNumber(1, true), new CauseSetElements(total, true), total, limit);
                cause = LogicCalcul.and(cause, LogicCalcul.getCause(num1), limit);
                return thisIsPrimeNum(num, divNum, total, cause);
            }
        } else {
            CauseSetNumber cause2 = Minus.minus(new CauseSetNumber(2, true), ((CauseSetNumber) res[1]), new CauseSetElements(total, true), total, limit);
            cause = LogicCalcul.and(cause, LogicCalcul.getCause(cause2), limit);
            return new Object[]{cause, false};
        }
    }

    public static void collatzConjecture() {

        for (int a = 2; a < 100; a++) {
            CauseSetNumber A = new CauseSetNumber(a, false);
            CauseSetNumber origin = A.clone();
            CauseSetElements cause = new CauseSetElements(1, true);
            CauseSetNumber two = new CauseSetNumber(2, true);
            Object[] res = Divide.div(A, two, cause, a, limit);

            if (((CauseSetNumber) res[1]).possiblePositiveSetNumbers.get(0).causeSetElements.size() == 2) {
                CauseSetElements cause1 = LogicCalcul.getCause((CauseSetNumber) res[1]);
                if (limit != -1) {
                    for (int b = limit; b < cause1.causeSetElements.size(); b++) {
                        cause1.causeSetElements.remove(b);
                        b--;
                    }
                }
                A = odd(A, cause1, a, origin);
            } else {
                CauseSetElements cause1 = LogicCalcul.getCause(Minus.minus(new CauseSetNumber(2, true), (CauseSetNumber) res[1], cause, a));
                if (limit != -1) {
                    for (int b = limit; b < cause1.causeSetElements.size(); b++) {
                        cause1.causeSetElements.remove(b);
                        b--;
                    }
                }
                A = even(A, cause1, a, origin);
            }
            A = Minus.minus(origin, A, cause, a);
            if (Decide.persistence(A, a)) {
                System.out.println("if it is established until a = " + a + " then collatzConjecture is true");
                return;
            }
        }
        System.out.println("if it is not established in 2<=a<100  then collatzConjecture is false");
    }

    public static CauseSetNumber odd(CauseSetNumber A, CauseSetElements cause, int total, CauseSetNumber origin) {
        if (A.possiblePositiveSetNumbers.get(0).causeSetElements.size() < origin.possiblePositiveSetNumbers.get(0).causeSetElements.size()) {
            CauseSetNumber temp1 = A.clone();
            CauseSetNumber temp = Minus.minus(origin, A, cause, total, limit);
            for (int a = 0; a < A.possiblePositiveSetNumbers.size(); a++) {
                for (int b = 0; b < temp.possiblePositiveSetNumbers.size(); b++) {
                    temp1.possiblePositiveSetNumbers.set(a, LogicCalcul.and(A.possiblePositiveSetNumbers.get(a), temp.possiblePositiveSetNumbers.get(b)));
                }
            }
            for (int a = 0; a < A.possibleNegativeSetNumbers.size(); a++) {
                for (int b = 0; b < temp.possiblePositiveSetNumbers.size(); b++) {
                    temp1.possibleNegativeSetNumbers.set(a, LogicCalcul.and(A.possibleNegativeSetNumbers.get(a), temp.possiblePositiveSetNumbers.get(b)));
                }
            }
            return temp1;
        }
        CauseSetNumber three = new CauseSetNumber(3, true);
        CauseSetNumber two = new CauseSetNumber(2, true);
        CauseSetNumber one = new CauseSetNumber(1, true);
        A = Mul.mul(A, three, cause, total, limit);
        if (limit != -1) {
            for (int b = limit; b < A.possiblePositiveSetNumbers.size(); b++) {
                A.possiblePositiveSetNumbers.remove(b);
                b--;
            }
        }
        if (limit != -1) {
            for (int b = limit; b < A.possibleNegativeSetNumbers.size(); b++) {
                A.possibleNegativeSetNumbers.remove(b);
                b--;
            }
        }
        A = Plus.plus(A, one, cause, limit);
        if (limit != -1) {
            for (int b = limit; b < A.possiblePositiveSetNumbers.size(); b++) {
                A.possiblePositiveSetNumbers.remove(b);
                b--;
            }
        }
        if (limit != -1) {
            for (int b = limit; b < A.possibleNegativeSetNumbers.size(); b++) {
                A.possibleNegativeSetNumbers.remove(b);
                b--;
            }
        }
        Object[] res = Divide.div(A, two, cause, total, limit);
        for (int a = 0; a < res.length; a++) {
            if (limit != -1) {
                for (int b = limit; b < ((CauseSetNumber) res[a]).possiblePositiveSetNumbers.size(); b++) {
                    ((CauseSetNumber) res[a]).possiblePositiveSetNumbers.remove(b);
                    b--;
                }
            }
            if (limit != -1) {
                for (int b = limit; b < ((CauseSetNumber) res[a]).possibleNegativeSetNumbers.size(); b++) {
                    ((CauseSetNumber) res[a]).possibleNegativeSetNumbers.remove(b);
                    b--;
                }
            }
        }

        A = ((CauseSetNumber) res[0]);
        A = Minus.minus(A, one, cause, total, limit);
        res = Divide.div(A, two, cause, total, limit);
        if (((CauseSetNumber) res[1]).possiblePositiveSetNumbers.get(0).causeSetElements.size() == 2) {
            cause = LogicCalcul.and(LogicCalcul.getCause(Minus.minus((CauseSetNumber) res[1], new CauseSetNumber(1, true), cause, total)), cause, limit);
            return odd(A, cause, total, origin);
        } else {
            cause = LogicCalcul.and(LogicCalcul.getCause(Minus.minus(new CauseSetNumber(2, true), (CauseSetNumber) res[1], cause, total)), cause, limit);
            return even(A, cause, total, origin);
        }
    }

    public static CauseSetNumber even(CauseSetNumber A, CauseSetElements cause, int total, CauseSetNumber origin) {
        if (A.possiblePositiveSetNumbers.get(0).causeSetElements.size() < origin.possiblePositiveSetNumbers.get(0).causeSetElements.size()) {
            CauseSetNumber temp1 = A.clone();
            CauseSetNumber temp = Minus.minus(origin, A, cause, total, limit);
            for (int a = 0; a < A.possiblePositiveSetNumbers.size(); a++) {
                for (int b = 0; b < temp.possiblePositiveSetNumbers.size(); b++) {
                    temp1.possiblePositiveSetNumbers.set(a, LogicCalcul.and(A.possiblePositiveSetNumbers.get(a), temp.possiblePositiveSetNumbers.get(b)));
                }
            }
            for (int a = 0; a < A.possibleNegativeSetNumbers.size(); a++) {
                for (int b = 0; b < temp.possiblePositiveSetNumbers.size(); b++) {
                    temp1.possibleNegativeSetNumbers.set(a, LogicCalcul.and(A.possibleNegativeSetNumbers.get(a), temp.possiblePositiveSetNumbers.get(b)));
                }
            }
            return temp1;
        }

        CauseSetNumber two = new CauseSetNumber(2, true);
        Object[] res = Divide.div(A, two, cause, total, limit);
        CauseSetNumber one = new CauseSetNumber(1, true);
        for (int a = 0; a < res.length; a++) {
            if (limit != -1) {
                for (int b = limit; b < ((CauseSetNumber) res[a]).possiblePositiveSetNumbers.size(); b++) {
                    ((CauseSetNumber) res[a]).possiblePositiveSetNumbers.remove(b);
                    b--;
                }
            }
            if (limit != -1) {
                for (int b = limit; b < ((CauseSetNumber) res[a]).possibleNegativeSetNumbers.size(); b++) {
                    ((CauseSetNumber) res[a]).possibleNegativeSetNumbers.remove(b);
                    b--;
                }
            }
        }
        A = ((CauseSetNumber) res[0]);
        A = Minus.minus(A, one, cause, total, limit);
        res = Divide.div(A, two, cause, total, limit);

        if (((CauseSetNumber) res[1]).possiblePositiveSetNumbers.get(0).causeSetElements.size() == 2) {
            cause = LogicCalcul.and(LogicCalcul.getCause(Minus.minus((CauseSetNumber) res[1], new CauseSetNumber(1, true), cause, total)), cause, limit);
            return odd(A, cause, total, origin);
        } else {
            cause = LogicCalcul.and(LogicCalcul.getCause(Minus.minus(new CauseSetNumber(2, true), (CauseSetNumber) res[1], cause, total)), cause, limit);
            return even(A, cause, total, origin);
        }
    }

    public static void test4() {
        //it is verificate which a+100>a*a is also establisheded 
        for (int a = 5; a < 20; a++) {
            CauseSetNumber A = new CauseSetNumber(a, false);
            A = Plus.plus(A, new CauseSetNumber(100, true), new CauseSetElements(1, true), limit);

            CauseSetNumber B = new CauseSetNumber(a, false);
            B = Mul.mul(B, B.clone(), new CauseSetElements(1, true), a, limit);

            CauseSetNumber temp1 = Minus.minus(A, B, new CauseSetElements(1, true), a, limit);
            if (Decide.persistence(temp1, a)) {
                System.out.println("if it is established until a = " + a + " then a+100>a*a is true");
                return;
            }
        }
        System.out.println("if it is not established in 5<=a <20 then a+100>a*a is false");
    }

    public static void test3() {
        //it is verificate which a+20<a+a is also establisheded   
        for (int a = 2; a < 300; a++) {
            CauseSetNumber A = new CauseSetNumber(a, false);
            A = Plus.plus(A, new CauseSetNumber(20, true), new CauseSetElements(1, true));

            CauseSetNumber B = new CauseSetNumber(a, false);
            B = Plus.plus(B, B.clone(), new CauseSetElements(1, true));

            CauseSetNumber temp1 = Minus.minus(B, A, new CauseSetElements(1, true), a);
            if (Decide.persistence(temp1, a)) {
                System.out.println("if it is established until a = " + a + " then a+20<a+a is true");
                return;
            }
        }
        System.out.println("if it is not established in 2<=a<300 then a+20<a+a is false");
    }

    public static void test2() {
        //it is verificate which a+20>a^2 is also establisheded        
        for (int a = 3; a < 30; a++) {
            CauseSetNumber A = new CauseSetNumber(a, false);
            A = Plus.plus(A, new CauseSetNumber(20, true), new CauseSetElements(1, true));

            CauseSetNumber B = new CauseSetNumber(a, false);
            B = Plus.plus(B, B.clone(), new CauseSetElements(1, true));

            CauseSetNumber temp1 = Minus.minus(A, B, new CauseSetElements(1, true), a);
            if (Decide.persistence(temp1, a)) {
                System.out.println("if it is established until a = " + a + " then a+20>a^2 is true");
                return;
            }
        }
        System.out.println("if it is not established in 3<=a <30 then a+20>a^2 is false");
    }

    public static void nSigmaFormulaVerification() {
        //sigma n formula verification
        for (int a = 3; a < 20; a++) {
            CauseSetNumber A = new CauseSetNumber(a, false);
            CauseSetNumber B = new CauseSetNumber(1, true);
            CauseSetElements cause = new CauseSetElements(1, true);
            CauseSetNumber totalSum = totalSum(A, B, a, cause);
            totalSum = Mul.mul(totalSum, new CauseSetNumber(2, true), new CauseSetElements(1, true), a);

            A = new CauseSetNumber(a, false);
            A = Plus.plus(A, new CauseSetNumber(1, true), new CauseSetElements(1, true));
            CauseSetNumber C = A.clone();
            B = new CauseSetNumber(1, true);
            cause = new CauseSetElements(1, true);
            CauseSetNumber mul = mul(A, B, C, a, cause);

            CauseSetNumber totalSum1 = Plus.plus(totalSum, new CauseSetNumber(1, true), new CauseSetElements(1, true));
            CauseSetNumber temp = Minus.minus(totalSum1, mul, new CauseSetElements(1, true), a);

            CauseSetNumber mul1 = Plus.plus(mul, new CauseSetNumber(1, true), new CauseSetElements(1, true));
            CauseSetNumber temp1 = Minus.minus(mul1, totalSum, new CauseSetElements(1, true), a);
            if (Decide.persistence(temp, a) && Decide.persistence(temp1, a)) {
                System.out.println("if it is established until n = " + a + " then sigma n formula is true");
                return;
            }

        }
        System.out.println("if it is not established in 3<=n<20 then sigma n formula is false");
    }

    public static CauseSetNumber mul(CauseSetNumber A, CauseSetNumber B, CauseSetNumber C, int total, CauseSetElements cause) {

        A = Plus.plus(A, C, cause);
        B = Minus.minus(B, new CauseSetNumber(1, true), cause, total);

        CauseSetNumber temp = Minus.minus(B, new CauseSetNumber(1, true), new CauseSetElements(1, true), total);
        for (int a = 0; a < temp.possiblePositiveSetNumbers.size(); a++) {
            for (int b = 0; b < temp.possiblePositiveSetNumbers.get(a).causeSetElements.size(); b++) {
                cause.causeSetElements.add(temp.possiblePositiveSetNumbers.get(a).causeSetElements.get(b).clone());
            }
        }
        if (temp.possiblePositiveSetNumbers.get(0).causeSetElements.size() > 1) {
            return mul(A, B, C, total, cause);
        }
        return A;
    }

    public static CauseSetNumber totalSum(CauseSetNumber A, CauseSetNumber B, int total, CauseSetElements cause) {

        A = Plus.plus(A, B, cause);
        B = Minus.minus(B, new CauseSetNumber(1, true), cause, total);

        CauseSetNumber temp = Minus.minus(B, new CauseSetNumber(1, true), new CauseSetElements(1, true), total);
        for (int a = 0; a < temp.possiblePositiveSetNumbers.size(); a++) {
            for (int b = 0; b < temp.possiblePositiveSetNumbers.get(a).causeSetElements.size(); b++) {
                cause.causeSetElements.add(temp.possiblePositiveSetNumbers.get(a).causeSetElements.get(b).clone());
            }
        }
        if (temp.possiblePositiveSetNumbers.get(0).causeSetElements.size() > 1) {
            return totalSum(A, B, total, cause);
        }
        return A;
    }

}
