/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binmethod;
import java.util.List;
/**
 *
 * @author Matt
 */
public class RiceRule extends BinFormulae {
    
    //Call the parent constructor
    public RiceRule(List<Float> data){
        super(data);
    }
    
    public void calculateNumberOfBins(){
        //Calculate the number of bins and then assign to self.NumberOfBins
        //k = 2(cuberoot(n))
        setNumberOfBins((int)(2*Math.cbrt(getStoredDataLength())));
        
    }
    
}
