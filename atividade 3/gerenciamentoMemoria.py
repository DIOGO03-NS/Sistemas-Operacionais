import random

# Definições iniciais
def cria_memoria(tamanho):
    return [0] * tamanho

# Funções auxiliares
def exibir_mapa(memoria):
    print("Mapa de Memória:", memoria)

def fragmentacao_externa(memoria):
    buracos = ''.join(map(str, memoria)).split('1')
    pequenos_buracos = sum(1 for buraco in buracos if len(buraco) > 0 and len(buraco) < min(tamanhos_processos))
    print(f"Fragmentação Externa: {pequenos_buracos} buracos pequenos.")

def alocar_processo(memoria, tamanho, indice):
    for i in range(indice, len(memoria) - tamanho + 1):
        if all(memoria[j] == 0 for j in range(i, i + tamanho)):
            for j in range(i, i + tamanho):
                memoria[j] = 1
            return i
    return -1

def desalocar_processo(memoria, inicio, tamanho):
    for i in range(inicio, inicio + tamanho):
        memoria[i] = 0

# Algoritmos de Gerenciamento de Memória
def first_fit(memoria, tamanho):
    return alocar_processo(memoria, tamanho, 0)

def next_fit(memoria, tamanho, ultima_posicao):
    alocacao = alocar_processo(memoria, tamanho, ultima_posicao)
    return alocacao if alocacao != -1 else alocar_processo(memoria, tamanho, 0)

def best_fit(memoria, tamanho):
    melhor_buraco = None
    melhor_inicio = -1

    buraco = []
    for i, bloco in enumerate(memoria + [1]):
        if bloco == 0:
            buraco.append(i)
        elif buraco:
            if len(buraco) >= tamanho and (melhor_buraco is None or len(buraco) < len(melhor_buraco)):
                melhor_buraco = buraco
                melhor_inicio = buraco[0]
            buraco = []

    if melhor_inicio != -1:
        for i in range(melhor_inicio, melhor_inicio + tamanho):
            memoria[i] = 1
    return melhor_inicio

def quick_fit(memoria, tamanho, listas_quick_fit):
    for i in listas_quick_fit[tamanho]:
        if all(memoria[j] == 0 for j in range(i, i + tamanho)):
            for j in range(i, i + tamanho):
                memoria[j] = 1
            return i
    return -1

def worst_fit(memoria, tamanho):
    maior_buraco = None
    maior_inicio = -1

    buraco = []
    for i, bloco in enumerate(memoria + [1]):
        if bloco == 0:
            buraco.append(i)
        elif buraco:
            if len(buraco) >= tamanho and (maior_buraco is None or len(buraco) > len(maior_buraco)):
                maior_buraco = buraco
                maior_inicio = buraco[0]
            buraco = []

    if maior_inicio != -1:
        for i in range(maior_inicio, maior_inicio + tamanho):
            memoria[i] = 1
    return maior_inicio

# Simulação principal
if __name__ == "__main__":
    tamanhos_processos = [5, 4, 2, 5, 8, 3, 5, 8, 2, 6]
    memoria = cria_memoria(32)
    algoritmos = [first_fit, next_fit, best_fit, worst_fit]
    random.shuffle(tamanhos_processos)

    for algoritmo in algoritmos:
        print(f"\nExecutando: {algoritmo.__name__}")
        memoria = cria_memoria(32)
        ultima_posicao = 0

        for processo_id, tamanho in enumerate(tamanhos_processos):
            print(f"Processo P{processo_id + 1} - Tamanho: {tamanho}")
            alocacao = algoritmo(memoria, tamanho) if algoritmo != next_fit else next_fit(memoria, tamanho, ultima_posicao)
            
            if alocacao != -1:
                print(f"Processo P{processo_id + 1} alocado em {alocacao}")
                ultima_posicao = alocacao + tamanho if algoritmo == next_fit else ultima_posicao
            else:
                print(f"Erro ao alocar Processo P{processo_id + 1}")

            exibir_mapa(memoria)
            fragmentacao_externa(memoria)
