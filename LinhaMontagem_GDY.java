// Guilherme Moreira de Carvalho & Marcus Vinícius Diniz Ribeiro
// 20/08/21 - Para Laboratório de Algoritmos e Estruturas de Dados II
// Implementa um algoritmo guloso para solucionar o problema das linhas de montagem
// considerando 2 linhas

public class LinhaMontagem_GDY {
    static int NUM_LINHA;
    static int NUM_ESTACAO;
    private int T_OUT;

    public LinhaMontagem_GDY (int s) {
        NUM_LINHA = 2;
        NUM_ESTACAO = s;
        T_OUT = 0;
    }

    public int getT_OUT () { return this.T_OUT; }
    
    // estima o tempo gasto no processo de montagem considerando os tempos de cada estação e de
    // transferência entre linhas
    public int[] montagemOtima (int A1[], int[] A2, int[] T1, int[] T2) {
        // estação pela qual se chega a cada estação pelo caminho mais rápido
        int[] l = new int[NUM_ESTACAO + 2];

        int[] e = {A1[0], A2[0]};
        int[] x = {A1[NUM_ESTACAO + 1], A2[NUM_ESTACAO + 1]};

        // tempo de entrada mais rápido
        if (e[0] + A1[1] <= e[1] + A2[1]) {
            l[0] = 1; l[1] = 1;
            T_OUT += e[0] + A1[1];
        } else {
            l[0] = 2; l[1] = 2;
            T_OUT += e[1] + A2[1];
        }

        // verifica, iterativamente, qual o caminho mais rápido a se seguir
        // > continua na linha atual ou
        // > transporta para outra
        for (int i = 2; i <= NUM_ESTACAO; i ++) {
            if (l[i-1] == 1) {
                if (T_OUT + A1[i] <= T_OUT + A2[i] + T1[i-2]) {
                    l[i] = 1;
                    T_OUT += A1[i];
                } else {
                    l[i] = 2;
                    T_OUT += A2[i] + T1[i-2];
                }
            } else {
                if (T_OUT + A2[i] <= T_OUT + A1[i] + T2[i-2]) {
                    l[i] = 2;
                    T_OUT += A2[i];
                } else {
                    l[i] = 1;
                    T_OUT += A1[i] + T2[i-2];
                }
            }
        }

        // tempo de saída mais rápido
        if (l[NUM_ESTACAO] == 1) {
            l[NUM_ESTACAO + 1] = 1;
            T_OUT += x[0];
        } else {
            l[NUM_ESTACAO + 1] = 2;
            T_OUT += x[1];
        }

        // retorna o percurso
        return l;
    }

    public String imprime (int[] l) {
        String out = "";
        for (int i = 1; i <= NUM_ESTACAO; i++) {
            out += "Linha " + l[i] + ", Estacao " + (i) + "\n";
        }
        return out;
    }
}
