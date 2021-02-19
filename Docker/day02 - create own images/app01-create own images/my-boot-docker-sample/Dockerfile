FROM centos

RUN yum install -y java

EXPOSE 8080

VOLUME /tmp
ADD target/my-boot-docker-sample.jar myapp.jar
RUN sh -c 'touch /myapp.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/myapp.jar"]