#! /bin/bash
# shellcheck disable=SC2164
cd ~/java_projects/JQF-demo/
mvn clean
mvn package
mvn jqf:fuzz -Dclass=com.jxfzzzt.string.AsciiStringTest -Dmethod=testCopy -Dtime=10s
