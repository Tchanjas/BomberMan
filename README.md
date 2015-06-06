# Bomber Man

Mapa & Outros
* [x] Tamanho do ecrã 20 x 20
* [ ] Possibilidade de gravar o estado do jogo.

Jogador
* [x] O jogador possui 3 vidas
* [ ] Se o jogador for apanhado por uma explosão o jogo termina
* [x] Cada vida perdida subtrai 5 pontos

Bombas
* [x] Número limitado de 20 bombas
* [x] A bomba ao explodir pode destruir duas posições adjaentes no sentido Norte, Sul, Este e Oeste

Paredes
* [x] Existem paredes de tijolos (blocos  cinzentos) que são destruídos pelas explosões.
* [x] Existem paredes de pedra (blocos pretos) que não são destruídos pelas explosões.
* [ ] Quando as paredes de tijolo são todas destruidas o jogo acaba
* [ ] Ao explodir uma parede de tijolo,  existe uma probabilidade de 50% de surgir um bonus que ao ser apanhado pelo jogador incrementa as bombas em 2.
* [x] Cada parede de tijolo destruida contabiliza 5 pontos

Inimigos
* [x] Passa a existir inimigos que vagueiam pelo espaço livre.
* [x] São  destruidos pelas explosões.
* [x] Ao ser destruido, nasce outro inimigo.
* [x] Ao tocarem o jogador, este perde uma vida.
* [x] O jogo começa com um inimigo numa posição aleatória.
* [x] Por cada inimigo destruído, o jogador ganha 1 bomba.


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