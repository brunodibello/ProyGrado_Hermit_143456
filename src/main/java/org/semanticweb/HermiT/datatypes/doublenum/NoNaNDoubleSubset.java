package org.semanticweb.HermiT.datatypes.doublenum;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.semanticweb.HermiT.datatypes.ValueSpaceSubset;

public class NoNaNDoubleSubset
implements ValueSpaceSubset {
    protected final List<DoubleInterval> m_intervals;

    public NoNaNDoubleSubset() {
        this.m_intervals = Collections.emptyList();
    }

    public NoNaNDoubleSubset(DoubleInterval interval) {
        this.m_intervals = Collections.singletonList(interval);
    }

    public NoNaNDoubleSubset(List<DoubleInterval> intervals) {
        this.m_intervals = intervals;
    }

    @Override
    public boolean hasCardinalityAtLeast(int number) {
        int left = number;
        for (int index = this.m_intervals.size() - 1; left > 0 && index >= 0; --index) {
            left = this.m_intervals.get(index).subtractSizeFrom(left);
        }
        return left == 0;
    }

    @Override
    public boolean containsDataValue(Object dataValue) {
        if (dataValue instanceof Double) {
            double number = (Double)dataValue;
            for (int index = this.m_intervals.size() - 1; index >= 0; --index) {
                if (!this.m_intervals.get(index).contains(number)) continue;
                return true;
            }
        }
        return false;
    }

    @Override
    public void enumerateDataValues(Collection<Object> dataValues) {
        for (int index = this.m_intervals.size() - 1; index >= 0; --index) {
            this.m_intervals.get(index).enumerateNumbers(dataValues);
        }
    }

    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("xsd:double{");
        for (int index = 0; index < this.m_intervals.size(); ++index) {
            if (index == 0) {
                buffer.append('+');
            }
            buffer.append(this.m_intervals.get(index).toString());
        }
        buffer.append('}');
        return buffer.toString();
    }
}

