package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.jxmapviewer.viewer.GeoPosition;

import model.FileManager;
import model.School;
import model.Tournament;

public class Interface extends JFrame {

	Tournament tournament;
	FileManager fm;
	Map jMap;
	Map jMap1;
	Map jMap2;

	private JTabbedPane tabs;

	private JPanel sectionalTab;
	private JPanel regionalTab;
	private JPanel semi_stateTab;
	

	private JMenuBar menu;
	private JMenu file;
	private JMenuItem load;
	private JMenuItem save;
	private JMenuItem saveAs;

	private DefaultListModel<String> secDefaultList;
	private DefaultListModel<String> regDefaultList;
	private DefaultListModel<String> semiDefaultList;

	private JList secJList;
	private JList regJList;
	private JList semiJList;

	private JComboBox c1;
	private JComboBox c2;
	private JComboBox c3;
	private JComboBox sList;
	private JComboBox rList;
	private JComboBox ssList;

	private JLabel cN1;

	private JLabel sece1;
	private JLabel sece2;
	
	private JLabel rege1;
	private JLabel rege2;
	
	private JLabel sse1;
	private JLabel sse2;

	private JLabel n1;
	private JLabel n2;
	private JLabel n3;
	private JLabel n4;
	private JLabel n5;
	private JLabel n6;
	private JLabel n7;

	private JTextField secl1;
	private JTextField secl2;
	private JTextField secl3;
	private JComboBox secl4;
	private JTextField secl5;
	private JTextField secl6;
	private JTextField secl7;

	private JTextField regl1;
	private JTextField regl2;
	private JTextField regl3;
	private JComboBox regl4;
	private JTextField regl5;
	private JTextField regl6;
	private JTextField regl7;
	
	private JTextField ssl1;
	private JTextField ssl2;
	private JTextField ssl3;
	private JComboBox ssl4;
	private JTextField ssl5;
	private JTextField ssl6;
	private JTextField ssl7;
	
	private JButton secSaveB;
	private JButton secEditB;
	private JButton secAddB;
	private JButton secRemoveB;
	
	private JButton regSaveB;
	private JButton regEditB;
	private JButton regAddB;
	private JButton regRemoveB;
	
	private JButton semiSaveB;
	private JButton semiEditB;
	private JButton semiAddB;
	private JButton semiRemoveB;

	JPanel seccenterC;
	JPanel regcenterC;
	JPanel semicenterC;

	boolean flag = false;

	private String[] tableColumns = { "School Name", "Nineth", "Tenth", "Eleventh", "Twelfth", "City", "Address",
			"Host", "Num" };

	public Interface(Tournament tournament, FileManager fm) {

		
		this.fm = fm;
		jMap = new Map();
		//mapUI();

		this.tournament = tournament;

		menu = new JMenuBar();
		file = new JMenu("File");
		load = new JMenuItem("Load");
		load.addActionListener(new SaveAndLoad());
		save = new JMenuItem("Save");
		save.addActionListener(new SaveAndLoad());
		saveAs = new JMenuItem("Save As");
		saveAs.addActionListener(new SaveAndLoad());

		file.add(load);
		file.add(save);
		file.add(saveAs);
		menu.add(file);

		sectionalTab = new JPanel();
		regionalTab = new JPanel();
		semi_stateTab = new JPanel();

		buildSec();
		buildReg();
		buildSemi();
		mapUI();
		
		tabs = new JTabbedPane();
		tabs.addTab("Sectional", sectionalTab);
		tabs.addTab("Regional", regionalTab);
		tabs.addTab("Semi State", semi_stateTab);

		setSize(new Dimension(1500, 500));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Tournament Realignment");
		setJMenuBar(menu);
		add(tabs);
		
		setVisible(true);

	}

	public void mapUI() {
		jMap.jXMapKit.getMainMap().addMouseListener(new MarkerListener());
		jMap1.jXMapKit.getMainMap().addMouseListener(new MarkerListener());
		jMap2.jXMapKit.getMainMap().addMouseListener(new MarkerListener());
	}

	public void buildSec() {
		JScrollPane listScroller;
		JPanel center = new JPanel();
		JPanel centerCC = new JPanel();
		seccenterC = new JPanel();
		JPanel centerS = new JPanel();
		JPanel centerN = new JPanel();
		JPanel east = new JPanel();
		JPanel eastN = new JPanel();
		JPanel west = new JPanel();
		JPanel westS = new JPanel();


		// populating slist
		String[] nums1 = new String[tournament.getSchoolList().size()];
		for (int i = 0; i < tournament.getSchoolList().size(); i++) {
			nums1[i] = (i + 1) + " " + tournament.getSchoolList().get(i).getName();
		}
		sList = new JComboBox(nums1);
		sList.setVisible(false);

		JScrollPane scroll = null;
		String[] nums = new String[32];
		secJList = new JList();
		secDefaultList = new DefaultListModel<String>();

		secSaveB = new JButton("Save Edit");
		secSaveB.addActionListener(new actionListener());
		secSaveB.setVisible(false);

		secEditB = new JButton("Edit");
		secEditB.addActionListener(new actionListener());

		secAddB = new JButton("+");
		secAddB.addActionListener(new actionListener());

		secRemoveB = new JButton("-");
		secRemoveB.addActionListener(new actionListener());

		centerCC.setLayout(new BorderLayout());
		centerCC.add(seccenterC, BorderLayout.CENTER);
		centerCC.add(sList, BorderLayout.NORTH);

		// layouts center panel
		center.setLayout(new BorderLayout());
		center.add(centerN, BorderLayout.NORTH);
		center.add(centerCC, BorderLayout.CENTER);
		center.add(centerS, BorderLayout.SOUTH);

		seccenterC.setLayout(new GridLayout(7, 2));
		seccenterC.setBorder(BorderFactory.createTitledBorder("Info"));

		centerS.setLayout(new BorderLayout());
		centerS.add(secSaveB, BorderLayout.EAST);

		cN1 = new JLabel("add a new School");
		cN1.setVisible(false);
		centerN.add(cN1);

		String[] yesNo = { "No", "Yes" };
		secl1 = new JTextField();
		secl1.setEditable(false);
		secl2 = new JTextField();
		secl2.setEditable(false);
		secl3 = new JTextField();
		secl3.setEditable(false);
		secl4 = new JComboBox(yesNo);
		secl4.setEnabled(false);
		secl5 = new JTextField();
		secl5.setEditable(false);
		secl6 = new JTextField();
		secl6.setEditable(false);
		secl7 = new JTextField();
		secl7.setEditable(false);

		n1 = new JLabel("Name:");
		n2 = new JLabel("Address:");
		n3 = new JLabel("city:");
		n4 = new JLabel("Host School?:");
		n5 = new JLabel("Total Students:");
		n6 = new JLabel("Current Distance to Host");
		n7 = new JLabel("Area Code");

		seccenterC.add(n1);
		seccenterC.add(secl1);
		seccenterC.add(n2);
		seccenterC.add(secl2);
		seccenterC.add(n3);
		seccenterC.add(secl3);
		seccenterC.add(n7);
		seccenterC.add(secl7);
		seccenterC.add(n4);
		seccenterC.add(secl4);
		seccenterC.add(n5);
		seccenterC.add(secl5);
		seccenterC.add(n6);
		seccenterC.add(secl6);

		sece1 = new JLabel("Average Distance: ");
		sece2 = new JLabel(" null");

		// more layouts and initialization
		eastN.setLayout(new BorderLayout());
		eastN.add(sece1, BorderLayout.WEST);
		eastN.add(sece2, BorderLayout.CENTER);

		east.setLayout(new BorderLayout());
		east.setBorder(BorderFactory.createTitledBorder("Map"));
		east.add(eastN, BorderLayout.NORTH);
		east.add(jMap.getMap(), BorderLayout.CENTER);

		// setting up labels
		secl1.setText(tournament.getSectionals().get(1).getParticipating().get(0).getName());
		secl2.setText(tournament.getSectionals().get(1).getParticipating().get(0).getAddress());
		secl3.setText(tournament.getSectionals().get(1).getParticipating().get(0).getCity());
		if (tournament.getSectionals().get(1).getParticipating().get(0).getSectionalHost()
				.compareToIgnoreCase("null") == 0) {
			secl4.setSelectedItem(0);
		} else {
			secl4.setSelectedItem(1);
		}
		secl5.setText(tournament.getSectionals().get(1).getParticipating().get(0).getTotal());
		secl6.setText(tournament.getSectionals().get(1).getParticipating().get(0).getDistance() + " Miles");
		secl7.setText(tournament.getSectionals().get(1).getParticipating().get(0).getAreaCode());

		// adding a number to the name of each school in JList
		for (int i = 0; i < tournament.getSectionals().get(1).getParticipating().size(); i++) {
			secDefaultList.add(i, i + " " + tournament.getSectionals().get(1).getParticipating().get(i).getName());
		}

		// populating JList with list of schools
		secJList.setModel(secDefaultList);
		listScroller = new JScrollPane(secJList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		listScroller.setPreferredSize(new Dimension(250, 80));
		secJList.setVisibleRowCount(-1);
		secJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		secJList.setLayoutOrientation(JList.VERTICAL);
		secJList.addMouseListener(new Listener());

		// creating west panel
		west.setLayout(new BorderLayout());
		west.add(listScroller, BorderLayout.CENTER);

		westS.setLayout(new GridLayout(1, 3));
		westS.add(secAddB);
		westS.add(secRemoveB);
		westS.add(secEditB);

		west.add(westS, BorderLayout.SOUTH);

		// creating JCombobox with sections list and number
		for (int i = 0; i < nums.length; i++) {
			//System.out.println(tournament.getSectionals().get(i+1).getHost().getName());
			nums[i] = "Sectional " + (i + 1) + " " + tournament.getSectionals().get(i + 1).getHost().getName();
		}
		c1 = new JComboBox(nums);
		c1.addItemListener(new ItemChangeListener());

		// finding average of initial tournament and setting the value;
		double average;
		double total = 0;
		for (int i = 0; i < tournament.getSectionals().get(1).getParticipating().size(); i++) {
			// System.out.println(tournament.getSectionals().get(1).getParticipating().get(i).getDistance());
			total = total + tournament.getSectionals().get(1).getParticipating().get(i).getDistance();
		}
		average = total / tournament.getSectionals().get(1).getParticipating().size();
		average = Math.round(average * 100.0) / 100.0;
		sece2.setText(average + " Miles");

		// creating map of initial tournament;
		jMap.createGeo(tournament.getSectionals().get(1).getParticipating(),
				tournament.getSectionals().get(1).getHost());
		
		secJList.setSelectedIndex(0);

		// adding all components to sectionalTab;
		sectionalTab.setLayout(new BorderLayout());
		sectionalTab.add(c1, BorderLayout.NORTH);
		sectionalTab.add(west, BorderLayout.WEST);
		sectionalTab.add(east, BorderLayout.EAST);
		sectionalTab.add(center, BorderLayout.CENTER);

	}

	public void buildReg() {
		jMap1 = new Map();
		JScrollPane listScroller;
		JPanel center = new JPanel();
		JPanel centerCC = new JPanel();
		regcenterC = new JPanel();
		JPanel centerS = new JPanel();
		JPanel centerN = new JPanel();
		JPanel east = new JPanel();
		JPanel eastN = new JPanel();
		JPanel west = new JPanel();
		JPanel westS = new JPanel();

		// populating rlist
		String[] nums1 = new String[tournament.getSchoolList().size()];
		for (int i = 0; i < tournament.getSchoolList().size(); i++) {
			nums1[i] = (i + 1) + " " + tournament.getSchoolList().get(i).getName();
		}
		rList = new JComboBox(nums1);
		rList.setVisible(false);

		JScrollPane scroll = null;
		regJList = new JList();
		regDefaultList = new DefaultListModel<String>();

		regSaveB = new JButton("Save Edit");
		regSaveB.addActionListener(new actionListener());
		regSaveB.setVisible(false);

		regEditB = new JButton("Edit");
		regEditB.addActionListener(new actionListener());

		regAddB = new JButton("+");
		regAddB.addActionListener(new actionListener());

		regRemoveB = new JButton("-");
		regRemoveB.addActionListener(new actionListener());

		centerCC.setLayout(new BorderLayout());
		centerCC.add(regcenterC, BorderLayout.CENTER);
		centerCC.add(rList, BorderLayout.NORTH);

		// layouts center panel
		center.setLayout(new BorderLayout());
		center.add(centerN, BorderLayout.NORTH);
		center.add(centerCC, BorderLayout.CENTER);
		center.add(centerS, BorderLayout.SOUTH);

		regcenterC.setLayout(new GridLayout(7, 2));
		regcenterC.setBorder(BorderFactory.createTitledBorder("Info"));

		centerS.setLayout(new BorderLayout());
		centerS.add(regSaveB, BorderLayout.EAST);

		cN1 = new JLabel("add a new School");
		cN1.setVisible(false);
		centerN.add(cN1);

		String[] yesNo = { "No", "Yes" };
		regl1 = new JTextField();
		regl1.setEditable(false);
		regl2 = new JTextField();
		regl2.setEditable(false);
		regl3 = new JTextField();
		regl3.setEditable(false);
		regl4 = new JComboBox(yesNo);
		regl4.setEnabled(false);
		regl5 = new JTextField();
		regl5.setEditable(false);
		regl6 = new JTextField();
		regl6.setEditable(false);
		regl7 = new JTextField();
		regl7.setEditable(false);

		n1 = new JLabel("Name:");
		n2 = new JLabel("Address:");
		n3 = new JLabel("city:");
		n4 = new JLabel("Host School?:");
		n5 = new JLabel("Total Students:");
		n6 = new JLabel("Current Distance to Host");
		n7 = new JLabel("Area Code");

		regcenterC.add(n1);
		regcenterC.add(regl1);
		regcenterC.add(n2);
		regcenterC.add(regl2);
		regcenterC.add(n3);
		regcenterC.add(regl3);
		regcenterC.add(n7);
		regcenterC.add(regl7);
		regcenterC.add(n4);
		regcenterC.add(regl4);
		regcenterC.add(n5);
		regcenterC.add(regl5);
		regcenterC.add(n6);
		regcenterC.add(regl6);

		rege1 = new JLabel("Average Distance: ");
		rege2 = new JLabel(" null");

		// more layouts and initialization
		eastN.setLayout(new BorderLayout());
		eastN.add(rege1, BorderLayout.WEST);
		eastN.add(rege2, BorderLayout.CENTER);

		east.setLayout(new BorderLayout());
		east.setBorder(BorderFactory.createTitledBorder("Map"));
		east.add(eastN, BorderLayout.NORTH);
		east.add(jMap1.getMap(), BorderLayout.CENTER);

		// setting up labels
		regl1.setText(tournament.getRegionals().get(1).getParticipating().get(0).getName());
		regl2.setText(tournament.getRegionals().get(1).getParticipating().get(0).getAddress());
		regl3.setText(tournament.getRegionals().get(1).getParticipating().get(0).getCity());
		if (tournament.getRegionals().get(1).getParticipating().get(0).getRegionalsHost()
				.compareToIgnoreCase("null") == 0) {
			regl4.setSelectedItem(0);
		} else {
			regl4.setSelectedItem(1);
		}
		regl5.setText(tournament.getRegionals().get(1).getParticipating().get(0).getTotal());
		regl6.setText(tournament.getRegionals().get(1).getParticipating().get(0).getDistance() + " Miles");
		regl7.setText(tournament.getRegionals().get(1).getParticipating().get(0).getAreaCode());

		// adding a number to the name of each school in JList
		for (int i = 0; i < tournament.getRegionals().get(1).getParticipating().size(); i++) {
			regDefaultList.add(i, i + " " + tournament.getRegionals().get(1).getParticipating().get(i).getName());
		}

		// populating JList with list of schools
		regJList.setModel(regDefaultList);
		listScroller = new JScrollPane(regJList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		listScroller.setPreferredSize(new Dimension(250, 80));
		regJList.setVisibleRowCount(-1);
		regJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		regJList.setLayoutOrientation(JList.VERTICAL);
		regJList.addMouseListener(new Listener());

		// creating west panel
		west.setLayout(new BorderLayout());
		west.add(listScroller, BorderLayout.CENTER);

		westS.setLayout(new GridLayout(1, 3));
		westS.add(regAddB);
		westS.add(regRemoveB);
		westS.add(regEditB);

		west.add(westS, BorderLayout.SOUTH);


		//tournament.getRegionals().get(14).setHost(tournament.getSchoolList().get(311));
		String[] nums = new String[16];
		for (int i = 0; i < nums.length; i++) {			
			nums[i] = "Regional " + (i + 1) + " "
					+ " " + tournament.getRegionals().get(i + 1).getHost().getName();
		//	System.out.println(nums[i]);
		}
		c2 = new JComboBox(nums);
		c2.addItemListener(new ItemChangeListener());

		// finding average of initial tournament and setting the value;
		double average;
		double total = 0;
		for (int i = 0; i < tournament.getRegionals().get(1).getParticipating().size(); i++) {
			// System.out.println(tournament.getSectionals().get(1).getParticipating().get(i).getDistance());
			total = total + tournament.getRegionals().get(1).getParticipating().get(i).getDistance();
		}
		average = total / tournament.getRegionals().get(1).getParticipating().size();
		average = Math.round(average * 100.0) / 100.0;
		rege2.setText(average + " Miles");

		// creating map of initial tournament;
		jMap1.createGeo(tournament.getRegionals().get(1).getParticipating(),
				tournament.getRegionals().get(1).getHost());
		
		regJList.setSelectedIndex(0);

		// adding all components to sectionalTab;
		regionalTab.setLayout(new BorderLayout());
		regionalTab.add(c2, BorderLayout.NORTH);
		regionalTab.add(west, BorderLayout.WEST);
		regionalTab.add(east, BorderLayout.EAST);
		regionalTab.add(center, BorderLayout.CENTER);
		
		
	}

	public void buildSemi() {

		jMap2 = new Map();
		JScrollPane listScroller;
		JPanel center = new JPanel();
		JPanel centerCC = new JPanel();
		semicenterC = new JPanel();
		JPanel centerS = new JPanel();
		JPanel centerN = new JPanel();
		JPanel east = new JPanel();
		JPanel eastN = new JPanel();
		JPanel west = new JPanel();
		JPanel westS = new JPanel();

		// populating sslist
		String[] nums1 = new String[tournament.getSchoolList().size()];
		for (int i = 0; i < tournament.getSchoolList().size(); i++) {
			nums1[i] = (i + 1) + " " + tournament.getSchoolList().get(i).getName();
		}
		ssList = new JComboBox(nums1);
		ssList.setVisible(false);

		JScrollPane scroll = null;
		semiJList = new JList();
		semiDefaultList = new DefaultListModel<String>();

		semiSaveB = new JButton("Save Edit");
		semiSaveB.addActionListener(new actionListener());
		semiSaveB.setVisible(false);

		semiEditB = new JButton("Edit");
		semiEditB.addActionListener(new actionListener());

		semiAddB = new JButton("+");
		semiAddB.addActionListener(new actionListener());

		semiRemoveB = new JButton("-");
		semiRemoveB.addActionListener(new actionListener());

		centerCC.setLayout(new BorderLayout());
		centerCC.add(semicenterC, BorderLayout.CENTER);
		centerCC.add(ssList, BorderLayout.NORTH);

		// layouts center panel
		center.setLayout(new BorderLayout());
		center.add(centerN, BorderLayout.NORTH);
		center.add(centerCC, BorderLayout.CENTER);
		center.add(centerS, BorderLayout.SOUTH);

		semicenterC.setLayout(new GridLayout(7, 2));
		semicenterC.setBorder(BorderFactory.createTitledBorder("Info"));

		centerS.setLayout(new BorderLayout());
		centerS.add(semiSaveB, BorderLayout.EAST);

		cN1 = new JLabel("add a new School");
		cN1.setVisible(false);
		centerN.add(cN1);

		String[] yesNo = { "No", "Yes" };
		ssl1 = new JTextField();
		ssl1.setEditable(false);
		ssl2 = new JTextField();
		ssl2.setEditable(false);
		ssl3 = new JTextField();
		ssl3.setEditable(false);
		ssl4 = new JComboBox(yesNo);
		ssl4.setEnabled(false);
		ssl5 = new JTextField();
		ssl5.setEditable(false);
		ssl6 = new JTextField();
		ssl6.setEditable(false);
		ssl7 = new JTextField();
		ssl7.setEditable(false);

		n1 = new JLabel("Name:");
		n2 = new JLabel("Address:");
		n3 = new JLabel("city:");
		n4 = new JLabel("Host School?:");
		n5 = new JLabel("Total Students:");
		n6 = new JLabel("Current Distance to Host");
		n7 = new JLabel("Area Code");

		semicenterC.add(n1);
		semicenterC.add(ssl1);
		semicenterC.add(n2);
		semicenterC.add(ssl2);
		semicenterC.add(n3);
		semicenterC.add(ssl3);
		semicenterC.add(n7);
		semicenterC.add(ssl7);
		semicenterC.add(n4);
		semicenterC.add(ssl4);
		semicenterC.add(n5);
		semicenterC.add(ssl5);
		semicenterC.add(n6);
		semicenterC.add(ssl6);

		sse1 = new JLabel("Average Distance: ");
		sse2 = new JLabel(" null");

		// more layouts and initialization
		eastN.setLayout(new BorderLayout());
		eastN.add(sse1, BorderLayout.WEST);
		eastN.add(sse2, BorderLayout.CENTER);

		east.setLayout(new BorderLayout());
		east.setBorder(BorderFactory.createTitledBorder("Map"));
		east.add(eastN, BorderLayout.NORTH);
		east.add(jMap2.getMap(), BorderLayout.CENTER);

		// setting up labels
		ssl1.setText(tournament.getSemiState().get(1).getParticipating().get(0).getName());
		ssl2.setText(tournament.getSemiState().get(1).getParticipating().get(0).getAddress());
		ssl3.setText(tournament.getSemiState().get(1).getParticipating().get(0).getCity());
		if (tournament.getSemiState().get(1).getParticipating().get(0).getSemiStateHost()
				.compareToIgnoreCase("null") == 0) {
			ssl4.setSelectedItem(0);
		} else {
			ssl4.setSelectedItem(1);
		}
		ssl5.setText(tournament.getSemiState().get(1).getParticipating().get(0).getTotal());
		ssl6.setText(tournament.getSemiState().get(1).getParticipating().get(0).getDistance() + " Miles");
		ssl7.setText(tournament.getSemiState().get(1).getParticipating().get(0).getAreaCode());

		// adding a number to the name of each school in JList
		for (int i = 0; i < tournament.getSemiState().get(1).getParticipating().size(); i++) {
			semiDefaultList.add(i, i + " " + tournament.getSemiState().get(1).getParticipating().get(i).getName());
		}

		// populating JList with list of schools
		semiJList.setModel(semiDefaultList);
		listScroller = new JScrollPane(semiJList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		listScroller.setPreferredSize(new Dimension(250, 80));
		semiJList.setVisibleRowCount(-1);
		semiJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		semiJList.setLayoutOrientation(JList.VERTICAL);
		semiJList.addMouseListener(new Listener());

		// creating west panel
		west.setLayout(new BorderLayout());
		west.add(listScroller, BorderLayout.CENTER);

		westS.setLayout(new GridLayout(1, 3));
		westS.add(semiAddB);
		westS.add(semiRemoveB);
		westS.add(semiEditB);

		west.add(westS, BorderLayout.SOUTH);

		// creating JCombobox with sections list and number
		String[] nums = new String[4];
		for (int i = 0; i < nums.length; i++) {
			
			nums[i] = "Semi State " + (i + 1)
					+ " " + tournament.getSemiState().get(i + 1).getHost().getName();
		}
		c3 = new JComboBox(nums);
		c3.addItemListener(new ItemChangeListener());

		// finding average of initial tournament and setting the value;
		double average;
		double total = 0;
		for (int i = 0; i < tournament.getSemiState().get(1).getParticipating().size(); i++) {
			// System.out.println(tournament.getSectionals().get(1).getParticipating().get(i).getDistance());
			total = total + tournament.getSemiState().get(1).getParticipating().get(i).getDistance();
		}
		average = total / tournament.getSemiState().get(1).getParticipating().size();
		average = Math.round(average * 100.0) / 100.0;
		sse2.setText(average + " Miles");

		// creating map of initial tournament;
		jMap2.createGeo(tournament.getSemiState().get(1).getParticipating(),
				tournament.getSemiState().get(1).getHost());
		
		semiJList.setSelectedIndex(0);

		// adding all components to sectionalTab;
		semi_stateTab.setLayout(new BorderLayout());
		semi_stateTab.add(c3, BorderLayout.NORTH);
		semi_stateTab.add(west, BorderLayout.WEST);
		semi_stateTab.add(east, BorderLayout.EAST);
		semi_stateTab.add(center, BorderLayout.CENTER);
		
		
	}


	class ItemChangeListener implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getSource() == c1) {
				int value = c1.getSelectedIndex() + 1;
				if (e.getStateChange() == 1) {
					
					jMap.removeMarkers();
					jMap.createGeo(tournament.getSectionals().get(value).getParticipating(),
							tournament.getSectionals().get(value).getHost());
					secDefaultList.removeAllElements();
					value = c1.getSelectedIndex() + 1;
					for (int i = 0; i < tournament.getSectionals().get(value).getParticipating().size(); i++) {
						secDefaultList.add(i,
								i + " " + tournament.getSectionals().get(value).getParticipating().get(i).getName());
					}
					double average;
					double total = 0;
					for (int i = 0; i < tournament.getSectionals().get(c1.getSelectedIndex() + 1).getParticipating()
							.size(); i++) {
						total = total + tournament.getSectionals().get(c1.getSelectedIndex() + 1).getParticipating()
								.get(i).getDistance();
					}
					average = total
							/ tournament.getSectionals().get(c1.getSelectedIndex() + 1).getParticipating().size();
					average = Math.round(average * 100.0) / 100.0;
					sece2.setText(average + " Miles");
					secJList.setSelectedIndex(0);
					
					
					
					
					secl1.setText(tournament.getSectionals().get(c1.getSelectedIndex() + 1).getParticipating()
							.get(0).getName());
					secl2.setText(tournament.getSectionals().get(c1.getSelectedIndex() + 1).getParticipating()
							.get(0).getAddress());
					secl3.setText(tournament.getSectionals().get(c1.getSelectedIndex() + 1).getParticipating()
							.get(0).getCity());
					if (tournament.getSectionals().get(c1.getSelectedIndex() + 1).getParticipating()
							.get(0).getSectionalHost().compareToIgnoreCase("NULL") == 0) {
						secl4.setSelectedIndex(0);
						;
					} else {
						secl4.setSelectedIndex(1);
						;
					}
					secl5.setText(tournament.getSectionals().get(c1.getSelectedIndex() + 1).getParticipating()
							.get(0).getTotal());
					secl6.setText(tournament.getSectionals().get(c1.getSelectedIndex() + 1).getParticipating()
							.get(0).getDistance() + " Miles");
					secl7.setText(tournament.getSectionals().get(c1.getSelectedIndex() + 1).getParticipating()
							.get(0).getAreaCode());
					
				}
			}

			if (e.getSource() == c2) {
					int value = c2.getSelectedIndex() + 1;
					if (e.getStateChange() == 1) {
						jMap1.removeMarkers();
						System.out.println(tournament.getRegionals().get(value).getHost());
						jMap1.createGeo(tournament.getRegionals().get(value).getParticipating(),
								tournament.getRegionals().get(value).getHost());
						regDefaultList.removeAllElements();
						value = c2.getSelectedIndex() + 1;
						for (int i = 0; i < tournament.getRegionals().get(value).getParticipating().size(); i++) {
							regDefaultList.add(i,
									i + " " + tournament.getRegionals().get(value).getParticipating().get(i).getName());
						}
						double average;
						double total = 0;
						for (int i = 0; i < tournament.getRegionals().get(c2.getSelectedIndex() + 1).getParticipating().size(); i++) {
							
							total = total + tournament.getRegionals().get(c2.getSelectedIndex()+1).getParticipating().get(i).getDistance();
							
						}
						average = total
								/ tournament.getRegionals().get(c2.getSelectedIndex()+1).getParticipating().size();
						average = Math.round(average * 100.0) / 100.0;
						rege2.setText(average + " Miles");
						
						
						regJList.setSelectedIndex(0);
						regl1.setText(tournament.getRegionals().get(c2.getSelectedIndex() + 1).getParticipating()
								.get(0).getName());
						regl2.setText(tournament.getRegionals().get(c2.getSelectedIndex() + 1).getParticipating()
								.get(0).getAddress());
						regl3.setText(tournament.getRegionals().get(c2.getSelectedIndex() + 1).getParticipating()
								.get(0).getCity());
						if (tournament.getRegionals().get(c2.getSelectedIndex() + 1).getParticipating()
								.get(0).getRegionalsHost().compareToIgnoreCase("NULL") == 0) {
							regl4.setSelectedIndex(0);
							;
						} else {
							regl4.setSelectedIndex(1);
							;
						}
						regl5.setText(tournament.getRegionals().get(c2.getSelectedIndex() + 1).getParticipating()
								.get(0).getTotal());
						regl6.setText(tournament.getRegionals().get(c2.getSelectedIndex() + 1).getParticipating()
								.get(0).getDistance() + " Miles");
						regl7.setText(tournament.getRegionals().get(c2.getSelectedIndex() + 1).getParticipating()
								.get(0).getAreaCode());

						
				}
			}

			if (e.getSource() == c3) {
				if (e.getStateChange() == 1) {
					int value = c3.getSelectedIndex() + 1;
					if (e.getStateChange() == 1) {
						jMap2.removeMarkers();
						jMap2.createGeo(tournament.getSemiState().get(value).getParticipating(), 
								tournament.getSemiState().get(value).getHost());
						semiDefaultList.removeAllElements();
						value = c3.getSelectedIndex() + 1;
						for (int i = 0; i < tournament.getSemiState().get(value).getParticipating().size(); i++) {
							semiDefaultList.add(i,
									tournament.getSemiState().get(value).getParticipating().get(i).getName());
						}
						double average;
						double total = 0;
						for(int i = 0; i < tournament.getSemiState().get(c3.getSelectedIndex()+1).getParticipating().size(); i++) {
							total = total + tournament.getSemiState().get(c3.getSelectedIndex()+1).getParticipating().get(i).getDistance();
						}
						average = total 
								/ tournament.getSemiState().get(c3.getSelectedIndex()+1).getParticipating().size();
						average = Math.round(average * 100.0) / 100.0;
						sse2.setText(average + " Miles");
						
						
						semiJList.setSelectedIndex(0);
						ssl1.setText(tournament.getSemiState().get(c3.getSelectedIndex() + 1).getParticipating()
								.get(0).getName());
						ssl2.setText(tournament.getSemiState().get(c3.getSelectedIndex() + 1).getParticipating()
								.get(0).getAddress());
						ssl3.setText(tournament.getSemiState().get(c3.getSelectedIndex() + 1).getParticipating()
								.get(0).getCity());
						if (tournament.getSemiState().get(c3.getSelectedIndex() + 1).getParticipating()
								.get(0).getSemiStateHost().compareToIgnoreCase("NULL") == 0) {
							ssl4.setSelectedIndex(0);
							;
						} else {
							ssl4.setSelectedIndex(1);
							;
						}
						ssl5.setText(tournament.getSemiState().get(c3.getSelectedIndex() + 1).getParticipating()
								.get(0).getTotal());
						ssl6.setText(tournament.getSemiState().get(c3.getSelectedIndex() + 1).getParticipating()
								.get(0).getDistance() + " Miles");
						ssl7.setText(tournament.getSemiState().get(c3.getSelectedIndex() + 1).getParticipating()
								.get(0).getAreaCode());
					}
				}
			}
		}
	}
	
	class MarkerListener implements MouseListener {
		
		public void mouseClicked(MouseEvent me) {
			Point2D gp_pt = null;

					if (sectionalTab.isShowing()) {

						for (GeoPosition waypoint : jMap.geoList) {
							// convert to world bitmap
							gp_pt = jMap.jXMapKit.getMainMap().getTileFactory().geoToPixel(waypoint,
									jMap.jXMapKit.getMainMap().getZoom());

							// convert to screen
							Rectangle rect = jMap.jXMapKit.getMainMap().getViewportBounds();
							Point converted_gp_pt = new Point((int) gp_pt.getX() - rect.x, (int) gp_pt.getY() - rect.y);
							// check if near the mouse
							if (converted_gp_pt.distance(me.getPoint()) < 15) {
								System.out.println("mapViewer mouse click has been detected within 10 pixels of a waypoint");
								jMap.jXMapKit.setCenterPosition(waypoint);
								jMap.jXMapKit.getMainMap().setZoom(7);
						
						for (int i = 0; i < tournament.getSectionals().get(c1.getSelectedIndex() + 1)
								.getParticipating().size(); i++) {
							if (tournament.getSectionals().get(c1.getSelectedIndex() + 1).getParticipating().get(i)
									.getLat() == waypoint.getLatitude()
									&& tournament.getSectionals().get(c1.getSelectedIndex() + 1).getParticipating()
											.get(i).getLng() == waypoint.getLongitude()) {
								secJList.setSelectedIndex(i);
								secl1.setText(tournament.getSectionals().get(c1.getSelectedIndex() + 1)
										.getParticipating().get(i).getName());
								secl2.setText(tournament.getSectionals().get(c1.getSelectedIndex() + 1)
										.getParticipating().get(i).getAddress());
								secl3.setText(tournament.getSectionals().get(c1.getSelectedIndex() + 1)
										.getParticipating().get(i).getCity());
								if (tournament.getSectionals().get(c1.getSelectedIndex() + 1).getParticipating()
										.get(i).getSectionalHost().compareToIgnoreCase("null") == 0) {
									secl4.setSelectedIndex(0);
								} else {
									secl4.setSelectedIndex(1);
								}
								secl5.setText(tournament.getSectionals().get(c1.getSelectedIndex() + 1)
										.getParticipating().get(i).getTotal());
								secl6.setText(tournament.getSectionals().get(c1.getSelectedIndex() + 1)
										.getParticipating().get(i).getDistance() + " Miles");
								secl7.setText(tournament.getSectionals().get(c1.getSelectedIndex() + 1)
										.getParticipating().get(i).getAreaCode());
									}
								}
							}
						}
					}
				    if(regionalTab.isShowing()) {
				    	
				    	for (GeoPosition waypoint : jMap1.geoList) {
							// convert to world bitmap
							gp_pt = jMap1.jXMapKit.getMainMap().getTileFactory().geoToPixel(waypoint,
									jMap1.jXMapKit.getMainMap().getZoom());

							// convert to screen
							Rectangle rect = jMap1.jXMapKit.getMainMap().getViewportBounds();
							Point converted_gp_pt = new Point((int) gp_pt.getX() - rect.x, (int) gp_pt.getY() - rect.y);
							// check if near the mouse
							if (converted_gp_pt.distance(me.getPoint()) < 15) {
								System.out.println("mapViewer mouse click has been detected within 10 pixels of a waypoint");
								jMap1.jXMapKit.setCenterPosition(waypoint);
								jMap1.jXMapKit.getMainMap().setZoom(7);
				    	
				    	for (int i = 0; i < tournament.getRegionals().get(c2.getSelectedIndex() + 1)
								.getParticipating().size(); i++) {
							if (tournament.getRegionals().get(c2.getSelectedIndex() + 1).getParticipating().get(i)
									.getLat() == waypoint.getLatitude()
									&& tournament.getRegionals().get(c2.getSelectedIndex() + 1).getParticipating()
											.get(i).getLng() == waypoint.getLongitude()) {
								regJList.setSelectedIndex(i);
								regl1.setText(tournament.getRegionals().get(c2.getSelectedIndex() + 1)
										.getParticipating().get(i).getName());
								regl2.setText(tournament.getRegionals().get(c2.getSelectedIndex() + 1)
										.getParticipating().get(i).getAddress());
								regl3.setText(tournament.getRegionals().get(c2.getSelectedIndex() + 1)
										.getParticipating().get(i).getCity());
								if (tournament.getRegionals().get(c2.getSelectedIndex() + 1).getParticipating()
										.get(i).getRegionalsHost().compareToIgnoreCase("null") == 0) {
									regl4.setSelectedIndex(0);
								} else {
									regl4.setSelectedIndex(1);
								}
								regl5.setText(tournament.getRegionals().get(c2.getSelectedIndex() + 1)
										.getParticipating().get(i).getTotal());
								regl6.setText(tournament.getRegionals().get(c2.getSelectedIndex() + 1)
										.getParticipating().get(i).getDistance() + " Miles");
								regl7.setText(tournament.getRegionals().get(c2.getSelectedIndex() + 1)
										.getParticipating().get(i).getAreaCode());
									}
								}	
							}
				    	}
				    }
					if(semi_stateTab.isShowing()) {
						
						for (GeoPosition waypoint : jMap2.geoList) {
							// convert to world bitmap
							gp_pt = jMap2.jXMapKit.getMainMap().getTileFactory().geoToPixel(waypoint,
									jMap2.jXMapKit.getMainMap().getZoom());

							// convert to screen
							Rectangle rect = jMap2.jXMapKit.getMainMap().getViewportBounds();
							Point converted_gp_pt = new Point((int) gp_pt.getX() - rect.x, (int) gp_pt.getY() - rect.y);
							// check if near the mouse
							if (converted_gp_pt.distance(me.getPoint()) < 15) {
								System.out.println("mapViewer mouse click has been detected within 10 pixels of a waypoint");
								jMap2.jXMapKit.setCenterPosition(waypoint);
								jMap2.jXMapKit.getMainMap().setZoom(7);
						
						for (int i = 0; i < tournament.getSemiState().get(c3.getSelectedIndex() + 1)
								.getParticipating().size(); i++) {
							if (tournament.getSemiState().get(c3.getSelectedIndex() + 1).getParticipating().get(i)
									.getLat() == waypoint.getLatitude()
									&& tournament.getSemiState().get(c3.getSelectedIndex() + 1).getParticipating()
											.get(i).getLng() == waypoint.getLongitude()) {
								semiJList.setSelectedIndex(i);
								ssl1.setText(tournament.getSemiState().get(c3.getSelectedIndex() + 1)
										.getParticipating().get(i).getName());
								ssl2.setText(tournament.getSemiState().get(c3.getSelectedIndex() + 1)
										.getParticipating().get(i).getAddress());
								ssl3.setText(tournament.getSemiState().get(c3.getSelectedIndex() + 1)
										.getParticipating().get(i).getCity());
								if (tournament.getSemiState().get(c3.getSelectedIndex() + 1).getParticipating()
										.get(i).getSemiStateHost().compareToIgnoreCase("null") == 0) {
									ssl4.setSelectedIndex(0);
								} else {
									ssl4.setSelectedIndex(1);
								}
								ssl5.setText(tournament.getSemiState().get(c3.getSelectedIndex() + 1)
										.getParticipating().get(i).getTotal());
								ssl6.setText(tournament.getSemiState().get(c3.getSelectedIndex() + 1)
										.getParticipating().get(i).getDistance() + " Miles");
								ssl7.setText(tournament.getSemiState().get(c3.getSelectedIndex() + 1)
										.getParticipating().get(i).getAreaCode());
						
									}		
								}
							}
						}
					}
		}
			

		

		@Override
		public void mouseEntered(MouseEvent arg0) {

		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}
	
	}
	
	class Listener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			if (sectionalTab.isShowing()) {
				int value = c1.getSelectedIndex() + 1;
				jMap.setFocus(secJList.getSelectedIndex());
				secl1.setText(tournament.getSectionals().get(c1.getSelectedIndex() + 1).getParticipating()
						.get(secJList.getSelectedIndex()).getName());
				secl2.setText(tournament.getSectionals().get(c1.getSelectedIndex() + 1).getParticipating()
						.get(secJList.getSelectedIndex()).getAddress());
				secl3.setText(tournament.getSectionals().get(c1.getSelectedIndex() + 1).getParticipating()
						.get(secJList.getSelectedIndex()).getCity());
				if (tournament.getSectionals().get(c1.getSelectedIndex() + 1).getParticipating()
						.get(secJList.getSelectedIndex()).getSectionalHost().compareToIgnoreCase("NULL") == 0) {
					secl4.setSelectedIndex(0);
					;
				} else {
					secl4.setSelectedIndex(1);
					;
				}
				secl5.setText(tournament.getSectionals().get(c1.getSelectedIndex() + 1).getParticipating()
						.get(secJList.getSelectedIndex()).getTotal());
				secl6.setText(tournament.getSectionals().get(c1.getSelectedIndex() + 1).getParticipating()
						.get(secJList.getSelectedIndex()).getDistance() + " Miles");
				secl7.setText(tournament.getSectionals().get(c1.getSelectedIndex() + 1).getParticipating()
						.get(secJList.getSelectedIndex()).getAreaCode());
			}

			if (regionalTab.isShowing()) {
				int value = c2.getSelectedIndex() + 1;
				jMap1.setFocus(regJList.getSelectedIndex());
				regl1.setText(tournament.getRegionals().get(c2.getSelectedIndex() + 1).getParticipating()
						.get(regJList.getSelectedIndex()).getName());
				regl2.setText(tournament.getRegionals().get(c2.getSelectedIndex() + 1).getParticipating()
						.get(regJList.getSelectedIndex()).getAddress());
				regl3.setText(tournament.getRegionals().get(c2.getSelectedIndex() + 1).getParticipating()
						.get(regJList.getSelectedIndex()).getCity());
				if (tournament.getRegionals().get(c2.getSelectedIndex() + 1).getParticipating()
						.get(regJList.getSelectedIndex()).getRegionalsHost().compareToIgnoreCase("NULL") == 0) {
					regl4.setSelectedIndex(0);
					;
				} else {
					regl4.setSelectedIndex(1);
					;
				}
				regl5.setText(tournament.getRegionals().get(c2.getSelectedIndex() + 1).getParticipating()
						.get(regJList.getSelectedIndex()).getTotal());
				regl6.setText(tournament.getRegionals().get(c2.getSelectedIndex() + 1).getParticipating()
						.get(regJList.getSelectedIndex()).getDistance() + " Miles");
				regl7.setText(tournament.getRegionals().get(c2.getSelectedIndex() + 1).getParticipating()
						.get(regJList.getSelectedIndex()).getAreaCode());

			}
			if (semi_stateTab.isShowing()) {
				int value = c3.getSelectedIndex() + 1;
				jMap2.setFocus(semiJList.getSelectedIndex());
				ssl1.setText(tournament.getSemiState().get(c3.getSelectedIndex() + 1).getParticipating()
						.get(semiJList.getSelectedIndex()).getName());
				ssl2.setText(tournament.getSemiState().get(c3.getSelectedIndex() + 1).getParticipating()
						.get(semiJList.getSelectedIndex()).getAddress());
				ssl3.setText(tournament.getSemiState().get(c3.getSelectedIndex() + 1).getParticipating()
						.get(semiJList.getSelectedIndex()).getCity());
				if (tournament.getSemiState().get(c3.getSelectedIndex() + 1).getParticipating()
						.get(semiJList.getSelectedIndex()).getSemiStateHost().compareToIgnoreCase("NULL") == 0) {
					ssl4.setSelectedIndex(0);
					;
				} else {
					ssl4.setSelectedIndex(1);
					;
				}
				ssl5.setText(tournament.getSemiState().get(c3.getSelectedIndex() + 1).getParticipating()
						.get(semiJList.getSelectedIndex()).getTotal());
				ssl6.setText(tournament.getSemiState().get(c3.getSelectedIndex() + 1).getParticipating()
						.get(semiJList.getSelectedIndex()).getDistance() + " Miles");
				ssl7.setText(tournament.getSemiState().get(c3.getSelectedIndex() + 1).getParticipating()
						.get(semiJList.getSelectedIndex()).getAreaCode());
			}

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}

	public class actionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == secEditB) {
			
				int value = c1.getSelectedIndex() + 1;
				secl1.setEditable(true);
				secl2.setEditable(true);
				secl3.setEditable(true);
				secl5.setEditable(true);
				secl7.setEditable(true);
				secSaveB.setVisible(true);
				secl4.setEnabled(true);
				secAddB.setEnabled(false);
				secRemoveB.setEnabled(false);
				
				

			}
			if(e.getSource() == regEditB) {
				int value = c2.getSelectedIndex() + 1;
			
				regl1.setEditable(true);
				regl2.setEditable(true);
				regl3.setEditable(true);
				regl5.setEditable(true);
				regl7.setEditable(true);
				regSaveB.setVisible(true);
				regl4.setEnabled(true);
				regAddB.setEnabled(false);
				regRemoveB.setEnabled(false);
			}
			if(e.getSource() == semiEditB) {
				
				int value = c3.getSelectedIndex() + 1;
				ssl1.setEditable(true);
				ssl2.setEditable(true);
				ssl3.setEditable(true);
				ssl5.setEditable(true);
				ssl7.setEditable(true);
				semiSaveB.setVisible(true);
				ssl4.setEnabled(true);
				semiRemoveB.setEnabled(false);
				semiAddB.setEnabled(false);
				
				
			}
	
			if (e.getSource() == secSaveB) {
					int value = c1.getSelectedIndex() + 1;
					int value1 = secJList.getSelectedIndex();
					if (flag) {
						

						School New = (tournament.getSchoolList().get(sList.getSelectedIndex()));

						if (New.getSectionalHost().compareToIgnoreCase("true") == 0) {
							tournament.getSectionals().get(value).getHost().setSectionalHost("false");
						} else {

						}

						tournament.getSectionals().get(value).addSchool(New);
						secDefaultList.addElement(secDefaultList.size() + " " + New.getName());

						jMap.createGeo(tournament.getSectionals().get(value).getParticipating(),
								tournament.getSectionals().get(value).getHost());

						double average;
						double total = 0;
						for (int i = 0; i < tournament.getSectionals().get(1).getParticipating().size(); i++) {
							// System.out.println(tournament.getSectionals().get(1).getParticipating().get(i).getDistance());
							total = total + tournament.getSectionals().get(1).getParticipating().get(i).getDistance();
						}
						average = total / tournament.getSectionals().get(1).getParticipating().size();
						average = Math.round(average * 100.0) / 100.0;
						sece2.setText(average + " Miles");

						secl1.setEditable(false);
						secl2.setEditable(false);
						secl3.setEditable(false);
						secl5.setEditable(false);
						secl4.setEnabled(false);
						secl7.setEditable(false);
						cN1.setVisible(false);
						secSaveB.setVisible(false);
						secEditB.setEnabled(true);
						seccenterC.setVisible(true);


					} else {
						String temp = tournament.getSectionals().get(value).getHost().getName();
						if (secl4.getSelectedItem().toString().compareToIgnoreCase("yes") == 0) {
							tournament.getSectionals().get(value).getParticipating().get(value1)
									.setSectionalHost("TRUE");
							tournament.getSectionals().get(value).setHost(tournament.getSectionals().get(value)
									.getParticipating().get(secJList.getSelectedIndex()));
						//	System.out.println(
						//			tournament.getSectionals().get(1).getParticipating().get(6).getSectionalHost());
						//	System.out.println(
						//			tournament.getSectionals().get(1).getParticipating().get(10).getSectionalHost());
						}

						for (int i = 0; i < tournament.getSectionals().get(value).getParticipating().size(); i++) {
							if (tournament.getSectionals().get(value).getParticipating().get(i).getName()
									.compareTo(temp) == 0) {
								tournament.getSectionals().get(value).getParticipating().get(i)
										.setSectionalHost("null");

							}
						}
						
						fm.distance(tournament);
						double average = 0;
						double total = 0;
						for (int i = 0; i < tournament.getSectionals().get(value).getParticipating().size(); i++) {
							// System.out.println(tournament.getSectionals().get(1).getParticipating().get(i).getDistance());
							total = total + tournament.getSectionals().get(value).getParticipating().get(i).getDistance();

							average = total / tournament.getSectionals().get(value).getParticipating().size();
							average = Math.round(average * 100.0) / 100.0;
						}
							sece2.setText(average + " Miles");
							tournament.getSectionals().get(value).getParticipating().get(secJList.getSelectedIndex()).setName(secl1.getText());
							tournament.getSectionals().get(value).getParticipating().get(secJList.getSelectedIndex()).setAddress(secl2.getText());	
							tournament.getSectionals().get(value).getParticipating().get(secJList.getSelectedIndex()).setCity(secl3.getText());
							tournament.getSectionals().get(value).getParticipating().get(secJList.getSelectedIndex()).setAreaCode(secl7.getText());
							tournament.getSectionals().get(value).getParticipating().get(secJList.getSelectedIndex()).setTotal(secl5.getText());
							secDefaultList.setElementAt(c1.getSelectedIndex() + " " + secl1.getText(),c1.getSelectedIndex());
							
						
						

						jMap.createGeo(tournament.getSectionals().get(value).getParticipating(),
								tournament.getSectionals().get(value).getHost());
						secl1.setEditable(false);
						secl2.setEditable(false);
						secl3.setEditable(false);
						secl5.setEditable(false);
						secl4.setEnabled(false);
						secl7.setEditable(false);
						secSaveB.setVisible(false);
						secRemoveB.setEnabled(true);
						secAddB.setEnabled(true);

					}
					flag = false;
					sList.setVisible(false);			
					secRemoveB.setEnabled(true);

				}

				if (e.getSource() == regSaveB) {
					int value = c2.getSelectedIndex() + 1;
					int value1 = regJList.getSelectedIndex();
					if (flag) {
						System.out.println("test");

						School New = (tournament.getSchoolList().get(rList.getSelectedIndex()));

						if (New.getRegionalsHost().compareToIgnoreCase("true") == 0) {
							tournament.getRegionals().get(value).getHost().setRegionalsHost("false");
						} else {

						}

						tournament.getRegionals().get(value).addSchool(New);
						regDefaultList.addElement(regDefaultList.size() + " " + New.getName());

						jMap1.createGeo(tournament.getRegionals().get(value).getParticipating(),
								tournament.getRegionals().get(value).getHost());

						double average;
						double total = 0;
						for (int i = 0; i < tournament.getRegionals().get(1).getParticipating().size(); i++) {
							// System.out.println(tournament.getSectionals().get(1).getParticipating().get(i).getDistance());
							total = total + tournament.getRegionals().get(1).getParticipating().get(i).getDistance();
						}
						average = total / tournament.getRegionals().get(1).getParticipating().size();
						average = Math.round(average * 100.0) / 100.0;
						rege2.setText(average + " Miles");

						regl1.setEditable(false);
						regl2.setEditable(false);
						regl3.setEditable(false);
						regl5.setEditable(false);
						regl4.setEnabled(false);
						regl7.setEditable(false);
						cN1.setVisible(false);
						regSaveB.setVisible(false);
						regEditB.setEnabled(true);
						regcenterC.setVisible(true);

					} else {
						String temp = tournament.getRegionals().get(value).getHost().getName();
						if (regl4.getSelectedItem().toString().compareToIgnoreCase("yes") == 0) {
							tournament.getRegionals().get(value).getParticipating().get(value1)
									.setRegionalsHost("TRUE");
							tournament.getRegionals().get(value).setHost(tournament.getRegionals().get(value)
									.getParticipating().get(regJList.getSelectedIndex()));
						
						}

						for (int i = 0; i < tournament.getRegionals().get(value).getParticipating().size(); i++) {
							if (tournament.getRegionals().get(value).getParticipating().get(i).getName()
									.compareTo(temp) == 0) {
								tournament.getRegionals().get(value).getParticipating().get(i)
										.setRegionalsHost("null");

							}
						}
						
						fm.distance(tournament);
						double average = 0;
						double total = 0;
						for (int i = 0; i < tournament.getRegionals().get(value).getParticipating().size(); i++) {
							// System.out.println(tournament.getSectionals().get(1).getParticipating().get(i).getDistance());
							total = total + tournament.getRegionals().get(value).getParticipating().get(i).getDistance();

							average = total / tournament.getRegionals().get(value).getParticipating().size();
							average = Math.round(average * 100.0) / 100.0;
						}
							rege2.setText(average + " Miles");
							
							
							tournament.getRegionals().get(value).getParticipating().get(regJList.getSelectedIndex()).setName(regl1.getText());
							tournament.getRegionals().get(value).getParticipating().get(regJList.getSelectedIndex()).setAddress(regl2.getText());	
							tournament.getRegionals().get(value).getParticipating().get(regJList.getSelectedIndex()).setCity(regl3.getText());
							tournament.getRegionals().get(value).getParticipating().get(regJList.getSelectedIndex()).setAreaCode(regl7.getText());
							tournament.getRegionals().get(value).getParticipating().get(regJList.getSelectedIndex()).setTotal(regl5.getText());
							regDefaultList.setElementAt(c2.getSelectedIndex() + " " + regl1.getText(),c2.getSelectedIndex());
							
						
						

						jMap1.createGeo(tournament.getRegionals().get(value).getParticipating(),
								tournament.getRegionals().get(value).getHost());
						regl1.setEditable(false);
						regl2.setEditable(false);
						regl3.setEditable(false);
						regl5.setEditable(false);
						regl4.setEnabled(false);
						regl7.setEditable(false);
						regSaveB.setVisible(false);
						regRemoveB.setEnabled(true);
						regAddB.setEnabled(true);

					}
					flag = false;
					rList.setVisible(false);
					semicenterC.setVisible(true);
					regRemoveB.setEnabled(true);

				}
				
				
				if (e.getSource() == semiSaveB) {
					int value = c3.getSelectedIndex() + 1;
					int value1 = semiJList.getSelectedIndex();
					if (flag) {
						System.out.println("test");

						School New = (tournament.getSchoolList().get(ssList.getSelectedIndex()));

						if (New.getSemiStateHost().compareToIgnoreCase("true") == 0) {
							tournament.getSemiState().get(value).getHost().setSemiStateHost("false");
						} else {

						}

						tournament.getSemiState().get(value).addSchool(New);
						semiDefaultList.addElement(semiDefaultList.size() + " " + New.getName());

						jMap2.createGeo(tournament.getSemiState().get(value).getParticipating(),
								tournament.getSemiState().get(value).getHost());

						double average;
						double total = 0;
						for (int i = 0; i < tournament.getSemiState().get(value).getParticipating().size(); i++) {
							// System.out.println(tournament.getSectionals().get(1).getParticipating().get(i).getDistance());
							total = total + tournament.getSemiState().get(value).getParticipating().get(i).getDistance();
						}
						average = total / tournament.getSemiState().get(value).getParticipating().size();
						average = Math.round(average * 100.0) / 100.0;
						sse2.setText(average + " Miles");
						
					
						
						
						ssl1.setEditable(false);
						ssl2.setEditable(false);
						ssl3.setEditable(false);
						ssl5.setEditable(false);
						ssl4.setEnabled(false);
						ssl7.setEditable(false);
						cN1.setVisible(false);
						semiSaveB.setVisible(false);
						semiEditB.setEnabled(true);
						semicenterC.setVisible(true);

					} else {
						String temp = tournament.getSemiState().get(value).getHost().getName();
						if (ssl4.getSelectedItem().toString().compareToIgnoreCase("yes") == 0) {
							tournament.getSemiState().get(value).getParticipating().get(value1)
									.setSemiStateHost("TRUE");
							tournament.getSemiState().get(value).setHost(tournament.getSemiState().get(value)
									.getParticipating().get(semiJList.getSelectedIndex()));
						}

						for (int i = 0; i < tournament.getSemiState().get(value).getParticipating().size(); i++) {
							if (tournament.getSemiState().get(value).getParticipating().get(i).getName()
									.compareTo(temp) == 0) {
								tournament.getSemiState().get(value).getParticipating().get(i)
										.setSemiStateHost("null");

							}
						}
						
						fm.distance(tournament);
						double average = 0;
						double total = 0;
						for (int i = 0; i < tournament.getSemiState().get(value).getParticipating().size(); i++) {
							// System.out.println(tournament.getSectionals().get(1).getParticipating().get(i).getDistance());
							total = total + tournament.getSemiState().get(value).getParticipating().get(i).getDistance();

							average = total / tournament.getSemiState().get(value).getParticipating().size();
							average = Math.round(average * 100.0) / 100.0;
						}
							sse2.setText(average + " Miles");
							
							
							tournament.getSemiState().get(value).getParticipating().get(semiJList.getSelectedIndex()).setName(ssl1.getText());
							tournament.getSemiState().get(value).getParticipating().get(semiJList.getSelectedIndex()).setAddress(ssl2.getText());	
							tournament.getSemiState().get(value).getParticipating().get(semiJList.getSelectedIndex()).setCity(ssl3.getText());
							tournament.getSemiState().get(value).getParticipating().get(semiJList.getSelectedIndex()).setAreaCode(ssl7.getText());
							tournament.getSemiState().get(value).getParticipating().get(semiJList.getSelectedIndex()).setTotal(ssl5.getText());
							semiDefaultList.setElementAt(c3.getSelectedIndex() + " " + ssl1.getText(),c3.getSelectedIndex());
						
						

						jMap2.createGeo(tournament.getSemiState().get(value).getParticipating(),
								tournament.getSemiState().get(value).getHost());
						ssl1.setEditable(false);
						ssl2.setEditable(false);
						ssl3.setEditable(false);
						ssl5.setEditable(false);
						ssl4.setEnabled(false);
						ssl7.setEditable(false);
						semiSaveB.setVisible(false);
						semiRemoveB.setEnabled(true);
						semiAddB.setEnabled(true);

					}
					flag = false;
					ssList.setVisible(false);
					semicenterC.setVisible(true);
					semiRemoveB.setEnabled(true);

				}

			

			if (e.getSource() == secAddB) {
				// change this method to show list of schools
					flag = true;
					cN1.setVisible(true);
					secSaveB.setVisible(true);
					secSaveB.setText("add");
					secl1.setEditable(true);
					secl2.setEditable(true);
					secl3.setEditable(true);
					secl5.setEditable(true);
					secl4.setEnabled(true);
					secl7.setEditable(true);
					semicenterC.setVisible(false);
					sList.setVisible(true);
					secRemoveB.setEnabled(false);
					secEditB.setEnabled(false);
					seccenterC.setVisible(false);

				}
				if(e.getSource() == regAddB) {
					flag = true;
					cN1.setVisible(true);
					regSaveB.setVisible(true);
					regSaveB.setText("add");
					regl1.setEditable(true);
					regl2.setEditable(true);
					regl3.setEditable(true);
					regl5.setEditable(true);
					regl4.setEnabled(true);
					regl7.setEditable(true);
					semicenterC.setVisible(false);
					rList.setVisible(true);
					regRemoveB.setEnabled(false);
					regEditB.setEnabled(false);
					regcenterC.setVisible(false);
					
				}
				if(e.getSource() == semiAddB) {
					flag = true;
					cN1.setVisible(true);
					semiSaveB.setVisible(true);
					semiSaveB.setText("add");
					ssl1.setEditable(true);
					ssl2.setEditable(true);
					ssl3.setEditable(true);
					ssl5.setEditable(true);
					ssl4.setEnabled(true);
					ssl7.setEditable(true);
					semicenterC.setVisible(false);
					ssList.setVisible(true);
					semiRemoveB.setEnabled(false);
					semiEditB.setEnabled(false);
					regcenterC.setVisible(false);
					
				}	
			if (e.getSource() == secRemoveB) {

					int value = c1.getSelectedIndex() + 1;
					tournament.getSectionals().get(value).removeSchool((secJList.getSelectedIndex()));
					jMap.createGeo(tournament.getSectionals().get(value).getParticipating(),
							tournament.getSectionals().get(value).getHost());
					//
					// adding a number to the name of each school in JList

					secDefaultList.remove(secJList.getSelectedIndex());

					double average;
					double total = 0;
					for (int i = 0; i < tournament.getSectionals().get(value).getParticipating().size(); i++) {
						// System.out.println(tournament.getSectionals().get(1).getParticipating().get(i).getDistance());
						total = total + tournament.getSectionals().get(value).getParticipating().get(i).getDistance();

						average = total / tournament.getSectionals().get(value).getParticipating().size();
						average = Math.round(average * 100.0) / 100.0;
						sece2.setText(average + " Miles");

					}
					

				}
				
				if (e.getSource() == regRemoveB) {
						
					int value = c2.getSelectedIndex() + 1;
					tournament.getRegionals().get(value).removeSchool((regJList.getSelectedIndex()));
					jMap1.createGeo(tournament.getRegionals().get(value).getParticipating(),
							tournament.getRegionals().get(value).getHost());
					//
					// adding a number to the name of each school in JList

					regDefaultList.remove(regJList.getSelectedIndex());

					double average;
					double total = 0;
					for (int i = 0; i < tournament.getRegionals().get(value).getParticipating().size(); i++) {
						// System.out.println(tournament.getSectionals().get(1).getParticipating().get(i).getDistance());
						total = total + tournament.getRegionals().get(value).getParticipating().get(i).getDistance();

						average = total / tournament.getRegionals().get(value).getParticipating().size();
						average = Math.round(average * 100.0) / 100.0;
						rege2.setText(average + " Miles");
					}
					
				}
				if (e.getSource() == semiRemoveB) {

					int value = c3.getSelectedIndex() + 1;
					tournament.getSemiState().get(value).removeSchool((semiJList.getSelectedIndex()));
					jMap2.createGeo(tournament.getSemiState().get(value).getParticipating(),
							tournament.getSemiState().get(value).getHost());
					//
					// adding a number to the name of each school in JList

					semiDefaultList.remove(semiJList.getSelectedIndex());

					double average;
					double total = 0;
					for (int i = 0; i < tournament.getSemiState().get(value).getParticipating().size(); i++) {
						// System.out.println(tournament.getSectionals().get(1).getParticipating().get(i).getDistance());
						total = total + tournament.getSemiState().get(value).getParticipating().get(i).getDistance();

						average = total / tournament.getSemiState().get(value).getParticipating().size();
						average = Math.round(average * 100.0) / 100.0;
						sse2.setText(average + " Miles");
	
					}
				}
			}
		}			
	
	class SaveAndLoad implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(load)) {

				FileNameExtensionFilter filter = new FileNameExtensionFilter("dat Files (*.dat)","dat");
				JFileChooser loadFile = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
				loadFile.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				loadFile.setFileFilter(filter);
				if(loadFile.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					
					//System.out.println(loadFile.getSelectedFile().getPath());
					tournament = fm.importExists(loadFile.getSelectedFile().getPath());
				}
				
				

			}
			
			if (e.getSource().equals(save)) {
				fm.save(tournament);
			}
			
			if(e.getSource().equals(saveAs)) {
				
				
				FileNameExtensionFilter filter = new FileNameExtensionFilter("dat Files (*.dat)","dat");
				JFileChooser saveFile = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
				saveFile.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				saveFile.setFileFilter(filter);
				if(saveFile.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
					if(saveFile.getSelectedFile().exists()) {
						fm.saveAs(tournament, saveFile.getSelectedFile().getPath());
					}
					else {
				           int result = JOptionPane.showConfirmDialog(null, "File already exists, overwrite?", "Overwrite Warning", JOptionPane.YES_NO_CANCEL_OPTION);
				           if(result == JOptionPane.YES_OPTION) {
				        	   fm.saveAs(tournament, saveFile.getSelectedFile().getPath());
				           }
				            
					}
				}
			}
		}
	}
}
