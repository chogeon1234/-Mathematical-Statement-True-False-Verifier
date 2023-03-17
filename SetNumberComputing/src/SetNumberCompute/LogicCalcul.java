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
public class LogicCalcul {

    public static CauseSetElements getCause(CauseSetNumber temp) {
        CauseSetElements cause = new CauseSetElements();
        for (int a = 0; a < temp.possiblePositiveSetNumbers.size(); a++) {
            for (int b = 0; b < temp.possiblePositiveSetNumbers.get(a).causeSetElements.size(); b++) {
                cause.causeSetElements.add(temp.possiblePositiveSetNumbers.get(a).causeSetElements.get(b).clone());
            }
        }
        return cause;
    }

    public static CauseSetElements and(CauseSetElements temp1, CauseSetElements temp2) {
        return and(temp1, temp2, -1);
    }

    public static CauseSetElements and(CauseSetElements temp1, CauseSetElements temp2, int limit) {
        if (temp1.causeSetElements.isEmpty()) {
            temp1.causeSetElements.add(new CauseSetElement());
            temp1.causeSetElements.get(0).causeSetElement.add(-1);
        }
        if (temp2.causeSetElements.isEmpty()) {
            temp2.causeSetElements.add(new CauseSetElement());
            temp2.causeSetElements.get(0).causeSetElement.add(-1);
        }
        CauseSetElements totalCauses = new CauseSetElements();
        for (int a = 0; a < temp1.causeSetElements.size(); a++) {
            for (int c = 0; c < temp2.causeSetElements.size(); c++) {
                CauseSetElement totalCause = new CauseSetElement();
                for (int b = 0; b < temp1.causeSetElements.get(a).causeSetElement.size(); b++) {
                    for (int d = 0; d < temp2.causeSetElements.get(c).causeSetElement.size(); d++) {
                        totalCause.causeSetElement.add(temp1.causeSetElements.get(a).causeSetElement.get(b));
                        totalCause.causeSetElement.add(temp2.causeSetElements.get(c).causeSetElement.get(d));
                    }
                }
                if (limit != -1 && limit < totalCauses.causeSetElements.size()) {
                    return totalCauses.clone();
                }
                totalCauses.causeSetElements.add(totalCause);
            }
        }
        return totalCauses.clone();
    }
}
