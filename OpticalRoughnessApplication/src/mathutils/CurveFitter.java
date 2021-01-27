/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathutils;
import static java.lang.Math.exp;
import static java.lang.Math.pow;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.fitting.*;
/**
 *
 * @author Matt
 */
public class CurveFitter {
    
    private static WeightedObservedPoints obs;
    private static List<Float> Normalised_freq;
    private static double[] parameters;
    private static List<Float> fitted_line;
    private static double alpha;
    private static double muo;
    private static double sigma;
    
    public CurveFitter(List<Float> norm){
        Normalised_freq = new ArrayList<Float>();
        Normalised_freq = norm;
    }
    
    public double[] Get_fitted_data(){
        return parameters;
    }
    
    public static void perform_calculations(){
        
        obs = new WeightedObservedPoints();
        
        for(int i = 0; i < Normalised_freq.size(); i++){
            obs.add(i, Normalised_freq.get(i));
        }
        parameters = GaussianCurveFitter.create().fit(obs.toList());
        alpha = parameters[0];
        muo = parameters[1];
        sigma = parameters[2]; 
    }
    
    public static List<Float> CreateLineData(){
        
        //y = alpha * e ^ (-0.5((x-muo)/sigma)^2
        fitted_line = new ArrayList<Float>();
        float raised;
        float start;
        System.out.printf("%f Alpha \n", alpha);
        System.out.printf("%f Muo \n", muo);
        System.out.printf("%f Sigma \n", sigma);
        for(int i = 0; i < Normalised_freq.size(); i++)
        {
            raised = (float) (-0.5*pow(((i-muo)/sigma),2));
            start = (float) (alpha * exp(raised));
            fitted_line.add(start); 
        }
        return fitted_line;
    }
    
    public double getNormFactor(){
        return parameters[0];
    }
    
    public double getMean(){
        return parameters[1];
    }
    
    public double getSigma(){
        return parameters[2];
    }
    
}
