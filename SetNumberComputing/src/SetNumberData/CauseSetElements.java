/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SetNumberData;

import java.util.ArrayList;

/**
 *
 * @author chogeon
 */
public class CauseSetElements {

    public ArrayList<CauseSetElement> causeSetElements = new ArrayList<CauseSetElement>();

    public CauseSetElements() {

    }

    public CauseSetElements(int total, boolean empty) {
        if (empty == false) {
            for (int a = 0; a < total; a++) {
                CauseSetElement cse = new CauseSetElement();
                cse.causeSetElement.add(a);
                causeSetElements.add(cse);
            }
        } else {
            for (int a = 0; a < total; a++) {
                CauseSetElement cse = new CauseSetElement();
                cse.causeSetElement.add(-1);
                causeSetElements.add(cse);
            }
        }
    }

    public CauseSetElements clone() {
        CauseSetElements cse = new CauseSetElements();
        for (int a = 0; a < causeSetElements.size(); a++) {
            cse.causeSetElements.add(causeSetElements.get(a).clone());
        }
        return cse;
    }
}
