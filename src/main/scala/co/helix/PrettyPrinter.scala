package co.helix

import co.helix.structure.{DNA, Nucleotide}

object PrettyPrinter {

    // ANSI Colors
    private val RESET = "\u001B[0m"
    private val GREEN = "\u001B[32m"
    private val RED = "\u001B[31m"
    private val BLUE = "\u001B[34m"

    private val nucleotideNames: Map[Nucleotide, String] = Map(
        Nucleotide.A -> "Adenine",
        Nucleotide.C -> "Cytosine",
        Nucleotide.G -> "Guanine",
        Nucleotide.T -> "Thymine"
    )

    def printValidation(dnaResult: Either[String, DNA]): Unit = dnaResult match {
        case Right(dna) =>
            println(s"$GREEN✅ Valid DNA:$RESET ${DNA.toString(dna)}")
        case Left(error) =>
            println(s"$RED❌ Invalid DNA:$RESET $error")
    }

    def printCounts(counts: Map[Nucleotide, Int]): Unit = {
        println(s"\n$BLUE Nucleotide Counts:$RESET")
        println("┌────────────┬───────┐")
        println("│ Name       │ Count │")
        println("├────────────┼───────┤")
        for ((nucleotide, count) <- counts) {
            val name = nucleotideNames(nucleotide)
            println(f"│ ${name.padTo(10, ' ')} │ ${count}%5d │")
        }
        println("└────────────┴───────┘")
    }

    def printRandomDNA(dna: DNA): Unit = {
        println(s"\n🔹 Random DNA (length: ${dna.sequence.length}):")
        println(DNA.toString(dna))
    }

}
