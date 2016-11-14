package com.techoffice.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.techoffice.example.controller.FxmlController;
import com.techoffice.example.javafx.SpringFxmlLoader;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * 
 * A Simple Spring Inversion of Control Simple through XML Configuration File (ClassPathXmlApplicationContext) 
 * 
 * @author Ben_c
 *
 */
public class HelloWorldExample extends Application{
	
	@Autowired
	private FxmlController fxmlController;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		SpringFxmlLoader loader = new SpringFxmlLoader();
        VBox root = (VBox) loader.load("FxmlControler.fxml");
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("FXML Controller Example");
        primaryStage.show();
	}
	
	public void run(String[] args){
		
		launch(args);
	}
	
	/**
	 * Main Program 
	 * @param args
	 */
	public static void main(String[] args){
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		HelloWorldExample helloWorldExample = context.getBean(HelloWorldExample.class);
		FxmlController fxmlController = context.getBean(FxmlController.class);
		fxmlController.setInputText1Value("abc");
		helloWorldExample.run(args);
	}
}
