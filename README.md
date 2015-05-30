# Bomber Man

Mapa & Outros
* Tamanho do ecrã 20 x 20
* Possibilidade de gravar o estado do jogo.

Jogador
* O jogador possui 3 vidas
* Se o jogador for apanhado por uma explosão o jogo termina
* Cada vida perdida subtrai 5 pontos

Bombas
* Número limitado de 20 bombas
* A bomba ao explodir pode destruir duas posições adjaentes no sentido Norte, Sul, Este e Oeste

Paredes
* Existem paredes de tijolos (blocos  cinzentos) que são destruídos pelas explosões.
* Existem paredes de pedra (blocos pretos) que não são destruídos pelas explosões.
* Quando as paredes de tijolo são todas destruidas o jogo acaba
* Ao explodir uma parede de tijolo,  existe uma probabilidade de 50% de surgir um bonus que ao ser apanhado pelo jogador incrementa as bombas em 2.
* Cada parede de tijolo destruida contabiliza 5 pontos

Inimigos
* Passa a existir inimigos que vagueiam pelo espaço livre. São  destruidos pelas explosões e ao tocarem o jogador, este perde uma vida.
* O jogo começa com um inimigo numa posição aleatória. Ao ser destruido, nasce outro inimigo.
* Por cada inimigo destruído, o jogador ganha 1 bomba.


# Classes, their hierarchy and stuff
Drawable
  * Block
    * Wall
    * Floor
    * Brick
  * Board
    * Matrix
    * Player
  * Temp (Runnable)
    * Bomb
    * Explosion
    * Bonus
  * Movable
    * Player
    * Enemies (Runnable)

Game
  * Board
  * ...