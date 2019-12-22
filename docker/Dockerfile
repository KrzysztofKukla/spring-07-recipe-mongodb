FROM centos

RUN yum install -y java

VOLUME tmp
ADD 05-recipe-0.0.1-SNAPSHOT.jar recipe.jar
RUN sh -c 'touch /recipe.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/recipe.jar"]