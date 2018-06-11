@echo 开始生成Rest Api文档...
TITLE Rest Api文档生成器

rem 工作路径（根目录）
set WK_PATH=..\..
set TARGET_PATH=%WK_PATH%\dubbo-springboot-demo\dubbo-springboot-demo-client\src\main\resources\static\apidoc

apidoc -i %WK_PATH% -o %TARGET_PATH% -t %WK_PATH%\apidoc\template