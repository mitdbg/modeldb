package ai.verta.modeldb.common.config;

import org.hibernate.dialect.MySQL5Dialect;
import org.hibernate.dialect.PostgreSQLDialect;
import org.hibernate.dialect.SQLServerDialect;

public class RdbConfig {
  public String RdbDatabaseName;
  // TODO: replace driver with "io.opentracing.contrib.jdbc.TracingDriver" if tracing is enabled
  public String RdbDriver;
  public String RdbDialect;
  public String RdbUrl;
  public String RdbUsername;
  public String RdbPassword;
  public String sslMode = "DISABLED";
  public Integer maxAllowedPacket = 4194304; // bytes
  private final static int MAX_ALLOWED_PACKET_1GB = 1073741824;

  public void Validate(String base) throws InvalidConfigException {
    if (RdbDatabaseName == null || RdbDatabaseName.isEmpty())
      throw new InvalidConfigException(base + ".RdbDatabaseName", Config.MISSING_REQUIRED);
    if (RdbDriver == null || RdbDriver.isEmpty())
      throw new InvalidConfigException(base + ".RdbDriver", Config.MISSING_REQUIRED);
    if (RdbDialect == null || RdbDialect.isEmpty())
      throw new InvalidConfigException(base + ".RdbDialect", Config.MISSING_REQUIRED);
    if (RdbUrl == null || RdbUrl.isEmpty())
      throw new InvalidConfigException(base + ".RdbUrl", Config.MISSING_REQUIRED);
    if (RdbUsername == null || RdbUsername.isEmpty())
      throw new InvalidConfigException(base + ".RdbUsername", Config.MISSING_REQUIRED);
    if (sslMode == null || sslMode.isEmpty())
      throw new InvalidConfigException(base + ".sslMode", Config.MISSING_REQUIRED);
    maxAllowedPacket = maxAllowedPacket > MAX_ALLOWED_PACKET_1GB ?
        MAX_ALLOWED_PACKET_1GB : maxAllowedPacket;
  }

  public boolean isPostgres() {
    return RdbDialect.equals(PostgreSQLDialect.class.getName());
  }

  public boolean isMysql() {
    return RdbDialect.equals(MySQL5Dialect.class.getName());
  }

  public boolean isMssql() {
    return RdbDialect.equals(SQLServerDialect.class.getName());
  }
}
