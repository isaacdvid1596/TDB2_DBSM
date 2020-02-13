
package dbms;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;


/**
 *
 * @author JulioD
 */
public class DBMS extends JApplet {

    
    public static void main(String[] args) {
        // TODO code application logic here
        login logscr = new login();
        logscr.setVisible(true);
        
    }
    
}
