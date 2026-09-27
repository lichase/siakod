package org.example;
public class Main {
    public static boolean prpereb(int [][] matrica,int chislo){
        if (matrica == null) {
            return false;
        }
        for (int i =0; i<matrica.length;i++){
            for (int j =0; j<matrica[i].length;j++){
                if (matrica[i][j] ==chislo){
                    return true;
                }
            }
        }
        return false;
    }


    public static boolean binpoisk(int [][] matrica,int chislo){
        for (int i=0; i< matrica.length ;i++){
            int levo =0; int pravo = matrica[i].length -1;
            while (levo<=pravo){
                int srednee=(levo+pravo)/2;
                if (matrica[i][srednee]==chislo){
                    return true;
                }
                if (matrica[i][srednee]<chislo){
                    levo= srednee+1;
                } else { pravo=srednee-1;
                }
            }
        }
        return false;
    }
    //поиск от правого верх угла
    public static boolean poicPy(int [][] matrica,int chislo){
        if (matrica.length==0){
            return false;
        }
        int i=0;
        int j= matrica[0].length-1;
        while (i<matrica.length && j>=0){
            if (matrica[i][j]== chislo){
                return true;
            }
            if (matrica[i][j]>chislo){
                j--;
            }else {
                i++;
            }
        }
        return false;
    }
    public static void main(String[] args) {
        int[][] matrica = {
                {1, 4, 7, 10},
                {2, 5, 8, 12},
                {3, 6, 9, 15}
        };

        int chislo = 8;

        System.out.println(prpereb(matrica, chislo));
        System.out.println(binpoisk(matrica, chislo));
        System.out.println(poicPy(matrica, chislo));
    }
}
