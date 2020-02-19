@ECHO OFF
SETLOCAL ENABLEDELAYEDEXPANSION
CLS

ECHO.
ECHO.

SET COUNTER=0
SET "appList=chromedriver.exe geckodriver.exe IEDriverServer.exe MicrosoftWebDriver.exe"

FOR %%I IN (%appList%) DO (
    FOR /F "TOKENS=1,2,*" %%A IN ('tasklist /FI "IMAGENAME eq %%I"') DO (
        IF /I "%%A" EQU "%%I" (
		    taskkill /F /PID %%B /T >nul
		    IF !ERRORLEVEL! EQU 0 (
		   		SET /A COUNTER=COUNTER+1
		   		@ECHO !COUNTER!.  %%A ^(PID: %%B^) successfully terminated.
			)
		)
	)
)

IF %COUNTER% EQU 0 (
    ECHO Nothing to do, no WebDrivers were running.
)

ECHO.
ECHO.

ECHO Press enter to exit
SET /p input=