set JAVA_HOME=E:\Apps\Java\jdk-8.0.341
set Path=%JAVA_HOME%\bin;%PATH%
@PushD "./output" 2> NUL && (RD /S /Q "." 2> NUL & PopD)
java -cp civ4utils.jar org.archid.civ4.main.InfoUtils -f "E:/WinMyDocs/My Games/Beyond the Sword/MODS/A-Mod/Assets/Xml/Buildings/Civ4BuildingInfos.xml" -x "../Docs/Civ4Archid.xlsx" -t Building -e
java -cp civ4utils.jar org.archid.civ4.main.InfoUtils -f "E:/WinMyDocs/My Games/Beyond the Sword/MODS/A-Mod/Assets/Xml/Buildings/Civ4BuildingClassInfos.xml" -x "../Docs/Civ4Archid.xlsx" -t BuildingClass -e
java -cp civ4utils.jar org.archid.civ4.main.InfoUtils -f "./output" -x "../Docs/Civ4Archid.xlsx" -t Building -i 
java -cp civ4utils.jar org.archid.civ4.main.InfoUtils -f "./output" -x "../Docs/Civ4Archid.xlsx" -t BuildingClass -i 
del /q "../Docs/Civ4Archid.xlsx.2*"