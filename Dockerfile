#从tomcat镜像开始
from  hub.c.163.com/library/tomcat 

#把jar包，war包添加进去
ADD target/*.war app.war

#声明端口
EXPOSE 8761

#声明运行方式
ENTRYPOINT ["java", "-jar" , "/app.jar"]

#所有者
MAINTAINER zhangyang zhangyang.z@icloud.com

# 复制war包到webapp目录
COPY np-web.war  /usr/local/tomcat/webapps