/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binmethod;
import java.util.List;
import java.lang.Math;
/**
 *
 * @author Matt
 */
public class SquareRootChoice extends BinFormulae {
    
    //Call the parent constructor
    public SquareRootChoice(List<Float> data){
        super(data);
    }
    
    public void calculateNumberOfBins(){
        //Calculate the number of bins and then assign to self.NumberOfBins
        // k = root(n)
        // where k is num of bins and n is number of data points
        //Round up 
        setNumberOfBins((int)(Math.sqrt(getStoredDataLength())));
        
    }
    
}
