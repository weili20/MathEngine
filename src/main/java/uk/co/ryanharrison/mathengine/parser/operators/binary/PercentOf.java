package uk.co.ryanharrison.mathengine.parser.operators.binary;

import uk.co.ryanharrison.mathengine.parser.nodes.NodeConstant;
import uk.co.ryanharrison.mathengine.parser.nodes.NodeFactory;
import uk.co.ryanharrison.mathengine.parser.nodes.NodeNumber;
import uk.co.ryanharrison.mathengine.parser.operators.BinaryOperator;

import java.util.function.BiFunction;

public class PercentOf extends BinaryOperator
{
	@Override
	public String[] getAliases()
	{
		return new String[] { "%of", "percentof" };
	}

	@Override
	public int getPrecedence()
	{
		return 3;
	}

	@Override
	public String toLongString()
	{
		return "percent of";
	}

	@Override
	public NodeConstant toResult(NodeConstant arg1, NodeConstant arg2)
	{
		//return arg1.multiply(arg2.divide(NodeFactory.createNodeNumberFrom(100.0)));

		BiFunction<NodeNumber, NodeNumber, NodeConstant> multiplier = NodeNumber::multiply;
		BiFunction<NodeNumber, NodeNumber, NodeConstant> divider = NodeNumber::divide;
		NodeNumber number = NodeFactory.createNodeNumberFrom(100.0);

		NodeConstant middle;
		if (arg2 instanceof NodeNumber) {
			middle = arg2.applyUniFunc(elem -> divider.apply(elem.getTransformer().toNodeNumber(), number));
			return arg1.applyUniFunc(elem -> multiplier.apply(elem.getTransformer().toNodeNumber(), middle.getTransformer().toNodeNumber()));
		} else {
			// marshal to vector
			middle = arg2.getTransformer().toNodeVector()
					.applyBiFunc(number, divider);
			return arg1.getTransformer().toNodeVector()
					.applyBiFunc(middle, multiplier);
		}
	}

	@Override
	public String toString()
	{
		return "% of";
	}
}
