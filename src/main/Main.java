package main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.LineBorder;

import event.ChangedSideEvent;
import event.ChangedSideListener;
import widget.*;
import tuio.*;

public class Main {

	public static void createGUI() {
		JFrame frame = new JFrame();
		frame.setPreferredSize(new Dimension(840, 620));
		frame.setLayout(new FlowLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		MTSurface surface = new MTSurface();
		surface.setPreferredSize(new Dimension(800, 600));
		surface.setBackground(new Color(255, 255, 255));
		surface.setBorder(new LineBorder(new Color(0, 0, 0)));

		surface.addChangedSideListener(new ChangedSideListener() {
			@Override
			public void changedSidePerformed(ChangedSideEvent evt) {
				System.out.println("curseur d'id: " + evt.getID() + " est à " + (evt.getSide()));
			}
		});

		frame.add(surface);

		JButton button = new JButton("Display Cursor");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				surface.dispCur = (surface.dispCur) ? false : true;
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
