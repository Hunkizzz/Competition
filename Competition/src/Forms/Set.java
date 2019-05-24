package Forms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JTable;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Set {

	private JFrame frame;
	private JTextField name;
	private JTextField column0;
	private JTextField column1;
	private JTextField column2;
	private JTextField column3;
	private JTextField column4;
	private JTextField column5;
	private JTextField column6;
	private JTextField column7;
	private JTextField column8;
	private JTextField column9;
	private JTextField column10;
	
	private JTable table;
	
	private String c0;
	
	private String c1;

	private String c2;

	private String c3;

	private String c4;

	private String c5;

	private String c6;

	private String c7;

	private String c8;

	private String c9;
	
	private String c10;
	
	private JLabel lblName;
	
	MainForm mainform;

	/**
	 * Launch the application.
	 */
	public static void main(JTable tableCompetitions, JLabel lblName, String c0, String c1, String c2, String c3, String c4,
			String c5, String c6, String c7, String c8, String c9, String c10,MainForm mainform) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Set window = new Set(tableCompetitions, lblName, c0,c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, mainform);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Set(JTable tableCompetitions, JLabel lblName, String c0, String c1, String c2, String c3, String c4, String c5,
			String c6, String c7, String c8, String c9, String c10, MainForm mainform) {
		initialize();
		this.table = tableCompetitions;
		this.lblName = lblName;
		this.name.setText(lblName.getText());
		this.column0.setText(c0);
		this.column1.setText(c1);
		this.column2.setText(c2);
		this.column3.setText(c3);
		this.column4.setText(c4);
		this.column5.setText(c5);
		this.column6.setText(c6);
		this.column7.setText(c7);
		this.column8.setText(c8);
		this.column9.setText(c9);
		this.column10.setText(c10);
		this.mainform = mainform;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();

		frame.setBounds(100, 100, 450, 372);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		JLabel lblNewLabel = new JLabel("\u041D\u0430\u0437\u0432\u0430\u043D\u0438\u0435");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		frame.getContentPane().add(lblNewLabel, gbc_lblNewLabel);
		
		name = new JTextField();
		GridBagConstraints gbc_name = new GridBagConstraints();
		gbc_name.insets = new Insets(0, 0, 5, 0);
		gbc_name.fill = GridBagConstraints.HORIZONTAL;
		gbc_name.gridx = 1;
		gbc_name.gridy = 0;
		frame.getContentPane().add(name, gbc_name);
		name.setColumns(10);
		
		JLabel lbl_team = new JLabel("\u041A\u043E\u043C\u0430\u043D\u0434\u0430");
		GridBagConstraints gbc_lbl_team = new GridBagConstraints();
		gbc_lbl_team.anchor = GridBagConstraints.WEST;
		gbc_lbl_team.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_team.gridx = 0;
		gbc_lbl_team.gridy = 1;
		frame.getContentPane().add(lbl_team, gbc_lbl_team);
		
		column0 = new JTextField();
		GridBagConstraints gbc_column0 = new GridBagConstraints();
		gbc_column0.insets = new Insets(0, 0, 5, 0);
		gbc_column0.fill = GridBagConstraints.HORIZONTAL;
		gbc_column0.gridx = 1;
		gbc_column0.gridy = 1;
		frame.getContentPane().add(column0, gbc_column0);
		column0.setColumns(10);
		
		JLabel label = new JLabel("\u041F\u0435\u0440\u0432\u044B\u0439 \u044D\u0442\u0430\u043F");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.anchor = GridBagConstraints.WEST;
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 0;
		gbc_label.gridy = 2;
		frame.getContentPane().add(label, gbc_label);
		
		column1 = new JTextField();
		column1.setColumns(10);
		GridBagConstraints gbc_column1 = new GridBagConstraints();
		gbc_column1.insets = new Insets(0, 0, 5, 0);
		gbc_column1.fill = GridBagConstraints.HORIZONTAL;
		gbc_column1.gridx = 1;
		gbc_column1.gridy = 2;
		frame.getContentPane().add(column1, gbc_column1);
		
		JLabel label_1 = new JLabel("\u0412\u0442\u043E\u0440\u043E\u0439 \u044D\u0442\u0430\u043F");
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.anchor = GridBagConstraints.WEST;
		gbc_label_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_1.gridx = 0;
		gbc_label_1.gridy = 3;
		frame.getContentPane().add(label_1, gbc_label_1);
		
		column2 = new JTextField();
		column2.setColumns(10);
		GridBagConstraints gbc_column2 = new GridBagConstraints();
		gbc_column2.insets = new Insets(0, 0, 5, 0);
		gbc_column2.fill = GridBagConstraints.HORIZONTAL;
		gbc_column2.gridx = 1;
		gbc_column2.gridy = 3;
		frame.getContentPane().add(column2, gbc_column2);
		
		JLabel label_2 = new JLabel("\u0442\u0440\u0435\u0442\u0438\u0439 \u044D\u0442\u0430\u043F");
		GridBagConstraints gbc_label_2 = new GridBagConstraints();
		gbc_label_2.anchor = GridBagConstraints.WEST;
		gbc_label_2.insets = new Insets(0, 0, 5, 5);
		gbc_label_2.gridx = 0;
		gbc_label_2.gridy = 4;
		frame.getContentPane().add(label_2, gbc_label_2);
		
		column3 = new JTextField();
		column3.setColumns(10);
		GridBagConstraints gbc_column3 = new GridBagConstraints();
		gbc_column3.insets = new Insets(0, 0, 5, 0);
		gbc_column3.fill = GridBagConstraints.HORIZONTAL;
		gbc_column3.gridx = 1;
		gbc_column3.gridy = 4;
		frame.getContentPane().add(column3, gbc_column3);
		
		JLabel label_3 = new JLabel("\u0427\u0435\u0442\u0432\u0435\u0440\u0442\u044B\u0439 \u044D\u0442\u0430\u043F");
		GridBagConstraints gbc_label_3 = new GridBagConstraints();
		gbc_label_3.anchor = GridBagConstraints.WEST;
		gbc_label_3.insets = new Insets(0, 0, 5, 5);
		gbc_label_3.gridx = 0;
		gbc_label_3.gridy = 5;
		frame.getContentPane().add(label_3, gbc_label_3);
		
		column4 = new JTextField();
		column4.setColumns(10);
		GridBagConstraints gbc_column4 = new GridBagConstraints();
		gbc_column4.insets = new Insets(0, 0, 5, 0);
		gbc_column4.fill = GridBagConstraints.HORIZONTAL;
		gbc_column4.gridx = 1;
		gbc_column4.gridy = 5;
		frame.getContentPane().add(column4, gbc_column4);
		
		JLabel label_4 = new JLabel("\u043F\u0440\u043E\u043C\u0435\u0436\u0443\u0442\u043E\u0447\u043D\u044B\u0439 \u0440\u0435\u0437\u0443\u043B\u044C\u0442\u0430\u0442");
		GridBagConstraints gbc_label_4 = new GridBagConstraints();
		gbc_label_4.anchor = GridBagConstraints.WEST;
		gbc_label_4.insets = new Insets(0, 0, 5, 5);
		gbc_label_4.gridx = 0;
		gbc_label_4.gridy = 6;
		frame.getContentPane().add(label_4, gbc_label_4);
		
		column5 = new JTextField();
		column5.setColumns(10);
		GridBagConstraints gbc_column5 = new GridBagConstraints();
		gbc_column5.insets = new Insets(0, 0, 5, 0);
		gbc_column5.fill = GridBagConstraints.HORIZONTAL;
		gbc_column5.gridx = 1;
		gbc_column5.gridy = 6;
		frame.getContentPane().add(column5, gbc_column5);
		
		JLabel label_5 = new JLabel("\u041F\u044F\u0442\u044B\u0439 \u044D\u0442\u0430\u043F");
		GridBagConstraints gbc_label_5 = new GridBagConstraints();
		gbc_label_5.anchor = GridBagConstraints.WEST;
		gbc_label_5.insets = new Insets(0, 0, 5, 5);
		gbc_label_5.gridx = 0;
		gbc_label_5.gridy = 7;
		frame.getContentPane().add(label_5, gbc_label_5);
		
		column6 = new JTextField();
		column6.setColumns(10);
		GridBagConstraints gbc_column6 = new GridBagConstraints();
		gbc_column6.insets = new Insets(0, 0, 5, 0);
		gbc_column6.fill = GridBagConstraints.HORIZONTAL;
		gbc_column6.gridx = 1;
		gbc_column6.gridy = 7;
		frame.getContentPane().add(column6, gbc_column6);
		
		JLabel label_6 = new JLabel("\u0428\u0435\u0441\u0442\u043E\u0439 \u044D\u0442\u0430\u043F");
		GridBagConstraints gbc_label_6 = new GridBagConstraints();
		gbc_label_6.anchor = GridBagConstraints.WEST;
		gbc_label_6.insets = new Insets(0, 0, 5, 5);
		gbc_label_6.gridx = 0;
		gbc_label_6.gridy = 8;
		frame.getContentPane().add(label_6, gbc_label_6);
		
		column7 = new JTextField();
		column7.setColumns(10);
		GridBagConstraints gbc_column7 = new GridBagConstraints();
		gbc_column7.insets = new Insets(0, 0, 5, 0);
		gbc_column7.fill = GridBagConstraints.HORIZONTAL;
		gbc_column7.gridx = 1;
		gbc_column7.gridy = 8;
		frame.getContentPane().add(column7, gbc_column7);
		
		JLabel label_7 = new JLabel("\u0421\u0435\u0434\u044C\u043C\u043E\u0439 \u044D\u0442\u0430\u043F");
		GridBagConstraints gbc_label_7 = new GridBagConstraints();
		gbc_label_7.anchor = GridBagConstraints.WEST;
		gbc_label_7.insets = new Insets(0, 0, 5, 5);
		gbc_label_7.gridx = 0;
		gbc_label_7.gridy = 9;
		frame.getContentPane().add(label_7, gbc_label_7);
		
		column8 = new JTextField();
		column8.setColumns(10);
		GridBagConstraints gbc_column8 = new GridBagConstraints();
		gbc_column8.insets = new Insets(0, 0, 5, 0);
		gbc_column8.fill = GridBagConstraints.HORIZONTAL;
		gbc_column8.gridx = 1;
		gbc_column8.gridy = 9;
		frame.getContentPane().add(column8, gbc_column8);
		
		JLabel label_8 = new JLabel("\u0412\u043E\u0441\u044C\u043C\u043E\u0439 \u044D\u0442\u0430\u043F");
		GridBagConstraints gbc_label_8 = new GridBagConstraints();
		gbc_label_8.anchor = GridBagConstraints.BASELINE_LEADING;
		gbc_label_8.insets = new Insets(0, 0, 5, 5);
		gbc_label_8.gridx = 0;
		gbc_label_8.gridy = 10;
		frame.getContentPane().add(label_8, gbc_label_8);
		
		column9 = new JTextField();
		column9.setColumns(10);
		GridBagConstraints gbc_column9 = new GridBagConstraints();
		gbc_column9.insets = new Insets(0, 0, 5, 0);
		gbc_column9.fill = GridBagConstraints.HORIZONTAL;
		gbc_column9.gridx = 1;
		gbc_column9.gridy = 10;
		frame.getContentPane().add(column9, gbc_column9);
		
		JLabel label_9 = new JLabel("\u0418\u0442\u043E\u0433");
		GridBagConstraints gbc_label_9 = new GridBagConstraints();
		gbc_label_9.anchor = GridBagConstraints.WEST;
		gbc_label_9.insets = new Insets(0, 0, 5, 5);
		gbc_label_9.gridx = 0;
		gbc_label_9.gridy = 11;
		frame.getContentPane().add(label_9, gbc_label_9);
		
		column10 = new JTextField();
		column10.setColumns(10);
		GridBagConstraints gbc_column10 = new GridBagConstraints();
		gbc_column10.insets = new Insets(0, 0, 5, 0);
		gbc_column10.fill = GridBagConstraints.HORIZONTAL;
		gbc_column10.gridx = 1;
		gbc_column10.gridy = 11;
		frame.getContentPane().add(column10, gbc_column10);
		
		JButton btnNewButton = new JButton("\u041F\u0440\u0438\u043C\u0435\u043D\u0438\u0442\u044C");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				
					refresh();
			}
		});
		btnNewButton.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent key) {
				if (key.getKeyCode() == KeyEvent.VK_ENTER) {
					refresh();
				}
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton.anchor = GridBagConstraints.SOUTH;
		gbc_btnNewButton.gridwidth = 2;
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 12;
		frame.getContentPane().add(btnNewButton, gbc_btnNewButton);
	}
	
	public void refresh() {
		mainform.Setcolumn0(column0.getText());
		mainform.Setcolumn1(column1.getText());
		mainform.Setcolumn2(column2.getText());
		mainform.Setcolumn3(column3.getText());
		mainform.Setcolumn4(column4.getText());
		mainform.Setcolumn5(column5.getText());
		mainform.Setcolumn6(column6.getText());
		mainform.Setcolumn7(column7.getText());
		mainform.Setcolumn8(column8.getText());
		mainform.Setcolumn9(column9.getText());
		mainform.Setcolumn10(column10.getText());
		
		table.getColumnModel().getColumn(1).setHeaderValue(column0.getText());
		table.getColumnModel().getColumn(2).setHeaderValue(column1.getText());
		table.getColumnModel().getColumn(3).setHeaderValue(column2.getText());
		table.getColumnModel().getColumn(4).setHeaderValue(column3.getText());
		table.getColumnModel().getColumn(5).setHeaderValue(column4.getText());
		table.getColumnModel().getColumn(6).setHeaderValue(column5.getText());
		table.getColumnModel().getColumn(7).setHeaderValue(column6.getText());
		table.getColumnModel().getColumn(8).setHeaderValue(column7.getText());
		table.getColumnModel().getColumn(9).setHeaderValue(column8.getText());
		table.getColumnModel().getColumn(10).setHeaderValue(column9.getText());
		table.getColumnModel().getColumn(12).setHeaderValue(column10.getText());
		table.updateUI();
		lblName.setText(name.getText());
	}

}
