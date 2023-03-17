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
public class Divide {

    public static Object[] div(CauseSetNumber left, CauseSetNumber right, CauseSetElements cause, int total) {
        return div(left, right, cause, total, -1);
    }

    public static Object[] div(CauseSetNumber left, CauseSetNumber right, CauseSetElements cause, int total, int limit) {
        CauseSetNumber copiedLeft = left.clone();
        CauseSetNumber copiedRight = right.clone();
        CauseSetNumber share = new CauseSetNumber(1, true);
        copiedLeft = Plus.plus(copiedLeft, new CauseSetNumber(1, true), cause, limit);
        Object[] res = divProc(copiedLeft, copiedRight, cause, total, share, limit);
        return res;
    }

    private static Object[] divProc(CauseSetNumber left, CauseSetNumber right, CauseSetElements cause, int total, CauseSetNumber share, int limit) {//몫, 나머지가 실제보다 1크게 나옴

        if (right.possiblePositiveSetNumbers.get(0).causeSetElements.size() >= left.possiblePositiveSetNumbers.get(0).causeSetElements.size()) {
            return new Object[]{share, left};
        } else {
            CauseSetNumber left1 = Minus.minus(left, right, cause, total, limit);
            CauseSetElements tempCauses = LogicCalcul.and(cause, LogicCalcul.getCause(left1), limit);

            share = Plus.plus(share, new CauseSetNumber(1, true), tempCauses, limit);
            return divProc(left1, right, tempCauses, total, share, limit);
        }

    }
}
