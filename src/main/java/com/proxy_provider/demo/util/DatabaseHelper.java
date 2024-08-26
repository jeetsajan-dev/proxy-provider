package com.proxy_provider.demo.util;

import com.proxy_provider.demo.data.Proxy;
import com.proxy_provider.demo.data.ProxyProviderVars;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class DatabaseHelper {
    private static final String HOST = System.getenv(ProxyProviderVars.DB_HOST);
    private static final String PORT = System.getenv(ProxyProviderVars.DB_PORT);
    private static final String DBNAME = System.getenv(ProxyProviderVars.DB_NAME);
    private static final String USER = System.getenv(ProxyProviderVars.DB_USER);
    private static final String PASSWORD = System.getenv(ProxyProviderVars.DB_PASSWORD);
    private static final String URL = ProxyProviderVars.URL_PREFIX + HOST + ProxyProviderVars.COLON + PORT +
            ProxyProviderVars.SLASH + DBNAME + ProxyProviderVars.SSL_MODE;
    public List<Proxy> getProxies(String query) {
        List<Proxy> proxies = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                proxies.add(new Proxy(resultSet.getString(ProxyProviderVars.IP), resultSet.getInt(ProxyProviderVars.PORT),
                        resultSet.getString(ProxyProviderVars.COUNTRY), resultSet.getString(ProxyProviderVars.ANONYMITY),
                        resultSet.getBoolean(ProxyProviderVars.ISSSL), resultSet.getBoolean(ProxyProviderVars.ISHTTPS),
                        resultSet.getString(ProxyProviderVars.LAST_CHECKED)));
            }
            System.out.println("Successfully fetched " + proxies.size() + " proxies from the database");
        } catch (SQLException e) {
            System.err.println("Error fetching proxies from the database: " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.err.println("Error closing database resources: " + e.getMessage());
            }
        }
        return proxies;
    }
}
