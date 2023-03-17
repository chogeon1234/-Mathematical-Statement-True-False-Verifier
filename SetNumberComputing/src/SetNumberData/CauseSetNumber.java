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
public class CauseSetNumber {
    public CauseSetNumber(int total, boolean empty){
        possiblePositiveSetNumbers.add(new CauseSetElements(total, empty));
        possibleNegativeSetNumbers.add(new CauseSetElements(total, empty));
    }
    
    public CauseSetNumber(){
        
    }
    
    
    public ArrayList<CauseSetElements> possiblePositiveSetNumbers=new ArrayList<CauseSetElements>();
    public ArrayList<CauseSetElements> possibleNegativeSetNumbers=new ArrayList<CauseSetElements>();
    
    public CauseSetNumber clone() {
        CauseSetNumber csn=new CauseSetNumber();
        for(int a=0; a<possiblePositiveSetNumbers.size(); a++){
            csn.possiblePositiveSetNumbers.add(possiblePositiveSetNumbers.get(a).clone());
        }
        for(int a=0; a<possibleNegativeSetNumbers.size(); a++){
            csn.possibleNegativeSetNumbers.add(possibleNegativeSetNumbers.get(a).clone());
        }
        return csn;
    }
    
    
}
