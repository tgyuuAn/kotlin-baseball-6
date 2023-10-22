package baseball

import camp.nextstep.edu.missionutils.Console

fun main() {
    val player = Player()
    val gameManager = GameManager()

    while (gameManager.gameState != GameManager.GameState.ENDED) {
        gameManager.startGame()
        println("타겟 넘버 : ${gameManager.targetNumber}")

        while (gameManager.gameState == GameManager.GameState.INPROGRESS) {
            print("숫자를 입력해주세요 : ")
            val playerInput = Console.readLine()
            player.setGuessNumber(playerInput)
            gameManager.calculateStrikeBall(player.guessNumber)
        }

        println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.")
        val playerChoice = Console.readLine()
    }
}