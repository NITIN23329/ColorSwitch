package SaveScreen;

import All_Obstacle.*;
import Screen_move.Ball;
import Screen_move.Coordinate;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.StrokeLineCap;
import mainWindow.MainWindowMain;
import mainWindow.PropertyOfSavedGames;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Save {
    private final String fileName;

    public Save(String fileName) {
        this.fileName = fileName;
    }

    public void saveData(Ball ball, Label scorelabel) throws IOException {
        String save=Arrays.toString(ball.getBoolean_obstacle_arr()) + '\n';

        for (int i = 0; i <ball.getObstacle_on_screen().length; i++) {
            Obstacle obstacle=ball.getObstacle_on_screen()[i];
            if(obstacle.getClass()==Circular_Obstacle.class)
            {
                save+= "0"+'\n';
            }
            if(obstacle.getClass()==Square_Obstacle.class)
            {
                save+="1"+'\n';
            }
            if(obstacle.getClass()==Star.class)
            {
                save+="2"+'\n';
            }
            if(obstacle.getClass()==Line_Obstacle.class)
            {
                save+="3"+'\n';
            }
            if(obstacle.getClass()==Double_Plus_Obstacle.class)
            {
                save+="4"+"\n";
            }
            if(obstacle.getClass()==Double_Circle_Obstacle.class)
            {
                save+="5"+'\n';
            }
            if(obstacle.getClass()==Square_Circle_Obstacle.class)
            {
                save+="6"+'\n';
            }
            if(obstacle.getClass()==Color_Switch.class)
            {
                save+="7"+'\n';
            }
            if(obstacle.getClass()==Plus_Obstacle.class)
            {
                save+="9"+'\n';
            }
        }

        save += getColor(ball.getCircle().getFill()) + "," + ball.getCircle().getRadius() + "," + ball.getCircle().getLayoutX() + "," + ball.getCircle().getLayoutY() + "," + ball.getLevelCount()+ '\n';
        Obstacle[] obstacles=ball.getObstacle_on_screen();
        for (Obstacle obstacle:obstacles) {
            if(obstacle.getClass()== Circular_Obstacle.class)
            {
                save+=saveCircle((Circular_Obstacle)obstacle);
            }
            if(obstacle.getClass()== Square_Obstacle.class)
            {
                Square_Obstacle square_obstacle = (Square_Obstacle) obstacle;
                save += saveSquarePlus(square_obstacle.getSquare1()) + square_obstacle.getInitialPosition1() + '\n';
                save += saveSquarePlus(square_obstacle.getSquare2()) + square_obstacle.getInitialPosition2() + '\n';
                save += saveSquarePlus(square_obstacle.getSquare3()) + square_obstacle.getInitialPosition3() + '\n';
                save += saveSquarePlus(square_obstacle.getSquare4()) + square_obstacle.getInitialPosition4() + '\n';
                save += Double.toString(square_obstacle.getStartingTime()) + '\n';
            }
            if(obstacle.getClass()== Star.class)
            {
                Star star=(Star)obstacle;
                save += saveStars(star);
            }
            if(obstacle.getClass()== Line_Obstacle.class)
            {
                Line_Obstacle line_obstacle = (Line_Obstacle) obstacle;
                save += saveLine(line_obstacle.getLine1()) + line_obstacle.getInitialPosition1() + '\n';
                save += saveLine(line_obstacle.getLine2()) + line_obstacle.getInitialPosition2() + '\n';
                save += saveLine(line_obstacle.getLine3()) + line_obstacle.getInitialPosition3() + '\n';
                save += saveLine(line_obstacle.getLine4()) + line_obstacle.getInitialPosition4() + '\n';
                save += Double.toString(line_obstacle.getStartingLevelTime()) + '\n';
            }
            if(obstacle.getClass()==Double_Plus_Obstacle.class)
            {
                Double_Plus_Obstacle doublePlusObstacle = (Double_Plus_Obstacle) obstacle;
                save += saveSquarePlus(doublePlusObstacle.getPlus_obstacle_left().getPlus1()) + doublePlusObstacle.getPlus_obstacle_left().getInitialPosition1() + '\n';
                save += saveSquarePlus(doublePlusObstacle.getPlus_obstacle_left().getPlus2()) + doublePlusObstacle.getPlus_obstacle_left().getInitialPosition2() + '\n';
                save += saveSquarePlus(doublePlusObstacle.getPlus_obstacle_left().getPlus3()) + doublePlusObstacle.getPlus_obstacle_left().getInitialPosition3() + '\n';
                save += saveSquarePlus(doublePlusObstacle.getPlus_obstacle_left().getPlus4()) + doublePlusObstacle.getPlus_obstacle_left().getInitialPosition4() + '\n';
                save += doublePlusObstacle.getPlus_obstacle_left().getStartingTime() + "," + doublePlusObstacle.getPlus_obstacle_left().isClockwise() + '\n';

                save += saveSquarePlus(doublePlusObstacle.getPlus_obstacle_right().getPlus1()) + doublePlusObstacle.getPlus_obstacle_right().getInitialPosition1() + '\n';
                save += saveSquarePlus(doublePlusObstacle.getPlus_obstacle_right().getPlus2()) + doublePlusObstacle.getPlus_obstacle_right().getInitialPosition2() + '\n';
                save += saveSquarePlus(doublePlusObstacle.getPlus_obstacle_right().getPlus3()) + doublePlusObstacle.getPlus_obstacle_right().getInitialPosition3() + '\n';
                save += saveSquarePlus(doublePlusObstacle.getPlus_obstacle_right().getPlus4()) + doublePlusObstacle.getPlus_obstacle_right().getInitialPosition4() + '\n';
                save += doublePlusObstacle.getPlus_obstacle_right().getStartingTime() + "," + doublePlusObstacle.getPlus_obstacle_right().isClockwise() + '\n';

            }
            if(obstacle.getClass()==Double_Circle_Obstacle.class)
            {
                Double_Circle_Obstacle doubleCircleObstacle = (Double_Circle_Obstacle) obstacle;
                save += saveCircle(doubleCircleObstacle.getLeft_circle()) ;
                save += saveCircle(doubleCircleObstacle.getRight_circle()) ;
            }
            if(obstacle.getClass()==Square_Circle_Obstacle.class)
            {
                Square_Circle_Obstacle squareCircleObstacle = (Square_Circle_Obstacle) obstacle;
                save += saveSquarePlus(squareCircleObstacle.getSquare_obstacle().getSquare1()) + squareCircleObstacle.getSquare_obstacle().getInitialPosition1() + '\n';
                save += saveSquarePlus(squareCircleObstacle.getSquare_obstacle().getSquare2()) + squareCircleObstacle.getSquare_obstacle().getInitialPosition2() + '\n';
                save += saveSquarePlus(squareCircleObstacle.getSquare_obstacle().getSquare3()) + squareCircleObstacle.getSquare_obstacle().getInitialPosition3() + '\n';
                save += saveSquarePlus(squareCircleObstacle.getSquare_obstacle().getSquare4()) + squareCircleObstacle.getSquare_obstacle().getInitialPosition4() + '\n';
                save = save + squareCircleObstacle.getSquare_obstacle().getStartingTime() + '\n';
                save += saveCircle(squareCircleObstacle.getCircular_obstacle());
            }
            if(obstacle.getClass()==Color_Switch.class)
            {
                Color_Switch colorSwitch = (Color_Switch) obstacle;
                save += saveCircle(colorSwitch.getColor_switch_circle());
            }
            if(obstacle.getClass()==Plus_Obstacle.class)
            {
                Plus_Obstacle plus_obstacle = (Plus_Obstacle) obstacle;
                save += saveSquarePlus(plus_obstacle.getPlus1()) + plus_obstacle.getInitialPosition1() + '\n';
                save += saveSquarePlus(plus_obstacle.getPlus2()) + plus_obstacle.getInitialPosition2() + '\n';
                save += saveSquarePlus(plus_obstacle.getPlus3()) + plus_obstacle.getInitialPosition3() + '\n';
                save += saveSquarePlus(plus_obstacle.getPlus4()) + plus_obstacle.getInitialPosition4() + '\n';
                save += plus_obstacle.getStartingTime() + "," + plus_obstacle.isClockwise() + '\n';
            }
        }
        save += scorelabel.getText() + '\n';
        save += MainWindowMain.score + "," + MainWindowMain.highScore + "," + MainWindowMain.total_Star;
        PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(fileName, true))); //true means open in append mode
        printWriter.println(save);
        printWriter.close();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Saved");
        alert.setHeaderText(null);
        alert.setContentText("Your Information has been saved!");
        alert.showAndWait();

    }

    private String saveCircle(Circular_Obstacle circular_obstacle) {
        return circular_obstacle.getArc1().getCenterX() + "," + circular_obstacle.getArc1().getCenterY() + "," + circular_obstacle.getArc1().getLayoutX() + "," + circular_obstacle.getArc1().getLayoutY() + "," + circular_obstacle.getArc1().getLength() + "," + circular_obstacle.getArc1().getRadiusX() + "," +
                circular_obstacle.getArc1().getRadiusY() + "," + circular_obstacle.getInitialPosition1() + "," + circular_obstacle.getInitialPosition2() + "," + circular_obstacle.getInitialPosition3() + "," + circular_obstacle.getInitialPosition4() + "," +
                circular_obstacle.getArc1().getStartAngle() + "," + circular_obstacle.isClockwise() + "," + circular_obstacle.isColor_switch() + "," + circular_obstacle.getStartingLevelTime()+'\n';

    }

    private String saveSquarePlus(Line line) {
        return line.getStartX() + "," + line.getEndX() + "," + line.getLayoutX() + "," + line.getLayoutY() + "," + line.getRotate() + "," + getColor(line.getStroke()) + ",";
    }

    private String saveLine(Line line) {
        return line.getEndX() + "," + line.getLayoutX() + "," + line.getLayoutY() + "," + getColor(line.getStroke()) + ",";
    }

    private String saveStars(Star star1) {
        String save = star1.getUpper_part().getLayoutX() + "," + star1.getUpper_part().getLayoutY() + "," + star1.getUpper_part().getRotate() + "," + points(star1.getUpper_part().getPoints()) + star1.getInitial_upper() + '\n';
        save += star1.getLower_part().getLayoutX() + "," + star1.getLower_part().getLayoutY() + "," + star1.getLower_part().getRotate() + "," + points(star1.getLower_part().getPoints()) + star1.getInitial_lower() + '\n';
        return save;
    }

    private String points(ObservableList<Double> list) {
        String str = "";
        for (Double points : list) {
            str = str + points + ",";
        }
        return str;
    }

    private String getColor(Paint color) {
        if (color == Color.AQUA) {
            return "AQUA";
        }
        if (color == Color.MAGENTA) {
            return "MAGENTA";
        }
        if (color == Color.RED) {
            return "RED";
        }
        if (color == Color.YELLOW) {
            return "YELLOW";
        }
        return null;
    }

    public void readData() throws IOException {
        MainWindowMain.savedGames=new ArrayList<>();
        MainWindowMain.list=new ListView<>();
        File file = new File(fileName);
        BufferedReader br = new BufferedReader(new FileReader(file));

        String str = br.readLine();
        while (str != null) {
            String[] ballArr=str.split(",");
            boolean[] arr = new boolean[11];
            arr[0] = ballArr[0].substring(1).equals("true");
            int count = 1;
            for (int i = 1; i < 10; i++) {
                arr[count] = ballArr[i].equals(" true");
                count++;
            }
            arr[count] = ballArr[10].equals(" true]");
            int position1=Integer.parseInt(br.readLine());
            int position2=Integer.parseInt(br.readLine());

            Ball ball=getBall(br.readLine().split(","));
            Obstacle[] obstacle_on_screen=new Obstacle[2];
            addInArr(position1,obstacle_on_screen,0,br);
            addInArr(position2,obstacle_on_screen,1,br);
            str=br.readLine();
            double scoreLabel = Double.parseDouble(str);
            str = br.readLine();

            String[] Score = str.split(",");
            str = br.readLine();
            double score = Double.parseDouble(Score[0]);
            double highScore = Double.parseDouble(Score[1]);
            double total_star = Double.parseDouble(Score[2]);

            ball.setObstacle_on_screen(obstacle_on_screen);
            ball.setBoolean_obstacle_arr(arr);
            PropertyOfSavedGames savedGames=new PropertyOfSavedGames(scoreLabel,score,highScore,total_star,ball);
            MainWindowMain.savedGames.add(savedGames);
            MainWindowMain.list.getItems().add("Game "+(MainWindowMain.savedGames.size()));
        }
    }

    private void addInArr(int position, Obstacle[] obstacle_on_screen, int counter, BufferedReader br) throws IOException {
        if(position==0)
        {
            // System.out.println(obstacle.getClass());
            obstacle_on_screen[counter]=getCircle(br.readLine().split(","));
        }
        if(position==1)
        {
            String[] Sqaure1 = br.readLine().split(",");
            String[] Sqaure2 = br.readLine().split(",");
            String[] Sqaure3 = br.readLine().split(",");
            String[] Sqaure4 = br.readLine().split(",");
            String Sqauretime = br.readLine();
            obstacle_on_screen[counter]= initializeSquare(Sqaure1, Sqaure2, Sqaure3, Sqaure4, Sqauretime);
        }
        if(position==2)
        {
            String[] upper1 = br.readLine().split(",");
            String[] lower1 = br.readLine().split(",");
            obstacle_on_screen[counter]= initializeStar(upper1, lower1);
        }
        if(position==3)
        {
            String[] Line1 = br.readLine().split(",");
            String[] Line2 = br.readLine().split(",");
            String[] Line3 = br.readLine().split(",");
            String[] Line4 = br.readLine().split(",");
            Line line1 = lineObstacle(Line1);
            Line line2 = lineObstacle(Line2);
            Line line3 = lineObstacle(Line3);
            Line line4 = lineObstacle(Line4);
            double startingTimingline = Double.parseDouble(br.readLine().split(",")[0]);
            Line_Obstacle line_obstacle = new Line_Obstacle(line1, line2, line3, line4, startingTimingline);
            line_obstacle.setInitialPosition1(Double.parseDouble(Line1[4]));
            line_obstacle.setInitialPosition2(Double.parseDouble(Line2[4]));
            line_obstacle.setInitialPosition3(Double.parseDouble(Line3[4]));
            line_obstacle.setInitialPosition4(Double.parseDouble(Line4[4]));

            obstacle_on_screen[counter]=line_obstacle;
        }
        if(position==4)
        {
            String[] leftPlus1 = br.readLine().split(",");
            String[] leftPlus2 = br.readLine().split(",");
            String[] leftPlus3 = br.readLine().split(",");
            String[] leftPlus4 = br.readLine().split(",");
            String[] left_plusinfo = br.readLine().split(",");
            Plus_Obstacle leftPlus = initializePlus(leftPlus1, leftPlus2, leftPlus3, leftPlus4, left_plusinfo);
            String[] rightPlus1 = br.readLine().split(",");
            String[] rightPlus2 = br.readLine().split(",");
            String[] rightPlus3 = br.readLine().split(",");
            String[] rightPlus4 = br.readLine().split(",");
            String[] right_plusinfo = br.readLine().split(",");
            Plus_Obstacle rightPlus = initializePlus(rightPlus1, rightPlus2, rightPlus3, rightPlus4, right_plusinfo);

            obstacle_on_screen[counter]= new Double_Plus_Obstacle(leftPlus, rightPlus);
        }

        if(position==5)
        {
            Circular_Obstacle doubleCircle1 = getCircle(br.readLine().split(","));
            Circular_Obstacle doubleCircle2 = getCircle(br.readLine().split(","));
            obstacle_on_screen[counter]= new Double_Circle_Obstacle(doubleCircle1, doubleCircle2);
        }

        if(position==6)
        {
            String[] circlesqaure1 = br.readLine().split(",");

            String[] circlesqaure2 = br.readLine().split(",");

            String[] circlesqaure3 = br.readLine().split(",");

            String[] circlesqaure4 = br.readLine().split(",");
            String circlesqauretime = br.readLine();
            Square_Obstacle circle_sqaure = initializeSquare(circlesqaure1, circlesqaure2, circlesqaure3, circlesqaure4, circlesqauretime);
            Circular_Obstacle sqaureCircle = getCircle(br.readLine().split(","));
            obstacle_on_screen[counter]= new Square_Circle_Obstacle(sqaureCircle, circle_sqaure);
        }

        if(position==7)
        {
            String[] color_switcher = br.readLine().split(",");
            obstacle_on_screen[counter]= getColorSwitch(color_switcher);
        }

        if(position==8)
        {
            String[] upper2 = br.readLine().split(",");
            String[] lower2 = br.readLine().split(",");
            obstacle_on_screen[counter]= initializeStar(upper2, lower2);
        }

        if(position==9)
        {
            String[] Plus1 = br.readLine().split(",");
            String[] Plus2 = br.readLine().split(",");
            String[] Plus3 = br.readLine().split(",");

            String[] Plus4 = br.readLine().split(",");
            String[] plusinfo = br.readLine().split(",");
            obstacle_on_screen[counter]= initializePlus(Plus1, Plus2, Plus3, Plus4, plusinfo);
        }
        if(position==10)
        {

            String[] upper3 = br.readLine().split(",");
            String[] lower3 = br.readLine().split(",");
            obstacle_on_screen[counter]= initializeStar(upper3, lower3);
        }
    }

    private Square_Obstacle initializeSquare(String[] Sqaure1, String[] Sqaure2, String[] Sqaure3, String[] Sqaure4, String starttime) {
        Line square1 = getSquarePlusLine(Sqaure1);
        Line square2 = getSquarePlusLine(Sqaure2);
        Line square3 = getSquarePlusLine(Sqaure3);
        Line square4 = getSquarePlusLine(Sqaure4);
        double startingTime = Double.parseDouble(starttime.split(",")[0]);
        Square_Obstacle square_obstacle = new Square_Obstacle(square1, square2, square3, square4, startingTime);
        square_obstacle.setInitialPosition1(Double.parseDouble(Sqaure1[6]));
        square_obstacle.setInitialPosition2(Double.parseDouble(Sqaure2[6]));
        square_obstacle.setInitialPosition3(Double.parseDouble(Sqaure3[6]));
        square_obstacle.setInitialPosition4(Double.parseDouble(Sqaure4[6]));
        return square_obstacle;
    }

    private Plus_Obstacle initializePlus(String[] Plus1, String[] Plus2, String[] Plus3, String[] Plus4, String[] plusinfo) {
        Line plus1 = getSquarePlusLine(Plus1);
        Line plus2 = getSquarePlusLine(Plus2);
        Line plus3 = getSquarePlusLine(Plus3);
        Line plus4 = getSquarePlusLine(Plus4);
        double startingTimePlus = Double.parseDouble(plusinfo[0]);
        boolean clockWise = Boolean.parseBoolean(plusinfo[1]);
        Plus_Obstacle plus_obstacle = new Plus_Obstacle(plus1, plus2, plus3, plus4, clockWise, startingTimePlus);
        plus_obstacle.setInitialPosition1(Double.parseDouble(Plus1[6]));
        plus_obstacle.setInitialPosition2(Double.parseDouble(Plus2[6]));
        plus_obstacle.setInitialPosition3(Double.parseDouble(Plus3[6]));
        plus_obstacle.setInitialPosition4(Double.parseDouble(Plus4[6]));
        return plus_obstacle;
    }

    private Star initializeStar(String[] upper1, String[] lower1) {
        double initialUpper = Double.parseDouble(upper1[9]);
        double initialLower = Double.parseDouble(lower1[9]);
        Polygon upperStar = getStar(upper1);
        Polygon lowerStar = getStar(lower1);
        return new Star(upperStar, lowerStar, initialUpper, initialLower);
    }

    private Ball getBall(String[] ballArr) {
        Paint color = setColor(ballArr[0]);
        double Radius = Double.parseDouble(ballArr[1]);
        double layoutX = Double.parseDouble(ballArr[2]);
        double layoutY = Double.parseDouble(ballArr[3]);
        int level = Integer.parseInt(ballArr[4]);
        Ball ball = new Ball(color, Radius, new Coordinate(layoutX, layoutY));
        ball.setLevelCount(level);
        return ball;
        //TODO:set obstacle_on_screen of ball
    }

    private Circular_Obstacle getCircle(String[] circleArr) {
        double centerX = Double.parseDouble(circleArr[0]);
        double centerY = Double.parseDouble(circleArr[1]);
        double layoutX = Double.parseDouble(circleArr[2]);
        double layoutY = Double.parseDouble(circleArr[3]);
        double length = Double.parseDouble(circleArr[4]);
        double RadiusX = Double.parseDouble(circleArr[5]);
        double RadiusY = Double.parseDouble(circleArr[6]);
        double InitialPosition1 = Double.parseDouble(circleArr[7]);
        double InitialPosition2 = Double.parseDouble(circleArr[8]);
        double InitialPosition3 = Double.parseDouble(circleArr[9]);
        double InitialPosition4 = Double.parseDouble(circleArr[10]);
        double StartingAngle = Double.parseDouble(circleArr[11]);
        boolean clockwise = Boolean.parseBoolean(circleArr[12]);
        boolean color_switch = Boolean.parseBoolean(circleArr[13]);
        double startLevelTime = Double.parseDouble(circleArr[14]);

        Circular_Obstacle circular_obstacle = new Circular_Obstacle(centerX, centerY, layoutX, layoutY, length, RadiusX, RadiusY, StartingAngle, clockwise, color_switch, startLevelTime);
        circular_obstacle.setInitialPosition1(InitialPosition1);
        circular_obstacle.setInitialPosition2(InitialPosition2);
        circular_obstacle.setInitialPosition3(InitialPosition3);
        circular_obstacle.setInitialPosition4(InitialPosition4);
        return circular_obstacle;
    }

    private Color_Switch getColorSwitch(String[] circleArr) {
        double centerX = Double.parseDouble(circleArr[0]);
        double centerY = Double.parseDouble(circleArr[1]);
        double layoutX = Double.parseDouble(circleArr[2]);
        double layoutY = Double.parseDouble(circleArr[3]);
        double length = Double.parseDouble(circleArr[4]);
        double RadiusX = Double.parseDouble(circleArr[5]);
        double RadiusY = Double.parseDouble(circleArr[6]);
        double InitialPosition1 = Double.parseDouble(circleArr[7]);
        double InitialPosition2 = Double.parseDouble(circleArr[8]);
        double InitialPosition3 = Double.parseDouble(circleArr[9]);
        double InitialPosition4 = Double.parseDouble(circleArr[10]);
        double StartingAngle = Double.parseDouble(circleArr[11]);
        boolean clockwise = Boolean.parseBoolean(circleArr[12]);
        boolean color_switch = Boolean.parseBoolean(circleArr[13]);
        double startLevelTime = Double.parseDouble(circleArr[14]);

        Color_Switch circular_obstacle = new Color_Switch(centerX, centerY, layoutX, layoutY, length, RadiusX, RadiusY, StartingAngle, clockwise, color_switch, startLevelTime);
        circular_obstacle.getColor_switch_circle().setInitialPosition1(InitialPosition1);
        circular_obstacle.getColor_switch_circle().setInitialPosition2(InitialPosition2);
        circular_obstacle.getColor_switch_circle().setInitialPosition3(InitialPosition3);
        circular_obstacle.getColor_switch_circle().setInitialPosition4(InitialPosition4);
        return circular_obstacle;
    }

    private Line getSquarePlusLine(String[] square) {
        double startX = Double.parseDouble(square[0]);
        double endX = Double.parseDouble(square[1]);
        double layoutX = Double.parseDouble(square[2]);
        double layoutY = Double.parseDouble(square[3]);
        double rotation = Double.parseDouble(square[4]);
        Paint paint = setColor(square[5]);
        Line line = new Line();
        line.setEndX(endX);
        line.setStartX(startX);
        line.setLayoutX(layoutX);
        line.setLayoutY(layoutY);
        line.setStroke(paint);
        line.setStrokeWidth(20);
        line.setRotate(rotation);

        return line;
    }

    private Polygon getStar(String[] star) {
        double layoutX = Double.parseDouble(star[0]);
        double layoutY = Double.parseDouble(star[1]);
        double rotation = Double.parseDouble(star[2]);
        double point1 = Double.parseDouble(star[3]);
        double point2 = Double.parseDouble(star[4]);
        double point3 = Double.parseDouble(star[5]);
        double point4 = Double.parseDouble(star[6]);
        double point5 = Double.parseDouble(star[7]);
        double point6 = Double.parseDouble(star[8]);

        Polygon polygon = new Polygon();
        polygon.setFill(Color.WHITE);
        polygon.setLayoutX(layoutX);
        polygon.setLayoutY(layoutY);
        polygon.setStroke(Color.WHITE);
        polygon.setRotate(rotation);
        polygon.setStrokeLineCap(StrokeLineCap.ROUND);
        polygon.getPoints().addAll(point1, point2, point3, point4, point4, point5, point6);
        return polygon;
    }

    private Line lineObstacle(String[] line) {
        double endX = Double.parseDouble(line[0]);
        double layoutX = Double.parseDouble(line[1]);
        double layoutY = Double.parseDouble(line[2]);
        Paint strokeColor = setColor(line[3]);
        Line line1 = new Line();
        line1.setEndX(endX);
        line1.setLayoutX(layoutX);
        line1.setLayoutY(layoutY);
        line1.setStroke(strokeColor);
        line1.setStrokeWidth(20);
        return line1;
    }

    private Paint setColor(String str) {
        if (str.equals("AQUA")) {
            return Color.AQUA;
        }
        if (str.equals("MAGENTA")) {
            return Color.MAGENTA;
        }
        if (str.equals("RED")) {
            return Color.RED;
        } else {
            return Color.YELLOW;
        }
    }
}
