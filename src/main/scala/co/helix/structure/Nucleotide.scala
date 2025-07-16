package co.helix.structure

enum Nucleotide(val char: Char) {
    case A extends Nucleotide('A')
    case C extends Nucleotide('C')
    case G extends Nucleotide('G')
    case T extends Nucleotide('T')
    
    def complement: Nucleotide = this match {
        case A => T
        case T => A
        case C => G
        case G => C
    }
}

object Nucleotide {

    val all: List[Nucleotide] = List(A, C, G, T)

    def fromChar(c: Char): Option[Nucleotide] =
        all.find(_.char == c.toUpper)

    def toChar(n: Nucleotide): Char = n.char
}
