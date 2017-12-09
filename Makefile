PROGRAM_NAME = pc
SRC=src
DIST_FILES=$(SRC) $(PROGRAM_NAME).ps $(PROGRAM_NAME).dvi $(PROGRAM_NAME).pdf Makefile

CHAPTER_1_DIR = "Chapter1"
CHAPTER_2_DIR = "Chapter2"
CHAPTER_3_DIR = "Chapter3"
CHAPTER_4_DIR = "Chapter4"
CHAPTER_5_DIR = "Chapter5"
CHAPTER_6_DIR = "Chapter6"
CHAPTER_7_DIR = "Chapter7"
CHAPTER_8_DIR = "Chapter8"
CHAPTER_9_DIR = "Chapter9"

TASK_CHAPTER_1_1_CHUNK_ID = "3n+1"
TASK_CHAPTER_1_1_FILENAME = "$(SRC)/$(CHAPTER_1_DIR)/Collatz.java"
TASK_CHAPTER_1_2_CHUNK_ID = "Minesweeper"
TASK_CHAPTER_1_2_FILENAME = "$(SRC)/$(CHAPTER_1_DIR)/Minesweeper.java"
TASK_CHAPTER_1_3_CHUNK_ID = "The Trip"
TASK_CHAPTER_1_3_FILENAME = "$(SRC)/$(CHAPTER_1_DIR)/TheTrip.java"
TASK_CHAPTER_1_4_CHUNK_ID= "LC Display"
TASK_CHAPTER_1_4_FILENAME = "$(SRC)/$(CHAPTER_1_DIR)/LCDisplay.java"
TASK_CHAPTER_1_5_CHUNK_ID= "Graphical Editor"
TASK_CHAPTER_1_5_FILENAME = "$(SRC)/$(CHAPTER_1_DIR)/GraphicalEditor.java"
TASK_CHAPTER_1_6_CHUNK_ID= "Interpreter"
TASK_CHAPTER_1_6_FILENAME = "$(SRC)/$(CHAPTER_1_DIR)/Interpreter.java"
TASK_CHAPTER_1_7_CHUNK_ID= "Check The Check"
TASK_CHAPTER_1_7_FILENAME = "$(SRC)/$(CHAPTER_1_DIR)/CheckTheCheck.java"
TASK_CHAPTER_1_8_CHUNK_ID= "Australian Voting"
TASK_CHAPTER_1_8_FILENAME = "$(SRC)/$(CHAPTER_1_DIR)/AustralianVoting.java"

TASK_CHAPTER_2_1_CHUNK_ID = "Jolly Jumpers"
TASK_CHAPTER_2_1_FILENAME = "$(SRC)/$(CHAPTER_2_DIR)/JollyJumpers.java"
TASK_CHAPTER_2_2_CHUNK_ID = "Poker Hands"
TASK_CHAPTER_2_2_FILENAME = "$(SRC)/$(CHAPTER_2_DIR)/PokerHands.java"
TASK_CHAPTER_2_3_CHUNK_ID = "Hartals"
TASK_CHAPTER_2_3_FILENAME = "$(SRC)/$(CHAPTER_2_DIR)/Hartals.java"
TASK_CHAPTER_2_4_CHUNK_ID = "Crypt Kicker"
TASK_CHAPTER_2_4_FILENAME = "$(SRC)/$(CHAPTER_2_DIR)/CryptKicker.java"
TASK_CHAPTER_2_5_CHUNK_ID = "Stack em Up"
TASK_CHAPTER_2_5_FILENAME = "$(SRC)/$(CHAPTER_2_DIR)/StackEmUp.java"
TASK_CHAPTER_2_6_CHUNK_ID = "Erdos Numbers"
TASK_CHAPTER_2_6_FILENAME = "$(SRC)/$(CHAPTER_2_DIR)/ErdosNumbers.java"
TASK_CHAPTER_2_7_CHUNK_ID = "Contest Scoreboard"
TASK_CHAPTER_2_7_FILENAME = "$(SRC)/$(CHAPTER_2_DIR)/ContestScoreboard.java"
TASK_CHAPTER_2_8_CHUNK_ID = "Yahtzee"
TASK_CHAPTER_2_8_FILENAME = "$(SRC)/$(CHAPTER_2_DIR)/Yahtzee.java"

TASK_CHAPTER_3_1_CHUNK_ID = "WERTYU"
TASK_CHAPTER_3_1_FILENAME = "$(SRC)/$(CHAPTER_3_DIR)/WERTYU.java"
TASK_CHAPTER_3_2_CHUNK_ID = "Where is Waldorf"
TASK_CHAPTER_3_2_FILENAME = "$(SRC)/$(CHAPTER_3_DIR)/WheresWaldorf.java"
TASK_CHAPTER_3_3_CHUNK_ID = "Common Permutation"
TASK_CHAPTER_3_3_FILENAME = "$(SRC)/$(CHAPTER_3_DIR)/CommonPermutation.java"
TASK_CHAPTER_3_4_CHUNK_ID = "Crypt Kicker II"
TASK_CHAPTER_3_4_FILENAME = "$(SRC)/$(CHAPTER_3_DIR)/CryptKickerII.java"
TASK_CHAPTER_3_5_CHUNK_ID = "Automated Judge Script"
TASK_CHAPTER_3_5_FILENAME = "$(SRC)/$(CHAPTER_3_DIR)/AutomatedJudgeScript.java"
TASK_CHAPTER_3_6_CHUNK_ID = "File Fragmentation"
TASK_CHAPTER_3_6_FILENAME = "$(SRC)/$(CHAPTER_3_DIR)/FileFragmentation.java"
TASK_CHAPTER_3_7_CHUNK_ID = "Doublets"
TASK_CHAPTER_3_7_FILENAME = "$(SRC)/$(CHAPTER_3_DIR)/Doublets.java"
TASK_CHAPTER_3_8_CHUNK_ID = "Fmt"
TASK_CHAPTER_3_8_FILENAME = "$(SRC)/$(CHAPTER_3_DIR)/Fmt.java"

TASK_CHAPTER_4_1_CHUNK_ID = "Vitos Family"
TASK_CHAPTER_4_1_FILENAME = "$(SRC)/$(CHAPTER_4_DIR)/VitosFamily.java"
TASK_CHAPTER_4_2_CHUNK_ID = "Stacks of Flapjacks"
TASK_CHAPTER_4_2_FILENAME = "$(SRC)/$(CHAPTER_4_DIR)/StacksOfFlapjacks.java"
TASK_CHAPTER_4_3_CHUNK_ID = "Bridge"
TASK_CHAPTER_4_3_FILENAME = "$(SRC)/$(CHAPTER_4_DIR)/Bridge.java"
TASK_CHAPTER_4_4_CHUNK_ID = "Longest Nap"
TASK_CHAPTER_4_4_FILENAME = "$(SRC)/$(CHAPTER_4_DIR)/LongestNap.java"
TASK_CHAPTER_4_5_CHUNK_ID = "Shoemakers Problem"
TASK_CHAPTER_4_5_FILENAME = "$(SRC)/$(CHAPTER_4_DIR)/ShoemakersProblem.java"
TASK_CHAPTER_4_6_CHUNK_ID = "CDVII"
TASK_CHAPTER_4_6_FILENAME = "$(SRC)/$(CHAPTER_4_DIR)/CDVII.java"
TASK_CHAPTER_4_7_CHUNK_ID = "ShellSort"
TASK_CHAPTER_4_7_FILENAME = "$(SRC)/$(CHAPTER_4_DIR)/ShellSort.java"
TASK_CHAPTER_4_8_CHUNK_ID = "Football aka Soccer"
TASK_CHAPTER_4_8_FILENAME = "$(SRC)/$(CHAPTER_4_DIR)/FootballAkaSoccer.java"

TASK_CHAPTER_5_1_CHUNK_ID = "Primary Arithmetic"
TASK_CHAPTER_5_1_FILENAME = "$(SRC)/$(CHAPTER_5_DIR)/PrimaryArithmetic.java"
TASK_CHAPTER_5_2_CHUNK_ID = "Reverse And Add"
TASK_CHAPTER_5_2_FILENAME = "$(SRC)/$(CHAPTER_5_DIR)/ReverseAndAdd.java"
TASK_CHAPTER_5_3_CHUNK_ID = "The Archeologists Dilemma"
TASK_CHAPTER_5_3_FILENAME = "$(SRC)/$(CHAPTER_5_DIR)/TheArcheologistsDilemma.java"
TASK_CHAPTER_5_4_CHUNK_ID = "Ones"
TASK_CHAPTER_5_4_FILENAME = "$(SRC)/$(CHAPTER_5_DIR)/Ones.java"
TASK_CHAPTER_5_5_CHUNK_ID = "A Multiplication Game"
TASK_CHAPTER_5_5_FILENAME = "$(SRC)/$(CHAPTER_5_DIR)/MultiplicationGame.java"
TASK_CHAPTER_5_6_CHUNK_ID = "Polynomial Coefficients"
TASK_CHAPTER_5_6_FILENAME = "$(SRC)/$(CHAPTER_5_DIR)/PolynomialCoefficients.java"
TASK_CHAPTER_5_7_CHUNK_ID = "The Stern-Brocot Number System"
TASK_CHAPTER_5_7_FILENAME = "$(SRC)/$(CHAPTER_5_DIR)/TheSternBrocotNumberSystem.java"
TASK_CHAPTER_5_8_CHUNK_ID = "Pairsumonious Numbers"
TASK_CHAPTER_5_8_FILENAME = "$(SRC)/$(CHAPTER_5_DIR)/PairsumoniousNumbers.java"

TASK_CHAPTER_6_1_CHUNK_ID = "How Many Fibs"
TASK_CHAPTER_6_1_FILENAME = "$(SRC)/$(CHAPTER_6_DIR)/HowManyFibs.java"
TASK_CHAPTER_6_2_CHUNK_ID = "How Many Pieces of Land"
TASK_CHAPTER_6_2_FILENAME = "$(SRC)/$(CHAPTER_6_DIR)/HowManyPiecesOfLand.java"
TASK_CHAPTER_6_3_CHUNK_ID = "Counting"
TASK_CHAPTER_6_3_FILENAME = "$(SRC)/$(CHAPTER_6_DIR)/Counting.java"
TASK_CHAPTER_6_4_CHUNK_ID = "Expressions"
TASK_CHAPTER_6_4_FILENAME = "$(SRC)/$(CHAPTER_6_DIR)/Expressions.java"
TASK_CHAPTER_6_5_CHUNK_ID = "Complete Tree Labeling"
TASK_CHAPTER_6_5_FILENAME = "$(SRC)/$(CHAPTER_6_DIR)/CompleteTreeLabeling.java"
TASK_CHAPTER_6_6_CHUNK_ID = "The Priest Mathematician"
TASK_CHAPTER_6_6_FILENAME = "$(SRC)/$(CHAPTER_6_DIR)/ThePriestMathematician.java"
TASK_CHAPTER_6_7_CHUNK_ID = "Self Describing Sequence"
TASK_CHAPTER_6_7_FILENAME = "$(SRC)/$(CHAPTER_6_DIR)/SelfDescribingSequence.java"
TASK_CHAPTER_6_8_CHUNK_ID = "Steps"
TASK_CHAPTER_6_8_FILENAME = "$(SRC)/$(CHAPTER_6_DIR)/Steps.java"

TASK_CHAPTER_7_1_CHUNK_ID = "Light, More Light"
TASK_CHAPTER_7_1_FILENAME = "$(SRC)/$(CHAPTER_7_DIR)/LightMoreLight.java"
TASK_CHAPTER_7_2_CHUNK_ID = "Carmichael Numbers"
TASK_CHAPTER_7_2_FILENAME = "$(SRC)/$(CHAPTER_7_DIR)/CarmichaelNumbers.java"
TASK_CHAPTER_7_4_CHUNK_ID = "Factovisors"
TASK_CHAPTER_7_4_FILENAME = "$(SRC)/$(CHAPTER_7_DIR)/Factovisors.java"
TASK_CHAPTER_7_5_CHUNK_ID = "Summation of Four Primes"
TASK_CHAPTER_7_5_FILENAME = "$(SRC)/$(CHAPTER_7_DIR)/SummationOfFourPrimes.java"
TASK_CHAPTER_7_6_CHUNK_ID = "Smith Numbers"
TASK_CHAPTER_7_6_FILENAME = "$(SRC)/$(CHAPTER_7_DIR)/SmithNumbers.java"
TASK_CHAPTER_7_7_CHUNK_ID = "Marbles"
TASK_CHAPTER_7_7_FILENAME = "$(SRC)/$(CHAPTER_7_DIR)/Marbles.java"

TASK_CHAPTER_8_1_CHUNK_ID = "Little Bishops"
TASK_CHAPTER_8_1_FILENAME = "$(SRC)/$(CHAPTER_8_DIR)/LittleBishops.java"
TASK_CHAPTER_8_2_CHUNK_ID = "15 Puzzle Problem"
TASK_CHAPTER_8_2_FILENAME = "$(SRC)/$(CHAPTER_8_DIR)/The15PuzzleProblem.java"

TASK_CHAPTER_9_1_CHUNK_ID = "Bicoloring"
TASK_CHAPTER_9_1_FILENAME = "$(SRC)/$(CHAPTER_9_DIR)/Bicoloring.java"
TASK_CHAPTER_9_2_CHUNK_ID = "Playing With Wheels"
TASK_CHAPTER_9_2_FILENAME = "$(SRC)/$(CHAPTER_9_DIR)/PlayingWithWheels.java"
TASK_CHAPTER_9_3_CHUNK_ID = "The Tourist Guide"
TASK_CHAPTER_9_3_FILENAME = "$(SRC)/$(CHAPTER_9_DIR)/TheTouristGuide.java"
TASK_CHAPTER_9_4_CHUNK_ID = "Slash Maze"
TASK_CHAPTER_9_4_FILENAME = "$(SRC)/$(CHAPTER_9_DIR)/SlashMaze.java"
TASK_CHAPTER_9_5_CHUNK_ID = "Edit Step Ladders"
TASK_CHAPTER_9_5_FILENAME = "$(SRC)/$(CHAPTER_9_DIR)/EditStepLadders.java"

all: dirs $(TASK_CHAPTER_1_1_CHUNK_ID) $(TASK_CHAPTER_1_2_CHUNK_ID) $(TASK_CHAPTER_1_3_CHUNK_ID) $(TASK_CHAPTER_1_4_CHUNK_ID) $(TASK_CHAPTER_1_5_CHUNK_ID) $(TASK_CHAPTER_1_6_CHUNK_ID) $(TASK_CHAPTER_1_7_CHUNK_ID) $(TASK_CHAPTER_1_8_CHUNK_ID) $(TASK_CHAPTER_2_1_CHUNK_ID) $(TASK_CHAPTER_2_2_CHUNK_ID) $(TASK_CHAPTER_2_3_CHUNK_ID) $(TASK_CHAPTER_2_5_CHUNK_ID) $(TASK_CHAPTER_2_4_CHUNK_ID) $(TASK_CHAPTER_2_6_CHUNK_ID) $(TASK_CHAPTER_2_7_CHUNK_ID) $(TASK_CHAPTER_2_8_CHUNK_ID) $(TASK_CHAPTER_3_1_CHUNK_ID) $(TASK_CHAPTER_3_2_CHUNK_ID) $(TASK_CHAPTER_3_3_CHUNK_ID) $(TASK_CHAPTER_3_4_CHUNK_ID) $(TASK_CHAPTER_3_5_CHUNK_ID) $(TASK_CHAPTER_3_6_CHUNK_ID) $(TASK_CHAPTER_3_7_CHUNK_ID) $(TASK_CHAPTER_3_8_CHUNK_ID) $(TASK_CHAPTER_4_1_CHUNK_ID) $(TASK_CHAPTER_4_2_CHUNK_ID) $(TASK_CHAPTER_4_3_CHUNK_ID) $(TASK_CHAPTER_4_4_CHUNK_ID) $(TASK_CHAPTER_4_5_CHUNK_ID) $(TASK_CHAPTER_4_6_CHUNK_ID) $(TASK_CHAPTER_4_7_CHUNK_ID) $(TASK_CHAPTER_4_8_CHUNK_ID) $(TASK_CHAPTER_5_1_CHUNK_ID) $(TASK_CHAPTER_5_2_CHUNK_ID) $(TASK_CHAPTER_5_3_CHUNK_ID) $(TASK_CHAPTER_5_4_CHUNK_ID) $(TASK_CHAPTER_5_5_CHUNK_ID) $(TASK_CHAPTER_5_6_CHUNK_ID) $(TASK_CHAPTER_5_7_CHUNK_ID) $(TASK_CHAPTER_5_8_CHUNK_ID) $(TASK_CHAPTER_6_1_CHUNK_ID) $(TASK_CHAPTER_6_2_CHUNK_ID) $(TASK_CHAPTER_6_3_CHUNK_ID) $(TASK_CHAPTER_6_4_CHUNK_ID) $(TASK_CHAPTER_6_5_CHUNK_ID) $(TASK_CHAPTER_6_6_CHUNK_ID) $(TASK_CHAPTER_6_7_CHUNK_ID) $(TASK_CHAPTER_6_8_CHUNK_ID) $(TASK_CHAPTER_7_1_CHUNK_ID) $(TASK_CHAPTER_7_2_CHUNK_ID) $(TASK_CHAPTER_7_3_CHUNK_ID) $(TASK_CHAPTER_7_4_CHUNK_ID) $(TASK_CHAPTER_7_5_CHUNK_ID) $(TASK_CHAPTER_7_6_CHUNK_ID) $(TASK_CHAPTER_7_7_CHUNK_ID) $(TASK_CHAPTER_8_1_CHUNK_ID) $(TASK_CHAPTER_8_2_CHUNK_ID) $(TASK_CHAPTER_9_1_CHUNK_ID) $(TASK_CHAPTER_9_2_CHUNK_ID) $(TASK_CHAPTER_9_3_CHUNK_ID) $(TASK_CHAPTER_9_4_CHUNK_ID) $(TASK_CHAPTER_9_5_CHUNK_ID) $(PROGRAM_NAME) dist 

$(TASK_CHAPTER_1_1_CHUNK_ID): $(PROGRAM_NAME).nw
	notangle -R$(TASK_CHAPTER_1_1_CHUNK_ID) $(PROGRAM_NAME).nw > $(TASK_CHAPTER_1_1_FILENAME)

$(TASK_CHAPTER_1_2_CHUNK_ID): $(PROGRAM_NAME).nw
	notangle -R$(TASK_CHAPTER_1_2_CHUNK_ID) $(PROGRAM_NAME).nw > $(TASK_CHAPTER_1_2_FILENAME)

$(TASK_CHAPTER_1_3_CHUNK_ID): $(PROGRAM_NAME).nw
	notangle -R$(TASK_CHAPTER_1_3_CHUNK_ID) $(PROGRAM_NAME).nw > $(TASK_CHAPTER_1_3_FILENAME)

$(TASK_CHAPTER_1_4_CHUNK_ID): $(PROGRAM_NAME).nw
	notangle -R$(TASK_CHAPTER_1_4_CHUNK_ID) $(PROGRAM_NAME).nw > $(TASK_CHAPTER_1_4_FILENAME)

$(TASK_CHAPTER_1_5_CHUNK_ID): $(PROGRAM_NAME).nw
	notangle -R$(TASK_CHAPTER_1_5_CHUNK_ID) $(PROGRAM_NAME).nw > $(TASK_CHAPTER_1_5_FILENAME)

$(TASK_CHAPTER_1_6_CHUNK_ID): $(PROGRAM_NAME).nw
	notangle -R$(TASK_CHAPTER_1_6_CHUNK_ID) $(PROGRAM_NAME).nw > $(TASK_CHAPTER_1_6_FILENAME)

$(TASK_CHAPTER_1_7_CHUNK_ID): $(PROGRAM_NAME).nw
	notangle -R$(TASK_CHAPTER_1_7_CHUNK_ID) $(PROGRAM_NAME).nw > $(TASK_CHAPTER_1_7_FILENAME)

$(TASK_CHAPTER_1_8_CHUNK_ID): $(PROGRAM_NAME).nw
	notangle -R$(TASK_CHAPTER_1_8_CHUNK_ID) $(PROGRAM_NAME).nw > $(TASK_CHAPTER_1_8_FILENAME)

$(TASK_CHAPTER_2_1_CHUNK_ID): $(PROGRAM_NAME).nw
	notangle -R$(TASK_CHAPTER_2_1_CHUNK_ID) $(PROGRAM_NAME).nw > $(TASK_CHAPTER_2_1_FILENAME)

$(TASK_CHAPTER_2_2_CHUNK_ID): $(PROGRAM_NAME).nw
	notangle -R$(TASK_CHAPTER_2_2_CHUNK_ID) $(PROGRAM_NAME).nw > $(TASK_CHAPTER_2_2_FILENAME)

$(TASK_CHAPTER_2_3_CHUNK_ID): $(PROGRAM_NAME).nw
	notangle -R$(TASK_CHAPTER_2_3_CHUNK_ID) $(PROGRAM_NAME).nw > $(TASK_CHAPTER_2_3_FILENAME)

$(TASK_CHAPTER_2_4_CHUNK_ID): $(PROGRAM_NAME).nw
	notangle -R$(TASK_CHAPTER_2_4_CHUNK_ID) $(PROGRAM_NAME).nw > $(TASK_CHAPTER_2_4_FILENAME)

$(TASK_CHAPTER_2_5_CHUNK_ID): $(PROGRAM_NAME).nw
	notangle -R$(TASK_CHAPTER_2_5_CHUNK_ID) $(PROGRAM_NAME).nw > $(TASK_CHAPTER_2_5_FILENAME)

$(TASK_CHAPTER_2_6_CHUNK_ID): $(PROGRAM_NAME).nw
	notangle -R$(TASK_CHAPTER_2_6_CHUNK_ID) $(PROGRAM_NAME).nw > $(TASK_CHAPTER_2_6_FILENAME)

$(TASK_CHAPTER_2_7_CHUNK_ID): $(PROGRAM_NAME).nw
	notangle -R$(TASK_CHAPTER_2_7_CHUNK_ID) $(PROGRAM_NAME).nw > $(TASK_CHAPTER_2_7_FILENAME)

$(TASK_CHAPTER_2_8_CHUNK_ID): $(PROGRAM_NAME).nw
	notangle -R$(TASK_CHAPTER_2_8_CHUNK_ID) $(PROGRAM_NAME).nw > $(TASK_CHAPTER_2_8_FILENAME)

$(TASK_CHAPTER_3_1_CHUNK_ID): $(PROGRAM_NAME).nw
	notangle -R$(TASK_CHAPTER_3_1_CHUNK_ID) $(PROGRAM_NAME).nw > $(TASK_CHAPTER_3_1_FILENAME)

$(TASK_CHAPTER_3_2_CHUNK_ID): $(PROGRAM_NAME).nw
	notangle -R$(TASK_CHAPTER_3_2_CHUNK_ID) $(PROGRAM_NAME).nw > $(TASK_CHAPTER_3_2_FILENAME)

$(TASK_CHAPTER_3_3_CHUNK_ID): $(PROGRAM_NAME).nw
	notangle -R$(TASK_CHAPTER_3_3_CHUNK_ID) $(PROGRAM_NAME).nw > $(TASK_CHAPTER_3_3_FILENAME)

$(TASK_CHAPTER_3_4_CHUNK_ID): $(PROGRAM_NAME).nw
	notangle -R$(TASK_CHAPTER_3_4_CHUNK_ID) $(PROGRAM_NAME).nw > $(TASK_CHAPTER_3_4_FILENAME)

$(TASK_CHAPTER_3_5_CHUNK_ID): $(PROGRAM_NAME).nw
	notangle -R$(TASK_CHAPTER_3_5_CHUNK_ID) $(PROGRAM_NAME).nw > $(TASK_CHAPTER_3_5_FILENAME)

$(TASK_CHAPTER_3_6_CHUNK_ID): $(PROGRAM_NAME).nw
	notangle -R$(TASK_CHAPTER_3_6_CHUNK_ID) $(PROGRAM_NAME).nw > $(TASK_CHAPTER_3_6_FILENAME)

$(TASK_CHAPTER_3_7_CHUNK_ID): $(PROGRAM_NAME).nw
	notangle -R$(TASK_CHAPTER_3_7_CHUNK_ID) $(PROGRAM_NAME).nw > $(TASK_CHAPTER_3_7_FILENAME)

$(TASK_CHAPTER_3_8_CHUNK_ID): $(PROGRAM_NAME).nw
	notangle -R$(TASK_CHAPTER_3_8_CHUNK_ID) $(PROGRAM_NAME).nw > $(TASK_CHAPTER_3_8_FILENAME)

$(TASK_CHAPTER_4_1_CHUNK_ID): $(PROGRAM_NAME).nw
	notangle -R$(TASK_CHAPTER_4_1_CHUNK_ID) $(PROGRAM_NAME).nw > $(TASK_CHAPTER_4_1_FILENAME)

$(TASK_CHAPTER_4_2_CHUNK_ID): $(PROGRAM_NAME).nw
	notangle -R$(TASK_CHAPTER_4_2_CHUNK_ID) $(PROGRAM_NAME).nw > $(TASK_CHAPTER_4_2_FILENAME)

$(TASK_CHAPTER_4_3_CHUNK_ID): $(PROGRAM_NAME).nw
	notangle -R$(TASK_CHAPTER_4_3_CHUNK_ID) $(PROGRAM_NAME).nw > $(TASK_CHAPTER_4_3_FILENAME)

$(TASK_CHAPTER_4_4_CHUNK_ID): $(PROGRAM_NAME).nw
	notangle -R$(TASK_CHAPTER_4_4_CHUNK_ID) $(PROGRAM_NAME).nw > $(TASK_CHAPTER_4_4_FILENAME)

$(TASK_CHAPTER_4_5_CHUNK_ID): $(PROGRAM_NAME).nw
	notangle -R$(TASK_CHAPTER_4_5_CHUNK_ID) $(PROGRAM_NAME).nw > $(TASK_CHAPTER_4_5_FILENAME)

$(TASK_CHAPTER_4_6_CHUNK_ID): $(PROGRAM_NAME).nw
	notangle -R$(TASK_CHAPTER_4_6_CHUNK_ID) $(PROGRAM_NAME).nw > $(TASK_CHAPTER_4_6_FILENAME)

$(TASK_CHAPTER_4_7_CHUNK_ID): $(PROGRAM_NAME).nw
	notangle -R$(TASK_CHAPTER_4_7_CHUNK_ID) $(PROGRAM_NAME).nw > $(TASK_CHAPTER_4_7_FILENAME)

$(TASK_CHAPTER_4_8_CHUNK_ID): $(PROGRAM_NAME).nw
	notangle -R$(TASK_CHAPTER_4_8_CHUNK_ID) $(PROGRAM_NAME).nw > $(TASK_CHAPTER_4_8_FILENAME)

$(TASK_CHAPTER_5_1_CHUNK_ID): $(PROGRAM_NAME).nw
	notangle -R$(TASK_CHAPTER_5_1_CHUNK_ID) $(PROGRAM_NAME).nw > $(TASK_CHAPTER_5_1_FILENAME)

$(TASK_CHAPTER_5_2_CHUNK_ID): $(PROGRAM_NAME).nw
	notangle -R$(TASK_CHAPTER_5_2_CHUNK_ID) $(PROGRAM_NAME).nw > $(TASK_CHAPTER_5_2_FILENAME)

$(TASK_CHAPTER_5_3_CHUNK_ID): $(PROGRAM_NAME).nw
	notangle -R$(TASK_CHAPTER_5_3_CHUNK_ID) $(PROGRAM_NAME).nw > $(TASK_CHAPTER_5_3_FILENAME)

$(TASK_CHAPTER_5_4_CHUNK_ID): $(PROGRAM_NAME).nw
	notangle -R$(TASK_CHAPTER_5_4_CHUNK_ID) $(PROGRAM_NAME).nw > $(TASK_CHAPTER_5_4_FILENAME)

$(TASK_CHAPTER_5_5_CHUNK_ID): $(PROGRAM_NAME).nw
	notangle -R$(TASK_CHAPTER_5_5_CHUNK_ID) $(PROGRAM_NAME).nw > $(TASK_CHAPTER_5_5_FILENAME)

$(TASK_CHAPTER_5_6_CHUNK_ID): $(PROGRAM_NAME).nw
	notangle -R$(TASK_CHAPTER_5_6_CHUNK_ID) $(PROGRAM_NAME).nw > $(TASK_CHAPTER_5_6_FILENAME)

$(TASK_CHAPTER_5_7_CHUNK_ID): $(PROGRAM_NAME).nw
	notangle -R$(TASK_CHAPTER_5_7_CHUNK_ID) $(PROGRAM_NAME).nw > $(TASK_CHAPTER_5_7_FILENAME)

$(TASK_CHAPTER_5_8_CHUNK_ID): $(PROGRAM_NAME).nw
	notangle -R$(TASK_CHAPTER_5_8_CHUNK_ID) $(PROGRAM_NAME).nw > $(TASK_CHAPTER_5_8_FILENAME)

$(TASK_CHAPTER_6_1_CHUNK_ID): $(PROGRAM_NAME).nw
	notangle -R$(TASK_CHAPTER_6_1_CHUNK_ID) $(PROGRAM_NAME).nw > $(TASK_CHAPTER_6_1_FILENAME)

$(TASK_CHAPTER_6_2_CHUNK_ID): $(PROGRAM_NAME).nw
	notangle -R$(TASK_CHAPTER_6_2_CHUNK_ID) $(PROGRAM_NAME).nw > $(TASK_CHAPTER_6_2_FILENAME)

$(TASK_CHAPTER_6_3_CHUNK_ID): $(PROGRAM_NAME).nw
	notangle -R$(TASK_CHAPTER_6_3_CHUNK_ID) $(PROGRAM_NAME).nw > $(TASK_CHAPTER_6_3_FILENAME)

$(TASK_CHAPTER_6_4_CHUNK_ID): $(PROGRAM_NAME).nw
	notangle -R$(TASK_CHAPTER_6_4_CHUNK_ID) $(PROGRAM_NAME).nw > $(TASK_CHAPTER_6_4_FILENAME)

$(TASK_CHAPTER_6_5_CHUNK_ID): $(PROGRAM_NAME).nw
	notangle -R$(TASK_CHAPTER_6_5_CHUNK_ID) $(PROGRAM_NAME).nw > $(TASK_CHAPTER_6_5_FILENAME)

$(TASK_CHAPTER_6_6_CHUNK_ID): $(PROGRAM_NAME).nw
	notangle -R$(TASK_CHAPTER_6_6_CHUNK_ID) $(PROGRAM_NAME).nw > $(TASK_CHAPTER_6_6_FILENAME)

$(TASK_CHAPTER_6_7_CHUNK_ID): $(PROGRAM_NAME).nw
	notangle -R$(TASK_CHAPTER_6_7_CHUNK_ID) $(PROGRAM_NAME).nw > $(TASK_CHAPTER_6_7_FILENAME)

$(TASK_CHAPTER_6_8_CHUNK_ID): $(PROGRAM_NAME).nw
	notangle -R$(TASK_CHAPTER_6_8_CHUNK_ID) $(PROGRAM_NAME).nw > $(TASK_CHAPTER_6_8_FILENAME)

$(TASK_CHAPTER_7_1_CHUNK_ID): $(PROGRAM_NAME).nw
	notangle -R$(TASK_CHAPTER_7_1_CHUNK_ID) $(PROGRAM_NAME).nw > $(TASK_CHAPTER_7_1_FILENAME)

$(TASK_CHAPTER_7_2_CHUNK_ID): $(PROGRAM_NAME).nw
	notangle -R$(TASK_CHAPTER_7_2_CHUNK_ID) $(PROGRAM_NAME).nw > $(TASK_CHAPTER_7_2_FILENAME)

$(TASK_CHAPTER_7_3_CHUNK_ID): $(PROGRAM_NAME).nw
	notangle -R$(TASK_CHAPTER_7_3_CHUNK_ID) $(PROGRAM_NAME).nw > $(TASK_CHAPTER_7_3_FILENAME)

$(TASK_CHAPTER_7_4_CHUNK_ID): $(PROGRAM_NAME).nw
	notangle -R$(TASK_CHAPTER_7_4_CHUNK_ID) $(PROGRAM_NAME).nw > $(TASK_CHAPTER_7_4_FILENAME)

$(TASK_CHAPTER_7_5_CHUNK_ID): $(PROGRAM_NAME).nw
	notangle -R$(TASK_CHAPTER_7_5_CHUNK_ID) $(PROGRAM_NAME).nw > $(TASK_CHAPTER_7_5_FILENAME)

$(TASK_CHAPTER_7_6_CHUNK_ID): $(PROGRAM_NAME).nw
	notangle -R$(TASK_CHAPTER_7_6_CHUNK_ID) $(PROGRAM_NAME).nw > $(TASK_CHAPTER_7_6_FILENAME)

$(TASK_CHAPTER_7_7_CHUNK_ID): $(PROGRAM_NAME).nw
	notangle -R$(TASK_CHAPTER_7_7_CHUNK_ID) $(PROGRAM_NAME).nw > $(TASK_CHAPTER_7_7_FILENAME)

$(TASK_CHAPTER_8_1_CHUNK_ID): $(PROGRAM_NAME).nw
	notangle -R$(TASK_CHAPTER_8_1_CHUNK_ID) $(PROGRAM_NAME).nw > $(TASK_CHAPTER_8_1_FILENAME)

$(TASK_CHAPTER_8_2_CHUNK_ID): $(PROGRAM_NAME).nw
	notangle -R$(TASK_CHAPTER_8_2_CHUNK_ID) $(PROGRAM_NAME).nw > $(TASK_CHAPTER_8_2_FILENAME)

$(TASK_CHAPTER_9_1_CHUNK_ID): $(PROGRAM_NAME).nw
	notangle -R$(TASK_CHAPTER_9_1_CHUNK_ID) $(PROGRAM_NAME).nw > $(TASK_CHAPTER_9_1_FILENAME)

$(TASK_CHAPTER_9_2_CHUNK_ID): $(PROGRAM_NAME).nw
	notangle -R$(TASK_CHAPTER_9_2_CHUNK_ID) $(PROGRAM_NAME).nw > $(TASK_CHAPTER_9_2_FILENAME)

$(TASK_CHAPTER_9_3_CHUNK_ID): $(PROGRAM_NAME).nw
	notangle -R$(TASK_CHAPTER_9_3_CHUNK_ID) $(PROGRAM_NAME).nw > $(TASK_CHAPTER_9_3_FILENAME)

$(TASK_CHAPTER_9_4_CHUNK_ID): $(PROGRAM_NAME).nw
	notangle -R$(TASK_CHAPTER_9_4_CHUNK_ID) $(PROGRAM_NAME).nw > $(TASK_CHAPTER_9_4_FILENAME)

$(TASK_CHAPTER_9_5_CHUNK_ID): $(PROGRAM_NAME).nw
	notangle -R$(TASK_CHAPTER_9_5_CHUNK_ID) $(PROGRAM_NAME).nw > $(TASK_CHAPTER_9_5_FILENAME)

$(PROGRAM_NAME).tex: 
	noweave -latex -index -delay $(PROGRAM_NAME).nw > $(PROGRAM_NAME).tex

$(PROGRAM_NAME).dvi: $(PROGRAM_NAME).tex
	latex -quiet $(PROGRAM_NAME).tex && latex -quiet $(PROGRAM_NAME).tex

$(PROGRAM_NAME).ps: $(PROGRAM_NAME).dvi
	dvips -q* $(PROGRAM_NAME).dvi

$(PROGRAM_NAME).pdf: $(PROGRAM_NAME).ps
	ps2pdf $(PROGRAM_NAME).ps

dirs:
	mkdir -p $(SRC)/$(CHAPTER_1_DIR) $(SRC)/$(CHAPTER_2_DIR) $(SRC)/$(CHAPTER_3_DIR) $(SRC)/$(CHAPTER_4_DIR) $(SRC)/$(CHAPTER_5_DIR) $(SRC)/$(CHAPTER_6_DIR) $(SRC)/$(CHAPTER_7_DIR) $(SRC)/$(CHAPTER_8_DIR) $(SRC)/$(CHAPTER_9_DIR)

dist:
	tar -cf $(PROGRAM_NAME).tar $(DIST_FILES) && gzip $(PROGRAM_NAME).tar

clean:
	-rm -f $(PROGRAM_NAME).toc $(PROGRAM_NAME).log $(PROGRAM_NAME).tex $(PROGRAM_NAME).aux $(PROGRAM_NAME).dvi $(PROGRAM_NAME).ps $(PROGRAM_NAME).pdf $(TASK_CHAPTER_1_1_FILENAME) $(TASK_CHAPTER_1_2_FILENAME) $(TASK_CHAPTER_1_3_FILENAME) $(TASK_CHAPTER_1_5_FILENAME) $(TASK_CHAPTER_1_4_FILENAME) $(TASK_CHAPTER_1_6_FILENAME) $(TASK_CHAPTER_1_7_FILENAME) $(TASK_CHAPTER_1_8_FILENAME) $(TASK_CHAPTER_2_1_FILENAME) $(TASK_CHAPTER_2_2_FILENAME) $(TASK_CHAPTER_2_3_FILENAME) $(TASK_CHAPTER_2_4_FILENAME) $(TASK_CHAPTER_2_5_FILENAME) $(TASK_CHAPTER_2_6_FILENAME) $(TASK_CHAPTER_2_7_FILENAME) $(TASK_CHAPTER_2_8_FILENAME) $(TASK_CHAPTER_3_1_FILENAME) $(TASK_CHAPTER_3_2_FILENAME) $(TASK_CHAPTER_3_3_FILENAME) $(TASK_CHAPTER_3_4_FILENAME) $(TASK_CHAPTER_3_5_FILENAME) $(TASK_CHAPTER_3_6_FILENAME) $(TASK_CHAPTER_3_7_FILENAME) $(TASK_CHAPTER_3_8_FILENAME)  $(TASK_CHAPTER_4_1_FILENAME) $(TASK_CHAPTER_4_2_FILENAME) $(TASK_CHAPTER_4_3_FILENAME) $(TASK_CHAPTER_4_4_FILENAME) $(TASK_CHAPTER_4_5_FILENAME) $(TASK_CHAPTER_4_6_FILENAME) $(TASK_CHAPTER_4_7_FILENAME) $(TASK_CHAPTER_4_8_FILENAME) $(TASK_CHAPTER_5_1_FILENAME) $(TASK_CHAPTER_5_2_FILENAME) $(TASK_CHAPTER_5_3_FILENAME) $(TASK_CHAPTER_5_4_FILENAME) $(TASK_CHAPTER_5_5_FILENAME) $(TASK_CHAPTER_5_6_FILENAME) $(TASK_CHAPTER_5_7_FILENAME) $(TASK_CHAPTER_5_8_FILENAME) $(TASK_CHAPTER_6_1_FILENAME)  $(TASK_CHAPTER_6_2_FILENAME) $(TASK_CHAPTER_6_3_FILENAME) $(TASK_CHAPTER_6_4_FILENAME) $(TASK_CHAPTER_6_5_FILENAME) $(TASK_CHAPTER_6_6_FILENAME) $(TASK_CHAPTER_6_7_FILENAME) $(TASK_CHAPTER_6_8_FILENAME) $(TASK_CHAPTER_7_1_FILENAME) $(TASK_CHAPTER_7_1_FILENAME) $(TASK_CHAPTER_7_2_FILENAME) $(TASK_CHAPTER_7_3_FILENAME) $(TASK_CHAPTER_7_4_FILENAME) $(TASK_CHAPTER_7_5_FILENAME) $(TASK_CHAPTER_7_6_FILENAME) $(TASK_CHAPTER_7_7_FILENAME) $(TASK_CHAPTER_8_1_FILENAME) $(TASK_CHAPTER_8_2_FILENAME) $(TASK_CHAPTER_9_1_FILENAME) $(TASK_CHAPTER_9_2_FILENAME) $(TASK_CHAPTER_9_3_FILENAME) $(TASK_CHAPTER_9_4_FILENAME) $(TASK_CHAPTER_9_5_FILENAME) $(PROGRAM_NAME).tar.gz

$(PROGRAM_NAME): $(DIST_FILES)

