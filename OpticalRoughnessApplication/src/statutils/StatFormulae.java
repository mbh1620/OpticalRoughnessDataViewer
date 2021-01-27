/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statutils;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

import binmethod.*;
/**
 *
 * @author Matt
 */
public class StatFormulae {
    //Abstract so that no one can use the base class
    private List<Float> data;
    public List<Float> Binned_data;
    //public List<Float> Normalised_data;
    private float bin_width;
    private float var;
    private float mean;
    private float min;
    private float max;
    private int data_size;
    
    //You probably wouldnt want a sub-class for each formulae in this case otherwise it would become very tedious setting up each instance.
    public StatFormulae(List<Float> _data){
        data = _data;
        data_size = data.size();
    }
    
    public StatFormulae(){
        //Default constructor
    }
    
    public void setData(List<Float> _data){
        data = _data;
        data_size = data.size();
    }
    
    public List<Float> getBinnedData(){
        return Binned_data;
    }
    
    //Mean
    public float getMean(){
        float sum = 0;
        for(int i = 0; i < data_size; i++){
            sum += data.get(i);
        }
        mean = (sum/data_size);
        return (sum/data_size);
    }
    //Variance
    public float getVar(){
        //Get variance
        /* subtract the mean from each data value and square the result
        
            (xi - mean_x)^2
        
        */
        float sum = 0;
        
        for(int i = 0; i < data_size; i++){
            sum +=  Math.pow((data.get(i)-mean), 2);
        }
        var = (sum/data_size);
        return (sum/data_size);
    }
    //Max
    public float getMax(){
        max = Collections.max(data);
        return Collections.max(data);        
    }
    //Min
    public float getMin(){
        
        min = Collections.min(data);
        return Collections.min(data); 
    }
    //Median
    public float getMedian(){
    //Median in the middle
    //Sort data then recursively tick both sides off until either no elements are left or 1 element is left
    Collections.sort(data);
    float median;
    //Data.size is a multiple of 2 then find middle two elements and average them 
    //If Data.size is odd then data.size/2 and then minus 1/2
    
    if (data_size % 2 == 0){
        float a = data.get(data_size/2);
        //System.out.printf("\n %d", data_size/2);
        float b = data.get((data_size/2)-1);
        median = (a+b)/2;
      
    } else {
        median = data.get((int)((data_size/2)-0.5));
    }
  
    return median;
    }
    //Standard deviation
    public float getStd(){
        //Take root of variance
        return (float) Math.sqrt(var);
    }
    
    public void binDataCount(BinFormulae the_formulae){
        //Use the binmethod package to work out number of bins and then count the data in each bin
        
        //Working out the bin size so that we can work out number of data in each bin 
        //Find max and min and the difference and then divide it by number of bins 
        Binned_data = new ArrayList<Float>();
        bin_width = ((max-min)/the_formulae.getNumberOfBins());
        
        /* now that we have the bin_width we can count up from the min and everything that is within that bin gets counted
       
        */
        float range1 = min;
        float range2 = min+bin_width;
        for(int z = 0; z < the_formulae.getNumberOfBins(); z++){
            float sum = (float) 0.0;
            for(int i = 0; i < data.size(); i++){
                
                if(data.get(i) > range1 && data.get(i) <= range2){
                    sum += 1.0;
                } else {
                    sum += 0;
                }
            }
            
            Binned_data.add(sum);
                //Increase ranges
                range1 += bin_width;
                range2 += bin_width;
        } 
    }

    public List<Float> histoNorm(BinFormulae the_formulae){
        //Calculate the Normalised histogram
        
        /*
        Normalised frequency is obtained by dividing the number of observation in each bin by the unnormalized data count * bin size
        
        bin size = (max(measurementdata)-min(measurement data))/k and k is the number of bins
        
        */
        
        List<Float> Normalised_freq = new ArrayList<Float>();
        
        float sum1 = 0;
        for(int i = 0; i < Binned_data.size(); i++){
            sum1 += Binned_data.get(i)*bin_width;
        }
        
        for(int i = 0; i < Binned_data.size(); i++){
            Normalised_freq.add((Binned_data.get(i)/sum1)*bin_width);
            System.out.printf("%f binned_data \n", (Binned_data.get(i)/sum1)*bin_width);
        }
        // /data.size())*bin_width
        
        return Normalised_freq;
        
    }
    
}
