
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
public abstract class BinFormulae {
       
    //Each sub-class requires a member variable number of bins
    private float NumberOfBins;
    private List<Float> storedData;
    
    //Each sub-class constructor is the same so we can implement it here. Each sub-class will call super constructor 
    public BinFormulae(List<Float> data){
        storedData = data;
    }
    
    //Setter for NumberOfBins
    public void setNumberOfBins(float number){
        NumberOfBins = number;
    }
    
    //Each sub-class requires a getter for number of bins
    public int getNumberOfBins(){
        return (int)NumberOfBins;
    }
    
    public float getStoredDataLength(){
        return (float)storedData.size();
    }
    
    public void calculateNumberOfBins(){
        //To be defined in the inherited class
        System.out.printf("test");
    }
    
}
