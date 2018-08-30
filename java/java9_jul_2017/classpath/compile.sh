
./clean.sh

javac -d build \
--source-path src \
--class-path jars/jackson-databind-2.8.9.jar:jars/jackson-core-2.8.9.jar \
`find src -name "*.java"`

jar --create --file=lib/net.codetojoy.jar \
-C build . 

