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
public class Plus {

    public static CauseSetNumber plus(CauseSetNumber origin, CauseSetNumber addedVal, CauseSetElements cause) {
        return plus(origin, addedVal, cause, -1);
    }
    public static CauseSetNumber plus(CauseSetNumber origin, CauseSetNumber addedVal, CauseSetElements cause, int limit) {
        CauseSetNumber copiedOrigin = origin.clone(), copiedAddedVal = addedVal.clone();

        CauseSetNumber newSetNumber = new CauseSetNumber();
        loop:
        for (int d = 0; d < cause.causeSetElements.size(); d++) {
            for (int a = 0; a < copiedOrigin.possiblePositiveSetNumbers.size(); a++) {
                for (int b = 0; b < copiedAddedVal.possiblePositiveSetNumbers.size(); b++) {
                    CauseSetElements newCse = new CauseSetElements();
                    for (int c = 0; c < copiedOrigin.possiblePositiveSetNumbers.get(a).causeSetElements.size(); c++) {
                        newCse.causeSetElements.add(copiedOrigin.possiblePositiveSetNumbers.get(a).causeSetElements.get(c).clone());
                    }
                    for (int c = 0; c < copiedAddedVal.possiblePositiveSetNumbers.get(b).causeSetElements.size(); c++) {
                        CauseSetElement temp = copiedAddedVal.possiblePositiveSetNumbers.get(b).causeSetElements.get(c).clone();
                        for (int e = 0; e < cause.causeSetElements.get(d).causeSetElement.size(); e++) {
                            if (temp.causeSetElement.contains(cause.causeSetElements.get(d).causeSetElement.get(e)) == false) {
                                temp.causeSetElement.add(cause.causeSetElements.get(d).causeSetElement.get(e));
                            }
                        }
                        newCse.causeSetElements.add(temp);
                    }
                    if(limit!=-1 && limit<newSetNumber.possiblePositiveSetNumbers.size()){
                        break loop;
                    }
                    newSetNumber.possiblePositiveSetNumbers.add(newCse);
                }
            }
        }
        loop1:
        for (int d = 0; d < cause.causeSetElements.size(); d++) {
            for (int a = 0; a < copiedOrigin.possibleNegativeSetNumbers.size(); a++) {
                for (int b = 0; b < copiedAddedVal.possibleNegativeSetNumbers.size(); b++) {
                    CauseSetElements newCse = new CauseSetElements();
                    for (int c = 0; c < copiedOrigin.possibleNegativeSetNumbers.get(a).causeSetElements.size(); c++) {
                        newCse.causeSetElements.add(copiedOrigin.possibleNegativeSetNumbers.get(a).causeSetElements.get(c).clone());
                    }
                    for (int c = 0; c < copiedAddedVal.possibleNegativeSetNumbers.get(b).causeSetElements.size(); c++) {
                        CauseSetElement temp = copiedAddedVal.possibleNegativeSetNumbers.get(b).causeSetElements.get(c).clone();
                        for (int e = 0; e < cause.causeSetElements.get(d).causeSetElement.size(); e++) {
                            if (temp.causeSetElement.contains(cause.causeSetElements.get(d).causeSetElement.get(e)) == false) {
                                temp.causeSetElement.add(cause.causeSetElements.get(d).causeSetElement.get(e));
                            }
                        }
                        newCse.causeSetElements.add(temp);
                    }
                    if(limit!=-1 && limit<newSetNumber.possibleNegativeSetNumbers.size()){
                        break loop1;
                    }
                    newSetNumber.possibleNegativeSetNumbers.add(newCse);
                }
            }
        }
        return newSetNumber;
    }
}
