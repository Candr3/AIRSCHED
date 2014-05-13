package gui;

import java.awt.Desktop;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import src.Airsched;

public class gui {

	// http://zetcode.com/tutorials/javaswingtutorial/

	private static final int N_COLS = 2;
	private static final int N_ROWS = 4;

	private static final int COL_SIZE = 20;
	private static final int ROW_SIZE = 10;

	private static final int BORDER_SIZE = 10;

	private static JPanel panel;

	private static JFrame getFrame() {
		JFrame ret = new JFrame();
		ret.setTitle("AirSched");
		ret.setSize(600, 200);
		ret.setLocationRelativeTo(null);
		ret.setResizable(false);
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
		ret.setBorder(BorderFactory.createEmptyBorder(BORDER_SIZE, BORDER_SIZE,
				BORDER_SIZE, BORDER_SIZE));
		ret.setLayout(new GridLayout(N_ROWS, N_COLS, ROW_SIZE, COL_SIZE));
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

	private static JFormattedTextField getInputDir() {
		JFormattedTextField ret = new JFormattedTextField();
		ret.setValue(Airsched.getInput_dir());
		ret.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent arg0) {
				JFormattedTextField e = (JFormattedTextField) arg0.getSource();
				String value = (String) e.getValue();
				Airsched.setInput_dir(value);
			}
		});
		return ret;
	}

	private static JButton getInputOpenFolder() {
		JButton ret = new JButton("Open Folder");
		ret.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Desktop.getDesktop()
							.open(new File(Airsched.getInput_dir()));
				} catch (IOException e1) {
					e1.printStackTrace();
					throwError("Invalid directory!");
				}
			}
		});
		return ret;
	}

	private static JFormattedTextField getOutputDir() {
		JFormattedTextField ret = new JFormattedTextField();
		ret.setValue(Airsched.getOutput_dir());
		ret.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent arg0) {
				JFormattedTextField e = (JFormattedTextField) arg0.getSource();
				String value = (String) e.getValue();
				Airsched.setOutput_dir(value);
			}
		});
		return ret;
	}

	private static JButton getOutputOpenFolder() {
		final JButton ret = new JButton("Open Folder");
		ret.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Desktop.getDesktop().open(
							new File(Airsched.getOutput_dir()));
				} catch (IOException e1) {
					e1.printStackTrace();
					throwError("Invalid directory!");
				}
			}
		});
		return ret;
	}

	private static JButton getAnalyseButton() {
		JButton ret = new JButton("Analyse");
		ret.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Airsched.analyse();
			}
		});
		return ret;
	}

	private static void throwError(final String msg) {
		JOptionPane.showMessageDialog(null, msg, "Error",
				JOptionPane.ERROR_MESSAGE);
	}

	public static void generateGUI() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				JFrame newFrame = getFrame();
				newFrame.setJMenuBar(getBar());

				panel = getPanel();
				panel.add(getComboBox());
				panel.add(getUtilizationThreshold());
				panel.add(getInputDir());
				panel.add(getInputOpenFolder());
				panel.add(getOutputDir());
				panel.add(getOutputOpenFolder());
				panel.add(getAnalyseButton());

				newFrame.add(panel);
				newFrame.setVisible(true);
			}
		});
	}

}
