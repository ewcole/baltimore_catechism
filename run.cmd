@echo off
setlocal EnableDelayedExpansion
    set cJar=%~dp0\build\libs\BaltimoreCatechism.jar
    for %%f in (%groovy_home%\embeddable\*.jar) do (
        set classpath=!classpath!;%%~sf
    )
    set classpath=%classpath%;%cJar%
    java baltimore_catechism.CatechismReader %~dp0\GutenbergBaltimoreCatechism1.txt
endlocal