// Guilherme Moreira de Carvalho & Marcus Vinícius Diniz Ribeiro
// 20/08/21 - Para Laboratório de Algoritmos e Estruturas de Dados II
// Implementa um algoritmo para solucionar o problema das linhas de montagem
// > utilizando a metodologia de pprogramação dinâmica - recursivo como memoização
// > considerando 2 linhas

public class LinhaMontagem_DYN {
    static int NUM_LINHA;   // qntd de linhas
    static int NUM_ESTACAO; // qntd de estções em cada linha
    private int T_OUT;      // tempo gasto

    public LinhaMontagem_DYN (int s) {
        NUM_LINHA = 2;
        NUM_ESTACAO = s;
        T_OUT = -1;
    }
    
    public int getT_OUT () { return this.T_OUT; }

    // retorna o mínimo entre a e b
    private int min (int a, int b) {
        if (b < a) return b;
        return a;
    }
    
    // otimiza o processo de montagem considerando os tempos de cada estação e de
    // transferência entre linhas
    public int[] montagemOtima (int A1[], int[] A2, int[] T1, int[] T2) {
        // f*: caminhos mais rápidos até cada estação, começando, respectivamente,
        // pela linha 1 e pela linha 2
        // l*: linha pela qual se chega a cada estação mais rapidamente
        int[] f1 = new int[NUM_ESTACAO + 1]; int[] l1 = new int[NUM_ESTACAO + 1];
        int[] f2 = new int[NUM_ESTACAO + 1]; int[] l2 = new int[NUM_ESTACAO + 1];

        // e: tempo de entrada; x: tempo de saída
        int[] e = {A1[0], A2[0]};
        int[] x = {A1[NUM_ESTACAO + 1], A2[NUM_ESTACAO + 1]};

        // entradas
        f1[0] = e[0] + A1[1]; l1[0] = 1; l1[NUM_ESTACAO] = 1;
        f2[0] = e[1] + A2[1]; l2[0] = 2; l2[NUM_ESTACAO] = 2;

        // verifica, recursivamente, qual o caminho mais rápido a cada estação
        // > pela estação anterior da mesma linha ou
        // > pela estação anterior de outra linha acrescido o tempo de transporte
        for (int i = 1; i < NUM_ESTACAO; i++) {
            if (f1[i-1] + A1[i+1] <= f2[i-1] + T2[i-1] + A1[i+1]) {
                f1[i] = f1[i-1] + A1[i+1]; l1[i] = 1;
            } else {
                f1[i] = f2[i-1] + T2[i-1] + A1[i+1]; l1[i] = 2;
            }
            if (f2[i-1] + A2[i+1] <= f1[i-1] + T1[i-1] + A2[i+1]) {
                f2[i] = f2[i-1] + A2[i+1]; l2[i] = 2;
            } else {
                f2[i] = f1[i-1] + T1[i-1] + A2[i+1]; l2[i] = 1;
            }
        }

        // saídas
        f1[NUM_ESTACAO] = f1[NUM_ESTACAO - 1] + x[0];
        f2[NUM_ESTACAO] = f2[NUM_ESTACAO - 1] + x[1];

        /*for (int i = 0; i <= NUM_ESTACAO; i++) {
            System.out.print(f1[i] + " ");
        }
        System.out.println();
        for (int i = 0; i <= NUM_ESTACAO; i++) {
            System.out.print(l1[i] + " ");
        }
        System.out.println();
        for (int i = 0; i <= NUM_ESTACAO; i++) {
            System.out.print(f2[i] + " ");
        }
        System.out.println();
        for (int i = 0; i <= NUM_ESTACAO; i++) {
            System.out.print(l2[i] + " ");
        }
        System.out.println();*/

        // o tempo gasto é o mínimo entre as saídas
        this.T_OUT = min(f1[NUM_ESTACAO], f2[NUM_ESTACAO]);

        // retorna o percurso com menor tempo
        if (f1[NUM_ESTACAO] <= f2[NUM_ESTACAO]) {
            return l1;
        } else {
            return l2;
        }
    }

    public String imprime (int[] l) {
        String out = "";
        for (int i = 1; i <= NUM_ESTACAO; i++) {
            out += "Linha " + l[i] + ", Estacao " + (i) + "\n";
        }
        return out;
    }
}
