package com.company;

import java.util.LinkedList;

public class PathFinder
{

    private static void Print(char[][] array, int height, int width)
    {
        for(int i=0;i<height;i++)
        {
            for(int j=0;j<width;j++)
            {
                System.out.print(array[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void PrintInt(int[][] array, int height, int width)
    {
        for(int i=0;i<height;i++)
        {
            for(int j=0;j<width;j++)
            {
                System.out.print(array[i][j]);
                System.out.print('\t');
            }
            System.out.println();
        }
        System.out.println();
    }

    public static char[][] BFS(char[][] array, LinkedList<Point> s, LinkedList<Point> f)
    {
        boolean[][] isChecked = new boolean[array.length][array[0].length]; //Разметка посещенных клеток
        int[][] numArray = new int[array.length][array[0].length]; //Порядок посещения клеток
        int[][] sumLength = new int[array.length][array[0].length]; //Расстояние до [i][j]клетки от входа

        Point exit = f.element();
        Point start = s.element();

        numArray[s.getFirst().Y_coord][s.getFirst().X_coord]=0;
        isChecked[s.getFirst().Y_coord][s.getFirst().X_coord]=true;
        sumLength[s.getFirst().Y_coord][s.getFirst().X_coord]=0;

        int i=1;

        while(!s.isEmpty())
        {
            Point p = s.getFirst();
            s.remove();
            if(p.X_coord+1 < array[0].length && isChecked[p.Y_coord][p.X_coord+1]==false)//размечена ли клетка и находится в пределах массива
            {
                if(array[p.Y_coord][p.X_coord+1]=='.' || array[p.Y_coord][p.X_coord+1]=='x' )//проверка правой клетки
                {
                    numArray[p.Y_coord][p.X_coord+1]=i++;
                    sumLength[p.Y_coord][p.X_coord+1]=sumLength[p.Y_coord][p.X_coord]+1;
                    isChecked[p.Y_coord][p.X_coord+1]=true;
                    s.add(new Point(p.Y_coord, p.X_coord+1));
                    PathFinder.PrintInt(numArray,array.length, array[0].length);
                    System.out.println();
                    PathFinder.PrintInt(sumLength,array.length, array[0].length);
                }
            }
            if(p.Y_coord-1 >= 0 && isChecked[p.Y_coord - 1][p.X_coord]==false)
            {
                if (array[p.Y_coord - 1][p.X_coord] == '.' || array[p.Y_coord-1][p.X_coord]=='x')//проверка верхней клетки
                {
                    numArray[p.Y_coord-1][p.X_coord]=i++;
                    sumLength[p.Y_coord-1][p.X_coord]=sumLength[p.Y_coord][p.X_coord]+1;
                    isChecked[p.Y_coord - 1][p.X_coord]=true;
                    s.add(new Point(p.Y_coord - 1, p.X_coord));
                    PathFinder.PrintInt(numArray,array.length, array[0].length);
                    System.out.println();
                    PathFinder.PrintInt(sumLength,array.length, array[0].length);
                }
            }
            if(p.X_coord-1 >=0 && isChecked[p.Y_coord][p.X_coord - 1] == false)
            {
                if (array[p.Y_coord][p.X_coord - 1] == '.' || array[p.Y_coord][p.X_coord-1]=='x')//проверка левой клетки
                {
                    numArray[p.Y_coord][p.X_coord - 1]=i++;
                    sumLength[p.Y_coord][p.X_coord-1]=sumLength[p.Y_coord][p.X_coord]+1;
                    isChecked[p.Y_coord][p.X_coord - 1] = true;
                    s.add(new Point(p.Y_coord, p.X_coord - 1));
                    PathFinder.PrintInt(numArray,array.length, array[0].length);
                    System.out.println();
                    PathFinder.PrintInt(sumLength,array.length, array[0].length);
                }
            }
            if (p.Y_coord+1 < array.length && isChecked[p.Y_coord+1][p.X_coord] == false)
            {
                if (array[p.Y_coord + 1][p.X_coord] == '.' || array[p.Y_coord+1][p.X_coord]=='x')//проверка нижней клетки
                {
                    numArray[p.Y_coord + 1][p.X_coord] = i++;
                    sumLength[p.Y_coord+1][p.X_coord]=sumLength[p.Y_coord][p.X_coord]+1;
                    isChecked[p.Y_coord+1][p.X_coord] = true;
                    s.add(new Point(p.Y_coord + 1, p.X_coord));
                    PathFinder.PrintInt(numArray,array.length, array[0].length);
                    System.out.println();
                    PathFinder.PrintInt(sumLength,array.length, array[0].length);
                }
            }
            if(isChecked[exit.Y_coord][exit.X_coord]==true) //если достигнута клетка выхода
            {
                boolean[][] isCheckedReversed = new boolean[array.length][array[0].length];
                isCheckedReversed=FindTheShortestWay(isChecked, sumLength, exit, start); //массив разметки принимает значения, соответствующие кратчайшему пути
                for(int it=0;it<array.length;it++)
                {
                    for(int jt=0;jt<array[0].length;jt++)
                    {
                        if(isChecked[it][jt]&isCheckedReversed[it][jt]==true)//конъюнкция двух массивов посещенных клеток, чтобы построить кратчайший путь из +
                        {
                            array[it][jt]='+';
                        }
                    }
                }
                array[start.Y_coord][start.X_coord]='@';
                array[exit.Y_coord][exit.X_coord]='x';
                Print(array, array.length, array[0].length);
                return array;
            }
        }
        return null;
    }

    private static boolean[][] FindTheShortestWay(boolean[][] alreadyChecked, int[][] sum_length, Point exit, Point start)
    {
        boolean[][] isChecked = new boolean[sum_length.length][sum_length[0].length];
        isChecked[exit.Y_coord][exit.X_coord]=true;

        LinkedList<Point> wayCoord = new LinkedList<>();
        wayCoord.add(exit);

        while(isChecked[start.Y_coord][start.X_coord]==false)
        {
            Point p = wayCoord.getFirst();
            wayCoord.remove();
            if(p.X_coord+1 < sum_length[0].length && alreadyChecked[p.Y_coord][p.X_coord+1]==true)
            {
            if(sum_length[p.Y_coord][p.X_coord]-sum_length[p.Y_coord][p.X_coord+1]==1)
            {
                isChecked[p.Y_coord][p.X_coord+1]=true;
                wayCoord.add(new Point(p.Y_coord,p.X_coord+1));
                continue;
            }
            }
            if(p.Y_coord-1 >= 0 && alreadyChecked[p.Y_coord-1][p.X_coord]==true)
            {
            if(sum_length[p.Y_coord][p.X_coord]-sum_length[p.Y_coord-1][p.X_coord]==1)
            {
                isChecked[p.Y_coord-1][p.X_coord]=true;
                wayCoord.add(new Point(p.Y_coord-1,p.X_coord));
                continue;
            }
            }
            if(p.X_coord-1 >=0 && alreadyChecked[p.Y_coord][p.X_coord-1]==true)
            {
            if(sum_length[p.Y_coord][p.X_coord]-sum_length[p.Y_coord][p.X_coord-1]==1)
            {
                isChecked[p.Y_coord][p.X_coord-1]=true;
                wayCoord.add(new Point(p.Y_coord,p.X_coord-1));
                continue;
            }
            }
            if (p.Y_coord+1 < sum_length.length && alreadyChecked[p.Y_coord+1][p.X_coord]==true)
            {
            if(sum_length[p.Y_coord][p.X_coord]-sum_length[p.Y_coord+1][p.X_coord]==1)
            {
                isChecked[p.Y_coord+1][p.X_coord]=true;
                wayCoord.add(new Point(p.Y_coord+1,p.X_coord));
                continue;
            }
            }
        }
        return isChecked;
    }
}
