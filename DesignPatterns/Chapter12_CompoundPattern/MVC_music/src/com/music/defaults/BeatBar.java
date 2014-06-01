package com.music.defaults;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.music.model.*;

public class BeatBar extends JProgressBar implements Runnable {
    JProgressBar progressBar;
        Thread thread;

        public BeatBar() {
                thread = new Thread(this);
                setMaximum(100);
                thread.start();
        }

        public void run() {
                for(;;) {
                        int value = getValue();
                        //value = (int)(value * .75);
                        setValue(value);
                        repaint();
                        try {
                                Thread.sleep(50);
                        } catch (Exception e) {};
                }
        }
}

