set -e "check JAVA_11_HOME var in setvars.sh"

JAVA_11_HOME=/Users/measter/tools/jdk-11.jdk/Contents/Home
stat $JAVA_11_HOME

export PATH=$JAVA_11_HOME/bin:$PATH
