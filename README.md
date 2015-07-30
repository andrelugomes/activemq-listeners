ActiveMQ Topic Subscriber
=============

# Usage

Clone this project

## Creating executable JAR
```console
mvn package
```

Or, the full build

```console
mvn clean install
```

## Running the JAR
All JARs are in target folder

```console
cd target/
```

```console
java -jar activemq-topic-subscriber-1.0-SNAPSHOT-jar-with-dependencies.jar [topic-name] 
```