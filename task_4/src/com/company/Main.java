package com.company;

import com.util.SwingUtils;

import java.text.ParseException;
import java.util.Locale;

public class Main {
    public static class CmdParams {
        public String inputFile;
        public String outputFile;
        public boolean error;
        public boolean window;
    }


    public static CmdParams parseArgs(String[] args) {
        CmdParams params = new CmdParams();
        if (args.length > 0) {
            if (args[0].equals("--window")) {
                params.window = true;
                return params;
            }
            params.inputFile = args[0];
            if (args.length > 1) {
                params.outputFile = args[1];
            }
        } else {
            params.error = true;
        }
        return params;
    }

    public static void winMain(){
//SwingUtils.setLookAndFeelByName("Windows");
        Locale.setDefault(Locale.ROOT);
//UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        SwingUtils.setDefaultFont("Microsoft Sans Serif", 18);

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            try {
                new FrameMain().setVisible(true);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
    }

    public static void main(String[] args) {
        CmdParams params = parseArgs(args);
        if (params.window) {
            winMain();
        }
    }
}
