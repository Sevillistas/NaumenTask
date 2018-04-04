package com.company;

import java.util.*;

public class Main {

    public static void main(String[] args)
    {
        //Для ввода пользователя

        /*
        Scanner reader = new Scanner(System.in);

        System.out.println("Введите длину(M) и ширину(N) лабиринта\n");

        int labLength=reader.nextInt();
        int labWidth=reader.nextInt();

        LabirintBuilder lb = new LabirintBuilder(labLength,labWidth);

        System.out.println("Введите координаты входа\n");
        int yEntryCoord=reader.nextInt();
        int xEntryCoord=reader.nextInt();

        System.out.println("Введите координаты выхода\n");
        int yExitCoord=reader.nextInt();
        int xExitCoord=reader.nextInt();

        Point p0 = new Point(yEntryCoord,xEntryCoord);
        Point pn = new Point(yExitCoord,xExitCoord);
        */


        //Для программиста

        //Генерация лабиринта размером MxN
        LabirintBuilder lb = new LabirintBuilder(100,100);

        //Инициализация координат начальной и конечной точек
        Point p0 = new Point(2,96);
        Point pn = new Point(87,3);

	    lb.SetEntryAndExit(p0,pn);
	    System.out.println();

	    lb.PrintLabirint();
	    System.out.println();

	    LinkedList<Point> q_entry = new LinkedList<>();
        LinkedList<Point> q_exit = new LinkedList<>();
	    q_entry.add(p0);
	    q_exit.add(pn);
        PathFinder.BFS(lb.labirint,q_entry,q_exit);
    }
}
