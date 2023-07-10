package com.sh1rl0ck.tower_of_hanoi;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class TowerOfHanoiController {
    private List<Double> rodsCenters;
    private int rods;
    private double rodWidth;
    private double rodHeight;

    private List<List<Rectangle>> disks;

    @FXML
    private TextField numberOfDisksInput;

    @FXML
    private AnchorPane animationPane;

    private void drawRods() {
        double rodDistance = (animationPane.getPrefWidth() - rods * rodWidth) / (rods + 1);
        double rodY = animationPane.getPrefHeight() - rodHeight;
        for(int i = 0; i < rods; i++) {
            Rectangle rod = new Rectangle(15, 250);
            animationPane.getChildren().add(rod);
            double rodX = rodDistance * (i+1) + rodWidth * i;
            rodsCenters.add(rodX + rodWidth / 2);
            rod.relocate(rodX, rodY);
        }
    }

    private void drawDisks(int numberOfRods) {
        double diskWidth = 150;
        for (int i = 1; i <= numberOfRods; i++) {
            Rectangle disk = new Rectangle(diskWidth, 30);
            animationPane.getChildren().add(disk);
            disk.setFill(Color.BLUE);
            disk.relocate(rodsCenters.get(0) - diskWidth / 2, 200);
        }
    }

    @FXML
    protected void start() {
    }

    @FXML
    protected void reset() {
        this.animationPane.getChildren().clear();
        this.disks.clear();
        this.rodsCenters.clear();
        this.drawRods();
        this.drawDisks(Integer.parseInt(this.numberOfDisksInput.getText()));
    }

    public void initialize() {
        this.rodsCenters = new ArrayList<>();
        this.disks = new ArrayList<>();
        this.rods = 3;
        this.rodWidth = 16;
        this.rodHeight = 250;
        reset();
    }
}