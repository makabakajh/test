# 设置本镜像需要使用的基础镜像
FROM openjdk:8

VOLUME /tmp 

#设置时区
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

# 把jar包添加到镜像中
ADD ./web-jdyw/target/jdyw.jar app.jar

# 添加授权文件
#ADD jeesite.lic jeesite.lic

# 镜像暴露的端口
EXPOSE 8980

# 容器启动命令
ENTRYPOINT ["nohup", "java","-jar" , "app.jar","app.out","&"]


