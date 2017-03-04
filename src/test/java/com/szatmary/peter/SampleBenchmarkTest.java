package com.szatmary.peter;

import org.junit.Assert;
import org.junit.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.results.BenchmarkResult;
import org.openjdk.jmh.results.RunResult;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;
import org.openjdk.jmh.runner.options.VerboseMode;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

import static com.szatmary.peter.SampleBenchmarkTest.St.AVERAGE_EXPECTED_TIME;
/**
 * It is recommended to run JMH not from tests but directly from different main method code.
 * Unit-tests and other IDE interferes with the measurements.
 *
 * If your measurements will be in second / minutes or longer than it running nechmarks from tests
 * will not affect your benchmark results.
 *
 * If your measurements will be in  miliseconds, microseconds , nanoseconds ... run your
 * benchmarks rather not from tests bud from main code to have better measurements.
 */
public class SampleBenchmarkTest {

    @State(Scope.Benchmark)
    public static class St {
        private App app = new App();
        static final double AVERAGE_EXPECTED_TIME = 100;
    }

    /**
     * Benchmark run with Junit
     * @throws Exception
     */
    @Test
    public void runTest() throws Exception {
        Options opt = initBench();
        Collection<RunResult> results = runBench(opt);
        assertOutputs(results);
    }


    /**
     * JMH benchmark
     * @param st
     */
    @Benchmark
    public void oldWay(St st) {
        st.app.oldWay();
    }

    /**
     * JMH benchmark
     * @param st
     */
    @Benchmark
    public void newWay(St st) {
        st.app.newWay();
    }

    /**
     * Runner options that runs all benchmarks in this test class
     * namely benchmark oldWay and newWay.
     * @return
     */
    private Options initBench() {
        return new OptionsBuilder() //
                .include(SampleBenchmarkTest.class.getSimpleName() + ".*") //
                .mode(Mode.AverageTime) //
                .verbosity(VerboseMode.EXTRA) //
                .timeUnit(TimeUnit.MILLISECONDS) //
                .warmupTime(TimeValue.seconds(1)) //
                .measurementTime(TimeValue.milliseconds(1)) //
                .measurementIterations(2) //
                .threads(4) //
                .warmupIterations(2) //
                .shouldFailOnError(true) //
                .shouldDoGC(true) //
                .forks(1) //
                .build();
    }

    /**
     *
     * @param opt
     * @return
     * @throws RunnerException
     */
    private Collection<RunResult> runBench(Options opt) throws RunnerException {
         return new Runner(opt).run();
    }

    /**
     * Assert benchmark results that are interesting for us
     * Asserting test mode and average test time
     * @param results
     */
    private void assertOutputs(Collection<RunResult> results) {
        for (RunResult r : results) {
            for (BenchmarkResult rr : r.getBenchmarkResults()) {

                Mode mode = rr.getParams().getMode();
                double score = rr.getPrimaryResult().getScore();
                String methodName = rr.getPrimaryResult().getLabel();

                Assert.assertEquals("Test mode is not average mode. Method = " + methodName ,
                        Mode.AverageTime, mode);
                Assert.assertTrue("Benchmark score = " + score + " is higher than " + AVERAGE_EXPECTED_TIME + " " + rr.getScoreUnit() + ". Too slow performance !",
                        score < AVERAGE_EXPECTED_TIME);
            }
        }
    }
}