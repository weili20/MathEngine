package uk.co.ryanharrison.mathengine.parser.nodes;

import uk.co.ryanharrison.mathengine.BigRational;

public final class NodeRational extends NodeNumber
{
	private BigRational value;
	private static final int maxIterations = 150;
	private static final double epsilon = 1E-15;

	public NodeRational(BigRational rational)
	{
		this.value = rational;
	}

	public NodeRational(double value)
	{
		this.value = new BigRational(value, epsilon, maxIterations);
	}

	public NodeRational(int numerator, int denominator)
	{
		this.value = new BigRational(numerator, denominator);
	}

	@Override
	public NodeNumber add(NodeNumber arg2)
	{
		if (arg2 instanceof NodeRational)
			return new NodeRational(value.add(((NodeRational) arg2).value));
		else if(arg2 instanceof NodePercent)
			return new NodeRational(value.multiply(BigRational.ONE.add(new BigRational(arg2.doubleValue()))));
		else
			return new NodeDouble(doubleValue() + arg2.doubleValue());
	}

	@Override
	public NodeNumber clone()
	{
		return new NodeRational((BigRational) value.clone());
	}

	@Override
	public int compareTo(NodeConstant o)
	{
		if (o instanceof NodeRational)
		{
			return value.compareTo(((NodeRational) o).getValue());
		}
		else
		{
			return new NodeDouble(doubleValue()).compareTo(o);
		}
	}

	@Override
	public NodeNumber divide(NodeNumber arg2)
	{
		if (arg2 instanceof NodeRational)
			return new NodeRational(value.divide(((NodeRational) arg2).value));
		else if(arg2 instanceof NodePercent)
			return new NodeRational(value.divide(new BigRational(arg2.doubleValue())));
		else
			return new NodeDouble(doubleValue() / arg2.doubleValue());
	}

	@Override
	public double doubleValue()
	{
		return value.doubleValue();
	}

	@Override
	public boolean equals(Object object)
	{
		if (object instanceof NodeRational)
		{
			return value.equals(((NodeRational) object).getValue());
		}

		return false;
	}

	public BigRational getValue()
	{
		return this.value;
	}

	@Override
	public int hashCode()
	{
		return value.hashCode();
	}

	@Override
	public NodeNumber multiply(NodeNumber arg2)
	{
		if (arg2 instanceof NodeRational)
			return new NodeRational(value.multiply(((NodeRational) arg2).value));
		else if(arg2 instanceof NodePercent)
			return new NodeRational(value.multiply(new BigRational(arg2.doubleValue())));
		else
			return new NodeDouble(doubleValue() * arg2.doubleValue());
	}

	@Override
	public NodeNumber pow(NodeNumber arg2)
	{
		double exp = arg2.doubleValue();
		if (arg2 instanceof NodeRational && exp % 1.0 == 0)
			return new NodeRational(value.pow((long) exp));
		else if(arg2 instanceof NodePercent)
			return new NodeDouble(doubleValue()).pow(arg2);
		else
			return new NodeDouble(Math.pow(doubleValue(), exp));
	}

	@Override
	public NodeNumber subtract(NodeNumber arg2)
	{
		if (arg2 instanceof NodeRational)
			return new NodeRational(value.subtract(((NodeRational) arg2).value));
		else if(arg2 instanceof NodePercent)
			return new NodeRational(value.multiply(BigRational.ONE.subtract(new BigRational(arg2.doubleValue()))));
		else
			return new NodeDouble(doubleValue() - arg2.doubleValue());
	}

	@Override
	public String toString()
	{
		return value.toString();
	}

	@Override
	public String toTypeString()
	{
		return "rational";
	}
}
