/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import binmethod.*;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Matt
 */
public class Unit_test_binmethod {

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void main(){
        //Creat an array of data 
        List<Double> exampleData = Arrays.asList(1.,2.,3.,4.,5.,6.,7.,8.,9.,10.,11.);
        
        //Test SturgesFormula Class
        SturgesFormula SturgesInstance = new SturgesFormula(exampleData);
        SturgesInstance.calculateNumberOfBins();
        System.out.printf("By Sturges Formula: %d \n", SturgesInstance.getNumberOfBins());
        //Add Asserts 
        
        //Test SquareRootChoice Class
        SquareRootChoice SquareRootChoiceInstance = new SquareRootChoice(exampleData);
        SquareRootChoiceInstance.calculateNumberOfBins();
        System.out.printf("By Square Root Formula: %d \n", SquareRootChoiceInstance.getNumberOfBins());
        //Add Asserts
        
        //Test RiceRule class
        RiceRule RiceRuleInstance = new RiceRule(exampleData);
        RiceRuleInstance.calculateNumberOfBins();
        System.out.printf("By Rice Rule Formala: %d \n", RiceRuleInstance.getNumberOfBins());
        //Add Asserts
       
    }
    
    
}
