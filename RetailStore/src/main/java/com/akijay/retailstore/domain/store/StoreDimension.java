package com.akijay.retailstore.domain.store;

import java.util.Objects;

/**
 * Created by vijay on 10/22/16.
 */
public class StoreDimension {

    private int length;
    private int width;

    StoreDimension(int length, int width) {
        this.length = length;
        this.width = width;
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof StoreDimension) {
            StoreDimension other = (StoreDimension) o;
            return other.length == length
                    && other.width == width;
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(width, length);
    }
}
