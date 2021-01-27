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
public class SturgesFormula extends BinFormulae {
    
    //Call the parent constructor
    public SturgesFormula(List<Float> data){
        super(data);
    }
    
    public void calculateNumberOfBins(){
        //Calculate the number of bins and then assign to self.NumberOfBins
        //k = 3.3ln(n)+1
        setNumberOfBins((int)(3.3*Math.log(getStoredDataLength()+1)));
    }
    
}
