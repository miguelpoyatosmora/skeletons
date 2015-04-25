## Scalable CRUD key value store based on Akka and Spring.

Example on how to partition a map using Akka

### Test and Compilation

`mvn install`

### Configuration

Configure the deployment placing an application.conf with the actor deployment for `AggregatorActor` and `CoordinatorActor`

### Execution 

`mvn exec:java  -Dexec.mainClass=com.miguelpoyatosmora.spring.Application`

### Enhancements 

Implement replication