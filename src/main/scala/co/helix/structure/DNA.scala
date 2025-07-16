package co.helix.structure

final case class DNA private (sequence: List[Nucleotide]) {
    def asString: String = sequence.map(Nucleotide.toChar).mkString
}

object DNA {
    private[helix] def unsafeCreate(sequence: List[Nucleotide]): DNA =
        DNA(sequence)

    def fromString(s: String): Either[String, DNA] = {
        val nucleotides = s.toList.foldRight(Right(List.empty[Nucleotide]): Either[String, List[Nucleotide]]) {
            (char, acc) =>
                for {
                    list <- acc
                    nucleotide <- Nucleotide.fromChar(char).toRight(s"Invalid char: ${char}")
                } yield nucleotide :: list
        }

        nucleotides.map(DNA(_))
    }

    def toString(dna: DNA): String =
        dna.sequence.map(Nucleotide.toChar).mkString
}

