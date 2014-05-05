package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import src.Airsched;

public class gui {

	private static JFrame getFrame() {
		JFrame ret = new JFrame();
		ret.setTitle("AirSched");
		ret.setSize(640, 480);
		ret.setLocationRelativeTo(null);
		ret.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		return ret;
	}

	private static JMenuBar getBar() {
		JMenuBar ret = new JMenuBar();
		JMenu file = new JMenu("File");
		JMenuItem exit = new JMenuItem("exit");
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			}
		});
		file.add(exit);
		ret.add(file);
		return ret;
	}

	private static JPanel getPanel() {
		JPanel ret = new JPanel();
		ret.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		ret.setLayout(new GridLayout(2, 3, 5, 5));
		return ret;
	}

	private static JComboBox<String> getComboBox() {
		String[] options = { "no padding", "empty partition",
				"parametric padding" };
		JComboBox<String> ret = new JComboBox<String>(options);
		ret.setSelectedIndex(0);
		ret.addActionListener(new ActionListener() {
			@SuppressWarnings("unchecked")
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox<String> cb = (JComboBox<String>) e.getSource();
				int selectedIndex = cb.getSelectedIndex();
				if (selectedIndex != -1) {
					Airsched.setPartitionPaddingMode(selectedIndex);
				}
			}
		});
		return ret;
	}

	private static JFormattedTextField getUtilizationThreshold() {
		JFormattedTextField ret = new JFormattedTextField();
		ret.setValue(100);
		ret.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent arg0) {
				JFormattedTextField e = (JFormattedTextField) arg0.getSource();
				int value = (int) e.getValue();
				Airsched.setUtilizationThreshold(value);
			}
		});
		return ret;
	}
	
	

	public static void generateGUI() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				JFrame newFrame = getFrame();
				newFrame.setJMenuBar(getBar());
				newFrame.add(getPanel());
				newFrame.setVisible(true);
			}
		});
	}

}
