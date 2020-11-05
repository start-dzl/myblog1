FROM registry.cn-hangzhou.aliyuncs.com/dzlxxh/openjdk-entrypoint:11-jre
VOLUME /tmp
ARG JAR_FILE
COPY ${JAR_FILE} app.jar
COPY docker-entrypoint.sh docker-entrypoint.sh
RUN chmod a+x /docker-entrypoint.sh
ENTRYPOINT ["/docker-entrypoint.sh"]
EXPOSE 8080