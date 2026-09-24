public class Main {
    public static boolean prpereb(int [][] matrica,int chislo){
        if (matrica == null) {
            return false;
        }
        for (int i =0; i<matrica.length;i++){
            for (int j =0; j<matrica.length;j++){
                if (matrica[i][j] ==chislo){
                    return true;
                }
            }
        }
        return false;
    }
}
