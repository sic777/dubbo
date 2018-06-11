@echo starting...
TITLE Demo网关
rem 工作路径（根目录）
set WK_PATH=
rem JDK根目录
set JAVA_HOME=

set JAR=dubbo-springboot-demo-server-1.0.0.jar

cd  %WK_PATH%
%JAVA_HOME%\bin\java -Dfile.encoding=utf-8 -jar %WK_PATH%\lib\%JAR%
pause