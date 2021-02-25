package com.valtech.matrix;

import java.util.List;

public interface DisplayInterface {
    public List<List<String>> getFullFrame();
    public void setMatrixDimensions(int height, int width);
}
