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
public class CauseSetElement {

    public ArrayList<Integer> causeSetElement = new ArrayList<Integer>();

    public CauseSetElement() {

    }

    public CauseSetElement clone() {
        CauseSetElement cse = new CauseSetElement();
        for (int a = 0; a < causeSetElement.size(); a++) {
            if (cse.causeSetElement.contains(causeSetElement.get(a)) == false) {
                cse.causeSetElement.add(causeSetElement.get(a));
            }
        }
        return cse;
    }
}
