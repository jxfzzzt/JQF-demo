package com.jxfzzzt.myself;

import com.pholser.junit.quickcheck.From;
import edu.berkeley.cs.jqf.fuzz.Fuzz;
import edu.berkeley.cs.jqf.fuzz.JQF;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.runner.RunWith;

@RunWith(JQF.class)
public class MyselfObjectTest {
    // 如果待测试的类是用户自己的类，一定要编写对应的Generator类才可以进行正常的Fuzzing
    @Fuzz(repro = "target/classes/fuzz-results/failures/id_000000")
    public void testAge(@From(MyselfObjectGenerator.class) MyselfObject object) {
        Assume.assumeTrue(object.getAge() > 0);
//        System.out.println(object + ": " + object.getAge());
        Assert.assertTrue(object.getAge() > 5);
    }
}
