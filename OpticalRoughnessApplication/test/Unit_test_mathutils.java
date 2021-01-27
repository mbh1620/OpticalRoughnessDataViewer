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
import statutils.StatFormulae;
import mathutils.CurveFitter;

/**
 *
 * @author Matt
 */
public class Unit_test_mathutils {
    
    
    @Test
    public void main(){
    
        List<Double> exampleData = Arrays.asList(1.,2.,3.,4.,5.,6.,7.,8.,9.,10.,11.);
        
        //Instantiate the Statformula class
        StatFormulae StatFormulaeInstance = new StatFormulae(exampleData);
        
        SturgesFormula SturgesInstance = new SturgesFormula(exampleData);
        SturgesInstance.calculateNumberOfBins();
        System.out.printf("By Sturges Formula: %d \n", SturgesInstance.getNumberOfBins());
        StatFormulaeInstance.binDataCount(SturgesInstance);
        StatFormulaeInstance.histoNorm(SturgesInstance);
        
        CurveFitter CurveFitterInstance = new CurveFitter(StatFormulaeInstance.histoNorm(SturgesInstance));
        CurveFitterInstance.perform_calculations();
        
        System.out.printf("Normalisation factor = %f\n", CurveFitterInstance.getNormFactor());
        System.out.printf("Mean = %f\n", CurveFitterInstance.getMean());
        System.out.printf("Sigma = %f\n", CurveFitterInstance.getSigma());
    
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
