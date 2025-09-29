package com.hsingh.flowcap.algorithm;

// Inner class to represent each bucket's state
public class LeakyBucket {
    private final int capacity;
    private final double leakRatePerMillis; // tokens per millisecond
    private double water; // current "water" in the bucket
    private long lastUpdateTime;

    public LeakyBucket(int capacity, double leakRatePerSecond) {
        this.capacity = capacity;
        this.leakRatePerMillis = leakRatePerSecond / 1000.0;
        this.water = 0.0;
        this.lastUpdateTime = System.currentTimeMillis();
    }

    public synchronized boolean allowRequest() {
        long now = System.currentTimeMillis();
        double leaked = (now - lastUpdateTime) * leakRatePerMillis;
        water = Math.max(0, water - leaked);
        lastUpdateTime = now;

        if (water < capacity) {
            water += 1;
            return true;
        } else {
            return false;
        }
    }
}
