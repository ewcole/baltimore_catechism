CLASSPATH=build/libs/baltimore_catechism.jar
    # for %%f in (%groovy_home%\embeddable\*.jar) do (
    #     set classpath=!classpath!;%%~sf
    # )
# set classpath
java baltimore_catechism.CatechismReader %~dp0\GutenbergBaltimoreCatechism1.txt
