#!/bin/sh
#1. 请配置工作路径为根目录（必填）
WK_PATH=
#2. 请配置JAR包全称（必填）
JAR=
#3. 请配置程序入口（必填）
MAIN_CLASS=
#4. 请配置入口参数（可选）
ARGS=
#5. 请配置JVM参数（必填）
JVM_OPT=-Xmx216M -Xms216M -server -XX:+PrintGCDetails -XX:+PrintGCDateStamps
#6. 请配置JDK（必填）
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

echo "Service startup success."
