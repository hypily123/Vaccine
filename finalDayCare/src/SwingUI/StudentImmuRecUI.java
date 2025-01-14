package SwingUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


import SubjectClass.Student;

import javax.swing.UIManager;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class StudentImmuRecUI extends JFrame {

	private JFrame frame;

	private JLabel Hibtimes;
	private JLabel DTaPtimes;
	private JLabel Poliotimes;
	private JLabel Heptimes;
	private JLabel MMRtimes;
	private JLabel Varicellatimes;
	private JLabel Tdaptimes;
	private JLabel Meningococcaltimes;

	private JTextField Hibyear,Hibmonth;
	private JTextField DTaPyear,DTaPmonth;
	private JTextField Polioyear,Poliomonth;
	private JTextField hepatitisByear,hepatitisBmonth;
	private JTextField MMRyear,MMRmonth;
	private JTextField Varicellayear,Varicellamonth;
	private JTextField Tdapyear,Tdapmonth;
	private JTextField Meningococcalyear,Meningococcalmonth;

	private JTextField idtxt;
	private JTextField firsttxt;
	private JTextField lasttxt;
	private JTextField agetxt;
	
	private Student stu = null;

	public void CreateStudent(String id, String firstn, String lastn, String age)  {
		stu = new Student(Integer.parseInt(id), firstn, lastn, Integer.parseInt(age));
	}
	/**
	 * Create the frame.
	 * @throws IOException 
	 * @throws NumberFormatException 
	 */
	public StudentImmuRecUI(String id, String firstName, String lastName, String age) {
		
		this.CreateStudent(id, firstName, lastName, age);
		int AGE = Integer.parseInt(age);
		StringBuilder sb = new StringBuilder();
		//sb.append("D:\\").append(id).append(".csv");
		sb.append("./").append(id).append(".csv");
		File IRfile = new File(sb.toString());
		if(!IRfile.exists())
		{
			try {
				IRfile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(IRfile.length() != 0)
		{
			FileReader IRfr = null;
			try {
				IRfr = new FileReader(IRfile);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			BufferedReader IRbr = new BufferedReader(IRfr);
			String s;
			Vector<Vector<String>> stuIR = new Vector<>();
			try {
				while ((s = IRbr.readLine()) != null) {
					String[] line = s.split(",");
					Vector<String> curline = new Vector<>(Arrays.asList(line));
					stuIR.add(curline);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			stu.setID(Integer.parseInt(stuIR.get(0).get(0)));
			stu.setFirstName(stuIR.get(0).get(1));
			stu.setLastName(stuIR.get(0).get(2));
			stu.setAge(Integer.parseInt(stuIR.get(0).get(3)));
//				HepatitisB hb;
//				Dtap dtap;
//				Hib hib;
//				Polio polio;
//				MMR mmr;
//				Varicella varicella;
//				Tdap tdap;
//				Meningococcal me;
			for(int i = 1; i <= stuIR.get(1).size() - 3; i++)
			{
				String[] date = stuIR.get(1).get(i).split("-");
				stu.hb.add(Integer.parseInt(date[0]), Integer.parseInt(date[1]));
			}
			for(int i = 1; i <= stuIR.get(2).size() - 3; i++)
			{
				String[] date = stuIR.get(2).get(i).split("-");
				stu.dtap.add(Integer.parseInt(date[0]), Integer.parseInt(date[1]));
			}
			for(int i = 1; i <= stuIR.get(3).size() - 3; i++)
			{
				String[] date = stuIR.get(3).get(i).split("-");
				stu.hib.add(Integer.parseInt(date[0]), Integer.parseInt(date[1]));
			}
			for(int i = 1; i <= stuIR.get(4).size() - 3; i++)
			{
				String[] date = stuIR.get(4).get(i).split("-");
				stu.polio.add(Integer.parseInt(date[0]), Integer.parseInt(date[1]));
			}
			for(int i = 1; i <= stuIR.get(5).size() - 3; i++)
			{
				String[] date = stuIR.get(5).get(i).split("-");
				stu.mmr.add(Integer.parseInt(date[0]), Integer.parseInt(date[1]));
			}
			for(int i = 1; i <= stuIR.get(6).size() - 3; i++)
			{
				String[] date = stuIR.get(6).get(i).split("-");
				stu.varicella.add(Integer.parseInt(date[0]), Integer.parseInt(date[1]));
			}
			for(int i = 1; i <= stuIR.get(7).size() - 3; i++)
			{
				String[] date = stuIR.get(7).get(i).split("-");
				stu.tdap.add(Integer.parseInt(date[0]), Integer.parseInt(date[1]));
			}
			for(int i = 1; i <= stuIR.get(8).size() - 3; i++)
			{
				String[] date = stuIR.get(8).get(i).split("-");
				stu.me.add(Integer.parseInt(date[0]), Integer.parseInt(date[1]));
			}
			try {
				IRbr.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				IRfr.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		stu.hib.SetNextDate(AGE);
		stu.dtap.SetNextDate(AGE);
		stu.polio.SetNextDate(AGE);
		stu.hb.SetNextDate(AGE);
		stu.mmr.SetNextDate(AGE);
		stu.varicella.SetNextDate(AGE);
		stu.tdap.SetNextDate(AGE);
		stu.me.SetNextDate(AGE);
					
		
		frame = new JFrame();
		frame.getContentPane().setForeground(Color.LIGHT_GRAY);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 725, 520);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		setTitle("Create a new teacher");
		setBounds(100, 100, 798, 520);
		getContentPane().setLayout(null);
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 780, 520);
		getContentPane().add(panel);
		panel.setLayout(null);

		//background
		/**JLabel lblNewLabel1 = new JLabel("");
		lblNewLabel1.setIcon(new ImageIcon(StudentImmuRecUI.class.getResource("/images/background.jpg")));
		lblNewLabel1.setBounds(0, 0, 725, 500);
		panel.add(lblNewLabel1);*/

		//set shot labels;
		JLabel lblTeacher = new JLabel("Student's Immunization record");
		lblTeacher.setForeground(Color.DARK_GRAY);
		lblTeacher.setFont(new Font("Sitka Banner", Font.BOLD, 24));
		lblTeacher.setBounds(175, 10, 423, 45);
		panel.add(lblTeacher);

		JLabel labelHib = new JLabel("Hib :");
		labelHib.setFont(new Font("Sitka Banner", Font.PLAIN, 20));
		labelHib.setBounds(86, 95, 120, 45);
		panel.add(labelHib);

		JLabel labelDTaP = new JLabel("DTaP :");
		labelDTaP.setFont(new Font("Sitka Banner", Font.PLAIN, 20));
		labelDTaP.setBounds(86, 141, 120, 45);
		panel.add(labelDTaP);

		JLabel lblPolio = new JLabel("Polio :");
		lblPolio.setFont(new Font("Sitka Banner", Font.PLAIN, 20));
		lblPolio.setBounds(86, 189, 120, 45);
		panel.add(lblPolio);

		JLabel lblHepatitis = new JLabel("Hepatitis B :");
		lblHepatitis.setFont(new Font("Sitka Banner", Font.PLAIN, 20));
		lblHepatitis.setBounds(86, 237, 120, 45);
		panel.add(lblHepatitis);	

		JLabel lblMMR = new JLabel("MMR :");
		lblMMR.setFont(new Font("Sitka Banner", Font.PLAIN, 20));
		lblMMR.setBounds(86, 276, 120, 45);
		panel.add(lblMMR);	

		JLabel lblVaricella = new JLabel("Varicella :");
		lblVaricella.setFont(new Font("Sitka Banner", Font.PLAIN, 20));
		lblVaricella.setBounds(86, 321, 120, 45);
		panel.add(lblVaricella);

		JLabel lblTdap = new JLabel("Tdap :");
		lblTdap.setFont(new Font("Sitka Banner", Font.PLAIN, 20));
		lblTdap.setBounds(86, 364, 120, 45);
		panel.add(lblTdap);

		JLabel lblMeningococcal = new JLabel("Meningococcal:");
		lblMeningococcal.setFont(new Font("Sitka Banner", Font.PLAIN, 20));
		lblMeningococcal.setBounds(86, 409, 153, 45);
		panel.add(lblMeningococcal);

		//to show how many shots times;
		JLabel Hibtimes = new JLabel();
		Hibtimes.setForeground(Color.BLUE);
		Hibtimes.setBounds(39, 109, 24, 16);
		panel.add(Hibtimes);
		Hibtimes.setFont(new Font("Dialog", Font.PLAIN, 20));
		Hibtimes.setText(String.valueOf(stu.hib.GetSize()));
		//Hibtimes.setText();this"()" will have initial value comes in;

		JLabel DTaPtimes = new JLabel();
		DTaPtimes.setForeground(Color.BLUE);
		DTaPtimes.setBounds(39, 155, 24, 16);
		panel.add(DTaPtimes);
		DTaPtimes.setFont(new Font("Dialog", Font.PLAIN, 20));
		DTaPtimes.setText(String.valueOf(stu.dtap.GetSize()));
		//DTaPtimes.setText();this"()" will have initial value comes in;

		JLabel Poliotimes = new JLabel();
		Poliotimes.setForeground(Color.BLUE);
		Poliotimes.setBounds(39, 204, 24, 16);
		panel.add(Poliotimes);
		Poliotimes.setFont(new Font("Dialog", Font.PLAIN, 20));
		Poliotimes.setText(String.valueOf(stu.polio.GetSize()));
		//Poliotimes.setText();this"()" will have initial value comes in;

		JLabel Heptimes = new JLabel();
		Heptimes.setForeground(Color.BLUE);
		Heptimes.setBounds(39, 250, 24, 16);
		panel.add(Heptimes);
		Heptimes.setFont(new Font("Dialog", Font.PLAIN, 20));
		Heptimes.setText(String.valueOf(stu.hb.GetSize()));
		//Hepatitistimes.setText();this"()" will have initial value comes in;

		JLabel MMRtimes = new JLabel();
		MMRtimes.setForeground(Color.BLUE);
		MMRtimes.setBounds(39, 289, 24, 16);
		panel.add(MMRtimes);
		MMRtimes.setFont(new Font("Dialog", Font.PLAIN, 20));
		MMRtimes.setText(String.valueOf(stu.mmr.GetSize()));
		//MMRtimes.setText();this"()" will have initial value comes in;

		JLabel Varicellatimes = new JLabel();
		Varicellatimes.setForeground(Color.BLUE);
		Varicellatimes.setBounds(39, 334, 24, 16);
		panel.add(Varicellatimes);
		Varicellatimes.setFont(new Font("Dialog", Font.PLAIN, 20));
		Varicellatimes.setText(String.valueOf(stu.varicella.GetSize()));
		//Varicellatimes.setText();this"()" will have initial value comes in;

		JLabel Tdaptimes = new JLabel();
		Tdaptimes.setForeground(Color.BLUE);
		Tdaptimes.setBounds(39, 380, 24, 16);
		panel.add(Tdaptimes);
		Tdaptimes.setFont(new Font("Dialog", Font.PLAIN, 20));
		Tdaptimes.setText(String.valueOf(stu.tdap.GetSize()));
		//Varicellatimes.setText();this"()" will have initial value comes in;

		JLabel Meningococcaltimes = new JLabel();
		Meningococcaltimes.setForeground(Color.BLUE);
		Meningococcaltimes.setBounds(39, 422, 24, 16);
		panel.add(Meningococcaltimes);
		Meningococcaltimes.setFont(new Font("Dialog", Font.PLAIN, 20));
		Meningococcaltimes.setText(String.valueOf(stu.me.GetSize()));
		//Varicellatimes.setText();this"()" will have initial value comes in;
		
		JLabel lblYear = new JLabel("year:\"YYYY\"");
	    lblYear.setBounds(210, 75, 74, 16);
	    panel.add(lblYear);
	    
	    JLabel lblMonthmm = new JLabel("month:\"MM\"");
	    lblMonthmm.setBounds(289, 75, 79, 16);
	    panel.add(lblMonthmm);
	    
	    JLabel lblRecommandNext = new JLabel("Suggested time");
	    lblRecommandNext.setBounds(387, 58, 113, 16);
	    panel.add(lblRecommandNext);
	    
	    JLabel lblDoseTime = new JLabel("for next dose");
	    lblDoseTime.setBounds(397, 75, 99, 16);
	    panel.add(lblDoseTime);
	    
	    JLabel lblHibdose = new JLabel();
	    lblHibdose.setFont(new Font("Sitka Banner", Font.PLAIN, 20));
	    lblHibdose.setBounds(407, 104, 87, 26);
	    panel.add(lblHibdose);
	    lblHibdose.setText(stu.hib.ShownextDate());
	    
	    JLabel lblDtapdose = new JLabel("dtapdose");
	    lblDtapdose.setFont(new Font("Sitka Banner", Font.PLAIN, 20));
	    lblDtapdose.setBounds(406, 153, 74, 18);
	    panel.add(lblDtapdose);
	    lblDtapdose.setText(stu.dtap.ShownextDate());
	    
	    JLabel lblPoliodose = new JLabel();
	    lblPoliodose.setFont(new Font("Sitka Banner", Font.PLAIN, 20));
	    lblPoliodose.setBounds(407, 196, 74, 31);
	    panel.add(lblPoliodose);
	    lblPoliodose.setText(stu.polio.ShownextDate());
	    
	    JLabel lblHbdose = new JLabel();
	    lblHbdose.setFont(new Font("Sitka Banner", Font.PLAIN, 20));
	    lblHbdose.setBounds(407, 244, 74, 31);
	    panel.add(lblHbdose);
	    lblHbdose.setText(stu.hb.ShownextDate());
	    
	    JLabel lblMmrdose = new JLabel();
	    lblMmrdose.setFont(new Font("Sitka Banner", Font.PLAIN, 20));
	    lblMmrdose.setBounds(407, 283, 90, 31);
	    panel.add(lblMmrdose);
	    lblMmrdose.setText(stu.mmr.ShownextDate());
	    
	    JLabel lblVadose = new JLabel();
	    lblVadose.setFont(new Font("Sitka Banner", Font.PLAIN, 20));
	    lblVadose.setBounds(407, 328, 74, 31);
	    panel.add(lblVadose);
	    lblVadose.setText(stu.varicella.ShownextDate());
	    
	    JLabel lblTdapdose = new JLabel();
	    lblTdapdose.setFont(new Font("Sitka Banner", Font.PLAIN, 20));
	    lblTdapdose.setBounds(407, 371, 88, 31);
	    panel.add(lblTdapdose);
	    lblTdapdose.setText(stu.tdap.ShownextDate());
	    
	    JLabel lblMedose = new JLabel();
	    lblMedose.setFont(new Font("Sitka Banner", Font.PLAIN, 20));
	    lblMedose.setBounds(407, 416, 88, 31);
	    panel.add(lblMedose);
	    lblMedose.setText(stu.me.ShownextDate());
	    
	    JLabel lblDoseTimes = new JLabel("times");
	    lblDoseTimes.setBounds(24, 75, 39, 16);
	    panel.add(lblDoseTimes);
	    
	    JLabel lblNewLabel = new JLabel("vaccine name");
	    lblNewLabel.setBounds(76, 75, 99, 16);
	    panel.add(lblNewLabel);

		//set shot date:
		Hibyear = new JTextField();
		Hibyear.setBounds(209, 104, 67, 26);
		panel.add(Hibyear);
		//Hibyear.addFocusListener(new JTextFieldHintListener(Hibyear, "YYYY"));
		Hibyear.setColumns(10);
		Hibmonth = new JTextField();
		Hibmonth.setBounds(300, 104, 50, 26);
		panel.add(Hibmonth);
		//Hibmonth.addFocusListener(new JTextFieldHintListener(Hibmonth, "MM"));
		Hibmonth.setColumns(10);

		DTaPyear = new JTextField();
		DTaPyear.setBounds(209, 150, 67, 26);
		panel.add(DTaPyear);
		//DTaPyear.addFocusListener(new JTextFieldHintListener(DTaPyear, "YYYY"));
		DTaPyear.setColumns(10);
		DTaPmonth = new JTextField();
		DTaPmonth.setBounds(300, 150, 50, 26);
		panel.add(DTaPmonth);
		//DTaPmonth.addFocusListener(new JTextFieldHintListener(DTaPmonth, "MM"));
		DTaPmonth.setColumns(10);

		Polioyear = new JTextField();
		Polioyear.setBounds(209, 198, 67, 26);
		panel.add(Polioyear);
		//Polioyear.addFocusListener(new JTextFieldHintListener(Polioyear, "YYYY"));
		Polioyear.setColumns(10);
		Poliomonth = new JTextField();
		Poliomonth.setBounds(300, 198, 50, 26);
		panel.add(Poliomonth);
		//Poliomonth.addFocusListener(new JTextFieldHintListener(Poliomonth, "MM"));
		Poliomonth.setColumns(10);

		hepatitisByear = new JTextField();
		hepatitisByear.setBounds(209, 246, 67, 26);
		panel.add(hepatitisByear);
		//hepatitisByear.addFocusListener(new JTextFieldHintListener(hepatitisByear, "YYYY"));
		hepatitisByear.setColumns(10);
		hepatitisBmonth = new JTextField();
		hepatitisBmonth.setBounds(300, 246, 50, 26);
		panel.add(hepatitisBmonth);
		//hepatitisBmonth.addFocusListener(new JTextFieldHintListener(hepatitisBmonth, "MM"));
		hepatitisBmonth.setColumns(10);

		MMRyear = new JTextField();
		MMRyear.setBounds(209, 285, 67, 26);
		panel.add(MMRyear);
		//MMRyear.addFocusListener(new JTextFieldHintListener(MMRyear, "YYYY"));
		MMRyear.setColumns(10);
		MMRmonth = new JTextField();
		MMRmonth.setBounds(300, 285, 50, 26);
		panel.add(MMRmonth);
		//MMRmonth.addFocusListener(new JTextFieldHintListener(MMRmonth, "MM"));
		MMRmonth.setColumns(10);

		Varicellayear = new JTextField();
		Varicellayear.setBounds(209, 330, 67, 26);
		panel.add(Varicellayear);
		//Varicellayear.addFocusListener(new JTextFieldHintListener(Varicellayear, "YYYY"));
		Varicellayear.setColumns(10);
		Varicellamonth = new JTextField();
		Varicellamonth.setBounds(300, 330, 50, 26);
		panel.add(Varicellamonth);
		//Varicellamonth.addFocusListener(new JTextFieldHintListener(Varicellamonth, "MM"));
		Varicellamonth.setColumns(10);

		Tdapyear = new JTextField();
		Tdapyear.setBounds(209, 373, 67, 26);
		panel.add(Tdapyear);
		//Tdapyear.addFocusListener(new JTextFieldHintListener(Tdapyear, "YYYY"));
		Tdapyear.setColumns(10);
		Tdapmonth = new JTextField();
		Tdapmonth.setBounds(300, 373, 50, 26);
		panel.add(Tdapmonth);
		//Tdapmonth.addFocusListener(new JTextFieldHintListener(Tdapmonth, "MM"));
		Tdapmonth.setColumns(10);

		Meningococcalyear = new JTextField();
		Meningococcalyear.setBounds(209, 418, 67, 26);
		panel.add(Meningococcalyear);
		//Meningococcalyear.addFocusListener(new JTextFieldHintListener(Meningococcalyear, "YYYY"));
		Meningococcalyear.setColumns(10);
		Meningococcalmonth = new JTextField();
		Meningococcalmonth.setBounds(300, 418, 50, 26);
		panel.add(Meningococcalmonth);
		//Meningococcalmonth.addFocusListener(new JTextFieldHintListener(Meningococcalmonth, "MM"));
		Meningococcalmonth.setColumns(10);
		
		//Show suggested date

		//Student's information:
		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(523, 95, 79, 16);
		panel.add(lblId);

		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setBounds(523, 155, 79, 16);
		panel.add(lblFirstName);

		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setBounds(523, 195, 79, 16);
		panel.add(lblLastName);

		JLabel lblAge = new JLabel("Age(month):");
		lblAge.setBounds(523, 241, 79, 16);
		panel.add(lblAge);

		idtxt = new JTextField();
		idtxt.setBounds(614, 90, 130, 26);
		panel.add(idtxt);
		idtxt.setColumns(10);
	    idtxt.setText(id);
		idtxt.setEditable(false);

		firsttxt = new JTextField();
		firsttxt.setBounds(614, 150, 130, 26);
		panel.add(firsttxt);
		firsttxt.setColumns(10);
	    firsttxt.setText(firstName);
		firsttxt.setEditable(false);

		lasttxt = new JTextField();
		lasttxt.setBounds(614, 190, 130, 26);
		panel.add(lasttxt);
		lasttxt.setColumns(10);
	    lasttxt.setText(lastName);
		lasttxt.setEditable(false);

		agetxt = new JTextField();
		agetxt.setBounds(614, 236, 130, 26);
		panel.add(agetxt);
		agetxt.setColumns(10);
	    agetxt.setText(age);
		agetxt.setEditable(false);

		
//		updateBtn
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String hibyear, hibmonth;
				String dtapyear, dtapmonth;
				String poyear, pomonth;
				String hbyear,hbmonth;
				String mmryear, mmrmonth;
				String vyear, vmonth;
				String tdapyear, tdapmonth;
				String meyear, memonth;
				
				hibyear = Hibyear.getText();           hibmonth = Hibmonth.getText();
				dtapyear = DTaPyear.getText();         dtapmonth = DTaPmonth.getText();
				poyear = Polioyear.getText();          pomonth = Poliomonth.getText();
				hbyear = hepatitisByear.getText();     hbmonth = hepatitisBmonth.getText();
				mmryear = MMRyear.getText();           mmrmonth = MMRmonth.getText();
				vyear = Varicellayear.getText();       vmonth = Varicellamonth.getText();
				tdapyear = Tdapyear.getText();         tdapmonth = Tdapmonth.getText();
				meyear = Meningococcalyear.getText();  memonth = Meningococcalmonth.getText();
				
				if(!hibyear.equals("") && !hibmonth.equals(""))
				{
					System.out.println("//////////////////////");
					stu.hib.add(Integer.parseInt(hibyear), Integer.parseInt(hibmonth));
				}
					//stu.hib.add(Integer.parseInt(hibyear), Integer.parseInt(hibmonth));
				if(!dtapyear.equals("") && !dtapmonth.equals(""))
					stu.dtap.add(Integer.parseInt(dtapyear), Integer.parseInt(dtapmonth));
				if(!poyear.equals("") && !pomonth.equals(""))
					stu.polio.add(Integer.parseInt(poyear), Integer.parseInt(pomonth));
				if(!hbyear.equals("") && !hbmonth.equals(""))
					stu.hb.add(Integer.parseInt(hbyear), Integer.parseInt(hbmonth));
				if(!mmryear.equals("") && !mmrmonth.equals(""))
					stu.mmr.add(Integer.parseInt(mmryear), Integer.parseInt(mmrmonth));
				if(!vyear.equals("") && !vyear.equals(""))
					stu.varicella.add(Integer.parseInt(vyear), Integer.parseInt(vmonth));
				if(!tdapyear.equals("") && !tdapmonth.equals(""))
					stu.tdap.add(Integer.parseInt(tdapyear), Integer.parseInt(tdapmonth));
				if(!meyear.equals("") && !memonth.equals(""))
					stu.me.add(Integer.parseInt(meyear), Integer.parseInt(memonth));
				
				stu.hib.SetNextDate(AGE);
				stu.dtap.SetNextDate(AGE);
				stu.polio.SetNextDate(AGE);
				stu.hb.SetNextDate(AGE);
				stu.mmr.SetNextDate(AGE);
				stu.varicella.SetNextDate(AGE);
				stu.tdap.SetNextDate(AGE);
				stu.me.SetNextDate(AGE);
				
				System.out.println("***************");
				System.out.println("Hib: " + stu.hib.ShowRecord());
				System.out.println("DTaP: " + stu.dtap.ShowRecord());
				System.out.println("HepatitisB: " + stu.hb.ShowRecord());
				System.out.println("Polio: " + stu.polio.ShowRecord());
				System.out.println("MMR: " + stu.mmr.ShowRecord());
				System.out.println("Varicella: " + stu.varicella.ShowRecord());
				System.out.println("TDaP: " + stu.tdap.ShowRecord());
				System.out.println("Meningococcal: " + stu.me.ShowRecord());
				System.out.println("***************");
				
				//System.out.println(stu.hib.ShownextDate());
				try {
					BufferedWriter IRwriter = new BufferedWriter(new FileWriter(IRfile));
					StringBuilder s = new StringBuilder();
					s.append(stu.getID()+","+stu.getFirstName()+","+stu.getLastName()+","+stu.getAge()+"\n")
					.append("HepatitisB:,"+stu.hb.ShowRecord()+"NextDoseDate:,"+stu.hb.ShownextDate()+"\n")
					.append("DTaP:,"+stu.dtap.ShowRecord()+"NextDoseDate:,"+stu.dtap.ShownextDate()+"\n")
					.append("Hib:,"+stu.hib.ShowRecord()+"NextDoseDate:,"+stu.hib.ShownextDate()+"\n")
					.append("Polio:,"+stu.polio.ShowRecord()+"NextDoseDate:,"+stu.polio.ShownextDate()+"\n")
					.append("MMR:,"+stu.mmr.ShowRecord()+"NextDoseDate:,"+stu.mmr.ShownextDate()+"\n")
					.append("Varicella:,"+stu.varicella.ShowRecord()+"NextDoseDate:,"+stu.varicella.ShownextDate()+"\n")
					.append("TDaP:,"+stu.tdap.ShowRecord()+"NextDoseDate:,"+stu.tdap.ShownextDate()+"\n")
					.append("Meningococcal:,"+stu.me.ShowRecord()+"NextDoseDate:,"+stu.me.ShownextDate()+"\n");
					IRwriter.write(s.toString());
					IRwriter.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				Hibyear.setText("");           Hibmonth.setText("");
				DTaPyear.setText("");          DTaPmonth.setText("");
				Polioyear.setText("");         Poliomonth.setText("");
				hepatitisByear.setText("");    hepatitisBmonth.setText("");
				MMRyear.setText("");           MMRmonth.setText("");
				Varicellayear.setText("");     Varicellamonth.setText("");
				Tdapyear.setText("");          Tdapmonth.setText("");
				Meningococcalyear.setText(""); Meningococcalmonth.setText("");
				Hibtimes.setText(String.valueOf(stu.hib.GetSize()));
				DTaPtimes.setText(String.valueOf(stu.dtap.GetSize()));
				Poliotimes.setText(String.valueOf(stu.polio.GetSize()));
				Heptimes.setText(String.valueOf(stu.hb.GetSize()));
				MMRtimes.setText(String.valueOf(stu.mmr.GetSize()));
				Varicellatimes.setText(String.valueOf(stu.varicella.GetSize()));
				Tdaptimes.setText(String.valueOf(stu.tdap.GetSize()));
				Meningococcaltimes.setText(String.valueOf(stu.me.GetSize()));
				lblHibdose.setText(stu.hib.ShownextDate());
				lblDtapdose.setText(stu.dtap.ShownextDate());
				lblPoliodose.setText(stu.polio.ShownextDate());
				lblHbdose.setText(stu.hb.ShownextDate());
				lblMmrdose.setText(stu.mmr.ShownextDate());
				lblVadose.setText(stu.varicella.ShownextDate());
				lblTdapdose.setText(stu.tdap.ShownextDate());
				lblMedose.setText(stu.me.ShownextDate());
			}
		});
	    btnUpdate.setBounds(553, 419, 130, 29);
	    panel.add(btnUpdate);
	    
	}
}
