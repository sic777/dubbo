#!/bin/sh
#请配置工作路径为根目录
WK_PATH=""
#JAR包全称
JAR=""
#程序入口
MAIN_CLASS=""
#入口参数
ARGS=""
#JVM参数
JVM_OPT="-Xmx216M -Xms216M -server -XX:+PrintGCDetails -XX:+PrintGCDateStamps"
#JDK
JAVA_HOME=

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

echo "success to start server ..."
