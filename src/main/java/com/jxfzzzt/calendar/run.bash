#! /bin/bash
# shellcheck disable=SC2164
cd ~/java_projects/JQF-demo/
mvn clean
mvn package
mvn jqf:fuzz -Dclass=com.jxfzzzt.demo.CalendarTest -Dmethod=testLeapYear -Dtime=10s