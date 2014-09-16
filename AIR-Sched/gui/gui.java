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
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import src.Airsched;

public class gui {

	// http://zetcode.com/tutorials/javaswingtutorial/

	private static final int N_COLS = 3;
	private static final int N_ROWS = 6;

	private static final int COL_GAP = 20;
	private static final int ROW_GAP = 10;

	private static final int BORDER_SIZE = 10;

	private static final int WIDTH = 150 * N_COLS;
	private static final int HEIGHT = 60 * N_ROWS;

	private static JPanel panel;

	private static JFrame getFrame() {
		JFrame ret = new JFrame();
		ret.setTitle("AirSched");
		ret.setSize(WIDTH, HEIGHT);
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
		ret.setLayout(new GridLayout(N_ROWS, N_COLS, ROW_GAP, COL_GAP));
		return ret;
	}

	private static JComboBox<String> getPaddingComboBox() {
//		String[] options = { "No padding", "Empty partition"};
		String[] options = { "No padding", "Empty partition", "Distributed padding" };
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

	private static JComboBox<String> getOrderComboBox() {
		String[] opt = { "No order", "Criticality first", "Larger first",
				"Smaller first" };
		JComboBox<String> ret = new JComboBox<String>(opt);
		ret.setSelectedIndex(0);
		ret.addActionListener(new ActionListener() {
			@SuppressWarnings("unchecked")
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox<String> cb = (JComboBox<String>) e.getSource();
				int selectedIndex = cb.getSelectedIndex();
				if (selectedIndex != -1) {
					Airsched.setOrder(selectedIndex);
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

	/*
	 * private static JPanel getBestModelCheckBox() { JPanel ret = new JPanel();
	 * ret.add(new JTextArea("Best model only?")); JCheckBox cb = new
	 * JCheckBox(); cb.addActionListener(new ActionListener() {
	 * 
	 * @Override public void actionPerformed(ActionEvent e) { JCheckBox source =
	 * (JCheckBox) e.getSource(); boolean state = source.isSelected(); if
	 * (state) { Airsched.setBestModel(true); } else {
	 * Airsched.setBestModel(false); } } }); ret.add(cb); return ret; }
	 */

	private static JCheckBox getBestModelCheckBox() {
		JCheckBox ret = new JCheckBox("Best model ?", true);
		ret.setSelected(Airsched.getBestModel());
		ret.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JCheckBox source = (JCheckBox) e.getSource();
				boolean state = source.isSelected();
				if (state) {
					Airsched.setBestModel(true);
				} else {
					Airsched.setBestModel(false);
				}
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
					openFolder(Airsched.getInput_dir());
				} catch (IOException e1) {
					e1.printStackTrace();
					// throwError("Invalid directory!");
					System.out.println("Invalid directory!");
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
					openFolder(Airsched.getOutput_dir());
				} catch (IOException e1) {
					e1.printStackTrace();
					// throwError("Invalid directory!");
					System.out.println("Invalid directory!");
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

	/*
	 * private static void throwError(final String msg) {
	 * JOptionPane.showMessageDialog(null, msg, "Error",
	 * JOptionPane.ERROR_MESSAGE); }
	 */
	// private static JPanel getDummy() {
	// return new JPanel();
	// }

	private static JFormattedTextField getMinPeriod() {
		JFormattedTextField ret = new JFormattedTextField();
		ret.setValue(String.valueOf(Airsched.getMin_period()));
		ret.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent arg0) {
				JFormattedTextField e = (JFormattedTextField) arg0.getSource();
				String value = (String) e.getValue();
				Airsched.setMin_period(Integer.parseInt(value));
			}
		});
		return ret;
	}

	private static JFormattedTextField getMaxPeriod() {
		JFormattedTextField ret = new JFormattedTextField();
		ret.setValue(String.valueOf(Airsched.getMax_period()));
		ret.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent arg0) {
				JFormattedTextField e = (JFormattedTextField) arg0.getSource();
				String value = (String) e.getValue();
				Airsched.setMax_period(Integer.parseInt(value));
			}
		});
		return ret;
	}

	private static void openFolder(String path) throws IOException {
		File dir = new File(path);
		if(!dir.exists())
			dir.mkdirs();
		Desktop.getDesktop().open(dir);
	}
	
	public static void generateGUI() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				JFrame newFrame = getFrame();
				newFrame.setJMenuBar(getBar());

				panel = getPanel();
				// row 1
				panel.add(new JLabel("Padding/Order:"));
				panel.add(getPaddingComboBox());
				panel.add(getOrderComboBox());
				// row 2
				panel.add(new JLabel("Period min/max:"));
				panel.add(getMinPeriod());
				panel.add(getMaxPeriod());
				// row 3
				panel.add(new JLabel("Processor cap:"));
				panel.add(getUtilizationThreshold());
				panel.add(getBestModelCheckBox());
				// row 4
				panel.add(new JLabel("Partition folder:"));
				panel.add(getInputDir());
				panel.add(getInputOpenFolder());
				// row 5
				panel.add(new JLabel("Model folder:"));
				panel.add(getOutputDir());
				panel.add(getOutputOpenFolder());
				// row 6
				panel.add(new JLabel(" "));
				panel.add(getAnalyseButton());
				panel.add(new JLabel(" "));

				newFrame.add(panel);
				newFrame.setVisible(true);
			}
		});
	}

}
