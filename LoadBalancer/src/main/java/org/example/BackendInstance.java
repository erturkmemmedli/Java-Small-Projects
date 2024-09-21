package org.example;

public class BackendInstance {
    private final String ipAddress;
    private int connectionCount;

    public BackendInstance(String ipAddress, int connectionCount) {
        this.ipAddress = ipAddress;
        this.connectionCount = connectionCount;
    }

    public BackendInstance(String ipAddress) {
        this(ipAddress, 0);
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public int getConnectionCount() {
        return connectionCount;
    }

    public void incrementConnectionCount() {
        this.connectionCount++;
    }

    @Override
    public String toString() {
        return ipAddress + ": " + connectionCount + " connections";
    }
}
