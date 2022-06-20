package com.guilhermepalma.streamexercices.view;

import com.guilhermepalma.streamexercices.util.Util;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Data
@AllArgsConstructor
public class GridView<T> {

    private List<T> values;
    private TotalView total;

    public GridView(List<T> values, Long total) {
        this.values = values;
        this.total = new TotalView(total);
    }

    public GridView(T value, Long total) {
        this.values = Collections.singletonList(value);
        this.total = new TotalView(total);
    }

    public Object getUniqueOrMultipleValues() {
        if (Util.isNullOrEmpty(values)) return null;
        return values.size() == 1 ? values.get(0) : values;
    }
}
