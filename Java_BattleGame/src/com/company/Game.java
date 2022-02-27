package com.company;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Objects;
import java.util.ArrayList;

import static java.lang.String.valueOf;



public class Game extends JFrame {

    // initializing
    String username = "";
    ArrayList<Monster> monsterList = new ArrayList<>();
    Monster currentMonster;
    Weapon currentWeapon;

    // creating Weapon objects
    Weapon dagger = new Weapon(5, 1, "A close-range blade. Easy to carry around on your person, and adds some attack to your character, no matter who you are.");
    Weapon sword = new Weapon(15, 11, "A good sword is a match for no foe! Unless they have a sword and a faster than you... Nonetheless, a sword will dish out a lot of damage.");
    Weapon hammer = new Weapon(10, 6, "It's hammer time! But for real... This will stop your enemies in their track, but will slow you down a bit - so tread lightly.");

    // creating Playable Character objects
    PlayerCharacter warrior = new PlayerCharacter("Warrior", 55, 11, 2, 15, "A warrior is a brave and experienced fighter and often have higher attack and defense, but low agility.");
    PlayerCharacter wizard = new PlayerCharacter("Wizard", 50, 8, 15, 10, "A wizard is a powerful spellcaster, capable of stopping time and enemies - but with almost no defense, a wizard must be careful in battle.");
    PlayerCharacter cleric = new PlayerCharacter("Cleric", 50, 9, 13, 11, "A cleric is diverse soldier, being an expert in both combat and divine magic. Some say they even possess powers of life or death.");
    PlayerCharacter currentCharacter;

    public Game() throws IOException, FontFormatException {


        this.setTitle("Dragons & Dungeons");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(150, 150, 860, 700);

        // Creating Monster objects and storing in arraylist
        monsterList.add(new Monster("Dragon", 100, 25, 10, 25));
        monsterList.add(new Monster("Goblin", 45, 5, 12, 7));
        monsterList.add(new Monster("Troll", 60, 5, 11, 17));
        monsterList.add(new Monster("Zombie", 12, 1, 2, 5));

        // set up my JPanel for this frames
        // This is the Parent one (Content Pane)
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        this.setContentPane(contentPane); // this.setContentPane = contentPane;
        contentPane.setLayout(new CardLayout(0, 0)); //For the Parent JPanel, before we used null


        Font font = Font.createFont(Font.TRUETYPE_FONT, Objects.requireNonNull(getClass().getResource("/OldLondon.ttf")).openStream());
        GraphicsEnvironment genv = GraphicsEnvironment.getLocalGraphicsEnvironment();
        genv.registerFont(font);
        // make sure to derive the size
        font = font.deriveFont(12f);


        // First Panel - HOME
        JPanel panelOne = new JPanel();
        // can we make this background colour into a variable, then apply it to all windows?
        panelOne.setBackground(new Color(130, 150, 214, 255));
        contentPane.add(panelOne);
        panelOne.setLayout(null);

        // Control Objects for panelOne
        JLabel labelOne = new JLabel("Dragons & Dungeons");
        labelOne.setFont(font.deriveFont(Font.PLAIN, 48f));
        labelOne.setBounds(230, 5, 400, 85);
        panelOne.add(labelOne);

        // Adding image
        JLabel homePagePic = new JLabel();
        homePagePic.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/MainGamePicResized.jpeg"))));
        homePagePic.setBounds(50, 100, 740, 450);
        panelOne.add(homePagePic);

        JButton buttonOne = new JButton("Build A Character");
        buttonOne.setFont(new Font("Old London", Font.BOLD, 24));
        buttonOne.setBounds(300, 570, 250, 30);
        panelOne.add(buttonOne);

        // Second Panel - CHARACTER SELECTION
        JPanel panelTwo = new JPanel();
        panelTwo.setBackground(new Color(130, 150, 214, 255));
        contentPane.add(panelTwo);
        panelTwo.setLayout(null);

        // Title Label
        JLabel labelTwo = new JLabel("Character Generator");
        labelTwo.setFont(new Font("Old London", Font.BOLD, 48));
        labelTwo.setBounds(40, 10, 400, 50);
        panelTwo.add(labelTwo);

        // Enter Name Label
        JLabel enterNameLabel = new JLabel("Enter Name");
        enterNameLabel.setFont(new Font("Old London", Font.BOLD, 38));
        enterNameLabel.setBounds(40, 60, 400, 50);
        panelTwo.add(enterNameLabel);

        // Character Type Label
        JLabel charTypeLabel = new JLabel("Character Type");
        charTypeLabel.setFont(new Font("Old London", Font.BOLD, 38));
        charTypeLabel.setBounds(40, 100, 400, 50);
        panelTwo.add(charTypeLabel);

        // Select Weapon Label
        JLabel selectWeaponLabel = new JLabel("Select Your Weapon");
        selectWeaponLabel.setFont(new Font("Old London", Font.BOLD, 38));
        selectWeaponLabel.setBounds(40, 350, 400, 50);
        panelTwo.add(selectWeaponLabel);

        // Character Stats Label
        JLabel charStatsLabel = new JLabel("Character Stats");
        charStatsLabel.setFont(new Font("Old London", Font.BOLD, 38));
        charStatsLabel.setBounds(520, 100, 400, 50);
        panelTwo.add(charStatsLabel);

        // Hit Points Label
        JLabel pointsLabel = new JLabel("Hit Points");
        pointsLabel.setFont(new Font("Serif", Font.PLAIN, 20));
        pointsLabel.setBounds(520, 160, 100, 30);
        panelTwo.add(pointsLabel);

        // Defense Label
        JLabel defenseLabel = new JLabel("Defense");
        defenseLabel.setFont(new Font("Serif", Font.PLAIN, 20));
        defenseLabel.setBounds(520, 190, 100, 30);
        panelTwo.add(defenseLabel);

        // Agility Label
        JLabel agilityLabel = new JLabel("Agility");
        agilityLabel.setFont(new Font("Serif", Font.PLAIN, 20));
        agilityLabel.setBounds(520, 220, 100, 30);
        panelTwo.add(agilityLabel);

        // Base Attack Label
        JLabel baseLabel = new JLabel("Base Attack");
        baseLabel.setFont(new Font("Serif", Font.PLAIN, 20));
        baseLabel.setBounds(520, 250, 100, 30);
        panelTwo.add(baseLabel);

        // Weapon Stats Label
        JLabel weaponStatsLabel = new JLabel("Weapon Stats");
        weaponStatsLabel.setFont(new Font("Old London", Font.BOLD, 38));
        weaponStatsLabel.setBounds(520, 350, 400, 50);
        panelTwo.add(weaponStatsLabel);

        // Selection Label
        JLabel selectLabel = new JLabel("Complete Selections First");
        selectLabel.setFont(new Font("Old London", Font.PLAIN, 18));
        selectLabel.setForeground(Color.RED);
        selectLabel.setBounds(542, 320, 175, 50);
        selectLabel.setVisible(false);
        panelTwo.add(selectLabel);

        // Attack Modifier Label
        JLabel modLabel = new JLabel("Attack Modifier");
        modLabel.setFont(new Font("Serif", Font.PLAIN, 20));
        modLabel.setBounds(520, 420, 150, 30);
        panelTwo.add(modLabel);

        // Weight Label
        JLabel weightLabel = new JLabel("Weight");
        weightLabel.setFont(new Font("Serif", Font.PLAIN, 20));
        weightLabel.setBounds(520, 450, 100, 30);
        panelTwo.add(weightLabel);

        // Adding TextFields
        // Enter Name text field
        JTextField nameField = new JTextField(" ");
        nameField.setBounds(240, 80, 200, 25);
        panelTwo.add(nameField);

        // Character Stats field
        JTextArea charInfoField = new JTextArea(" ");
        charInfoField.setBounds(40, 280, 400, 80);
        Font textfont = new Font("Serif", Font.PLAIN, 18);
        charInfoField.setFont(textfont);
        charInfoField.setForeground(new Color(81, 118, 236, 255));
        charInfoField.setEditable(false);
        charInfoField.setLineWrap(true);
        charInfoField.setWrapStyleWord(true);
        panelTwo.add(charInfoField);

        // Weapons Stats field
        JTextArea weaponsInfoField = new JTextArea(" ");
        weaponsInfoField.setBounds(40, 540, 400, 95);
        weaponsInfoField.setFont(textfont);
        weaponsInfoField.setForeground(new Color(81, 118, 236, 255));
        weaponsInfoField.setEditable(false);
        weaponsInfoField.setLineWrap(true);
        weaponsInfoField.setWrapStyleWord(true);
        panelTwo.add(weaponsInfoField);

        // Hit Points Text field
        JTextField pointsField = new JTextField(" ");
        pointsField.setBounds(670, 160, 40, 28);
        pointsField.setFont(textfont);
        pointsField.setForeground(new Color(81, 118, 236, 255));
        pointsField.setEditable(false);
        panelTwo.add(pointsField);

        // Defense Text field
        JTextField defenseField = new JTextField(" ");
        defenseField.setBounds(670, 190, 40, 28);
        defenseField.setFont(textfont);
        defenseField.setForeground(new Color(81, 118, 236, 255));
        defenseField.setEditable(false);
        panelTwo.add(defenseField);

        // Agility Text field
        JTextField agilityField = new JTextField(" ");
        agilityField.setBounds(670, 220, 40, 28);
        agilityField.setFont(textfont);
        agilityField.setForeground(new Color(81, 118, 236, 255));
        agilityField.setEditable(false);
        panelTwo.add(agilityField);

        // Base Attack Text field
        JTextField baseField = new JTextField(" ");
        baseField.setBounds(670, 250, 40, 28);
        baseField.setFont(textfont);
        baseField.setForeground(new Color(81, 118, 236, 255));
        baseField.setEditable(false);
        panelTwo.add(baseField);

        // Attack Modifier Text field
        JTextField modField = new JTextField(" ");
        modField.setBounds(670, 420, 40, 28);
        modField.setFont(textfont);
        modField.setForeground(new Color(81, 118, 236, 255));
        modField.setEditable(false);
        panelTwo.add(modField);

        // Weight Text field
        JTextField weightField = new JTextField(" ");
        weightField.setBounds(670, 450, 40, 28);
        weightField.setFont(textfont);
        weightField.setForeground(new Color(81, 118, 236, 255));
        weightField.setEditable(false);
        panelTwo.add(weightField);

        // Button Groups
        ButtonGroup characterButtons = new ButtonGroup();
        ButtonGroup weaponButtons = new ButtonGroup();

        // Warrior Button
        JRadioButton warButton = new JRadioButton("Warrior");
        warButton.setActionCommand("Warrior");
        warButton.setFont(new Font("Serif", Font.PLAIN, 20));
        warButton.setBackground(new Color(130, 150, 214, 255));
        warButton.setBounds(40, 160, 100, 30);
        characterButtons.add(warButton);
        panelTwo.add(warButton);

        // Wizard Button
        JRadioButton wizButton = new JRadioButton("Wizard");
        wizButton.setActionCommand("Wizard");
        wizButton.setFont(new Font("Serif", Font.PLAIN, 20));
        wizButton.setBackground(new Color(130, 150, 214, 255));
        wizButton.setBounds(40, 200, 100, 30);
        characterButtons.add(wizButton);
        panelTwo.add(wizButton);

        // Cleric Button
        JRadioButton clerButton = new JRadioButton("Cleric");
        clerButton.setActionCommand("Cleric");
        clerButton.setFont(new Font("Serif", Font.PLAIN, 20));
        clerButton.setBackground(new Color(130, 150, 214, 255));
        clerButton.setBounds(40, 240, 100, 30);
        characterButtons.add(clerButton);
        panelTwo.add(clerButton);

        // Dagger Button
        JRadioButton daggerButton = new JRadioButton("Dagger");
        daggerButton.setFont(new Font("Serif", Font.PLAIN, 20));
        daggerButton.setBackground(new Color(130, 150, 214, 255));
        daggerButton.setBounds(40, 420, 100, 30);
        weaponButtons.add(daggerButton);
        panelTwo.add(daggerButton);

        // Sword Button
        JRadioButton swordButton = new JRadioButton("Sword");
        swordButton.setFont(new Font("Serif", Font.PLAIN, 20));
        swordButton.setBackground(new Color(130, 150, 214, 255));
        swordButton.setBounds(40, 460, 100, 30);
        weaponButtons.add(swordButton);
        panelTwo.add(swordButton);

        // Hammer Button
        JRadioButton hammerButton = new JRadioButton("Hammer");
        hammerButton.setFont(new Font("Serif", Font.PLAIN, 20));
        hammerButton.setBackground(new Color(130, 150, 214, 255));
        hammerButton.setBounds(40, 500, 120, 30);
        weaponButtons.add(hammerButton);
        panelTwo.add(hammerButton);


        // Adding Character Images
        // Warrior Image
        JLabel warriorImage = new JLabel();
        warriorImage.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/WarriorResized.jpeg"))));
        warriorImage.setBounds(200, 165, 80, 110);
        warriorImage.setVisible(false);
        panelTwo.add(warriorImage);
        // Wizard Image
        JLabel wizardImage = new JLabel();
        wizardImage.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/WizardResized.jpeg"))));
        wizardImage.setBounds(200, 165, 80, 110);
        wizardImage.setVisible(false);
        panelTwo.add(wizardImage);
        // Cleric Image
        JLabel clericImage = new JLabel();
        clericImage.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/ClericResized.jpeg"))));
        clericImage.setBounds(200, 165, 80, 110);
        clericImage.setVisible(false);
        panelTwo.add(clericImage);

        // Adding Weapon Images
        // Dagger Image
        JLabel daggerImage = new JLabel();
        daggerImage.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/daggerResized.jpeg"))));
        daggerImage.setBounds(200, 427, 100, 100);
        daggerImage.setVisible(false);
        panelTwo.add(daggerImage);

        // Sword Image
        JLabel swordImage = new JLabel();
        swordImage.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/swordResized.jpeg"))));
        swordImage.setBounds(200, 427, 100, 100);
        swordImage.setVisible(false);
        panelTwo.add(swordImage);

        // Hammer Image
        JLabel hammerImage = new JLabel();
        hammerImage.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/hammerResized.jpeg"))));
        hammerImage.setBounds(200, 427, 100, 100);
        hammerImage.setVisible(false);
        panelTwo.add(hammerImage);

        // Reroll Button
        JButton reroll = new JButton("Reroll");
        reroll.setFont(new Font("Old London", Font.BOLD, 20));
        reroll.setBounds(575, 300, 100, 30);
        panelTwo.add(reroll);

        // Start Battle Button
        JButton startBattle = new JButton("Start Battle");
        startBattle.setFont(new Font("Old London", Font.BOLD, 20));
        startBattle.setBounds(555, 570, 150, 30);
        panelTwo.add(startBattle);

        // Third Panel - BATTLE
        JPanel panelThree = new JPanel();
        panelThree.setBackground(new Color(130, 150, 214, 255));
        contentPane.add(panelThree);
        panelThree.setLayout(null);

        // Title Label
        JLabel labelThree = new JLabel("Battle To The Death!");
        labelThree.setFont(new Font("Old London", Font.BOLD, 64));
        labelThree.setBounds(120, 20, 600, 70);
        panelThree.add(labelThree);

        // Quit Button
        JButton buttonThree = new JButton("Quit!");
        buttonThree.setBounds(350, 580, 150, 30);
        buttonThree.setFont(new Font("Old London", Font.BOLD, 20));
        panelThree.add(buttonThree);

        // Summary Text Area
        JTextArea sumArea = new JTextArea(" ");
        sumArea.setBounds(75, 300, 680, 250);
        sumArea.setFont(textfont);
        sumArea.setForeground(new Color(81, 118, 236, 255));
        sumArea.setEditable(false);
        sumArea.setLineWrap(true);
        sumArea.setWrapStyleWord(true);
        panelThree.add(sumArea);
        sumArea.setVisible(false);

        // Battle Button
        JButton buttonFour = new JButton("Battle!");
        buttonFour.setBounds(350, 340, 150, 30);
        buttonFour.setFont(new Font("Old London", Font.BOLD, 20));
        panelThree.add(buttonFour);

        // Action Listeners
        // First Panel - HOME
        buttonOne.addActionListener(e -> {
            panelOne.setVisible(false);
            panelTwo.setVisible(true);
        });


        reroll.addActionListener(e -> {
            if (characterButtons.getSelection() != null && weaponButtons.getSelection() != null) {
                reroll.setEnabled(false);
                selectLabel.setVisible(false);
                currentCharacter.rerollAll();
                pointsField.setText(valueOf(currentCharacter.getHitPoints()));
                defenseField.setText(valueOf(currentCharacter.getDefense()));
                agilityField.setText(valueOf(currentCharacter.getAgility()));
                baseField.setText(valueOf(currentCharacter.getBaseAttack()));
                if (warButton.isSelected()) {
                    wizButton.setEnabled(false);
                    clerButton.setEnabled(false);
                } else if (wizButton.isSelected()) {
                    warButton.setEnabled(false);
                    clerButton.setEnabled(false);
                } else if (clerButton.isSelected()) {
                    warButton.setEnabled(false);
                    wizButton.setEnabled(false);
                }
                if (swordButton.isSelected()) {
                    daggerButton.setEnabled(false);
                    hammerButton.setEnabled(false);
                } else if (daggerButton.isSelected()) {
                    swordButton.setEnabled(false);
                    hammerButton.setEnabled(false);
                } else if (hammerButton.isSelected()) {
                    swordButton.setEnabled(false);
                    daggerButton.setEnabled(false);
                }
            } else {
                selectLabel.setVisible(true);
            }
        });

        warButton.addActionListener(e -> {
            warriorImage.setVisible(true);
            wizardImage.setVisible(false);
            clericImage.setVisible(false);
            pointsField.setText(valueOf(warrior.getHitPoints()));
            defenseField.setText(valueOf(warrior.getDefense()));
            agilityField.setText(valueOf(warrior.getAgility()));
            baseField.setText(valueOf(warrior.getBaseAttack()));
            charInfoField.setText(warrior.getDescription());
            Game.this.currentCharacter = warrior;
        });

        wizButton.addActionListener(e -> {
            wizardImage.setVisible(true);
            warriorImage.setVisible(false);
            clericImage.setVisible(false);
            pointsField.setText(valueOf(wizard.getHitPoints()));
            defenseField.setText(valueOf(wizard.getDefense()));
            agilityField.setText(valueOf(wizard.getAgility()));
            baseField.setText(valueOf(wizard.getBaseAttack()));
            charInfoField.setText(wizard.getDescription());
            Game.this.currentCharacter = wizard;
        });

        clerButton.addActionListener(e -> {
            clericImage.setVisible(true);
            wizardImage.setVisible(false);
            warriorImage.setVisible(false);
            pointsField.setText(valueOf(cleric.getHitPoints()));
            defenseField.setText(valueOf(cleric.getDefense()));
            agilityField.setText(valueOf(cleric.getAgility()));
            baseField.setText(valueOf(cleric.getBaseAttack()));
            charInfoField.setText(cleric.getDescription());
            Game.this.currentCharacter = cleric;
        });

        daggerButton.addActionListener(e -> {
            daggerImage.setVisible(true);
            swordImage.setVisible(false);
            hammerImage.setVisible(false);
            weaponsInfoField.setText(dagger.getWpnText());
            modField.setText("+" + dagger.getWpnAttackMod());
            weightField.setText(valueOf(dagger.getWpnWeight()));
            currentWeapon = dagger;
        });

        swordButton.addActionListener(e -> {
            daggerImage.setVisible(false);
            swordImage.setVisible(true);
            hammerImage.setVisible(false);
            weaponsInfoField.setText(sword.getWpnText());
            modField.setText("+" + sword.getWpnAttackMod());
            weightField.setText(valueOf(sword.getWpnWeight()));
            currentWeapon = sword;
        });

        hammerButton.addActionListener(e -> {
            daggerImage.setVisible(false);
            swordImage.setVisible(false);
            hammerImage.setVisible(true);
            weaponsInfoField.setText(hammer.getWpnText());
            modField.setText("+" + hammer.getWpnAttackMod());
            weightField.setText(valueOf(hammer.getWpnWeight()));
            currentWeapon = hammer;
        });

        // Second Panel - CHARACTER SELECTION
        startBattle.addActionListener(e -> {
            username = nameField.getText();
            // determine which monster the user will face
            int max = 4;
            int min = 0;
            int monsterSelection = (int) Math.floor(Math.random() * (max - min) + min);
            currentMonster = monsterList.get(monsterSelection);
            //Player Label
            String playerNameConcat = "Player: " + currentCharacter.getName();
            JLabel playerLabel = new JLabel(playerNameConcat);
            playerLabel.setFont(new Font("Old London", Font.BOLD, 26));
            playerLabel.setBounds(155, 100, 200, 50);
            //Monster Label
            String monsterNameConcat = "Monster: " + currentMonster.getName();
            JLabel monsterLabel = new JLabel(monsterNameConcat);
            monsterLabel.setFont(new Font("Old London", Font.BOLD, 26));
            monsterLabel.setBounds(500, 100, 200, 50);
            //ADD IMAGES
            panelThree.add(playerLabel);
            panelThree.add(monsterLabel);
            // image for player and monster
            JLabel playerImage = new JLabel();
            JLabel monsterImage = new JLabel();
            playerImage.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/" + currentCharacter.getName() + "Resized.jpeg"))));
            monsterImage.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/" + currentMonster.getName() + "Resized.jpeg"))));
            playerImage.setBounds(175, 150, 100, 100);
            monsterImage.setBounds(540, 150, 100, 100);
            playerImage.setVisible(true);
            monsterImage.setVisible(true);
            panelThree.add(playerImage);
            panelThree.add(monsterImage);
            //JTextArea battle summary update
            sumArea.setText("Player: " + username + "\nClass: " + currentCharacter.getName() + "\n\nVS.\n\n");
            sumArea.append("Monster: " + currentMonster.getName() + "\n---------------\n");
            sumArea.setFont(new Font("Serif", Font.BOLD, 22));
            sumArea.append(runBattle(currentCharacter, currentMonster, currentWeapon));
            panelTwo.setVisible(false);
            panelThree.setVisible(true);
        });

        // Quit Button
        buttonThree.addActionListener(e -> {
            System.exit(0);
        });

        // Run Battle button
        buttonFour.addActionListener(e -> {
            buttonFour.setVisible(false);
            sumArea.setVisible(true);
        });
    }

    public String runBattle(PlayerCharacter player, Monster monster, Weapon weapon) {
        String results = "";
        double playerTough = (1.2 * player.getDefense()) + player.getHitPoints();
        double monsterTough = (1.2 * monster.getDefense()) + monster.getHitPoints();
        final double playerToughOriginal = playerTough;
        final double monsterToughOriginal = monsterTough;
        double playerSpeed = (player.getAgility()) - weapon.getWpnWeight();
        double playerPower = (player.getBaseAttack() + weapon.getWpnAttackMod());
        boolean playerGainTough = false;
        boolean monsterGainTough = false;
        int turnCount = 1;

        while (player.getHitPoints() > 0 && monster.getHitPoints() > 0) {
            if (playerSpeed >= monster.getAgility()) {                              // if player goes first (has at least equal speed)
                monster.setHitPoints((int) (monsterTough - playerPower));           // player attacks monster
                if (monster.getHitPoints() <= 0) {                                  // evaluate if monster died
                    results = ("After " + turnCount + " turn(s), the player wins!");// assign results
                    return results;                                                 // return results
                }
                turnCount++;                                                        // increment turn counter -- out
                monsterTough = (1.2 * monster.getDefense()) + monster.getHitPoints(); // recalculate monster toughness
                if (monsterTough > monsterToughOriginal) {                          // evaluate if monster gained toughness via battle
                    monsterTough = monsterToughOriginal;                            // if so, make it equal to base toughness
                    monsterGainTough = true;                                        // if both of these would be true, fight will be endless
                }
                player.setHitPoints((int) (playerTough - monster.getBaseAttack())); // monster attacks player
                if (player.getHitPoints() <= 0) {                                   // evaluate if player died
                    results = ("After " + turnCount + " turns, the monster wins!"); // assign results
                    return results;                                                 // return results
                }
                turnCount++;                                                        // increment turn counter -- out
                playerTough = (1.2 * player.getDefense()) + player.getHitPoints();  // recalculate player toughness
                if (playerTough > playerToughOriginal) {                            // evaluate if player gained toughness via battle
                    playerTough = playerToughOriginal;                              // if so, make it equal to base toughness
                    playerGainTough = true;                                         // if both of these would be true, fight will be endless
                }
                if (playerGainTough && monsterGainTough){                           // evaluate if both characters would've gained toughness
                    results = "It's a draw!";                                       // if tied
                    return results;
                }
            }
            else {
                player.setHitPoints((int) (playerTough - monster.getBaseAttack())); // monster attacks player
                if (player.getHitPoints() <= 0){                                    // evaluate if player died
                    results = ("After " + turnCount + " turns, the monster wins!"); // assign results
                    return results;                                                 // return results
                }
                turnCount++;                                                        // increment turn counter -- out
                playerTough = (1.2 * player.getDefense() + player.getHitPoints());  // recalculate player toughness
                if (playerTough > playerToughOriginal) {                            // evaluate to see if player gained toughness via battle
                    playerTough = playerToughOriginal;                              // if so, make it equal to base toughness
                    playerGainTough = true;                                         // if both of these would be true, fight will be endless
                }
                monster.setHitPoints((int) (monsterTough - playerPower));           // player attacks monster
                if (monster.getHitPoints() <= 0) {                                  // evaluate if monster died
                    results = ("After " + turnCount + " turns, the player wins!");  // assign results
                    return results;                                                 // return results
                }
                turnCount++;                                                        // increment turn counter -- out
                monsterTough = (1.2 * monster.getDefense()) + monster.getHitPoints();// recalculate monster toughness
                if (monsterTough > monsterToughOriginal) {                          // evaluate if monster gained toughness via battle
                    monsterTough = monsterToughOriginal;                            // if so, make it equal to base toughness
                    monsterGainTough = true;                                        // if both of these would be true, fight will be endless
                }
                if (playerGainTough && monsterGainTough){                           // evaluate if both characters would've gained toughness
                    results = "It's a draw!";                                       // if tied
                    return results;
                }
            }

                }
    return "* * Battle Error: 0001 * *"; // will only return this if somehow the loop gets broken and doesn't return results
    }
}