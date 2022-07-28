// Guilherme Moreira de Carvalho & Marcus Vinícius Diniz Ribeiro
// 20/08/21 - Para Laboratório de Algoritmos e Estruturas de Dados II
// Teste das classes LinhaMontagem_DYN - algoritmo dinâmico - e LinhaMontagem_GDY - algoritmo guloso

import java.io.FileWriter;
import java.io.IOException;

public class Teste {
    public static void main (String[] args) {
        // Ai: Tempo de processamento de cada esstação na linha i - in e out inclusos
        // Ti: Tempo de transporte de uma estação na linha i à estação seguinte na linha 2
        int[] A1 = {3, 5, 7, 10, 5, 9, 11, 9, 5, 2, 6};
        int[] A2 = {2, 6, 3, 9, 11, 4, 9, 3, 12, 4, 5};
        int[] T1 = {3, 5, 4, 2, 7, 5, 8, 1};
        int[] T2 = {5, 3, 7, 5, 6, 2, 5, 2};

        LinhaMontagem_DYN DP = new LinhaMontagem_DYN(9);
        LinhaMontagem_GDY GP = new LinhaMontagem_GDY(9);

        String out = "";

        out += "\tEXEMPLO 1\n--PROGRAMACAO DINAMICA--\nCaminho:\n";
        int[] l = DP.montagemOtima(A1, A2, T1, T2);
        out += DP.imprime(l);
        out += "Tempo gasto: " + DP.getT_OUT() + "\n";
        out += "\n--ALGORITMO GULOSO--\nCaminho:\n";
        l = GP.montagemOtima(A1, A2, T1, T2);
        out += GP.imprime(l);
        out += "Tempo gasto: " + GP.getT_OUT() + "\n";

        LinhaMontagem_DYN.NUM_ESTACAO = 8;
        LinhaMontagem_GDY.NUM_ESTACAO = 8;
        int[] A1_ = {5, 10, 6, 3, 8, 5, 3, 7, 12, 8};
        int[] A2_ = {7, 3, 5, 3, 7, 6, 4, 9, 10, 9};
        int[] T1_ = {4, 2, 7, 2, 5, 8, 2};
        int[] T2_ = {6, 1, 7, 3, 6, 4, 5};

        out += "\n\tEXEMPLO 2\n--PROGRAMACAO DINAMICA--\nCaminho:\n";
        l = DP.montagemOtima(A1_, A2_, T1_, T2_);
        out += DP.imprime(l);
        out += "Tempo gasto: " + DP.getT_OUT() + "\n";
        out += "\n--ALGORITMO GULOSO--\nCaminho:\n";
        l = GP.montagemOtima(A1_, A2_, T1_, T2_);
        out += GP.imprime(l);
        out += "Tempo gasto: " + GP.getT_OUT() + "\n";

        try {
            FileWriter fp = new FileWriter("ProgramacaoDinamica_Saida.txt");
            fp.write(out);
            fp.close();
        } catch (IOException e) {
            System.out.println("ERRO: NAO FOI POSSIVEL CRIAR O ARQUIVO DE SAIDA");
        }
    }
}
