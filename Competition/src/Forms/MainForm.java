package Forms;

import java.awt.EventQueue;
import java.util.ArrayList;

import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import Source.Competition;
import Source.Member;
import javafx.scene.control.TableColumn;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.PopupMenuEvent;
import javax.swing.JSplitPane;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.beans.PropertyChangeEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.FlowLayout;
import javax.swing.JLayeredPane;
import javax.swing.JLabel;
import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;

public class MainForm {

	private JFrame frame;
	private static Statement st;
	private static Connection conn = null;
	private JComboBox<String> comboBoxMembers;
	private JTable tableCompetitions;
	private DefaultTableModel model;
	private static String path;
	private String column0,column1,column2,column3,column4,column5,column6,column7,column8,column9,column10;
	private static MainForm window;
	private JLabel lblName;
	private String NameCompetition;
	
	public void setPath(String path) {
		this.path = path;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public void setSt(Statement st) {
		this.st = st;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new MainForm();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void insert() {

		if (comboBoxMembers.toString() != "") {
			int id_member = 0;
			String queryid = "select id from Members where name = " + "'" + comboBoxMembers.getSelectedItem() + "'";
			System.out.println(queryid);
			try {
				ResultSet rs = st.executeQuery(queryid);
				id_member = rs.getInt("id");
			} catch (SQLException e1) {
				e1.printStackTrace();
				return;
			}
			String query = "Insert into Competition (id_member,stage1,stage2,stage3,stage4,first_res,stage5,stage6,stage7,stage8,second_res,total) Values("
					+ id_member + ",0,0,0,0,0,0,0,0,0,0,0);";
			System.out.println(query);
			try {
				st.executeUpdate(query);

			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			model.setRowCount(0);
			show_comp();

		}

	}

	private void delete() {

		int row = tableCompetitions.getSelectedRow();
		// int col = tableMembers.getSelectedColumn();
		// DefaultTableModel tm = (DefaultTableModel) talbeHistory.getModel();
		// Object ob = tm.getValueAt(row, col);
		// talbeHistory.setValueAt(ob, row, col);
		String query1 = "Delete From Competition Where id = " + tableCompetitions.getValueAt(row, 0);
		;

		try {
			st.executeUpdate(query1);
			JOptionPane.showMessageDialog(null, "данные удалены!");
			DefaultTableModel model = (DefaultTableModel) tableCompetitions.getModel();

		} catch (SQLException e1) {

			e1.printStackTrace();
		}
		model.setRowCount(0);
		show_comp();

	}

	public ArrayList<Competition> compList() {
		ArrayList<Competition> compList = new ArrayList<>();
		String query1 = "Select * From Competition ORDER BY total Desc";
		try {
			ResultSet rs = st.executeQuery(query1);
			Competition comp;
			while (rs.next()) {
				comp = new Competition(rs.getInt("id"), rs.getInt("id_member"), rs.getFloat("stage1"),
						rs.getFloat("stage2"), rs.getFloat("stage3"), rs.getFloat("stage4"), rs.getFloat("stage5"),
						rs.getFloat("stage6"), rs.getFloat("stage7"), rs.getFloat("stage8"));
				compList.add(comp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return compList;
	}

	public void show_comp() {
		ArrayList<Competition> list = compList();
		model = (DefaultTableModel) tableCompetitions.getModel();
		Object[] row = new Object[13];
		String queryMember = "select name from Members where id = ";
		String name = "";
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getId());
			row[0] = list.get(i).getId();
			ResultSet rs;
			try {
				rs = st.executeQuery(queryMember + list.get(i).getId_member());
				name = rs.getString("name");
				row[1] = name;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			row[2] = list.get(i).getStage1();
			row[3] = list.get(i).getStage2();
			row[4] = list.get(i).getStage3();
			row[5] = list.get(i).getStage4();
			row[6] = list.get(i).getFirst_res();
			row[7] = list.get(i).getStage5();
			row[8] = list.get(i).getStage6();
			row[9] = list.get(i).getStage7();
			row[10] = list.get(i).getStage8();
			row[11] = list.get(i).getSecond_res();
			row[12] = list.get(i).getTotal();
			model.addRow(row);
		}
	}

	/**
	 * Create the application.
	 */
	public MainForm() {
		initialize();
		if (conn == null) {
			init();
			return;
		}
		if (conn != null) {
			fillComboBox();
			show_comp();
		}
	}

	public void init() {
		try {
			if (path == null) {
				return;
			}
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:" + path);
			st = conn.createStatement();
			fillComboBox();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		
	}

	private void fillComboBox() {
		try {
			String query = "select * from members";

			PreparedStatement pst = conn.prepareStatement(query);
			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {
				String name = rs.getString("name");
				comboBoxMembers.addItem(name);
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		javax.swing.table.TableColumn membercell = tableCompetitions.getColumnModel().getColumn(1);
		if (conn != null) {
			membercell.setCellEditor(new DefaultCellEditor(generateBox()));
		}
	}
	public void Setcolumn0(String column0) {
		this.column0 = column0;
	}
	public void Setcolumn1(String column1) {
		this.column1 = column1;
	}
	public void Setcolumn2(String column2) {
		this.column2 = column2;
	}
	public void Setcolumn3(String column3) {
		this.column3 = column3;
	}
	public void Setcolumn4(String column4) {
		this.column4 = column4;
	}
	public void Setcolumn5(String column5) {
		this.column5 = column5;
	}
	public void Setcolumn6(String column6) {
		this.column6 = column6;
	}
	public void Setcolumn7(String column7) {
		this.column7 = column7;
	}
	public void Setcolumn8(String column8) {
		this.column8 = column8;
	}
	public void Setcolumn9(String column9) {
		this.column9 = column9;
	}
	public void Setcolumn10(String column10) {
		this.column10 = column10;
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 680, 415);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		column0 = new String("Команда");
		column1 = new String("1 этап");
		column2 = new String("2 этап");
		column3 = new String("3 этап");
		column4 = new String("4 этап");
		column5 = new String("Промежуточный результат");
		column6 = new String("5 этап");
		column7 = new String("6 этап");
		column8 = new String("7 этап");
		column9 = new String("8 этап");
		column10 = new String("Всего");
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mnMenu = new JMenu("Меню");
		menuBar.add(mnMenu);

		JMenuItem mntmNewMenuItem = new JMenuItem("Пользователи");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(conn == null) {
					JOptionPane.showMessageDialog(null, "Не выбран файл базы!");
					return;
				}
				MemberForm memberform = new MemberForm();
				memberform.setConn(conn);
				memberform.setSt(st);
				memberform.main(null);
				frame.dispose();
			}
		});

		JMenuItem mntmChooseFile = new JMenuItem("Выбрать файл");
		mntmChooseFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				JFileChooser fileopen = new JFileChooser();
				int ret = fileopen.showDialog(null, "Открыть файл");
				if (ret == JFileChooser.APPROVE_OPTION) {
					File file = fileopen.getSelectedFile();
					path = file.getAbsolutePath();
					init();
					//System.out.println(path);
					show_comp();
				}

			}
		});
		mntmChooseFile.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

			}
		});
		mnMenu.add(mntmChooseFile);
		mnMenu.add(mntmNewMenuItem);
		
		JMenuItem menuItem = new JMenuItem("\u041D\u0430\u0441\u0442\u0440\u043E\u0439\u043A\u0438");
		menuItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				System.out.println(column1);
				Set.main(tableCompetitions, lblName,column0,column1,column2,column3,column4,column5,column6,column7,column8,column9,column10,window);
			}
		});
		mnMenu.add(menuItem);

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.SOUTH);

		comboBoxMembers = new JComboBox();
		panel.add(comboBoxMembers);

		JSplitPane splitPane = new JSplitPane();
		panel.add(splitPane);

		JButton btnAddComp = new JButton("Добавить");
		btnAddComp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				insert();
			}
		});
		splitPane.setLeftComponent(btnAddComp);

		JButton btnDeleteComp = new JButton("Удалить");
		btnDeleteComp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				delete();
			}
		});
		splitPane.setRightComponent(btnDeleteComp);
		comboBoxMembers.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent key) {
				if (key.getKeyCode() == key.VK_ENTER) {
					insert();
				}

			}
		});
		comboBoxMembers.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent arg0) {
			}

			public void popupMenuWillBecomeInvisible(PopupMenuEvent arg0) {
			}

			public void popupMenuWillBecomeVisible(PopupMenuEvent arg0) {

			}
		});
		comboBoxMembers.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

			}
		});
		comboBoxMembers.addContainerListener(new ContainerAdapter() {
			@Override
			public void componentAdded(ContainerEvent arg0) {

			}
		});
		comboBoxMembers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});
		// panel_2.add(tableCompetitions, BorderLayout.CENTER);

		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1, BorderLayout.NORTH);

		lblName= new JLabel("\u0421\u043E\u0440\u0435\u0432\u043D\u043E\u0432\u0430\u043D\u0438\u0435");
		panel_1.add(lblName);

		JPanel panel_2 = new JPanel();
		frame.getContentPane().add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(0, 0));

		tableCompetitions = new JTable();
		//panel_2.add(tableCompetitions);
		JScrollPane scrollPane = new JScrollPane(tableCompetitions);
		panel_2.add(scrollPane);

		tableCompetitions.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent key) {
				if (key.getKeyCode() == key.VK_DELETE) {
					delete();
				}
			}
		});
		panel_2.add(tableCompetitions.getTableHeader(), BorderLayout.NORTH);
		tableCompetitions.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {

				int row = tableCompetitions.getEditingRow();
				int column = tableCompetitions.getEditingColumn();
				if (row != -1) {

					String queryid = "select id from Members where name = " + "'"
							+ tableCompetitions.getModel().getValueAt(row, 1) + "'";
					int id_member = -1;
					try {
						ResultSet rs = st.executeQuery(queryid);
						id_member = rs.getInt("id");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					String value = tableCompetitions.getModel().getValueAt(row, 1).toString();
					float first_res, second_res = 0;
					first_res = 0;
					second_res = 0;
					String query1 = "Update Competition Set id_member = '" + id_member + "', stage1 = "
							+ tableCompetitions.getModel().getValueAt(row, 2) + ",stage2 = "
							+ tableCompetitions.getModel().getValueAt(row, 3) + ",stage3 ="
							+ tableCompetitions.getModel().getValueAt(row, 4) + ",stage4 ="
							+ tableCompetitions.getModel().getValueAt(row, 5) + ",first_res ="
							+ tableCompetitions.getModel().getValueAt(row, 6) + ",stage5 ="
							+ tableCompetitions.getModel().getValueAt(row, 7) + ",stage6 ="
							+ tableCompetitions.getModel().getValueAt(row, 8) + ",stage7 ="
							+ tableCompetitions.getModel().getValueAt(row, 9) + ",stage8 ="
							+ tableCompetitions.getModel().getValueAt(row, 10) + ",second_res ="
							+ tableCompetitions.getModel().getValueAt(row, 11) + ",total ="
							+ tableCompetitions.getModel().getValueAt(row, 12) + " Where id = "
							+ tableCompetitions.getModel().getValueAt(row, 0);
					System.out.println(query1);
					try {
						st.executeUpdate(query1);

					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					model.setRowCount(0);
					show_comp();
					query1 = "Update Competition Set total =" + tableCompetitions.getModel().getValueAt(row, 12)
							+ " Where id =" + tableCompetitions.getModel().getValueAt(row, 0);
					try {
						st.executeUpdate(query1);

					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					model.setRowCount(0);
					show_comp();
				}
			}
		});

		tableCompetitions.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "id", column0, column1, column2, column3, column4 , column5,
						column6, column7, column8, column9, "Промежуточный результат", column10 }));

		tableCompetitions.setColumnSelectionAllowed(true);
		tableCompetitions.setCellSelectionEnabled(true);

		// tableCompetitions.removeColumn(tableCompetitions.getColumnModel().getColumn(0));
		tableCompetitions.getColumnModel().getColumn(0).setMinWidth(0);
		tableCompetitions.getColumnModel().getColumn(0).setMaxWidth(0);
		tableCompetitions.getColumnModel().getColumn(0).setPreferredWidth(0);
		tableCompetitions.getColumnModel().getColumn(0).setResizable(false);
		
		tableCompetitions.getColumnModel().getColumn(11).setMinWidth(0);
		tableCompetitions.getColumnModel().getColumn(11).setMaxWidth(0);
		tableCompetitions.getColumnModel().getColumn(11).setPreferredWidth(0);
		tableCompetitions.getColumnModel().getColumn(11).setResizable(false);
		

		// new JScrollPane(tableCompetitions, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
		// JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		// tableCompetitions.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

	}

	private JComboBox generateBox() {
		JComboBox bx = null;

		try {
			String query = "select * from members";
			ResultSet rs = st.executeQuery(query);
			System.out.println("есть контакт");
			bx = new JComboBox();
			while (rs.next()) {

				String name = rs.getString("name");
				bx.addItem(name);
			}

		} catch (Exception x) {
			// System.out.println(x.getMessage());
		}
		return bx;

	}

	private static void closeStatements() throws SQLException {

		conn.close();

	}

}
