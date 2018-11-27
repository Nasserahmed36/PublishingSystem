package com.atypon.managing;

public interface SavePoint {
    void write(String value);

    String read();

    boolean exists();

    void remove();
}
