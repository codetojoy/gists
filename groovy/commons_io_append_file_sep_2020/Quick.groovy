
@Grab(group='commons-io', module='commons-io', version='2.8.0')

import org.apache.commons.io.FileUtils

def file = new File("test.txt")

FileUtils.writeStringToFile(file, "5150\n", "UTF-8", true);
FileUtils.writeStringToFile(file, "6160\n", "UTF-8", true);

