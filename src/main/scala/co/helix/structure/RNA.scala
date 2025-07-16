package co.helix.structure

final case class RNA(sequence: String) {
    override def toString: String = sequence
}

object RNA {
    def fromDNA(dna: DNA): RNA =
        RNA(dna.sequence.map {
            case Nucleotide.A => 'A'
            case Nucleotide.C => 'C'
            case Nucleotide.G => 'G'
            case Nucleotide.T => 'U'
        }.mkString)
}
