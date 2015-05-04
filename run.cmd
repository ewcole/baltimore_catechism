@echo off
setlocal EnableDelayedExpansion
    for %%j in (%~dp0\build\libs\*.jar) do (
        set classpath=%%~sj;!classpath!
    )
    for %%f in (%groovy_home%\embeddable\*.jar) do (
        set classpath=!classpath!;%%~sf
    )
set classpath
    java baltimore_catechism.CatechismReader %~dp0\GutenbergBaltimoreCatechism1.txt
endlocal