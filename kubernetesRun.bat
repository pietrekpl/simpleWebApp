kubectl apply -f allAppDeployment.yaml
kubectl apply -f allServices.yaml

@echo off
@timeout /t 24


start "App" "C:\Windows\System32\WindowsPowerShell\v1.0\powershell.exe" -NoExit "-Command" "kubectl port-forward services/app 8080:8080"

start "Angular" "C:\Windows\System32\WindowsPowerShell\v1.0\powershell.exe" -NoExit "-Command" "kubectl port-forward services/angular 4200:4200"

start "ActiveMQ" "C:\Windows\System32\WindowsPowerShell\v1.0\powershell.exe" -NoExit "-Command" "kubectl port-forward services/activemq 8161:8161"

start "Postgres" "C:\Windows\System32\WindowsPowerShell\v1.0\powershell.exe" -NoExit "-Command" "kubectl port-forward services/postgres 5432:5432"




