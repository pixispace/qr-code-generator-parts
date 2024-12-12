package com.pixispace.delqrcodegenerator;

public enum ImageSize {
    small(300),
    medium(640),
    large(800);

    final int size;

    ImageSize(int size) {
        this.size = size;
    }
}
