@echo off
setlocal enabledelayedexpansion
  for %%f in (%~dp0\build\libs\*.jar) do (
    set classpath=%%f;!classpath!
    )
    java org.antlr.v4.runtime.misc.TestRig baltimore_catechism.Catechism1 file -tokens -tree %~dp0\GutenbergBaltimoreCatechism1.txt
    start     java org.antlr.v4.runtime.misc.TestRig baltimore_catechism.Catechism1 file -gui %~dp0\GutenbergBaltimoreCatechism1.txt

endlocal