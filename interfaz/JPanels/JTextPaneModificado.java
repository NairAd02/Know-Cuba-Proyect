package JPanels;

import java.awt.Rectangle;

import javax.swing.JTextPane;
import javax.swing.text.DefaultCaret;



public class JTextPaneModificado extends JTextPane {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JTextPaneModificado(){
		setCaret(new MyCaret());
	}
	
	private class MyCaret extends DefaultCaret{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		protected void adjustVisibility(Rectangle ncloc){
			
		}
	}


}
