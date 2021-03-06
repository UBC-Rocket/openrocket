package net.sf.openrocket.optimization.rocketoptimization.goals;

import net.sf.openrocket.optimization.rocketoptimization.OptimizationGoal;

/**
 * An optimization goal that maximizes a function value.  The method simply
 * returns the opposite of the function value.
 * 
 * @author Sampo Niskanen <sampo.niskanen@iki.fi>
 */
public class MaximizationGoal implements OptimizationGoal {
	
	@Override
	public double getMinimizationParameter(double value) {
		return -value;
	}
	
}
