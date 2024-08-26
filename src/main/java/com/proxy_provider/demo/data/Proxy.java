package com.proxy_provider.demo.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Proxy {
    private String ip;
    private int port;
    private String country;
    private String anonymity;
    private boolean isSSL;
    private boolean isHTTPS;
    private String lastChecked;
}
