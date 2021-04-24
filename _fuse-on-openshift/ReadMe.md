# Fuse-7 Examples

## Fuse on Openshift

This folder contains all the examples(Fuse on Openshift) which can be deployed to openshfit-4.x/CodeReady Containers.

Create the new project to deploy your project. (While testing/learning, it is recommended to create new project each time for smooth understanding)
```
	oc new-project dev-test-1
```

Your local terminal client is now using above mentioned new project(in this case, it is 'dev-test-1').

Now deploy your application:

```
	mvn oc:resource oc:deploy
```
