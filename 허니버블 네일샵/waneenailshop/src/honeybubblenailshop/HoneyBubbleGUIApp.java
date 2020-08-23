package honeybubblenailshop;

/********************************************************
 파   일   명 : HoneyBubbleGUIApp.java
 기        능 : 네일샵 회원관리 프로그램

 작 성  일 자 : 2019.06.05
 작   성   자 : 전혜원
 *********************************************************/
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class HoneyBubbleGUIApp extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;

// 필드 선언 ----------------------------------------------------------------------------------------------------
	public static final int NONE = 0;
	public static final int ADD = 1;
	public static final int DELETE = 2;
	public static final int UPDATE = 3;
	public static final int UPDATE_CHANGE = 4;
	public static final int SEARCH = 5;

	JTextField noTF, nameTF, phoneTF, typeTF, memoTF;
	JButton addB, deleteB, updateB, searchB, cancelB;
	JTable table;

	int cmd;
	private JPanel top;
	private JPanel panel;

// 디자인 메소드 ----------------------------------------------------------------------------------------------------
	
	public HoneyBubbleGUIApp() throws Exception {
		setTitle("*~허니버블 네일샵 회원관리 프로그램~*");
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("img/nailshop logo.jpg")));
		setSize(800, 600);

		Dimension dim = getToolkit().getScreenSize();
		setLocation(dim.width / 2 - getWidth() / 2, dim.height / 2 - getHeight() / 2);

		Object[] title = { "번   호", "이   름", "연 락 처", "구   분", "메   모" };
		getContentPane().setLayout(null);

		top = new JPanel();
		top.setBounds(0, 0, 784, 136);
		getContentPane().add(top);
		top.setBackground(new Color(230, 230, 250));
		top.setForeground(Color.DARK_GRAY);
		top.setLayout(null);
		top.add(searchB = new JButton("회원검색"));
		searchB.setForeground(new Color(224, 255, 255));

		JPanel coreinfor = new JPanel();
		coreinfor.setBorder(new LineBorder(new Color(112, 128, 144)));
		coreinfor.setBounds(24, 35, 509, 39);
		coreinfor.setForeground(Color.DARK_GRAY);
		coreinfor.setBackground(new Color(230, 230, 250));
		coreinfor.setLayout(null);
		top.add(coreinfor);
		
		JLabel label_3 = new JLabel("이    름");
		label_3.setBounds(5, 12, 40, 15);
		coreinfor.add(label_3);
		coreinfor.add(nameTF = new JTextField(10));
		nameTF.setBounds(50, 9, 116, 21);
		searchB.setBounds(344, 8, 155, 23);
		searchB.setBackground(new Color(188, 143, 143));
		
		JLabel label = new JLabel("연 락 처");
		label.setBounds(170, 12, 58, 15);
		coreinfor.add(label);
		coreinfor.add(phoneTF = new JTextField(10));
		phoneTF.setBounds(216, 9, 116, 21);
		coreinfor.add(phoneTF);
		coreinfor.add(searchB);

		JPanel alpha = new JPanel();
		alpha.setBorder(new LineBorder(new Color(119, 136, 153)));
		alpha.setBounds(24, 83, 509, 37);
		alpha.setForeground(Color.DARK_GRAY);
		alpha.setBackground(new Color(230, 230, 250));
		alpha.setLayout(null);
		
		JLabel label_4 = new JLabel("번    호");
		label_4.setBounds(5, 12, 40, 15);
		alpha.add(label_4);
		
		alpha.add(noTF = new JTextField(10));
		noTF.setBounds(50, 8, 116, 21);

		JLabel label_1 = new JLabel("구    분");
		label_1.setBounds(170, 12, 40, 15);
		alpha.add(label_1);
		
		alpha.add(typeTF = new JTextField(10));
		typeTF.setBounds(216, 8, 116, 21);
		alpha.add(typeTF);
		
		JLabel label_2 = new JLabel("메    모");
		label_2.setBounds(337, 12, 40, 15);
		alpha.add(label_2);
		
		alpha.add(memoTF = new JTextField(10));
		memoTF.setBounds(382, 8, 116, 21);
		alpha.add(memoTF);

		JPanel buttons = new JPanel();
		buttons.setBounds(542, 26, 216, 100);
		buttons.setLayout(new GridLayout(1, 5));

		top.add(alpha);
		top.add(buttons);


		//버튼에 이미지 입히기
		addB = new JButton(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("img/bplus.jpg"))));
		addB.setAlignmentX(5.0f);
		addB.setContentAreaFilled(false);
		addB.setFocusPainted(false);
		buttons.add(addB);

		updateB = new JButton(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("img/bchange.jpg"))));
		updateB.setAlignmentX(5.0f);
		updateB.setContentAreaFilled(false);
		updateB.setFocusPainted(false);
		buttons.add(updateB);

		deleteB = new JButton(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("img/bdelete.jpg"))));
		deleteB.setAlignmentX(5.0f);
		deleteB.setContentAreaFilled(false);
		deleteB.setFocusPainted(false);
		buttons.add(deleteB);

		cancelB = new JButton(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("img/bcancel.jpg"))));
		cancelB.setAlignmentX(5.0f);
		cancelB.setContentAreaFilled(false);
		cancelB.setFocusPainted(false);
		buttons.add(cancelB);

		//버튼에 이벤트 부여
		addB.addActionListener(this);
		updateB.addActionListener(this);
		deleteB.addActionListener(this);
		cancelB.addActionListener(this);
		searchB.addActionListener(this);

		panel = new JPanel();
		panel.setBackground(new Color(230, 230, 250));
		panel.setBounds(0, 118, 784, 444);
		getContentPane().add(panel);
		panel.setLayout(null);
		table = new JTable(new DefaultTableModel(title, 0));
		table.setBackground(new Color(248, 248, 255));

		JScrollPane sp = new JScrollPane(table);
		sp.setBounds(24, 25, 734, 376);
		panel.add(sp);
		sp.setForeground(new Color(119, 136, 153));
		cmd = NONE;
		initialize();

		displayAllMember();

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	
// 필드&버튼 활성화-비활성화  ----------------------------------------------------------------------------------------------------
	public void initialize() {
		noTF.setEditable(false);
		nameTF.setEditable(false);
		phoneTF.setEditable(false);
		typeTF.setEditable(false);
		memoTF.setEditable(false);
	}

	public void setEditable(int n) {
		switch (n) {
		case ADD:
			noTF.setEditable(true);
			nameTF.setEditable(true);
			phoneTF.setEditable(true);
			typeTF.setEditable(true);
			memoTF.setEditable(true);
			break;
		case DELETE:
			noTF.setEditable(true);
			break;
		case UPDATE:
			noTF.setEditable(true);
			break;
		case UPDATE_CHANGE:
			noTF.setEditable(false);
			nameTF.setEditable(true);
			phoneTF.setEditable(true);
			typeTF.setEditable(true);
			memoTF.setEditable(true);
			break;
		case SEARCH:
			nameTF.setEditable(true);
			phoneTF.setEditable(true);
			break;
		case NONE:
			noTF.setEditable(false);
			nameTF.setEditable(false);
			phoneTF.setEditable(false);
			typeTF.setEditable(false);
			memoTF.setEditable(false);
		}
	}

	public void setEnable(int n) {
		addB.setEnabled(false);
		deleteB.setEnabled(false);
		updateB.setEnabled(false);
		searchB.setEnabled(false);

		switch (n) {
		case ADD:
			addB.setEnabled(true);
			setEditable(ADD);
			cmd = ADD;
			break;
		case DELETE:
			deleteB.setEnabled(true);
			setEditable(DELETE);
			cmd = DELETE;
			break;
		case UPDATE:
			updateB.setEnabled(true);
			setEditable(UPDATE);
			cmd = UPDATE;
			break;
		case UPDATE_CHANGE:
			updateB.setEnabled(true);
			setEditable(UPDATE_CHANGE);
			cmd = UPDATE_CHANGE;
			break;
		case SEARCH:
			searchB.setEnabled(true);
			setEditable(SEARCH);
			cmd = SEARCH;
			break;
		case NONE:
			addB.setEnabled(true);
			deleteB.setEnabled(true);
			updateB.setEnabled(true);
			searchB.setEnabled(true);
		}
	}

// 초기화  ----------------------------------------------------------------------------------------------------
	public void clear() {
		noTF.setText("");
		nameTF.setText("");
		phoneTF.setText("");
		typeTF.setText("");
		memoTF.setText("");
	}

	public void initDisplay() {
		setEnable(NONE);
		clear();
		cmd = NONE;
		initialize();
	}
	

// 메인 메소드  ---------------------------------------------------------------------------------------------
	public static void main(String args[]) throws Exception {
		new HoneyBubbleGUIApp();
	}
	

// 액션리스너  ----------------------------------------------------------------------------------------------
	public void actionPerformed(ActionEvent ev) {
		Component c = (Component) ev.getSource();
		try {
			if (c == addB) {
				if (cmd != ADD) {
					setEnable(ADD);
				} else {
					addMember();
				}
			} else if (c == deleteB) {
				if (cmd != DELETE) {
					setEnable(DELETE);
				} else {
					removeMember();
				}
			} else if (c == updateB) {
				if (cmd != UPDATE && cmd != UPDATE_CHANGE) {
					setEnable(UPDATE);
				} else if (cmd != UPDATE_CHANGE) {
					searchNoMember();
				} else {
					modifyMember();
				}
			} else if (c == searchB) {
				if (cmd != SEARCH) {
					setEnable(SEARCH);
				} else {
					searchNameMember();
				}
			} else if (c == cancelB) {
				displayAllMember();
				initDisplay();
			}
		} catch (Exception e) {
			System.out.println("예외 발생 : " + e);
		}
	}
	

// 모든 테이블값을 보여주는 메소드  ----------------------------------------------------------------------------------------------------
	public void displayAllMember() {

		List<MemberDTO> MemberList = MemberDAO.getMemberDAO().selectAllMember();

		if (MemberList.isEmpty()) {
			JOptionPane.showMessageDialog(this, "저장된 회원정보가 존재하지 않습니다.");
			return;
		}

		DefaultTableModel model = (DefaultTableModel) table.getModel();

		for (int i = model.getRowCount(); i > 0; i--) {
			model.removeRow(0);
		}

		for (MemberDTO member : MemberList) {
			Vector<Object> rowData = new Vector<Object>();
			rowData.add(member.getNo());
			rowData.add(member.getName());
			rowData.add(member.getPhone());
			rowData.add(member.getType());
			rowData.add(member.getMemo());
			model.addRow(rowData);
		}
	}


// 신규회원 등록  ----------------------------------------------------------------------------------------------------
	public void addMember() {

		String temp = noTF.getText();
		if (temp.equals("")) {
			JOptionPane.showMessageDialog(this, "회원번호를 입력해 주세요.");
			noTF.requestFocus();
			return;
		}

		Pattern noP = Pattern.compile("\\d{4}");
		Matcher noM = noP.matcher(temp);
		if (!noM.matches()) {
			JOptionPane.showMessageDialog(this, "회원번호를 형식에 맞게 입력해 주세요.");
			noTF.requestFocus();
			return;
		}

		int no = Integer.parseInt(temp);
		if (MemberDAO.getMemberDAO().selectNoMember(no) != null) {
			JOptionPane.showMessageDialog(this, "이미 존재하는 회원입니다.");
			noTF.requestFocus();
			return;
		}

		String name = nameTF.getText();
		if (name.equals("")) {
			JOptionPane.showMessageDialog(this, "이름을 입력해 주세요.");
			nameTF.requestFocus();
			return;
		}

		String phone = phoneTF.getText();
		if (phone.equals("")) {
			JOptionPane.showMessageDialog(this, "전화번호를 입력해 주세요.");
			phoneTF.requestFocus();
			return;
		}

		Pattern phoneP = Pattern.compile("(01[016789])-\\d{4}-\\d{4}");
		Matcher phoneM = phoneP.matcher(phone);
		if (!phoneM.matches()) {
			JOptionPane.showMessageDialog(this, "전화번호를 형식에 맞게 입력해 주세요.");
			phoneTF.requestFocus();
			return;
		}

		String type = typeTF.getText();
		if (type.equals("")) {
			JOptionPane.showMessageDialog(this, "구분정보를 입력해 주세요.");
			typeTF.requestFocus();
			return;
		}

		String memo = memoTF.getText();
		if (memo.equals("")) {
			JOptionPane.showMessageDialog(this, "메모를 입력해 주세요.");
			memoTF.requestFocus();
			return;
		}
		

		MemberDTO member = new MemberDTO();
		member.setNo(no);
		member.setName(name);
		member.setPhone(phone);
		member.setType(type);
		member.setMemo(memo);

		int rows = MemberDAO.getMemberDAO().insertMember(member);

		JOptionPane.showMessageDialog(this, rows + "명의 회원정보를 저장 하였습니다.");

		displayAllMember();
		initDisplay();
	}


	public void removeMember() {
		String temp = noTF.getText();
		if (temp.equals("")) {
			JOptionPane.showMessageDialog(this, "회원번호를 입력해 주세요.");
			noTF.requestFocus();// 입력촛점을 이동하는 메소드
			return;
		}

		Pattern noP = Pattern.compile("\\d{4}");
		Matcher noM = noP.matcher(temp);
		if (!noM.matches()) {
			JOptionPane.showMessageDialog(this, "회원번호를 형식에 맞게 입력해 주세요.");
			noTF.requestFocus();
			return;
		}

		int no = Integer.parseInt(temp);


		int rows = MemberDAO.getMemberDAO().deleteMember(no);

		if (rows > 0) {
			JOptionPane.showMessageDialog(this, rows + "명의 회원정보를 삭제 하였습니다.");
			displayAllMember();
		} else {
			JOptionPane.showMessageDialog(this, "삭제하고자 하는 회원정보가 존재하지 않습니다.");
		}
		initDisplay();
	}


	public void searchNoMember() {
		String temp = noTF.getText();
		if (temp.equals("")) {
			JOptionPane.showMessageDialog(this, "회원번호를 입력해 주세요.");
			noTF.requestFocus();
			return;
		}

		Pattern noP = Pattern.compile("\\d{4}");
		Matcher noM = noP.matcher(temp);
		if (!noM.matches()) {
			JOptionPane.showMessageDialog(this, "회원번호를 형식에 맞게 입력해 주세요.");
			noTF.requestFocus();
			return;
		}

		int no = Integer.parseInt(temp);
	
		MemberDTO member = MemberDAO.getMemberDAO().selectNoMember(no);
		if (member == null) {
			JOptionPane.showMessageDialog(this, "변경하고자 하는 회원정보가 존재하지 않습니다.");
			noTF.requestFocus();
			noTF.setText("");
			return;
		}

		noTF.setText(member.getNo() + "");
		nameTF.setText(member.getName());
		phoneTF.setText(member.getPhone());
		typeTF.setText(member.getType());
		memoTF.setText(member.getMemo());

		setEnable(UPDATE_CHANGE);
	}


	public void modifyMember() {
		int no = Integer.parseInt(noTF.getText());

		String name = nameTF.getText();
		if (name.equals("")) {
			JOptionPane.showMessageDialog(this, "이름을 입력해 주세요.");
			nameTF.requestFocus();
			return;
		}

		String phone = phoneTF.getText();
		if (phone.equals("")) {
			JOptionPane.showMessageDialog(this, "전화번호를 입력해 주세요.");
			phoneTF.requestFocus();
			return;
		}

		Pattern phoneP = Pattern.compile("(01[016789])-\\d{3,4}-\\d{4}");
		Matcher phoneM = phoneP.matcher(phone);
		if (!phoneM.matches()) {
			JOptionPane.showMessageDialog(this, "전화번호를 형식에 맞게 입력해 주세요.");
			phoneTF.requestFocus();
			return;
		}

		String type = typeTF.getText();
		if (type.equals("")) {
			JOptionPane.showMessageDialog(this, "회원구분 정보를 입력해 주세요.");
			typeTF.requestFocus();
			return;
		}

		String memo = memoTF.getText();
		if (memo.equals("")) {
			JOptionPane.showMessageDialog(this, "메모를 입력해 주세요.");
			memoTF.requestFocus();
			return;
		}

		MemberDTO member = new MemberDTO();
		member.setName(name);
		member.setPhone(phone);
		member.setType(type);
		member.setMemo(memo);
		member.setNo(no);

		int rows = MemberDAO.getMemberDAO().updateMember(member);


		JOptionPane.showMessageDialog(this, rows + "명의 회원정보를 변경 하였습니다.");

		displayAllMember();
		initDisplay();
	}

	
	public void searchNameMember() {
		String name = nameTF.getText();
		String phone= phoneTF.getText();
		if (name.equals("") && phone.contentEquals("")) {
			JOptionPane.showMessageDialog(this, "정보를 입력해 주세요.");
			nameTF.requestFocus();
			return;
		}

		List<MemberDTO> memberList = MemberDAO.getMemberDAO().selectNameMember(name, phone);
		
		if (memberList.isEmpty()) {
			JOptionPane.showMessageDialog(this, "검색된 회원정보가 존재하지 않습니다.");
			return;
		}

		DefaultTableModel model = (DefaultTableModel) table.getModel();

		for (int i = model.getRowCount(); i > 0; i--) {
			model.removeRow(0);
		}

		for (MemberDTO member : memberList) {
			Vector<Object> rowData = new Vector<Object>();
			rowData.add(member.getNo());
			rowData.add(member.getName());
			rowData.add(member.getPhone());
			rowData.add(member.getType());
			rowData.add(member.getMemo());
			model.addRow(rowData);
		}

		initDisplay();
	}
}