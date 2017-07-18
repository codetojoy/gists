
echo "preparing ..."
./prepare.sh 

echo "compiling ..."
./compile.sh 

CLASSPATH=jars/jackson-core-2.8.9.jar
CLASSPATH=$CLASSPATH:jars/jackson-annotations-2.8.9.jar

MODULEPATH=mlib:mjars

java \
--module-path $MODULEPATH \
--class-path $CLASSPATH \
-m net.codetojoy/net.codetojoy.App
