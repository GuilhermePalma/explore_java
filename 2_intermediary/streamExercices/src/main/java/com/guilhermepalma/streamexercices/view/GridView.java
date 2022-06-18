package com.guilhermepalma.streamexercices.view;

import lombok.AllArgsConstructor;
import lombok.Data;

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
}
