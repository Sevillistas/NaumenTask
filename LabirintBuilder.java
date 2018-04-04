package com.company;

/**
 * Created by Aleks on 22.03.2018.
 */

public class LabirintBuilder
{
    public char[][] labirint;
    public int height;
    public int width;

    public LabirintBuilder(int M, int N)
    {
        height = M;
        width = N;
        labirint = new char[M][N];
        for(int i=0;i<M;i++)
        {
            for(int j=0;j<N;j++)
            {
                labirint[i][j]=FillLabirint();
            }
        }
    }

    private static char FillLabirint() {
        int randValue = 0;
        int minValue = 0;
        int maxValue = 10;
        randValue = minValue + (int)(Math.random()*maxValue);
        if (randValue < 2)
            return '#';
        else
            return '.';
    }

    public void PrintLabirint() {
        for(int i=0;i<height;i++)
        {
            for(int j=0;j<width;j++)
            {
                System.out.print(labirint[i][j]);
            }
            System.out.println();
        }
    }

    public void SetEntryAndExit(Point entry, Point exit)
    {
        labirint[entry.Y_coord][entry.X_coord]='@';
        labirint[exit.Y_coord][exit.X_coord]='x';
    }
}
