package com.proxy_provider.demo.data;

public interface ProxyProviderVars {
    String BASE_MAPPING = "/";
    String GET_QUERY = "SELECT IP, PORT, COUNTRY, ANONYMITY, IS_SSL, IS_HTTPS, LAST_CHECKED FROM proxy_list";
    String WHERE = " WHERE ";
    String AND = "AND";
    String WHITESPACE = " ";
    String IS_SSL = "IS_SSL = ";
    String IS_HTTPS = "IS_HTTPS = ";
    String IS_ANONYMITY = "ANONYMITY = ";
    String DB_HOST = "${DB_HOST}";
    String DB_PORT = "${DB_PORT}";
    String DB_NAME = "${DB_NAME}";
    String DB_USER = "${DB_USER}";
    String DB_PASSWORD = "${DB_PASSWORD}";
    String URL_PREFIX = "jdbc:mysql://";
    String SLASH = "/";
    String SSL_MODE = "?ssl-mode=REQUIRED"; // or "?ssl-mode=DISABLED"
    String COLON = ":";
    String IP = "IP";
    String PORT = "PORT";
    String COUNTRY = "COUNTRY";
    String ANONYMITY = "ANONYMITY";
    String ISSSL = "IS_SSL";
    String ISHTTPS = "IS_HTTPS";
    String LAST_CHECKED = "LAST_CHECKED";
    String ONE = "1";
    String ZERO = "0";
}
