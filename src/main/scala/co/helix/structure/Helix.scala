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

    def transcribe(dna: DNA): RNA = RNA.fromDNA(dna)

    def reverseComplement(dna: DNA): DNA =
        DNA.unsafeCreate(dna.sequence.reverse.map(_.complement))

    def gcContent(dna: DNA): Double = {
        val seq = dna.sequence.map(_.char)
        val gcCount = seq.count(c => c == 'G' || c == 'C')
        (gcCount.toDouble / seq.length) * 100
    }

    def gcContentSubsections(dna: DNA, k: Int = 20): List[Double] = {
        val seq = dna.sequence.map(_.char).mkString
        (0 to seq.length - k by k).map { i =>
            val subseq = seq.slice(i, i + k)
            val gcCount = subseq.count(c => c == 'G' || c == 'C')
            (gcCount.toDouble / subseq.length) * 100
        }.toList
    }

}
