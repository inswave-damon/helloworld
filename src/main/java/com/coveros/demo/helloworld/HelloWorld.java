package com.coveros.demo.helloworld;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.fusesource.jansi.Ansi;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.jline.utils.AttributedStringBuilder;
import org.jline.utils.AttributedStyle;
import org.jline.utils.InfoCmp.Capability;

public class HelloWorld {

    public static void main(final String[] args) throws Exception {
        final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("h:mm:ss a 'on' MMMM d, yyyy'.'");
        final LocalDateTime now = LocalDateTime.now();

        Terminal terminal = TerminalBuilder.builder()
                .system(true)
                .jansi(true)
                .build();

        String[] blocks = {
            "  #### ",
            "  #  # ",
            "  #### ",
            "#### ",
            "#  # ",
            "#  # ",
            "#### "
        };

        long startTime = System.currentTimeMillis();

        while (System.currentTimeMillis() - startTime < 3000) {

            terminal.puts(Capability.clear_screen);

            int randomRow = (int) (Math.random() * (terminal.getHeight() - blocks.length));
            int randomCol = (int) (Math.random() * (terminal.getWidth() - blocks[0].length()));
            Ansi.Color randomColor = Ansi.Color.values()[(int) (Math.random() * Ansi.Color.values().length)]; 

            for (int i = 0; i < blocks.length; i++) {
                AttributedStringBuilder asb = new AttributedStringBuilder();
                asb.append(String.format("%" + (randomCol + blocks[i].length()) + "s", blocks[i]).substring(randomCol), AttributedStyle.DEFAULT.foreground(randomColor.ordinal()));
                terminal.writer().println(asb.toAnsi()); 
            }
            
            terminal.flush();

            Thread.sleep(200); 
        }

        System.out.println("Hello, World! The current time is " + dtf.format(now));
        System.out.println("Eclipse Che 테스트");

        terminal.close();
    }
}