# jmh-benchmark-demo
[Java Microbenchmark Harness (JMH)](http://openjdk.java.net/projects/code-tools/jmh/) that runs with Junit and Maven.

Project is about how to run JMH benchmark tests from test scope with junit and maven.

It is recommended to run JMH **not from your tests** but directly from different main method code.
Unit-tests and other IDE interferes with the measurements.

If your measurements will be in second / minutes or longer than it running the benchmarks from tests
will not affect so hugely your benchmark results.

If your measurements will be in  miliseconds, microseconds , nanoseconds ... run your benchmarks
rather not from your tests but from main code to have better measurements.

## Java Microbenchmark Harness Integration

Integration and usage of JMH is really easy. Just take a look at
[pom.xml](https://github.com/peterszatmary/jmh-benchmark-demo/blob/master/pom.xml) and
[SampleBenchamrkTest](https://github.com/peterszatmary/jmh-benchmark-demo/blob/master/src/test/java/com/szatmary/peter/SampleBenchmarkTest.java).

## Run

you can run test with your IDE also with maven
```
mvn clean test
```

Results after all benchmark ends

```
# Run complete. Total time: 00:00:07

Benchmark                   Mode  Cnt   Score   Error  Units
SampleBenchmarkTest.newWay  avgt    2   8.462          ms/op
SampleBenchmarkTest.oldWay  avgt    2  11.676          ms/op
Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 7.839 sec
```


## Conclusion

Dont use this demo if your benchamarks are really "micro". If not it is nice solution to have.

For some is maybe not good choice to have benchmarks in test maven lifecycle.
If you wish take benchmarking from maven test phase you can create maven profile and and run
benchmarks separately.
