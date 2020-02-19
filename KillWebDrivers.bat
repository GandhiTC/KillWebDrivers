@ECHO OFF

echo.
echo.

cd %~d0\

cd %~dp0
cd src\com\github\GandhiTC\java\KillDrivers\
javac KillDrivers.java

cd %~dp0
java -cp ./bin;. com.github.GandhiTC.java.KillWebDrivers.KillWebDrivers "2"

echo.
echo.

echo Press enter to exit
set /p input=