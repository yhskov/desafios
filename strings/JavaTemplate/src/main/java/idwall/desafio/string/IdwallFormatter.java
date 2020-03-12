package idwall.desafio.string;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rodrigo Catão Araujo on 06/02/2018.
 */
public class IdwallFormatter {

    /**
     * Should format as described in the challenge
     *
     * @param text
     * @return
     */

    public List<String> format(String input, int limit) {
    	String[] words = input.split(" ");
        List<String> formattedText = new ArrayList<>();
        int index = 0;

        // Executa o loop enquanto o iterador não varrer todo o array
        while (index < words.length) {
            int line = words[index].length(); // Recebe a quantidade de caracteres + 1, ou seja, o espaço após a palavra
            int next = index + 1; // Segundo iterador que irá fazer a verificação da próxima palavra
            
            // Executa o loop enquanto houver a próxima posição do array
            while (next < words.length) {
                if (line + 1 + words[next].length() > limit) break; // Verifica se a palavra atual mais a palavra seguinte e o espaço entre elas não ultrapassará o limite de caracteres
                line += 1 + words[next].length();
                next++;
            }
            
            /**
             *  Quantidade de espaços entre index e next
             *	Exemplo: se houverem 3 palavras temos 2 gaps
             */
            int gaps = next - index - 1;
            StringBuilder sb = new StringBuilder();

            // Verifica se é a última linha
            if (next == words.length || gaps == 0) {
            	// Neste caso o laço varrerá o array até a última palavra e deixará apenas um espaço entre elas
                for (int i = index; i < next; i++) {
                    sb.append(words[i]);
                    sb.append(' ');
                }                
                
                // Preenche com espaços o resto dos caracteres que faltam
                while (sb.length() < limit) {
                    sb.append(' ');
                }
            } else { // Se não for a última linha
                int spaces = (limit - line) / gaps;
                int rest = (limit - line) % gaps;
                
                // Adiciona as palavras para ir formando a linha
                for (int i = index; i < next - 1; i++) {
                    sb.append(words[i]);
                    sb.append(' ');
                    
                    // Calcula a quantidade de espaços necessários para formar a linha
                    for (int j = 0; j < spaces + (i - index < rest ? 1 : 0); j++) {
                        sb.append(' ');
                    }
                }
                
                // Remove o espaço após a última palavra da linha
                sb.append(words[next - 1]);
            }
            
            // Constrói a String
            formattedText.add(sb.toString());
            index = next;
        }
        
		return formattedText; 	                

    }
}
