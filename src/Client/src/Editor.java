// Java Program to create a text editor using java
import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.plaf.metal.*;
import javax.swing.text.*;
import java.util.concurrent.TimeUnit;
import java.util.*;

public class Editor extends JFrame{
  // Text component
  JTextArea t;

  // Frame
  JFrame f;

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