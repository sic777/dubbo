# 安装步骤
```
1.下载nodejs
2.安装apidocjs:npm install apidoc -g
3.执行脚本生成Rest接口
4.默认访问/apidoc/index.html即可查看接口文档(如有修改路径，则按照实际路径访问)
```

# 生成脚本

## Linux
```
#!/bin/sh
#-i：工作空间 -o：目标路径 -t：模板
apidoc -i . -o ./rs -t ./template
```

## Windows
```
TITLE Rest Api Document
apidoc -i . -o .\rs -t .\template
```