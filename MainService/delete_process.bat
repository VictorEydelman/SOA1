@echo off
setlocal enabledelayedexpansion

for /f "tokens=5" %%a in ('netstat -ano ^| findstr ":8089 "') do (
    set PID=%%a
    goto found
)

echo No process found on port %PORT%
exit /b 0

:found
echo Found process with PID: !PID!
taskkill /f /pid !PID!

if !errorlevel! equ 0 (
    echo Process !PID! terminated successfully
) else (
    echo Failed to terminate process !PID!
    exit /b 1
)