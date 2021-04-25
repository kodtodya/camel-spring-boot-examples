# Fuse-7 Examples

## Fuse on Openshift

This folder contains all the examples(Fuse on Openshift) which can be deployed to openshfit-4.x/CodeReady Containers.

Create the new project to deploy your project. (While testing/learning, it is recommended to create new project each time for smooth understanding)
	
	oc new-project dev-test-1

Your local terminal client is now using above mentioned new project(in this case, it is 'dev-test-1').

Let's build application image:

	mvn oc:resource oc:build

Now deploy your application:

	mvn oc:resource oc:deploy

Please follow below sequence for the examples to have smooth understanding:

## Part-1:
- timer-log-example
- quartz2-component-example
- rest-camel-example
- http-camel-example
- file-component-example
- ehcache-camel-example
- splitter-aggregator-example
- splitter-aggregator-file-example
- jms-config-shread-data-config

## Part-2:
- rest-jms-file-example
- camel-sql-example
- kafka-camel-example
- reactive-streams-example
- encryption-decryption-example
- Performance tuning for camel & amq/messaging
- Clustering with cellar
- camel-transformation-example
- camel-rest-junit-example
- camel-file-junit-example
