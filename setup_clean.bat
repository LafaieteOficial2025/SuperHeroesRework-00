@echo off
setlocal enabledelayedexpansion
title Fisk Heroes Rebirth - Setup Limpo
color 0A

echo.
echo ============================================================
echo  FISK HEROES REBIRTH - SETUP LIMPO (sem caracteres especiais)
echo ============================================================
echo.
echo Pasta atual: %CD%
echo.

set BASE=src\main\java\com\fiskheroesrebirth\mod
set RES=src\main\resources
set PKG=com.fiskheroesrebirth.mod

echo [1] Verificando se posso criar pastas aqui...
mkdir "teste_temp" 2>nul
if not exist "teste_temp" (
    echo.
    echo ERRO: Nao consigo criar pastas aqui!
    echo Causas possiveis:
    echo  - Sem permissao na pasta
    echo  - Antivirus bloqueando
    echo  - Roda como Administrador
    echo.
    pause
    exit /b 1
)
rmdir "teste_temp" 2>nul
echo [OK] Permissao verificada.
echo.

echo [2] Criando estrutura de pastas...
mkdir "%BASE%" 2>nul
mkdir "%BASE%\data" 2>nul
mkdir "%BASE%\data\serializer" 2>nul
mkdir "%BASE%\network" 2>nul
mkdir "%BASE%\network\packets" 2>nul
mkdir "%BASE%\network\packets\hero" 2>nul
mkdir "%BASE%\network\packets\ability" 2>nul
mkdir "%BASE%\network\packets\animation" 2>nul
mkdir "%BASE%\network\packets\sync" 2>nul
mkdir "%BASE%\hero" 2>nul
mkdir "%BASE%\hero\attribute" 2>nul
mkdir "%BASE%\hero\trait" 2>nul
mkdir "%BASE%\hero\iteration" 2>nul
mkdir "%BASE%\ability" 2>nul
mkdir "%BASE%\ability\types" 2>nul
mkdir "%BASE%\ability\types\combat" 2>nul
mkdir "%BASE%\ability\types\movement" 2>nul
mkdir "%BASE%\ability\types\defense" 2>nul
mkdir "%BASE%\ability\types\elemental" 2>nul
mkdir "%BASE%\ability\category" 2>nul
mkdir "%BASE%\ability\condition" 2>nul
mkdir "%BASE%\ability\modifier" 2>nul
mkdir "%BASE%\capability" 2>nul
mkdir "%BASE%\capability\storage" 2>nul
mkdir "%BASE%\energy" 2>nul
mkdir "%BASE%\suit" 2>nul
mkdir "%BASE%\suit\material" 2>nul
mkdir "%BASE%\util" 2>nul
mkdir "%BASE%\util\math" 2>nul
mkdir "%BASE%\util\nbt" 2>nul
mkdir "%BASE%\config" 2>nul
mkdir "%BASE%\client" 2>nul
mkdir "%BASE%\client\render" 2>nul
mkdir "%BASE%\client\render\layer" 2>nul
mkdir "%BASE%\client\render\entity" 2>nul
mkdir "%BASE%\client\model" 2>nul
mkdir "%BASE%\client\gui" 2>nul
mkdir "%BASE%\client\gui\screen" 2>nul
mkdir "%BASE%\client\gui\widget" 2>nul
mkdir "%BASE%\client\hud" 2>nul
mkdir "%BASE%\client\hud\overlay" 2>nul
mkdir "%BASE%\client\particle" 2>nul
mkdir "%BASE%\client\particle\types" 2>nul
mkdir "%BASE%\client\shader" 2>nul
mkdir "%BASE%\client\sound" 2>nul
mkdir "%BASE%\client\input" 2>nul
mkdir "%BASE%\client\animation" 2>nul
mkdir "%RES%\data\fiskheroesrebirth\heroes" 2>nul
mkdir "%RES%\data\fiskheroesrebirth\abilities" 2>nul
mkdir "%RES%\assets\fiskheroesrebirth\lang" 2>nul
mkdir "%RES%\assets\fiskheroesrebirth\animations" 2>nul
echo [OK] Pastas criadas.
echo.

echo [3] Verificando se a pasta capability\storage foi criada...
if exist "%BASE%\capability\storage" (
    echo [OK] capability\storage existe!
) else (
    echo [ERRO] capability\storage NAO foi criada!
)
echo.

echo [4] Listando todas as pastas criadas:
dir /AD /B "%BASE%" 2>nul
echo.

echo.
echo ============================================================
echo  ESTRUTURA CRIADA! 
echo  
echo  Verifique se as pastas estao em:
echo  %CD%\%BASE%
echo ============================================================
echo.
echo Pressione qualquer tecla para continuar com a geracao
echo dos arquivos Java...
pause