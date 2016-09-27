@echo off
xcopy /s /e /EXCLUDE:exclude.txt .\game .\gamehandle

pause

for /r ".\game" %%i in (handler^\*.as) do del /q /f /a "%%i"

pause

::for /d /r ".\game" %%i in (^\handler) do rd /s /q "%%i"

pause