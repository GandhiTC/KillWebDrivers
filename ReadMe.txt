KillWebDrivers is a small utility to kill off any lingering instances of web drivers.
It is useful for freeing resources between/after tests.

KillWebDrivers is currently Windows only.
It uses Window's built-in Tasklist and Taskkill commands.



---------------------------------------------------------------------------------------------------------------------------------------
KillWebDrivers.java
---------------------------------------------------------------------------------------------------------------------------------------
This class calls the Window's commands from within java.
Its can be added to projects to call on WebDriver cleanup.



---------------------------------------------------------------------------------------------------------------------------------------
KillWebDrivers.bat
---------------------------------------------------------------------------------------------------------------------------------------
A Windows batch file that calls on java to execute the KillWebDrivers.java file.



---------------------------------------------------------------------------------------------------------------------------------------
TerminateWebDrivers.bat
---------------------------------------------------------------------------------------------------------------------------------------
A Windows batch file that calls on the Tasklist and Taskkill commands directly.
WebDrivers are closed without java.



