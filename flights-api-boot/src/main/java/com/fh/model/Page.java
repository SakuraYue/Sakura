package com.fh.model;

import lombok.Data;

/**
 * Created by gy on 2019/10/14.
 */
@Data
public class Page {
    private int draw;

    private int start;

    private int length;

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
