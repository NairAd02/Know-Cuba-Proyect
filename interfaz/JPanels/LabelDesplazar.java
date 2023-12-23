package JPanels;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import javax.swing.border.LineBorder;

import JFrames.FramePaquetes;

import java.awt.Color;

public class LabelDesplazar extends JLabel {

	private static final long serialVersionUID = 1L;
	private int number;
	/**
	 * Create the panel.
	 */
	public LabelDesplazar(int n) {
		this.number = n;
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setFont(new Font("Tahoma", Font.PLAIN, 14));
		setText(String.valueOf(this.number));
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				FramePaquetes.getInstancie().ubicarPanelPaquetes(number - 1);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			@Override
			public void mouseExited(MouseEvent e) {
			}
		});

	}

}
