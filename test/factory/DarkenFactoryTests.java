package factory;

import controller.factory.terminal.CommandFactory;
import controller.factory.terminal.DarkenFactory;
import factory.AbstractFactoryTests;
import view.ImageProcessorView;

import java.util.Scanner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DarkenFactoryTests extends AbstractFactoryTests {
    @Override
    public void testSuccess() {
        input("original 50 original-dark");
        assertTrue(runTest().isPresent());


        setup();
        input("original 10000 original-dark-max");
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
    public CommandFactory makeFactory(ImageProcessorView view, Scanner s){
        return new DarkenFactory(view, s);
    }
}
