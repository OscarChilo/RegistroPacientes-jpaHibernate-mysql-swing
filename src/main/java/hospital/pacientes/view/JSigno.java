package hospital.pacientes.view;

import java.awt.EventQueue;

import hospital.pacientes.*;
import hospital.pacientes.view.*;

import javax.swing.JInternalFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.panayotis.gnuplot.GNUPlotParameters;
import com.panayotis.gnuplot.JavaPlot;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class JSigno extends JInternalFrame {
	public JTextField txtIdpa;
	public JTextField txtTemp;
	public JTextField txtSatu;
	public JTextField txtBuscar;
	private JTable tableSigns;
	
	static EntityManagerFactory factory;
	static EntityManager entityManager;;
	
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JSigno frame = new JSigno();
					//frame.setMaximizable(true);
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private static void begin() {
		factory = Persistence.createEntityManagerFactory("HospitalUnit");
		entityManager = factory.createEntityManager();

		entityManager.getTransaction().begin();
	}
	private static void end() {
		entityManager.getTransaction().commit();
		entityManager.close();
		factory.close();
	}
	private void limpiar() {
		txtIdpa.setText("");
		txtTemp.setText("");
		txtSatu.setText("");
		txtBuscar.setText("");
		txtTemp.requestFocus();
		
	}
	public void listBuscar(JTable tabla,int clave){
			
		DefaultTableModel model = new DefaultTableModel(null, new Object[] {"NRO","ID_SIGNOS","ID_PACIENTE","APELLIDOS","SATURACION","TEMPERATURA","FECHA"});
		
	
		String jpql = "SELECT s FROM Signo s WHERE s.idPaciente LIKE :idpaciente";
		Query query = entityManager.createQuery(jpql).setParameter("idpaciente", clave);
	
		@SuppressWarnings("unchecked")
		List<Signo> listSignos = query.getResultList();
	
		int count=1;
		for (Signo s : listSignos) {
			model.addRow(new Object[] {count,s.getIdSigno(),s.getIdPaciente(),s.getApellidos(),s.getSaturacion(),s.getTemperatura(),s.getFecha()});
			count++;
		}
		tabla.setModel(model);
	
		}
		
	public void listarTabla(JTable tabla) {
	
		DefaultTableModel model = new DefaultTableModel(null, new Object[] {"ID_SIGNOS","ID_PACIENTE","APELLIDOS","SATURACION","TEMPERATURA","FECHA"});
		
	
		String jpql = "SELECT p FROM Signo p";
		Query query = entityManager.createQuery(jpql);
	
		@SuppressWarnings("unchecked")
		List<Signo> listSignos = query.getResultList();
	
		
		for (Signo s : listSignos) {
			model.addRow(new Object[] {s.getIdSigno(),s.getIdPaciente(),s.getApellidos(),s.getSaturacion(),s.getTemperatura(),s.getFecha()});
		}
		tabla.setModel(model);
	
	}
	

	/**
	 * Create the frame.
	 */
	public JSigno() {
		//setMaximum(true);
		
		setClosable(true);
		//setMaximizable(true);
		setIconifiable(true);
		setBounds(100, 100, 930, 516);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Registro", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(21, 73, 260, 252);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("ID Paciente");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel_1.setBounds(23, 41, 55, 14);
		panel.add(lblNewLabel_1);
		
		txtIdpa = new JTextField();
		txtIdpa.setBounds(102, 38, 92, 20);
		panel.add(txtIdpa);
		txtIdpa.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Temperatura");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel_2.setBounds(23, 158, 62, 14);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Saturacion");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel_3.setBounds(23, 98, 51, 14);
		panel.add(lblNewLabel_3);
		
		txtTemp = new JTextField();
		txtTemp.setBounds(102, 155, 92, 20);
		panel.add(txtTemp);
		txtTemp.setColumns(10);
		
		txtSatu = new JTextField();
		txtSatu.setBounds(102, 95, 92, 20);
		panel.add(txtSatu);
		txtSatu.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("C\u00B0");
		lblNewLabel_4.setBounds(204, 158, 46, 14);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("%");
		lblNewLabel_5.setBounds(204, 98, 46, 14);
		panel.add(lblNewLabel_5);
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiar();
			}
		});
		btnLimpiar.setBounds(93, 205, 89, 23);
		panel.add(btnLimpiar);
		
		JLabel lblNewLabel = new JLabel("SIGNOS VITALES");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel.setBounds(391, 25, 146, 21);
		getContentPane().add(lblNewLabel);
		
		JButton btnGuardar = new JButton("GUARDAR");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int  temp,idPa;
				double satu;
				
				Paciente paciente=new Paciente();
				Signo newSigno=new Signo();
				
				Date fecha=new Date();
				
				begin();
				idPa=Integer.parseInt(txtIdpa.getText());
				paciente=entityManager.find(Paciente.class, idPa);
				String apellido=paciente.getApellidos();
				temp=Integer.parseInt(txtTemp.getText());
				satu=Double.parseDouble(txtSatu.getText());
				
				newSigno.setIdPaciente(idPa);
				newSigno.setApellidos(apellido);
				newSigno.setSaturacion(satu);
				newSigno.setTemperatura(temp);
				newSigno.setFecha(fecha);
				
				entityManager.persist(newSigno);
				
				System.out.println(fecha);
				end();
				JOptionPane.showMessageDialog(null, "Signos vitales NUEVO REGISTRADO con EXITO!!");
				limpiar();
				
				
				
			}
		});
		btnGuardar.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnGuardar.setBounds(115, 336, 89, 23);
		getContentPane().add(btnGuardar);
		
		JLabel lblNewLabel_6 = new JLabel("ID Paciente");
		lblNewLabel_6.setBounds(30, 383, 55, 14);
		getContentPane().add(lblNewLabel_6);
		
		txtBuscar = new JTextField();
		txtBuscar.setBounds(95, 380, 97, 20);
		getContentPane().add(txtBuscar);
		txtBuscar.setColumns(10);
		
		JButton btnBuscar = new JButton("BUSCAR");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int idbuscar=Integer.parseInt(txtBuscar.getText());
				begin();
				listBuscar(tableSigns,idbuscar);
			
				String jpqls = "SELECT s.saturacion FROM Signo s WHERE s.idPaciente LIKE :idpaciente";
				String jpqlt = "SELECT s.temperatura FROM Signo s WHERE s.idPaciente LIKE :idpaciente";
				Query querys = entityManager.createQuery(jpqls).setParameter("idpaciente", idbuscar);
				Query queryt = entityManager.createQuery(jpqlt).setParameter("idpaciente", idbuscar);
			
				@SuppressWarnings("unchecked")
				List<Signo> listTemperatura = queryt.getResultList();
				@SuppressWarnings("unchecked")
				List<Signo> listSaturacion = querys.getResultList();
				
				for (int p=0 ;p<listTemperatura.size();p++) {
					System.out.println((p+1)+" "+listTemperatura.get(p)+"\n");
				}
				
				
				//GRAFICANDO EN GNUPLOT
				
				// txtIdpa
				String id = txtBuscar.getText(); // Llama txtBuscar para graficar el id en el HashMap
				
				try {
					File temperatura = null;
					File saturacion = null;
					BufferedWriter bw = null;
					BufferedWriter bw2 = null;
					temperatura = new File("temperatura.txt");
					saturacion = new File("saturacion.txt");
					temperatura.createNewFile();
					saturacion.createNewFile();
					bw = new BufferedWriter(new FileWriter(temperatura));
					bw2 = new BufferedWriter(new FileWriter(saturacion));
					
					int cont = 1;
					for(int  i =0; i<listTemperatura.size();i++) {
						bw.write((i+1) + " " + listTemperatura.get(i) +"\t"); // tiempo x temperatura
						bw.newLine();
						cont++;
					}
					
					for(int  j =0; j<listSaturacion.size();j++) {
						bw2.write((j+1) + " " + listSaturacion.get(j) +"\t"); // tiempo x temperatura
						bw2.newLine();
						cont++;
					}
					
					bw.close();
					bw2.close();
					//Desktop.getDesktop().open(gnu);
					//Desktop.getDesktop().open(gnu2);
					JavaPlot p = new JavaPlot();
					
					GNUPlotParameters params=p.getParameters();
					p.setTitle("Signos Vitales");
					params.set("xlabel 'FECHAS'");
					params.set("ylabel 'TEMPERATURA   Y   SATURACION'");

			        p.addPlot("\"temperatura.txt\" with lines");
			        p.addPlot("\"saturacion.txt\" with lines");
			        p.plot();
					
					
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				//JOptionPane.showMessageDialog(null, "Graficado con EXITO!!");
				limpiar();
							
				
				end();
			}
		});
		btnBuscar.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnBuscar.setBounds(201, 379, 73, 23);
		getContentPane().add(btnBuscar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(312, 83, 592, 276);
		getContentPane().add(scrollPane);
		
		tableSigns = new JTable();
		scrollPane.setViewportView(tableSigns);
		
		JButton btnMostrar = new JButton("MOSTRAR TABLA");
		btnMostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				begin();
				listarTabla(tableSigns);
				end();
			}
		});
		btnMostrar.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnMostrar.setBounds(533, 373, 131, 34);
		getContentPane().add(btnMostrar);

	}

}
