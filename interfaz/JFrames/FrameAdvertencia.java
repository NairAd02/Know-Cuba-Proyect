package JFrames;


import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;
import java.awt.Font;

public class FrameAdvertencia extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	boolean tiempoTranscurrido = false;
	Timer timer;
	private JLabel lblMensaje;

	public FrameAdvertencia(String mensaje) {
		setUndecorated(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle(mensaje);
		setBounds(100, 100, 355, 90);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(5, 150, 177));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		/*JPanel panel = new JPanel();
		panel.setBackground(getForeground());*/

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(FrameAdvertencia.class.getResource("/images/WhatsApp Image 2023-11-14 at 8.59.57 PM - copia - copia.jpeg")));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 0, 79, 90);
		contentPane.add(lblNewLabel);

		lblMensaje = new JLabel(mensaje);
		lblMensaje.setFont(new Font("Arial", Font.PLAIN, 14));
		lblMensaje.setBounds(99, 30, 246, 14);
		contentPane.add(lblMensaje);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblNewLabel_2.setBounds(0, 0, 355, 90);
		contentPane.add(lblNewLabel_2);

		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Rectangle bounds = ge.getMaximumWindowBounds();
		setLocation(bounds.width - getWidth() - 10, (bounds.height - getHeight())- 10);

		setOpacity(1);


		timer = new Timer(100, new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {


				float opacity = getOpacity();
				if(opacity > 0){
					opacity -= 0.1;
					if(opacity < 0){
						opacity = 0;
					}
					setOpacity(opacity);
				} else{
					dispose();
					((Timer)e.getSource()).stop();
				}	
			}
		});
		timer.setInitialDelay(3000);
		timer.start();
	}
}
