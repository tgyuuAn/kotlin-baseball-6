package baseball

class GameManager {

    enum class GameState {
        INIT, INPROGRESS, ENDED
    }

    init {
        println("숫자 야구 게임을 시작합니다.")
    }

    var gameState: GameState = GameState.INIT
        private set

    private val randomTargetGenerator: RandomTargetGenerator by lazy { RandomTargetGenerator() }

    lateinit var targetNumber: List<Int>
        private set

    private val scoreBoard: HashMap<String, Int> = hashMapOf("strike" to 0, "ball" to 0)

    fun startGame() {
        setGameState(GameState.INPROGRESS)
        targetNumber = randomTargetGenerator.generateRandomTarget()
    }

    fun calculateStrikeBall(playerInput: List<Int>) {
        calculateStrike(playerInput)
        calculateBall(playerInput)
        printStrikeBallNothing()
        endGameIfThreeStrikes()
        clearScore()
    }

    private fun calculateStrike(playerInput: List<Int>) {
        playerInput.forEachIndexed { index, value ->
            if (targetNumber[index] == value) {
                scoreBoard["strike"] = scoreBoard["strike"]!! + 1
            }
        }
    }

    private fun calculateBall(playerInput: List<Int>) {
        playerInput.forEachIndexed { index, value ->
            if (value in targetNumber && targetNumber[index] != value) {
                scoreBoard["ball"] = scoreBoard["ball"]!! + 1
            }
        }
    }

    private fun printStrikeBallNothing() {
        if (scoreBoard["strike"]!! == 0 && scoreBoard["ball"]!! == 0) {
            print("낫싱")
        }
        if (scoreBoard["ball"]!! >= 1) {
            print("${scoreBoard["ball"].toString()}볼 ")
        }
        if (scoreBoard["strike"]!! >= 1) {
            print("${scoreBoard["strike"].toString()}스트라이크")
        }
        println()
    }

    private fun endGame() {
        println("3개의 숫자를 모두 맞히셨습니다! 게임 종료")
        setGameState(GameState.ENDED)
    }

    fun setGameState(gameState: GameState) {
        this.gameState = gameState
    }

    fun getScoreBoard(): HashMap<String, Int> {
        return hashMapOf("strike" to scoreBoard["strike"]!!, "ball" to scoreBoard["ball"]!!)
    }

    private fun endGameIfThreeStrikes(){
        if (scoreBoard["strike"] == 3) {
            endGame()
        }
    }

    private fun clearScore() {
        scoreBoard["strike"] = 0
        scoreBoard["ball"] = 0
    }
}