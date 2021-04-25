# timer-seda component example

This example demonstrates how you can use Apache Camel with Spring Boot to deal with timer and seda components.

### Building

The example can be built with

    mvn clean package -DskipTests

    OR

    mvn clean package


## Build Docker/S2I image


    mvn oc:resource oc:build


## Deploy to openshift-4.x


    mvn oc:deploy


## Check Hawtio

All the resource utilization can be monitored using Hawtio:

http://localhost:8080/actuator/hawtio

If you have changed config in properties file for Hawtio, all the resource utilization can be monitored using Hawtio:

http://localhost:8080/hawtio

## External properties

Let's create a config map for the few properties in openshfit using below command
    
    oc create configmap camel-config --from-file=configuration/application.properties

Then, go to current project in openshift and click on `DeploymentConfig` in `Inventory` section

Once, it is open then select the application deployment config for which you want to apply external properties. Simply click on respective deployment config(DC).

Now, you should see Deployment Config details. Please click on `Environment` menu.

And, inside `All values from existing ConfigMaps or Secrets (envFrom)` section, please configure as below:

    CONFIGMAP/SECRET -> prod-app-config
    PREFIX (OPTIONAL) -> <Leave it blank>

`SAVE` this config.