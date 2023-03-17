/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SetNumberCompute;

import SetNumberData.CauseSetElements;
import SetNumberData.CauseSetNumber;

/**
 *
 * @author chogeon
 */
public class Mul {

    public static CauseSetNumber mul(CauseSetNumber left, CauseSetNumber right, CauseSetElements cause, int total) {
        return mul(left, right, cause, total, -1);
    }

    public static CauseSetNumber mul(CauseSetNumber left, CauseSetNumber right, CauseSetElements cause, int total, int limit) {
        if(right.possiblePositiveSetNumbers.get(0).causeSetElements.size()==1){
            return left.clone();
        }
        if(left.possiblePositiveSetNumbers.get(0).causeSetElements.size()==1){
            return right.clone();
        }
        CauseSetNumber copiedLeft = left.clone();
        CauseSetNumber copiedRight = right.clone();
        copiedRight = Plus.plus(copiedRight, new CauseSetNumber(1, true), cause, limit);
        return mulProc(copiedLeft, copiedRight, cause, total, limit, copiedLeft.clone());
    }

    private static CauseSetNumber mulProc(CauseSetNumber left, CauseSetNumber right, CauseSetElements cause, int total, int limit, CauseSetNumber addedVal) {
        left = Plus.plus(left, addedVal, cause, limit);
        right = Minus.minus(right, new CauseSetNumber(1, true), cause, total, limit);
        if (right.possiblePositiveSetNumbers.get(0).causeSetElements.size() > 2) {
            return mulProc(left, right, LogicCalcul.getCause(Minus.minus(right, new CauseSetNumber(2, true), cause, total, limit)), total, limit, addedVal);
        }
        return left;

    }
}
