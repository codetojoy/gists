rm -r jars
mkdir jars

rm -r mjars
mkdir mjars

cd jars

# jackson-annotations

wget https://search.maven.org/remotecontent?filepath=com/fasterxml/jackson/core/jackson-annotations/2.8.9/jackson-annotations-2.8.9.jar

mv remotecontent?filepath=com%2Ffasterxml%2Fjackson%2Fcore%2Fjackson-annotations%2F2.8.9%2Fjackson-annotations-2.8.9.jar jackson-annotations-2.8.9.jar

# jackson-core

wget https://search.maven.org/remotecontent?filepath=com/fasterxml/jackson/core/jackson-core/2.8.9/jackson-core-2.8.9.jar

mv remotecontent?filepath=com%2Ffasterxml%2Fjackson%2Fcore%2Fjackson-core%2F2.8.9%2Fjackson-core-2.8.9.jar jackson-core-2.8.9.jar

cd ../mjars

# jackson-databind

wget https://search.maven.org/remotecontent?filepath=com/fasterxml/jackson/core/jackson-databind/2.8.9/jackson-databind-2.8.9.jar

mv remotecontent?filepath=com%2Ffasterxml%2Fjackson%2Fcore%2Fjackson-databind%2F2.8.9%2Fjackson-databind-2.8.9.jar jackson.databind.jar

