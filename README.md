Version 2017.1.1 with project-stages.yml

mvn clean install && java -jar -Dswarm.project.stage=dev ./target/project-stages-test-0.0.1-SNAPSHOT-swarm.jar

Prints out the following:


[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 13.301 s
[INFO] Finished at: 2017-05-02T18:04:10+10:00
[INFO] Final Memory: 38M/480M
[INFO] ------------------------------------------------------------------------
2017-05-02 18:04:11,662 INFO  [org.wildfly.swarm] (main) WFSWARM0024: Using project stage: dev
2017-05-02 18:04:11,832 INFO  [org.wildfly.swarm] (main) WFSWARM0018: Installed fraction:                  Logging - STABLE          org.wildfly.swarm:logging:2017.1.1
2017-05-02 18:04:11,833 INFO  [org.wildfly.swarm] (main) WFSWARM0018: Installed fraction:          Bean Validation - STABLE          org.wildfly.swarm:bean-validation:2017.1.1
2017-05-02 18:04:11,833 INFO  [org.wildfly.swarm] (main) WFSWARM0018: Installed fraction:        CDI Configuration - STABLE          org.wildfly.swarm:cdi-config:2017.1.1


Notice:
Using project stage: dev

Great!!

--------------------------------------------------------------------------------------


Version 2017.5.0 with project-defaults.yml and project-dev.yml

I run:
mvn clean install && java -jar ./target/project-stages-test-0.0.1-SNAPSHOT-swarm.jar



[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 14.815 s
[INFO] Finished at: 2017-05-02T18:39:27+10:00
[INFO] Final Memory: 38M/431M
[INFO] ------------------------------------------------------------------------
2017-05-02 18:39:29,172 INFO  [org.wildfly.swarm] (main) WFSWARM0013: Installed fraction:                  Logging - STABLE          org.wildfly.swarm:logging:2017.5.0
2017-05-02 18:39:29,182 INFO  [org.wildfly.swarm] (main) WFSWARM0013: Installed fraction:                      JCA - STABLE          org.wildfly.swarm:jca:2017.5.0
2017-05-02 18:39:29,182 INFO  [org.wildfly.swarm] (main) WFSWARM0013: Installed fraction:                      JMX - STABLE          org.wildfly.swarm:jmx:2017.5.0
2017-05-02 18:39:29,182 INFO  [org.wildfly.swarm] (main) WFSWARM0013: Installed fraction:               Infinispan - STABLE          org.wildfly.swarm:infinispan:2017.5.0



Notice:
No reference to the project-<stage>.yml being used

--------------------------------------------------------------------------------------

Now I need to add the following to in Main.java to see what profile is being used:

String stage = config.resolve("project.stage").getValue();
System.out.println(stage);

Version 2017.5.0 with project-defaults.yml and project-dev.yml

I run:
mvn clean install && java -jar ./target/project-stages-test-0.0.1-SNAPSHOT-swarm.jar


[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 13.440 s
[INFO] Finished at: 2017-05-02T18:47:58+10:00
[INFO] Final Memory: 38M/441M
[INFO] ------------------------------------------------------------------------
default
2017-05-02 18:48:01,038 INFO  [org.wildfly.swarm] (main) WFSWARM0013: Installed fraction:                  Logging - STABLE          org.wildfly.swarm:logging:2017.5.0
2017-05-02 18:48:01,045 INFO  [org.wildfly.swarm] (main) WFSWARM0013: Installed fraction:                      JCA - STABLE          org.wildfly.swarm:jca:2017.5.0
2017-05-02 18:48:01,046 INFO  [org.wildfly.swarm] (main) WFSWARM0013: Installed fraction:                      JMX - STABLE          org.wildfly.swarm:jmx:2017.5.0
2017-05-02 18:48:01,047 INFO  [org.wildfly.swarm] (main) WFSWARM0013: Installed fraction:               Infinispan - STABLE          org.wildfly.swarm:infinispan:2017.5.0

Great!! I can see that stage-defaults.yml is being used.


--------------------------------------------------------------------------------------


Version 2017.5.0 with project-defaults.yml and project-dev.yml 

Still with the following in Main.java 

String stage = config.resolve("project.stage").getValue();
System.out.println(stage);

I run:
mvn clean install && java -jar ./target/project-stages-test-0.0.1-SNAPSHOT-swarm.jar --project=dev

And it still uses the default profile:

[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 13.440 s
[INFO] Finished at: 2017-05-02T18:47:58+10:00
[INFO] Final Memory: 38M/441M
[INFO] ------------------------------------------------------------------------
default
2017-05-02 18:48:01,038 INFO  [org.wildfly.swarm] (main) WFSWARM0013: Installed fraction:                  Logging - STABLE          org.wildfly.swarm:logging:2017.5.0
2017-05-02 18:48:01,045 INFO  [org.wildfly.swarm] (main) WFSWARM0013: Installed fraction:                      JCA - STABLE          org.wildfly.swarm:jca:2017.5.0
2017-05-02 18:48:01,046 INFO  [org.wildfly.swarm] (main) WFSWARM0013: Installed fraction:                      JMX - STABLE          org.wildfly.swarm:jmx:2017.5.0
2017-05-02 18:48:01,047 INFO  [org.wildfly.swarm] (main) WFSWARM0013: Installed fraction:               Infinispan - STABLE          org.wildfly.swarm:infinispan:2017.5.0




--------------------------------------------------------------------------------------


Now I run the same config with the old syntax

mvn clean install && java -jar -Dswarm.project.stage=dev ./target/project-stages-test-0.0.1-SNAPSHOT-swarm.jar


And it adhears to the deprecated syntax of -Dswarm.project.stage. This did not work with 2017.4.0. I think backwards compatiblity was adding in 2017.5.0

[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 13.760 s
[INFO] Finished at: 2017-05-02T18:57:34+10:00
[INFO] Final Memory: 38M/421M
[INFO] ------------------------------------------------------------------------
dev
2017-05-02 18:57:36,634 INFO  [org.wildfly.swarm] (main) WFSWARM0013: Installed fraction:                  Logging - STABLE          org.wildfly.swarm:logging:2017.5.0
2017-05-02 18:57:36,643 INFO  [org.wildfly.swarm] (main) WFSWARM0013: Installed fraction:                      JCA - STABLE          org.wildfly.swarm:jca:2017.5.0
2017-05-02 18:57:36,643 INFO  [org.wildfly.swarm] (main) WFSWARM0013: Installed fraction:                      JMX - STABLE          org.wildfly.swarm:jmx:2017.5.0
2017-05-02 18:57:36,644 INFO  [org.wildfly.swarm] (main) WFSWARM0013: Installed fraction:               Infinispan - STABLE          org.wildfly.swarm:infinispan:2017.5.0


Success!! The project-stage of dev is pulled in.

