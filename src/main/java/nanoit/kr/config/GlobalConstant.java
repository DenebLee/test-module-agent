package nanoit.kr.config;

import java.text.SimpleDateFormat;

public final class GlobalConstant {
    public static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd H:mm:ss");
    public static final String AGENT_VERSION = "Lee-AGENT-1.0.0";

    // DRIVER
    public static final String POSTGRESQL_DRIVER = "org.postgresql.Driver";
    public static final String ORACLE_DRIVER = "com.mysql.jdbc.Driver";
    public static final String MYSQL_DRIVER = ""; // not yet


    // FILE
    public static final String CONFIG_FILE_PATH = System.getProperty("user.dir") + "/config";
    public static final String FILE_FORMAT = ".xml";
    public static final String AGENT_DEFAULT_ENCODING = "MS949";

    // POLICY
    public static final int SMS_TPS = 80;
    public static final int MMS_TPS = 20;
    public static final int KMS_TPS = 20;
    public static final long SEND_INTERVAL_MILLIS = 1000;


    // SOCKET

    public static final int MAX_POOL_SIZE = 10;
    public static final String SERVER_HOST = "localhost";
    public static final int SERVER_PORT = 12345;
}
