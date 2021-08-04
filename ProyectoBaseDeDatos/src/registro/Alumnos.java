package registro;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTable;
import java.awt.Panel;
import java.awt.Label;
import javax.swing.JTextPane;
import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

public class Alumnos extends JFrame {

	ButtonGroup btnGr;
	
	private JPanel contentPane;
	private JTable tblAlumnos;
	private JTextField txtMatricula;
	private JTextField txtNombre;
	private JTextField txtEdad;
	private JTextField txtEmail;
	private JTextField textId;
	private JRadioButton rbMaculino;
	private JRadioButton rbFemenino;
	private JPanel panel;
	private JButton btnGuardar;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblEdad;
	private JLabel lblSexo;
	private JLabel lblNewLabel_3;
	private JButton btnModificar;
	private JButton btnEliminar;
	private JButton btnLimpiar;
	/**
	 * Launch the application.
	 */
	
	private void limpiar() {
		textId.setText("");
		txtMatricula.setText("");
		txtNombre.setText("");
		txtEdad.setText("");
		txtEmail.setText("");
		btnGr.clearSelection();
	}

	private void cargarTabla() {
		
		DefaultTableModel modeloTabla = (DefaultTableModel) tblAlumnos.getModel();
		modeloTabla.setRowCount(0);
		
		PreparedStatement ps;
		ResultSet rs;
		ResultSetMetaData rsmd;
		int columnas;
		
		int[] anchos = {10,50,100,30,100};
		for(int i=0; i<tblAlumnos.getColumnCount();i++) {
			tblAlumnos.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
		}
		
		try {
			Connection con = Conexion.getConexion();
			ps = con.prepareStatement("SELECT id, matricula, nombre, sexo, email FROM alumnos");
			rs = ps.executeQuery();
			rsmd = (ResultSetMetaData) rs.getMetaData();
			columnas = rsmd.getColumnCount();
			
			while(rs.next()) {
				Object[] fila = new Object[columnas];
				for(int indice=0; indice<columnas;indice++) {
					fila[indice]=rs.getObject(indice+1);
				}
				modeloTabla.addRow(fila);
			}
			
		}catch(SQLException e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}
	}

	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Alumnos frame = new Alumnos();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Alumnos() {

		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 729, 465);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("REGISTRO DE ALUMNOS");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		panel = new JPanel();
		panel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.setBackground(Color.WHITE);
		panel.setBorder(new TitledBorder(null, "Datos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setLayout(null);
		
		lblNewLabel_1 = new JLabel("Matricula:");
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblNewLabel_1.setBounds(10, 33, 67, 14);
		panel.add(lblNewLabel_1);
		
		txtMatricula = new JTextField();
		txtMatricula.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		txtMatricula.setBounds(87, 29, 189, 23);
		panel.add(txtMatricula);
		txtMatricula.setColumns(10);
		
		txtNombre = new JTextField();
		txtNombre.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		txtNombre.setBounds(87, 66, 189, 23);
		panel.add(txtNombre);
		txtNombre.setColumns(10);
		
		lblNewLabel_2 = new JLabel("Nombre:");
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblNewLabel_2.setBounds(10, 70, 67, 14);
		panel.add(lblNewLabel_2);
		
		lblEdad = new JLabel("Edad:");
		lblEdad.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblEdad.setBounds(10, 111, 46, 14);
		panel.add(lblEdad);
		
		txtEdad = new JTextField();
		txtEdad.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		txtEdad.setBounds(87, 107, 189, 23);
		panel.add(txtEdad);
		txtEdad.setColumns(10);
		
		rbMaculino = new JRadioButton("Masculino");
		rbMaculino.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		rbMaculino.setBackground(Color.WHITE);
		rbMaculino.setBounds(87, 141, 87, 23);
		panel.add(rbMaculino);
		
		rbFemenino = new JRadioButton("Femenino");
		rbFemenino.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		rbFemenino.setBackground(Color.WHITE);
		rbFemenino.setBounds(199, 141, 100, 23);
		panel.add(rbFemenino);
		
		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		txtEmail.setBounds(87, 184, 189, 23);
		panel.add(txtEmail);
		txtEmail.setColumns(10);
		
		lblNewLabel_3 = new JLabel("Email:");
		lblNewLabel_3.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblNewLabel_3.setBounds(10, 187, 46, 14);
		panel.add(lblNewLabel_3);
		
		lblSexo = new JLabel("Sexo:");
		lblSexo.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblSexo.setBounds(10, 148, 46, 14);
		panel.add(lblSexo);
		
		btnGuardar = new JButton("");
		btnGuardar.setBorder(null);
		btnGuardar.setPressedIcon(new ImageIcon("C:\\Users\\Administrador\\eclipse-workspace\\ProyectoBaseDeDatos\\src\\iconos\\btnagregar3.png"));
		btnGuardar.setRolloverIcon(new ImageIcon("C:\\Users\\Administrador\\eclipse-workspace\\ProyectoBaseDeDatos\\src\\iconos\\btnagregar2.png"));
		btnGuardar.setFocusPainted(false);
		btnGuardar.setBackground(Color.WHITE);
		btnGuardar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnGuardar.setIcon(new ImageIcon("C:\\Users\\Administrador\\eclipse-workspace\\ProyectoBaseDeDatos\\src\\iconos\\btnagregar.png"));
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String matricula = txtMatricula.getText();
				String nombre = txtNombre.getText();
				int edad = Integer.parseInt(txtEdad.getText());
				String email = txtEmail.getText();
				String sexo;
				
				if(rbMaculino.isSelected()==true) {
					sexo = "M";
				} else if (rbFemenino.isSelected()==true) {
					sexo = "F";
				} else {
					sexo = "M";
				}
				
				try {
					Connection con = Conexion.getConexion();
					PreparedStatement ps = con.prepareStatement("INSERT INTO alumnos (matricula , nombre, edad, sexo, email,activo) VALUES (?,?,?,?,?,?)");
					ps.setString(1, matricula);
					ps.setString(2, nombre);
					ps.setInt(3, edad);
					ps.setString(4, sexo);
					ps.setString(5, email);
					ps.setInt(6, 1);
					ps.executeUpdate();
					JOptionPane.showMessageDialog(null, "Registro guardado");
					limpiar();
					cargarTabla();
					
				}catch(SQLException e1) {
					JOptionPane.showMessageDialog(null, e1.toString());
				}
			}
		});
		btnGuardar.setBounds(44, 242, 87, 39);
		panel.add(btnGuardar);
		
		btnModificar = new JButton("");
		btnModificar.setBackground(Color.WHITE);
		btnModificar.setBorder(null);
		btnModificar.setFocusPainted(false);
		btnModificar.setRolloverIcon(new ImageIcon("C:\\Users\\Administrador\\eclipse-workspace\\ProyectoBaseDeDatos\\src\\iconos\\btneditar2.png"));
		btnModificar.setPressedIcon(new ImageIcon("C:\\Users\\Administrador\\eclipse-workspace\\ProyectoBaseDeDatos\\src\\iconos\\btneditar3.png"));
		btnModificar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnModificar.setIcon(new ImageIcon("C:\\Users\\Administrador\\eclipse-workspace\\ProyectoBaseDeDatos\\src\\iconos\\btneditar.png"));
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int id = Integer.parseInt(textId.getText());
				String matricula = txtMatricula.getText();
				String nombre = txtNombre.getText();
				int edad = Integer.parseInt(txtEdad.getText());
				String email = txtEmail.getText();
				String sexo;
				
				if(rbMaculino.isSelected()==true) {
					sexo = "M";
				} else if (rbFemenino.isSelected()==true) {
					sexo = "F";
				} else {
					sexo = "M";
				}
				
				try {
					Connection con = Conexion.getConexion();
					PreparedStatement ps = con.prepareStatement("UPDATE alumnos SET matricula=? , nombre=?, edad=?, sexo=?, email=? WHERE id=?");
					ps.setString(1, matricula);
					ps.setString(2, nombre);
					ps.setInt(3, edad);
					ps.setString(4, sexo);
					ps.setString(5, email);
					ps.setInt(6, id);
					ps.executeUpdate();
					JOptionPane.showMessageDialog(null, "Registro modificado");
					limpiar();
					cargarTabla();
					
				}catch(SQLException e1) {
					JOptionPane.showMessageDialog(null, e1.toString());
				
			}
		}});
		btnModificar.setBounds(166, 242, 87, 39);
		panel.add(btnModificar);
		
		btnEliminar = new JButton("");
		btnEliminar.setBorderPainted(false);
		btnEliminar.setBackground(Color.WHITE);
		btnEliminar.setFocusPainted(false);
		btnEliminar.setBorder(null);
		btnEliminar.setPressedIcon(new ImageIcon("C:\\Users\\Administrador\\eclipse-workspace\\ProyectoBaseDeDatos\\src\\iconos\\btnborrar3.png"));
		btnEliminar.setRolloverIcon(new ImageIcon("C:\\Users\\Administrador\\eclipse-workspace\\ProyectoBaseDeDatos\\src\\iconos\\btnborrar2.png"));
		btnEliminar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnEliminar.setIcon(new ImageIcon("C:\\Users\\Administrador\\eclipse-workspace\\ProyectoBaseDeDatos\\src\\iconos\\btnborrar.png"));
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int id = Integer.parseInt(textId.getText());
				
				try {
					Connection con = Conexion.getConexion();
					PreparedStatement ps = con.prepareStatement("DELETE FROM alumnos WHERE id=?");
					ps.setInt(1, id);
					ps.executeUpdate();
					JOptionPane.showMessageDialog(null, "Registro eliminado");
					limpiar();
					cargarTabla();
					
				}catch(SQLException e1) {
					JOptionPane.showMessageDialog(null, e1.toString());
				
			}
			}
		});
		btnEliminar.setBounds(44, 308, 87, 39);
		panel.add(btnEliminar);
		
		btnLimpiar = new JButton("");
		btnLimpiar.setBackground(Color.WHITE);
		btnLimpiar.setBorder(null);
		btnLimpiar.setFocusPainted(false);
		btnLimpiar.setRolloverIcon(new ImageIcon("C:\\Users\\Administrador\\eclipse-workspace\\ProyectoBaseDeDatos\\src\\iconos\\btnlimpiar2.png"));
		btnLimpiar.setPressedIcon(new ImageIcon("C:\\Users\\Administrador\\eclipse-workspace\\ProyectoBaseDeDatos\\src\\iconos\\btnlimpiar3.png"));
		btnLimpiar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnLimpiar.setIcon(new ImageIcon("C:\\Users\\Administrador\\eclipse-workspace\\ProyectoBaseDeDatos\\src\\iconos\\btnlimpiar.png"));
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiar();
			}
		});
		btnLimpiar.setBounds(166, 308, 87, 39);
		panel.add(btnLimpiar);
		
		textId = new JTextField();
		textId.setBounds(269, 27, 23, 17);
		panel.add(textId);
		textId.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(null);
		scrollPane.setBorder(null);
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setForeground(Color.BLACK);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 375, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 299, GroupLayout.PREFERRED_SIZE)
					.addGap(11))
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(250, Short.MAX_VALUE)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 252, GroupLayout.PREFERRED_SIZE)
					.addGap(201))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 369, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		
		tblAlumnos = new JTable();
		tblAlumnos.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tblAlumnos.setBorder(null);
		tblAlumnos.setSelectionForeground(Color.BLACK);
		tblAlumnos.setSelectionBackground(new Color(245, 245, 220));
		tblAlumnos.setGridColor(Color.GRAY);
		tblAlumnos.setBackground(Color.WHITE);
		tblAlumnos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					int fila = tblAlumnos.getSelectedRow();
					int id = Integer.parseInt(tblAlumnos.getValueAt(fila,0).toString());
					PreparedStatement ps;
					ResultSet rs;
					Connection con = Conexion.getConexion();
					ps = con.prepareStatement("SELECT id, matricula, nombre, edad, sexo, email FROM alumnos WHERE id=?");
					ps.setInt(1, id);
					rs = ps.executeQuery();
					
					while(rs.next()) {
						textId.setText(String.valueOf(id));
						txtMatricula.setText(rs.getString("matricula"));
						txtNombre.setText(rs.getString("nombre"));
						txtEdad.setText(rs.getString("edad"));
						txtEmail.setText(rs.getString("email"));
						if(rs.getString("sexo").equals("M")) {
							rbMaculino.setSelected(true);
						}else if(rs.getString("sexo").equals("F")) {
							rbFemenino.setSelected(true);
						}
					}
				}catch(SQLException e1){
					JOptionPane.showMessageDialog(null,  e1.toString());
				}
			}
		});
		scrollPane.setViewportView(tblAlumnos);
		tblAlumnos.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Id", "Matricula", "Nombre", "Sexo", "Email"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		contentPane.setLayout(gl_contentPane);
	
		textId.setVisible(false);
		btnGr = new ButtonGroup();
		btnGr.add(rbMaculino);
		btnGr.add(rbFemenino);
		cargarTabla();
	}
	public JRadioButton getRbMaculino() {
		return rbMaculino;
	}
	public JRadioButton getRbFemenino() {
		return rbFemenino;
	}
	public JPanel getPanel() {
		return panel;
	}
	public JButton getBtnGuardar() {
		return btnGuardar;
	}
	public JTable getTblAlumnos() {
		return tblAlumnos;
	}
	public JLabel getLblNewLabel_1() {
		return lblNewLabel_1;
	}
	public JTextField getTxtMatricula() {
		return txtMatricula;
	}
	public JTextField getTxtNombre() {
		return txtNombre;
	}
	public JLabel getLblNewLabel_2() {
		return lblNewLabel_2;
	}
	public JLabel getLblEdad() {
		return lblEdad;
	}
	public JTextField getTxtEdad() {
		return txtEdad;
	}
	public JTextField getTxtEmail() {
		return txtEmail;
	}
	public JLabel getLblSexo() {
		return lblSexo;
	}
	public JLabel getLblNewLabel_3() {
		return lblNewLabel_3;
	}
	public JButton getBtnModificar() {
		return btnModificar;
	}
	public JButton getBtnEliminar() {
		return btnEliminar;
	}
	public JButton getBtnLimpiar() {
		return btnLimpiar;
	}
}


