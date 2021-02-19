FROM centos

RUN yum install -y java

EXPOSE 8081

VOLUME /tmpsoap
ADD target/my-simple-soap.jar myapp.jar
RUN sh -c 'touch /myapp.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/myapp.jar"]