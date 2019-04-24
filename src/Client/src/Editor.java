// Java Program to create a text editor using java
import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.plaf.metal.*;
import javax.swing.text.*;
import java.util.concurrent.TimeUnit;
import java.util.*;

import java.awt.BorderLayout;
import java.awt.Rectangle;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;

public class Editor extends JFrame {
  // Text component
  JTextArea t;

  // Frame
  JFrame f;

  // Button
  JButton b;

  // Constructor
  Editor()
  {
    // Create a frame
    f = new JFrame("P2P Collaborative Editing");

    try {
      // Set metl look and feel
      UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");

      // Set theme to ocean
      MetalLookAndFeel.setCurrentTheme(new OceanTheme());
    }
    catch (Exception e) {
    }

    // Text component
    t = new JTextArea();


    f.add(t);
    f.setSize(500, 500);
    f.show();
  }

}
