首先需要进入到target/classes文件夹中

- 使用Shell脚本执行JQF进行Fuzzing (需要本地配置jdk11及以上的环境)

```shell
/Users/zhouzhuotong/java_projects/JQF/bin/jqf-zest -c .:$(/Users/zhouzhuotong/java_projects/JQF/scripts/classpath.sh) com.jxfzzzt.calendar.CalendarTest testLeapYear
```

- 使用Shell脚本执行Repro (需要本地配置jdk11及以上的环境)

```shell
/Users/zhouzhuotong/java_projects/JQF/bin/jqf-repro -c .:$(/Users/zhouzhuotong/java_projects/JQF/scripts/classpath.sh) com.jxfzzzt.calendar.CalendarTest testLeapYear ./fuzz-results/failures/id_000000
```

使用shell脚本执行的好处是：可以对JQF源代码进行更改，从而进行自定话的Fuzzing