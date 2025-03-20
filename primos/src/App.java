import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class App {
    static class Resultado {
        int numero;
        long tempoSeq;
        long tempo5Threads;
        long tempo10Threads;

        public Resultado(int numero, long tempoSeq, long tempo5Threads, long tempo10Threads) {
            this.numero = numero;
            this.tempoSeq = tempoSeq;
            this.tempo5Threads = tempo5Threads;
            this.tempo10Threads = tempo10Threads;
        }
    }

    public static void main(String[] args) throws Exception {
        List<Integer> numbers = Files.readAllLines(Paths.get("Entrada01.txt"))
                .stream()
                .skip(1)
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        List<Resultado> resultados = new ArrayList<>();
        PrimeChecker seqChecker = new SequentialPrimeChecker();
        PrimeChecker paralelo5 = new ParallelPrimeChecker(5);
        PrimeChecker paralelo10 = new ParallelPrimeChecker(10);

        // Medição progressiva
        for (int num : numbers) {
            List<Integer> subList = numbers.subList(0, numbers.indexOf(num) + 1);

            long t1 = medirTempo(seqChecker, subList);
            long t2 = medirTempo(paralelo5, subList);
            long t3 = medirTempo(paralelo10, subList);

            resultados.add(new Resultado(num, t1, t2, t3));

            System.out.printf("Número: %d, Tempo Sequencial: %d ns, Tempo 5 Threads: %d ns, Tempo 10 Threads: %d ns%n",
                    num, t1, t2, t3);
        }

        // Salvar dados
        List<String> linhas = new ArrayList<>();
        linhas.add("Numero,Tempo_Sequencial,Tempo_5_Threads,Tempo_10_Threads");
        for (Resultado r : resultados) {
            linhas.add(String.format("%d,%d,%d,%d",
                    r.numero, r.tempoSeq, r.tempo5Threads, r.tempo10Threads));
        }
        Files.write(Paths.get("dados_tempo.csv"), linhas);
    }

    private static long medirTempo(PrimeChecker checker, List<Integer> numbers) throws Exception {
        long start = System.nanoTime();
        checker.checkPrimes(numbers);
        return System.nanoTime() - start;
    }
}