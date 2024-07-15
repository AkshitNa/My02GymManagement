package Gym_Management;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FitnessBot extends JFrame {
    private JTextField goalField;
    private JTextArea workoutArea, nutritionArea;
    private JButton createPlanButton, savePlanButton;
    private Map<String, String> keywordResponses;
    private Map<String, String> nutritionPlans;

    public FitnessBot() {
        setTitle("Fitness Bot");
        setSize(540, 500);
        setLocationRelativeTo(null);
        setResizable(false);

        // Add a window listener to handle the close event
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        initializeKeywordResponses();
        initializeNutritionPlans();
        initComponents();

        // Setting Icon on FRAME
        ImageIcon loginImage = new ImageIcon(getClass().getResource("/Gym_Management/icons/777.png"));
        Image resultloginImage = loginImage.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        setIconImage(resultloginImage);
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(240, 240, 240));

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridBagLayout());

        // Font for labels
        Font labelFont = new Font("Arial", Font.BOLD, 16);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel goalLabel = new JLabel("Fitness Goal:");
        goalLabel.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(goalLabel, gbc);

        goalField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        inputPanel.add(goalField, gbc);

        JLabel workoutLabel = new JLabel("Workout Plan:");
        workoutLabel.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(workoutLabel, gbc);

        workoutArea = new JTextArea(10, 30);
        workoutArea.setFont(new Font("Arial", Font.PLAIN, 14));
        workoutArea.setLineWrap(true); // Enable automatic wrapping
        workoutArea.setWrapStyleWord(true); // Wrap at word boundaries
        JScrollPane workoutScrollPane = new JScrollPane(workoutArea);
        gbc.gridx = 1;
        gbc.gridy = 1;
        inputPanel.add(workoutScrollPane, gbc);

        JLabel nutritionLabel = new JLabel("Nutrition Plan:");
        nutritionLabel.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridy = 2;
        inputPanel.add(nutritionLabel, gbc);

        nutritionArea = new JTextArea(10, 30);
        nutritionArea.setFont(new Font("Arial", Font.PLAIN, 14));
        nutritionArea.setLineWrap(true); // Enable automatic wrapping
        nutritionArea.setWrapStyleWord(true); // Wrap at word boundaries
        JScrollPane nutritionScrollPane = new JScrollPane(nutritionArea);
        gbc.gridx = 1;
        gbc.gridy = 2;
        inputPanel.add(nutritionScrollPane, gbc);

        mainPanel.add(inputPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(240, 240, 240));

        // Font for buttons
        Font buttonFont = new Font("Arial", Font.BOLD, 14);

        createPlanButton = new JButton("Create Plan");
        createPlanButton.setFont(buttonFont);
        createPlanButton.setBackground(new Color(0, 153, 204));
        createPlanButton.setForeground(Color.WHITE);
        buttonPanel.add(createPlanButton);

        savePlanButton = new JButton("Save Plan");
        savePlanButton.setFont(buttonFont);
        savePlanButton.setBackground(new Color(51, 204, 51));
        savePlanButton.setForeground(Color.WHITE);
        buttonPanel.add(savePlanButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);

        createPlanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createPlan();
            }
        });

        savePlanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                savePlan();
            }
        });
    }

        private void initializeKeywordResponses() {
        keywordResponses = new HashMap<>();
        keywordResponses.put("thin", "For a 'thin' goal, focus on cardio exercises and a calorie deficit diet.");
        keywordResponses.put("fat", "For a 'fat' goal, focus on strength training and a balanced diet with a calorie surplus.");
        keywordResponses.put("healthy", "For a 'healthy' goal, focus on a balanced mix of cardio, strength training, and a nutrient-rich diet.");
        keywordResponses.put("muscles", "For a 'muscles' goal, focus on high-intensity strength training and a protein-rich diet.");
        keywordResponses.put("precautions", "It's important to take precautions during workouts to avoid injuries. Always warm up before exercising and cool down afterwards.");
        keywordResponses.put("good habits", "Developing good habits like regular exercise, balanced diet, adequate sleep, and staying hydrated are key to maintaining overall health and fitness.");
        keywordResponses.put("foods to avoid", "Avoid processed foods, sugary snacks, and excessive fast food. Instead, focus on whole foods such as fruits, vegetables, lean proteins, and whole grains.");
        keywordResponses.put("alcohol", "Limit alcohol consumption as it can interfere with muscle recovery, impair coordination, and negatively impact overall health. Excessive drinking can lead to various health problems including liver disease and obesity.");
        keywordResponses.put("water intake", "Stay hydrated by drinking plenty of water throughout the day. Aim for at least 8 glasses of water daily to support overall health and fitness.");
        keywordResponses.put("cardio", "Incorporate cardio exercises like running, cycling, or swimming into your workout routine to improve cardiovascular health and endurance.");
        keywordResponses.put("strength training", "Include strength training exercises such as weightlifting or bodyweight exercises to build muscle mass and increase strength.");
        keywordResponses.put("flexibility", "Don't forget to stretch regularly to improve flexibility and reduce the risk of injury during workouts.");
        keywordResponses.put("rest and recovery", "Allow your body adequate time to rest and recover between workouts to prevent overtraining and promote muscle growth.");
        keywordResponses.put("portion control", "Practice portion control by measuring food servings to manage caloric intake and support weight management goals.");
        keywordResponses.put("mindful eating", "Practice mindful eating by paying attention to hunger and fullness cues, and savoring each bite to promote healthy eating habits.");
        keywordResponses.put("meal planning", "Plan your meals ahead of time to ensure a balanced diet and avoid unhealthy food choices when hungry.");
        keywordResponses.put("supplements", "Consider adding supplements like protein powder or multivitamins to support your fitness goals, but consult with a healthcare professional before starting any new supplements.");
        keywordResponses.put("recovery meals", "Consume a balanced recovery meal or snack containing carbohydrates and protein after workouts to replenish energy stores and support muscle repair.");
        keywordResponses.put("sleep quality", "Prioritize sleep and aim for 7-9 hours of quality sleep each night to support overall health, recovery, and performance.");
        keywordResponses.put("stress management", "Manage stress through relaxation techniques like deep breathing, meditation, or yoga to improve mental well-being and reduce the risk of stress-related health issues.");
        keywordResponses.put("motivation", "Stay motivated by setting realistic goals, tracking progress, celebrating achievements, and surrounding yourself with supportive peers or mentors.");
        keywordResponses.put("consistency", "Consistency is key to achieving fitness goals. Stick to a regular exercise routine and healthy eating habits for long-term success.");
        keywordResponses.put("hydration", "Stay hydrated before, during, and after workouts to maintain optimal performance, prevent dehydration, and support recovery.");
        keywordResponses.put("variety", "Keep workouts interesting by incorporating a variety of exercises, activities, and fitness classes to prevent boredom and plateaus.");
        keywordResponses.put("form and technique", "Focus on proper form and technique during exercises to maximize effectiveness, prevent injuries, and achieve optimal results.");
        keywordResponses.put("accountability", "Stay accountable by tracking progress, setting deadlines, and seeking support from friends, family, or a fitness coach.");
        keywordResponses.put("recovery days", "Incorporate active recovery days with light exercises like walking, yoga, or stretching to promote muscle repair and reduce soreness.");
        keywordResponses.put("positive mindset", "Maintain a positive mindset by focusing on progress, embracing challenges, and cultivating self-confidence to overcome obstacles and achieve goals.");
        keywordResponses.put("body composition", "Focus on improving body composition by reducing body fat percentage and increasing lean muscle mass through a combination of strength training and proper nutrition.");
        keywordResponses.put("self-care", "Prioritize self-care activities like massage, foam rolling, or hot baths to reduce muscle tension, improve recovery, and enhance overall well-being.");
        keywordResponses.put("flexibility training", "Incorporate regular flexibility training exercises such as yoga or Pilates to improve range of motion, joint mobility, and muscle flexibility.");
        keywordResponses.put("recovery strategies", "Implement recovery strategies like foam rolling, contrast baths, or compression garments to enhance recovery, reduce muscle soreness, and prevent injuries.");
        keywordResponses.put("portion sizes", "Be mindful of portion sizes and avoid overeating by using smaller plates, measuring servings, and listening to hunger cues to maintain a healthy weight.");
        keywordResponses.put("mind-body connection", "Cultivate a strong mind-body connection through mindfulness practices like meditation, deep breathing, or visualization techniques to enhance performance and well-being.");
        keywordResponses.put("social support", "Seek social support from friends, family, or workout buddies to stay motivated, accountable, and engaged in your fitness journey.");
        keywordResponses.put("active lifestyle", "Incorporate physical activity into your daily routine by taking the stairs, walking or biking to work, or participating in recreational sports to maintain an active lifestyle.");
        keywordResponses.put("functional fitness", "Focus on functional fitness exercises that mimic real-life movements to improve strength, balance, and coordination for everyday activities and injury prevention.");
        keywordResponses.put("body awareness", "Develop body awareness through mindful movement practices like tai chi or Feldenkrais to improve posture, alignment, and movement efficiency.");
        keywordResponses.put("nutrition tracking", "Track your daily food intake using a food journal or mobile app to monitor calories, macronutrients, and micronutrients for optimal nutrition and health.");
        keywordResponses.put("progress tracking", "Track progress by taking measurements, keeping a workout log, or using fitness apps to monitor changes in strength, endurance, and body composition over time.");
        keywordResponses.put("hydration habits", "Establish hydration habits by carrying a water bottle, setting reminders, or tracking daily water intake to ensure adequate hydration and support overall health and performance.");
        keywordResponses.put("meal timing", "Optimize meal timing by eating smaller, balanced meals every 3-4 hours to maintain energy levels, stabilize blood sugar, and prevent overeating.");
        keywordResponses.put("portion control tips", "Practice portion control by using smaller plates, measuring servings, and dividing meals into appropriate portions to manage calorie intake and promote weight loss or maintenance.");
        keywordResponses.put("stress reduction techniques", "Incorporate stress reduction techniques like deep breathing, progressive muscle relaxation, or mindfulness meditation to lower stress levels and improve overall well-being.");
        keywordResponses.put("restoration strategies", "Implement restoration strategies like sleep hygiene practices, relaxation exercises, or therapeutic modalities to enhance recovery, reduce fatigue, and promote overall wellness.");
        keywordResponses.put("personalized nutrition plan", "Consult with a registered dietitian or nutritionist to create a personalized nutrition plan tailored to your individual goals, preferences, and dietary needs.");
        keywordResponses.put("supplement recommendations", "Consider incorporating supplements like protein powder, creatine, or omega-3 fatty acids into your nutrition plan to support muscle growth, recovery, and overall health.");
        keywordResponses.put("mindful eating practices", "Practice mindful eating by slowing down, savoring each bite, and paying attention to hunger and fullness cues to prevent overeating and promote a healthy relationship with food.");
        keywordResponses.put("hydration tips", "Stay hydrated by drinking water throughout the day, sipping water during workouts, and consuming hydrating foods like fruits, vegetables, and soups to maintain proper hydration levels.");
        keywordResponses.put("meal prep ideas", "Simplify meal prep by batch cooking, using leftovers creatively, or preparing meals in advance to save time, money, and stress during busy weekdays.");
        keywordResponses.put("nutrition education", "Educate yourself about nutrition basics, food labels, portion sizes, and macronutrient distribution to make informed choices and build a foundation for lifelong healthy eating habits.");
        keywordResponses.put("sleep hygiene", "Practice good sleep hygiene habits like maintaining a consistent sleep schedule, creating a relaxing bedtime routine, and optimizing your sleep environment for quality rest.");
        keywordResponses.put("mind-body practices", "Incorporate mind-body practices like yoga, tai chi, or qigong to reduce stress, improve flexibility, and enhance overall physical and mental well-being.");
        keywordResponses.put("active recovery", "Engage in active recovery activities like walking, swimming, or light stretching on rest days to promote blood flow, reduce muscle soreness, and enhance recovery between workouts.");
        keywordResponses.put("bodyweight exercises", "Incorporate bodyweight exercises like push-ups, squats, and lunges into your workout routine to build strength, endurance, and muscle tone without the need for equipment.");
        keywordResponses.put("meal frequency", "Experiment with meal frequency by eating smaller, more frequent meals or larger, less frequent meals to find a pattern that works best for your appetite, energy levels, and lifestyle.");
        keywordResponses.put("nutrition coaching", "Consider working with a nutrition coach or registered dietitian to receive personalized guidance, support, and accountability in achieving your health and fitness goals.");
        keywordResponses.put("hydration strategies", "Develop hydration strategies such as carrying a water bottle, flavoring water with fruit or herbs, or setting reminders to drink water throughout the day to maintain optimal hydration levels.");
        keywordResponses.put("meal planning tips", "Master meal planning with tips like creating a weekly meal schedule, preparing grocery lists, and cooking versatile ingredients to streamline meal prep and encourage healthier eating habits.");
        keywordResponses.put("nutrient timing", "Strategize nutrient timing by consuming carbohydrates and protein before and after workouts to fuel performance, support recovery, and maximize muscle protein synthesis.");
        keywordResponses.put("nutrition consultations", "Schedule nutrition consultations with a qualified professional to assess your dietary intake, address nutritional imbalances, and develop a personalized nutrition plan aligned with your goals.");
        keywordResponses.put("hydration habits", "Cultivate hydration habits by carrying a reusable water bottle, infusing water with fruits or herbs, or setting reminders to drink water throughout the day to maintain hydration status.");
        keywordResponses.put("meal frequency recommendations", "Experiment with different meal frequencies and patterns to find what works best for your appetite, energy levels, and fitness goals, whether it's three square meals a day or multiple smaller meals and snacks.");
        keywordResponses.put("nutrition workshops", "Attend nutrition workshops, seminars, or webinars to expand your knowledge, learn practical tips, and stay up-to-date on the latest research and trends in nutrition and health.");
        keywordResponses.put("height", "Height is largely determined by genetics. Focus on posture and exercises that promote spinal health for better posture, but embrace your natural height.");
        keywordResponses.put("drinking", "Moderation is key when it comes to drinking alcohol. Limit consumption to maintain overall health and well-being.");
        keywordResponses.put("drink water", "Staying hydrated is crucial for overall health. Aim to drink at least 8 glasses of water per day to maintain proper hydration levels.");
        keywordResponses.put("water", "Water is essential for life. Ensure you're drinking enough water throughout the day to stay hydrated and support bodily functions.");
        keywordResponses.put("cigarette", "Smoking has serious health risks. Seek support to quit smoking and improve your overall well-being.");
        keywordResponses.put("food", "Food is fuel for your body. Aim for a balanced diet rich in nutrients to support overall health and well-being.");
        keywordResponses.put("junk food", "Junk food is high in calories, sugar, and unhealthy fats. Limit consumption to occasional treats and focus on nourishing your body with whole, nutrient-rich foods.");
        keywordResponses.put("sex", "Sexual health is an important aspect of overall well-being. Practice safe and consensual sex, and prioritize communication with your partner.");
        keywordResponses.put("focus", "Maintaining focus is essential for achieving your goals. Practice mindfulness techniques and eliminate distractions to enhance concentration.");
        keywordResponses.put("discipline", "Discipline is the bridge between goals and accomplishment. Cultivate self-discipline through consistent habits and setting realistic expectations.");
        keywordResponses.put("motivation", "Motivation is the driving force behind success. Find your 'why' and stay committed to your goals, even when faced with challenges.");
        keywordResponses.put("wellness", "Wellness encompasses physical, mental, and emotional health. Prioritize self-care practices such as exercise, meditation, and adequate sleep.");
        keywordResponses.put("fitness", "Fitness is a journey, not a destination. Embrace the process and celebrate small victories along the way.");
        keywordResponses.put("health", "Health is wealth. Invest in your well-being by making healthy lifestyle choices and prioritizing preventive care.");
        keywordResponses.put("mindfulness", "Mindfulness is the practice of being present in the moment without judgment. Incorporate mindfulness techniques into your daily routine to reduce stress and increase overall happiness.");
    }

        private void initializeNutritionPlans() {
        nutritionPlans = new HashMap<>();
        // Add nutrition plans for each keyword
        nutritionPlans.put("thin", "Nutrition plan for a 'thin' goal: Focus on consuming lean protein sources like chicken breast, fish, and tofu. Include plenty of vegetables and whole grains, and limit processed foods and sugary snacks.");
        nutritionPlans.put("fat", "Nutrition plan for a 'fat' goal: Focus on consuming a balanced diet with plenty of protein, healthy fats, and complex carbohydrates. Incorporate lean protein sources like chicken, fish, and beans, and include plenty of fruits and vegetables.");
        nutritionPlans.put("healthy", "Nutrition plan for a 'healthy' goal: Focus on consuming a variety of nutrient-rich foods, including fruits, vegetables, whole grains, lean proteins, and healthy fats. Limit processed foods, sugary snacks, and excessive alcohol consumption.");
        nutritionPlans.put("muscles", "Nutrition plan for a 'muscles' goal: Focus on consuming a high-protein diet to support muscle growth and repair. Include lean protein sources like chicken, turkey, fish, and tofu, and incorporate carbohydrates and healthy fats for energy.");
        nutritionPlans.put("precautions", "Nutrition plan for taking precautions: Stay hydrated by drinking plenty of water before, during, and after exercise. Fuel your body with a balanced diet rich in fruits, vegetables, lean proteins, and whole grains to support overall health and fitness.");
        nutritionPlans.put("good habits", "Nutrition plan for developing good habits: Focus on establishing a routine of regular, balanced meals and snacks to support overall health and fitness. Include a variety of nutrient-rich foods like fruits, vegetables, whole grains, lean proteins, and healthy fats.");
        nutritionPlans.put("foods to avoid", "Nutrition plan for avoiding certain foods: Limit processed foods, sugary snacks, and excessive fast food. Instead, focus on whole foods such as fruits, vegetables, lean proteins, and whole grains to support overall health and fitness.");
        nutritionPlans.put("alcohol", "Nutrition plan for avoiding alcohol: Limit alcohol consumption as it can interfere with muscle recovery, impair coordination, and negatively impact overall health. Opt for hydrating beverages like water, herbal tea, or sparkling water instead.");
        nutritionPlans.put("hydration", "Nutrition plan for hydration: Drink plenty of water throughout the day to stay hydrated and support overall health and fitness. Aim to consume at least 8-10 glasses of water daily, and increase fluid intake during exercise or in hot weather.");
        nutritionPlans.put("variety", "Nutrition plan for variety: Include a wide range of foods in your diet to ensure you get a diverse array of nutrients. Aim to eat foods from all food groups, including fruits, vegetables, whole grains, lean proteins, and healthy fats, to support overall health and well-being.");
        nutritionPlans.put("form and technique", "Nutrition plan for form and technique: Fuel your workouts with a balanced diet that provides the energy and nutrients your body needs to perform at its best. Focus on consuming complex carbohydrates, lean proteins, and healthy fats to support energy levels, muscle repair, and recovery.");
        nutritionPlans.put("accountability", "Nutrition plan for accountability: Stay accountable to your nutrition goals by tracking your food intake, setting realistic targets, and seeking support from friends, family, or a nutrition coach. Use tools like food journals, meal planners, or nutrition apps to monitor progress and stay on track.");
        nutritionPlans.put("recovery days", "Nutrition plan for recovery days: Use recovery days to refuel and replenish your body with nutrient-rich foods that support muscle repair and recovery. Focus on consuming high-quality proteins, complex carbohydrates, and healthy fats, and drink plenty of water to stay hydrated.");
        nutritionPlans.put("positive mindset", "Nutrition plan for a positive mindset: Cultivate a positive relationship with food by practicing mindful eating, listening to your body's hunger and fullness cues, and enjoying a variety of foods without guilt or restriction. Focus on nourishing your body with balanced meals and snacks that support overall health and well-being.");
        nutritionPlans.put("body composition", "Nutrition plan for body composition: Achieve and maintain a healthy body composition by eating a balanced diet that provides the nutrients your body needs to support muscle growth, repair, and maintenance. Focus on consuming adequate protein, carbohydrates, and healthy fats, and limit foods high in added sugars and unhealthy fats.");
        nutritionPlans.put("self-care", "Nutrition plan for self-care: Prioritize self-care by nourishing your body with wholesome, nutrient-rich foods that support overall health and well-being. Focus on eating a balanced diet that includes plenty of fruits, vegetables, whole grains, lean proteins, and healthy fats, and indulge in occasional treats or comfort foods in moderation.");
        nutritionPlans.put("flexibility training", "Nutrition plan for flexibility training: Support your flexibility training efforts by fueling your body with a balanced diet that includes a variety of nutrient-rich foods. Focus on consuming complex carbohydrates, lean proteins, and healthy fats to provide the energy and nutrients your muscles need to perform optimally and recover effectively.");
        nutritionPlans.put("recovery strategies", "Nutrition plan for recovery strategies: Enhance your recovery with nutrition by consuming nutrient-rich foods that support muscle repair and replenishment. Focus on consuming adequate protein, carbohydrates, and fluids, and include foods rich in antioxidants, vitamins, and minerals to support overall health and well-being.");
        nutritionPlans.put("portion sizes", "Nutrition plan for portion sizes: Practice portion control by measuring and/or weighing your food to ensure you're eating appropriate serving sizes. Use tools like measuring cups, food scales, or visual guides to estimate portion sizes, and focus on eating slowly and mindfully to avoid overeating.");
        nutritionPlans.put("mind-body connection", "Nutrition plan for mind-body connection: Cultivate a strong mind-body connection through nutrition by choosing foods that nourish your body and support your overall health and well-being. Focus on eating a balanced diet that includes a variety of nutrient-rich foods, and practice mindful eating to tune into your body's hunger and fullness cues.");
        nutritionPlans.put("social support", "Nutrition plan for social support: Surround yourself with a supportive network of friends, family, or peers who share your health and fitness goals. Use social support to stay motivated, accountable, and engaged in your nutrition journey, and seek out opportunities for group activities, cooking classes, or meal prep sessions to foster community and connection.");
        nutritionPlans.put("active lifestyle", "Nutrition plan for an active lifestyle: Fuel your active lifestyle with a balanced diet that provides the energy and nutrients your body needs to perform at its best. Focus on consuming a variety of nutrient-rich foods, including fruits, vegetables, whole grains, lean proteins, and healthy fats, and stay hydrated by drinking plenty of water throughout the day.");
        nutritionPlans.put("functional fitness", "Nutrition plan for functional fitness: Support your functional fitness goals with a balanced diet that provides the energy and nutrients your body needs to perform everyday activities with ease. Focus on consuming complex carbohydrates, lean proteins, and healthy fats to support energy levels, muscle function, and overall well-being.");
        nutritionPlans.put("body awareness", "Nutrition plan for body awareness: Develop body awareness through nutrition by paying attention to how different foods make you feel physically, mentally, and emotionally. Focus on eating foods that nourish and energize your body, and listen to your body's hunger and fullness cues to guide your eating habits and choices.");
        nutritionPlans.put("goal setting", "Nutrition plan for goal setting: Set specific, measurable, achievable, relevant, and time-bound (SMART) nutrition goals to help you stay focused, motivated, and on track. Break larger goals into smaller, manageable steps, and celebrate your progress along the way to keep yourself motivated and engaged in your nutrition journey.");
        nutritionPlans.put("mindful eating", "Nutrition plan for mindful eating: Practice mindful eating by paying attention to your food choices, hunger and fullness cues, and eating habits. Slow down, savor each bite, and eat without distractions to fully enjoy your meals and develop a healthier relationship with food.");
        nutritionPlans.put("meal prep tips", "Nutrition plan for meal prep tips: Simplify meal prep with tips like planning meals in advance, batch cooking staple ingredients, and storing meals in portioned containers for easy grab-and-go options. Focus on preparing balanced meals that include a variety of nutrient-rich foods to support your health and fitness goals.");
        nutritionPlans.put("post-workout nutrition", "Nutrition plan for post-workout nutrition: Refuel and recover after workouts with a balanced meal or snack that includes carbohydrates to replenish glycogen stores, protein to support muscle repair and growth, and fluids to rehydrate your body. Aim to eat within 30-60 minutes after exercise to optimize recovery.");
        nutritionPlans.put("meal timing", "Nutrition plan for meal timing: Pay attention to meal timing by spacing out meals and snacks evenly throughout the day to maintain steady energy levels and support overall health and well-being. Aim to eat every 3-4 hours, and include a balance of carbohydrates, protein, and healthy fats in each meal.");
        nutritionPlans.put("food tracking", "Nutrition plan for food tracking: Keep track of your food intake using a food diary, app, or journal to monitor your eating habits, track progress towards your goals, and identify areas for improvement. Focus on recording portion sizes, food choices, and meal timings to gain insight into your nutritional patterns and make informed decisions.");
        nutritionPlans.put("nutrition education", "Nutrition plan for nutrition education: Educate yourself about nutrition by reading books, articles, or reputable websites, attending workshops or seminars, or consulting with a registered dietitian or nutritionist. Focus on learning about the role of nutrients in the body, the importance of balanced eating, and how to make healthy food choices.");
        nutritionPlans.put("meal variety", "Nutrition plan for meal variety: Increase meal variety by experimenting with different foods, flavors, and cuisines to keep meals interesting and enjoyable. Focus on incorporating a variety of fruits, vegetables, whole grains, lean proteins, and healthy fats into your meals to ensure you get a wide range of nutrients.");
        nutritionPlans.put("portion control", "Nutrition plan for portion control: Practice portion control by measuring or estimating serving sizes to avoid overeating and manage calorie intake. Use tools like measuring cups, food scales, or visual cues to portion out foods, and focus on eating slowly and mindfully to recognize feelings of fullness and satisfaction.");
        nutritionPlans.put("nutrition labels", "Nutrition plan for reading nutrition labels: Learn to interpret nutrition labels to make informed food choices and monitor your nutrient intake. Focus on paying attention to serving sizes, calories, and nutrient content, and aim to choose foods that are high in essential nutrients and low in added sugars, saturated fats, and sodium.");
        nutritionPlans.put("meal frequency", "Nutrition plan for meal frequency: Experiment with different meal frequencies and patterns to find what works best for your appetite, energy levels, and lifestyle, whether it's eating smaller, more frequent meals or larger, less frequent meals. Focus on establishing a consistent eating pattern that supports your health and fitness goals.");
        nutritionPlans.put("nutrition coaching", "Nutrition plan for nutrition coaching: Consider working with a nutrition coach or registered dietitian to receive personalized guidance, support, and accountability in achieving your health and fitness goals. Focus on collaborating with a qualified professional to develop a nutrition plan tailored to your individual needs, preferences, and goals.");
        nutritionPlans.put("hydration strategies", "Nutrition plan for hydration strategies: Develop hydration strategies such as carrying a water bottle, flavoring water with fruit or herbs, or setting reminders to drink water throughout the day to maintain optimal hydration levels and support overall health and well-being.");
        nutritionPlans.put("meal planning tips", "Nutrition plan for meal planning tips: Master meal planning with tips like creating a weekly meal schedule, preparing grocery lists, and cooking versatile ingredients to streamline meal prep and encourage healthier eating habits. Focus on planning balanced meals that include a variety of nutrient-rich foods to support your health and fitness goals.");
        nutritionPlans.put("nutrient timing", "Nutrition plan for nutrient timing: Strategize nutrient timing by consuming carbohydrates and protein before and after workouts to fuel performance, support recovery, and maximize muscle protein synthesis. Focus on timing your meals and snacks to provide the energy and nutrients your body needs to perform optimally and recover effectively.");
        nutritionPlans.put("nutrition consultations", "Nutrition plan for nutrition consultations: Schedule nutrition consultations with a qualified professional to assess your dietary intake, address nutritional imbalances, and develop a personalized nutrition plan aligned with your goals. Focus on collaborating with a registered dietitian or nutritionist to receive expert advice, support, and guidance in achieving optimal health and well-being.");
        nutritionPlans.put("hydration habits", "Nutrition plan for hydration habits: Cultivate hydration habits by carrying a reusable water bottle, infusing water with fruits or herbs, or setting reminders to drink water throughout the day to maintain hydration status and support overall health and well-being. Focus on staying adequately hydrated to support physical and mental performance, digestion, and overall health.");
        nutritionPlans.put("meal frequency recommendations", "Nutrition plan for meal frequency recommendations: Experiment with different meal frequencies and patterns to find what works best for your appetite, energy levels, and fitness goals, whether it's eating three square meals a day or multiple smaller meals and snacks. Focus on establishing a regular eating pattern that supports your nutritional needs and lifestyle.");
        nutritionPlans.put("nutrition workshops", "Nutrition plan for nutrition workshops: Attend nutrition workshops, seminars, or webinars to expand your knowledge, learn practical tips, and stay up-to-date on the latest research and trends in nutrition and health. Focus on participating in educational events that provide evidence-based information and practical strategies for improving your nutrition and well-being.");
        nutritionPlans.put("hydration guidelines", "Nutrition plan for hydration guidelines: Follow hydration guidelines by drinking at least 8-10 glasses of water daily, and adjust fluid intake based on factors like activity level, climate, and individual hydration needs. Focus on staying adequately hydrated to support physical performance, cognitive function, and overall health and well-being.");
        nutritionPlans.put("meal timing strategies", "Nutrition plan for meal timing strategies: Optimize meal timing by consuming balanced meals and snacks at regular intervals throughout the day to maintain stable energy levels, support metabolism, and prevent overeating. Focus on spacing out meals and snacks evenly and listening to your body's hunger and fullness cues to guide your eating habits and choices.");
        nutritionPlans.put("nutrition resources", "Nutrition plan for nutrition resources: Explore nutrition resources like books, articles, websites, podcasts, and social media accounts to expand your knowledge, find inspiration, and discover practical tips for improving your nutrition and well-being. Focus on accessing reliable, evidence-based information from reputable sources to support your health and fitness goals.");
        nutritionPlans.put("hydration tips", "Nutrition plan for hydration tips: Stay hydrated by drinking water throughout the day, and add flavor to your water with lemon, cucumber, or mint for a refreshing twist. Focus on drinking water before, during, and after exercise, and listen to your body's thirst signals to ensure you're adequately hydrated at all times.");
        nutritionPlans.put("meal frequency recommendations", "Nutrition plan for meal frequency recommendations: Experiment with different meal frequencies and patterns to find what works best for your appetite, energy levels, and fitness goals, whether it's eating three square meals a day or multiple smaller meals and snacks. Focus on establishing a regular eating pattern that supports your nutritional needs and lifestyle.");
        nutritionPlans.put("nutrition workshops", "Nutrition plan for nutrition workshops: Attend nutrition workshops, seminars, or webinars to expand your knowledge, learn practical tips, and stay up-to-date on the latest research and trends in nutrition and health. Focus on participating in educational events that provide evidence-based information and practical strategies for improving your nutrition and well-being.");
        nutritionPlans.put("hydration guidelines", "Nutrition plan for hydration guidelines: Follow hydration guidelines by drinking at least 8-10 glasses of water daily, and adjust fluid intake based on factors like activity level, climate, and individual hydration needs. Focus on staying adequately hydrated to support physical performance, cognitive function, and overall health and well-being.");
        nutritionPlans.put("meal timing strategies", "Nutrition plan for meal timing strategies: Optimize meal timing by consuming balanced meals and snacks at regular intervals throughout the day to maintain stable energy levels, support metabolism, and prevent overeating. Focus on spacing out meals and snacks evenly and listening to your body's hunger and fullness cues to guide your eating habits and choices.");
        nutritionPlans.put("nutrition resources", "Nutrition plan for nutrition resources: Explore nutrition resources like books, articles, websites, podcasts, and social media accounts to expand your knowledge, find inspiration, and discover practical tips for improving your nutrition and well-being. Focus on accessing reliable, evidence-based information from reputable sources to support your health and fitness goals.");
        nutritionPlans.put("hydration tips", "Nutrition plan for hydration tips: Stay hydrated by drinking water throughout the day, and add flavor to your water with lemon, cucumber, or mint for a refreshing twist. Focus on drinking water before, during, and after exercise, and listen to your body's thirst signals to ensure you're adequately hydrated at all times.");
        nutritionPlans.put("drink water", "Ensure adequate hydration alongside alcoholic beverages to counteract dehydration effects.");
        nutritionPlans.put("thin", "Nutrition plan for a 'thin' goal: Focus on consuming lean protein sources like chicken breast, fish, and tofu. Include plenty of vegetables and whole grains, and limit processed foods and sugary snacks.");
        nutritionPlans.put("fat", "Nutrition plan for a 'fat' goal: Focus on consuming a balanced diet with plenty of protein, healthy fats, and complex carbohydrates. Incorporate lean protein sources like chicken, fish, and beans, and include plenty of fruits and vegetables.");
        nutritionPlans.put("hydration", "Stay hydrated by drinking at least 8 glasses of water per day. Incorporate hydrating foods such as fruits and vegetables into your diet.");
        nutritionPlans.put("balanced diet", "A balanced diet includes a variety of nutrient-rich foods from all food groups. Aim for a colorful plate filled with fruits, vegetables, lean proteins, whole grains, and healthy fats.");
        nutritionPlans.put("portion control", "Practice portion control by being mindful of serving sizes and listening to your body's hunger and fullness cues. Avoid oversized portions and eat slowly to savor your food.");
        nutritionPlans.put("meal prep", "Meal prepping can help you stay on track with your nutrition goals by preparing healthy meals in advance. Spend some time each week planning and preparing meals to save time and make healthier choices.");
        nutritionPlans.put("nutrient timing", "Timing your nutrient intake around workouts can optimize performance and recovery. Aim to consume a balanced meal or snack containing carbohydrates and protein within 30 minutes to 2 hours before and after exercise.");
        nutritionPlans.put("supplementation", "Supplements can complement a healthy diet, but they should not replace whole foods. Consult with a healthcare professional to determine if you have any nutrient deficiencies and to discuss appropriate supplementation.");
        nutritionPlans.put("health", "Nutrition plan for a 'healthy' goal: Focus on consuming a variety of nutrient-rich foods, including fruits, vegetables, whole grains, lean proteins, and healthy fats. Limit processed foods, sugary snacks, and excessive alcohol consumption.");
        }

        private String generateResponse(String goal) {
        String response;
        String lowercaseGoal = goal.toLowerCase();

        if (lowercaseGoal.contains("thin")) {
            response = keywordResponses.get("thin");
        } else if (lowercaseGoal.contains("fat")) {
            response = keywordResponses.get("fat");
        } else if (lowercaseGoal.contains("healthy")) {
            response = keywordResponses.get("healthy");
        } else if (lowercaseGoal.contains("muscles")) {
            response = keywordResponses.get("muscles");
        } else if (lowercaseGoal.contains("precautions")) {
            response = keywordResponses.get("precautions");
        } else if (lowercaseGoal.contains("good habits")) {
            response = keywordResponses.get("good habits");
        } else if (lowercaseGoal.contains("foods to avoid")) {
            response = keywordResponses.get("foods to avoid");
        } else if (lowercaseGoal.contains("alcohol")) {
            response = keywordResponses.get("alcohol");
        } else if (lowercaseGoal.contains("protein")) {
            response = keywordResponses.get("protein");
        } else if (lowercaseGoal.contains("carbs")) {
            response = keywordResponses.get("carbs");
        } else if (lowercaseGoal.contains("cardio")) {
            response = keywordResponses.get("cardio");
        } else if (lowercaseGoal.contains("strength training")) {
            response = keywordResponses.get("strength training");
        } else if (lowercaseGoal.contains("nutrient-rich")) {
            response = keywordResponses.get("nutrient-rich");
        } else if (lowercaseGoal.contains("calorie deficit")) {
            response = keywordResponses.get("calorie deficit");
        } else if (lowercaseGoal.contains("calorie surplus")) {
            response = keywordResponses.get("calorie surplus");
        } else if (lowercaseGoal.contains("balanced diet")) {
            response = keywordResponses.get("balanced diet");
        } else if (lowercaseGoal.contains("lean proteins")) {
            response = keywordResponses.get("lean proteins");
        } else if (lowercaseGoal.contains("whole grains")) {
            response = keywordResponses.get("whole grains");
        } else if (lowercaseGoal.contains("high-intensity")) {
            response = keywordResponses.get("high-intensity");
        } else if (lowercaseGoal.contains("muscle growth")) {
            response = keywordResponses.get("muscle growth");
        } else if (lowercaseGoal.contains("muscle repair")) {
            response = keywordResponses.get("muscle repair");
        } else if (lowercaseGoal.contains("warm-up")) {
            response = keywordResponses.get("warm-up");
        } else if (lowercaseGoal.contains("cool down")) {
            response = keywordResponses.get("cool down");
        } else if (lowercaseGoal.contains("regular exercise")) {
            response = keywordResponses.get("regular exercise");
        } else if (lowercaseGoal.contains("adequate sleep")) {
            response = keywordResponses.get("adequate sleep");
        } else if (lowercaseGoal.contains("hydration")) {
            response = keywordResponses.get("hydration");
        } else if (lowercaseGoal.contains("processed foods")) {
            response = keywordResponses.get("processed foods");
        } else if (lowercaseGoal.contains("sugary snacks")) {
            response = keywordResponses.get("sugary snacks");
        } else if (lowercaseGoal.contains("fast food")) {
            response = keywordResponses.get("fast food");
        } else if (lowercaseGoal.contains("liver disease")) {
            response = keywordResponses.get("liver disease");
        } else if (lowercaseGoal.contains("obesity")) {
            response = keywordResponses.get("obesity");
        } else if (lowercaseGoal.contains("energy levels")) {
            response = keywordResponses.get("energy levels");
        } else if (lowercaseGoal.contains("heart health")) {
            response = keywordResponses.get("heart health");
        } else if (lowercaseGoal.contains("burning calories")) {
            response = keywordResponses.get("burning calories");
        } else if (lowercaseGoal.contains("cardiovascular health")) {
            response = keywordResponses.get("cardiovascular health");
        } else if (lowercaseGoal.contains("lean muscle")) {
            response = keywordResponses.get("lean muscle");
        } else if (lowercaseGoal.contains("physical activity")) {
            response = keywordResponses.get("physical activity");
        } else if (lowercaseGoal.contains("fiber-rich")) {
            response = keywordResponses.get("fiber-rich");
        } else if (lowercaseGoal.contains("vitamins")) {
            response = keywordResponses.get("vitamins");
        } else if (lowercaseGoal.contains("minerals")) {
            response = keywordResponses.get("minerals");
        } else if (lowercaseGoal.contains("antioxidants")) {
            response = keywordResponses.get("antioxidants");
        } else if (lowercaseGoal.contains("electrolytes")) {
            response = keywordResponses.get("electrolytes");
        } else if (lowercaseGoal.contains("portion control")) {
            response = keywordResponses.get("portion control");
        } else if (lowercaseGoal.contains("metabolism")) {
            response = keywordResponses.get("metabolism");
        } else if (lowercaseGoal.contains("flexibility")) {
            response = keywordResponses.get("flexibility");
        } else if (lowercaseGoal.contains("mobility")) {
            response = keywordResponses.get("mobility");
        } else if (lowercaseGoal.contains("joint health")) {
            response = keywordResponses.get("joint health");
        } else if (lowercaseGoal.contains("stress management"))
        {
            response = keywordResponses.get("height");
        } else if (lowercaseGoal.contains("drinking")) {
            response = keywordResponses.get("drinking");
        } else if (lowercaseGoal.contains("drink water")) {
            response = keywordResponses.get("drink water");
        } else if (lowercaseGoal.contains("water")) {
            response = keywordResponses.get("water");
        } else if (lowercaseGoal.contains("cigarette")) {
            response = keywordResponses.get("cigarette");
        } else if (lowercaseGoal.contains("food")) {
            response = keywordResponses.get("food");
        } else if (lowercaseGoal.contains("junk food")) {
            response = keywordResponses.get("junk food");
        } else if (lowercaseGoal.contains("sex")) {
            response = keywordResponses.get("sex");
        } else if (lowercaseGoal.contains("focus")) {
            response = keywordResponses.get("focus");
        } else if (lowercaseGoal.contains("discipline")) {
            response = keywordResponses.get("discipline");
        } else if (lowercaseGoal.contains("motivation")) {
            response = keywordResponses.get("motivation");
        } else if (lowercaseGoal.contains("wellness")) {
            response = keywordResponses.get("wellness");
        } else if (lowercaseGoal.contains("fitness")) {
            response = keywordResponses.get("fitness");
        } else if (lowercaseGoal.contains("health")) {
            response = keywordResponses.get("health");
        } else if (lowercaseGoal.contains("mindfulness")) {
            response = keywordResponses.get("mindfulness");}
        else if (lowercaseGoal.contains("stress"))  {
            response = keywordResponses.get("stress management");
        } else if (lowercaseGoal.contains("mental well-being")) {
            response = keywordResponses.get("mental well-being");
        } else {
            response = "Invalid fitness goal. Please specify a goal related to one of the provided keywords.";
        }
        return response;
    }


    private String generateNutritionPlan(String goal) {
        String nutritionPlan;
        String lowercaseGoal = goal.toLowerCase();

        if (lowercaseGoal.contains("thin")) {
            nutritionPlan = nutritionPlans.get("thin");
        } else if (lowercaseGoal.contains("fat")) {
            nutritionPlan = nutritionPlans.get("fat");
        } else if (lowercaseGoal.contains("healthy")) {
            nutritionPlan = nutritionPlans.get("healthy");
        } else if (lowercaseGoal.contains("muscles")) {
            nutritionPlan = nutritionPlans.get("muscles");
        } else if (lowercaseGoal.contains("precautions")) {
            nutritionPlan = nutritionPlans.get("precautions");
        } else if (lowercaseGoal.contains("good habits")) {
            nutritionPlan = nutritionPlans.get("good habits");
        } else if (lowercaseGoal.contains("foods to avoid")) {
            nutritionPlan = nutritionPlans.get("foods to avoid");
        } else if (lowercaseGoal.contains("alcohol")) {
            nutritionPlan = nutritionPlans.get("alcohol");
        } else if (lowercaseGoal.contains("protein")) {
            nutritionPlan = nutritionPlans.get("protein");
        } else if (lowercaseGoal.contains("carbs")) {
            nutritionPlan = nutritionPlans.get("carbs");
        } else if (lowercaseGoal.contains("cardio")) {
            nutritionPlan = nutritionPlans.get("cardio");
        } else if (lowercaseGoal.contains("strength training")) {
            nutritionPlan = nutritionPlans.get("strength training");
        } else if (lowercaseGoal.contains("nutrient-rich")) {
            nutritionPlan = nutritionPlans.get("nutrient-rich");
        } else if (lowercaseGoal.contains("calorie deficit")) {
            nutritionPlan = nutritionPlans.get("calorie deficit");
        } else if (lowercaseGoal.contains("calorie surplus")) {
            nutritionPlan = nutritionPlans.get("calorie surplus");
        } else if (lowercaseGoal.contains("balanced diet")) {
            nutritionPlan = nutritionPlans.get("balanced diet");
        } else if (lowercaseGoal.contains("lean proteins")) {
            nutritionPlan = nutritionPlans.get("lean proteins");
        } else if (lowercaseGoal.contains("whole grains")) {
            nutritionPlan = nutritionPlans.get("whole grains");
        } else if (lowercaseGoal.contains("high-intensity")) {
            nutritionPlan = nutritionPlans.get("high-intensity");
        } else if (lowercaseGoal.contains("muscle growth")) {
            nutritionPlan = nutritionPlans.get("muscle growth");
        } else if (lowercaseGoal.contains("muscle repair")) {
            nutritionPlan = nutritionPlans.get("muscle repair");
        } else if (lowercaseGoal.contains("warm-up")) {
            nutritionPlan = nutritionPlans.get("warm-up");
        } else if (lowercaseGoal.contains("cool down")) {
            nutritionPlan = nutritionPlans.get("cool down");
        } else if (lowercaseGoal.contains("regular exercise")) {
            nutritionPlan = nutritionPlans.get("regular exercise");
        } else if (lowercaseGoal.contains("adequate sleep")) {
            nutritionPlan = nutritionPlans.get("adequate sleep");
        } else if (lowercaseGoal.contains("hydration")) {
            nutritionPlan = nutritionPlans.get("hydration");
        } else if (lowercaseGoal.contains("processed foods")) {
            nutritionPlan = nutritionPlans.get("processed foods");
        } else if (lowercaseGoal.contains("sugary snacks")) {
            nutritionPlan = nutritionPlans.get("sugary snacks");
        } else if (lowercaseGoal.contains("fast food")) {
            nutritionPlan = nutritionPlans.get("fast food");
        } else if (lowercaseGoal.contains("liver disease")) {
            nutritionPlan = nutritionPlans.get("liver disease");
        } else if (lowercaseGoal.contains("obesity")) {
            nutritionPlan = nutritionPlans.get("obesity");
        } else if (lowercaseGoal.contains("energy levels")) {
            nutritionPlan = nutritionPlans.get("energy levels");
        } else if (lowercaseGoal.contains("heart health")) {
            nutritionPlan = nutritionPlans.get("heart health");
        } else if (lowercaseGoal.contains("burning calories")) {
            nutritionPlan = nutritionPlans.get("burning calories");
        } else if (lowercaseGoal.contains("cardiovascular health")) {
            nutritionPlan = nutritionPlans.get("cardiovascular health");
        } else if (lowercaseGoal.contains("lean muscle")) {
            nutritionPlan = nutritionPlans.get("lean muscle");
        } else if (lowercaseGoal.contains("physical activity")) {
            nutritionPlan = nutritionPlans.get("physical activity");
        } else if (lowercaseGoal.contains("fiber-rich")) {
            nutritionPlan = nutritionPlans.get("fiber-rich");
        } else if (lowercaseGoal.contains("vitamins")) {
            nutritionPlan = nutritionPlans.get("vitamins");
        } else if (lowercaseGoal.contains("minerals")) {
            nutritionPlan = nutritionPlans.get("minerals");
        } else if (lowercaseGoal.contains("antioxidants")) {
            nutritionPlan = nutritionPlans.get("antioxidants");
        } else if (lowercaseGoal.contains("electrolytes")) {
            nutritionPlan = nutritionPlans.get("electrolytes");
        } else if (lowercaseGoal.contains("portion control")) {
            nutritionPlan = nutritionPlans.get("portion control");
        } else if (lowercaseGoal.contains("metabolism")) {
            nutritionPlan = nutritionPlans.get("metabolism");
        } else if (lowercaseGoal.contains("flexibility")) {
            nutritionPlan = nutritionPlans.get("flexibility");
        } else if (lowercaseGoal.contains("mobility")) {
            nutritionPlan = nutritionPlans.get("mobility");
        } else if (lowercaseGoal.contains("joint health")) {
            nutritionPlan = nutritionPlans.get("joint health");
        } else if (lowercaseGoal.contains("stress management")) {
            nutritionPlan = nutritionPlans.get("stress management");
        } else if (lowercaseGoal.contains("mental well-being")) {
            nutritionPlan = nutritionPlans.get("mental well-being");}
            else if (lowercaseGoal.contains("drink water")) {
                nutritionPlan = nutritionPlans.get("drink water");
            } else if (lowercaseGoal.contains("hydration")) {
                nutritionPlan = nutritionPlans.get("hydration");
            } else if (lowercaseGoal.contains("balanced diet")) {
                nutritionPlan = nutritionPlans.get("balanced diet");
            } else if (lowercaseGoal.contains("portion control")) {
                nutritionPlan = nutritionPlans.get("portion control");
            } else if (lowercaseGoal.contains("meal prep")) {
                nutritionPlan = nutritionPlans.get("meal prep");
            } else if (lowercaseGoal.contains("nutrient timing")) {
                nutritionPlan = nutritionPlans.get("nutrient timing");
            } else if (lowercaseGoal.contains("supplementation")) {
                nutritionPlan = nutritionPlans.get("supplementation");
        } else if (lowercaseGoal.contains("health")) {
            nutritionPlan = nutritionPlans.get("health");
        } else {
            nutritionPlan = "No nutrition plan available for this goal.";
        }
        return nutritionPlan;
    }

        private void createPlan() {
        String goal = goalField.getText().trim();
        if (goal.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a fitness goal.");
            return;
        }

        String response = generateResponse(goal);
        String nutritionPlan = generateNutritionPlan(goal);

        workoutArea.setText(response);
        nutritionArea.setText(nutritionPlan);
    }

    private void savePlan() {
        String workoutContent = workoutArea.getText();
        String nutritionContent = nutritionArea.getText();

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Specify a file to save");

        int userSelection = fileChooser.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileToSave + ".txt"))) {
                writer.write("Workout Plan:\n\n" + workoutContent + "\n\nNutrition Plan:\n\n" + nutritionContent);
                JOptionPane.showMessageDialog(this, "File saved successfully!");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error saving file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FitnessBot gymPlan = new FitnessBot();
            gymPlan.setVisible(true);
        });
    }
}