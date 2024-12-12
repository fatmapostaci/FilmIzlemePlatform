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

        JLabel nameLabel = new JLabel("Audience Name:");
        JTextField nameField = new JTextField();

        JLabel ageLabel = new JLabel("Audience Age:");
        JTextField ageField = new JTextField();

        JLabel commentLabel = new JLabel("Comment:");
        JTextField commentField = new JTextField();

        JButton submitButton = new JButton("Submit");

        formPanel.add(nameLabel);
        formPanel.add(nameField);
        formPanel.add(ageLabel);
        formPanel.add(ageField);
        formPanel.add(new JLabel("Selected Film:")); // Placeholder for film selection
        formPanel.add(new JLabel()); // Empty
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

        // ActionListener for submit button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                int age;
                try {
                    age = Integer.parseInt(ageField.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Please enter a valid age!");
                    return;
                }
                String commentText = commentField.getText();
                int selectedFilmIndex = filmList.getSelectedIndex();

                if (selectedFilmIndex == -1) {
                    JOptionPane.showMessageDialog(frame, "Please select a film from the list!");
                    return;
                }

                if (name.isEmpty() || commentText.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please fill in all fields!");
                    return;
                }

                // Create objects
                Audience audience = new Audience(name, age);
                Film selectedFilm = films.get(selectedFilmIndex);
                Comment comment = new Comment(audience, selectedFilm, commentText);

                // Add comment to list and table
                comments.add(comment);
                tableModel.addRow(new Object[]{audience.name, selectedFilm.name, commentText});

                // Clear fields
                nameField.setText("");
                ageField.setText("");
                commentField.setText("");

                // Show confirmation
                JOptionPane.showMessageDialog(frame, "Comment added successfully for the film: " + selectedFilm.name);
            }
        });

        // Show frame
        frame.setVisible(true);
    }
}
