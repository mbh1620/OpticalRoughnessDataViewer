/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import binmethod.SturgesFormula;
import java.util.Arrays;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import statutils.*;
/**
 *
 * @author Matt
 */
public class Unit_test_statutils {
    
    @Test
    public void main(){
        
        List<Double> exampleData = Arrays.asList(1.,2.,3.,4.,5.,6.,7.,8.,9.,10.,11.);
        
        //Instantiate the Statformula class
        StatFormulae StatFormulaeInstance = new StatFormulae(exampleData);
        
        //Now use the different methods to test them
        
        //Mean
        System.out.printf("Mean of data: %f \n",StatFormulaeInstance.getMean());
        assertEquals(6.000000,StatFormulaeInstance.getMean(),0.000001);
        //Variance
        System.out.printf("Variance of data: %f \n", StatFormulaeInstance.getVar());
        assertEquals(10.000000,StatFormulaeInstance.getVar(),0.000001);
        //Max
        System.out.printf("Max of data: %f \n", StatFormulaeInstance.getMax());
        assertEquals(11.000000,StatFormulaeInstance.getMax(),0.000001);
        //Min
        System.out.printf("Min of data: %f \n", StatFormulaeInstance.getMin());
        assertEquals(1.000000,StatFormulaeInstance.getMin(),0.000001);
        //Median
        System.out.printf("Median of data: %f \n", StatFormulaeInstance.getMedian());
        assertEquals(6.000000,StatFormulaeInstance.getMedian(),0.000001);
        //Standard Deviation
        System.out.printf("Standard Deviation of data: %f \n", StatFormulaeInstance.getStd());
        assertEquals(3.162278,StatFormulaeInstance.getStd(),0.000001);
        
        //Test SturgesFormula Class
        SturgesFormula SturgesInstance = new SturgesFormula(exampleData);
        SturgesInstance.calculateNumberOfBins();
        System.out.printf("By Sturges Formula: %d \n", SturgesInstance.getNumberOfBins());
        StatFormulaeInstance.binDataCount(SturgesInstance);
        
        //Test the stat formulae for creating the Binned Data and the Normalised data (Normalised should add up to approximately 1.00)
        for(int i = 0; i < SturgesInstance.getNumberOfBins(); i++){
            System.out.printf("%.2f \n",StatFormulae.getBinnedData().get(i));
            System.out.printf("%.2f \n",StatFormulaeInstance.histoNorm(SturgesInstance).get(i));
        }
        //Add Asserts 
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
