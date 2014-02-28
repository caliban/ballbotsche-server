Purpose
--------
Technology test project. May be in the future the webapp for ballbotsche application

Technology
----------
* jax / rs
* consumed by angular js 
* or android client (coming soon tm)
* service layer a mix of cdi and ejb
* data access through jpa and querydsl
* JBoss Wildfly
* Mysql 5+
* goal is to deploy to the openshift cloud.

Migration and warnings 
---------
* Arquillian and Wildfly: You have to provide a jboss.home (for example in the settings.xml)
* Arquillian tests need wildfly with no customized ports. If you change them in the standalone.xml arquillian will not pick them up. <= investigate. 
* .setHint("javax.persistence.cache.storeMode", "REFRESH") triggered a fancy exception. <= investigate

Tests
-----
Soon (tm)

