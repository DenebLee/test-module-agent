package nanoit.kr;


public enum DatabaseType {
    TYPE_ORACLE("ORACLE"),
    TYPE_ORACLE_URL("ORACLE_URL"),
    TYPE_MYSQL("MYSQL"),
    TYPE_MSSQL("MSSQL"),
    TYPE_MARIADB("MARIADB"),
    TYPE_POSTGRESQL("POSTGRESQL");

    private final String property;

    DatabaseType(String property) {
        this.property = property;
    }

    public static DatabaseType fromPropertyName(String x) {
        for (DatabaseType currentType : DatabaseType.values()) {
            if (x.equals(currentType.getProperty())) {
                return currentType;
            }
        }
        return null;
    }

    public static String getPort(String type) {
        switch (type) {
            case "MYSQL":
            case "MARIADB":
                return "3306";
            case "ORACLE":
                return "1521";
            case "MSSQL":
                return "1433";
            case "SYBASE":
                return "5000";
            case "POSTGRESQL":
                return "5432";
            default:
                return "";
        }
    }

    public String getProperty() {
        return property;
    }

    @Override
    public String toString() {
        return property;
    }
}
