package mttoolkit.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.LineBorder;

import mttoolkit.event.DiscreteEvent;
import mttoolkit.event.DiscreteEventListener;
import mttoolkit.event.SRTEvent;
import mttoolkit.event.SRTEventListener;
import mttoolkit.mygeom.Point2;
import mttoolkit.widget.MTPicture;
import mttoolkit.widget.MTSurface;

public class Main {

	private static void createGUI() {
		JFrame frame = new JFrame();
		frame.setPreferredSize(new Dimension(840, 620));
		frame.setLayout(new FlowLayout());
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		MTSurface surface = new MTSurface();
		surface.setPreferredSize(new Dimension(800, 600));
		surface.setBackground(new Color(255, 255, 255));
		surface.setBorder(new LineBorder(new Color(0, 0, 0)));
		MTPicture picture = new MTPicture(new Point2(50, 50), new Point2(300, 100), "data/Bird.jpg");
		MTPicture picture2 = new MTPicture(new Point2(100, 80), new Point2(50, 150), "data/Thunder.jpg");
		surface.add(picture);
		surface.add(picture2);

		frame.add(surface);

		JButton button = new JButton("Display Cursor");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				surface.setCursorsVisible(!surface.areCursorsVisible());
			}
		});

		frame.add(button);

		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String args[]) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				createGUI();
			}
		});
		System.out.println("ok");
	}

}