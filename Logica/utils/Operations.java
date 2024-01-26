package utils;

import java.text.NumberFormat;

import javax.swing.JFormattedTextField;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JFormattedTextField.AbstractFormatterFactory;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.text.NumberFormatter;

public class Operations {
	public static boolean esNumero(String str) {
	    return str.matches("\\d+");
	}

	
	public static void crearJSpinnerNumericoInteger(JSpinner spinner, SpinnerNumberModel modelo ) {
	    
	    spinner.setModel(modelo);
	    JFormattedTextField tf = ((JSpinner.DefaultEditor) spinner.getEditor()).getTextField();
	    tf.setFormatterFactory(new AbstractFormatterFactory() {
	        @Override
	        public AbstractFormatter getFormatter(JFormattedTextField tf) {
	            NumberFormat format = NumberFormat.getIntegerInstance();
	            format.setGroupingUsed(false);
	            NumberFormatter formatter = new NumberFormatter(format);
	            formatter.setValueClass(Integer.class);
	            formatter.setAllowsInvalid(false);
	            formatter.setCommitsOnValidEdit(true);
	            return formatter;
	        }
	    });
	}
	
public static void crearJSpinnerNumericoDouble(JSpinner spinner, SpinnerNumberModel modelo ) {
	    
	    spinner.setModel(modelo);
	    JFormattedTextField tf = ((JSpinner.DefaultEditor) spinner.getEditor()).getTextField();
	    tf.setFormatterFactory(new AbstractFormatterFactory() {
	        @Override
	        public AbstractFormatter getFormatter(JFormattedTextField tf) {
	            NumberFormat format = NumberFormat.getIntegerInstance();
	            format.setGroupingUsed(false);
	            NumberFormatter formatter = new NumberFormatter(format);
	            formatter.setValueClass(Double.class);
	            formatter.setAllowsInvalid(false);
	            formatter.setCommitsOnValidEdit(true);
	            return formatter;
	        }
	    });
	}
}
