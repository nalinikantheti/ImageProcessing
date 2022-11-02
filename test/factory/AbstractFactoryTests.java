package factory;

import command.Command;
import controller.factory.terminal.BrightenFactory;
import controller.factory.terminal.CommandFactory;
import org.junit.Before;
import org.junit.Test;
import view.ImageProcessorTextView;
import view.ImageProcessorView;

import java.io.IOException;
import java.io.StringReader;
import java.util.Optional;
import java.util.Scanner;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

public abstract class AbstractFactoryTests {
    StringBuilder expectedOutput;
    StringBuilder actualOuptut;
    StringBuilder inputBuilder;

    @Before
    public void setup() {
        expectedOutput = new StringBuilder();
        actualOuptut = new StringBuilder();
        inputBuilder = new StringBuilder();
    }

    @Test
    public void testSuccess() {
        input("original original-modified");
        assertTrue(runTest().isPresent());

        setup();
        input("clap your-hands");
        assertTrue(runTest().isPresent());
    }

    @Test
    public void testQuit() {
        input("quit");
        output("Quitting program...");

        assertFalse(runTest().isPresent());

        setup();
        input("qUiT broriginal");
        output("Quitting program...");

        assertFalse(runTest().isPresent());

        setup();
        input("original Q");
        output("Quitting program...");
        assertFalse(runTest().isPresent());
    }

    @Test
    public void testEndOfInput() {
        input("original");
        output("Reached end of input.");
        assertFalse(runTest().isPresent());

        setup();
        input("originaljfkdla");
        output("Reached end of input.");
        assertFalse(runTest().isPresent());
    }
    @Test
    public void testConstructorThrows() {
        assertThrows(IllegalArgumentException.class,
                () -> this.makeFactory(null, makeScanner()));
        assertThrows(IllegalArgumentException.class,
                () -> this.makeFactory(makeView(), null));
    }

    @Test
    public void testTransmitThrows() {
        Appendable badAppendable = new Appendable() {
            @Override
            public Appendable append(CharSequence csq) throws IOException {
                throw new IOException();
            }

            @Override
            public Appendable append(CharSequence csq, int start, int end) throws IOException {
                throw new IOException();
            }

            @Override
            public Appendable append(char c) throws IOException {
                throw new IOException();
            }
        };
        ImageProcessorView view = new ImageProcessorTextView(badAppendable);
        CommandFactory factory = this.makeFactory(view, makeScanner());


        input("original 50 boriginal");
        assertThrows(IllegalStateException.class, factory::make);

    }

    protected void output(String message) {
        expectedOutput.append(message + "\n");
    }

    protected void input(String message) {
        inputBuilder.append(message + "\n");
    }

    protected Optional<Command> runTest() {
        CommandFactory factory = this.makeFactory(makeView(), makeScanner());
        Optional<Command> command = factory.make();
        assertEquals(expectedOutput.toString(), actualOuptut.toString());
        return command;
    }

    private Scanner makeScanner() {
        return new Scanner(new StringReader(inputBuilder.toString()));
    }

    private ImageProcessorView makeView() {
        return new ImageProcessorTextView(actualOuptut);
    }

    protected abstract CommandFactory makeFactory(ImageProcessorView view, Scanner scanner);
}
