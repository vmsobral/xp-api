FROM java:8-alpine

RUN mkdir -p /opt/app && \
    mkdir -p /opt/datadog

ADD application/build/distributions/application-1.0.tar /opt/app/
COPY libs/dd-java-agent.jar /opt/datadog

EXPOSE 8080
CMD ["/opt/app/application-1.0/bin/application"]
