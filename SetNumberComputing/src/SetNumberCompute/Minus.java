/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SetNumberCompute;

import SetNumberData.CauseSetElement;
import SetNumberData.CauseSetElements;
import SetNumberData.CauseSetNumber;

/**
 *
 * @author chogeon
 */
public class Minus {

    public static CauseSetNumber minus(CauseSetNumber origin, CauseSetNumber minusVal, CauseSetElements cause, int total) {
        return minus(origin, minusVal, cause, total, -1);
    }

    public static CauseSetNumber minus(CauseSetNumber origin, CauseSetNumber minusVal, CauseSetElements cause, int total, int limit) {
        CauseSetNumber copiedOrigin = origin.clone(), copiedMinusVal = minusVal.clone();

        CauseSetNumber newSetNumber = new CauseSetNumber();
        loop:
        for (int d = 0; d < cause.causeSetElements.size(); d++) {
            for (int a = 0; a < copiedOrigin.possiblePositiveSetNumbers.size(); a++) {
                for (int b = 0; b < copiedMinusVal.possibleNegativeSetNumbers.size(); b++) {
                    CauseSetElements newCse = new CauseSetElements();
                    for (int c = 0; c < copiedOrigin.possiblePositiveSetNumbers.get(a).causeSetElements.size(); c++) {
                        newCse.causeSetElements.add(copiedOrigin.possiblePositiveSetNumbers.get(a).causeSetElements.get(c).clone());
                    }
                    CauseSetElements newCse1 = new CauseSetElements();
                    for (int c = 0; c < copiedMinusVal.possibleNegativeSetNumbers.get(b).causeSetElements.size(); c++) {
                        CauseSetElement temp = copiedMinusVal.possibleNegativeSetNumbers.get(b).causeSetElements.get(c).clone();
                        for (int e = 0; e < cause.causeSetElements.get(d).causeSetElement.size(); e++) {
                            temp.causeSetElement.add(cause.causeSetElements.get(d).causeSetElement.get(e));
                        }
                        newCse1.causeSetElements.add(temp);
                    }
                    if (limit != -1 && limit < newSetNumber.possiblePositiveSetNumbers.size()) {
                        break loop;
                    }
                    newSetNumber.possiblePositiveSetNumbers.add(minus(newCse, newCse1, true, total));
                }
            }
        }
        loop1:
        for (int d = 0; d < cause.causeSetElements.size(); d++) {
            for (int a = 0; a < copiedOrigin.possibleNegativeSetNumbers.size(); a++) {
                for (int b = 0; b < copiedMinusVal.possiblePositiveSetNumbers.size(); b++) {
                    CauseSetElements newCse = new CauseSetElements();
                    for (int c = 0; c < copiedOrigin.possibleNegativeSetNumbers.get(a).causeSetElements.size(); c++) {
                        newCse.causeSetElements.add(copiedOrigin.possibleNegativeSetNumbers.get(a).causeSetElements.get(c).clone());
                    }
                    CauseSetElements newCse1 = new CauseSetElements();
                    for (int c = 0; c < copiedMinusVal.possiblePositiveSetNumbers.get(b).causeSetElements.size(); c++) {
                        CauseSetElement temp = copiedMinusVal.possiblePositiveSetNumbers.get(b).causeSetElements.get(c).clone();
                        for (int e = 0; e < cause.causeSetElements.get(d).causeSetElement.size(); e++) {
                            temp.causeSetElement.add(cause.causeSetElements.get(d).causeSetElement.get(e));
                        }
                        newCse1.causeSetElements.add(temp);
                    }
                    if (limit != -1 && limit < newSetNumber.possibleNegativeSetNumbers.size()) {
                        break loop1;
                    }
                    newSetNumber.possibleNegativeSetNumbers.add(minus(newCse, newCse1, false, total));
                }
            }
        }
        return newSetNumber;
    }

    private static CauseSetElements minus(CauseSetElements origin, CauseSetElements minusVal, boolean positive, int total) {
        CauseSetElements copiedOrigin = origin.clone();
        CauseSetElements copiedMinusVal = minusVal.clone();
        CauseSetElement minNoMinusPossibleCauseSetElement = null;
        CauseSetElement cause = new CauseSetElement();
        int min, select, select1;
        for (int a = 0; a < copiedOrigin.causeSetElements.size(); a++) {
            min = Integer.MAX_VALUE;
            select = -1;
            select1 = -1;
            for (int d = 0; d < copiedOrigin.causeSetElements.size(); d++) {
                if (cause.causeSetElement.isEmpty()==false) {
                    for (int b = 0; b < copiedMinusVal.causeSetElements.size(); b++) {
                        CauseSetElement minusNoPossibleCauseSetElement = getMinusNoPossibleCauseSetElement(copiedOrigin.causeSetElements.get(d), copiedMinusVal.causeSetElements.get(b), positive, total);
                        boolean test = false;
                        for (int c = 0; c < minusNoPossibleCauseSetElement.causeSetElement.size(); c++) {
                            if (cause.causeSetElement.contains(minusNoPossibleCauseSetElement.causeSetElement.get(c)) == false) {
                                test = true;
                                break;
                            }
                        }
                        if (test == false) {
                            if (min > minusNoPossibleCauseSetElement.causeSetElement.size()) {
                                min = minusNoPossibleCauseSetElement.causeSetElement.size();
                                select = b;
                                select1 = d;
                                minNoMinusPossibleCauseSetElement = minusNoPossibleCauseSetElement.clone();
                            }
                        }
                    }
                }
            }
            if (select == -1) {
                select1 = a;
                for (int b = 0; b < copiedMinusVal.causeSetElements.size(); b++) {
                    CauseSetElement minusNoPossibleCauseSetElement = getMinusNoPossibleCauseSetElement(copiedOrigin.causeSetElements.get(a), copiedMinusVal.causeSetElements.get(b), positive, total);

                    if (min > minusNoPossibleCauseSetElement.causeSetElement.size()) {
                        min = minusNoPossibleCauseSetElement.causeSetElement.size();
                        select = b;
                        minNoMinusPossibleCauseSetElement = minusNoPossibleCauseSetElement.clone();
                    }

                }
            }
            for (int b = 0; b < minNoMinusPossibleCauseSetElement.causeSetElement.size(); b++) {
                if (cause.causeSetElement.contains(minNoMinusPossibleCauseSetElement.causeSetElement.get(b)) == false) {
                    cause.causeSetElement.add(minNoMinusPossibleCauseSetElement.causeSetElement.get(b));
                }
            }
            copiedOrigin.causeSetElements.remove(select1);
            if (a >= select1) {
                a--;
            }
            copiedMinusVal.causeSetElements.remove(select);
            if (copiedMinusVal.causeSetElements.isEmpty()) {
                break;
            }
        }
        for (int a = 0; a < copiedOrigin.causeSetElements.size(); a++) {
            for (int b = 0; b < cause.causeSetElement.size(); b++) {
                if (copiedOrigin.causeSetElements.get(a).causeSetElement.contains(cause.causeSetElement.get(b)) == false) {
                    copiedOrigin.causeSetElements.get(a).causeSetElement.add(cause.causeSetElement.get(b));
                }
            }
        }
        return copiedOrigin;
    }

    private static CauseSetElement getMinusNoPossibleCauseSetElement(CauseSetElement right, CauseSetElement left, boolean positive, int total) {
        if (positive) {
            CauseSetElement ncse = new CauseSetElement();
            if (Decide.persistence(left, total) == false || Decide.persistence(right, total) == false) {
//                for (int a = 0; a < total; a++) {
                boolean test = false;
                for (int a = 0; a < ncse.causeSetElement.size(); a++) {
                    if (ncse.causeSetElement.get(a) != -1) {
                        test = true;
                        break;
                    }
                }
                if (test == false) {
                    ncse.causeSetElement.add(0);
                }
//                }
            }
            for (int a = 0; a < left.causeSetElement.size(); a++) {
                if (right.causeSetElement.contains(left.causeSetElement.get(a)) == false
                        && ncse.causeSetElement.contains(left.causeSetElement.get(a)) == false) {
                    ncse.causeSetElement.add(left.causeSetElement.get(a));
                }
            }
            return ncse;
        } else {
            CauseSetElement ncse = new CauseSetElement();
            if (Decide.persistence(right, total) == false || Decide.persistence(left, total) == false) {
//                for (int a = 0; a < total; a++) {
                boolean test = false;
                for (int a = 0; a < ncse.causeSetElement.size(); a++) {
                    if (ncse.causeSetElement.get(a) != -1) {
                        test = true;
                        break;
                    }
                }
                if (test == false) {
                    ncse.causeSetElement.add(0);
                }
//                }
            }
            for (int a = 0; a < right.causeSetElement.size(); a++) {
                if (left.causeSetElement.contains(right.causeSetElement.get(a)) == false
                        && ncse.causeSetElement.contains(right.causeSetElement.get(a)) == false) {
                    ncse.causeSetElement.add(right.causeSetElement.get(a));
                }
            }
            return ncse;
        }
    }
}
