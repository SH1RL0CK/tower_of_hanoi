package com.sh1rl0ck.tower_of_hanoi;

import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class TowerOfHanoiController {
    private List<Double> rodsCenters;
    private int numberOfRods;
    private double rodWidth;
    private double rodHeight;
    private double diskHeight;
    private double maxDiskWidth;
    private double minDiskWidth;
    private List<List<Rectangle>> disks;
    private List<Double> diskYs;
    private SequentialTransition transitions;
    private double transitionDuration;
    @FXML
    private TextField numberOfDisksInput;

    @FXML
    private AnchorPane animationPane;

    private void drawRods() {
        double rodDistance = (animationPane.getPrefWidth() - numberOfRods * rodWidth) / (numberOfRods + 1);
        double rodY = animationPane.getPrefHeight() - rodHeight;
        for(int i = 0; i < numberOfRods; i++) {
            Rectangle rod = new Rectangle(15, 250);
            animationPane.getChildren().add(rod);
            double rodX = rodDistance * (i+1) + rodWidth * i;
            rodsCenters.add(rodX + rodWidth / 2);
            rod.relocate(rodX, rodY);
        }
    }

    private void drawDisks(int numberOfDisks) {
        double diskWidthStep = (maxDiskWidth - minDiskWidth) / numberOfDisks;
        double diskDistance = (rodHeight - diskHeight * numberOfDisks ) / (numberOfDisks + 1);
        for (int i = numberOfDisks-1; i >= 0 ; i--) {
            double diskWidth = minDiskWidth + i * diskWidthStep;
            double diskX = rodsCenters.get(0) - diskWidth / 2;
            double diskY = animationPane.getPrefHeight() - rodHeight +  (i + 1) * diskDistance + i * diskHeight;
            diskYs.add(diskY);
            Rectangle disk = new Rectangle(diskWidth, diskHeight);
            animationPane.getChildren().add(disk);
            disk.setFill(Color.BLUE);
            disk.relocate(diskX, diskY);
            disks.get(0).add(disk);
        }
    }

    private void moveDisk(int source, int target) {
        Rectangle disk = disks.get(source).get(disks.get(source).size() - 1);
        double diskFromX = rodsCenters.get(source) - disk.getWidth() / 2;
        double diskToX = rodsCenters.get(target) - disk.getWidth() / 2;
        double diskFromY = diskYs.get(disks.get(source).size() - 1);
        double diskToY = diskYs.get(disks.get(target).size());
        TranslateTransition transition = new TranslateTransition(Duration.millis(transitionDuration), disk);
        transition.setByX(diskToX - diskFromX );
        transition.setByY(diskToY - diskFromY);
        transitions.getChildren().add(transition);
        disks.get(source).remove( disks.get(source).size() - 1);
        disks.get(target).add(disk);
    }

    private void hanoi(int n, int source, int helper, int target) {
        if(n > 0) {
            hanoi(n - 1, source, target, helper);
            if(!disks.get(source).isEmpty()) {
                moveDisk(source, target);
            }
            hanoi(n - 1, helper, source, target);
        }
    }

    @FXML
    protected void start() {
        reset();
        transitions.getChildren().add(new PauseTransition(Duration.millis(1000)));
        hanoi(Integer.parseInt(numberOfDisksInput.getText()), 0, 1, 2);
        transitions.play();
    }

    @FXML
    protected void reset() {
        animationPane.getChildren().clear();
        for (List<Rectangle> rod: disks) {
            rod.clear();
        }
        rodsCenters.clear();
        diskYs.clear();
        this.transitions.getChildren().clear();
        drawRods();
        drawDisks(Integer.parseInt(numberOfDisksInput.getText()));
    }

    public void initialize() {
        this.rodsCenters = new ArrayList<>();
        this.diskYs = new ArrayList<>();
        this.disks = new ArrayList<>();
        this.transitions = new SequentialTransition();
        this.numberOfRods = 3;
        this.rodWidth = 16;
        this.rodHeight = 250;
        this.diskHeight = 20;
        this.maxDiskWidth = 160;
        this.minDiskWidth = 50;
        for (int i = 0; i < numberOfRods; i++) {
            this.disks.add(new ArrayList<>());
        }
        this.transitionDuration = 500;
        reset();
    }
}