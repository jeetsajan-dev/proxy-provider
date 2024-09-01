package com.proxy_provider.demo.controller;

import com.proxy_provider.demo.data.Proxy;
import com.proxy_provider.demo.service.ProxyProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProxyProviderController {
    @Autowired
    private final ProxyProviderService proxyProviderService;

    public ProxyProviderController(ProxyProviderService proxyProviderService) {
        this.proxyProviderService = proxyProviderService;
    }

    @GetMapping("/proxy")
    public ResponseEntity<Proxy> getProxy(@RequestParam(required = false) Boolean isSSL,
                                          @RequestParam(required = false) Boolean isHTTPS,
                                          @RequestParam(required = false) String anonymity) {
        Proxy proxy = proxyProviderService.getProxy(isSSL, isHTTPS, anonymity);
        if (proxy == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(proxy, HttpStatus.OK);
    }
}
