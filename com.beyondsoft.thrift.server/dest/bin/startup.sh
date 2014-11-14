#!/bin/sh 
if [ "$JAVA_HOME" = "" ] ;then
echo "JAVA_HOME did not find the current system environment variable, if set to the effective JAVA_HOME? (y/n)"
read FLAG
if [ "$FLAG" = "y"] ;then
echo "set JAVA_HOME="
read JAVA_HOME
fi
fi
echo ===============================================================================
echo JAVA_HOME=$JAVA_HOME
echo ===============================================================================


JAVA="$JAVA_HOME/bin/java"
JVM_MEMORY="-Xms512M -Xmx1024M -Xmn340M -server"
JVM_TIMEZONE="-Duser.timezone=Asia/Shanghai"

JAVA_OPTS="$JVM_MEMORY $JVM_TIMEZONE -XX:+AggressiveOpts -XX:+UseParallelGC"
MAIN="com.beyondsoft.thrift.server.RunServer"

CP="../config:"
LIB_JAR="/com.beyondsoft.thrift.server-0.0.1.jar"
CP="$CP..$LIB_JAR:"

LIBS="../libs"
for FILE in $LIBS/*.jar
do 
   CP="$CP$FILE:"
done

echo ===============================================================================
echo   CLASSPATH: $CP
echo ===============================================================================
echo   Intercom Server Application is running......
echo   JAVA_HOME: $JAVA_HOME
echo   JVM_MEMORY: $JVM_MEMORY
echo   JVM_TIMEZONE: $JVM_TIMEZONE
echo   MAIN_CLASS: $MAIN
echo ===============================================================================

$JAVA $JAVA_OPTS  -cp $CP $MAIN

echo "VAR JAVA: $JAVA"
echo "VAR JAVA_OPTS : $JAVA_OPTS"
echo "VAR CP: $CP"
echo "VAR MAIN: $MAIN"

