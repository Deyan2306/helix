import co.helix.structure.{DNA, Helix, Nucleotide}
import co.helix.PrettyPrinter
import co.helix.structure.DNA.toString

import scala.util.Random

object Main extends App {

    val dna = DNA.fromString("ATGCGCGATTAGCGCGCTAGCTAGCTAGCGCGCGCGAT").getOrElse(sys.error("Invalid DNA"))

    println(f"Overall GC Content: ${Helix.gcContent(dna)}%.2f%%")
    PrettyPrinter.showGCContentSubsections(dna, 10)

}
