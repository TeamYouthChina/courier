FROM openjdk:11
WORKDIR /courier
ENV YOUTHCHINAACCESSKEYID=${YOUTHCHINAACCESSKEYID}
ENV YOUTHCHINAACCESSKEYIDKEYSECRET=${YOUTHCHINAACCESSKEYIDKEYSECRET}
ENV YOUTHCHINADBURL = ${YOUTHCHINADBURL}
COPY target/youthchina-courier.jar /courier/youthchina-courier.jar
COPY resume.pdf /resume.pdf
COPY application.properties /courier/application.properties
ENTRYPOINT ["java","-jar","youthchina-courier.jar", "--spring.config.location=classpath:/application.properties"]
