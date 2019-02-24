package uk.co.ryanharrison.mathengine.parser.nodes;

import uk.co.ryanharrison.mathengine.unitconversion.units.SubUnit;

public class NodeUnit extends NodeDouble {

    private Node value;
    private SubUnit unit;
    private boolean hasValue;

    public NodeUnit(SubUnit unit, Node val) {
        this(unit, val, true);
    }

    private NodeUnit(SubUnit unit, Node val, boolean hasValue) {
        super(1.0);
        this.unit = unit;
        this.value = val;
        this.hasValue = hasValue;
    }

    public SubUnit getUnit() {
        return unit;
    }

    public Node getValue() {
        return value;
    }

    @Override
    public double doubleValue() {
        return value.getTransformer().toNodeNumber().doubleValue();
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof NodeUnit) {
            NodeUnit node = ((NodeUnit) object);
            return unit.equals(node.getUnit()) && doubleValue() == node.doubleValue();
        }
        return false;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((unit == null) ? 0 : unit.hashCode());
        return result;
    }

    @Override
    public NodeDouble copy() {
        return new NodeUnit(unit, value);
    }

    @Override
    public NodeTransformer createTransformer() {
        return new NodeUnitTransformer();
    }

    private class NodeUnitTransformer implements NodeTransformer {
        @Override
        public NodeVector toNodeVector() {
            return new NodeVector(new Node[]{toNodeNumber()});
        }

        @Override
        public NodeMatrix toNodeMatrix() {
            return new NodeMatrix(new Node[][]{{toNodeNumber()}});
        }

        @Override
        public NodeNumber toNodeNumber() {
            return value.getTransformer().toNodeNumber();
        }
    }

    @Override
    public String toString() {
        if (hasValue) {
            if (doubleValue() == 1)
                return getUnit().getSingular();

            if (hasValue)
                return doubleValue() + " " + getUnit().getPlural();
        }

        return getUnit().getPlural();
    }
}
