package com.proxy_provider.demo.service;

import com.proxy_provider.demo.data.Proxy;
import com.proxy_provider.demo.data.ProxyProviderVars;
import com.proxy_provider.demo.util.DatabaseHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;

/**
    TABLE FORMAT:
    String ip = cells.get(0).getText();
    String port = cells.get(1).getText();
    String country = cells.get(3).getText();
    String anonymity = cells.get(4).getText();
    String https = cells.get(6).getText();
    String lastChecked = cells.get(7).getText();
**/
@Service
public class ProxyProviderService {
    private final DatabaseHelper databaseHelper;

    @Autowired
    public ProxyProviderService(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }
    public Proxy getProxy(Boolean isSSL, Boolean isHTTPS, String anonymity) {
        String query = buildQuery(isSSL, isHTTPS, anonymity);
        List<Proxy> proxies = fetchProxies(query);
        Random random = new Random();

        if (proxies.isEmpty()) {
            return null;
        }

        return proxies.get(random.nextInt(proxies.size()));
    }
    @Cacheable(value = "proxiesCache", key = "#query")
    public List<Proxy> fetchProxies(String query) {
        return databaseHelper.getProxies(query);
    }
    @CacheEvict(value = "proxiesCache", allEntries = true)
    public void clearCache() {
    }
    private String buildQuery(Boolean isSSL, Boolean isHTTPS, String anonymity) {
        StringBuilder queryBuilder = new StringBuilder(ProxyProviderVars.GET_QUERY);
        boolean whereClauseAdded = false;

        if(isSSL!= null) {
            queryBuilder.append(ProxyProviderVars.WHERE)
                    .append(ProxyProviderVars.IS_SSL)
                    .append((isSSL) ? ProxyProviderVars.ONE : ProxyProviderVars.ZERO);
            whereClauseAdded = true;
        }
        if(isHTTPS!= null) {
            if(whereClauseAdded) {
                queryBuilder.append(ProxyProviderVars.WHITESPACE)
                        .append(ProxyProviderVars.AND)
                        .append(ProxyProviderVars.IS_HTTPS)
                        .append((isHTTPS) ? ProxyProviderVars.ONE : ProxyProviderVars.ZERO);
            } else {
                queryBuilder.append(ProxyProviderVars.WHERE)
                       .append(ProxyProviderVars.IS_HTTPS)
                       .append((isHTTPS) ? ProxyProviderVars.ONE : ProxyProviderVars.ZERO);
                whereClauseAdded = true;
            }
        }
        if(anonymity!= null) {
            if(whereClauseAdded) {
                queryBuilder.append(ProxyProviderVars.WHITESPACE)
                        .append(ProxyProviderVars.AND)
                        .append(ProxyProviderVars.IS_ANONYMITY)
                        .append(anonymity);
            } else {
                queryBuilder.append(ProxyProviderVars.WHERE)
                       .append(ProxyProviderVars.IS_ANONYMITY)
                       .append(anonymity);
            }
        }

        return queryBuilder.toString();
    }
}
