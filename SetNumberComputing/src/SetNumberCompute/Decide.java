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
public class Decide {
    
    public static boolean persistence(CauseSetElements cses, int total) {
        for (int a = 0; a < cses.causeSetElements.size(); a++) {
            for (int c = 0; c < total; c++) {
                if (cses.causeSetElements.get(a).causeSetElement.contains(c) == false) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public static boolean persistence(CauseSetElement cse, int total) {
        for (int c = 0; c < total; c++) {
            if (cse.causeSetElement.contains(c) == false) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean persistence(CauseSetNumber csn, int total) {
        for (int a = 0; a < csn.possiblePositiveSetNumbers.size(); a++) {
            for (int b = 0; b < csn.possiblePositiveSetNumbers.get(a).causeSetElements.size(); b++) {
                for (int c = 0; c < total; c++) {
                    if (csn.possiblePositiveSetNumbers.get(a).causeSetElements.get(b).causeSetElement.contains(c) == false) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
