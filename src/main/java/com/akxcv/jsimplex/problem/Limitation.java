package com.akxcv.jsimplex.problem;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

public class Limitation {

	public enum LimitationSign { LE, EQ, GE }
	
	private double[] coefs;
	private Variable[] variables;
	private LimitationSign sign;
	private double freeTerm;

	public Limitation(double[] coefs, Variable[] variables, LimitationSign sign, double freeTerm) {
		this.coefs = coefs;
		this.variables = variables;
		this.sign = sign;
		this.freeTerm = freeTerm;
	}
	
	public String toString() {
        String string = "";

        NumberFormat nf = NumberFormat.getNumberInstance(Locale.US);
        DecimalFormat df = (DecimalFormat)nf;
        df.applyPattern("0.####");

        if (coefs[0] != 0)
            string += df.format(coefs[0]) + variables[0] + " ";
		
		for (int i = 1; i < coefs.length; i++) {
			if (coefs[i] != 0) {
				if (Math.signum(coefs[i]) >= 0)
					string += "+";
				else
					string += "-";
				string += df.format(Math.abs(coefs[i])) + variables[i] + " ";
			}
		}
		
		switch(sign) {
			
			case LE:
			string += "<= ";
			break;
			
			case EQ:
			string += "= ";
			break;
			
			case GE:
			string += ">= ";
			break;
			
			default:
			break;
		}
		
		string += df.format(freeTerm);
		
		return string;
	}

	public double getCoef(int number) {
		return coefs[number];
	}

    public double getCoef(Variable variable) {
        ArrayList<Variable> variableList = new ArrayList<>(Arrays.asList(variables));
        if (variableList.contains(variable))
            return coefs[variableList.indexOf(variable)];
        return 0;
    }

    public double[] getCoefs() {
        return coefs;
    }
	
	public int getCoefCount() {
		return coefs.length;
	}

    public Variable[] getVariables() {
        return variables;
    }
	
	public LimitationSign getSign() {
		return sign;
	}

	public double getFreeTerm() {
		return freeTerm;
	}
	
}
