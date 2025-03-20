## Relatorio Atv complementar 01

Nessa atividade, foi feito um comparativo dos tempos de execução de um algoritmo de detecção de números primos,
utilizando programação paralela e sequencial.

## Estratégia Utilizada

Para encarar esse problema, foi utilizada a mesma estratégia de cálculo, tanto para a versão sequencial quanto para a versão paralela. Desse modo, foi possível comparar os tempos de execução de ambas as versões, isolando a diferença principal, que é justamente a abordagem sequencial versus paralela.
Foram feitos testes sem paralelismo, com o paralelismo de 5 threads e com o paralelismo de 10 threads.

## Resultados

Apesar de, em alguns casos individuais, os tempos de execução não terem apresentado grandes diferenças, na esmagadora maioria dos casos, a versão paralela foi mais rápida que a versão sequencial. Isso se deve ao fato de que, ao dividir o trabalho entre várias threads, é possível realizar várias operações ao mesmo tempo, o que acelera o processo de cálculo. Ainda nessa análise, foi possível perceber que, ao aumentar o número de threads, o tempo de execução diminuiu, o que é esperado, uma vez que mais threads significam mais operações simultâneas. Em dados momentos, entretanto, o tempo de execução da versão de 5 threads foi menor do que a de 10 threads, o que pode ser explicado por questões de sincronização e overhead de criação de threads. Entretanto, de modo geral, o aumento do número de threads resultou em uma diminuição do tempo de execução.

Conforme o código foi sendo executado, a velocidade de execução foi diminuindo, o que pode ser observado nos três experimentos. Ainda assim, a vantagem da utilização de threads ficou clara.

## Gráfico

O grafico se encontra na raiz do projeto, e exibe os tempos de execução de cada número ao longo do tempo de execução do código.
