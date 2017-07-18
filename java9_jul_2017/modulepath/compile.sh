
./clean.sh

javac -d build/modules \
--module-path src:mjars \
--class-path jars/jackson-core-2.8.9.jar \
`find src -name "*.java"`

jar --create --file=mlib/net.codetojoy.jar \
-C build/modules . 

