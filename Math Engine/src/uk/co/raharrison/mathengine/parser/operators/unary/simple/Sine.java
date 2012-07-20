package uk.co.raharrison.mathengine.parser.operators.unary.simple;

import uk.co.raharrison.mathengine.parser.AngleUnit;
import uk.co.raharrison.mathengine.parser.nodes.NodeFactory;
import uk.co.raharrison.mathengine.parser.nodes.NodeNumber;
import uk.co.raharrison.mathengine.parser.operators.TrigOperator;

public class Sine extends TrigOperator
{
	@Override
	public NodeNumber getResult(NodeNumber num, AngleUnit unit)
	{
		double result = Math.sin(super.radiansTo(num.doubleValue(), unit));
		
		return NodeFactory.createNodeNumberFrom(result);
	}
}
