
* ensure current directory contains `resources/jdk-9+175_linux-x64_bin.tar.gz`
* run: `docker build -t="jdk9/b175" .` 
* run: `docker run -i -t -v $(pwd):/data jdk9/b175`
* first time, inside container, run: `/data/resources/install.sh`
* every time, inside container, run: `. /data/resources/setvars.sh`
* confirm: `java --version`

