import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class FilmCommentApp {

    private static List<Film> films = new ArrayList<>();
    private static List<Comment> comments = new ArrayList<>();

    public static void main(String[] args) {
        // Initialize some films
        films.add(new Film("Titanic", "Romantic", 8.5, 5400));
        films.add(new Film("Look Up", "Animation", 7.0, 1200));
        films.add(new Film("Inception", "Sci-Fi", 9.0, 6500));

        // Create frame
        JFrame frame = new JFrame("Film Comment Platform");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        // Film list panel
        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BorderLayout());

        JLabel filmListLabel = new JLabel("Available Films:");
        DefaultListModel<String> filmListModel = new DefaultListModel<>();
        for (Film film : films) {
            filmListModel.addElement(film.name + " (" + film.type + ")");
        }
        JList<String> filmList = new JList<>(filmListModel);
        JScrollPane filmListScroll = new JScrollPane(filmList);


        listPanel.add(filmListLabel, BorderLayout.NORTH);
        listPanel.add(filmListScroll, BorderLayout.CENTER);

        // Form panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(5, 2, 5, 5));

        JLabel nameLabel = new JLabel("  Audience Name:");
        JTextField nameField = new JTextField();

        JLabel mailLabel = new JLabel("  Audience Mail:");  //1
        JTextField mailField = new JTextField();  //

        JLabel selectedFilmLabel = new JLabel("Selected Film:");
        JTextField selectedFilmField = new JTextField();

        JLabel commentLabel = new JLabel("  Comment:");
        JTextField commentField = new JTextField();

        JButton submitButton = new JButton("  Submit");

        formPanel.add(nameLabel);
        formPanel.add(nameField);
        formPanel.add(mailLabel);  //1
        formPanel.add(mailField);   //burası
        formPanel.add(selectedFilmLabel); //
        formPanel.add(selectedFilmField); // selected film
        formPanel.add(commentLabel);
        formPanel.add(commentField);
        formPanel.add(new JLabel()); // Empty cell
        formPanel.add(submitButton);


        // Table panel
        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BorderLayout());

        JLabel tableLabel = new JLabel("All Comments:");
        DefaultTableModel tableModel = new DefaultTableModel(new String[]{"Audience Name", "Film", "Comment"}, 0);
        JTable commentTable = new JTable(tableModel);
        JScrollPane tableScroll = new JScrollPane(commentTable);

        tablePanel.add(tableLabel, BorderLayout.NORTH);
        tablePanel.add(tableScroll, BorderLayout.CENTER);

        // Add panels to frame
        frame.add(listPanel, BorderLayout.WEST);
        frame.add(formPanel, BorderLayout.CENTER);
        frame.add(tablePanel, BorderLayout.SOUTH);

        // Film listesini dinlemek için bir ListSelectionListener ekliyoruz
        filmList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) { // Kullanıcı seçimi tamamladıysa
                String selectedFilm = filmList.getSelectedValue(); // Seçilen film
                selectedFilmField.setText( selectedFilm );
            }
        });


        // ActionListener for submit button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String mail = mailField.getText();

                boolean isMailValid = MailFormatControl.isFormatAccepted(mail);
                if (!isMailValid){
                    JOptionPane.showMessageDialog(frame, "Please enter a valid mail!");
                    return;
                }
                String commentText = commentField.getText();
                int selectedFilmIndex = filmList.getSelectedIndex();
                String selectedFilmValue = filmList.getSelectedValue();


                if (selectedFilmIndex == -1) {
                    JOptionPane.showMessageDialog(frame, "Please select a film from the list!");
                    return;
                }

                if (name.isEmpty() || commentText.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please fill in all fields!");
                    return;
                }

                // Create objects
                Audience audience = new Audience(name, mail);
                Film selectedFilm = films.get(selectedFilmIndex);
                Comment comment = new Comment(audience, selectedFilm, commentText);

                //fill selectedFilmField
                if(commentTable.getSelectedColumn() > 0)
                    JOptionPane.showMessageDialog(frame, selectedFilm.name);
                    //selectedFilmField.setText(selectedFilm.name);

                // Add comment to list and table
                comments.add(comment);
                tableModel.addRow(new Object[]{audience.name, selectedFilm.name, commentText});

                // Clear fields
                nameField.setText("");
                mailField.setText("");
                selectedFilmField.setText("");
                commentField.setText("");

                // Show confirmation
                JOptionPane.showMessageDialog(frame, "Comment added successfully for the film: " + selectedFilm.name);
            }
        });

        // Show frame
        frame.setVisible(true);
    }
}
