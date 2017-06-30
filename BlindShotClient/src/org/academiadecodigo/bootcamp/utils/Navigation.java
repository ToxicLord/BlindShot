package org.academiadecodigo.bootcamp.utils;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.academiadecodigo.bootcamp.Client;
import org.academiadecodigo.bootcamp.Main;
import org.academiadecodigo.bootcamp.controller.GridController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by codecadet on 23/06/17.
 */
public final class Navigation {

    private static Navigation navigation = null;
    private Stage stage;
    private Scene scene;
    public static final int WIDTH = 600;
    public static final int HEIGHT = 600;

    private Map<String, Initializable> controllers = new HashMap<>();

    private BufferedReader in = null;
    private PrintStream out = null;
    private static Socket clientSocket = null;

    private Client client;

    private int turn = 0;

    private Navigation() {

    }

    public void connectToSocket() {

    }

    public static synchronized Navigation getInstance() {
        if(navigation == null) {
            navigation = new Navigation();
        }

        return navigation;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void loadScreen(String view) {

        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/grid.fxml"));

            GridPane root = fxmlLoader.load();

            GridController gridController = fxmlLoader.getController();

            System.out.println(gridController);

            controllers.put(view, gridController);

            //((GridController)gridController).setClient(client);

            scene = new Scene(root, WIDTH, HEIGHT);
            setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setScene(Scene scene) {
        stage.setScene(scene);
        stage.show();
    }

    public Scene getScene() {
        return scene;
    }

    public void setClientSocket(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public Socket getClientSocket() {
        return clientSocket;
    }

    public BufferedReader getIn() {
        return in;
    }

    public PrintStream getOut() {
        return out;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public int getTurn() {
        return turn;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Map<String, Initializable> getControllers() {
        return controllers;
    }
}