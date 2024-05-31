package com.delta.server.api.data;

public class PingFilesDTO {
    private final String hostname;
    private final String ip;
    private final int port;
    private final String info;
    private final String info2;
    private final LocationDTO location;
    private final String id;
    private final String key;

    public PingFilesDTO(String hostname, String ip, int port, String info, String info2,
                        LocationDTO location, String id, String key) {
        this.hostname = hostname;
        this.ip = ip;
        this.port = port;
        this.info = info;
        this.info2 = info2;
        this.location = location;
        this.id = id;
        this.key = key;
    }

    public String getHostname() {
        return hostname;
    }

    public String getIp() {
        return ip;
    }

    public int getPort() {
        return port;
    }

    public String getInfo() {
        return info;
    }

    public String getInfo2() {
        return info2;
    }

    public LocationDTO getLocation() {
        return location;
    }

    public String getId() {
        return id;
    }

    public String getKey() {
        return key;
    }
}
