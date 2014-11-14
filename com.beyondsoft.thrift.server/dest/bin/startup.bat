@echo off

setlocal enabledelayedexpansion
title= The Server Application

:start

	echo.
	echo ===============================================================================
	echo JAVA_HOME=%JAVA_HOME%	
	echo ===============================================================================
	
	
	set JAVA="%JAVA_HOME%"\bin\java
	set JVM_MEMORY=-Xms512M -Xmx1024M -Xmn340M -server
	set JVM_TIMEZONE=-Duser.timezone=Asia/Shanghai
	
	set JAVA_OPTS= %JVM_MEMORY% %JVM_TIMEZONE% -XX:+AggressiveOpts -XX:+UseParallelGC 

	set MAIN=com.beyondsoft.thrift.server.RunServer

	set CP=..\config;
	set LIB_JAR=com.beyondsoft.thrift.server-0.0.1.jar
	set CP=!CP!..\%LIB_JAR%;
	
	set LIBS=..\libs
	for /f %%i in ('dir /b %LIBS%\*.jar^|sort') do (
	   set CP=!CP!%LIBS%\%%i;
	)
			
	goto showenv

:showenv
	echo ===============================================================================	
	echo   CLASSPATH: %CP%
	echo ===============================================================================
	echo   The Server Application is running......
	echo   JAVA_HOME: %JAVA_HOME%
	echo   JVM_MEMORY: %JVM_MEMORY%
	echo   JVM_TIMEZONE: %JVM_TIMEZONE% 
	echo   MAIN_CLASS: %MAIN%
	echo ===============================================================================
	
	goto startupapp

:startupapp
	echo.
	%JAVA% %JAVA_OPTS% -cp %CP% %MAIN%
	echo.
	goto end
	
:end
	goto exit
:exit
	pause
rem	exit