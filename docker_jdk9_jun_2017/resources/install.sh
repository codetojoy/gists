
mkdir -p /var/my_jdk
cd /var/my_jdk

cp /data/resources/jdk-9+175_linux-x64_bin.tar.gz .
gunzip jdk-9+175_linux-x64_bin.tar.gz
tar xf jdk-9+175_linux-x64_bin.tar

echo "Ready."
