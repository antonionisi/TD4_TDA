import java.util.Vector;

public class BoundaryMatrix {
    
    private int[][] matrix;

    public BoundaryMatrix(Vector<Simplex> F) {
        // question 3 : n is equal to the number of simplices in F
        int n = F.size();
        F.sort((Simplex s1, Simplex s2) -> s1.compareTo(s2));
        matrix = new int[n][n];
        for (int i=0; i<n; i++) {
            Simplex s = F.get(i);
            for (int j=0; j<n; j++) {
                Simplex t = F.get(j);
                if (s.vert.containsAll(t.vert) && s.vert.size() == t.vert.size()+1) {
                    matrix[j][i] = 1;
                }
                else {
                    matrix[j][i] = 0;
                }
            }
        }
    }

    public void reduce() {
        int n = matrix.length;
        int m = matrix[0].length;
        int[] pivot = new int[m];
        for (int i=0; i<m; i++) {
            pivot[i] = -1;
        }
        for (int j=0; j<m; j++) {
            for (int i=0; i<n; i++) {
                if (matrix[i][j] == 1) {
                    pivot[j] = i;
                    break;
                }
            }
            if (pivot[j] != -1) {
                for (int k=j; k<m; k++) {
                    if (k != j && matrix[pivot[j]][k] == 1) {
                        for (int i=0; i<n; i++) {
                            matrix[i][k] = (matrix[i][k] + matrix[i][j]) % 2;
                        }
                    }
                }
            }
        }
    }

    public String toString() {
        String s = "";
        for (int i=0; i<matrix.length; i++) {
            for (int j=0; j<matrix[0].length; j++) {
                s += matrix[i][j] + " ";
            }
            s += "\n";
        }
        return s;
    }

}
