call mvn -B -s settings.xml -DskipTests=true clean package
rem call java -Dspring.profiles.active="datajpa,heroku" -DDATABASE_URL="postgres://user:password@localhost:5432/topjava" -jar target/dependency/webapp-runner.jar target/*.war
call java  -DDATABASE_URL="postgres://user:password@localhost:5432/enclave" -classpath target/dependency webapp.runner.launch.Main target/*.war
