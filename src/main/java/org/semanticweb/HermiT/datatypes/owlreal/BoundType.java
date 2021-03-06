package org.semanticweb.HermiT.datatypes.owlreal;

public enum BoundType {
    INCLUSIVE,
    EXCLUSIVE;
    

    public BoundType getComplement() {
        return BoundType.values()[1 - this.ordinal()];
    }

    public static BoundType getMoreRestrictive(BoundType boundType1, BoundType boundType2) {
        int maxOrdinal = Math.max(boundType1.ordinal(), boundType2.ordinal());
        return BoundType.values()[maxOrdinal];
    }
}

