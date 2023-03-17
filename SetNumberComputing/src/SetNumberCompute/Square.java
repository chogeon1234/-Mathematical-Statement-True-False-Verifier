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
public class Square {
    
    public static CauseSetNumber spaure(CauseSetNumber num, CauseSetNumber factor, CauseSetElements cause, int total) {
        return spaure(num, factor, cause, total, -1);
    }

    public static CauseSetNumber spaure(CauseSetNumber num, CauseSetNumber factor, CauseSetElements cause, int total, int limit) {
        CauseSetNumber copiedNum = num.clone();
        CauseSetNumber copiedFactor = factor.clone();
        copiedFactor = Plus.plus(copiedFactor, new CauseSetNumber(1, true), cause, limit);
        return spaureProc(copiedNum, copiedFactor, cause, total, limit, copiedNum.clone());
    }

    private static CauseSetNumber spaureProc(CauseSetNumber num, CauseSetNumber factor, CauseSetElements cause, int total, int limit, CauseSetNumber mulVal) {
        num = Mul.mul(num, mulVal, cause, limit);
        factor = Minus.minus(factor, new CauseSetNumber(1, true), cause, total, limit);
        if (factor.possiblePositiveSetNumbers.get(0).causeSetElements.size() > 2) {
            return spaureProc(num, factor, LogicCalcul.getCause(Minus.minus(factor, new CauseSetNumber(2, true), cause, total, limit)), total, limit, mulVal);
        }
        return num;

    }
}
