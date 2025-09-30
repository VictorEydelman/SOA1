# Запуск asadmin
asadmin
asadmin start-domain
# Создание connection pool
asadmin create-jdbc-connection-pool \
  --datasourceclassname=org.postgresql.ds.PGConnectionPoolDataSource \
  --restype=javax.sql.ConnectionPoolDataSource \
  --property user=s291485:password=qz6XMfGNKAgNOWkq:serverName=localhost:portNumber=5432:databaseName=studs \
  postgresPool

# Создание jdbc resource
asadmin create-jdbc-resource --connectionpoolid postgresPool jdbc/postgresDS

# Проверка connection pool
asadmin ping-connection-pool postgresPool

mvn clean package

# Деплой на сервер
deploy "C:\Users\veyde\OneDrive - ITMO UNIVERSITY\Работы ИТМО\СОА\SOA1\Service1\target\Service1-1.0-SNAPSHOT.war"
java -jar ../payara-micro-6.2025.9.jar --deploy ./target/Service1-1.0-SNAPSHOT.war --port 8089
# Или через asadmin консоль
asadmin> deploy /path/to/jaxrs-jpa-app.war

create-jdbc-connection-pool --datasourceclassname=org.postgresql.ds.PGSimpleDataSource --restype=javax.sql.DataSource --property user=s291485:password=qz6XMfGNKAgNOWkq:serverName=localhost:portNumber=5432:databaseName=studs postgresPool