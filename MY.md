
## 启动及停止
+ 启动目录
cd /mnt/dolphinscheduler
+ 启动
sh ./bin/dolphinscheduler-daemon.sh start standalone-server
+ 停止
sh ./bin/dolphinscheduler-daemon.sh stop standalone-server

## 账户
+ admin/dolphinscheduler123

## 打包指定模块
mvn clean -Dmaven.test.skip=true package -Prelease -pl dolphinscheduler-datasource-plugin/dolphinscheduler-datasource-api -am
mvn clean -Dmaven.test.skip=true package -Prelease -pl dolphinscheduler-api -am

## 前端
+ 依赖安装: npm install
+ dev调试: npm run dev
+ 打包: npm run build:release
+

