package Forms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JSeparator;
import java.awt.BorderLayout;
import javax.swing.JSplitPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import Source.Member;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.event.ActionListener;

import java.sql.*;
import javax.swing.JTable;
import javax.swing.JTabbedPane;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ListSelectionModel;
import java.awt.Scrollbar;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.CardLayout;
import java.awt.GridLayout;

public class MemberForm {

	private JFrame frame;
	private JTextField textMember;
	private final Action action = new SwingAction();

	private JSplitPane splitPane_1;
	private JButton btnDeleteMember;
	private JPanel panel;

	private JTable tableMembers;
	private static Statement st;
	private static Connection conn;
	private static String path;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 * 
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			public void run() {
				try {
					MemberForm window = new MemberForm();
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

	public MemberForm() {
		initialize();
		if (conn != null) {
			show_member();
		}
	}

	public void show_member() {
		ArrayList<Member> list = membersList();
		DefaultTableModel model = (DefaultTableModel) tableMembers.getModel();
		Object[] row = new Object[2];

		for (int i = 0; i < list.size(); i++) {
			// System.out.println(list.get(i).getid());
			row[0] = list.get(i).getid();
			row[1] = list.get(i).getName();
			model.addRow(row);
		}
	}

	public ArrayList<Member> membersList() {
		ArrayList<Member> memberList = new ArrayList<>();
		String query1 = "Select * From Members;";
		try {
			ResultSet rs = st.executeQuery(query1);
			Member member;
			while (rs.next()) {
				member = new Member(rs.getInt("id"), rs.getString("name"));
				memberList.add(member);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return memberList;

	}

	/**
	 * Initialize the contents of the frame.
	 */

	public void delete() {

		int row = tableMembers.getSelectedRow();

		DefaultTableModel model = (DefaultTableModel) tableMembers.getModel();

		int[] selectedRows = tableMembers.getSelectedRows();
		for (int i = 0; i < selectedRows.length; i++) {
			int selIndex = selectedRows[i];

			System.out.println("row:" + selIndex);
			String query1 = "";

			try {
				Object value = model.getValueAt(selIndex, 0);
				query1 = "Delete From members Where id = " + value;

				String querycheck = "select id from Competition where id_member = " + value;

				ResultSet rs = st.executeQuery(querycheck);
				if (rs.next()) {
					String querydel = "Delete from Competition where id = " + rs.getInt("id");
					st.executeUpdate(querydel);
				}

				st.executeUpdate(query1);

			} catch (SQLException e1) {

				e1.printStackTrace();
			}
		}
		model.setRowCount(0);
		show_member();
		System.out.println("Good");

	}

	private void insert() {

		if (!textMember.getText().isEmpty()) {
			// System.out.println(textMember.getText());
			String query1 = "Insert into members (name) Values ('" + textMember.getText() + "')";

			try {
				st.executeUpdate(query1);
				JOptionPane.showMessageDialog(null, "данные добавлены!");
				DefaultTableModel model = (DefaultTableModel) tableMembers.getModel();
				model.setRowCount(0);
				show_member();
				textMember.setText("");

			} catch (SQLException e1) {

				e1.printStackTrace();
			}
		} else {
			System.out.println("Введите значение");
		}

	}

	private void initialize() {
		frame = new JFrame();
		frame.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent key) {

			}

			@Override
			public void keyReleased(KeyEvent e) {

			}
		});
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent arg0) {
				String query1 = "";
				String queryres = "";
				for (Member member : membersList()) {
					query1 = "select id from Competition where id_member = " + member.getid();
					try {
						ResultSet rs = st.executeQuery(query1);
						if (!rs.next()) {
							queryres = "Insert into Competition (id_member,stage1,stage2,stage3,stage4,first_res,stage5,stage6,stage7,stage8,second_res,total) Values("
									+ member.getid() + ",0,0,0,0,0,0,0,0,0,0,0);";
							st.executeUpdate(queryres);
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
				MainForm mainform = new MainForm();
				mainform.setConn(conn);
				mainform.setSt(st);
				mainform.main(null);
			}
		});
		frame.setBounds(100, 100, 497, 530);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		frame.getContentPane().add(panel, BorderLayout.SOUTH);

		JLabel label = new JLabel("\u0423\u0447\u0430\u0441\u0442\u043D\u0438\u043A");
		panel.add(label);

		textMember = new JTextField();
		textMember.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent key) {
				if (key.getKeyCode() == key.VK_ENTER) {
					insert();
				}
			}
		});
		panel.add(textMember);
		textMember.setColumns(10);

		splitPane_1 = new JSplitPane();
		panel.add(splitPane_1);

		JButton btnAddMember = new JButton("Добавить");
		splitPane_1.setLeftComponent(btnAddMember);

		btnDeleteMember = new JButton("Удалить");
		btnDeleteMember.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				delete();
			}
		});
		splitPane_1.setRightComponent(btnDeleteMember);

		scrollPane = new JScrollPane();
		frame.getContentPane().add(scrollPane);

		tableMembers = new JTable();
		tableMembers.setFillsViewportHeight(true);
		scrollPane.setViewportView(tableMembers);
		tableMembers.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		tableMembers.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent key) {

			}

			@Override
			public void keyPressed(KeyEvent key) {
				if (key.getKeyCode() == key.VK_DELETE) {
					delete();
				}

			}
		});
		tableMembers.addInputMethodListener(new InputMethodListener() {
			public void caretPositionChanged(InputMethodEvent arg0) {
			}

			public void inputMethodTextChanged(InputMethodEvent arg0) {
			}
		});
		tableMembers.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {

				int row = tableMembers.getEditingRow();
				int column = tableMembers.getEditingColumn();
				if (row != -1) {

					String value = tableMembers.getModel().getValueAt(row, 1).toString();

					String query1 = "Update members Set name = '" + value + "' Where id = "
							+ tableMembers.getModel().getValueAt(row, 0);

					try {
						st.executeUpdate(query1);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		tableMembers.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "id", "Название" }));
		// frame.getContentPane().add(tableMembers.getTableHeader(),
		// BorderLayout.NORTH);
		// tableMembers.removeColumn(tableMembers.getColumnModel().getColumn(0));

		tableMembers.getColumnModel().getColumn(0).setMinWidth(0);
		tableMembers.getColumnModel().getColumn(0).setMaxWidth(0);
		tableMembers.getColumnModel().getColumn(0).setPreferredWidth(0);
		tableMembers.getColumnModel().getColumn(0).setResizable(false);

		btnAddMember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insert();
			}
		});
		btnAddMember.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
		});
	}

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
		}
	}

	public JTable getTableMembers() {
		return tableMembers;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void setConn(Connection conn) {
		MemberForm.conn = conn;
	}

	public void setSt(Statement st) {
		this.st = st;
	}
}
