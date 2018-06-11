#!/bin/sh
# JDK根目录
JAVA_HOME=
# 工作路径（根目录）
WK_PATH=
# 主函数参数(如果有就填)
ARGS=
JVM_OPT="-Xmx128M -Xms128M -server -XX:+PrintGCDetails -XX:+PrintGCDateStamps"

JAR=dubbo-springboot-demo-server-1.0.0.jar
MAIN_CLASS=com.sic777.demo.server.DemoServerLauncher

if [ ! -n "$WK_PATH" ];then
    echo "please set up the work path : [WK_PATH]"
    exit
fi

G_PID=`ps -ef | grep $JAR |grep -v 'grep' |awk '{print $2}'`
if [ -n "$G_PID" ];then
    echo "PROCESS EXISTS"
    exit
fi

cd $WK_PATH

/usr/bin/nohup $JAVA_HOME/bin/java  $JVM_OPT -cp $WK_PATH/lib/$JAR:$WK_PATH/lib/*  $MAIN_CLASS $ARGS >/dev/null 2>&1 &

PID=`ps -ef | grep $JAR |grep -v 'grep' |awk '{print $2}'`


if [ ! -d "$WK_PATH/pid" ]; then
  mkdir $WK_PATH/pid
fi

echo $PID > $WK_PATH/pid/pid

echo "Service startup success."
