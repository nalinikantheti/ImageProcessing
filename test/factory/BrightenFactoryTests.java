package factory;

import controller.factory.terminal.BrightenFactory;
import controller.factory.terminal.CommandFactory;
import org.junit.Test;
import view.ImageProcessorView;

import java.util.Scanner;

import static org.junit.Assert.*;

public class BrightenFactoryTests extends AbstractFactoryTests {

    @Override
    public void testSuccess() {
        input("original 50 original-bright");
        assertTrue(runTest().isPresent());


        setup();
        input("original 10000 original-bright-max");
        assertTrue(runTest().isPresent());

    }

    @Test
    public void testBadInteger() {
        input("original blerner");
        output("Expected integer for intensity, got: blerner");
        output("Re-enter value.");

        input("30 original-bright");

        assertTrue(runTest().isPresent());
    }

    @Override
    public void testQuit() {
        input("quit");
        output("Quitting program...");

        assertFalse(runTest().isPresent());

        setup();
        input("original qUiT broriginal");
        output("Quitting program...");

        assertFalse(runTest().isPresent());

        setup();
        input("original 25 Q");
        output("Quitting program...");
        assertFalse(runTest().isPresent());
    }

    @Override
    public void testEndOfInput() {
        input("original");
        output("Reached end of input.");
        assertFalse(runTest().isPresent());

        setup();
        input("original 50");
        output("Reached end of input.");
        assertFalse(runTest().isPresent());

        setup();
        input("original boriginal 50");
        output("Expected integer for intensity, got: boriginal");
        output("Re-enter value.");

        output("Reached end of input.");
        assertFalse(runTest().isPresent());
    }

    @Override
    protected CommandFactory makeFactory(ImageProcessorView view, Scanner scanner) {
        return new BrightenFactory(view, scanner);
    }

}
