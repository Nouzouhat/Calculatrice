import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculatrice extends JFrame implements ActionListener {

    private JTextField txtEcran;
    private JButton[] boutonsChiffres = new JButton[10];
    private JButton btPlus, btMoins, btMult, btDiv, btPoint, btEgal, btEffacer, btEffacerTout;

    private double num1, num2, resultat;
    private char operateur;

    public Calculatrice() {
        this.titreFenetre();
        this.initialiserInterfaceGraphique();
    }

    private void titreFenetre() {
        this.setTitle("Calculatrice_Windows");
        this.setSize(400, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.getContentPane().setBackground(Color.gray);
        this.setLayout(new BorderLayout());
    }

    private void initialiserInterfaceGraphique() {
        // Barre de texte
        this.txtEcran = new JTextField();
        this.txtEcran.setFont(new Font("Arial", Font.PLAIN, 30));
        this.txtEcran.setHorizontalAlignment(JTextField.RIGHT);
        this.txtEcran.setEditable(false);
        this.add(this.txtEcran, BorderLayout.NORTH);

        // Panneau de boutons
        JPanel panneauBoutons = new JPanel();
        panneauBoutons.setLayout(new GridLayout(5, 4, 10, 10));

        // Initialisation des boutons numériques
        for (int i = 0; i < 10; i++) {
            this.boutonsChiffres[i] = new JButton(String.valueOf(i));
            this.boutonsChiffres[i].setFont(new Font("Arial", Font.PLAIN, 24));
            this.boutonsChiffres[i].addActionListener(this);
            panneauBoutons.add(this.boutonsChiffres[i]);
        }

        // Initialisation des boutons d'opérations
        this.btPlus = new JButton("+");
        this.btMoins = new JButton("-");
        this.btMult = new JButton("*");
        this.btDiv = new JButton("/");
        this.btPoint = new JButton(".");
        this.btEgal = new JButton("=");
        this.btEffacer = new JButton("C");
        this.btEffacerTout = new JButton("CE");

        JButton[] boutonsOperations = {this.btPlus, this.btMoins, this.btMult, this.btDiv, this.btPoint, this.btEgal, this.btEffacer, this.btEffacerTout};

        for (JButton bouton : boutonsOperations) {
            bouton.setFont(new Font("Arial", Font.PLAIN, 24));
            bouton.addActionListener(this);
            panneauBoutons.add(bouton);
        }

        this.add(panneauBoutons, BorderLayout.CENTER);

        // Rendre la fenêtre visible
        this.setVisible(true);
    } 
    
    public static void main(String[] args) {
        // instanciation de la classe calculatrice 
    	Calculatrice unCalculatrice = new Calculatrice ();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Gestion des événements
        for (int i = 0; i < 10; i++) {
            if (e.getSource() == this.boutonsChiffres[i]) {
                this.txtEcran.setText(this.txtEcran.getText() + i);
            }
        }

        if (e.getSource() == this.btPoint) {
            this.txtEcran.setText(this.txtEcran.getText() + ".");
        }

        // Gestion des opérations
        if (e.getSource() == this.btPlus || e.getSource() == this.btMoins || e.getSource() == this.btMult || e.getSource() == this.btDiv) {
            this.num1 = Double.parseDouble(this.txtEcran.getText());
            this.operateur = e.getActionCommand().charAt(0);
            this.txtEcran.setText("");
        }

        // Gestion du bouton égal
        if (e.getSource() == this.btEgal) {
            this.num2 = Double.parseDouble(this.txtEcran.getText());
            this.calculerResultat();
        }

        // Gestion du bouton CE (pour tout supprimer )
        if (e.getSource() == this.btEffacerTout) {
            this.txtEcran.setText("");
            this.num1 = this.num2 = this.resultat = 0;
        }

        // Gestion du bouton C (Supprimer un par un )
        if (e.getSource() == this.btEffacer) {
            String texteActuel = this.txtEcran.getText();
            if (texteActuel.length() > 0) {
                this.txtEcran.setText(texteActuel.substring(0, texteActuel.length() - 1));
            }
        }
    }

    private void calculerResultat() {
        // Calculer le résultat en fonction de l'opérateur choisi
        switch (this.operateur) {
            case '+':
                this.resultat = this.num1 + this.num2;
                break;
            case '-':
                this.resultat = this.num1 - this.num2;
                break;
            case '*':
                this.resultat = this.num1 * this.num2;
                break;
            case '/':
                if (this.num2 != 0) {
                    this.resultat = this.num1 / this.num2;
                } else {
                    this.txtEcran.setText("Erreur");
                    return;
                } 
                break;
        }

        // Affichage du résultat
        this.txtEcran.setText(String.valueOf(this.resultat));
        this.num1 = this.resultat;
    }
}


