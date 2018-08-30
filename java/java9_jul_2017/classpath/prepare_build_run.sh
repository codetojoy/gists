
echo "preparing ..."
./prepare.sh 

echo "compiling ..."
./compile.sh 

CLASSPATH=lib/net.codetojoy.jar
CLASSPATH=$CLASSPATH:jars/jackson-annotations-2.8.9.jar
CLASSPATH=$CLASSPATH:jars/jackson-core-2.8.9.jar
CLASSPATH=$CLASSPATH:jars/jackson-databind-2.8.9.jar

java --class-path $CLASSPATH net.codetojoy.App
