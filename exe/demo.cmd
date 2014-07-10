@echo off
set installedPath = %~dp0
REM Running jar file with jre
%installedPath%jre\bin\java -jar stock-manager.jar