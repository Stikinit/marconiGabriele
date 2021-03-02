package it.unibo.boundaryWalk;

import org.checkerframework.checker.regex.RegexUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertTrue;

public class TestBoundaryWalk {
    private MoveVirtualRobot appl;

    @Before
    public void systemSetUp() {
        System.out.println("TestBoundaryWalk | setUp: robot should be at HOME-DOWN ");
        appl = new MoveVirtualRobot();
    }

    @After
    public void  terminate() {
        System.out.println("%%%  TestBoundaryWalk |  terminates ");
    }

    @Test
    public void testFirstStrategy() {
        System.out.println("TestBoundaryWlak | firstStrategy ");
        StringBuilder sb = new StringBuilder();
        boolean hasCollided = false;
        for(int i=0; i<4; i++) {
            while(!hasCollided) {
                boolean moveFailed = appl.moveForward(300);

                if(moveFailed) {
                    appl.moveLeft(600);
                    sb.append("l");
                    hasCollided = true;
                }
                else {
                    sb.append("w");
                }
            }
            hasCollided = false;
        }
        System.out.println("Resulting string: "+sb.toString());
        assertTrue(Pattern.compile("w*lw*lw*lw*l").matcher(sb.toString()).matches());
    }
}
