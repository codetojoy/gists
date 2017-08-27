
gradle clean compileJava 

mkdir -p pack
rm -r pack
mkdir pack

cd pack

cp -r ../build/classes/main/* .

cp ../src/main/resources/unpack.sh .
cp ../src/main/resources/payload.zip .

jar -cvfm main_gate.jar ../src/main/resources/manifest.txt .

cd ..

mkdir -p test
rm -r test
mkdir test

cp pack/main_gate.jar test

echo "Ready."

