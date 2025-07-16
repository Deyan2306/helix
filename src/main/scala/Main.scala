import co.helix.structure.{DNA, Helix}
import co.helix.PrettyPrinter

import scala.util.Random

object Main extends App {

    val dnaStr = "ACGTACGT"
    val dna = DNA.fromString(dnaStr)

    PrettyPrinter.printValidation(dna)

    dna match {
        case Right(validDna) =>
            // Pretty print nucleotide counts
            val counts = Helix.countNucleotides(validDna)
            PrettyPrinter.printCounts(counts)

            // Pretty print random DNA
            val randomDNA = Helix.randomDNA(10, new Random())
            PrettyPrinter.printRandomDNA(randomDNA)

        case Left(_) => // Already printer an error
    }
}
