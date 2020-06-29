# Implementing the Outbox Pattern

## Building and Installing required tools.

#### 1: Build the  `custom-debezium-connect` component.

```shell
cd custom-debezium-transformer
mvn clean install
docker build -t custom-debezium-transformer .
```

#### 2: Run the Docker-Compose under the project folder to install all the needed tools.

```shell
docker-compose up -d
```

## Setting up Kafka topics and Kafka Connectors.

#### - Setting up needed Kafka Topics
```shell
docker exec -t kafka /usr/bin/kafka-topics \
      --create --bootstrap-server :9092 \
      --topic student_enrolled \
      --partitions 1 \
      --replication-factor 1
```

#### - Linking the Debezium Kafka Connect With the Outbox Table
```shell
curl -X POST \
  http://localhost:8083/connectors/ \
  -H 'content-type: application/json' \
  -d '{
   "name": "student-outbox-connector",
   "config": {
      "connector.class": "io.debezium.connector.postgresql.PostgresConnector",
      "tasks.max": "1",
      "database.hostname": "postgres",
      "database.port": "5432",
      "database.user": "user",
      "database.password": "password",
      "database.dbname": "studentdb",
      "database.server.name": "pg-outbox-server",
      "tombstones.on.delete": "false",
      "table.whitelist": "public.outbox",
      "transforms": "outbox",
      "transforms.outbox.type": "com.debezium.custom.transformer.CustomTransformation"
   }
}'
```

#### - Consuming Kafka Topics
```shell
docker exec -t kafka /usr/bin/kafka-console-consumer \
      --bootstrap-server :9092 \
      --from-beginning \
      --topic student_enrolled

docker exec -t kafka /usr/bin/kafka-console-consumer \
      --bootstrap-server :9092 \
      --from-beginning \
      --topic student_email_changed
```

## Service endpoints.

#### Create Student
```shell
curl -X POST \
  'http://localhost:8080/students/~/enroll' \
  -H 'content-type: application/json' \
  -d '{ 
    "name": "Megan Clark",
    "email": "mclark@gmail.com",
    "address": "Toronto, ON"
}'
```
