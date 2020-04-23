
### note

* this is a resuable container for a given project/volume

### initial steps

* ensure current directory contains `resources/jdk-9+175_linux-x64_bin.tar.gz`
* run: `docker build -t="jdk9/b175" .` 
* run: `docker run -i -t -v $(pwd):/data jdk9/b175`
* inside container, run: `/data/resources/install.sh`
* inside container, run: `. /data/resources/setvars.sh`
* confirm: `java --version`

### subsequent steps

* `docker start [container name]`
* `docker attach [container name]`
* run: `. /data/resources/setvars.sh`
* confirm: `java --version`
