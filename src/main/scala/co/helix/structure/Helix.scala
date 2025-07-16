package co.helix.structure

import co.helix.*

import scala.util.Random

object Helix {

    def countNucleotides(dna: DNA): Map[Nucleotide, Int] =
        dna.sequence.groupBy(identity).view.mapValues(_.size).toMap

    def randomDNA(length: Int, rng: Random): DNA = {
        val nucleotides = List.fill(length)(Nucleotide.all(rng.nextInt(Nucleotide.all.size)))
        DNA.unsafeCreate(nucleotides)
    }
}
